package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.item.ItemStack;

public class S04PacketEntityEquipment implements Packet {
   public int count2;
   public int count;
   public ItemStack itemStack;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS04PacketEntityEquipment(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count2 = packet.getInt3();
      this.count = packet.readShort();
      this.itemStack = packet.getItemStack();
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count2);
      packet.writeShort(this.count);
      packet.applyItemStack(this.itemStack);
   }

   public S04PacketEntityEquipment(int count3, int count4, ItemStack stack) {
      this.count2 = count3;
      this.count = count4;
      this.itemStack = stack == null ? null : stack.getItemStack();
   }

   public S04PacketEntityEquipment() {
   }
}
