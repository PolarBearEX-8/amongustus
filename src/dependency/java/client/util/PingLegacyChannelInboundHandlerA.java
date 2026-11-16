package client.util;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.InetSocketAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PingLegacyChannelInboundHandlerA extends ChannelInboundHandlerAdapter {
   public static final Logger logger = LogManager.getLogger();
   public CustomLoggerEntry customLoggerEntry;

   private void applyChannelHandlerContext(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
      channelHandlerContext.pipeline().firstContext().writeAndFlush(byteBuf).addListener(ChannelFutureListener.CLOSE);
   }

   public PingLegacyChannelInboundHandlerA(CustomLoggerEntry customLoggerEntry2) {
      this.customLoggerEntry = customLoggerEntry2;
   }

   private ByteBuf resolveByteBuf(String text) {
      ByteBuf byteBuf = Unpooled.buffer();
      byteBuf.writeByte(255);
      char[] characterArray = text.toCharArray();
      byteBuf.writeShort(characterArray.length);

      for (char character : characterArray) {
         byteBuf.writeChar(character);
      }

      return byteBuf;
   }

   public void channelRead(ChannelHandlerContext channelHandlerContext, Object entry) throws Exception {
      ByteBuf byteBuf = (ByteBuf)entry;
      byteBuf.markReaderIndex();

      try {
         if (byteBuf.readUnsignedByte() == 254) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress)channelHandlerContext.channel().remoteAddress();
            VanillaRunnable vanillaRunnable = this.customLoggerEntry.getVanillaRunnable();
            switch (byteBuf.readableBytes()) {
               case 0:
                  logger.debug("Ping: (<1.3.x) from {}:{}", new Object[]{inetSocketAddress.getAddress(), inetSocketAddress.getPort()});
                  String text = String.format("%s§%d§%d", vanillaRunnable.getLabelText5(), vanillaRunnable.getCount8(), vanillaRunnable.getCount2());
                  this.applyChannelHandlerContext(channelHandlerContext, this.resolveByteBuf(text));
                  break;
               case 1:
                  if (byteBuf.readUnsignedByte() != 1) {
                     return;
                  }

                  logger.debug("Ping: (1.4-1.5.x) from {}:{}", new Object[]{inetSocketAddress.getAddress(), inetSocketAddress.getPort()});
                  String text2 = String.format(
                     "§1\u0000%d\u0000%s\u0000%s\u0000%d\u0000%d", 127, vanillaRunnable.getText2(), vanillaRunnable.getLabelText5(), vanillaRunnable.getCount8(), vanillaRunnable.getCount2()
                  );
                  this.applyChannelHandlerContext(channelHandlerContext, this.resolveByteBuf(text2));
                  break;
               default:
                  boolean flag = byteBuf.readUnsignedByte() == 1
                     & byteBuf.readUnsignedByte() == 250
                     & "MC|PingHost".equals(new String(byteBuf.readBytes(byteBuf.readShort() * 2).array(), Charsets.UTF_16BE));
                  int count = byteBuf.readUnsignedShort();
                  if (!(
                     flag
                        & byteBuf.readUnsignedByte() >= 73
                        & 3 + byteBuf.readBytes(byteBuf.readShort() * 2).array().length + 4 == count
                        & byteBuf.readInt() <= 65535
                        & byteBuf.readableBytes() == 0
                  )) {
                     return;
                  }

                  logger.debug("Ping: (1.6) from {}:{}", new Object[]{inetSocketAddress.getAddress(), inetSocketAddress.getPort()});
                  String text3 = String.format(
                     "§1\u0000%d\u0000%s\u0000%s\u0000%d\u0000%d", 127, vanillaRunnable.getText2(), vanillaRunnable.getLabelText5(), vanillaRunnable.getCount8(), vanillaRunnable.getCount2()
                  );
                  ByteBuf byteBuf2 = this.resolveByteBuf(text3);

                  try {
                     this.applyChannelHandlerContext(channelHandlerContext, byteBuf2);
                  } finally {
                     byteBuf2.release();
                  }
            }

            byteBuf.release();
         }
      } catch (RuntimeException exception) {
      } finally {
         byteBuf.resetReaderIndex();
         channelHandlerContext.channel().pipeline().remove("legacy_query");
         channelHandlerContext.fireChannelRead(entry);
      }
   }
}
