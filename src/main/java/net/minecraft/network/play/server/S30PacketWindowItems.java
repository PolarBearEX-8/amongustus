package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S30PacketWindowItems implements Packet {
   private int windowId;
   private ItemStack[] itemStacks;

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleWindowItems(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public ItemStack[] getItemStacks() {
      return this.itemStacks;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.windowId = buf.readUnsignedByte();
      short s = buf.readShort();
      this.itemStacks = new ItemStack[s];

      for (int i = 0; i < s; i++) {
         this.itemStacks[i] = buf.readItemStackFromBuffer();
      }
   }

   public int getWindowId() {
      return this.windowId;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeShort(this.itemStacks.length);

      for (ItemStack stack : this.itemStacks) {
         buf.writeItemStackToBuffer(stack);
      }
   }

   public S30PacketWindowItems(int n, List list) {
      this.windowId = n;
      this.itemStacks = new ItemStack[list.size()];

      for (int i = 0; i < this.itemStacks.length; i++) {
         ItemStack stack = (ItemStack)list.get(i);
         this.itemStacks[i] = stack == null ? null : stack.copy();
      }
   }

   public S30PacketWindowItems() {
   }
}
