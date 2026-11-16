package cc.baritone.utils;

import net.minecraft.util.BlockPos;

public class AStarEvaluator extends AStarManager {
   public final BuilderPausedSchematicsHandler builderPausedSchematicsHandler;

   @Override
   public boolean checkIndex(int count, int count2, int count3) {
      return count2 <= this.count && (count != this.count2 || count2 != this.count || count3 != this.count3) ? super.checkIndex(count, count2, count3) : false;
   }

   public AStarEvaluator(BuilderPausedSchematicsHandler builderPausedSchematicsHandler2, BlockPos blockPos) {
      super(blockPos);
      this.builderPausedSchematicsHandler = builderPausedSchematicsHandler2;
   }
}
