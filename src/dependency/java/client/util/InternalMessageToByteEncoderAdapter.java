package client.util;

import client.event.PostMotionProcessor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.IOException;

public class InternalMessageToByteEncoderAdapter extends MessageToByteEncoder {
   public final SpecialLongEntry specialLongEntry;

   public void applyChannelHandlerContext(ChannelHandlerContext channelHandlerContext, PostMotionProcessor postMotionProcessor, ByteBuf byteBuf) throws IOException, Exception {
      int count = 0;
      Object entry = null;
      this.specialLongEntry.updateState2();
      count = (Integer)AccountSessionManager.getKGBfUoUg0L8Manager().hashMap.get(postMotionProcessor.getClass()) + 7;
      if (postMotionProcessor instanceof EnhancedByteArrayEntry) {
         count = 5;
      }

      if (count >= 0) {
         entry = new ByteBufHandler(byteBuf);
         ((ByteBufHandler)entry).writeInt(count);
         postMotionProcessor.writeInt((ByteBufHandler)entry);
      } else {
         throw new NullPointerException("Couldn't find id of packet " + postMotionProcessor.getClass().getSimpleName());
      }
   }

   public InternalMessageToByteEncoderAdapter(SpecialLongEntry specialLongEntry2) {
      this.specialLongEntry = specialLongEntry2;
   }

   public void encode(ChannelHandlerContext channelHandlerContext, Object entry, ByteBuf byteBuf) throws Exception {
      PostMotionProcessor postMotionProcessor = (PostMotionProcessor)entry;
      this.applyChannelHandlerContext(channelHandlerContext, postMotionProcessor, byteBuf);
   }
}
