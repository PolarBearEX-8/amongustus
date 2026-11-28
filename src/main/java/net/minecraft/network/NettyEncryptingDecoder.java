package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

public class NettyEncryptingDecoder extends MessageToMessageDecoder {
   private final NettyEncryptionTranslator decryptionCodec;

   public void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws ShortBufferException, Exception {
      out.add(this.decryptionCodec.decipher(ctx, in));
   }

   public NettyEncryptingDecoder(Cipher cipher) {
      this.decryptionCodec = new NettyEncryptionTranslator(cipher);
   }

   public void decode(ChannelHandlerContext ctx, Object in, List out) throws Exception {
      this.decode(ctx, (ByteBuf)in, out);
   }
}
