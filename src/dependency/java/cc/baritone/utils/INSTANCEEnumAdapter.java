package cc.baritone.utils;

import java.util.stream.Stream;
import net.minecraft.util.MathHelper;

public enum INSTANCEEnumAdapter implements Listener10Extension {
    INSTANCE;

   public DepthFirstResolver resolveDepthFirstResolver(HeuristicSampler heuristicSampler, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) throws ExceptionAdapter {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = betterBlockPosXSYSZSBlockPos;
      if (betterBlockPosXSYSZSBlockPos == null) {
         betterBlockPosXSYSZSBlockPos2 = BetterBlockPosXSYSZSBlockPos.betterBlockPosXSYSZSBlockPos;
      }

      DepthFirstManager depthFirstManager = heuristicSampler.getDepthFirstManager();
      return new DepthFirstResolver(
         MathHelper.ceiling_double_int((Double)depthFirstManager.resolveTarget(DepthFirstCalibrator.INSTANCE, (double)betterBlockPosXSYSZSBlockPos2.count9)),
         MathHelper.ceiling_double_int((Double)depthFirstManager.resolveTarget(DepthFirstCalibrator.INSTANCE, (double)betterBlockPosXSYSZSBlockPos2.count12)),
         MathHelper.ceiling_double_int((Double)depthFirstManager.resolveTarget(DepthFirstCalibrator.INSTANCE, (double)betterBlockPosXSYSZSBlockPos2.count10))
      );
   }

   @Override
   public Stream empty(HeuristicSampler heuristicSampler) {
      DepthFirstManager depthFirstManager = heuristicSampler.getDepthFirstManager();
      return depthFirstManager.sizeByIndex(3) ? depthFirstManager.resolveStream(DepthFirstCalibrator.INSTANCE) : Stream.empty();
   }

   @Override
   public Object resolveTarget(HeuristicSampler heuristicSampler, Object entry) throws ExceptionAdapter {
      return this.resolveDepthFirstResolver(heuristicSampler, (BetterBlockPosXSYSZSBlockPos)entry);
   }


}
