package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;
import org.apache.commons.lang3.StringUtils;

public class C14PacketTabComplete implements Packet {
   private String message;
   private BlockPos targetBlock;

   public String getMessage() {
      return this.message;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.message = buf.readUtf8String(32767);
      if (buf.readBoolean()) {
         this.targetBlock = buf.readBlockPos();
      }
   }

   public BlockPos getTargetBlock() {
      return this.targetBlock;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(StringUtils.substring(this.message, 0, 32767));
      boolean flag = this.targetBlock != null;
      buf.writeBoolean(flag);
      if (flag) {
         buf.writeBlockPos(this.targetBlock);
      }
   }

   public C14PacketTabComplete() {
   }

   public C14PacketTabComplete(String str) {
      this(str, (BlockPos)null);
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processTabComplete(this);
   }

   public C14PacketTabComplete(String str, BlockPos pos) {
      this.message = str;
      this.targetBlock = pos;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }
}
