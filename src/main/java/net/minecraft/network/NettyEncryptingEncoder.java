package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

public class NettyEncryptingEncoder extends MessageToByteEncoder {
   private final NettyEncryptionTranslator encryptionCodec;

   public void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
      this.encode(ctx, (ByteBuf)msg, out);
   }

   public NettyEncryptingEncoder(Cipher cipher) {
      this.encryptionCodec = new NettyEncryptionTranslator(cipher);
   }

   public void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws ShortBufferException, Exception {
      this.encryptionCodec.cipher(msg, out);
   }
}
