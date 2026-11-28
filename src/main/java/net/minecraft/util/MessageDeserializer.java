package net.minecraft.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.IOException;
import java.util.List;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class MessageDeserializer extends ByteToMessageDecoder {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Marker RECEIVED_PACKET_MARKER = MarkerManager.getMarker("PACKET_RECEIVED", NetworkManager.logMarkerPackets);
   private final EnumPacketDirection direction;

   public MessageDeserializer(EnumPacketDirection enumPacketDirection) {
      this.direction = enumPacketDirection;
   }

   public void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws IOException, InstantiationException, IllegalAccessException, Exception {
      if (in.readableBytes() != 0) {
         PacketBuffer buf = new PacketBuffer(in);
         int n = buf.readVarIntFromBuffer();
         Packet packet = ((EnumConnectionState)ctx.channel().attr(NetworkManager.attrKeyConnectionState).get()).getPacket(this.direction, n);
         if (packet == null) {
            throw new IOException("Bad packet id " + n);
         }

         packet.readPacketData(buf);
         if (buf.readableBytes() > 0) {
            throw new IOException(
               "Packet "
                  + ((EnumConnectionState)ctx.channel().attr(NetworkManager.attrKeyConnectionState).get()).getId()
                  + "/"
                  + n
                  + " ("
                  + packet.getClass().getSimpleName()
                  + ") was larger than I expected, found "
                  + buf.readableBytes()
                  + " bytes extra whilst reading packet "
                  + n
            );
         }

         out.add(packet);
         if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(
               RECEIVED_PACKET_MARKER,
               " IN: [{}:{}] {}",
               new Object[]{ctx.channel().attr(NetworkManager.attrKeyConnectionState).get(), n, packet.getClass().getName()}
            );
         }
      }
   }
}
