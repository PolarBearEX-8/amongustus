package net.minecraft.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import java.util.List;
import net.minecraft.network.PacketBuffer;

public class MessageDeserializer2 extends ByteToMessageDecoder {
   public void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
      in.markReaderIndex();
      byte[] bytes = new byte[3];

      for (int i = 0; i < bytes.length; i++) {
         if (!in.isReadable()) {
            in.resetReaderIndex();
            return;
         }

         bytes[i] = in.readByte();
         if (bytes[i] >= 0) {
            PacketBuffer buf = new PacketBuffer(Unpooled.wrappedBuffer(bytes));

            try {
               int n = buf.readVarIntFromBuffer();
               if (in.readableBytes() < n) {
                  in.resetReaderIndex();
                  return;
               }

               out.add(in.readBytes(n));
            } finally {
               buf.release();
            }

            return;
         }
      }

      throw new CorruptedFrameException("length wider than 21-bit");
   }
}
