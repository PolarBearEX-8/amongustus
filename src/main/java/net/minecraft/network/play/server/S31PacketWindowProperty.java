package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S31PacketWindowProperty implements Packet {
   private int windowId;
   private int varIndex;
   private int varValue;

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public int getVarIndex() {
      return this.varIndex;
   }

   public S31PacketWindowProperty(int n, int n2, int n3) {
      this.windowId = n;
      this.varIndex = n2;
      this.varValue = n3;
   }

   public S31PacketWindowProperty() {
   }

   public int getWindowId() {
      return this.windowId;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeShort(this.varIndex);
      buf.writeShort(this.varValue);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleWindowProperty(this);
   }

   public int getVarValue() {
      return this.varValue;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.windowId = buf.readUnsignedByte();
      this.varIndex = buf.readShort();
      this.varValue = buf.readShort();
   }
}
