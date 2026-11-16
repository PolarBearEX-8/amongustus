package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class S49PacketUpdateEntityNBT implements Packet {
   public int count;
   public NBTTagCompound nBTTagCompound;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.getInt3();
      this.nBTTagCompound = packet.getNBTTagCompound();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public Entity resolveEntity(World world) {
      return world.getEntityByID(this.count);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count);
      packet.applyNBTTagCompound(this.nBTTagCompound);
   }

   public NBTTagCompound getNBTTagCompound() {
      return this.nBTTagCompound;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS49PacketUpdateEntityNBT(this);
   }

   public S49PacketUpdateEntityNBT(int count2, NBTTagCompound tagCompound) {
      this.count = count2;
      this.nBTTagCompound = tagCompound;
   }

   public S49PacketUpdateEntityNBT() {
   }
}
