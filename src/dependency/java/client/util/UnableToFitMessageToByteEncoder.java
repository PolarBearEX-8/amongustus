package client.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class UnableToFitMessageToByteEncoder extends MessageToByteEncoder {
   public void applyChannelHandlerContext(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
      int count = byteBuf.readableBytes();
      int count2 = PacketBuffer.computeCount(count);
      if (count2 > 3) {
         throw new IllegalArgumentException("unable to fit " + count + " into " + 3);
      } else {
         PacketBuffer packet = new PacketBuffer(byteBuf2);
         packet.ensureWritable(count2 + count);
         packet.updateInt(count);
         packet.writeBytes(byteBuf, byteBuf.readerIndex(), count);
      }
   }

   public void encode(ChannelHandlerContext channelHandlerContext, Object entry, ByteBuf byteBuf) throws Exception {
      this.applyChannelHandlerContext(channelHandlerContext, (ByteBuf)entry, byteBuf);
   }
}
