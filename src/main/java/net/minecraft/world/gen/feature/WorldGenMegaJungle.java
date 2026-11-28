package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.BlockVine;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenMegaJungle extends WorldGenHugeTrees {
   private void createCrown(World world, BlockPos pos, int n) {

      for (int i = -((byte)2); i <= 0; i++) {
         this.growLeavesLayerStrict(world, pos.up(i), n + 1 - i);
      }
   }

   public WorldGenMegaJungle(boolean flag, int n, int n2, IBlockState state, IBlockState state2) {
      super(flag, n, n2, state, state2);
   }

   private void placeVine(World world, Random rand, BlockPos pos, PropertyBool propertyBool) {
      if (rand.nextInt(3) > 0 && world.isAirBlock(pos)) {
         this.setBlockAndNotifyAdequately(world, pos, Blocks.vine.getDefaultState().withProperty(propertyBool, Boolean.TRUE));
      }
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      int height = this.getHeight(rand);
      if (!this.ensureGrowable(world, rand, pos, height)) {
         return false;
      } else {
         this.createCrown(world, pos.up(height), 2);

         for (int i = pos.getY() + height - 2 - rand.nextInt(4); i > pos.getY() + height / 2; i -= 2 + rand.nextInt(4)) {
            float f = rand.nextFloat() * (float) Math.PI * 2.0F;
            int n = pos.getX() + (int)(0.5F + MathHelper.cos(f) * 4.0F);
            int n2 = pos.getZ() + (int)(0.5F + MathHelper.sin(f) * 4.0F);

            for (int j = 0; j < 5; j++) {
               n = pos.getX() + (int)(1.5F + MathHelper.cos(f) * (float)j);
               n2 = pos.getZ() + (int)(1.5F + MathHelper.sin(f) * (float)j);
               this.setBlockAndNotifyAdequately(world, new BlockPos(n, i - 3 + j / 2, n2), this.woodMetadata);
            }

            int n3 = 1 + rand.nextInt(2);
            int n4 = i;

            for (int k = i - n3; k <= n4; k++) {
               int n5 = k - n4;
               this.growLeavesLayer(world, new BlockPos(n, k, n2), 1 - n5);
            }
         }

         for (int m = 0; m < height; m++) {
            BlockPos pos2 = pos.up(m);
            if (this.canGrowInto(world.getBlockState(pos2).getBlock())) {
               this.setBlockAndNotifyAdequately(world, pos2, this.woodMetadata);
               if (m > 0) {
                  this.placeVine(world, rand, pos2.west(), BlockVine.EAST);
                  this.placeVine(world, rand, pos2.north(), BlockVine.SOUTH);
               }
            }

            if (m < height - 1) {
               BlockPos pos3 = pos2.east();
               if (this.canGrowInto(world.getBlockState(pos3).getBlock())) {
                  this.setBlockAndNotifyAdequately(world, pos3, this.woodMetadata);
                  if (m > 0) {
                     this.placeVine(world, rand, pos3.east(), BlockVine.WEST);
                     this.placeVine(world, rand, pos3.north(), BlockVine.SOUTH);
                  }
               }

               BlockPos pos4 = pos2.south().east();
               if (this.canGrowInto(world.getBlockState(pos4).getBlock())) {
                  this.setBlockAndNotifyAdequately(world, pos4, this.woodMetadata);
                  if (m > 0) {
                     this.placeVine(world, rand, pos4.east(), BlockVine.WEST);
                     this.placeVine(world, rand, pos4.south(), BlockVine.NORTH);
                  }
               }

               BlockPos pos5 = pos2.south();
               if (this.canGrowInto(world.getBlockState(pos5).getBlock())) {
                  this.setBlockAndNotifyAdequately(world, pos5, this.woodMetadata);
                  if (m > 0) {
                     this.placeVine(world, rand, pos5.west(), BlockVine.EAST);
                     this.placeVine(world, rand, pos5.south(), BlockVine.NORTH);
                  }
               }
            }
         }

         return true;
      }
   }
}
