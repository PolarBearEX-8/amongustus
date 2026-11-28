package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S2DPacketOpenWindow implements Packet {
   private int windowId;
   private String inventoryType;
   private IChatComponent windowTitle;
   private int slotCount;
   private int entityId;

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeString(this.inventoryType);
      buf.writeChatComponent(this.windowTitle);
      buf.writeByte(this.slotCount);
      if (this.inventoryType.equals("EntityHorse")) {
         buf.writeInt(this.entityId);
      }
   }

   public IChatComponent getWindowTitle() {
      return this.windowTitle;
   }

   public S2DPacketOpenWindow(int n, String str, IChatComponent component) {
      this(n, str, component, 0);
   }

   public String getGuiId() {
      return this.inventoryType;
   }

   public boolean hasSlots() {
      return this.slotCount > 0;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public int getWindowId() {
      return this.windowId;
   }

   public int getSlotCount() {
      return this.slotCount;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleOpenWindow(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.windowId = buf.readUnsignedByte();
      this.inventoryType = buf.readStringFromBuffer(32);
      this.windowTitle = buf.readChatComponent();
      this.slotCount = buf.readUnsignedByte();
      if (this.inventoryType.equals("EntityHorse")) {
         this.entityId = buf.readInt();
      }
   }

   public S2DPacketOpenWindow() {
   }

   public S2DPacketOpenWindow(int n, String str, IChatComponent component, int n2, int n3) {
      this(n, str, component, n2);
      this.entityId = n3;
   }

   public int getEntityId() {
      return this.entityId;
   }

   public S2DPacketOpenWindow(int n, String str, IChatComponent component, int n2) {
      this.windowId = n;
      this.inventoryType = str;
      this.windowTitle = component;
      this.slotCount = n2;
   }
}
