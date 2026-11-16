package client.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.util.zip.Deflater;

public class AuxiliaryMessageToByteEncoderAdapter extends MessageToByteEncoder {
   public final byte[] byteFlagArray = new byte[8192];
   public final Deflater deflater;
   public int count;

   public void applyChannelHandlerContext(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
      int count2 = byteBuf.readableBytes();
      PacketBuffer packet = new PacketBuffer(byteBuf2);
      if (count2 < this.count) {
         packet.updateInt(0);
         packet.writeBytes(byteBuf);
      } else {
         byte[] byteValueArray = new byte[count2];
         byteBuf.readBytes(byteValueArray);
         packet.updateInt(byteValueArray.length);
         this.deflater.setInput(byteValueArray, 0, count2);
         this.deflater.finish();

         while (!this.deflater.finished()) {
            int count3 = this.deflater.deflate(this.byteFlagArray);
            packet.writeBytes(this.byteFlagArray, 0, count3);
         }

         this.deflater.reset();
      }
   }

   public void setCount(int count2) {
      this.count = count2;
   }

   public AuxiliaryMessageToByteEncoderAdapter(int count2) {
      this.count = count2;
      this.deflater = new Deflater();
   }

   public void encode(ChannelHandlerContext channelHandlerContext, Object entry, ByteBuf byteBuf) throws Exception {
      this.applyChannelHandlerContext(channelHandlerContext, (ByteBuf)entry, byteBuf);
   }
}
