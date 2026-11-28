package net.raphimc.vialegacy.netty;

import com.google.common.collect.EvictingQueue;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.type.Types;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import java.util.logging.Logger;
import net.raphimc.vialegacy.ViaLegacy;
import net.raphimc.vialegacy.api.splitter.PreNettyPacketType;
import net.raphimc.vialegacy.api.splitter.PreNettySplitter;

public class PreNettyLengthPrepender extends ByteToMessageDecoder {
   public static final String NAME = "vialegacy-pre-netty-length-prepender";
   protected final UserConnection user;
   private final EvictingQueue<Integer> lastPackets = EvictingQueue.create(8);

   public PreNettyLengthPrepender(UserConnection user) {
      this.user = user;
   }

   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
      if (in.isReadable() && in.readableBytes() > 0) {
         PreNettySplitter splitter = (PreNettySplitter)this.user.get(PreNettySplitter.class);
         if (splitter == null) {
            ViaLegacy.getPlatform().getLogger().severe("Received data, but no splitter is set");
         } else {
            while (in.readableBytes() > 0) {
               in.markReaderIndex();
               int packetId = in.readUnsignedByte();
               PreNettyPacketType packetType = splitter.getPacketType(packetId);
               if (packetType == null) {
                  Logger logger = ViaLegacy.getPlatform().getLogger();
                  String protocolName = splitter.getProtocolName();
                  logger.severe("Encountered undefined packet: " + packetId + " in " + protocolName);
                  ViaLegacy.getPlatform().getLogger().severe(ByteBufUtil.hexDump(in.readSlice(in.readableBytes())));
                  logger = ViaLegacy.getPlatform().getLogger();
                  String str = String.valueOf(this.lastPackets);
                  logger.severe("Last 8 read packet ids: " + str);
                  ctx.channel().close();
                  return;
               }

               this.lastPackets.add(packetId);

               try {
                  int begin = in.readerIndex();
                  packetType.getPacketReader().accept(this.user, in);
                  int length = in.readerIndex() - begin;
                  in.readerIndex(begin);
                  int totalLength = length;
                  int i = 1;

                  while (true) {
                     if (i < 5) {
                        if ((packetId & -1 << i * 7) != 0) {
                           i++;
                           continue;
                        }

                        totalLength = length + i;
                     }

                     ByteBuf buf = ctx.alloc().buffer();
                     Types.VAR_INT.writePrimitive(buf, totalLength);
                     Types.VAR_INT.writePrimitive(buf, packetId);
                     buf.writeBytes(in.readSlice(length));
                     out.add(buf);
                     break;
                  }
               } catch (IndexOutOfBoundsException e) {
                  in.resetReaderIndex();
                  return;
               }
            }
         }
      }
   }
}
