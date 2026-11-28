package net.minecraft.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.network.PacketBuffer;

public class MessageSerializer2 extends MessageToByteEncoder {

   public void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
      this.encode(ctx, (ByteBuf)msg, out);
   }

   public void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
      int n = msg.readableBytes();
      int varIntSize = PacketBuffer.getVarIntSize(n);
      if (varIntSize > 3) {
         throw new IllegalArgumentException("unable to fit " + n + " into " + 3);
      } else {
         PacketBuffer buf = new PacketBuffer(out);
         buf.ensureWritable(varIntSize + n);
         buf.writeVarIntToBuffer(n);
         buf.writeBytes(msg, msg.readerIndex(), n);
      }
   }
}
