package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.TertiaryItemEntryAdapter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class HitBoxValidator extends TertiaryItemEntryAdapter {
   public int count2;

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("Seed", this.count2);
   }

   public HitBoxValidator(int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
      this.count2 = random.nextInt();
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      Random random2 = new Random((long)this.count2);

      for (int index = 0; index <= 4; index++) {
         for (int index2 = 3; index2 <= 4; index2++) {
            int count = random2.nextInt(8);
            this.applyWorld(world, distanceAggregator, index, index2, 0, index, index2, count, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
         }
      }

      int count3 = random2.nextInt(8);
      this.applyWorld(world, distanceAggregator, 0, 5, 0, 0, 5, count3, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      int count4 = random2.nextInt(8);
      this.applyWorld(world, distanceAggregator, 4, 5, 0, 4, 5, count4, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);

      for (int index3 = 0; index3 <= 4; index3++) {
         int count5 = random2.nextInt(5);
         this.applyWorld(world, distanceAggregator, index3, 2, 0, index3, 2, count5, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
      }

      for (int index4 = 0; index4 <= 4; index4++) {
         for (int index5 = 0; index5 <= 1; index5++) {
            int count6 = random2.nextInt(3);
            this.applyWorld(world, distanceAggregator, index4, index5, 0, index4, index5, count6, Blocks.block86.getDefaultState(), Blocks.block86.getDefaultState(), false);
         }
      }

      return true;
   }

   public static HitBoxValidator resolveHitBoxValidator(List list, Random random, int count, int count3, int count4, EnumFacing enumFacing, int count5) {
      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count3, count4, -1, -3, 0, 5, 10, 8, enumFacing);
      return matchesDistanceAggregator(distanceAggregator) && GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null ? new HitBoxValidator(count5, random, distanceAggregator, enumFacing) : null;
   }

   public HitBoxValidator() {
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.count2 = tagCompound.computeCount("Seed");
   }
}
