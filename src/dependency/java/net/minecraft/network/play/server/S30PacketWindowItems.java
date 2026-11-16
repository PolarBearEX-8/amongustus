package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import java.util.List;
import net.minecraft.item.ItemStack;

public class S30PacketWindowItems implements Packet {
   public int count;
   public ItemStack[] itemStackArray;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS30PacketWindowItems(this);
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.readUnsignedByte();
      short shortValue = packet.readShort();
      this.itemStackArray = new ItemStack[shortValue];

      for (int index = 0; index < shortValue; index++) {
         this.itemStackArray[index] = packet.getItemStack();
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(this.count);
      packet.writeShort(this.itemStackArray.length);

      for (ItemStack stack : this.itemStackArray) {
         packet.applyItemStack(stack);
      }
   }

   public ItemStack[] getItemStackArray() {
      return this.itemStackArray;
   }

   public S30PacketWindowItems() {
   }

   public S30PacketWindowItems(int count2, List list) {
      this.count = count2;
      this.itemStackArray = new ItemStack[list.size()];

      for (int index = 0; index < this.itemStackArray.length; index++) {
         ItemStack stack = (ItemStack)list.get(index);
         this.itemStackArray[index] = stack == null ? null : stack.getItemStack();
      }
   }
}
