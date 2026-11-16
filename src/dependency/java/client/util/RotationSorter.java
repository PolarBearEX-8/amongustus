package client.util;

import client.event.PostMotionProcessor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import java.util.function.Supplier;

public class RotationSorter extends ByteToMessageDecoder {
   public final SpecialLongEntry specialLongEntry;

   public RotationSorter(SpecialLongEntry specialLongEntry2) {
      this.specialLongEntry = specialLongEntry2;
   }

   public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
      Object entry = null;
      int count = 0;
      Supplier supplier = null;
      Object entry2 = null;
      this.specialLongEntry.updateState3();
      if (byteBuf.readableBytes() != 0) {
         entry = new ByteBufHandler(byteBuf);
         count = ((ByteBufHandler)entry).readInt();
         supplier = (Supplier)AccountSessionManager.getKGBfUoUg0L8Manager().list.get(count - 7);
         if (supplier == null) {
            throw new NullPointerException("Couldn't find packet by id " + count);
         }

         entry2 = (PostMotionProcessor)supplier.get();
         ((PostMotionProcessor)entry2).readInt((ByteBufHandler)entry);
         list.add(entry2);
      }
   }
}
