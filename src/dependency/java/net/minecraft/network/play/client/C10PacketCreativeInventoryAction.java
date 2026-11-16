package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.item.ItemStack;

public class C10PacketCreativeInventoryAction implements Packet {
   public int count;
   public ItemStack itemStack;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.readShort();
      this.itemStack = packet.getItemStack();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public int getCount() {
      return this.count;
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeShort(this.count);
      packet.applyItemStack(this.itemStack);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC10PacketCreativeInventory(this);
   }

   public C10PacketCreativeInventoryAction() {
   }

   public C10PacketCreativeInventoryAction(int count2, ItemStack stack) {
      this.count = count2;
      this.itemStack = stack != null ? stack.getItemStack() : null;
   }
}
