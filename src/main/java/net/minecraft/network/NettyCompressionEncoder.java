package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.util.zip.Deflater;

public class NettyCompressionEncoder extends MessageToByteEncoder {
   private final byte[] buffer = new byte[8192];
   private final Deflater deflater;
   private int treshold;

   public void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
      int n = msg.readableBytes();
      PacketBuffer buf = new PacketBuffer(out);
      if (n < this.treshold) {
         buf.writeVarIntToBuffer(0);
         buf.writeBytes(msg);
      } else {
         byte[] bytes = new byte[n];
         msg.readBytes(bytes);
         buf.writeVarIntToBuffer(bytes.length);
         this.deflater.setInput(bytes, 0, n);
         this.deflater.finish();

         while (!this.deflater.finished()) {
            int n2 = this.deflater.deflate(this.buffer);
            buf.writeBytes(this.buffer, 0, n2);
         }

         this.deflater.reset();
      }
   }

   public void setCompressionTreshold(int n) {
      this.treshold = n;
   }

   public NettyCompressionEncoder(int n) {
      this.treshold = n;
      this.deflater = new Deflater();
   }

   public void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
      this.encode(ctx, (ByteBuf)msg, out);
   }
}
