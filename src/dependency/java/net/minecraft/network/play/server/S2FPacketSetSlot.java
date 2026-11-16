package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.item.ItemStack;

public class S2FPacketSetSlot implements Packet {
   public int count;
   public int count2;
   public ItemStack itemStack;

   public int getCount() {
      return this.count;
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.readByte();
      this.count2 = packet.readShort();
      this.itemStack = packet.getItemStack();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(this.count);
      packet.writeShort(this.count2);
      packet.applyItemStack(this.itemStack);
   }

   public int getCount2() {
      return this.count2;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS2FPacketSetSlot(this);
   }

   public S2FPacketSetSlot(int count3, int count4, ItemStack stack) {
      this.count = count3;
      this.count2 = count4;
      this.itemStack = stack == null ? null : stack.getItemStack();
   }

   public S2FPacketSetSlot() {
   }
}
