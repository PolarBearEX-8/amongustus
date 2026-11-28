package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C10PacketCreativeInventoryAction implements Packet {
   private int slotId;
   private ItemStack stack;

   public C10PacketCreativeInventoryAction(int n, ItemStack stack2) {
      this.slotId = n;
      this.stack = stack2 != null ? stack2.copy() : null;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processCreativeInventoryAction(this);
   }

   public C10PacketCreativeInventoryAction() {
   }

   public int getSlotId() {
      return this.slotId;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.slotId = buf.readShort();
      this.stack = buf.readItemStackFromBuffer();
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeShort(this.slotId);
      buf.writeItemStackToBuffer(this.stack);
   }

   public ItemStack getStack() {
      return this.stack;
   }
}
