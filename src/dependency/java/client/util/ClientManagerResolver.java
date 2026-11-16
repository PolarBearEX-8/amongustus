package client.util;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class ClientManagerResolver extends CurveParser {
   public void applyWorld(World world, Random random, BlockPos blockPos) {
   }

   public void applyWorldByWorld(World world, BlockPos blockPos) {
      if (world.getBlockState(blockPos).getBlock() != Blocks.block115) {
         this.applyWorld(world, blockPos, Blocks.block115.getDefaultState());
      }
   }

   public boolean matchesBlock(Block block) {
      Material material = block.getMaterial();
      return material == Material.coreItemEntry
         || material == Material.material9
         || block == Blocks.blockGrass
         || block == Blocks.block115
         || block == Blocks.block140
         || block == Blocks.block104
         || block == Blocks.block83
         || block == Blocks.block144;
   }

   public ClientManagerResolver(boolean flag) {
      super(flag);
   }
}
