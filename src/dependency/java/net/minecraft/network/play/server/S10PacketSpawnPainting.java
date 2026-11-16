package net.minecraft.network.play.server;

import client.util.BezierProfiler;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.EntityPainting;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class S10PacketSpawnPainting implements Packet {
   public int count;
   public BlockPos blockPos;
   public EnumFacing enumFacing;
   public String labelText;

   public EnumFacing getEnumFacing() {
      return this.enumFacing;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.getInt3();
      this.labelText = packet.resolveTextByIndex(BezierProfiler.count);
      this.blockPos = packet.getBlockPos();
      this.enumFacing = EnumFacing.resolveEnumFacing(packet.readUnsignedByte());
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int getCount() {
      return this.count;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count);
      packet.getPacketBuffer(this.labelText);
      packet.applyBlockPos(this.blockPos);
      packet.writeByte(this.enumFacing.getIndexOffset());
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS10PacketSpawnPainting(this);
   }

   public S10PacketSpawnPainting(EntityPainting entityPainting) {
      this.count = entityPainting.getEntityId();
      this.blockPos = entityPainting.getBlockPos2();
      this.enumFacing = entityPainting.enumFacing2;
      this.labelText = entityPainting.bezierProfiler.title;
   }

   public S10PacketSpawnPainting() {
   }
}
