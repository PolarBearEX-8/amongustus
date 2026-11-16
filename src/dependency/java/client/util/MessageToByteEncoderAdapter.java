package client.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageToByteEncoderAdapter extends MessageToByteEncoder {
   public final SpecialLongEntry specialLongEntry;

   public void applyChannelHandlerContext(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
      int count = 0;
      int count2 = 0;
      Object entry = null;
      this.specialLongEntry.updateState3();
      count = byteBuf.readableBytes();
      count2 = ByteBufHandler.computeCount(count);
      if (count2 <= 3) {
         entry = new ByteBufHandler(byteBuf2);
         int count3 = count2 + count;
         ((ByteBufHandler)entry).ensureWritable(count3);
         ((ByteBufHandler)entry).applyIndexByIndex(count);
         int count4 = byteBuf.readerIndex();
         ((ByteBufHandler)entry).writeBytes(byteBuf, count4, count);
      } else {
         throw new IllegalArgumentException("unable to fit " + count + " into " + 3);
      }
   }

   public MessageToByteEncoderAdapter(SpecialLongEntry specialLongEntry2) {
      this.specialLongEntry = specialLongEntry2;
   }

   public void encode(ChannelHandlerContext channelHandlerContext, Object entry, ByteBuf byteBuf) throws Exception {
      ByteBuf byteBuf2 = (ByteBuf)entry;
      this.applyChannelHandlerContext(channelHandlerContext, byteBuf2, byteBuf);
   }
}
