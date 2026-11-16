package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.BlockPos;
import org.apache.commons.lang3.StringUtils;

public class C14PacketTabComplete implements Packet {
   public String labelText;
   public BlockPos blockPos;

   public String getLabelText() {
      return this.labelText;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.labelText = packet.resolveText(32767);
      if (packet.readBoolean()) {
         this.blockPos = packet.getBlockPos();
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC14PacketTabComplete(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(StringUtils.substring(this.labelText, 0, 32767));
      boolean flag = this.blockPos != null;
      packet.writeBoolean(flag);
      if (flag) {
         packet.applyBlockPos(this.blockPos);
      }
   }

   public C14PacketTabComplete() {
   }

   public C14PacketTabComplete(String text) {
      this(text, (BlockPos)null);
   }

   public C14PacketTabComplete(String text, BlockPos blockPos2) {
      this.labelText = text;
      this.blockPos = blockPos2;
   }
}
