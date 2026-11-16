package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.ExtendedItemEntryAdapter;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.IRONDOOREnum;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class FovAdapter extends ExtendedItemEntryAdapter {
   public boolean enabled;
   public boolean enabled2;

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Left", this.enabled);
      tagCompound.applyText("Right", this.enabled2);
   }

   public FovAdapter() {
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesWorld(world, distanceAggregator)) {
         return false;
      } else {
         this.applyWorld(world, distanceAggregator, 0, 0, 0, 4, 4, 6, true, random, ClassEntry3ArrayEntry.getIBlockStateEntryAdapter());
         this.applyWorld(world, random, distanceAggregator, this.iRONDOOREnum, 1, 1, 0);
         this.applyWorld(world, random, distanceAggregator, IRONDOOREnum.OPENING, 1, 1, 6);
         this.applyWorld(world, distanceAggregator, random, 0.1F, 1, 2, 1, Blocks.block21.getDefaultState());
         this.applyWorld(world, distanceAggregator, random, 0.1F, 3, 2, 1, Blocks.block21.getDefaultState());
         this.applyWorld(world, distanceAggregator, random, 0.1F, 1, 2, 5, Blocks.block21.getDefaultState());
         this.applyWorld(world, distanceAggregator, random, 0.1F, 3, 2, 5, Blocks.block21.getDefaultState());
         if (this.enabled) {
            this.applyWorld(world, distanceAggregator, 0, 1, 2, 0, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         if (this.enabled2) {
            this.applyWorld(world, distanceAggregator, 4, 1, 2, 4, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         return true;
      }
   }

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      this.resolveGoldenIngotByClassEntry3Entry((ClassEntry3Entry)goldenIngot, list, random, 1, 1);
      if (this.enabled) {
         this.resolveGoldenIngot((ClassEntry3Entry)goldenIngot, list, random, 1, 2);
      }

      if (this.enabled2) {
         this.resolveGoldenIngot2((ClassEntry3Entry)goldenIngot, list, random, 1, 2);
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.enabled = tagCompound.matchesText("Left");
      this.enabled2 = tagCompound.matchesText("Right");
   }

   public static FovAdapter resolveFovAdapter(List list, Random random, int count, int count2, int count3, EnumFacing enumFacing, int count4) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, -1, -1, 0, 5, 5, 7, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new FovAdapter(count4, random, distanceAggregator, enumFacing) : null;
   }

   public FovAdapter(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.iRONDOOREnum = this.nextInt(random);
      this.distanceAggregator = distanceAggregator;
      this.enabled = random.nextInt(2) == 0;
      this.enabled2 = random.nextInt(2) == 0;
   }
}
