package client.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import java.util.List;

public class LengthWiderThan21BitByteToMessageDecoder extends ByteToMessageDecoder {
   public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
      byteBuf.markReaderIndex();
      byte[] byteValueArray = new byte[3];

      for (int index = 0; index < byteValueArray.length; index++) {
         if (!byteBuf.isReadable()) {
            byteBuf.resetReaderIndex();
            return;
         }

         byteValueArray[index] = byteBuf.readByte();
         if (byteValueArray[index] >= 0) {
            PacketBuffer packet = new PacketBuffer(Unpooled.wrappedBuffer(byteValueArray));

            try {
               int intItem = packet.getInt3();
               if (byteBuf.readableBytes() < intItem) {
                  byteBuf.resetReaderIndex();
                  return;
               }

               list.add(byteBuf.readBytes(intItem));
            } finally {
               packet.release();
            }

            return;
         }
      }

      throw new CorruptedFrameException("length wider than 21-bit");
   }
}
