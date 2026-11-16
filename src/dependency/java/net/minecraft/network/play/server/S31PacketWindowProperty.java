package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S31PacketWindowProperty implements Packet {
   public int count2;
   public int count3;
   public int count;

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count2 = packet.readUnsignedByte();
      this.count3 = packet.readShort();
      this.count = packet.readShort();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int getCount3() {
      return this.count3;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(this.count2);
      packet.writeShort(this.count3);
      packet.writeShort(this.count);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS31PacketWindowProperty(this);
   }

   public int getCount() {
      return this.count;
   }

   public S31PacketWindowProperty() {
   }

   public S31PacketWindowProperty(int count4, int count5, int count6) {
      this.count2 = count4;
      this.count3 = count5;
      this.count = count6;
   }
}
