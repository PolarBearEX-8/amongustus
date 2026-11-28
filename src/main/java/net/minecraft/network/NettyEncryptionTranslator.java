package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

public class NettyEncryptionTranslator {
   private final Cipher cipher;
   private byte[] inputBuffer = new byte[0];
   private byte[] outputBuffer = new byte[0];

   public NettyEncryptionTranslator(Cipher cipher2) {
      this.cipher = cipher2;
   }

   public ByteBuf decipher(ChannelHandlerContext ctx, ByteBuf buf) throws ShortBufferException {
      int n = buf.readableBytes();
      byte[] bytes = this.bufToBytes(buf);
      ByteBuf buf2 = ctx.alloc().heapBuffer(this.cipher.getOutputSize(n));
      buf2.writerIndex(this.cipher.update(bytes, 0, n, buf2.array(), buf2.arrayOffset()));
      return buf2;
   }

   public void cipher(ByteBuf buf, ByteBuf buf2) throws ShortBufferException {
      int n = buf.readableBytes();
      byte[] bytes = this.bufToBytes(buf);
      int outputSize = this.cipher.getOutputSize(n);
      if (this.outputBuffer.length < outputSize) {
         this.outputBuffer = new byte[outputSize];
      }

      buf2.writeBytes(this.outputBuffer, 0, this.cipher.update(bytes, 0, n, this.outputBuffer));
   }

   private byte[] bufToBytes(ByteBuf buf) {
      int n = buf.readableBytes();
      if (this.inputBuffer.length < n) {
         this.inputBuffer = new byte[n];
      }

      buf.readBytes(this.inputBuffer, 0, n);
      return this.inputBuffer;
   }
}
