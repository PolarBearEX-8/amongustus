package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenBlockBlob extends WorldGenerator {
   private final Block block;
   private final int startRadius;

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      while (pos.getY() > 3) {
         if (!world.isAirBlock(pos.down())) {
            Block block2 = world.getBlockState(pos.down()).getBlock();
            if (block2 == Blocks.grass || block2 == Blocks.dirt || block2 == Blocks.stone) {
               break;
            }
         }

         pos = pos.down();
      }

      if (pos.getY() <= 3) {
         return false;
      }

      int startRadius2 = this.startRadius;

      for (int i = 0; startRadius2 >= 0 && i < 3; i++) {
         int n = startRadius2 + rand.nextInt(2);
         int n2 = startRadius2 + rand.nextInt(2);
         int n3 = startRadius2 + rand.nextInt(2);
         float f = (float)(n + n2 + n3) * 0.333F + 0.5F;

         for (BlockPos pos2 : (Iterable<BlockPos>)(BlockPos.getAllInBox(pos.add(-n, -n2, -n3), pos.add(n, n2, n3)))) {
            if (pos2.distanceSq(pos) <= (double)(f * f)) {
               world.setBlockState(pos2, this.block.getDefaultState(), 4);
            }
         }

         pos = pos.add(-(startRadius2 + 1) + rand.nextInt(2 + startRadius2 * 2), 0 - rand.nextInt(2), -(startRadius2 + 1) + rand.nextInt(2 + startRadius2 * 2));
      }

      return true;
   }

   public WorldGenBlockBlob(Block block2, int n) {
      super(false);
      this.block = block2;
      this.startRadius = n;
   }
}
