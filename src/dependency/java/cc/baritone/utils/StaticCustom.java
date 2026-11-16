package cc.baritone.utils;

import java.util.stream.Stream;
import net.minecraft.util.MathHelper;

public enum StaticCustom implements Listener10Extension {
    INSTANCE;

   @Override
   public Stream empty(HeuristicSampler heuristicSampler) {
      DepthFirstManager depthFirstManager = heuristicSampler.getDepthFirstManager();
      return depthFirstManager.sizeByIndex(2) ? depthFirstManager.resolveStream(DepthFirstCalibrator.INSTANCE) : Stream.empty();
   }

   @Override
   public Object resolveTarget(HeuristicSampler heuristicSampler, Object entry) throws ExceptionAdapter {
      return this.resolveDiagonalEvaluator(heuristicSampler, (BetterBlockPosXSYSZSBlockPos)entry);
   }



   public DiagonalEvaluator resolveDiagonalEvaluator(HeuristicSampler heuristicSampler, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) throws ExceptionAdapter {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = betterBlockPosXSYSZSBlockPos;
      if (betterBlockPosXSYSZSBlockPos == null) {
         betterBlockPosXSYSZSBlockPos2 = BetterBlockPosXSYSZSBlockPos.betterBlockPosXSYSZSBlockPos;
      }

      DepthFirstManager depthFirstManager = heuristicSampler.getDepthFirstManager();
      return new DiagonalEvaluator(
         MathHelper.ceiling_double_int((Double)depthFirstManager.resolveTarget(DepthFirstCalibrator.INSTANCE, (double)betterBlockPosXSYSZSBlockPos2.count9)),
         MathHelper.ceiling_double_int((Double)depthFirstManager.resolveTarget(DepthFirstCalibrator.INSTANCE, (double)betterBlockPosXSYSZSBlockPos2.count10))
      );
   }
}
