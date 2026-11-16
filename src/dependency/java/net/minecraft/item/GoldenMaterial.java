package net.minecraft.item;

import client.setting.IndirectOptionType;
import client.setting.MovementFactorProperty;
import client.util.ClassEntry3ArrayEntry;
import client.util.ClassEntry3Entry;
import client.util.DistanceAggregator;
import client.util.HitBoxSerializer;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenMaterial extends ExtendedItemEntryAdapter {
   public boolean enabled;

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      if (this.enabled) {
         ClassEntry3ArrayEntry.resolveClass(HitBoxSerializer.class);
      }

      this.resolveGoldenIngotByClassEntry3Entry((ClassEntry3Entry)goldenIngot, list, random, 1, 1);
   }

   public GoldenMaterial(int count, Random random, int count2, int count3) {
      super(count);
      this.enabled = true;
      this.enumFacing = MovementFactorProperty.HORIZONTAL.resolveEnumFacing(random);
      this.iRONDOOREnum = IRONDOOREnum.OPENING;
      switch (ExtendedItemEntryAdapterIRONDOOREnumSwitchMap.countArray[this.enumFacing.ordinal()]) {
         case 1:
         case 4:
            this.distanceAggregator = new DistanceAggregator(count2, 64, count3, count2 + 5 - 1, 74, count3 + 5 - 1);
            break;
         default:
            this.distanceAggregator = new DistanceAggregator(count2, 64, count3, count2 + 5 - 1, 74, count3 + 5 - 1);
      }
   }

   public GoldenMaterial(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enabled = false;
      this.enumFacing = enumFacing;
      this.iRONDOOREnum = this.nextInt(random);
      this.distanceAggregator = distanceAggregator;
   }

   public static GoldenMaterial resolveGoldenMaterial(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -1, -7, 0, 5, 11, 5, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new GoldenMaterial(count4, random, distanceAggregator, enumFacing) : null;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Source", this.enabled);
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         this.applyWorld(world, distanceAggregator, 0, 0, 0, 4, 10, 4, true, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, random, distanceAggregator, this.iRONDOOREnum, 1, 7, 0);
         this.applyWorld(world, random, distanceAggregator, IRONDOOREnum.OPENING, 1, 1, 4);
         this.applyWorld(world, Blocks.block42.getDefaultState(), 2, 6, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block42.getDefaultState(), 1, 5, 1, distanceAggregator);
         this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.STONE.getCount()), 1, 6, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block42.getDefaultState(), 1, 5, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block42.getDefaultState(), 1, 4, 3, distanceAggregator);
         this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.STONE.getCount()), 1, 5, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block42.getDefaultState(), 2, 4, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block42.getDefaultState(), 3, 3, 3, distanceAggregator);
         this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.STONE.getCount()), 3, 4, 3, distanceAggregator);
         this.applyWorld(world, Blocks.block42.getDefaultState(), 3, 3, 2, distanceAggregator);
         this.applyWorld(world, Blocks.block42.getDefaultState(), 3, 2, 1, distanceAggregator);
         this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.STONE.getCount()), 3, 3, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block42.getDefaultState(), 2, 2, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block42.getDefaultState(), 1, 1, 1, distanceAggregator);
         this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.STONE.getCount()), 1, 2, 1, distanceAggregator);
         this.applyWorld(world, Blocks.block42.getDefaultState(), 1, 1, 2, distanceAggregator);
         this.applyWorld(world, Blocks.extraBlockAdapter6.processAction(IndirectOptionType.STONE.getCount()), 1, 1, 3, distanceAggregator);
         return true;
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabled = tagCompound.matchesText("Source");
   }

   public GoldenMaterial() {
   }
}
