package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0EPacketClickWindow implements Packet {
   private int windowId;
   private int slotId;
   private int usedButton;
   private short actionNumber;
   private ItemStack clickedItem;
   private int mode;

   public ItemStack getClickedItem() {
      return this.clickedItem;
   }

   public C0EPacketClickWindow(int n, int n2, int n3, int n4, ItemStack stack, short s) {
      this.windowId = n;
      this.slotId = n2;
      this.usedButton = n3;
      this.clickedItem = stack != null ? stack.copy() : null;
      this.actionNumber = s;
      this.mode = n4;
   }

   public int getSlotId() {
      return this.slotId;
   }

   public int getMode() {
      return this.mode;
   }

   public int getUsedButton() {
      return this.usedButton;
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processClickWindow(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeShort(this.slotId);
      buf.writeByte(this.usedButton);
      buf.writeShort(this.actionNumber);
      buf.writeByte(this.mode);
      buf.writeItemStackToBuffer(this.clickedItem);
   }

   public C0EPacketClickWindow() {
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.windowId = buf.readByte();
      this.slotId = buf.readShort();
      this.usedButton = buf.readByte();
      this.actionNumber = buf.readShort();
      this.mode = buf.readByte();
      this.clickedItem = buf.readItemStackFromBuffer();
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   public int getWindowId() {
      return this.windowId;
   }

   public short getActionNumber() {
      return this.actionNumber;
   }
}
