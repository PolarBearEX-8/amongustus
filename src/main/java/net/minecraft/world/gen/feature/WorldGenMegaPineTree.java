package net.minecraft.world.gen.feature;

import net.minecraft.block.BlockPlanks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenMegaPineTree extends WorldGenHugeTrees {
   private static final IBlockState TRUNK = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
   private static final IBlockState LEAF = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE)
      .withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);
   private static final IBlockState PODZOL = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
   private boolean useBaseHeight;

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      int height = this.getHeight(rand);
      if (!this.ensureGrowable(world, rand, pos, height)) {
         return false;
      } else {
         this.createCrown(world, pos.getX(), pos.getZ(), pos.getY() + height, 0, rand);

         for (int i = 0; i < height; i++) {
            Block block = world.getBlockState(pos.up(i)).getBlock();
            if (block.getMaterial() == Material.air || block.getMaterial() == Material.leaves) {
               this.setBlockAndNotifyAdequately(world, pos.up(i), this.woodMetadata);
            }

            if (i < height - 1) {
               block = world.getBlockState(pos.add(1, i, 0)).getBlock();
               if (block.getMaterial() == Material.air || block.getMaterial() == Material.leaves) {
                  this.setBlockAndNotifyAdequately(world, pos.add(1, i, 0), this.woodMetadata);
               }

               block = world.getBlockState(pos.add(1, i, 1)).getBlock();
               if (block.getMaterial() == Material.air || block.getMaterial() == Material.leaves) {
                  this.setBlockAndNotifyAdequately(world, pos.add(1, i, 1), this.woodMetadata);
               }

               block = world.getBlockState(pos.add(0, i, 1)).getBlock();
               if (block.getMaterial() == Material.air || block.getMaterial() == Material.leaves) {
                  this.setBlockAndNotifyAdequately(world, pos.add(0, i, 1), this.woodMetadata);
               }
            }
         }

         return true;
      }
   }

   private void createCrown(World world, int n, int n2, int n3, int n4, Random rand) {
      int n5 = rand.nextInt(5) + (this.useBaseHeight ? this.baseHeight : 3);
      int n6 = 0;

      for (int i = n3 - n5; i <= n3; i++) {
         int n7 = n3 - i;
         int n8 = n4 + MathHelper.floor_float((float)n7 / (float)n5 * 3.5F);
         this.growLeavesLayerStrict(world, new BlockPos(n, i, n2), n8 + (n7 > 0 && n8 == n6 && (i & 1) == 0 ? 1 : 0));
         n6 = n8;
      }
   }

   @Override
   public void generateSaplings(World world, Random rand, BlockPos pos) {
      this.placePodzolCircle(world, pos.west().north());
      this.placePodzolCircle(world, pos.east(2).north());
      this.placePodzolCircle(world, pos.west().south(2));
      this.placePodzolCircle(world, pos.east(2).south(2));

      for (int i = 0; i < 5; i++) {
         int n = rand.nextInt(64);
         int n2 = n % 8;
         int n3 = n / 8;
         if (n2 == 0 || n2 == 7 || n3 == 0 || n3 == 7) {
            this.placePodzolCircle(world, pos.add(-3 + n2, 0, -3 + n3));
         }
      }
   }

   public WorldGenMegaPineTree(boolean flag, boolean flag2) {
      super(flag, 13, 15, TRUNK, LEAF);
      this.useBaseHeight = flag2;
   }

   private void placePodzolAt(World world, BlockPos pos) {
      for (int i = 2; i >= -3; i--) {
         BlockPos pos2 = pos.up(i);
         Block block = world.getBlockState(pos2).getBlock();
         if (block == Blocks.grass || block == Blocks.dirt) {
            this.setBlockAndNotifyAdequately(world, pos2, PODZOL);
            break;
         }

         if (block.getMaterial() != Material.air && i < 0) {
            break;
         }
      }
   }

   private void placePodzolCircle(World world, BlockPos pos) {
      for (int i = -2; i <= 2; i++) {
         for (int j = -2; j <= 2; j++) {
            if (Math.abs(i) != 2 || Math.abs(j) != 2) {
               this.placePodzolAt(world, pos.add(i, 0, j));
            }
         }
      }
   }
}
