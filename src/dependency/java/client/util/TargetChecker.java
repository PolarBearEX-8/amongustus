package client.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

public class TargetChecker extends MessageToByteEncoder {
   public final CipherController cipherController;

   public void encode(ChannelHandlerContext channelHandlerContext, Object entry, ByteBuf byteBuf) throws Exception {
      this.applyChannelHandlerContext(channelHandlerContext, (ByteBuf)entry, byteBuf);
   }

   public void applyChannelHandlerContext(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws ShortBufferException, Exception {
      this.cipherController.applyByteBuf(byteBuf, byteBuf2);
   }

   public TargetChecker(Cipher cipher) {
      this.cipherController = new CipherController(cipher);
   }
}
