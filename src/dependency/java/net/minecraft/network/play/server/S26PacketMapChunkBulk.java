package net.minecraft.network.play.server;

import client.util.AlertNotificationSound;
import client.util.ByteArrayHolder;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import java.util.List;

public class S26PacketMapChunkBulk implements Packet {
   public int[] countArray2;
   public int[] countArray;
   public ByteArrayHolder[] byteArrayHolderArray;
   public boolean enabled;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.enabled = packet.readBoolean();
      int intItem = packet.getInt3();
      this.countArray2 = new int[intItem];
      this.countArray = new int[intItem];
      this.byteArrayHolderArray = new ByteArrayHolder[intItem];

      for (int index = 0; index < intItem; index++) {
         this.countArray2[index] = packet.readInt();
         this.countArray[index] = packet.readInt();
         this.byteArrayHolderArray[index] = new ByteArrayHolder();
         this.byteArrayHolderArray[index].count = packet.readShort() & '\uffff';
         this.byteArrayHolderArray[index].byteFlagArray = new byte[S21PacketChunkData.computeCount(
            Integer.bitCount(this.byteArrayHolderArray[index].count), this.enabled, true
         )];
      }

      for (int index2 = 0; index2 < intItem; index2++) {
         packet.readBytes(this.byteArrayHolderArray[index2].byteFlagArray);
      }
   }

   public byte[] resolveByteArray(int count) {
      return this.byteArrayHolderArray[count].byteFlagArray;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeBoolean(this.enabled);
      packet.updateInt(this.byteArrayHolderArray.length);

      for (int index = 0; index < this.countArray2.length; index++) {
         packet.writeInt(this.countArray2[index]);
         packet.writeInt(this.countArray[index]);
         packet.writeShort((short)(this.byteArrayHolderArray[index].count & 65535));
      }

      for (int index2 = 0; index2 < this.countArray2.length; index2++) {
         packet.writeBytes(this.byteArrayHolderArray[index2].byteFlagArray);
      }
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS26PacketMapChunkBulk(this);
   }

   public int computeCountByIndex(int count) {
      return this.countArray2[count];
   }

   public int computeCount2(int count) {
      return this.byteArrayHolderArray[count].count;
   }

   public int getCount() {
      return this.countArray2.length;
   }

   public int computeCount(int count) {
      return this.countArray[count];
   }

   public S26PacketMapChunkBulk() {
   }

   public S26PacketMapChunkBulk(List list) {
      int size = list.size();
      this.countArray2 = new int[size];
      this.countArray = new int[size];
      this.byteArrayHolderArray = new ByteArrayHolder[size];
      this.enabled = !((AlertNotificationSound)list.get(0)).getWorld().secondaryFloatArrayEntry.isEnabled();

      for (int index = 0; index < size; index++) {
         AlertNotificationSound alertNotificationSound = (AlertNotificationSound)list.get(index);
         ByteArrayHolder byteArrayHolder = S21PacketChunkData.resolveByteArrayHolder(alertNotificationSound, true, this.enabled, 65535);
         this.countArray2[index] = alertNotificationSound.indexOffset1;
         this.countArray[index] = alertNotificationSound.indexOffset;
         this.byteArrayHolderArray[index] = byteArrayHolder;
      }
   }
}
