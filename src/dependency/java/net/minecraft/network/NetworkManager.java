package net.minecraft.network;

import augustus.modules.latency.PacketHandler;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.HeuristicProcessor;
import cc.baritone.utils.SecondaryOptionType;
import cc.baritone.utils.SettingsTxtManager;
import client.Client;
import client.ClientGlobals;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.PreUpdateConsumer;
import client.module.Module;
import client.network.AuxiliaryChannelInitializerAdapter;
import client.network.LatencyPacket;
import client.network.TimeoutChannelInitializer;
import client.network.TrafficShapingTracker;
import client.setting.CombatKeybindProperty;
import client.setting.EventPacketServerboundType;
import client.setting.SupplementalOptionType;
import client.util.AuxiliaryMessageToByteEncoderAdapter;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.BezierService;
import client.util.ExtendedRunnableAdapter;
import client.util.INetHandler;
import client.util.InternalConcurrentHashMapEntry;
import client.util.InterpolationEstimator;
import client.util.IsBelowServerThresholdOfByteToMessageDecoder;
import client.util.NettyClientIODManager;
import client.util.NettyEpollClientIODClientManager;
import client.util.NettyLocalClientIODManager;
import client.util.Packet;
import client.util.QueuedNetworkPacket;
import client.util.RayTraceUtil;
import client.util.RuntimeExceptionAdapter;
import client.util.SharedLoggerEntry;
import client.util.TargetChecker;
import client.via.ViaLoadingBase;
import com.google.common.collect.Queues;
import com.viaversion.vialoader.netty.CompressionReorderEvent;
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
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.raphimc.vialegacy.api.LegacyProtocolVersion;
import net.raphimc.viaproxy.ViaProxyCore;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class NetworkManager extends SimpleChannelInboundHandler {
   public static final Logger logger = LogManager.getLogger();
   public static final Marker marker2 = MarkerManager.getMarker("NETWORK");
   public static final Marker marker = MarkerManager.getMarker("NETWORK_PACKETS", marker2);
   public static final AttributeKey attributeKey = AttributeKey.valueOf("protocol");
   public static final InterpolationEstimator interpolationEstimator = new NettyClientIODManager();
   public static final InterpolationEstimator interpolationEstimator2 = new NettyEpollClientIODClientManager();
   public static final InterpolationEstimator interpolationEstimator3 = new NettyLocalClientIODManager();
   public final EventPacketServerboundType eventPacketServerboundType;
   public final Queue queue = Queues.newConcurrentLinkedQueue();
   public final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
   public Channel channel;
   public SocketAddress socketAddress;
   public INetHandler iNetHandler;
   public IChatComponent iChatComponent;
   public boolean enabled;
   public boolean enabled2;
   public Cipher cipher;

   public void applyPacket(Packet packet, boolean flag) {
      if (!flag) {
         this.applyPacketByPacket(packet);
      } else {
         CompletableFuture completableFuture = CompletableFuture.runAsync(() -> this.applyPacket(packet));

         while (!completableFuture.isDone()) {
         }
      }
   }

   public void updateState4() {
      this.channel.config().setAutoRead(false);
   }

   public boolean canExecute() {
      return this.enabled;
   }

   public void updateState2() {
      this.updateState();
      if (this.iNetHandler instanceof PreUpdateConsumer) {
         ((PreUpdateConsumer)this.iNetHandler).updateState();
      }

      if (this.channel != null) {
         this.channel.flush();
      }
   }

   private void applyPacketByPacket(Packet packet) {
      try {
         if (this.channel.isOpen()) {
            if (packet instanceof LatencyPacket) {
               return;
            }

            packet.processAction(this.iNetHandler);
         }
      } catch (RuntimeExceptionAdapter exception) {
      }
   }

   public synchronized void applyChannelHandlerContext(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
      if (this.channel.isOpen()) {
         ChannelHandler channelHandler = this.channel.pipeline().get("network-stats-tracker");
         if (channelHandler instanceof TrafficShapingTracker) {
            ((TrafficShapingTracker)channelHandler).updateState2();
         }

         if (((Module)ClientGlobals.moduleManager.openAuthMod).isToggled() && ViaProxyCore.viaProxyCore.matchesNetworkManager(this, packet)) {
            return;
         }

         try {
            EventPacketServerboundType eventPacketServerboundType2 = this.eventPacketServerboundType;
            InternalConcurrentHashMapEntry.onPacketServerboundType(packet, eventPacketServerboundType2);
         } catch (Exception exception) {
         }

         try {
            SupplementalOptionType supplementalOptionType = SupplementalOptionType.READ_NETTY;
            EventPacket packet2 = PacketListener.resolveEventPacket(packet, supplementalOptionType);
            if (packet2.isCancelled()) {
               return;
            }

            if (ClientGlobals.moduleManager.packetHandler.checkPacket(packet) && this.eventPacketServerboundType == EventPacketServerboundType.CLIENTBOUND) {
               EventPacketServerboundType eventPacketServerboundType3 = this.eventPacketServerboundType;
               INetHandler iNetHandler2 = this.iNetHandler;
               EventSendPacket packet3 = NetworkPacketListener.onPacketServerboundType(packet, iNetHandler2, eventPacketServerboundType3);
               Packet packet4 = packet3.getPacket();
               boolean flag = ClientGlobals.moduleManager.packetHandler.checkPacket4(packet4);
               if (packet3.isState() || packet3.isActive2() && ClientGlobals.moduleManager.packetHandler.canExecute6() || flag) {
                  if (!packet3.isState()
                     && (!packet3.isActive2() || !ClientGlobals.moduleManager.packetHandler.canExecute6())
                     && ClientGlobals.moduleManager.packetHandler.settingMacroDelayModes.getSelected().equals("ExperimentalMacroDelay")
                     && Minecraft.getMinecraft().thePlayer != null
                     && Minecraft.getMinecraft().thePlayer.ticksExisted > 5
                     && packet4 instanceof S00PacketKeepAlive) {
                     ClientGlobals.moduleManager.packetHandler.setExpanded2(true);
                  } else {
                     ClientGlobals.moduleManager.packetHandler.setExpanded2(false);
                  }

                  ClientGlobals.moduleManager.packetHandler.onSendPacket(packet3);
                  if (packet3.isActive2() && !packet3.isState()) {
                     PacketHandler packetHandler = ClientGlobals.moduleManager.packetHandler;
                     int indexOffset = packet3.getIndexOffset();
                     packetHandler.processAction3(indexOffset);
                     packetHandler = ClientGlobals.moduleManager.packetHandler;
                     EntityLivingBase targetEntity = packet3.getTargetEntity();
                     double coordOffset = packet3.getCoordOffset();
                     packetHandler.applyEntityLivingBase(coordOffset, targetEntity);
                  }

                  if (packet3.isState() && !packet3.isActive2()) {
                     PacketHandler packetHandler2 = ClientGlobals.moduleManager.packetHandler;
                     EntityLivingBase targetEntity2 = packet3.getTargetEntity();
                     double coordOffset2 = packet3.getCoordOffset();
                     packetHandler2.applyEntityLivingBase(coordOffset2, targetEntity2);
                  }

                  return;
               }

               ClientGlobals.moduleManager.packetHandler.updateState();
            }

            if (ClientGlobals.moduleManager.packetHandler.checkPacket2(packet)) {
               ClientGlobals.moduleManager.packetHandler.updateState();
            }

            if (packet instanceof LatencyPacket) {
               return;
            }

            ClientGlobals.moduleManager.packetHandler.handlePacket(packet);
            if (((Module)Client.getClient().getModuleManager().baritone).isToggled()
               && this.eventPacketServerboundType == EventPacketServerboundType.CLIENTBOUND) {
               for (ActiveListener activeListener : (Iterable<ActiveListener>)(Iterable<?>)SettingsTxtManager.getExtraListener().getList()) {
                  if (activeListener.getStaticListener().getEntityPlayerSP() != null
                     && activeListener.getStaticListener().getEntityPlayerSP().netHandlerPlayClient.getNetworkManager() == this) {
                     activeListener.getCompositeListener().forEachByHeuristicProcess(new HeuristicProcessor(this, SecondaryOptionType.PRE, packet));
                  }
               }
            }

            packet2.getPacket().processAction(this.iNetHandler);
         } catch (RuntimeExceptionAdapter exception2) {
         }

         if (((Module)Client.getClient().getModuleManager().baritone).isToggled() && this.eventPacketServerboundType == EventPacketServerboundType.CLIENTBOUND) {
            for (ActiveListener activeListener2 : (Iterable<ActiveListener>)(Iterable<?>)SettingsTxtManager.getExtraListener().getList()) {
               if (activeListener2.getStaticListener().getEntityPlayerSP() != null
                  && activeListener2.getStaticListener().getEntityPlayerSP().netHandlerPlayClient.getNetworkManager() == this) {
                  activeListener2.getCompositeListener().forEachByHeuristicProcess(new HeuristicProcessor(this, SecondaryOptionType.POST, packet));
               }
            }
         }
      }
   }

   private void applyPacket(Packet packet, GenericFutureListener[] genericFutureListenerArray) {
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled() && this.eventPacketServerboundType == EventPacketServerboundType.CLIENTBOUND) {
         for (ActiveListener activeListener : (Iterable<ActiveListener>)(Iterable<?>)SettingsTxtManager.getExtraListener().getList()) {
            if (activeListener.getStaticListener().getEntityPlayerSP() != null
               && activeListener.getStaticListener().getEntityPlayerSP().netHandlerPlayClient.getNetworkManager() == this) {
               activeListener.getCompositeListener().forEach(new HeuristicProcessor(this, SecondaryOptionType.PRE, packet));
            }
         }
      }

      if (packet instanceof LatencyPacket) {
         RayTraceUtil.applyString("That should not happen!");
      }

      ChannelHandler channelHandler = this.channel.pipeline().get("network-stats-tracker");
      if (channelHandler instanceof TrafficShapingTracker) {
         ((TrafficShapingTracker)channelHandler).updateState();
      }

      CombatKeybindProperty combatKeybindProperty = CombatKeybindProperty.resolveCombatKeybindProperty(packet);
      CombatKeybindProperty combatKeybindProperty2 = (CombatKeybindProperty)this.channel.attr(attributeKey).get();
      if (combatKeybindProperty2 != combatKeybindProperty) {
         logger.debug("Disabled auto read");
         this.channel.config().setAutoRead(false);
      }

      if (this.channel.eventLoop().inEventLoop()) {
         if (combatKeybindProperty != combatKeybindProperty2) {
            this.applyCombatKeybindProperty(combatKeybindProperty);
         }

         ChannelFuture channelFuture = this.channel.writeAndFlush(packet);
         if (genericFutureListenerArray != null) {
            channelFuture.addListeners(genericFutureListenerArray);
         }

         channelFuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
      } else {
         this.channel.eventLoop().execute(new ExtendedRunnableAdapter(this, combatKeybindProperty, combatKeybindProperty2, packet, genericFutureListenerArray));
      }

      if (((Module)Client.getClient().getModuleManager().baritone).isToggled() && this.eventPacketServerboundType != EventPacketServerboundType.CLIENTBOUND) {
         for (ActiveListener activeListener2 : (Iterable<ActiveListener>)(Iterable<?>)SettingsTxtManager.getExtraListener().getList()) {
            if (activeListener2.getStaticListener().getEntityPlayerSP() != null
               && activeListener2.getStaticListener().getEntityPlayerSP().netHandlerPlayClient.getNetworkManager() == this) {
               activeListener2.getCompositeListener().forEach(new HeuristicProcessor(this, SecondaryOptionType.POST, packet));
            }
         }
      }
   }

   public static NetworkManager resolveNetworkManager(InetAddress inetAddress, int count, boolean flag) {
      return resolveNetworkManager(inetAddress, count, flag, true);
   }

   public boolean isOpen() {
      return this.channel != null && this.channel.isOpen();
   }

   private void applyPacket(Packet packet) {
      this.applyPacketByPacket(packet);
   }

   public static NetworkManager resolveNetworkManager(SocketAddress socketAddress2) {
      NetworkManager networkManager = new NetworkManager(EventPacketServerboundType.CLIENTBOUND);
      ((Bootstrap)((Bootstrap)((Bootstrap)new Bootstrap().group((EventLoopGroup)interpolationEstimator3.getTargetAsTarget()))
               .handler(new AuxiliaryChannelInitializerAdapter(networkManager)))
            .channel(LocalChannel.class))
         .connect(socketAddress2)
         .syncUninterruptibly();
      return networkManager;
   }

   private void updateState() {
      if (this.channel != null && this.channel.isOpen()) {
         this.reentrantReadWriteLock.readLock().lock();

         try {
            while (!this.queue.isEmpty()) {
               QueuedNetworkPacket packet = (QueuedNetworkPacket)this.queue.poll();
               Packet packet2 = QueuedNetworkPacket.resolvePacket(packet);
               if ((packet2 instanceof C04PacketPlayerPosition || packet2 instanceof C06PacketPlayerPosLook)
                  && Minecraft.getMinecraft().theWorld != null
                  && Minecraft.getMinecraft().thePlayer != null) {
                  C03PacketPlayer packet3 = (C03PacketPlayer)packet2;
                  Minecraft.getMinecraft().thePlayer.setPositionVec12(Minecraft.getMinecraft().thePlayer.getPositionVec8());
                  Minecraft.getMinecraft().thePlayer.setPositionVec8(new Vec3(packet3.getCoordinate1(), packet3.getCoordinate(), packet3.getCoordinate3()));
               }

               if (!(packet2 instanceof LatencyPacket)) {
                  SupplementalOptionType supplementalOptionType = SupplementalOptionType.SEND_NETTY_DELAY;
                  PacketListener.resolveEventPacket(packet2, supplementalOptionType);
                  this.applyPacket(packet2, QueuedNetworkPacket.resolveGenericFutureListenerArray(packet));
               }
            }
         } finally {
            this.reentrantReadWriteLock.readLock().unlock();
         }
      }
   }

   public void applyINetHandler(INetHandler iNetHandler2) {
      Validate.notNull(iNetHandler2, "packetListener", new Object[0]);
      logger.debug("Set listener of {} to {}", new Object[]{this, iNetHandler2});
      this.iNetHandler = iNetHandler2;
   }

   public void applyPacket(Packet packet, GenericFutureListener genericFutureListener, GenericFutureListener[] genericFutureListenerArray) {
      if (this.isOpen()) {
         this.updateState();
         this.applyPacket(packet, (GenericFutureListener[])ArrayUtils.add(genericFutureListenerArray, 0, genericFutureListener));
      } else {
         this.reentrantReadWriteLock.writeLock().lock();

         try {
            this.queue.add(new QueuedNetworkPacket(packet, (GenericFutureListener[])ArrayUtils.add(genericFutureListenerArray, 0, genericFutureListener)));
         } finally {
            this.reentrantReadWriteLock.writeLock().unlock();
         }
      }
   }

   public void updateState5() {
      this.channel.pipeline().addBefore("vialegacy-pre-netty-length-prepender", "decrypt", new TargetChecker(this.cipher));
   }

   public boolean isActiveAsFlag() {
      return this.channel == null;
   }

   public static NetworkManager resolveNetworkManager(InetAddress inetAddress, int count, boolean flag, boolean flag2) {
      NetworkManager networkManager = new NetworkManager(EventPacketServerboundType.CLIENTBOUND);
      Class<? extends Channel> clazz;
      InterpolationEstimator interpolationEstimator4;
      if (Epoll.isAvailable() && flag) {
         clazz = EpollSocketChannel.class;
         interpolationEstimator4 = interpolationEstimator2;
      } else {
         clazz = NioSocketChannel.class;
         interpolationEstimator4 = interpolationEstimator;
      }

      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group((EventLoopGroup)interpolationEstimator4.getTargetAsTarget());
      bootstrap.handler(new TimeoutChannelInitializer(flag2, networkManager, flag));
      ((Bootstrap)bootstrap.channel(clazz)).connect(inetAddress, count).syncUninterruptibly();
      return networkManager;
   }

   public void applySecretKey(SecretKey secretKey) {
      this.cipher = SharedLoggerEntry.resolveCipher(2, secretKey);
      Cipher cipher2 = SharedLoggerEntry.resolveCipher(1, secretKey);
      if (ViaLoadingBase.protocolVersion.olderThanOrEqualTo(LegacyProtocolVersion.r1_6_4)) {
         this.channel.pipeline().addBefore("vialegacy-pre-netty-length-remover", "encrypt", new TargetChecker(cipher2));
      } else {
         this.channel.pipeline().addBefore("splitter", "decrypt", new BezierService(this.cipher));
         this.channel.pipeline().addBefore("prepender", "encrypt", new TargetChecker(cipher2));
      }

      this.enabled = true;
   }

   public INetHandler getINetHandler() {
      return this.iNetHandler;
   }

   public void updateState3() {
      if (this.channel != null && !this.channel.isOpen()) {
         if (!this.enabled2) {
            this.enabled2 = true;
            if (this.getIChatComponent() != null) {
               this.getINetHandler().applyIChatComponent(this.getIChatComponent());
            } else if (this.getINetHandler() != null) {
               this.getINetHandler().applyIChatComponent(new ChatComponentText("Disconnected"));
            }
         } else {
            logger.warn("handleDisconnection() called twice");
         }
      }
   }

   public void handlePacket6(Packet packet) {
      SupplementalOptionType supplementalOptionType = SupplementalOptionType.SEND_NETTY;
      if (!PacketListener.resolveEventPacket(packet, supplementalOptionType).isCancelled()) {
         if (ClientGlobals.moduleManager.packetHandler.checkPacket3(packet)) {
            ReentrantReadWriteLock reentrantReadWriteLock2 = this.reentrantReadWriteLock;
            Queue queue2 = this.queue;
            EventSendPacket packet2 = NetworkPacketListener.resolveEventSendPacket(packet, queue2, reentrantReadWriteLock2);
            if (packet2.isState() || packet2.isActive() && ClientGlobals.moduleManager.packetHandler.canExecute6()) {
               ClientGlobals.moduleManager.packetHandler.onSendPacket3(packet2);
               if (packet2.isActive() && !packet2.isState()) {
                  PacketHandler packetHandler = ClientGlobals.moduleManager.packetHandler;
                  int indexOffset = packet2.getIndexOffset2();
                  packetHandler.onSendPacket2(packet2, indexOffset);
                  this.updateState();
               }

               return;
            }

            ReentrantReadWriteLock reentrantReadWriteLock3 = this.reentrantReadWriteLock;
            Queue queue3 = this.queue;
            ClientGlobals.moduleManager.packetHandler.applyQueue(queue3, reentrantReadWriteLock3);
         }

         if (ClientGlobals.moduleManager.packetHandler.checkPacket5(packet)) {
            ReentrantReadWriteLock reentrantReadWriteLock4 = this.reentrantReadWriteLock;
            Queue queue4 = this.queue;
            ClientGlobals.moduleManager.packetHandler.applyQueue(queue4, reentrantReadWriteLock4);
         }

         if (this.isOpen()) {
            this.updateState();
            if ((packet instanceof C04PacketPlayerPosition || packet instanceof C06PacketPlayerPosLook)
               && Minecraft.getMinecraft().theWorld != null
               && Minecraft.getMinecraft().thePlayer != null) {
               C03PacketPlayer packet3 = (C03PacketPlayer)packet;
               Minecraft.getMinecraft().thePlayer.setPositionVec12(Minecraft.getMinecraft().thePlayer.getPositionVec8());
               Minecraft.getMinecraft().thePlayer.setPositionVec8(new Vec3(packet3.getCoordinate1(), packet3.getCoordinate(), packet3.getCoordinate3()));
            }

            if (!(packet instanceof LatencyPacket)) {
               SupplementalOptionType supplementalOptionType2 = SupplementalOptionType.SEND_NETTY_DELAY;
               PacketListener.resolveEventPacket(packet, supplementalOptionType2);
               this.applyPacket(packet, (GenericFutureListener[])null);
            }
         } else {
            this.reentrantReadWriteLock.writeLock().lock();

            try {
               this.queue.add(new QueuedNetworkPacket(packet, (GenericFutureListener[])null));
            } finally {
               this.reentrantReadWriteLock.writeLock().unlock();
            }
         }
      }
   }

   public SocketAddress getSocketAddress() {
      return this.socketAddress;
   }

   private static void applyPacket2(Packet packet) {
      try {
         Minecraft.getMinecraft().getNetHandlerPlayClient().getNetworkManager().applyChannelHandlerContext(null, packet);
      } catch (Exception exception) {
      }
   }

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   public void applyCombatKeybindProperty(CombatKeybindProperty combatKeybindProperty) {
      this.channel.attr(attributeKey).set(combatKeybindProperty);
      this.channel.config().setAutoRead(true);
      logger.debug("Enabled auto read");
   }

   public boolean isActive() {
      return this.channel instanceof LocalChannel || this.channel instanceof LocalServerChannel;
   }

   public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
      throwable.printStackTrace();
      BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter;
      if (throwable instanceof TimeoutException) {
         baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("disconnect.timeout", new Object[0]);
      } else {
         baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("disconnect.genericReason", new Object[]{"Internal Exception: " + throwable});
      }

      this.applyIChatComponent(baseManagerStyleIChatComponentAdapter);
   }

   public void applyIndex(int count) {
      if (count >= 0) {
         if (this.channel.pipeline().get("decompress") instanceof IsBelowServerThresholdOfByteToMessageDecoder) {
            ((IsBelowServerThresholdOfByteToMessageDecoder)this.channel.pipeline().get("decompress")).setCount(count);
         } else {
            this.channel.pipeline().addBefore("decoder", "decompress", new IsBelowServerThresholdOfByteToMessageDecoder(count));
         }

         if (this.channel.pipeline().get("compress") instanceof AuxiliaryMessageToByteEncoderAdapter) {
            ((AuxiliaryMessageToByteEncoderAdapter)this.channel.pipeline().get("decompress")).setCount(count);
         } else {
            this.channel.pipeline().addBefore("encoder", "compress", new AuxiliaryMessageToByteEncoderAdapter(count));
         }
      } else {
         if (this.channel.pipeline().get("decompress") instanceof IsBelowServerThresholdOfByteToMessageDecoder) {
            this.channel.pipeline().remove("decompress");
         }

         if (this.channel.pipeline().get("compress") instanceof AuxiliaryMessageToByteEncoderAdapter) {
            this.channel.pipeline().remove("compress");
         }
      }

      this.channel.pipeline().fireUserEventTriggered(CompressionReorderEvent.INSTANCE);
   }

   public void applyIChatComponent(IChatComponent iChatComponent2) {
      if (this.channel.isOpen()) {
         this.channel.close().awaitUninterruptibly();
         this.iChatComponent = iChatComponent2;
      }
   }

   public void handlePacket8(Packet packet) {
      CompletableFuture completableFuture = CompletableFuture.runAsync(() -> applyPacket2(packet));

      while (!completableFuture.isDone()) {
      }
   }

   public void channelRead0(ChannelHandlerContext channelHandlerContext, Object entry) throws Exception {
      this.applyChannelHandlerContext(channelHandlerContext, (Packet)entry);
   }

   public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
      super.channelActive(channelHandlerContext);
      this.channel = channelHandlerContext.channel();
      this.socketAddress = this.channel.remoteAddress();

      try {
         this.applyCombatKeybindProperty(CombatKeybindProperty.HANDSHAKING);
      } catch (Throwable throwable) {
         logger.fatal(throwable);
      }
   }

   public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
      this.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("disconnect.endOfStream", new Object[0]));
   }

   public NetworkManager(EventPacketServerboundType packet) {
      this.eventPacketServerboundType = packet;
   }
}
