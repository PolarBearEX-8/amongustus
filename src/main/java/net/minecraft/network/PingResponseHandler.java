package net.minecraft.network;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.InetSocketAddress;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PingResponseHandler extends ChannelInboundHandlerAdapter {
   private static final Logger LOGGER = LogManager.getLogger();
   private NetworkSystem networkSystem;

   public PingResponseHandler(NetworkSystem networkSystem2) {
      this.networkSystem = networkSystem2;
   }

   private void writeAndFlush(ChannelHandlerContext ctx, ByteBuf buf) {
      ctx.pipeline().firstContext().writeAndFlush(buf).addListener(ChannelFutureListener.CLOSE);
   }

   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      ByteBuf buf = (ByteBuf)msg;
      buf.markReaderIndex();
      boolean flag = true;

      try {
         if (buf.readUnsignedByte() == 254) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
            MinecraftServer server = this.networkSystem.getServer();
            switch (buf.readableBytes()) {
               case 0:
                  LOGGER.debug("Ping: (<1.3.x) from {}:{}", new Object[]{inetSocketAddress.getAddress(), inetSocketAddress.getPort()});
                  this.writeAndFlush(ctx, this.getStringBuffer(String.format("%s§%d§%d", server.getMOTD(), server.getCurrentPlayerCount(), server.getMaxPlayers())));
                  break;
               case 1:
                  if (buf.readUnsignedByte() != 1) {
                     return;
                  }

                  LOGGER.debug("Ping: (1.4-1.5.x) from {}:{}", new Object[]{inetSocketAddress.getAddress(), inetSocketAddress.getPort()});
                  String str2 = String.format(
                     "§1\u0000%d\u0000%s\u0000%s\u0000%d\u0000%d",
                     127,
                     server.getMinecraftVersion(),
                     server.getMOTD(),
                     server.getCurrentPlayerCount(),
                     server.getMaxPlayers()
                  );
                  this.writeAndFlush(ctx, this.getStringBuffer(str2));
                  break;
               default:
                  boolean flag2 = buf.readUnsignedByte() == 1;
                  flag2 &= buf.readUnsignedByte() == 250;
                  flag2 &= "MC|PingHost".equals(new String(buf.readBytes(buf.readShort() * 2).array(), Charsets.UTF_16BE));
                  int n2 = buf.readUnsignedShort();
                  flag2 &= buf.readUnsignedByte() >= 73;
                  flag2 &= 3 + buf.readBytes(buf.readShort() * 2).array().length + 4 == n2;
                  flag2 &= buf.readInt() <= 65535;
                  flag2 &= buf.readableBytes() == 0;
                  if (!flag2) {
                     return;
                  }

                  LOGGER.debug("Ping: (1.6) from {}:{}", new Object[]{inetSocketAddress.getAddress(), inetSocketAddress.getPort()});
                  String str3 = String.format(
                     "§1\u0000%d\u0000%s\u0000%s\u0000%d\u0000%d",
                     127,
                     server.getMinecraftVersion(),
                     server.getMOTD(),
                     server.getCurrentPlayerCount(),
                     server.getMaxPlayers()
                  );
                  ByteBuf stringBuffer = this.getStringBuffer(str3);

                  try {
                     this.writeAndFlush(ctx, stringBuffer);
                  } finally {
                     stringBuffer.release();
                  }
            }

            buf.release();
            flag = false;
         }
      } catch (RuntimeException e) {
      } finally {
         if (flag) {
            buf.resetReaderIndex();
            ctx.channel().pipeline().remove("legacy_query");
            ctx.fireChannelRead(msg);
         }
      }
   }

   private ByteBuf getStringBuffer(String str) {
      ByteBuf buf = Unpooled.buffer();
      buf.writeByte(255);
      char[] chars = str.toCharArray();
      buf.writeShort(chars.length);

      for (char ch : chars) {
         buf.writeChar(ch);
      }

      return buf;
   }
}
