package cc.baritone.utils;

import net.minecraft.util.BlockPos;

public class HeuristicController extends GoalRunAwayFromListener {
   public final ExploringRandomlyToFindManager exploringRandomlyToFindManager;

   @Override
   public boolean checkIndex(int count, int count2, int count3) {
      return false;
   }

   public HeuristicController(ExploringRandomlyToFindManager exploringRandomlyToFindManager2, double offset, BlockPos[] blockPosArray) {
      super(offset, blockPosArray);
      this.exploringRandomlyToFindManager = exploringRandomlyToFindManager2;
   }

   @Override
   public double getValue() {
      return Double.NEGATIVE_INFINITY;
   }
}
