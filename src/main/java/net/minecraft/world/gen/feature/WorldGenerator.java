package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class WorldGenerator {
   private final boolean doBlockNotify;

   public WorldGenerator(boolean flag) {
      this.doBlockNotify = flag;
   }

   public abstract boolean generate(World world, Random rand, BlockPos pos);

   public void setDecorationDefaults() {
   }

   public void setBlockAndNotifyAdequately(World world, BlockPos pos, IBlockState state) {
      if (this.doBlockNotify) {
         world.setBlockState(pos, state, 3);
      } else {
         world.setBlockState(pos, state, 2);
      }
   }

   public WorldGenerator() {
      this(false);
   }
}
