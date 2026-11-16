package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S2EPacketCloseWindow implements Packet {
   public int count;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS2EPacketCloseWindow(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.readUnsignedByte();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(this.count);
   }

   public S2EPacketCloseWindow() {
   }

   public S2EPacketCloseWindow(int count2) {
      this.count = count2;
   }
}
