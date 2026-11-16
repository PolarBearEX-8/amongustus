package client.util;

import client.network.ChannelInitializerAdapter;
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
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.util.ChatComponentText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLoggerEntry {
   public static final Logger logger = LogManager.getLogger();
   public static final InterpolationEstimator interpolationEstimator = new AuxiliaryClientManagerDispatcher();
   public static final InterpolationEstimator interpolationEstimator2 = new ClientManagerProcessor();
   public static final InterpolationEstimator interpolationEstimator3 = new InternalClientManagerDispatcher();
   public final VanillaRunnable vanillaRunnable;
   public volatile boolean enabled;
   public final List list = Collections.synchronizedList(Lists.newArrayList());
   public final List list2 = Collections.synchronizedList(Lists.newArrayList());

   public VanillaRunnable getVanillaRunnable() {
      return this.vanillaRunnable;
   }

   public void applyInetAddress(InetAddress inetAddress, int count) throws IOException {
      synchronized (this.list) {
         Class<? extends io.netty.channel.socket.ServerSocketChannel> clazz;
         InterpolationEstimator interpolationEstimator4;
         if (Epoll.isAvailable() && this.vanillaRunnable.isActive4()) {
            clazz = EpollServerSocketChannel.class;
            interpolationEstimator4 = interpolationEstimator2;
            logger.info("Using epoll channel type");
         } else {
            clazz = NioServerSocketChannel.class;
            interpolationEstimator4 = interpolationEstimator;
            logger.info("Using default channel type");
         }

         this.list
            .add(
               ((ServerBootstrap)((ServerBootstrap)new ServerBootstrap().channel(clazz))
                     .childHandler(new TimeoutLegacyChannelInitializer(this))
                     .group((EventLoopGroup)interpolationEstimator4.getTargetAsTarget())
                     .localAddress(inetAddress, count))
                  .bind()
                  .syncUninterruptibly()
            );
      }
   }

   public void updateState2() {
      synchronized (this.list2) {
         Iterator iterator = this.list2.iterator();

         while (iterator.hasNext()) {
            NetworkManager networkManager = (NetworkManager)iterator.next();
            if (!networkManager.isActiveAsFlag()) {
               if (!networkManager.isOpen()) {
                  iterator.remove();
                  networkManager.updateState3();
               } else {
                  try {
                     networkManager.updateState2();
                  } catch (Exception exception) {
                     if (networkManager.isActive()) {
                        CrashReport crashReport = CrashReport.resolveCrashReport(exception, "Ticking memory connection");
                        crashReport.resolveCrashReportCategory("Ticking connection").applyText("Connection", new NetworkManagerTask(this, networkManager));
                        throw new SecondaryRuntimeExceptionAdapter(crashReport);
                     }

                     logger.warn("Failed to handle packet for " + networkManager.getSocketAddress(), exception);
                     ChatComponentText chatComponentText = new ChatComponentText("Internal server error");
                     networkManager.applyPacket(new S40PacketDisconnect(chatComponentText), new NetworkManagerGenericFutureListener(this, networkManager, chatComponentText), new GenericFutureListener[0]);
                     networkManager.updateState4();
                  }
               }
            }
         }
      }
   }

   public SocketAddress getSocketAddress() {
      ChannelFuture channelFuture;
      synchronized (this.list) {
         channelFuture = ((ServerBootstrap)((ServerBootstrap)new ServerBootstrap().channel(LocalServerChannel.class))
               .childHandler(new ChannelInitializerAdapter(this))
               .group((EventLoopGroup)interpolationEstimator.getTargetAsTarget())
               .localAddress(LocalAddress.ANY))
            .bind()
            .syncUninterruptibly();
         this.list.add(channelFuture);
      }

      return channelFuture.channel().localAddress();
   }

   public CustomLoggerEntry(VanillaRunnable vanillaRunnable2) {
      this.vanillaRunnable = vanillaRunnable2;
      this.enabled = true;
   }

   public void updateState() {
      this.enabled = false;

      for (ChannelFuture channelFuture : (Iterable<ChannelFuture>)(Iterable<?>)this.list) {
         try {
            channelFuture.channel().close().sync();
         } catch (InterruptedException interruptedException) {
            logger.error("Interrupted whilst closing channel");
         }
      }
   }

   public static VanillaRunnable resolveVanillaRunnable(CustomLoggerEntry customLoggerEntry) {
      return customLoggerEntry.vanillaRunnable;
   }

   public static List resolveList(CustomLoggerEntry customLoggerEntry) {
      return customLoggerEntry.list2;
   }
}
