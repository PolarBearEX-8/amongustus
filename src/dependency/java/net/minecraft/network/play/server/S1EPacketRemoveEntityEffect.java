package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.potion.PotionEffect;

public class S1EPacketRemoveEntityEffect implements Packet {
   public int count2;
   public int count;

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count2 = packet.getInt3();
      this.count = packet.readUnsignedByte();
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS1EPacketRemoveEntityEffec(this);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count2);
      packet.writeByte(this.count);
   }

   public S1EPacketRemoveEntityEffect(int count3, PotionEffect potionEffect) {
      this.count2 = count3;
      this.count = potionEffect.getIndexOffset3();
   }

   public S1EPacketRemoveEntityEffect() {
   }
}
