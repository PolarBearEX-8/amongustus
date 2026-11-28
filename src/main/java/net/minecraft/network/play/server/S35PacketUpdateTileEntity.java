package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S35PacketUpdateTileEntity implements Packet {
   private BlockPos blockPos;
   private int metadata;
   private NBTTagCompound nbt;

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeBlockPos(this.blockPos);
      buf.writeByte((byte)this.metadata);
      buf.writeNBTTagCompoundToBuffer(this.nbt);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public NBTTagCompound getNbtCompound() {
      return this.nbt;
   }

   public S35PacketUpdateTileEntity(BlockPos pos, int n, NBTTagCompound tag) {
      this.blockPos = pos;
      this.metadata = n;
      this.nbt = tag;
   }

   public int getTileEntityType() {
      return this.metadata;
   }

   public S35PacketUpdateTileEntity() {
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleUpdateTileEntity(this);
   }

   public BlockPos getPos() {
      return this.blockPos;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.blockPos = buf.readBlockPos();
      this.metadata = buf.readUnsignedByte();
      this.nbt = buf.readNBTTagCompoundFromBuffer();
   }
}
