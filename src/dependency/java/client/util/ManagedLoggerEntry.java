package client.util;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManagedLoggerEntry {
   public static final Logger logger = LogManager.getLogger();
   public final WorldServer worldServer;
   public final List list = Lists.newArrayList();
   public final InternalFloatEntry internalFloatEntry = new InternalFloatEntry();
   public final List list2 = Lists.newArrayList();
   public final List list3 = Lists.newArrayList();
   public final int[][] countArray = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
   public final Map map = new HashMap();
   public int count;
   public long timestamp;

   public ManagedLoggerEntry(WorldServer worldServer2) {
      this.worldServer = worldServer2;
      this.applyIndex(worldServer2.getVanillaRunnable().getMovingManager().getCount3());
   }

   public static List resolveListByManagedLoggerEnt(ManagedLoggerEntry managedLoggerEntry) {
      return managedLoggerEntry.list3;
   }

   private PriorityQueue resolvePriorityQueue(Set set, EntityPlayerAdapter entityPlayerAdapter, int count2) {
      float factor = entityPlayerAdapter.rotationYaw + 90.0F;

      while (factor <= -180.0F) {
         factor += 360.0F;
      }

      while (factor > 180.0F) {
         factor -= 360.0F;
      }

      double offset = (double)factor * (Math.PI / 180.0);
      double offset2 = (double)entityPlayerAdapter.rotationPitch * (Math.PI / 180.0);
      StandardComparatorAdapter standardComparatorAdapter = new StandardComparatorAdapter(entityPlayerAdapter.count7, entityPlayerAdapter.count8, offset, offset2);
      Comparator comparator = Collections.reverseOrder(standardComparatorAdapter);
      PriorityQueue priorityQueue = new PriorityQueue(count2, comparator);

      for (InterpolationCollector interpolationCollector : (Iterable<InterpolationCollector>)(Object)(set)) {
         if (priorityQueue.size() < count2) {
            priorityQueue.add(interpolationCollector);
         } else {
            InterpolationCollector interpolationCollector2 = (InterpolationCollector)priorityQueue.peek();
            if (standardComparatorAdapter.computeCount(interpolationCollector, interpolationCollector2) < 0) {
               priorityQueue.remove();
               priorityQueue.add(interpolationCollector);
            }
         }
      }

      return priorityQueue;
   }

   private ExtraListEntry resolveExtraListEntry(int count2, int count3, boolean flag) {
      long value = (long)count2 + 2147483647L | (long)count3 + 2147483647L << 32;
      ExtraListEntry extraListEntry = (ExtraListEntry)this.internalFloatEntry.resolveTarget(value);
      if (extraListEntry == null && flag) {
         extraListEntry = new ExtraListEntry(this, count2, count3);
         this.internalFloatEntry.applyTarget(value, extraListEntry);
         this.list3.add(extraListEntry);
      }

      return extraListEntry;
   }

   public boolean checkIndex(int count2, int count3) {
      long value = (long)count2 + 2147483647L | (long)count3 + 2147483647L << 32;
      return this.internalFloatEntry.resolveTarget(value) != null;
   }

   public void applyIndex(int count2) {
      int intItem = MathHelper.getInt12(count2, 3, 64);
      if (intItem != this.count) {
         int count3 = intItem - this.count;

         for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>)(Object)(Lists.newArrayList(this.list))) {
            int count4 = (int)entityPlayerAdapter.posX >> 4;
            int count5 = (int)entityPlayerAdapter.posZ >> 4;
            Set set = this.resolveSet(entityPlayerAdapter);
            if (count3 > 0) {
               for (int index = count4 - intItem; index <= count4 + intItem; index++) {
                  for (int index2 = count5 - intItem; index2 <= count5 + intItem; index2++) {
                     if (BuildManager.isActive73()) {
                        set.add(new InterpolationCollector(index, index2));
                     } else {
                        ExtraListEntry extraListEntry = this.resolveExtraListEntry(index, index2, true);
                        if (!ExtraListEntry.resolveList(extraListEntry).contains(entityPlayerAdapter)) {
                           extraListEntry.applyEntityPlayerAdapter(entityPlayerAdapter);
                        }
                     }
                  }
               }
            } else {
               for (int index3 = count4 - this.count; index3 <= count4 + this.count; index3++) {
                  for (int index4 = count5 - this.count; index4 <= count5 + this.count; index4++) {
                     if (!this.checkIndex(index3, index4, count4, count5, intItem)) {
                        set.remove(new InterpolationCollector(index3, index4));
                        ExtraListEntry extraListEntry2 = this.resolveExtraListEntry(index3, index4, true);
                        if (extraListEntry2 != null) {
                           extraListEntry2.applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter);
                        }
                     }
                  }
               }
            }
         }

         this.count = intItem;
      }
   }

   public static int computeCount(int count2) {
      return count2 * 16 - 16;
   }

   private Set resolveSet(EntityPlayerAdapter entityPlayerAdapter) {
      Set set = (Set)this.map.get(entityPlayerAdapter);
      if (set != null) {
         return set;
      } else {
         int count2 = Math.min(this.count, 8);
         int count3 = this.count * 2 + 1;
         int count4 = count2 * 2 + 1;
         int count5 = Math.max(count3 * count3 - count4 * count4, 16);
         HashSet set2 = new HashSet(count5);
         this.map.put(entityPlayerAdapter, set2);
         return set2;
      }
   }

   private boolean checkIndex(int count2, int count3, int count4, int count5, int count6) {
      int count7 = count2 - count4;
      int count8 = count3 - count5;
      return count7 >= -count6 && count7 <= count6 && count8 >= -count6 && count8 <= count6;
   }

   public void applyEntityPlayerAdapter3(EntityPlayerAdapter entityPlayerAdapter) {
      int count2 = (int)entityPlayerAdapter.posX >> 4;
      int count3 = (int)entityPlayerAdapter.posZ >> 4;
      entityPlayerAdapter.value40 = entityPlayerAdapter.posX;
      entityPlayerAdapter.value39 = entityPlayerAdapter.posZ;
      int count4 = Math.min(this.count, 8);
      int count5 = count2 - count4;
      int count6 = count2 + count4;
      int count7 = count3 - count4;
      int count8 = count3 + count4;
      Set set = this.resolveSet(entityPlayerAdapter);

      for (int index = count2 - this.count; index <= count2 + this.count; index++) {
         for (int index2 = count3 - this.count; index2 <= count3 + this.count; index2++) {
            if (index >= count5 && index <= count6 && index2 >= count7 && index2 <= count8) {
               this.resolveExtraListEntry(index, index2, true).applyEntityPlayerAdapter(entityPlayerAdapter);
            } else {
               set.add(new InterpolationCollector(index, index2));
            }
         }
      }

      this.list.add(entityPlayerAdapter);
      this.applyEntityPlayerAdapter2(entityPlayerAdapter);
   }

   public void applyEntityPlayerAdapterByEntityPlayerAdap(EntityPlayerAdapter entityPlayerAdapter) {
      this.map.remove(entityPlayerAdapter);
      int count2 = (int)entityPlayerAdapter.value40 >> 4;
      int count3 = (int)entityPlayerAdapter.value39 >> 4;

      for (int index = count2 - this.count; index <= count2 + this.count; index++) {
         for (int index2 = count3 - this.count; index2 <= count3 + this.count; index2++) {
            ExtraListEntry extraListEntry = this.resolveExtraListEntry(index, index2, false);
            if (extraListEntry != null) {
               extraListEntry.applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter);
            }
         }
      }

      this.list.remove(entityPlayerAdapter);
   }

   public static WorldServer resolveWorldServer(ManagedLoggerEntry managedLoggerEntry) {
      return managedLoggerEntry.worldServer;
   }

   public static List resolveList(ManagedLoggerEntry managedLoggerEntry) {
      return managedLoggerEntry.list2;
   }

   public void applyEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter) {
      int count2 = (int)entityPlayerAdapter.posX >> 4;
      int count3 = (int)entityPlayerAdapter.posZ >> 4;
      double offset = entityPlayerAdapter.value40 - entityPlayerAdapter.posX;
      double offset2 = entityPlayerAdapter.value39 - entityPlayerAdapter.posZ;
      if (offset * offset + offset2 * offset2 >= 64.0) {
         int count4 = (int)entityPlayerAdapter.value40 >> 4;
         int count5 = (int)entityPlayerAdapter.value39 >> 4;
         int count6 = this.count;
         int count7 = count2 - count4;
         int count8 = count3 - count5;
         if (count7 != 0 || count8 != 0) {
            Set set = this.resolveSet(entityPlayerAdapter);

            for (int index = count2 - count6; index <= count2 + count6; index++) {
               for (int index2 = count3 - count6; index2 <= count3 + count6; index2++) {
                  if (!this.checkIndex(index, index2, count4, count5, count6)) {
                     if (BuildManager.isActive73()) {
                        set.add(new InterpolationCollector(index, index2));
                     } else {
                        this.resolveExtraListEntry(index, index2, true).applyEntityPlayerAdapter(entityPlayerAdapter);
                     }
                  }

                  if (!this.checkIndex(index - count7, index2 - count8, count2, count3, count6)) {
                     set.remove(new InterpolationCollector(index - count7, index2 - count8));
                     ExtraListEntry extraListEntry = this.resolveExtraListEntry(index - count7, index2 - count8, false);
                     if (extraListEntry != null) {
                        extraListEntry.applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter);
                     }
                  }
               }
            }

            this.applyEntityPlayerAdapter2(entityPlayerAdapter);
            entityPlayerAdapter.value40 = entityPlayerAdapter.posX;
            entityPlayerAdapter.value39 = entityPlayerAdapter.posZ;
         }
      }
   }

   public static Logger getLogger() {
      return logger;
   }

   public void updateState() {
      Iterator iterator = this.map.entrySet().iterator();

      while (iterator.hasNext()) {
         Entry entry = (Entry)iterator.next();
         Set value = (Set)entry.getValue();
         if (!value.isEmpty()) {
            EntityPlayerAdapter key = (EntityPlayerAdapter)entry.getKey();
            if (key.worldObj != this.worldServer) {
               iterator.remove();
            } else {
               int count2 = this.count / 3 + 1;
               if (!BuildManager.isActive73()) {
                  count2 = this.count * 2 + 1;
               }

               for (InterpolationCollector interpolationCollector : (Iterable<InterpolationCollector>)(Object)(this.resolvePriorityQueue(value, key, count2))) {
                  this.resolveExtraListEntry(interpolationCollector.count3, interpolationCollector.count2, true).applyEntityPlayerAdapter(key);
                  value.remove(interpolationCollector);
               }
            }
         }
      }

      long timestamp2 = this.worldServer.getTimestamp2();
      if (timestamp2 - this.timestamp > 8000L) {
         this.timestamp = timestamp2;

         for (int index = 0; index < this.list3.size(); index++) {
            ExtraListEntry extraListEntry = (ExtraListEntry)this.list3.get(index);
            extraListEntry.updateState();
            extraListEntry.updateState2();
         }
      } else {
         for (int index2 = 0; index2 < this.list2.size(); index2++) {
            ((ExtraListEntry)this.list2.get(index2)).updateState();
         }
      }

      this.list2.clear();
      if (this.list.isEmpty() && !this.worldServer.secondaryFloatArrayEntry.isActiveAsFlag()) {
         this.worldServer.listenerAdapter21.updateState2();
      }
   }

   public void applyBlockPos(BlockPos blockPos) {
      int count2 = blockPos.getX() >> 4;
      int count3 = blockPos.getZ() >> 4;
      ExtraListEntry extraListEntry = this.resolveExtraListEntry(count2, count3, false);
      if (extraListEntry != null) {
         extraListEntry.applyIndex(blockPos.getX() & 15, blockPos.getY(), blockPos.getZ() & 15);
      }
   }

   public static InternalFloatEntry resolveInternalFloatEntry(ManagedLoggerEntry managedLoggerEntry) {
      return managedLoggerEntry.internalFloatEntry;
   }

   public boolean matchesEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter, int count2, int count3) {
      ExtraListEntry extraListEntry = this.resolveExtraListEntry(count2, count3, false);
      return extraListEntry != null && ExtraListEntry.resolveList(extraListEntry).contains(entityPlayerAdapter) && !entityPlayerAdapter.list.contains(ExtraListEntry.resolveInterpolationCollector(extraListEntry));
   }

   public void applyEntityPlayerAdapter2(EntityPlayerAdapter entityPlayerAdapter) {
      ArrayList list4 = Lists.newArrayList(entityPlayerAdapter.list);
      int count2 = 0;
      int count3 = this.count;
      int count4 = (int)entityPlayerAdapter.posX >> 4;
      int count5 = (int)entityPlayerAdapter.posZ >> 4;
      int count6 = 0;
      int count7 = 0;
      InterpolationCollector interpolationCollector = ExtraListEntry.resolveInterpolationCollector(this.resolveExtraListEntry(count4, count5, true));
      entityPlayerAdapter.list.clear();
      if (list4.contains(interpolationCollector)) {
         entityPlayerAdapter.list.add(interpolationCollector);
      }

      for (int index = 1; index <= count3 * 2; index++) {
         for (int index2 = 0; index2 < 2; index2++) {
            int[] countArray2 = this.countArray[count2++ % 4];

            for (int index3 = 0; index3 < index; index3++) {
               count6 += countArray2[0];
               count7 += countArray2[1];
               InterpolationCollector interpolationCollector2 = ExtraListEntry.resolveInterpolationCollector(this.resolveExtraListEntry(count4 + count6, count5 + count7, true));
               if (list4.contains(interpolationCollector2)) {
                  entityPlayerAdapter.list.add(interpolationCollector2);
               }
            }
         }
      }

      int count8 = count2 % 4;

      for (int index4 = 0; index4 < count3 * 2; index4++) {
         count6 += this.countArray[count8][0];
         count7 += this.countArray[count8][1];
         InterpolationCollector interpolationCollector3 = ExtraListEntry.resolveInterpolationCollector(this.resolveExtraListEntry(count4 + count6, count5 + count7, true));
         if (list4.contains(interpolationCollector3)) {
            entityPlayerAdapter.list.add(interpolationCollector3);
         }
      }
   }

   public WorldServer getWorldServer() {
      return this.worldServer;
   }
}
