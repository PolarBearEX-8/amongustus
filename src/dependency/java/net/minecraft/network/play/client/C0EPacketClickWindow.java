package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.item.ItemStack;

public class C0EPacketClickWindow implements Packet {
   public int count;
   public int count3;
   public int count2;
   public short shortCode;
   public ItemStack itemStack;
   public int count4;

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public short getShortCode() {
      return this.shortCode;
   }

   public int getCount4() {
      return this.count4;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.readByte();
      this.count3 = packet.readShort();
      this.count2 = packet.readByte();
      this.shortCode = packet.readShort();
      this.count4 = packet.readByte();
      this.itemStack = packet.getItemStack();
   }

   public int getCount3() {
      return this.count3;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC0EPacketClickWindow(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(this.count);
      packet.writeShort(this.count3);
      packet.writeByte(this.count2);
      packet.writeShort(this.shortCode);
      packet.writeByte(this.count4);
      packet.applyItemStack(this.itemStack);
   }

   public int getCount2() {
      return this.count2;
   }

   public int getCount() {
      return this.count;
   }

   public C0EPacketClickWindow(int count5, int count6, int count7, int count8, ItemStack stack, short shortValue) {
      this.count = count5;
      this.count3 = count6;
      this.count2 = count7;
      this.itemStack = stack != null ? stack.getItemStack() : null;
      this.shortCode = shortValue;
      this.count4 = count8;
   }

   public C0EPacketClickWindow() {
   }
}
