package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S0DPacketCollectItem implements Packet {
   public int count2;
   public int count;

   public int getCount() {
      return this.count;
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count2 = packet.getInt3();
      this.count = packet.getInt3();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS0DPacketCollectItem(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count2);
      packet.updateInt(this.count);
   }

   public S0DPacketCollectItem() {
   }

   public S0DPacketCollectItem(int count3, int count4) {
      this.count2 = count3;
      this.count = count4;
   }
}
