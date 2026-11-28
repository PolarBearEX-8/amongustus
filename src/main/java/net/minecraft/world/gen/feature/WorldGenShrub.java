package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenShrub extends WorldGenTrees {
   private final IBlockState leavesMetadata;
   private final IBlockState woodMetadata;

   public WorldGenShrub(IBlockState state, IBlockState state2) {
      super(false);
      this.woodMetadata = state;
      this.leavesMetadata = state2;
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      Block block;
      while (((block = world.getBlockState(pos).getBlock()).getMaterial() == Material.air || block.getMaterial() == Material.leaves) && pos.getY() > 0) {
         pos = pos.down();
      }

      Block block2 = world.getBlockState(pos).getBlock();
      if (block2 == Blocks.dirt || block2 == Blocks.grass) {
         pos = pos.up();
         this.setBlockAndNotifyAdequately(world, pos, this.woodMetadata);

         for (int i = pos.getY(); i <= pos.getY() + 2; i++) {
            int n2 = 2 - (i - pos.getY());

            for (int j = pos.getX() - n2; j <= pos.getX() + n2; j++) {
               int n3 = j - pos.getX();

               for (int k = pos.getZ() - n2; k <= pos.getZ() + n2; k++) {
                  int n4 = k - pos.getZ();
                  if (Math.abs(n3) != n2 || Math.abs(n4) != n2 || rand.nextInt(2) != 0) {
                     BlockPos pos2 = new BlockPos(j, i, k);
                     if (!world.getBlockState(pos2).getBlock().isFullBlock()) {
                        this.setBlockAndNotifyAdequately(world, pos2, this.leavesMetadata);
                     }
                  }
               }
            }
         }
      }

      return true;
   }
}
