package client.util;

import net.minecraft.block.Blocks;

public class FovScheduler extends BiomeIDIsOutOfBoundsManager {
   public FovScheduler(int count) {
      super(count);
      this.list4.clear();
      this.iBlockState = Blocks.blockSand.getDefaultState();
      this.iBlockState2 = Blocks.blockSand.getDefaultState();
      this.alreadyDecoratingManager.count9 = -999;
      this.alreadyDecoratingManager.count = 0;
      this.alreadyDecoratingManager.count4 = 0;
      this.alreadyDecoratingManager.count11 = 0;
   }
}
