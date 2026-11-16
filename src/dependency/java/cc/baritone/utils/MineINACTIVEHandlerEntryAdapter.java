package cc.baritone.utils;

import net.minecraft.util.BlockPos;

public class MineINACTIVEHandlerEntryAdapter extends GoalRunAwayFromListener {
   public final MineINACTIVEHandlerAdapter mineINACTIVEHandlerAdapter;

   @Override
   public boolean checkIndex(int count, int count2, int count3) {
      return false;
   }

   @Override
   public double getValue() {
      return Double.NEGATIVE_INFINITY;
   }

   public MineINACTIVEHandlerEntryAdapter(MineINACTIVEHandlerAdapter mineINACTIVEHandlerAdapter2, double offset, Integer count, BlockPos[] blockPosArray) {
      super(offset, count, blockPosArray);
      this.mineINACTIVEHandlerAdapter = mineINACTIVEHandlerAdapter2;
   }
}
