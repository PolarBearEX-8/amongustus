package client.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class IsBelowServerThresholdOfByteToMessageDecoder extends ByteToMessageDecoder {
   public final Inflater inflater;
   public int count;

   public void setCount(int count2) {
      this.count = count2;
   }

   public IsBelowServerThresholdOfByteToMessageDecoder(int count2) {
      this.count = count2;
      this.inflater = new Inflater();
   }

   public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws DataFormatException, Exception {
      if (byteBuf.readableBytes() != 0) {
         PacketBuffer packet = new PacketBuffer(byteBuf);
         int intItem = packet.getInt3();
         if (intItem == 0) {
            list.add(packet.readBytes(packet.readableBytes()));
         } else {
            if (intItem < this.count) {
               throw new DecoderException("Badly compressed packet - size of " + intItem + " is below server threshold of " + this.count);
            }

            if (intItem > 2097152) {
               throw new DecoderException("Badly compressed packet - size of " + intItem + " is larger than protocol maximum of " + 2097152);
            }

            byte[] byteValueArray = new byte[packet.readableBytes()];
            packet.readBytes(byteValueArray);
            this.inflater.setInput(byteValueArray);
            byte[] byteValueArray2 = new byte[intItem];
            this.inflater.inflate(byteValueArray2);
            list.add(Unpooled.wrappedBuffer(byteValueArray2));
            this.inflater.reset();
         }
      }
   }
}
