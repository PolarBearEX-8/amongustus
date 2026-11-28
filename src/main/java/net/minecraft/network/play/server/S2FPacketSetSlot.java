package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S2FPacketSetSlot implements Packet {
   private int windowId;
   private int slot;
   private ItemStack item;

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.windowId = buf.readByte();
      this.slot = buf.readShort();
      this.item = buf.readItemStackFromBuffer();
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeShort(this.slot);
      buf.writeItemStackToBuffer(this.item);
   }

   public S2FPacketSetSlot(int n, int n2, ItemStack stack) {
      this.windowId = n;
      this.slot = n2;
      this.item = stack == null ? null : stack.copy();
   }

   public ItemStack getItem() {
      return this.item;
   }

   public int getSlot() {
      return this.slot;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleSetSlot(this);
   }

   public S2FPacketSetSlot() {
   }

   public int getWindowId() {
      return this.windowId;
   }
}
