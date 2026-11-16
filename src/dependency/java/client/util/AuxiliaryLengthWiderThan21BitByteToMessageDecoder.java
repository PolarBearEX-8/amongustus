package client.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import java.util.List;

public class AuxiliaryLengthWiderThan21BitByteToMessageDecoder extends ByteToMessageDecoder {
   public final SpecialLongEntry specialLongEntry;

   public AuxiliaryLengthWiderThan21BitByteToMessageDecoder(SpecialLongEntry specialLongEntry2) {
      this.specialLongEntry = specialLongEntry2;
   }

   public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
      this.specialLongEntry.updateState2();
      byteBuf.markReaderIndex();
      byte[] byteValueArray = new byte[5];

      for (int index = 0; index < byteValueArray.length; index++) {
         if (!byteBuf.isReadable()) {
            byteBuf.resetReaderIndex();
            return;
         }

         byteValueArray[index] = byteBuf.readByte();
         if (byteValueArray[index] >= 0) {
            ByteBufHandler byteBufHandler = new ByteBufHandler(Unpooled.wrappedBuffer(byteValueArray));

            try {
               int count = byteBufHandler.getCount();
               if (byteBuf.readableBytes() < count) {
                  byteBuf.resetReaderIndex();
                  return;
               }

               list.add(byteBuf.readBytes(count));
            } finally {
               byteBufHandler.release();
            }

            return;
         }
      }

      throw new CorruptedFrameException("length wider than 21-bit");
   }
}
