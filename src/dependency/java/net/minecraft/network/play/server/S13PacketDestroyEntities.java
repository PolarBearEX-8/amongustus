package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S13PacketDestroyEntities implements Packet {
   public int[] countArray;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.countArray = new int[packet.getInt3()];

      for (int index = 0; index < this.countArray.length; index++) {
         this.countArray[index] = packet.getInt3();
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int[] getIntValueArray() {
      return this.countArray;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS13PacketDestroyEntities(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.countArray.length);

      for (int index = 0; index < this.countArray.length; index++) {
         packet.updateInt(this.countArray[index]);
      }
   }

   public S13PacketDestroyEntities() {
   }

   public S13PacketDestroyEntities(int[] countArray2) {
      this.countArray = countArray2;
   }
}
