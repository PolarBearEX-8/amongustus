package net.minecraft.item;

import client.util.ClassEntry3ArrayEntry;
import client.util.ClassEntry3Entry;
import client.util.DistanceAggregator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class ExtendedItemEntryAdapter extends GoldenIngot {
   public IRONDOOREnum iRONDOOREnum = IRONDOOREnum.OPENING;

   public static boolean matchesDistanceAggregator(DistanceAggregator distanceAggregator) {
      return distanceAggregator != null && distanceAggregator.count6 > 10;
   }

   public void applyWorld(World world, Random random, DistanceAggregator distanceAggregator, IRONDOOREnum iRONDOOREnum2, int count, int count2, int count3) {
      switch (ExtendedItemEntryAdapterIRONDOOREnumSwitchMap.countArray2[iRONDOOREnum2.ordinal()]) {
         case 1:
         default:
            this.applyWorld(world, distanceAggregator, count, count2, count3, count + 3 - 1, count2 + 3 - 1, count3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            break;
         case 2:
            this.applyWorld(world, Blocks.block42.getDefaultState(), count, count2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), count, count2 + 1, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), count, count2 + 2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), count + 1, count2 + 2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), count + 2, count2 + 2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), count + 2, count2 + 1, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), count + 2, count2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block110.getDefaultState(), count + 1, count2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block110.processAction(8), count + 1, count2 + 1, count3, distanceAggregator);
            break;
         case 3:
            this.applyWorld(world, Blocks.air.getDefaultState(), count + 1, count2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.air.getDefaultState(), count + 1, count2 + 1, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block127.getDefaultState(), count, count2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block127.getDefaultState(), count, count2 + 1, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block127.getDefaultState(), count, count2 + 2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block127.getDefaultState(), count + 1, count2 + 2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block127.getDefaultState(), count + 2, count2 + 2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block127.getDefaultState(), count + 2, count2 + 1, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block127.getDefaultState(), count + 2, count2, count3, distanceAggregator);
            break;
         case 4:
            this.applyWorld(world, Blocks.block42.getDefaultState(), count, count2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), count, count2 + 1, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), count, count2 + 2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), count + 1, count2 + 2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), count + 2, count2 + 2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), count + 2, count2 + 1, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block42.getDefaultState(), count + 2, count2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.ironDoor.getDefaultState(), count + 1, count2, count3, distanceAggregator);
            this.applyWorld(world, Blocks.ironDoor.processAction(8), count + 1, count2 + 1, count3, distanceAggregator);
            this.applyWorld(world, Blocks.block75.processAction(this.computeCount(Blocks.block75, 4)), count + 2, count2 + 1, count3 + 1, distanceAggregator);
            this.applyWorld(world, Blocks.block75.processAction(this.computeCount(Blocks.block75, 3)), count + 2, count2 + 1, count3 - 1, distanceAggregator);
      }
   }

   public ExtendedItemEntryAdapter() {
   }

   public ExtendedItemEntryAdapter(int count) {
      super(count);
   }

   public GoldenIngot resolveGoldenIngot(ClassEntry3Entry classEntry3Entry, List list, Random random, int count, int count2) {
      if (this.enumFacing != null) {
         switch (ExtendedItemEntryAdapterIRONDOOREnumSwitchMap.countArray[this.enumFacing.ordinal()]) {
            case 1:
               return ClassEntry3ArrayEntry.resolveGoldenIngotByClassEntry3Entry(
                  classEntry3Entry,
                  list,
                  random,
                  this.distanceAggregator.count3 - 1,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 + count2,
                  EnumFacing.WEST,
                  this.getCount()
               );
            case 2:
               return ClassEntry3ArrayEntry.resolveGoldenIngotByClassEntry3Entry(
                  classEntry3Entry,
                  list,
                  random,
                  this.distanceAggregator.count3 + count2,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 - 1,
                  EnumFacing.NORTH,
                  this.getCount()
               );
            case 3:
               return ClassEntry3ArrayEntry.resolveGoldenIngotByClassEntry3Entry(
                  classEntry3Entry,
                  list,
                  random,
                  this.distanceAggregator.count3 + count2,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 - 1,
                  EnumFacing.NORTH,
                  this.getCount()
               );
            case 4:
               return ClassEntry3ArrayEntry.resolveGoldenIngotByClassEntry3Entry(
                  classEntry3Entry,
                  list,
                  random,
                  this.distanceAggregator.count3 - 1,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 + count2,
                  EnumFacing.WEST,
                  this.getCount()
               );
         }
      }

      return null;
   }

   public IRONDOOREnum nextInt(Random random) {
      switch (random.nextInt(5)) {
         case 0:
         case 1:
         default:
            return IRONDOOREnum.OPENING;
         case 2:
            return IRONDOOREnum.WOOD_DOOR;
         case 3:
            return IRONDOOREnum.GRATES;
         case 4:
            return IRONDOOREnum.IRON_DOOR;
      }
   }

   public GoldenIngot resolveGoldenIngotByClassEntry3Entry(ClassEntry3Entry classEntry3Entry, List list, Random random, int count, int count2) {
      if (this.enumFacing != null) {
         switch (ExtendedItemEntryAdapterIRONDOOREnumSwitchMap.countArray[this.enumFacing.ordinal()]) {
            case 1:
               return ClassEntry3ArrayEntry.resolveGoldenIngotByClassEntry3Entry(
                  classEntry3Entry,
                  list,
                  random,
                  this.distanceAggregator.count3 + count,
                  this.distanceAggregator.count6 + count2,
                  this.distanceAggregator.count + 1,
                  this.enumFacing,
                  this.getCount()
               );
            case 2:
               return ClassEntry3ArrayEntry.resolveGoldenIngotByClassEntry3Entry(
                  classEntry3Entry,
                  list,
                  random,
                  this.distanceAggregator.count3 - 1,
                  this.distanceAggregator.count6 + count2,
                  this.distanceAggregator.count4 + count,
                  this.enumFacing,
                  this.getCount()
               );
            case 3:
               return ClassEntry3ArrayEntry.resolveGoldenIngotByClassEntry3Entry(
                  classEntry3Entry,
                  list,
                  random,
                  this.distanceAggregator.count5 + 1,
                  this.distanceAggregator.count6 + count2,
                  this.distanceAggregator.count4 + count,
                  this.enumFacing,
                  this.getCount()
               );
            case 4:
               return ClassEntry3ArrayEntry.resolveGoldenIngotByClassEntry3Entry(
                  classEntry3Entry,
                  list,
                  random,
                  this.distanceAggregator.count3 + count,
                  this.distanceAggregator.count6 + count2,
                  this.distanceAggregator.count4 - 1,
                  this.enumFacing,
                  this.getCount()
               );
         }
      }

      return null;
   }

   public GoldenIngot resolveGoldenIngot2(ClassEntry3Entry classEntry3Entry, List list, Random random, int count, int count2) {
      if (this.enumFacing != null) {
         switch (ExtendedItemEntryAdapterIRONDOOREnumSwitchMap.countArray[this.enumFacing.ordinal()]) {
            case 1:
               return ClassEntry3ArrayEntry.resolveGoldenIngotByClassEntry3Entry(
                  classEntry3Entry,
                  list,
                  random,
                  this.distanceAggregator.count5 + 1,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 + count2,
                  EnumFacing.EAST,
                  this.getCount()
               );
            case 2:
               return ClassEntry3ArrayEntry.resolveGoldenIngotByClassEntry3Entry(
                  classEntry3Entry,
                  list,
                  random,
                  this.distanceAggregator.count3 + count2,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count + 1,
                  EnumFacing.SOUTH,
                  this.getCount()
               );
            case 3:
               return ClassEntry3ArrayEntry.resolveGoldenIngotByClassEntry3Entry(
                  classEntry3Entry,
                  list,
                  random,
                  this.distanceAggregator.count3 + count2,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count + 1,
                  EnumFacing.SOUTH,
                  this.getCount()
               );
            case 4:
               return ClassEntry3ArrayEntry.resolveGoldenIngotByClassEntry3Entry(
                  classEntry3Entry,
                  list,
                  random,
                  this.distanceAggregator.count5 + 1,
                  this.distanceAggregator.count6 + count,
                  this.distanceAggregator.count4 + count2,
                  EnumFacing.EAST,
                  this.getCount()
               );
         }
      }

      return null;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("EntryDoor", this.iRONDOOREnum.name());
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.iRONDOOREnum = IRONDOOREnum.valueOf(tagCompound.resolveText("EntryDoor"));
   }
}
