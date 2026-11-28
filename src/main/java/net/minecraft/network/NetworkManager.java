package net.minecraft.network;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import net.augustus.utils.network.ProxyEntry;
import net.minecraft.util.MessageDeserializer;
import net.minecraft.util.MessageDeserializer2;
import net.minecraft.util.MessageSerializer;
import net.minecraft.util.MessageSerializer2;
import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.event.events.PacketEvent;
import baritone.api.event.events.type.EventState;
import com.google.common.collect.Queues;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.TimeoutException;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import net.augustus.modules.latency.PacketHandler;
import net.augustus.events.EventPacket;
import net.augustus.utils.network.NetworkStatsTrafficHandler;
import net.augustus.utils.network.PacketFlushMarker;
import net.augustus.ClientManagers;
import net.augustus.utils.ChatUtil;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.viaversion.ViaAugustus;
import net.augustus.Augustus;
import net.augustus.events.PacketStage;
import net.augustus.events.listeners.PacketListener;
import net.augustus.utils.network.ServerEntityPositionTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.CryptManager;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.Vec3;
import net.raphimc.vialegacy.api.LegacyProtocolVersion;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class NetworkManager extends SimpleChannelInboundHandler {
   private static final Logger logger = LogManager.getLogger();
   public static final Marker logMarkerNetwork = MarkerManager.getMarker("NETWORK");
   public static final Marker logMarkerPackets = MarkerManager.getMarker("NETWORK_PACKETS", logMarkerNetwork);
   public static final AttributeKey attrKeyConnectionState = AttributeKey.valueOf("protocol");
   public static final LazyLoadBase CLIENT_NIO_EVENTLOOP = new NetworkManager.DefaultLazyLoadBase();
   public static final LazyLoadBase CLIENT_EPOLL_EVENTLOOP = new NetworkManager.DefaultLazyLoadBase2();
   public static final LazyLoadBase CLIENT_LOCAL_EVENTLOOP = new NetworkManager.DefaultLazyLoadBase3();
   private final EnumPacketDirection direction;
   private final Queue outboundPacketsQueue = Queues.newConcurrentLinkedQueue();
   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   public Channel channel;
   private SocketAddress socketAddress;
   private INetHandler packetListener;
   private IChatComponent terminationReason;
   private boolean isEncrypted;
   private boolean disconnected;
   private Cipher decryptionCipher;

   public void sendPacket(Packet packet, GenericFutureListener listener, GenericFutureListener[] listeners) {
      if (this.isChannelOpen()) {
         this.flushOutboundQueue();
         this.dispatchPacket(packet, (GenericFutureListener[])ArrayUtils.add(listeners, 0, listener));
      } else {
         this.readWriteLock.writeLock().lock();

         try {
            this.outboundPacketsQueue.add(new NetworkManager.InboundHandlerTuplePacketListener(packet, (GenericFutureListener[])ArrayUtils.add(listeners, 0, listener)));
         } finally {
            this.readWriteLock.writeLock().unlock();
         }
      }
   }

   public void enableEncryption(SecretKey secretKey) {
      this.decryptionCipher = CryptManager.createNetCipherInstance(2, secretKey);
      Cipher cipher = CryptManager.createNetCipherInstance(1, secretKey);
      if (ViaAugustus.targetProtocolVersion.olderThanOrEqualTo(LegacyProtocolVersion.r1_6_4)) {
         this.channel.pipeline().addBefore("vialegacy-pre-netty-length-remover", "encrypt", new NettyEncryptingEncoder(cipher));
      } else {
         this.channel.pipeline().addBefore("splitter", "decrypt", new NettyEncryptingDecoder(this.decryptionCipher));
         this.channel.pipeline().addBefore("prepender", "encrypt", new NettyEncryptingEncoder(cipher));
      }

      this.isEncrypted = true;
   }

   public boolean isChannelOpen() {
      return this.channel != null && this.channel.isOpen();
   }

   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
      this.closeChannel(new ChatComponentTranslation("disconnect.endOfStream", new Object[0]));
   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      cause.printStackTrace();
      ChatComponentTranslation chatComponentTranslation;
      if (cause instanceof TimeoutException) {
         chatComponentTranslation = new ChatComponentTranslation("disconnect.timeout", new Object[0]);
      } else {
         chatComponentTranslation = new ChatComponentTranslation("disconnect.genericReason", new Object[]{"Internal Exception: " + cause});
      }

      this.closeChannel(chatComponentTranslation);
   }

   private void flushOutboundQueue() {
      if (this.channel != null && this.channel.isOpen()) {
         this.readWriteLock.readLock().lock();

         try {
            while (!this.outboundPacketsQueue.isEmpty()) {
               NetworkManager.InboundHandlerTuplePacketListener listener = (NetworkManager.InboundHandlerTuplePacketListener)this.outboundPacketsQueue.poll();
               Packet packet = NetworkManager.InboundHandlerTuplePacketListener.accessPacket(listener);
               if ((packet instanceof C03PacketPlayer.C04PacketPlayerPosition || packet instanceof C03PacketPlayer.C06PacketPlayerPosLook)
                  && Minecraft.getMinecraft().theWorld != null
                  && Minecraft.getMinecraft().thePlayer != null) {
                  C03PacketPlayer packet2 = (C03PacketPlayer)packet;
                  Minecraft.getMinecraft().thePlayer.setPrevServerPos(Minecraft.getMinecraft().thePlayer.getServerPos());
                  Minecraft.getMinecraft().thePlayer.setServerPos(new Vec3(packet2.getPositionX(), packet2.getPositionY(), packet2.getPositionZ()));
               }

               if (!(packet instanceof PacketFlushMarker)) {
                  PacketListener.postPacket(packet, PacketStage.SEND_NETTY_DELAY);
                  this.dispatchPacket(packet, NetworkManager.InboundHandlerTuplePacketListener.accessFutureListeners(listener));
               }
            }
         } finally {
            this.readWriteLock.readLock().unlock();
         }
      }
   }

   private void dispatchPacket(Packet packet, GenericFutureListener[] listeners) {
      if (Augustus.getInstance().getModuleManager().baritone.isToggled() && this.direction == EnumPacketDirection.CLIENTBOUND) {
         for (IBaritone baritone : (Iterable<IBaritone>)(BaritoneAPI.getProvider().getAllBaritones())) {
            if (baritone.getPlayerContext().player() != null && baritone.getPlayerContext().player().sendQueue.getNetworkManager() == this) {
               baritone.getGameEventHandler().onSendPacket(new PacketEvent(this, EventState.PRE, packet));
            }
         }
      }

      if (packet instanceof PacketFlushMarker) {
         ChatUtil.sendChat("That should not happen!");
      }

      ChannelHandler channelHandler = this.channel.pipeline().get("network-stats-tracker");
      if (channelHandler instanceof NetworkStatsTrafficHandler) {
         ((NetworkStatsTrafficHandler)channelHandler).recordOutgoingPacket();
      }

      EnumConnectionState fromPacket = EnumConnectionState.getFromPacket(packet);
      EnumConnectionState enumConnectionState = (EnumConnectionState)this.channel.attr(attrKeyConnectionState).get();
      if (enumConnectionState != fromPacket) {
         logger.debug("Disabled auto read");
         this.channel.config().setAutoRead(false);
      }

      if (this.channel.eventLoop().inEventLoop()) {
         if (fromPacket != enumConnectionState) {
            this.setConnectionState(fromPacket);
         }

         ChannelFuture channelFuture = this.channel.writeAndFlush(packet);
         if (listeners != null) {
            channelFuture.addListeners(listeners);
         }

         channelFuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
      } else {
         this.channel.eventLoop().execute(new NetworkManager.WorkerTask(this, fromPacket, enumConnectionState, packet, listeners));
      }

      if (Augustus.getInstance().getModuleManager().baritone.isToggled() && this.direction != EnumPacketDirection.CLIENTBOUND) {
         for (IBaritone baritone2 : (Iterable<IBaritone>)(BaritoneAPI.getProvider().getAllBaritones())) {
            if (baritone2.getPlayerContext().player() != null && baritone2.getPlayerContext().player().sendQueue.getNetworkManager() == this) {
               baritone2.getGameEventHandler().onSendPacket(new PacketEvent(this, EventState.POST, packet));
            }
         }
      }
   }

   public boolean hasNoChannel() {
      return this.channel == null;
   }

   private void processPacketDirect(Packet packet) {
      try {
         if (this.channel.isOpen()) {
            if (packet instanceof PacketFlushMarker) {
               return;
            }

            packet.processPacket(this.packetListener);
         }
      } catch (ThreadQuickExitException e) {
      }
   }

   public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
      this.channelRead0(ctx, (Packet)msg);
   }

   public void checkDisconnected() {
      if (this.channel != null && !this.channel.isOpen()) {
         if (!this.disconnected) {
            this.disconnected = true;
            if (this.getExitMessage() != null) {
               this.getNetHandler().onDisconnect(this.getExitMessage());
            } else if (this.getNetHandler() != null) {
               this.getNetHandler().onDisconnect(new ChatComponentText("Disconnected"));
            }
         } else {
            logger.warn("handleDisconnection() called twice");
         }
      }
   }

   public static NetworkManager createNetworkManagerAndConnect(InetAddress inetAddress, int n, boolean flag) {
      return createNetworkManagerAndConnect(inetAddress, n, flag, true);
   }

   public synchronized void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
      if (this.channel.isOpen()) {
         ChannelHandler channelHandler = this.channel.pipeline().get("network-stats-tracker");
         if (channelHandler instanceof NetworkStatsTrafficHandler) {
            ((NetworkStatsTrafficHandler)channelHandler).recordIncomingPacket();
         }

         try {
            ServerEntityPositionTracker.onPacket(msg, this.direction);
         } catch (Exception e) {
         }

         try {
            EventPacket event = PacketListener.postPacket(msg, PacketStage.READ_NETTY);
            if (event.isCancelled()) {
               return;
            }

            if (ClientManagers.moduleManager.packetHandler.shouldDelayIncomingPacket(msg) && this.direction == EnumPacketDirection.CLIENTBOUND) {
               EnumPacketDirection direction3 = this.direction;
               EventPacketDelay event2 = PacketDelayListener.postIncomingPacketDelay(msg, this.packetListener, direction3);
               Packet packet = event2.getPacket();
               boolean flag = ClientManagers.moduleManager.packetHandler.shouldHoldTransactionInMacroDelay(packet);
               if (event2.isDelayed() || event2.hasIncomingDelay() && ClientManagers.moduleManager.packetHandler.isWorldAndPlayerReady() || flag) {
                  if (!event2.isDelayed()
                     && (!event2.hasIncomingDelay() || !ClientManagers.moduleManager.packetHandler.isWorldAndPlayerReady())
                     && ClientManagers.moduleManager.packetHandler.macroDelayModes.getSelectedValue().equals("ExperimentalMacroDelay")
                     && Minecraft.getMinecraft().thePlayer != null
                     && Minecraft.getMinecraft().thePlayer.ticksExisted > 5
                     && packet instanceof S00PacketKeepAlive) {
                     ClientManagers.moduleManager.packetHandler.setFlushingPackets(true);
                  } else {
                     ClientManagers.moduleManager.packetHandler.setFlushingPackets(false);
                  }

                  ClientManagers.moduleManager.packetHandler.enqueueIncomingPacket(event2);
                  if (event2.hasIncomingDelay() && !event2.isDelayed()) {
                     PacketHandler packetHandler = ClientManagers.moduleManager.packetHandler;
                     packetHandler.flushExpiredIncomingPackets(event2.getIncomingDelayMs());
                     packetHandler = ClientManagers.moduleManager.packetHandler;
                     EntityLivingBase targetEntity = event2.getTargetEntity();
                     packetHandler.flushIncomingUntilTargetRange(event2.getMaxTargetDistance(), targetEntity);
                  }

                  if (event2.isDelayed() && !event2.hasIncomingDelay()) {
                     PacketHandler packetHandler2 = ClientManagers.moduleManager.packetHandler;
                     EntityLivingBase targetEntity2 = event2.getTargetEntity();
                     packetHandler2.flushIncomingUntilTargetRange(event2.getMaxTargetDistance(), targetEntity2);
                  }

                  return;
               }

               ClientManagers.moduleManager.packetHandler.flushAllIncomingPackets();
            }

            if (ClientManagers.moduleManager.packetHandler.shouldAbortIncomingDelay(msg)) {
               ClientManagers.moduleManager.packetHandler.flushAllIncomingPackets();
            }

            if (msg instanceof PacketFlushMarker) {
               return;
            }

            ClientManagers.moduleManager.packetHandler.cleanupTrackedEntityPacket(msg);
            if (Augustus.getInstance().getModuleManager().baritone.isToggled() && this.direction == EnumPacketDirection.CLIENTBOUND) {
               for (IBaritone baritone : (Iterable<IBaritone>)(BaritoneAPI.getProvider().getAllBaritones())) {
                  if (baritone.getPlayerContext().player() != null && baritone.getPlayerContext().player().sendQueue.getNetworkManager() == this) {
                     baritone.getGameEventHandler().onReceivePacket(new PacketEvent(this, EventState.PRE, msg));
                  }
               }
            }

            event.getPacket().processPacket(this.packetListener);
         } catch (ThreadQuickExitException e2) {
         }

         if (Augustus.getInstance().getModuleManager().baritone.isToggled() && this.direction == EnumPacketDirection.CLIENTBOUND) {
            for (IBaritone baritone2 : (Iterable<IBaritone>)(BaritoneAPI.getProvider().getAllBaritones())) {
               if (baritone2.getPlayerContext().player() != null && baritone2.getPlayerContext().player().sendQueue.getNetworkManager() == this) {
                  baritone2.getGameEventHandler().onReceivePacket(new PacketEvent(this, EventState.POST, msg));
               }
            }
         }
      }
   }

   public void setNetHandler(INetHandler netHandler) {
      Validate.notNull(netHandler, "packetListener", new Object[0]);
      logger.debug("Set listener of {} to {}", new Object[]{this, netHandler});
      this.packetListener = netHandler;
   }

   public void setConnectionState(EnumConnectionState enumConnectionState) {
      this.channel.attr(attrKeyConnectionState).set(enumConnectionState);
      this.channel.config().setAutoRead(true);
      logger.debug("Enabled auto read");
   }

   public NetworkManager(EnumPacketDirection enumPacketDirection) {
      this.direction = enumPacketDirection;
   }

   public INetHandler getNetHandler() {
      return this.packetListener;
   }

   public void disableAutoRead() {
      this.channel.config().setAutoRead(false);
   }

   public void processPacketDirect(Packet packet, boolean flag) {
      if (!flag) {
         this.processPacketDirect(packet);
      } else {
         CompletableFuture completableFuture = CompletableFuture.runAsync(() -> this.processPacketDirect(packet));

         while (!completableFuture.isDone()) {
         }
      }
   }

   public void processReceivedPackets() {
      this.flushOutboundQueue();
      if (this.packetListener instanceof ITickable) {
         ((ITickable)this.packetListener).update();
      }

      if (this.channel != null) {
         this.channel.flush();
      }
   }

   public void receivePacketAsync(Packet packet) {
      CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
         try {
            Minecraft.getMinecraft().getNetHandler().getNetworkManager().channelRead0(null, packet);
         } catch (Exception e) {
         }
      });

      while (!completableFuture.isDone()) {
      }
   }

   public boolean getIsencrypted() {
      return this.isEncrypted;
   }

   public static NetworkManager createNetworkManagerAndConnect(InetAddress inetAddress, int n, boolean flag, boolean flag2) {
      NetworkManager networkManager = new NetworkManager(EnumPacketDirection.CLIENTBOUND);
      Class clazz;
      LazyLoadBase lazyLoadBase;
      if (Epoll.isAvailable() && flag) {
         clazz = EpollSocketChannel.class;
         lazyLoadBase = CLIENT_EPOLL_EVENTLOOP;
      } else {
         clazz = NioSocketChannel.class;
         lazyLoadBase = CLIENT_NIO_EVENTLOOP;
      }

      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group((EventLoopGroup)lazyLoadBase.getValue());
      bootstrap.handler(new NetworkManager.PipelineInitializer(flag2, networkManager, flag));
      ((Bootstrap)bootstrap.channel(clazz)).connect(inetAddress, n).syncUninterruptibly();
      return networkManager;
   }

   public void closeChannel(IChatComponent component) {
      if (this.channel.isOpen()) {
         this.channel.close().awaitUninterruptibly();
         this.terminationReason = component;
      }
   }

   public IChatComponent getExitMessage() {
      return this.terminationReason;
   }

   public static NetworkManager provideLocalClient(SocketAddress socketAddress2) {
      NetworkManager networkManager = new NetworkManager(EnumPacketDirection.CLIENTBOUND);
      ((Bootstrap)((Bootstrap)((Bootstrap)new Bootstrap().group((EventLoopGroup)CLIENT_LOCAL_EVENTLOOP.getValue())).handler(new NetworkManager.PipelineInitializer2(networkManager)))
            .channel(LocalChannel.class))
         .connect(socketAddress2)
         .syncUninterruptibly();
      return networkManager;
   }

   public void channelActive(ChannelHandlerContext ctx) throws Exception {
      super.channelActive(ctx);
      this.channel = ctx.channel();
      this.socketAddress = this.channel.remoteAddress();

      try {
         this.setConnectionState(EnumConnectionState.HANDSHAKING);
      } catch (Throwable e) {
         logger.fatal(e);
      }
   }

   public void enableViaLegacyDecryption() {
      this.channel.pipeline().addBefore("vialegacy-pre-netty-length-prepender", "decrypt", new NettyEncryptingEncoder(this.decryptionCipher));
   }

   public void setCompressionTreshold(int n) {
      if (n >= 0) {
         if (this.channel.pipeline().get("decompress") instanceof NettyCompressionDecoder) {
            ((NettyCompressionDecoder)this.channel.pipeline().get("decompress")).setCompressionTreshold(n);
         } else {
            this.channel.pipeline().addBefore("decoder", "decompress", new NettyCompressionDecoder(n));
         }

         if (this.channel.pipeline().get("compress") instanceof NettyCompressionEncoder) {
            ((NettyCompressionEncoder)this.channel.pipeline().get("decompress")).setCompressionTreshold(n);
         } else {
            this.channel.pipeline().addBefore("encoder", "compress", new NettyCompressionEncoder(n));
         }
      } else {
         if (this.channel.pipeline().get("decompress") instanceof NettyCompressionDecoder) {
            this.channel.pipeline().remove("decompress");
         }

         if (this.channel.pipeline().get("compress") instanceof NettyCompressionEncoder) {
            this.channel.pipeline().remove("compress");
         }
      }

      if (ViaAugustus.targetProtocolVersion != ProtocolVersion.v1_8) {
         ViaAugustus.reorderCompressionHandlers(this.channel.pipeline());
      }
   }

   public SocketAddress getRemoteAddress() {
      return this.socketAddress;
   }

   public void sendPacket(Packet packet) {
      if (!PacketListener.postPacket(packet, PacketStage.SEND_NETTY).isCancelled()) {
         if (ClientManagers.moduleManager.packetHandler.shouldDelayOutgoingPacket(packet)) {
            ReentrantReadWriteLock readWriteLock2 = this.readWriteLock;
            EventPacketDelay event2 = PacketDelayListener.postOutgoingPacketDelay(packet, this.outboundPacketsQueue, readWriteLock2);
            if (event2.isDelayed() || event2.hasOutgoingDelay() && ClientManagers.moduleManager.packetHandler.isWorldAndPlayerReady()) {
               ClientManagers.moduleManager.packetHandler.enqueueOutgoingPacket(event2);
               if (event2.hasOutgoingDelay() && !event2.isDelayed()) {
                  ClientManagers.moduleManager.packetHandler.flushExpiredOutgoingPackets(event2, event2.getOutgoingDelayMs());
                  this.flushOutboundQueue();
               }

               return;
            }

            ReentrantReadWriteLock readWriteLock3 = this.readWriteLock;
            Queue outboundPacketsQueue3 = this.outboundPacketsQueue;
            ClientManagers.moduleManager.packetHandler.flushAllOutgoingPackets(outboundPacketsQueue3, readWriteLock3);
         }

         if (ClientManagers.moduleManager.packetHandler.shouldAbortOutgoingDelay(packet)) {
            ReentrantReadWriteLock readWriteLock4 = this.readWriteLock;
            Queue outboundPacketsQueue4 = this.outboundPacketsQueue;
            ClientManagers.moduleManager.packetHandler.flushAllOutgoingPackets(outboundPacketsQueue4, readWriteLock4);
         }

         if (this.isChannelOpen()) {
            this.flushOutboundQueue();
            if ((packet instanceof C03PacketPlayer.C04PacketPlayerPosition || packet instanceof C03PacketPlayer.C06PacketPlayerPosLook)
               && Minecraft.getMinecraft().theWorld != null
               && Minecraft.getMinecraft().thePlayer != null) {
               C03PacketPlayer packet2 = (C03PacketPlayer)packet;
               Minecraft.getMinecraft().thePlayer.setPrevServerPos(Minecraft.getMinecraft().thePlayer.getServerPos());
               Minecraft.getMinecraft().thePlayer.setServerPos(new Vec3(packet2.getPositionX(), packet2.getPositionY(), packet2.getPositionZ()));
            }

            if (!(packet instanceof PacketFlushMarker)) {
               PacketListener.postPacket(packet, PacketStage.SEND_NETTY_DELAY);
               this.dispatchPacket(packet, (GenericFutureListener[])null);
            }
         } else {
            this.readWriteLock.writeLock().lock();

            try {
               this.outboundPacketsQueue.add(new NetworkManager.InboundHandlerTuplePacketListener(packet, (GenericFutureListener[])null));
            } finally {
               this.readWriteLock.writeLock().unlock();
            }
         }
      }
   }

   public boolean isLocalChannel() {
      return this.channel instanceof LocalChannel || this.channel instanceof LocalServerChannel;
   }



   public static final class DefaultLazyLoadBase extends LazyLoadBase {
      @Override
      public Object load() {
         return this.loadEventLoopGroup();
      }

      public NioEventLoopGroup loadEventLoopGroup() {
         return new NioEventLoopGroup(2, new ThreadFactoryBuilder().setNameFormat("Netty Client IO #%d").setDaemon(true).build());
      }

   }

   public static final class DefaultLazyLoadBase2 extends LazyLoadBase {
      public EpollEventLoopGroup loadEventLoopGroup() {
         return new EpollEventLoopGroup(2, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Client IO #%d").setDaemon(true).build());
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
         return new LocalEventLoopGroup(2, new ThreadFactoryBuilder().setNameFormat("Netty Local Client IO #%d").setDaemon(true).build());
      }

   }

   public static class WorkerTask implements Runnable {
      public final EnumConnectionState packetState;
      public final EnumConnectionState currentState;
      public final Packet packet;
      public final GenericFutureListener[] futureListeners;
      public final NetworkManager networkManager;

      @Override
      public void run() {
         if (this.packetState != this.currentState) {
            this.networkManager.setConnectionState(this.packetState);
         }

         ChannelFuture channelFuture = this.networkManager.channel.writeAndFlush(this.packet);
         if (this.futureListeners != null) {
            channelFuture.addListeners(this.futureListeners);
         }

         channelFuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
      }

      public WorkerTask(NetworkManager networkManager2, EnumConnectionState enumConnectionState, EnumConnectionState enumConnectionState2, Packet packet2, GenericFutureListener[] listeners) {
         this.networkManager = networkManager2;
         this.packetState = enumConnectionState;
         this.currentState = enumConnectionState2;
         this.packet = packet2;
         this.futureListeners = listeners;
      }
   }

   public static final class PipelineInitializer extends ChannelInitializer {
      public final boolean useProxy;
      public final NetworkManager networkManager;
      public final boolean trackNetworkStats;

      public void initChannel(Channel channel) {

         try {
            channel.config().setOption(ChannelOption.TCP_NODELAY, Boolean.TRUE);
         } catch (ChannelException e) {
         }

         if (Augustus.getInstance().getProxy() != null && this.useProxy) {
            Augustus.getInstance().getProxy().applyToChannel(channel);
         }

         channel.pipeline()
            .addLast("timeout", new ReadTimeoutHandler(30))
            .addLast("splitter", new MessageDeserializer2())
            .addLast("decoder", new MessageDeserializer(EnumPacketDirection.CLIENTBOUND))
            .addLast("prepender", new MessageSerializer2())
            .addLast("encoder", new MessageSerializer(EnumPacketDirection.SERVERBOUND))
            .addLast("packet_handler", this.networkManager);
         if (channel instanceof SocketChannel && ViaAugustus.targetProtocolVersion != ProtocolVersion.v1_8) {
            ViaAugustus.injectChannelHandlers(channel);
         }

         if (this.trackNetworkStats) {
            channel.pipeline().addFirst("network-stats-tracker", new NetworkStatsTrafficHandler());
         }
      }

      public PipelineInitializer(boolean flag, NetworkManager networkManager2, boolean flag2) {
         this.useProxy = flag;
         this.networkManager = networkManager2;
         this.trackNetworkStats = flag2;
      }

   }

   public static final class PipelineInitializer2 extends ChannelInitializer {
      public final NetworkManager networkManager;

      public void initChannel(Channel channel) {
         channel.pipeline().addLast("packet_handler", this.networkManager);
      }

      public PipelineInitializer2(NetworkManager networkManager2) {
         this.networkManager = networkManager2;
      }

   }

   public static class InboundHandlerTuplePacketListener {
      private final Packet packet;
      private final GenericFutureListener[] futureListeners;

      public Packet getPacket() {
         return this.packet;
      }

      public InboundHandlerTuplePacketListener(Packet packet2, GenericFutureListener[] listeners) {
         this.packet = packet2;
         this.futureListeners = listeners;
      }

      public static GenericFutureListener[] accessFutureListeners(InboundHandlerTuplePacketListener listener) {
         return listener.futureListeners;
      }

      public static Packet accessPacket(InboundHandlerTuplePacketListener listener) {
         return listener.packet;
      }
   }
}
