package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityEnderman;

public class FovProfiler extends BiomeIDIsOutOfBoundsManager {
   @Override
   public int computeCount(float factor) {
      return 0;
   }

   public FovProfiler(int count) {
      super(count);
      this.list3.clear();
      this.list4.clear();
      this.list2.clear();
      this.list.clear();
      this.list3.add(new ClassHolder(EntityEnderman.class, 10, 4, 4));
      this.iBlockState = Blocks.block115.getDefaultState();
      this.iBlockState2 = Blocks.block115.getDefaultState();
      this.alreadyDecoratingManager = new AlreadyDecoratingManagerAdapter();
   }
}
