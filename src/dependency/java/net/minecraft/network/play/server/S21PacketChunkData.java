package net.minecraft.network.play.server;

import client.util.AlertNotificationSound;
import client.util.ByteArrayHolder;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.InternalCharArrayEntry;
import client.util.Packet;
import client.util.PacketBuffer;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class S21PacketChunkData implements Packet {
   public int count2;
   public int count;
   public ByteArrayHolder byteArrayHolder;
   public boolean enabled;

   public int getCount() {
      return this.byteArrayHolder.count;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count2 = packet.readInt();
      this.count = packet.readInt();
      this.enabled = packet.readBoolean();
      this.byteArrayHolder = new ByteArrayHolder();
      this.byteArrayHolder.count = packet.readShort();
      this.byteArrayHolder.byteFlagArray = packet.getByteArray();
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
      packet.writeInt(this.count2);
      packet.writeInt(this.count);
      packet.writeBoolean(this.enabled);
      packet.writeShort((short)(this.byteArrayHolder.count & 65535));
      packet.applyByteArray(this.byteArrayHolder.byteFlagArray);
   }

   private static int arraycopy(byte[] byteValueArray, byte[] byteValueArray2, int count3) {
      System.arraycopy(byteValueArray, 0, byteValueArray2, count3, byteValueArray.length);
      return count3 + byteValueArray.length;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public static int computeCount(int count3, boolean flag, boolean flag2) {
      int count4 = count3 * 2 * 16 * 16 * 16;
      int count5 = count3 * 16 * 16 * 16 / 2;
      int count6 = flag ? count3 * 16 * 16 * 16 / 2 : 0;
      int count7 = flag2 ? 256 : 0;
      return count4 + count5 + count6 + count7;
   }

   public int getCountAsIndex() {
      return this.count;
   }

   public static ByteArrayHolder resolveByteArrayHolder(AlertNotificationSound alertNotificationSound, boolean flag, boolean flag2, int count3) {
      InternalCharArrayEntry[] internalCharArrayEntryArray = alertNotificationSound.getInternalCharArrayEntryArray();
      ByteArrayHolder byteArrayHolder2 = new ByteArrayHolder();
      ArrayList list = Lists.newArrayList();

      for (int index = 0; index < internalCharArrayEntryArray.length; index++) {
         InternalCharArrayEntry internalCharArrayEntry = internalCharArrayEntryArray[index];
         if (internalCharArrayEntry != null && (!flag || !internalCharArrayEntry.isActiveAsFlag()) && (count3 & 1 << index) != 0) {
            byteArrayHolder2.count |= 1 << index;
            list.add(internalCharArrayEntry);
         }
      }

      byteArrayHolder2.byteFlagArray = new byte[computeCount(Integer.bitCount(byteArrayHolder2.count), flag2, flag)];
      int count4 = 0;
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         for (char character : ((InternalCharArrayEntry)iterator.next()).getCharCodeArray()) {
            byteArrayHolder2.byteFlagArray[count4++] = (byte)(character & 255);
            byteArrayHolder2.byteFlagArray[count4++] = (byte)(character >> '\b' & 0xFF);
         }
      }

      Iterator iterator2 = list.iterator();

      while (iterator2.hasNext()) {
         count4 = arraycopy(((InternalCharArrayEntry)iterator2.next()).getSpecialByteArrayEntry2().getByteFlagArray(), byteArrayHolder2.byteFlagArray, count4);
      }

      if (flag2) {
         Iterator iterator3 = list.iterator();

         while (iterator3.hasNext()) {
            count4 = arraycopy(((InternalCharArrayEntry)iterator3.next()).getSpecialByteArrayEntry().getByteFlagArray(), byteArrayHolder2.byteFlagArray, count4);
         }
      }

      if (flag) {
         arraycopy(alertNotificationSound.getByteFlagArray(), byteArrayHolder2.byteFlagArray, count4);
      }

      return byteArrayHolder2;
   }

   public byte[] getByteArray() {
      return this.byteArrayHolder.byteFlagArray;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS21PacketChunkData(this);
   }

   public S21PacketChunkData(AlertNotificationSound alertNotificationSound, boolean flag, int count3) {
      this.count2 = alertNotificationSound.indexOffset1;
      this.count = alertNotificationSound.indexOffset;
      this.enabled = flag;
      this.byteArrayHolder = resolveByteArrayHolder(alertNotificationSound, flag, !alertNotificationSound.getWorld().secondaryFloatArrayEntry.isEnabled(), count3);
   }

   public S21PacketChunkData() {
   }
}
