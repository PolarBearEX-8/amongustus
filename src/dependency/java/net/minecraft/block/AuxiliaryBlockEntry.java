package net.minecraft.block;

import client.util.CurveParser;
import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class AuxiliaryBlockEntry extends CurveParser {
   public final Block block;
   public final int count;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      BlockPos blockPos2;
      for (blockPos2 = blockPos; blockPos2.getY() > 3; blockPos2 = blockPos2.down()) {
         if (!world.isAirBlock(blockPos2.down())) {
            Block block2 = world.getBlockState(blockPos2.down()).getBlock();
            if (block2 == Blocks.blockGrass || block2 == Blocks.block115 || block2 == Blocks.stone) {
               break;
            }
         }
      }

      if (blockPos2.getY() <= 3) {
         return false;
      } else {
         int count2 = this.count;

         for (int index = 0; count2 >= 0 && index < 3; index++) {
            int count3 = count2 + random.nextInt(2);
            int count4 = count2 + random.nextInt(2);
            int count5 = count2 + random.nextInt(2);
            float factor = (float)(count3 + count4 + count5) * 0.333F + 0.5F;

            for (BlockPos blockPos3 : (Iterable<BlockPos>)(Iterable<?>)BlockPos.resolveIterable(blockPos2.add(-count3, -count4, -count5), blockPos2.add(count3, count4, count5))) {
               if (blockPos3.getCoordinate(blockPos2) <= (double)(factor * factor)) {
                  world.matchesBlockPos(blockPos3, this.block.getDefaultState(), 4);
               }
            }

            blockPos2 = blockPos2.add(-(count2 + 1) + random.nextInt(2 + count2 * 2), 0 - random.nextInt(2), -(count2 + 1) + random.nextInt(2 + count2 * 2));
         }

         return true;
      }
   }

   public AuxiliaryBlockEntry(Block block2, int count2) {
      super(false);
      this.block = block2;
      this.count = count2;
   }
}
