package client.util;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.item.GoldenAmulet;
import net.minecraft.item.GoldenArmor;
import net.minecraft.item.GoldenCrown;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.GoldenRing;
import net.minecraft.item.GoldenRod;
import net.minecraft.item.GoldenTalisman;
import net.minecraft.item.GoldenTool;
import net.minecraft.item.GoldenWand;
import net.minecraft.item.GoldenWeapon;
import net.minecraft.item.InternalItemEntryAdapter;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;

public class AngleService {
   public static GoldenIngot resolveGoldenIngot3(
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4
   ) {
      return resolveGoldenIngot(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing, count4);
   }

   public static GoldenIngot resolveGoldenIngotByInvalidBiomeIdMa(
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4
   ) {
      return resolveGoldenIngot2(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing, count4);
   }

   private static GoldenIngot resolveGoldenIngot2(
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4
   ) {
      if (count4 > 3 + invalidBiomeIdManagerEntry.count4) {
         return null;
      } else if (Math.abs(count - invalidBiomeIdManagerEntry.getDistanceAggregator().count3) <= 112 && Math.abs(count3 - invalidBiomeIdManagerEntry.getDistanceAggregator().count4) <= 112) {
         DistanceAggregator distanceAggregator = HitBoxTransformer.resolveDistanceAggregator(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing);
         if (distanceAggregator != null && distanceAggregator.count6 > 10) {
            HitBoxTransformer hitBoxTransformer = new HitBoxTransformer(invalidBiomeIdManagerEntry, count4, random, distanceAggregator, enumFacing);
            int count5 = (hitBoxTransformer.distanceAggregator.count3 + hitBoxTransformer.distanceAggregator.count5) / 2;
            int count6 = (hitBoxTransformer.distanceAggregator.count4 + hitBoxTransformer.distanceAggregator.count) / 2;
            int count7 = hitBoxTransformer.distanceAggregator.count5 - hitBoxTransformer.distanceAggregator.count3;
            int count8 = hitBoxTransformer.distanceAggregator.count - hitBoxTransformer.distanceAggregator.count4;
            int count9 = count7 > count8 ? count7 : count8;
            if (invalidBiomeIdManagerEntry.getInvalidBiomeIdManager().contains(count5, count6, count9 / 2 + 4, ActiveListEntry.list)) {
               list.add(hitBoxTransformer);
               invalidBiomeIdManagerEntry.list.add(hitBoxTransformer);
               return hitBoxTransformer;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private static int computeCount(List list) {
      boolean flag = false;
      int count = 0;

      for (ClassControllerManager classControllerManager : (Iterable<ClassControllerManager>) (Iterable) (list)) {
         if (classControllerManager.count3 > 0 && classControllerManager.count2 < classControllerManager.count3) {
            flag = true;
         }

         count += classControllerManager.count;
      }

      return flag ? count : -1;
   }

   public static void updateState() {
      EnhancedLoggerEntry.applyClassByClass(GoldenWeapon.class, "ViBH");
      EnhancedLoggerEntry.applyClassByClass(GoldenWand.class, "ViDF");
      EnhancedLoggerEntry.applyClassByClass(GoldenTool.class, "ViF");
      EnhancedLoggerEntry.applyClassByClass(GoldenArmor.class, "ViL");
      EnhancedLoggerEntry.applyClassByClass(GoldenRing.class, "ViPH");
      EnhancedLoggerEntry.applyClassByClass(GoldenCrown.class, "ViSH");
      EnhancedLoggerEntry.applyClassByClass(GoldenAmulet.class, "ViSmH");
      EnhancedLoggerEntry.applyClassByClass(GoldenRod.class, "ViST");
      EnhancedLoggerEntry.applyClassByClass(NativeListEntry.class, "ViS");
      EnhancedLoggerEntry.applyClassByClass(InvalidBiomeIdManagerEntry.class, "ViStart");
      EnhancedLoggerEntry.applyClassByClass(HitBoxTransformer.class, "ViSR");
      EnhancedLoggerEntry.applyClassByClass(FovBridge.class, "ViTRH");
      EnhancedLoggerEntry.applyClassByClass(GoldenTalisman.class, "ViW");
   }

   private static GoldenIngot resolveGoldenIngot(
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4
   ) {
      if (count4 > 50) {
         return null;
      } else if (Math.abs(count - invalidBiomeIdManagerEntry.getDistanceAggregator().count3) <= 112 && Math.abs(count3 - invalidBiomeIdManagerEntry.getDistanceAggregator().count4) <= 112) {
         InternalItemEntryAdapter internalItemEntryAdapter = resolveInternalItemEntryAdapter(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing, count4 + 1);
         if (internalItemEntryAdapter != null) {
            int count5 = (internalItemEntryAdapter.distanceAggregator.count3 + internalItemEntryAdapter.distanceAggregator.count5) / 2;
            int count6 = (internalItemEntryAdapter.distanceAggregator.count4 + internalItemEntryAdapter.distanceAggregator.count) / 2;
            int count7 = internalItemEntryAdapter.distanceAggregator.count5 - internalItemEntryAdapter.distanceAggregator.count3;
            int count8 = internalItemEntryAdapter.distanceAggregator.count - internalItemEntryAdapter.distanceAggregator.count4;
            int count9 = count7 > count8 ? count7 : count8;
            if (invalidBiomeIdManagerEntry.getInvalidBiomeIdManager().contains(count5, count6, count9 / 2 + 4, ActiveListEntry.list)) {
               list.add(internalItemEntryAdapter);
               invalidBiomeIdManagerEntry.list2.add(internalItemEntryAdapter);
               return internalItemEntryAdapter;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private static InternalItemEntryAdapter resolveInternalItemEntryAdapter(
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4
   ) {
      int count5 = computeCount(invalidBiomeIdManagerEntry.list3);
      if (count5 <= 0) {
         return null;
      } else {
         int count6 = 0;

         while (count6 < 5) {
            count6++;
            int count7 = random.nextInt(count5);

            for (ClassControllerManager classControllerManager : (Iterable<ClassControllerManager>) (Iterable) (invalidBiomeIdManagerEntry.list3)) {
               count7 -= classControllerManager.count;
               if (count7 < 0) {
                  if (!classControllerManager.checkIndex(count4) || classControllerManager == invalidBiomeIdManagerEntry.classControllerManager && invalidBiomeIdManagerEntry.list3.size() > 1) {
                     break;
                  }

                  InternalItemEntryAdapter internalItemEntryAdapter = resolveInternalItemEntryAdapter(invalidBiomeIdManagerEntry, classControllerManager, list, random, count, count2, count3, enumFacing, count4);
                  if (internalItemEntryAdapter != null) {
                     classControllerManager.count2++;
                     invalidBiomeIdManagerEntry.classControllerManager = classControllerManager;
                     if (!classControllerManager.isActive()) {
                        invalidBiomeIdManagerEntry.list3.remove(classControllerManager);
                     }

                     return internalItemEntryAdapter;
                  }
               }
            }
         }

         DistanceAggregator distanceAggregator = GoldenArmor.resolveDistanceAggregator(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing);
         return distanceAggregator != null ? new GoldenArmor(invalidBiomeIdManagerEntry, count4, random, distanceAggregator, enumFacing) : null;
      }
   }

   public static List resolveList(Random random, int count) {
      ArrayList list = Lists.newArrayList();
      list.add(new ClassControllerManager(GoldenCrown.class, 4, MathHelper.nextInt(random, 2 + count, 4 + count * 2)));
      list.add(new ClassControllerManager(GoldenRod.class, 20, MathHelper.nextInt(random, 0 + count, 1 + count)));
      list.add(new ClassControllerManager(GoldenWeapon.class, 20, MathHelper.nextInt(random, 0 + count, 2 + count)));
      list.add(new ClassControllerManager(GoldenAmulet.class, 3, MathHelper.nextInt(random, 2 + count, 5 + count * 3)));
      list.add(new ClassControllerManager(GoldenRing.class, 15, MathHelper.nextInt(random, 0 + count, 2 + count)));
      list.add(new ClassControllerManager(GoldenWand.class, 3, MathHelper.nextInt(random, 1 + count, 4 + count)));
      list.add(new ClassControllerManager(GoldenTool.class, 3, MathHelper.nextInt(random, 2 + count, 4 + count * 2)));
      list.add(new ClassControllerManager(NativeListEntry.class, 15, MathHelper.nextInt(random, 0, 1 + count)));
      list.add(new ClassControllerManager(FovBridge.class, 8, MathHelper.nextInt(random, 0 + count, 3 + count * 2)));
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         if (((ClassControllerManager)iterator.next()).count3 == 0) {
            iterator.remove();
         }
      }

      return list;
   }

   private static InternalItemEntryAdapter resolveInternalItemEntryAdapter(
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, ClassControllerManager classControllerManager, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4
   ) {
      Class memberRef = classControllerManager.memberRef;
      Object entry = null;
      if (memberRef == GoldenCrown.class) {
         entry = GoldenCrown.resolveGoldenCrown(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == GoldenRod.class) {
         entry = GoldenRod.resolveGoldenRod(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == GoldenWeapon.class) {
         entry = GoldenWeapon.resolveGoldenWeapon(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == GoldenAmulet.class) {
         entry = GoldenAmulet.resolveGoldenAmulet(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == GoldenRing.class) {
         entry = GoldenRing.resolveGoldenRing(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == GoldenWand.class) {
         entry = GoldenWand.resolveGoldenWand(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == GoldenTool.class) {
         entry = GoldenTool.resolveGoldenTool(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == NativeListEntry.class) {
         entry = NativeListEntry.resolveNativeListEntry(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing, count4);
      } else if (memberRef == FovBridge.class) {
         entry = FovBridge.resolveFovBridge(invalidBiomeIdManagerEntry, list, random, count, count2, count3, enumFacing, count4);
      }

      return (InternalItemEntryAdapter)entry;
   }
}
