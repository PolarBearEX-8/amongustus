package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.GoldenOrb;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class HitBoxTransformer extends GoldenOrb {
   public int count4;

   @Override
   public void applyGoldenIngot(GoldenIngot goldenIngot, List list, Random random) {
      boolean flag = false;

      for (int index = random.nextInt(5); index < this.count4 - 8; index += 2 + random.nextInt(5)) {
         GoldenIngot goldenIngot2 = this.resolveGoldenIngotByInvalidBiomeIdMa((InvalidBiomeIdManagerEntry)goldenIngot, list, random, 0, index);
         if (goldenIngot2 != null) {
            index += Math.max(goldenIngot2.distanceAggregator.getCountAsIndex(), goldenIngot2.distanceAggregator.getCount());
            flag = true;
         }
      }

      for (int index2 = random.nextInt(5); index2 < this.count4 - 8; index2 += 2 + random.nextInt(5)) {
         GoldenIngot goldenIngot3 = this.resolveGoldenIngot((InvalidBiomeIdManagerEntry)goldenIngot, list, random, 0, index2);
         if (goldenIngot3 != null) {
            index2 += Math.max(goldenIngot3.distanceAggregator.getCountAsIndex(), goldenIngot3.distanceAggregator.getCount());
            flag = true;
         }
      }

      if (flag && random.nextInt(3) > 0 && this.enumFacing != null) {
         switch (HitBoxTransformerEnumFacingSwitchMap.countArray[this.enumFacing.ordinal()]) {
            case 1:
               AngleService.resolveGoldenIngotByInvalidBiomeIdMa(
                  (InvalidBiomeIdManagerEntry)goldenIngot,
                  list,
                  random,
                  this.distanceAggregator.count3 - 1,
                  this.distanceAggregator.count6,
                  this.distanceAggregator.count4,
                  EnumFacing.WEST,
                  this.getCount()
               );
               break;
            case 2:
               AngleService.resolveGoldenIngotByInvalidBiomeIdMa(
                  (InvalidBiomeIdManagerEntry)goldenIngot,
                  list,
                  random,
                  this.distanceAggregator.count3 - 1,
                  this.distanceAggregator.count6,
                  this.distanceAggregator.count - 2,
                  EnumFacing.WEST,
                  this.getCount()
               );
               break;
            case 3:
               AngleService.resolveGoldenIngotByInvalidBiomeIdMa(
                  (InvalidBiomeIdManagerEntry)goldenIngot,
                  list,
                  random,
                  this.distanceAggregator.count3,
                  this.distanceAggregator.count6,
                  this.distanceAggregator.count4 - 1,
                  EnumFacing.NORTH,
                  this.getCount()
               );
               break;
            case 4:
               AngleService.resolveGoldenIngotByInvalidBiomeIdMa(
                  (InvalidBiomeIdManagerEntry)goldenIngot,
                  list,
                  random,
                  this.distanceAggregator.count5 - 2,
                  this.distanceAggregator.count6,
                  this.distanceAggregator.count4 - 1,
                  EnumFacing.NORTH,
                  this.getCount()
               );
         }
      }

      if (flag && random.nextInt(3) > 0 && this.enumFacing != null) {
         switch (HitBoxTransformerEnumFacingSwitchMap.countArray[this.enumFacing.ordinal()]) {
            case 1:
               AngleService.resolveGoldenIngotByInvalidBiomeIdMa(
                  (InvalidBiomeIdManagerEntry)goldenIngot,
                  list,
                  random,
                  this.distanceAggregator.count5 + 1,
                  this.distanceAggregator.count6,
                  this.distanceAggregator.count4,
                  EnumFacing.EAST,
                  this.getCount()
               );
               break;
            case 2:
               AngleService.resolveGoldenIngotByInvalidBiomeIdMa(
                  (InvalidBiomeIdManagerEntry)goldenIngot,
                  list,
                  random,
                  this.distanceAggregator.count5 + 1,
                  this.distanceAggregator.count6,
                  this.distanceAggregator.count - 2,
                  EnumFacing.EAST,
                  this.getCount()
               );
               break;
            case 3:
               AngleService.resolveGoldenIngotByInvalidBiomeIdMa(
                  (InvalidBiomeIdManagerEntry)goldenIngot,
                  list,
                  random,
                  this.distanceAggregator.count3,
                  this.distanceAggregator.count6,
                  this.distanceAggregator.count + 1,
                  EnumFacing.SOUTH,
                  this.getCount()
               );
               break;
            case 4:
               AngleService.resolveGoldenIngotByInvalidBiomeIdMa(
                  (InvalidBiomeIdManagerEntry)goldenIngot,
                  list,
                  random,
                  this.distanceAggregator.count5 - 2,
                  this.distanceAggregator.count6,
                  this.distanceAggregator.count + 1,
                  EnumFacing.SOUTH,
                  this.getCount()
               );
         }
      }
   }

   public HitBoxTransformer() {
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("Length", this.count4);
   }

   public HitBoxTransformer(InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, int count, Random random, DistanceAggregator distanceAggregator, EnumFacing enumFacing) {
      super(invalidBiomeIdManagerEntry, count);
      this.enumFacing = enumFacing;
      this.distanceAggregator = distanceAggregator;
      this.count4 = Math.max(distanceAggregator.getCountAsIndex(), distanceAggregator.getCount());
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      IBlockState blockState = this.resolveIBlockState(Blocks.block45.getDefaultState());
      IBlockState blockState2 = this.resolveIBlockState(Blocks.block65.getDefaultState());

      for (int index = this.distanceAggregator.count3; index <= this.distanceAggregator.count5; index++) {
         for (int index2 = this.distanceAggregator.count4; index2 <= this.distanceAggregator.count; index2++) {
            BlockPos blockPos = new BlockPos(index, 64, index2);
            if (distanceAggregator.matchesVec3i(blockPos)) {
               BlockPos blockPos2 = world.resolveBlockPos(blockPos).down();
               world.matchesBlockPos(blockPos2, blockState, 2);
               world.matchesBlockPos(blockPos2.down(), blockState2, 2);
            }
         }
      }

      return true;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.count4 = tagCompound.computeCount("Length");
   }

   public static DistanceAggregator resolveDistanceAggregator(
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry, List list, Random random, int count, int count2, int count3, EnumFacing enumFacing
   ) {
      for (int index = 7 * MathHelper.nextInt(random, 3, 5); index >= 7; index -= 7) {
         DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(count, count2, count3, 0, 0, 0, 3, 3, index, enumFacing);
         if (GoldenIngot.resolveGoldenIngot(list, distanceAggregator) == null) {
            return distanceAggregator;
         }
      }

      return null;
   }
}
