package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class NettyCompressionDecoder extends ByteToMessageDecoder {
   private final Inflater inflater;
   private int treshold;

   public void setCompressionTreshold(int n) {
      this.treshold = n;
   }

   public NettyCompressionDecoder(int n) {
      this.treshold = n;
      this.inflater = new Inflater();
   }

   public void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws DataFormatException, Exception {
      if (in.readableBytes() != 0) {
         PacketBuffer buf = new PacketBuffer(in);
         int n = buf.readVarIntFromBuffer();
         if (n == 0) {
            out.add(buf.readBytes(buf.readableBytes()));
         } else {
            if (n < this.treshold) {
               throw new DecoderException("Badly compressed packet - size of " + n + " is below server threshold of " + this.treshold);
            }

            if (n > 2097152) {
               throw new DecoderException("Badly compressed packet - size of " + n + " is larger than protocol maximum of " + 2097152);
            }

            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);
            this.inflater.setInput(bytes);
            byte[] bytes2 = new byte[n];
            this.inflater.inflate(bytes2);
            out.add(Unpooled.wrappedBuffer(bytes2));
            this.inflater.reset();
         }
      }
   }
}
