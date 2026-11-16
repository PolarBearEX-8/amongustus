package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.item.ExtendedItemEntryAdapter;
import net.minecraft.item.GoldenBlade;
import net.minecraft.item.GoldenDagger;
import net.minecraft.item.GoldenHammer;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.GoldenMaterial;
import net.minecraft.item.IronAxe;
import net.minecraft.item.IronShovel;
import net.minecraft.util.EnumFacing;

public class ClassEntry3ArrayEntry {
   public static final InternalClassEntry[] internalClassEntryArray = new InternalClassEntry[]{
      new InternalClassEntry(FovAdapter.class, 40, 0),
      new InternalClassEntry(HitBoxEvaluator.class, 5, 5),
      new InternalClassEntry(GoldenHammer.class, 20, 0),
      new InternalClassEntry(HitBoxDeserializer.class, 20, 0),
      new InternalClassEntry(PrimaryListEntry.class, 10, 6),
      new InternalClassEntry(GoldenBlade.class, 5, 5),
      new InternalClassEntry(GoldenMaterial.class, 5, 5),
      new InternalClassEntry(HitBoxSerializer.class, 5, 4),
      new InternalClassEntry(IronAxe.class, 5, 4),
      new AuxiliaryClassEntryAdapter(DefaultListEntry.class, 10, 2),
      new ClassEntryAdapter(GoldenDagger.class, 20, 1)
   };
   public static List list;
   public static Class memberRef;
   public static int count;
   public static final IBlockStateEntryAdapter iBlockStateEntryAdapter = new IBlockStateEntryAdapter(null);

   public static Class resolveClass(Class clazz) {
      memberRef = clazz;
      return clazz;
   }

   public static GoldenIngot resolveGoldenIngotByClassEntry3Entry(
      ClassEntry3Entry classEntry3Entry, List list2, Random random, int count2, int count3, int count4, EnumFacing enumFacing, int count5
   ) {
      return resolveGoldenIngot(classEntry3Entry, list2, random, count2, count3, count4, enumFacing, count5);
   }

   private static boolean isActive() {
      boolean flag = false;
      count = 0;

      for (Object internalClassEntryObj : list) {
         InternalClassEntry internalClassEntry = (InternalClassEntry)internalClassEntryObj;
         if (internalClassEntry.count3 > 0 && internalClassEntry.count2 < internalClassEntry.count3) {
            flag = true;
         }

         count = count + internalClassEntry.count;
      }

      return flag;
   }

   private static GoldenIngot resolveGoldenIngot(ClassEntry3Entry classEntry3Entry, List list2, Random random, int count2, int count3, int count4, EnumFacing enumFacing, int count5) {
      if (count5 > 50) {
         return null;
      } else if (Math.abs(count2 - classEntry3Entry.getDistanceAggregator().count3) <= 112 && Math.abs(count4 - classEntry3Entry.getDistanceAggregator().count4) <= 112) {
         ExtendedItemEntryAdapter extendedItemEntryAdapter = resolveExtendedItemEntryAdapter(classEntry3Entry, list2, random, count2, count3, count4, enumFacing, count5 + 1);
         if (extendedItemEntryAdapter != null) {
            list2.add(extendedItemEntryAdapter);
            classEntry3Entry.list.add(extendedItemEntryAdapter);
         }

         return extendedItemEntryAdapter;
      } else {
         return null;
      }
   }

   public static void updateState() {
      list = Lists.newArrayList();

      for (InternalClassEntry internalClassEntry : internalClassEntryArray) {
         internalClassEntry.count2 = 0;
         list.add(internalClassEntry);
      }

      memberRef = null;
   }

   public static IBlockStateEntryAdapter getIBlockStateEntryAdapter() {
      return iBlockStateEntryAdapter;
   }

   public static void updateState2() {
      EnhancedLoggerEntry.applyClassByClass(IronAxe.class, "SHCC");
      EnhancedLoggerEntry.applyClassByClass(IronShovel.class, "SHFC");
      EnhancedLoggerEntry.applyClassByClass(HitBoxSerializer.class, "SH5C");
      EnhancedLoggerEntry.applyClassByClass(GoldenHammer.class, "SHLT");
      EnhancedLoggerEntry.applyClassByClass(DefaultListEntry.class, "SHLi");
      EnhancedLoggerEntry.applyClassByClass(GoldenDagger.class, "SHPR");
      EnhancedLoggerEntry.applyClassByClass(HitBoxEvaluator.class, "SHPH");
      EnhancedLoggerEntry.applyClassByClass(HitBoxDeserializer.class, "SHRT");
      EnhancedLoggerEntry.applyClassByClass(PrimaryListEntry.class, "SHRC");
      EnhancedLoggerEntry.applyClassByClass(GoldenMaterial.class, "SHSD");
      EnhancedLoggerEntry.applyClassByClass(ClassEntry3Entry.class, "SHStart");
      EnhancedLoggerEntry.applyClassByClass(FovAdapter.class, "SHS");
      EnhancedLoggerEntry.applyClassByClass(GoldenBlade.class, "SHSSD");
   }

   private static ExtendedItemEntryAdapter resolveExtendedItemEntryAdapter(
      Class clazz, List list2, Random random, int count2, int count3, int count4, EnumFacing enumFacing, int count5
   ) {
      Object entry = null;
      if (clazz == FovAdapter.class) {
         entry = FovAdapter.resolveFovAdapter(list2, random, count2, count3, count4, enumFacing, count5);
      } else if (clazz == HitBoxEvaluator.class) {
         entry = HitBoxEvaluator.resolveHitBoxEvaluator(list2, random, count2, count3, count4, enumFacing, count5);
      } else if (clazz == GoldenHammer.class) {
         entry = GoldenHammer.resolveGoldenHammer(list2, random, count2, count3, count4, enumFacing, count5);
      } else if (clazz == HitBoxDeserializer.class) {
         entry = HitBoxDeserializer.resolveGoldenHammer(list2, random, count2, count3, count4, enumFacing, count5);
      } else if (clazz == PrimaryListEntry.class) {
         entry = PrimaryListEntry.resolvePrimaryListEntry(list2, random, count2, count3, count4, enumFacing, count5);
      } else if (clazz == GoldenBlade.class) {
         entry = GoldenBlade.resolveGoldenBlade(list2, random, count2, count3, count4, enumFacing, count5);
      } else if (clazz == GoldenMaterial.class) {
         entry = GoldenMaterial.resolveGoldenMaterial(list2, random, count2, count3, count4, enumFacing, count5);
      } else if (clazz == HitBoxSerializer.class) {
         entry = HitBoxSerializer.resolveHitBoxSerializer(list2, random, count2, count3, count4, enumFacing, count5);
      } else if (clazz == IronAxe.class) {
         entry = IronAxe.resolveIronAxe(list2, random, count2, count3, count4, enumFacing, count5);
      } else if (clazz == DefaultListEntry.class) {
         entry = DefaultListEntry.resolveDefaultListEntry(list2, random, count2, count3, count4, enumFacing, count5);
      } else if (clazz == GoldenDagger.class) {
         entry = GoldenDagger.resolveGoldenDagger(list2, random, count2, count3, count4, enumFacing, count5);
      }

      return (ExtendedItemEntryAdapter)entry;
   }

   private static ExtendedItemEntryAdapter resolveExtendedItemEntryAdapter(
      ClassEntry3Entry classEntry3Entry, List list2, Random random, int count2, int count3, int count4, EnumFacing enumFacing, int count5
   ) {
      if (!isActive()) {
         return null;
      } else {
         if (memberRef != null) {
            ExtendedItemEntryAdapter extendedItemEntryAdapter = resolveExtendedItemEntryAdapter(memberRef, list2, random, count2, count3, count4, enumFacing, count5);
            memberRef = null;
            if (extendedItemEntryAdapter != null) {
               return extendedItemEntryAdapter;
            }
         }

         int count6 = 0;

         while (count6 < 5) {
            count6++;
            int count7 = random.nextInt(count);

            for (Object internalClassEntryObj : list) {
               InternalClassEntry internalClassEntry = (InternalClassEntry)internalClassEntryObj;
               count7 -= internalClassEntry.count;
               if (count7 < 0) {
                  if (!internalClassEntry.checkIndex(count5) || internalClassEntry == classEntry3Entry.internalClassEntry) {
                     break;
                  }

                  ExtendedItemEntryAdapter extendedItemEntryAdapter2 = resolveExtendedItemEntryAdapter(internalClassEntry.memberRef, list2, random, count2, count3, count4, enumFacing, count5);
                  if (extendedItemEntryAdapter2 != null) {
                     internalClassEntry.count2++;
                     classEntry3Entry.internalClassEntry = internalClassEntry;
                     if (!internalClassEntry.isActive()) {
                        list.remove(internalClassEntry);
                     }

                     return extendedItemEntryAdapter2;
                  }
               }
            }
         }

         DistanceAggregator distanceAggregator = IronShovel.resolveDistanceAggregator(list2, random, count2, count3, count4, enumFacing);
         return distanceAggregator != null && distanceAggregator.count6 > 1 ? new IronShovel(count5, random, distanceAggregator, enumFacing) : null;
      }
   }
}
