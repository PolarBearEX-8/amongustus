package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.ExtendedItemEntryAdapter;
import net.minecraft.item.GoldenIngot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class HitBoxSerializer extends ExtendedItemEntryAdapter {
   public boolean enabled;
   public boolean enabled2;
   public boolean enabled3;
   public boolean enabled4;

   public HitBoxSerializer(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.iRONDOOREnum = this.nextInt(random);
      this.distanceAggregator = distanceAggregator;
      this.enabled = random.nextBoolean();
      this.enabled2 = random.nextBoolean();
      this.enabled3 = random.nextBoolean();
      this.enabled4 = random.nextInt(3) > 0;
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         this.applyWorld(world, distanceAggregator, 0, 0, 0, 9, 8, 10, true, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, random, distanceAggregator, this.iRONDOOREnum, 4, 3, 0);
         if (this.enabled) {
            this.applyWorld(world, distanceAggregator, 0, 3, 1, 0, 5, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         if (this.enabled3) {
            this.applyWorld(world, distanceAggregator, 9, 3, 1, 9, 5, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         if (this.enabled2) {
            this.applyWorld(world, distanceAggregator, 0, 5, 7, 0, 7, 9, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         if (this.enabled4) {
            this.applyWorld(world, distanceAggregator, 9, 5, 7, 9, 7, 9, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         this.applyWorld(world, distanceAggregator, 5, 1, 10, 7, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 1, 2, 1, 8, 2, 6, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, distanceAggregator, 4, 1, 5, 4, 4, 9, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, distanceAggregator, 8, 1, 5, 8, 4, 9, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, distanceAggregator, 1, 4, 7, 3, 4, 9, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, distanceAggregator, 1, 3, 5, 3, 3, 6, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, distanceAggregator, 1, 3, 4, 3, 3, 4, Blocks.extraBlockAdapter6.getDefaultState(), Blocks.extraBlockAdapter6.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 1, 4, 6, 3, 4, 6, Blocks.extraBlockAdapter6.getDefaultState(), Blocks.extraBlockAdapter6.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 5, 1, 7, 7, 1, 8, false, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, distanceAggregator, 5, 1, 9, 7, 1, 9, Blocks.extraBlockAdapter6.getDefaultState(), Blocks.extraBlockAdapter6.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 5, 2, 7, 7, 2, 7, Blocks.extraBlockAdapter6.getDefaultState(), Blocks.extraBlockAdapter6.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 4, 5, 7, 4, 5, 9, Blocks.extraBlockAdapter6.getDefaultState(), Blocks.extraBlockAdapter6.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 8, 5, 7, 8, 5, 9, Blocks.extraBlockAdapter6.getDefaultState(), Blocks.extraBlockAdapter6.getDefaultState(), false);
         this.applyWorld(world, distanceAggregator, 5, 5, 7, 7, 5, 9, Blocks.extraBlockAdapter5.getDefaultState(), Blocks.extraBlockAdapter5.getDefaultState(), false);
         this.applyWorld(world, Blocks.block21.getDefaultState(), 6, 5, 6, distanceAggregator);
         return true;
      }
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      int count = 3;
      int count2 = 5;
      if (this.enumFacing == EnumFacing.WEST || this.enumFacing == EnumFacing.NORTH) {
         count = 8 - count;
         count2 = 8 - count2;
      }

      this.resolveGoldenIngotByClassEntry3Entry((ClassEntry3Entry)goldenIngot, list, random, 5, 1);
      if (this.enabled) {
         this.resolveGoldenIngot((ClassEntry3Entry)goldenIngot, list, random, count, 1);
      }

      if (this.enabled2) {
         this.resolveGoldenIngot((ClassEntry3Entry)goldenIngot, list, random, count2, 7);
      }

      if (this.enabled3) {
         this.resolveGoldenIngot2((ClassEntry3Entry)goldenIngot, list, random, count, 1);
      }

      if (this.enabled4) {
         this.resolveGoldenIngot2((ClassEntry3Entry)goldenIngot, list, random, count2, 7);
      }
   }

   public HitBoxSerializer() {
   }

   public static HitBoxSerializer resolveHitBoxSerializer(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -4, -3, 0, 10, 9, 11, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new HitBoxSerializer(count4, random, distanceAggregator, enumFacing) : null;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabled = tagCompound.matchesText("leftLow");
      this.enabled2 = tagCompound.matchesText("leftHigh");
      this.enabled3 = tagCompound.matchesText("rightLow");
      this.enabled4 = tagCompound.matchesText("rightHigh");
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("leftLow", this.enabled);
      tagCompound.applyText("leftHigh", this.enabled2);
      tagCompound.applyText("rightLow", this.enabled3);
      tagCompound.applyText("rightHigh", this.enabled4);
   }
}
