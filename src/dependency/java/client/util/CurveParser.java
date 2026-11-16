package client.util;

import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class CurveParser {
   public final boolean enabled;

   public void updateState() {
   }

   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState) {
      if (this.enabled) {
         world.matchesBlockPos(blockPos, blockState, 3);
      } else {
         world.matchesBlockPos(blockPos, blockState, 2);
      }
   }

   public abstract boolean matchesWorld(World world, Random random, BlockPos blockPos);

   public CurveParser(boolean flag) {
      this.enabled = flag;
   }

   public CurveParser() {
      this(false);
   }
}
