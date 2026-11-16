package cc.baritone.utils;

import java.util.stream.Stream;
import net.minecraft.util.MathHelper;

public enum INSTANCEEnumHandler implements Listener10Extension {
    INSTANCE;

   @Override
   public Object resolveTarget(HeuristicSampler heuristicSampler, Object entry) throws ExceptionAdapter {
      return this.resolveBreadthFirstInspector(heuristicSampler, (BetterBlockPosXSYSZSBlockPos)entry);
   }

   public BreadthFirstInspector resolveBreadthFirstInspector(HeuristicSampler heuristicSampler, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) throws ExceptionAdapter {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = betterBlockPosXSYSZSBlockPos;
      if (betterBlockPosXSYSZSBlockPos == null) {
         betterBlockPosXSYSZSBlockPos2 = BetterBlockPosXSYSZSBlockPos.betterBlockPosXSYSZSBlockPos;
      }

      return new BreadthFirstInspector(
         MathHelper.ceiling_double_int((Double)heuristicSampler.getDepthFirstManager().resolveTarget(DepthFirstCalibrator.INSTANCE, (double)betterBlockPosXSYSZSBlockPos2.count12))
      );
   }

   @Override
   public Stream empty(HeuristicSampler heuristicSampler) {
      DepthFirstManager depthFirstManager = heuristicSampler.getDepthFirstManager();
      return depthFirstManager.sizeByIndex(1) ? depthFirstManager.resolveStream(DepthFirstCalibrator.INSTANCE) : Stream.empty();
   }
}
