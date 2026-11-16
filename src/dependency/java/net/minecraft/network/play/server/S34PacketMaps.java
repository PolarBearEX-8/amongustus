package net.minecraft.network.play.server;

import client.util.ByteEntry;
import client.util.DimensionStringEntry;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import java.util.Collection;

public class S34PacketMaps implements Packet {
   public int count4;
   public byte byteFlag;
   public ByteEntry[] byteEntryArray;
   public int count2;
   public int count3;
   public int count5;
   public int count;
   public byte[] byteFlagArray;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS34PacketMaps(this);
   }

   public void applyDimensionStringEntry(DimensionStringEntry dimensionStringEntry) {
      dimensionStringEntry.byteFlag2 = this.byteFlag;
      dimensionStringEntry.map.clear();

      for (int index = 0; index < this.byteEntryArray.length; index++) {
         ByteEntry byteEntry = this.byteEntryArray[index];
         dimensionStringEntry.map.put("icon-" + index, byteEntry);
      }

      for (int index2 = 0; index2 < this.count5; index2++) {
         for (int index3 = 0; index3 < this.count; index3++) {
            dimensionStringEntry.byteFlagArray[this.count2 + index2 + (this.count3 + index3) * 128] = this.byteFlagArray[index2 + index3 * this.count5];
         }
      }
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count4 = packet.getInt3();
      this.byteFlag = packet.readByte();
      this.byteEntryArray = new ByteEntry[packet.getInt3()];

      for (int index = 0; index < this.byteEntryArray.length; index++) {
         short shortValue = (short)packet.readByte();
         this.byteEntryArray[index] = new ByteEntry((byte)(shortValue >> 4 & 15), packet.readByte(), packet.readByte(), (byte)(shortValue & 15));
      }

      this.count5 = packet.readUnsignedByte();
      if (this.count5 > 0) {
         this.count = packet.readUnsignedByte();
         this.count2 = packet.readUnsignedByte();
         this.count3 = packet.readUnsignedByte();
         this.byteFlagArray = packet.getByteArray();
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count4);
      packet.writeByte(this.byteFlag);
      packet.updateInt(this.byteEntryArray.length);

      for (ByteEntry byteEntry : this.byteEntryArray) {
         packet.writeByte((byteEntry.getByteFlag() & 15) << 4 | byteEntry.getByteFlag2() & 15);
         packet.writeByte(byteEntry.getByteFlag4());
         packet.writeByte(byteEntry.getByteFlag3());
      }

      packet.writeByte(this.count5);
      if (this.count5 > 0) {
         packet.writeByte(this.count);
         packet.writeByte(this.count2);
         packet.writeByte(this.count3);
         packet.applyByteArray(this.byteFlagArray);
      }
   }

   public int getCount4() {
      return this.count4;
   }

   public S34PacketMaps(int count6, byte byteValue, Collection collection, byte[] byteValueArray, int count7, int count8, int count9, int count10) {
      this.count4 = count6;
      this.byteFlag = byteValue;
      this.byteEntryArray = ((ByteEntry[])collection.toArray(new ByteEntry[collection.size()]));
      this.count2 = count7;
      this.count3 = count8;
      this.count5 = count9;
      this.count = count10;
      this.byteFlagArray = new byte[count9 * count10];

      for (int index = 0; index < count9; index++) {
         for (int index2 = 0; index2 < count10; index2++) {
            this.byteFlagArray[index + index2 * count9] = byteValueArray[count7 + index + (count8 + index2) * 128];
         }
      }
   }

   public S34PacketMaps() {
   }
}
