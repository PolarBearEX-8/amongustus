package net.augustus.viaversion;

import net.augustus.viaversion.platform.ViaAugustusPlatform;
import net.augustus.viaversion.platform.ViaAugustusPlatformLoader;
import com.viaversion.viaaprilfools.ViaAprilFoolsPlatformImpl;
import com.viaversion.viabackwards.ViaBackwardsPlatformImpl;
import com.viaversion.viabackwards.protocol.v1_17to1_16_4.Protocol1_17To1_16_4;
import com.viaversion.viarewind.ViaRewindPlatformImpl;
import com.viaversion.viaversion.ViaManagerImpl;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.commands.ViaCommandHandler;
import com.viaversion.viaversion.platform.NoopInjector;
import com.viaversion.viaversion.platform.ViaChannelInitializer;
import com.viaversion.viaversion.platform.ViaDecodeHandler;
import com.viaversion.viaversion.platform.ViaEncodeHandler;
import com.viaversion.viaversion.protocols.v1_16_1to1_16_2.packet.ClientboundPackets1_16_2;
import com.viaversion.viaversion.protocols.v1_16_1to1_16_2.packet.ServerboundPackets1_16_2;
import com.viaversion.viaversion.protocols.v1_16_4to1_17.packet.ClientboundPackets1_17;
import com.viaversion.viaversion.protocols.v1_16_4to1_17.packet.ServerboundPackets1_17;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.util.AttributeKey;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.raphimc.vialegacy.ViaLegacyPlatformImpl;
import net.raphimc.vialegacy.api.LegacyProtocolVersion;
import net.raphimc.vialegacy.netty.PreNettyLengthPrepender;
import net.raphimc.vialegacy.netty.PreNettyLengthRemover;

public final class ViaAugustus {
   public static final AttributeKey CONNECTION_ATTRIBUTE_KEY = AttributeKey.newInstance("viaprotocolhack-minecraft-connection");
   public static ProtocolVersion targetProtocolVersion = ProtocolVersion.v1_8;

   public static void injectChannelHandlers(Channel channel) {
      UserConnection userConnection = ViaChannelInitializer.createUserConnection(channel, true);
      channel.pipeline().addBefore("decoder", "via-decoder", new ViaDecodeHandler(userConnection));
      channel.pipeline().addBefore("encoder", "via-encoder", new ViaEncodeHandler(userConnection));
      if (targetProtocolVersion.olderThanOrEqualTo(LegacyProtocolVersion.r1_6_4)) {
         channel.pipeline().addBefore("splitter", "vialegacy-pre-netty-length-prepender", new PreNettyLengthPrepender(userConnection));
         channel.pipeline().addBefore("prepender", "vialegacy-pre-netty-length-remover", new PreNettyLengthRemover(userConnection));
      }
   }

   public static void sendOpenInventoryPacketIfLegacy() {
      if (targetProtocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_11_1)) {
         Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
      }
   }

   public static void reorderCompressionHandlers(ChannelPipeline channelPipeline) {
      int n = channelPipeline.names().indexOf("decompress");
      if (n != -1) {
         if (n > channelPipeline.names().indexOf("via-decoder")) {
            ChannelHandler channelHandler = channelPipeline.get("via-decoder");
            ChannelHandler channelHandler2 = channelPipeline.get("via-encoder");
            channelPipeline.remove(channelHandler);
            channelPipeline.remove(channelHandler2);
            channelPipeline.addAfter("decompress", "via-decoder", channelHandler);
            channelPipeline.addAfter("compress", "via-encoder", channelHandler2);
         }
      }
   }


   public static double getMotionZeroThreshold() {
      return targetProtocolVersion.newerThan(ProtocolVersion.v1_9) ? 0.003 : 0.005;
   }

   public static void initialize() {
      ViaManagerImpl.initAndLoad(new ViaAugustusPlatform(), new NoopInjector(), new ViaCommandHandler(false), new ViaAugustusPlatformLoader(), new Runnable[]{() -> {
         new ViaBackwardsPlatformImpl();
         new ViaRewindPlatformImpl();
         new ViaAprilFoolsPlatformImpl();
         new ViaLegacyPlatformImpl();
      }});
      Protocol1_17To1_16_4 protocol = (Protocol1_17To1_16_4)Via.getManager().getProtocolManager().getProtocol(Protocol1_17To1_16_4.class);
      protocol.registerClientbound(ClientboundPackets1_17.PING, ClientboundPackets1_16_2.CONTAINER_ACK, packetWrapper -> {
      }, true);
      protocol.registerServerbound(ServerboundPackets1_16_2.CONTAINER_ACK, ServerboundPackets1_17.PONG, packetWrapper2 -> {
      }, true);
   }




}
