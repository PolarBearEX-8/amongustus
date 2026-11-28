package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class C07PacketPlayerDigging implements Packet {
   private BlockPos position;
   private EnumFacing facing;
   private C07PacketPlayerDigging.Action status;

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.status = (C07PacketPlayerDigging.Action)buf.readEnumValue(C07PacketPlayerDigging.Action.class);
      this.position = buf.readBlockPos();
      this.facing = EnumFacing.getFront(buf.readUnsignedByte());
   }

   public BlockPos getPosition() {
      return this.position;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeEnumValue(this.status);
      buf.writeBlockPos(this.position);
      buf.writeByte(this.facing.getIndex());
   }

   public EnumFacing getFacing() {
      return this.facing;
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processPlayerDigging(this);
   }

   public C07PacketPlayerDigging.Action getStatus() {
      return this.status;
   }

   public C07PacketPlayerDigging() {
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   public C07PacketPlayerDigging(C07PacketPlayerDigging.Action action, BlockPos pos, EnumFacing facing2) {
      this.status = action;
      this.position = pos;
      this.facing = facing2;
   }


   public enum Action {
      START_DESTROY_BLOCK,
      ABORT_DESTROY_BLOCK,
      STOP_DESTROY_BLOCK,
      DROP_ALL_ITEMS,
      DROP_ITEM,
      RELEASE_USE_ITEM;
   }
}
