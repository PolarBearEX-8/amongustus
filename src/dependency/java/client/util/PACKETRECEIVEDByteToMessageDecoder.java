package client.util;

import client.setting.CombatKeybindProperty;
import client.setting.EventPacketServerboundType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.IOException;
import java.util.List;
import net.minecraft.network.NetworkManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class PACKETRECEIVEDByteToMessageDecoder extends ByteToMessageDecoder {
   public static final Logger logger = LogManager.getLogger();
   public static final Marker marker = MarkerManager.getMarker("PACKET_RECEIVED", NetworkManager.marker);
   public final EventPacketServerboundType eventPacketServerboundType;

   public PACKETRECEIVEDByteToMessageDecoder(EventPacketServerboundType packet) {
      this.eventPacketServerboundType = packet;
   }

   public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws IOException, InstantiationException, IllegalAccessException, Exception {
      if (byteBuf.readableBytes() != 0) {
         PacketBuffer packet = new PacketBuffer(byteBuf);
         int intItem = packet.getInt3();
         Packet packet2 = ((CombatKeybindProperty)channelHandlerContext.channel().attr(NetworkManager.attributeKey).get())
            .onPacketServerboundType(this.eventPacketServerboundType, intItem);
         if (packet2 == null) {
            throw new IOException("Bad packet id " + intItem);
         }

         packet2.processAction(packet);
         if (packet.readableBytes() > 0) {
            throw new IOException(
               "Packet "
                  + ((CombatKeybindProperty)channelHandlerContext.channel().attr(NetworkManager.attributeKey).get()).getCount3()
                  + "/"
                  + intItem
                  + " ("
                  + packet2.getClass().getSimpleName()
                  + ") was larger than I expected, found "
                  + packet.readableBytes()
                  + " bytes extra whilst reading packet "
                  + intItem
            );
         }

         list.add(packet2);
         if (logger.isDebugEnabled()) {
            logger.debug(marker, " IN: [{}:{}] {}", new Object[]{channelHandlerContext.channel().attr(NetworkManager.attributeKey).get(), intItem, packet2.getClass().getName()});
         }
      }
   }
}
