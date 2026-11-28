package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class WorldGenAbstractTree extends WorldGenerator {
   public WorldGenAbstractTree(boolean flag) {
      super(flag);
   }

   public void setDirtAt(World world, BlockPos pos) {
      if (world.getBlockState(pos).getBlock() != Blocks.dirt) {
         this.setBlockAndNotifyAdequately(world, pos, Blocks.dirt.getDefaultState());
      }
   }

   public void generateSaplings(World world, Random rand, BlockPos pos) {
   }

   public boolean canGrowInto(Block block) {
      Material material = block.getMaterial();
      return material == Material.air
         || material == Material.leaves
         || block == Blocks.grass
         || block == Blocks.dirt
         || block == Blocks.log
         || block == Blocks.log2
         || block == Blocks.sapling
         || block == Blocks.vine;
   }
}
