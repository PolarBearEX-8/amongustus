package net.minecraft.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.IOException;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class MessageSerializer extends MessageToByteEncoder {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Marker RECEIVED_PACKET_MARKER = MarkerManager.getMarker("PACKET_SENT", NetworkManager.logMarkerPackets);
   private final EnumPacketDirection direction;

   public void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
      this.encode(ctx, (Packet)msg, out);
   }

   public MessageSerializer(EnumPacketDirection enumPacketDirection) {
      this.direction = enumPacketDirection;
   }

   public void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws IOException, Exception {
      Integer packetId = ((EnumConnectionState)ctx.channel().attr(NetworkManager.attrKeyConnectionState).get()).getPacketId(this.direction, msg);
      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug(
            RECEIVED_PACKET_MARKER,
            "OUT: [{}:{}] {}",
            new Object[]{ctx.channel().attr(NetworkManager.attrKeyConnectionState).get(), packetId, msg.getClass().getName()}
         );
      }

      if (packetId == null) {
         throw new IOException("Can't serialize unregistered packet");
      } else {
         PacketBuffer buf = new PacketBuffer(out);
         buf.writeVarIntToBuffer(packetId);

         try {
            msg.writePacketData(buf);
         } catch (Throwable e) {
            LOGGER.error(e);
         }
      }
   }
}
