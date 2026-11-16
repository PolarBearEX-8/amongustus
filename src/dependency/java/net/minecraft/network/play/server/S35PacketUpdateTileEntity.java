package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public class S35PacketUpdateTileEntity implements Packet {
   public BlockPos blockPos;
   public int count;
   public NBTTagCompound nBTTagCompound;

   public NBTTagCompound getNBTTagCompound() {
      return this.nBTTagCompound;
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.blockPos = packet.getBlockPos();
      this.count = packet.readUnsignedByte();
      this.nBTTagCompound = packet.getNBTTagCompound();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyBlockPos(this.blockPos);
      packet.writeByte((byte)this.count);
      packet.applyNBTTagCompound(this.nBTTagCompound);
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS35PacketUpdateTileEntity(this);
   }

   public S35PacketUpdateTileEntity() {
   }

   public S35PacketUpdateTileEntity(BlockPos blockPos2, int count2, NBTTagCompound tagCompound) {
      this.blockPos = blockPos2;
      this.count = count2;
      this.nBTTagCompound = tagCompound;
   }
}
