package client.util;

import client.setting.CombatKeybindProperty;
import client.setting.EventPacketServerboundType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.IOException;
import net.minecraft.network.NetworkManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class OUTMessageToByteEncoder extends MessageToByteEncoder {
   public static final Logger logger = LogManager.getLogger();
   public static final Marker marker = MarkerManager.getMarker("PACKET_SENT", NetworkManager.marker);
   public final EventPacketServerboundType eventPacketServerboundType;

   public void applyChannelHandlerContext(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws IOException, Exception {
      Integer count = ((CombatKeybindProperty)channelHandlerContext.channel().attr(NetworkManager.attributeKey).get())
         .onPacketServerboundType(this.eventPacketServerboundType, packet);
      if (logger.isDebugEnabled()) {
         logger.debug(marker, "OUT: [{}:{}] {}", new Object[]{channelHandlerContext.channel().attr(NetworkManager.attributeKey).get(), count, packet.getClass().getName()});
      }

      if (count == null) {
         throw new IOException("Can't serialize unregistered packet");
      } else {
         PacketBuffer packet2 = new PacketBuffer(byteBuf);
         packet2.updateInt(count);

         try {
            packet.processAction2(packet2);
         } catch (Throwable throwable) {
            logger.error(throwable);
         }
      }
   }

   public OUTMessageToByteEncoder(EventPacketServerboundType packet) {
      this.eventPacketServerboundType = packet;
   }

   public void encode(ChannelHandlerContext channelHandlerContext, Object entry, ByteBuf byteBuf) throws Exception {
      this.applyChannelHandlerContext(channelHandlerContext, (Packet)entry, byteBuf);
   }
}
