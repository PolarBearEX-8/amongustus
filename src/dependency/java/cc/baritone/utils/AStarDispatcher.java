package cc.baritone.utils;

import java.util.stream.Stream;

public enum AStarDispatcher implements Listener10Extension {
    INSTANCE;

   public SecondaryListener resolveSecondaryListener(HeuristicSampler heuristicSampler, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) throws ExceptionAdapter {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = betterBlockPosXSYSZSBlockPos;
      if (betterBlockPosXSYSZSBlockPos == null) {
         betterBlockPosXSYSZSBlockPos2 = BetterBlockPosXSYSZSBlockPos.betterBlockPosXSYSZSBlockPos;
      }

      DepthFirstManager depthFirstManager = heuristicSampler.getDepthFirstManager();
      DepthFirstResolver depthFirstResolver = (DepthFirstResolver)depthFirstManager.resolveTarget3(INSTANCEEnumAdapter.INSTANCE, betterBlockPosXSYSZSBlockPos2);
      if (depthFirstResolver != null) {
         return depthFirstResolver;
      } else {
         DiagonalEvaluator diagonalEvaluator = (DiagonalEvaluator)depthFirstManager.resolveTarget3(StaticCustom.INSTANCE, betterBlockPosXSYSZSBlockPos2);
         if (diagonalEvaluator != null) {
            return diagonalEvaluator;
         } else {
            BreadthFirstInspector breadthFirstInspector = (BreadthFirstInspector)depthFirstManager.resolveTarget3(INSTANCEEnumHandler.INSTANCE, betterBlockPosXSYSZSBlockPos2);
            return (SecondaryListener)(breadthFirstInspector != null ? breadthFirstInspector : new DepthFirstResolver(betterBlockPosXSYSZSBlockPos2));
         }
      }
   }

   @Override
   public Stream empty(HeuristicSampler heuristicSampler) {
      return heuristicSampler.getDepthFirstManager().resolveStream(DepthFirstCalibrator.INSTANCE);
   }



   @Override
   public Object resolveTarget(HeuristicSampler heuristicSampler, Object entry) throws ExceptionAdapter {
      return this.resolveSecondaryListener(heuristicSampler, (BetterBlockPosXSYSZSBlockPos)entry);
   }
}
