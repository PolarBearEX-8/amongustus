package client.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

public class BezierService extends MessageToMessageDecoder {
   public final CipherController cipherController;

   public void applyChannelHandlerContext(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws ShortBufferException, Exception {
      list.add(this.cipherController.resolveByteBuf(channelHandlerContext, byteBuf));
   }

   public void decode(ChannelHandlerContext channelHandlerContext, Object entry, List list) throws Exception {
      this.applyChannelHandlerContext(channelHandlerContext, (ByteBuf)entry, list);
   }

   public BezierService(Cipher cipher) {
      this.cipherController = new CipherController(cipher);
   }
}
