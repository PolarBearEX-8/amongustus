package client.util;

import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BiomeIDIsOutOfBoundsManagerAdapter extends BiomeIDIsOutOfBoundsManager {
   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      super.applyWorld(world, random, blockPos);
      if (random.nextInt(1000) == 0) {
         int count = random.nextInt(16) + 8;
         int count2 = random.nextInt(16) + 8;
         BlockPos blockPos2 = world.resolveBlockPos2(blockPos.add(count, 0, count2)).up();
         new RotationDispatcher().matchesWorld(world, random, blockPos2);
      }
   }

   public BiomeIDIsOutOfBoundsManagerAdapter(int count) {
      super(count);
      this.list4.clear();
      this.iBlockState = Blocks.blockSand.getDefaultState();
      this.iBlockState2 = Blocks.blockSand.getDefaultState();
      this.alreadyDecoratingManager.count9 = -999;
      this.alreadyDecoratingManager.count = 2;
      this.alreadyDecoratingManager.count4 = 50;
      this.alreadyDecoratingManager.count11 = 10;
      this.list4.clear();
   }
}
