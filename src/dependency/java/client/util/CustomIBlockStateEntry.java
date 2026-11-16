package client.util;

import java.util.Random;
import net.minecraft.block.Blocks;

public abstract class CustomIBlockStateEntry {
   public IBlockState iBlockState = Blocks.air.getDefaultState();

   public IBlockState getIBlockState() {
      return this.iBlockState;
   }

   public abstract void applyRandom(Random random, int count, int count2, int count3, boolean flag);
}
