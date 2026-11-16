package client.util;

import net.minecraft.block.Blocks;

public class TargetSerializer extends BiomeIDIsOutOfBoundsManager {
   public TargetSerializer(int count) {
      super(count);
      this.list4.clear();
      this.iBlockState = Blocks.stone.getDefaultState();
      this.iBlockState2 = Blocks.stone.getDefaultState();
      this.alreadyDecoratingManager.count9 = -999;
      this.alreadyDecoratingManager.count = 0;
      this.alreadyDecoratingManager.count4 = 0;
      this.alreadyDecoratingManager.count11 = 0;
   }
}
