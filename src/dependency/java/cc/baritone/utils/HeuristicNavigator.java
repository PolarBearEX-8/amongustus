package cc.baritone.utils;

import java.util.stream.Stream;

public enum HeuristicNavigator implements Listener10Extension {
    INSTANCE;

   @Override
   public Stream empty(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      DepthFirstManager depthFirstManager = heuristicSampler.getDepthFirstManager();
      if (depthFirstManager.isActive() && !depthFirstManager.size(4)) {
         while (depthFirstManager.size(2) && depthFirstManager.resolveTarget(DepthFirstCalibrator.INSTANCE) != null) {
            depthFirstManager.getBreadthFirstSchedulerAsBreadthFirstSche();
         }

         return depthFirstManager.resolveStream(DepthFirstCalibrator.INSTANCE);
      } else {
         return Stream.empty();
      }
   }

   @Override
   public Object resolveTarget(HeuristicSampler heuristicSampler, Object entry) throws ExceptionAdapter {
      return this.resolveBetterBlockPosXSYSZSBlockPos(heuristicSampler, (BetterBlockPosXSYSZSBlockPos)entry);
   }

   public BetterBlockPosXSYSZSBlockPos resolveBetterBlockPosXSYSZSBlockPos(HeuristicSampler heuristicSampler, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) throws ExceptionAdapter {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = betterBlockPosXSYSZSBlockPos;
      if (betterBlockPosXSYSZSBlockPos == null) {
         betterBlockPosXSYSZSBlockPos2 = BetterBlockPosXSYSZSBlockPos.betterBlockPosXSYSZSBlockPos;
      }

      DepthFirstManager depthFirstManager = heuristicSampler.getDepthFirstManager();
      return new BetterBlockPosXSYSZSBlockPos(
         (Double)depthFirstManager.resolveTarget(DepthFirstCalibrator.INSTANCE, (double)betterBlockPosXSYSZSBlockPos2.count9),
         (Double)depthFirstManager.resolveTarget(DepthFirstCalibrator.INSTANCE, (double)betterBlockPosXSYSZSBlockPos2.count12),
         (Double)depthFirstManager.resolveTarget(DepthFirstCalibrator.INSTANCE, (double)betterBlockPosXSYSZSBlockPos2.count10)
      );
   }


}
