package net.minecraft.item;

import client.util.AuxiliaryClassEntry;
import client.util.ClassEntry2Entry;
import client.util.ClientManagerAdapter2;
import client.util.DistanceAggregator;
import client.util.HitBoxValidator;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public abstract class TertiaryItemEntryAdapter extends GoldenIngot {
   public static final List list = Lists.newArrayList(
      new ClientManagerAdapter2[]{
         new ClientManagerAdapter2(Items.diamond, 0, 1, 3, 5),
         new ClientManagerAdapter2(Items.ironIngot, 0, 1, 5, 5),
         new ClientManagerAdapter2(Items.goldIngot, 0, 1, 3, 15),
         new ClientManagerAdapter2(Items.goldenSword, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.goldenChestplate, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.flintAndSteel, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.netherWart, 0, 3, 7, 5),
         new ClientManagerAdapter2(Items.saddle, 0, 1, 1, 10),
         new ClientManagerAdapter2(Items.goldenHorseArmor, 0, 1, 1, 8),
         new ClientManagerAdapter2(Items.ironHorseArmor, 0, 1, 1, 5),
         new ClientManagerAdapter2(Items.diamondHorseArmor, 0, 1, 1, 3),
         new ClientManagerAdapter2(Item.processAction2(Blocks.block113), 0, 2, 4, 2)
      }
   );

   private GoldenIngot resolveGoldenIngot(ClassEntry2Entry classEntry2Entry, List list2, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4, boolean flag) {
      if (Math.abs(count - classEntry2Entry.getDistanceAggregator().count3) <= 112 && Math.abs(count3 - classEntry2Entry.getDistanceAggregator().count4) <= 112) {
         List list3 = classEntry2Entry.list3;
         if (flag) {
            list3 = classEntry2Entry.list4;
         }

         TertiaryItemEntryAdapter tertiaryItemEntryAdapter = this.resolveTertiaryItemEntryAdapter(classEntry2Entry, list3, list2, random, count, count2, count3, enumFacing, count4 + 1);
         if (tertiaryItemEntryAdapter != null) {
            list2.add(tertiaryItemEntryAdapter);
            classEntry2Entry.list2.add(tertiaryItemEntryAdapter);
         }

         return tertiaryItemEntryAdapter;
      } else {
         return HitBoxValidator.resolveHitBoxValidator(list2, random, count, count2, count3, enumFacing, count4);
      }
   }

   private TertiaryItemEntryAdapter resolveTertiaryItemEntryAdapter(
      ClassEntry2Entry classEntry2Entry, List list2, List list3, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4
   ) {
      int count5 = this.computeCount(list2);
      boolean flag = count5 > 0 && count4 <= 30;
      int count6 = 0;

      while (count6 < 5 && flag) {
         count6++;
         int count7 = random.nextInt(count5);

         for (AuxiliaryClassEntry auxiliaryClassEntry : (Iterable<AuxiliaryClassEntry>)(Iterable)list2) {
            count7 -= auxiliaryClassEntry.count;
            if (count7 < 0) {
               if (!auxiliaryClassEntry.checkIndex(count4) || auxiliaryClassEntry == classEntry2Entry.auxiliaryClassEntry && !auxiliaryClassEntry.enabled) {
                  break;
               }

               TertiaryItemEntryAdapter tertiaryItemEntryAdapter = InternalItemArrayEntry.resolveTertiaryItemEntryAdapter(auxiliaryClassEntry, list3, random, count, count2, count3, enumFacing, count4);
               if (tertiaryItemEntryAdapter != null) {
                  auxiliaryClassEntry.count2++;
                  classEntry2Entry.auxiliaryClassEntry = auxiliaryClassEntry;
                  if (!auxiliaryClassEntry.isActive()) {
                     list2.remove(auxiliaryClassEntry);
                  }

                  return tertiaryItemEntryAdapter;
               }
            }
         }
      }

      return HitBoxValidator.resolveHitBoxValidator(list3, random, count, count2, count3, enumFacing, count4);
   }

   public TertiaryItemEntryAdapter(int count) {
      super(count);
   }

   private int computeCount(List list2) {
      boolean flag = false;
      int count = 0;

      for (AuxiliaryClassEntry auxiliaryClassEntry : (Iterable<AuxiliaryClassEntry>)(Iterable)list2) {
         if (auxiliaryClassEntry.count3 > 0 && auxiliaryClassEntry.count2 < auxiliaryClassEntry.count3) {
            flag = true;
         }

         count += auxiliaryClassEntry.count;
      }

      return flag ? count : -1;
   }

   public TertiaryItemEntryAdapter() {
   }

   public GoldenIngot resolveGoldenIngot2(ClassEntry2Entry classEntry2Entry, List list2, Random random, int count, int count2, boolean flag) {
      if (this.enumFacing != null) {
         switch (TertiaryItemEntryAdapterEnumFacingSwitchMap.countArray[this.enumFacing.ordinal()]) {
            case 1:
               return this.resolveGoldenIngot(
                  classEntry2Entry,
                  list2,
                  random,
                  this.distanceAggregator.count5 + 1,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 + count2,
                  EnumFacing.EAST,
                  this.getCount(),
                  flag
               );
            case 2:
               return this.resolveGoldenIngot(
                  classEntry2Entry,
                  list2,
                  random,
                  this.distanceAggregator.count5 + 1,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 + count2,
                  EnumFacing.EAST,
                  this.getCount(),
                  flag
               );
            case 3:
               return this.resolveGoldenIngot(
                  classEntry2Entry,
                  list2,
                  random,
                  this.distanceAggregator.count3 + count2,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count + 1,
                  EnumFacing.SOUTH,
                  this.getCount(),
                  flag
               );
            case 4:
               return this.resolveGoldenIngot(
                  classEntry2Entry,
                  list2,
                  random,
                  this.distanceAggregator.count3 + count2,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count + 1,
                  EnumFacing.SOUTH,
                  this.getCount(),
                  flag
               );
         }
      }

      return null;
   }

   public GoldenIngot resolveGoldenIngot(ClassEntry2Entry classEntry2Entry, List list2, Random random, int count, int count2, boolean flag) {
      if (this.enumFacing != null) {
         switch (TertiaryItemEntryAdapterEnumFacingSwitchMap.countArray[this.enumFacing.ordinal()]) {
            case 1:
               return this.resolveGoldenIngot(
                  classEntry2Entry,
                  list2,
                  random,
                  this.distanceAggregator.count3 - 1,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 + count2,
                  EnumFacing.WEST,
                  this.getCount(),
                  flag
               );
            case 2:
               return this.resolveGoldenIngot(
                  classEntry2Entry,
                  list2,
                  random,
                  this.distanceAggregator.count3 - 1,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 + count2,
                  EnumFacing.WEST,
                  this.getCount(),
                  flag
               );
            case 3:
               return this.resolveGoldenIngot(
                  classEntry2Entry,
                  list2,
                  random,
                  this.distanceAggregator.count3 + count2,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 - 1,
                  EnumFacing.NORTH,
                  this.getCount(),
                  flag
               );
            case 4:
               return this.resolveGoldenIngot(
                  classEntry2Entry,
                  list2,
                  random,
                  this.distanceAggregator.count3 + count2,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 - 1,
                  EnumFacing.NORTH,
                  this.getCount(),
                  flag
               );
         }
      }

      return null;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
   }

   public GoldenIngot resolveGoldenIngotByClassEntry2Entry(ClassEntry2Entry classEntry2Entry, List list2, Random random, int count, int count2, boolean flag) {
      if (this.enumFacing != null) {
         switch (TertiaryItemEntryAdapterEnumFacingSwitchMap.countArray[this.enumFacing.ordinal()]) {
            case 1:
               return this.resolveGoldenIngot(
                  classEntry2Entry,
                  list2,
                  random,
                  this.distanceAggregator.count3 + count,
                  this.distanceAggregator.count6 + count2,
                  this.distanceAggregator.count4 - 1,
                  this.enumFacing,
                  this.getCount(),
                  flag
               );
            case 2:
               return this.resolveGoldenIngot(
                  classEntry2Entry,
                  list2,
                  random,
                  this.distanceAggregator.count3 + count,
                  this.distanceAggregator.count6 + count2,
                  this.distanceAggregator.count + 1,
                  this.enumFacing,
                  this.getCount(),
                  flag
               );
            case 3:
               return this.resolveGoldenIngot(
                  classEntry2Entry,
                  list2,
                  random,
                  this.distanceAggregator.count3 - 1,
                  this.distanceAggregator.count6 + count2,
                  this.distanceAggregator.count4 + count,
                  this.enumFacing,
                  this.getCount(),
                  flag
               );
            case 4:
               return this.resolveGoldenIngot(
                  classEntry2Entry,
                  list2,
                  random,
                  this.distanceAggregator.count5 + 1,
                  this.distanceAggregator.count6 + count2,
                  this.distanceAggregator.count4 + count,
                  this.enumFacing,
                  this.getCount(),
                  flag
               );
         }
      }

      return null;
   }

   public static boolean matchesDistanceAggregator(DistanceAggregator distanceAggregator) {
      return distanceAggregator != null && distanceAggregator.count6 > 10;
   }
}
