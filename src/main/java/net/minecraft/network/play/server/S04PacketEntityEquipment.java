package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S04PacketEntityEquipment implements Packet {
   private int entityID;
   private int equipmentSlot;
   private ItemStack itemStack;

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityID);
      buf.writeShort(this.equipmentSlot);
      buf.writeItemStackToBuffer(this.itemStack);
   }

   public int getEquipmentSlot() {
      return this.equipmentSlot;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityEquipment(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S04PacketEntityEquipment() {
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public S04PacketEntityEquipment(int n, int n2, ItemStack stack) {
      this.entityID = n;
      this.equipmentSlot = n2;
      this.itemStack = stack == null ? null : stack.copy();
   }

   public int getEntityID() {
      return this.entityID;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityID = buf.readVarIntFromBuffer();
      this.equipmentSlot = buf.readShort();
      this.itemStack = buf.readItemStackFromBuffer();
   }
}
