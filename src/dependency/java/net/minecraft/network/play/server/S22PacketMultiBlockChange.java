package net.minecraft.network.play.server;

import client.util.AlertNotificationSound;
import client.util.IBlockState;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.InterpolationCollector;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.ShortEntry;
import java.io.IOException;
import net.minecraft.block.Block;

public class S22PacketMultiBlockChange implements Packet {
   public InterpolationCollector interpolationCollector;
   public ShortEntry[] shortEntryArray;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.interpolationCollector = new InterpolationCollector(packet.readInt(), packet.readInt());
      this.shortEntryArray = new ShortEntry[packet.getInt3()];

      for (int index = 0; index < this.shortEntryArray.length; index++) {
         this.shortEntryArray[index] = new ShortEntry(this, packet.readShort(), (IBlockState)Block.identityHashMapEntry.resolveTarget(packet.getInt3()));
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public ShortEntry[] getShortEntryArray() {
      return this.shortEntryArray;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS22PacketMultiBlockChange(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeInt(this.interpolationCollector.count3);
      packet.writeInt(this.interpolationCollector.count2);
      packet.updateInt(this.shortEntryArray.length);

      for (ShortEntry shortEntry : this.shortEntryArray) {
         packet.writeShort(shortEntry.getShortCode());
         packet.updateInt(Block.identityHashMapEntry.computeCount(shortEntry.getIBlockState()));
      }
   }

   public static InterpolationCollector resolveInterpolationCollector(S22PacketMultiBlockChange packet) {
      return packet.interpolationCollector;
   }

   public S22PacketMultiBlockChange() {
   }

   public S22PacketMultiBlockChange(int count, short[] shortValueArray, AlertNotificationSound alertNotificationSound) {
      this.interpolationCollector = new InterpolationCollector(alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset);
      this.shortEntryArray = new ShortEntry[count];

      for (int index = 0; index < this.shortEntryArray.length; index++) {
         this.shortEntryArray[index] = new ShortEntry(this, shortValueArray[index], alertNotificationSound);
      }
   }
}
