package client.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

public class CipherController {
   public final Cipher cipher;
   public byte[] byteFlagArray = new byte[0];
   public byte[] byteFlagArray2 = new byte[0];

   public ByteBuf resolveByteBuf(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws ShortBufferException {
      int count = byteBuf.readableBytes();
      byte[] byteValueArray = this.resolveByteArray(byteBuf);
      ByteBuf byteBuf2 = channelHandlerContext.alloc().heapBuffer(this.cipher.getOutputSize(count));
      byteBuf2.writerIndex(this.cipher.update(byteValueArray, 0, count, byteBuf2.array(), byteBuf2.arrayOffset()));
      return byteBuf2;
   }

   public void applyByteBuf(ByteBuf byteBuf, ByteBuf byteBuf2) throws ShortBufferException {
      int count = byteBuf.readableBytes();
      byte[] byteValueArray = this.resolveByteArray(byteBuf);
      int outputSize = this.cipher.getOutputSize(count);
      if (this.byteFlagArray2.length < outputSize) {
         this.byteFlagArray2 = new byte[outputSize];
      }

      byteBuf2.writeBytes(this.byteFlagArray2, 0, this.cipher.update(byteValueArray, 0, count, this.byteFlagArray2));
   }

   private byte[] resolveByteArray(ByteBuf byteBuf) {
      int count = byteBuf.readableBytes();
      if (this.byteFlagArray.length < count) {
         this.byteFlagArray = new byte[count];
      }

      byteBuf.readBytes(this.byteFlagArray, 0, count);
      return this.byteFlagArray;
   }

   public CipherController(Cipher cipher2) {
      this.cipher = cipher2;
   }
}
