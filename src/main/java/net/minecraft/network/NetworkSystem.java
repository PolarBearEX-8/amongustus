package net.minecraft.network;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.Future;
import java.util.concurrent.Callable;
import net.minecraft.client.network.NetHandlerHandshakeMemory;
import net.minecraft.server.network.NetHandlerHandshakeTCP;
import net.minecraft.util.MessageDeserializer;
import net.minecraft.util.MessageDeserializer2;
import net.minecraft.util.MessageSerializer;
import net.minecraft.util.MessageSerializer2;
import com.google.common.collect.Lists;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ReportedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetworkSystem {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final LazyLoadBase eventLoops = new NetworkSystem.DefaultLazyLoadBase();
   public static final LazyLoadBase SERVER_EPOLL_EVENTLOOP = new NetworkSystem.DefaultLazyLoadBase2();
   public static final LazyLoadBase SERVER_LOCAL_EVENTLOOP = new NetworkSystem.DefaultLazyLoadBase3();
   private final MinecraftServer mcServer;
   public volatile boolean isAlive;
   private final List endpoints = Collections.synchronizedList(Lists.newArrayList());
   private final List networkManagers = Collections.synchronizedList(Lists.newArrayList());

   public static MinecraftServer accessMcServer(NetworkSystem networkSystem) {
      return networkSystem.mcServer;
   }

   public void terminateEndpoints() {
      this.isAlive = false;

      for (ChannelFuture channelFuture : (Iterable<ChannelFuture>)(this.endpoints)) {
         try {
            channelFuture.channel().close().sync();
         } catch (InterruptedException e) {
            LOGGER.error("Interrupted whilst closing channel");
         }
      }
   }

   public MinecraftServer getServer() {
      return this.mcServer;
   }

   public void networkTick() {
      synchronized (this.networkManagers) {
         Iterator it = this.networkManagers.iterator();

         while (it.hasNext()) {
            NetworkManager networkManager = (NetworkManager)it.next();
            if (!networkManager.hasNoChannel()) {
               if (!networkManager.isChannelOpen()) {
                  it.remove();
                  networkManager.checkDisconnected();
               } else {
                  try {
                     networkManager.processReceivedPackets();
                  } catch (Exception e) {
                     if (networkManager.isLocalChannel()) {
                        CrashReport crashReport = CrashReport.makeCrashReport(e, "Ticking memory connection");
                        crashReport.makeCategory("Ticking connection").addCrashSectionCallable("Connection", new NetworkSystem.ValueCallable(this, networkManager));
                        throw new ReportedException(crashReport);
                     }

                     LOGGER.warn("Failed to handle packet for " + networkManager.getRemoteAddress(), e);
                     ChatComponentText chatComponentText = new ChatComponentText("Internal server error");
                     networkManager.sendPacket(new S40PacketDisconnect(chatComponentText), new NetworkSystem.DefaultGenericFutureListener(this, networkManager, chatComponentText), new GenericFutureListener[0]);
                     networkManager.disableAutoRead();
                  }
               }
            }
         }
      }
   }

   public NetworkSystem(MinecraftServer minecraftServer) {
      this.mcServer = minecraftServer;
      this.isAlive = true;
   }

   public static List accessNetworkManagers(NetworkSystem networkSystem) {
      return networkSystem.networkManagers;
   }

   public SocketAddress addLocalEndpoint() {
      ChannelFuture channelFuture;
      synchronized (this.endpoints) {
         channelFuture = ((ServerBootstrap)((ServerBootstrap)new ServerBootstrap().channel(LocalServerChannel.class))
               .childHandler(new NetworkSystem.PipelineInitializer2(this))
               .group((EventLoopGroup)eventLoops.getValue())
               .localAddress(LocalAddress.ANY))
            .bind()
            .syncUninterruptibly();
         this.endpoints.add(channelFuture);
      }

      return channelFuture.channel().localAddress();
   }

   public void addLanEndpoint(InetAddress inetAddress, int n) throws IOException {
      synchronized (this.endpoints) {
         Class clazz;
         LazyLoadBase lazyLoadBase;
         if (Epoll.isAvailable() && this.mcServer.shouldUseNativeTransport()) {
            clazz = EpollServerSocketChannel.class;
            lazyLoadBase = SERVER_EPOLL_EVENTLOOP;
            LOGGER.info("Using epoll channel type");
         } else {
            clazz = NioServerSocketChannel.class;
            lazyLoadBase = eventLoops;
            LOGGER.info("Using default channel type");
         }

         this.endpoints
            .add(
               ((ServerBootstrap)((ServerBootstrap)new ServerBootstrap().channel(clazz))
                     .childHandler(new NetworkSystem.PipelineInitializer(this))
                     .group((EventLoopGroup)lazyLoadBase.getValue())
                     .localAddress(inetAddress, n))
                  .bind()
                  .syncUninterruptibly()
            );
      }
   }


   public static final class DefaultLazyLoadBase extends LazyLoadBase {
      public NioEventLoopGroup loadEventLoopGroup() {
         return new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Server IO #%d").setDaemon(true).build());
      }

      @Override
      public Object load() {
         return this.loadEventLoopGroup();
      }
   }

   public static final class DefaultLazyLoadBase2 extends LazyLoadBase {
      public EpollEventLoopGroup loadEventLoopGroup() {
         return new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Server IO #%d").setDaemon(true).build());
      }

      @Override
      public Object load() {
         return this.loadEventLoopGroup();
      }
   }

   public static final class DefaultLazyLoadBase3 extends LazyLoadBase {
      @Override
      public Object load() {
         return this.loadEventLoopGroup();
      }

      public LocalEventLoopGroup loadEventLoopGroup() {
         return new LocalEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Local Server IO #%d").setDaemon(true).build());
      }
   }

   public static class PipelineInitializer extends ChannelInitializer {
      public final NetworkSystem networkSystem;

      public PipelineInitializer(NetworkSystem networkSystem2) {
         this.networkSystem = networkSystem2;
      }

      public void initChannel(Channel channel) throws Exception {
         try {
            channel.config().setOption(ChannelOption.TCP_NODELAY, Boolean.TRUE);
         } catch (ChannelException e) {
         }

         channel.pipeline()
            .addLast("timeout", new ReadTimeoutHandler(30))
            .addLast("legacy_query", new PingResponseHandler(this.networkSystem))
            .addLast("splitter", new MessageDeserializer2())
            .addLast("decoder", new MessageDeserializer(EnumPacketDirection.SERVERBOUND))
            .addLast("prepender", new MessageSerializer2())
            .addLast("encoder", new MessageSerializer(EnumPacketDirection.CLIENTBOUND));
         NetworkManager networkManager = new NetworkManager(EnumPacketDirection.SERVERBOUND);
         NetworkSystem.accessNetworkManagers(this.networkSystem).add(networkManager);
         channel.pipeline().addLast("packet_handler", networkManager);
         networkManager.setNetHandler(new NetHandlerHandshakeTCP(NetworkSystem.accessMcServer(this.networkSystem), networkManager));
      }
   }

   public static class PipelineInitializer2 extends ChannelInitializer {
      public final NetworkSystem networkSystem;

      public PipelineInitializer2(NetworkSystem networkSystem2) {
         this.networkSystem = networkSystem2;
      }

      public void initChannel(Channel channel) throws Exception {
         NetworkManager networkManager = new NetworkManager(EnumPacketDirection.SERVERBOUND);
         networkManager.setNetHandler(new NetHandlerHandshakeMemory(NetworkSystem.accessMcServer(this.networkSystem), networkManager));
         NetworkSystem.accessNetworkManagers(this.networkSystem).add(networkManager);
         channel.pipeline().addLast("packet_handler", networkManager);
      }
   }

   public static class ValueCallable implements Callable {
      public final NetworkManager networkManager;
      public final NetworkSystem networkSystem;

      public String callAsString() throws Exception {
         return this.networkManager.toString();
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable(NetworkSystem networkSystem2, NetworkManager networkManager2) {
         this.networkSystem = networkSystem2;
         this.networkManager = networkManager2;
      }
   }

   public static class DefaultGenericFutureListener implements GenericFutureListener {
      public final NetworkManager networkManager;
      public final ChatComponentText disconnectReason;
      public final NetworkSystem networkSystem;

      public void operationComplete(Future future) throws Exception {
         this.networkManager.closeChannel(this.disconnectReason);
      }

      public DefaultGenericFutureListener(NetworkSystem networkSystem2, NetworkManager networkManager2, ChatComponentText chatComponentText) {
         this.networkSystem = networkSystem2;
         this.networkManager = networkManager2;
         this.disconnectReason = chatComponentText;
      }
   }
}
