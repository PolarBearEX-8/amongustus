package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class ExtraListEntry {
   public final List list;
   public final InterpolationCollector interpolationCollector;
   public final short[] shortCodeArray;
   public int count;
   public int count2;
   public long timestamp;
   public final ManagedLoggerEntry managedLoggerEntry;

   public static InterpolationCollector resolveInterpolationCollector(ExtraListEntry extraListEntry) {
      return extraListEntry.interpolationCollector;
   }

   public void applyEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter) {
      if (this.list.contains(entityPlayerAdapter)) {
         ManagedLoggerEntry.getLogger()
            .debug(
               "Failed to add player. {} already is in chunk {}, {}",
               new Object[]{entityPlayerAdapter, this.interpolationCollector.count3, this.interpolationCollector.count2}
            );
      } else {
         if (this.list.isEmpty()) {
            this.timestamp = ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry).getTimestamp2();
         }

         this.list.add(entityPlayerAdapter);
         entityPlayerAdapter.list.add(this.interpolationCollector);
      }
   }

   public void applyPacket(Packet packet) {
      for (int index = 0; index < this.list.size(); index++) {
         EntityPlayerAdapter entityPlayerAdapter = (EntityPlayerAdapter)this.list.get(index);
         if (!entityPlayerAdapter.list.contains(this.interpolationCollector)) {
            entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(packet);
         }
      }
   }

   public void updateState2() {
      this.applyAlertNotificationSound(
         ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry).getLoggerEntry2(this.interpolationCollector.count3, this.interpolationCollector.count2)
      );
   }

   public void updateState() {
      if (this.count != 0) {
         if (this.count == 1) {
            int count3 = (this.shortCodeArray[0] >> 12 & 15) + this.interpolationCollector.count3 * 16;
            int count4 = this.shortCodeArray[0] & 255;
            int count5 = (this.shortCodeArray[0] >> 8 & 15) + this.interpolationCollector.count2 * 16;
            BlockPos blockPos = new BlockPos(count3, count4, count5);
            this.applyPacket(new S23PacketBlockChange(ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry), blockPos));
            if (ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry).getBlockState(blockPos).getBlock().isState8()) {
               this.applyTileEntity(ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry).resolveTileEntity(blockPos));
            }
         } else if (this.count != 64) {
            this.applyPacket(
               new S22PacketMultiBlockChange(
                  this.count,
                  this.shortCodeArray,
                  ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry)
                     .getLoggerEntry2(this.interpolationCollector.count3, this.interpolationCollector.count2)
               )
            );

            for (int index = 0; index < this.count; index++) {
               int count6 = (this.shortCodeArray[index] >> 12 & 15) + this.interpolationCollector.count3 * 16;
               int count7 = this.shortCodeArray[index] & 255;
               int count8 = (this.shortCodeArray[index] >> 8 & 15) + this.interpolationCollector.count2 * 16;
               BlockPos blockPos2 = new BlockPos(count6, count7, count8);
               if (ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry).getBlockState(blockPos2).getBlock().isState8()) {
                  this.applyTileEntity(ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry).resolveTileEntity(blockPos2));
               }
            }
         } else {
            int count9 = this.interpolationCollector.count3 * 16;
            int count10 = this.interpolationCollector.count2 * 16;
            this.applyPacket(
               new S21PacketChunkData(
                  ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry)
                     .getLoggerEntry2(this.interpolationCollector.count3, this.interpolationCollector.count2),
                  false,
                  this.count2
               )
            );

            for (int index2 = 0; index2 < 16; index2++) {
               if ((this.count2 & 1 << index2) != 0) {
                  int count11 = index2 << 4;
                  List list2 = ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry).resolveList(count9, count11, count10, count9 + 16, count11 + 16, count10 + 16);

                  for (int index3 = 0; index3 < list2.size(); index3++) {
                     this.applyTileEntity((TileEntity)list2.get(index3));
                  }
               }
            }
         }

         this.count = 0;
         this.count2 = 0;
      }
   }

   public ExtraListEntry(ManagedLoggerEntry managedLoggerEntry2, int count3, int count4) {
      this.managedLoggerEntry = managedLoggerEntry2;
      this.list = Lists.newArrayList();
      this.shortCodeArray = new short[64];
      this.interpolationCollector = new InterpolationCollector(count3, count4);
      managedLoggerEntry2.getWorldServer().listenerAdapter21.resolveAlertNotificationSoundByIndex(count3, count4);
   }

   public static List resolveList(ExtraListEntry extraListEntry) {
      return extraListEntry.list;
   }

   private void applyTileEntity(TileEntity tileEntity) {
      if (tileEntity != null) {
         Packet packet = tileEntity.getPacket();
         if (packet != null) {
            this.applyPacket(packet);
         }
      }
   }

   private void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound) {
      alertNotificationSound.setTimestamp(alertNotificationSound.getTimestamp() + ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry).getTimestamp2() - this.timestamp);
      this.timestamp = ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry).getTimestamp2();
   }

   public void applyEntityPlayerAdapterByEntityPlayerAdap(EntityPlayerAdapter entityPlayerAdapter) {
      if (this.list.contains(entityPlayerAdapter)) {
         AlertNotificationSound loggerEntry = ManagedLoggerEntry.resolveWorldServer(this.managedLoggerEntry)
            .getLoggerEntry2(this.interpolationCollector.count3, this.interpolationCollector.count2);
         if (loggerEntry.isActive()) {
            entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S21PacketChunkData(loggerEntry, true, 0));
         }

         this.list.remove(entityPlayerAdapter);
         entityPlayerAdapter.list.remove(this.interpolationCollector);
         if (this.list.isEmpty()) {
            long value = (long)this.interpolationCollector.count3 + 2147483647L | (long)this.interpolationCollector.count2 + 2147483647L << 32;
            this.applyAlertNotificationSound(loggerEntry);
            ManagedLoggerEntry.resolveInternalFloatEntry(this.managedLoggerEntry).resolveTargetByTime(value);
            ManagedLoggerEntry.resolveListByManagedLoggerEnt(this.managedLoggerEntry).remove(this);
            if (this.count > 0) {
               ManagedLoggerEntry.resolveList(this.managedLoggerEntry).remove(this);
            }

            this.managedLoggerEntry.getWorldServer().listenerAdapter21.applyIndex(this.interpolationCollector.count3, this.interpolationCollector.count2);
         }
      }
   }

   public void applyIndex(int count3, int count4, int count5) {
      if (this.count == 0) {
         ManagedLoggerEntry.resolveList(this.managedLoggerEntry).add(this);
      }

      this.count2 |= 1 << (count4 >> 4);
      if (this.count < 64) {
         short shortValue = (short)(count3 << 12 | count5 << 8 | count4);

         for (int index = 0; index < this.count; index++) {
            if (this.shortCodeArray[index] == shortValue) {
               return;
            }
         }

         this.shortCodeArray[this.count++] = shortValue;
      }
   }
}
