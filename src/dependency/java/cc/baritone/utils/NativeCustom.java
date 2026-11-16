package cc.baritone.utils;

import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.util.EnumFacing;

public enum NativeCustom implements AuxiliaryListener10Extension {
    INSTANCE;

   public static final NativeCustom[] ENUM_VALUES = new NativeCustom[]{INSTANCE};

   @Override
   public Stream empty(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return new HeuristicEstimator()
         .concat(Stream.of(EnumFacing.values()).map(EnumFacing::getText).map(String::toLowerCase))
         .resolveHeuristicEstimatorByText(heuristicSampler.getDepthFirstManager().getText())
         .getStream();
   }

   @Override
   public Object resolveTarget(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return this.resolveEnumFacing(heuristicSampler);
   }

   public EnumFacing resolveEnumFacing(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return EnumFacing.valueOf(heuristicSampler.getDepthFirstManager().getText().toUpperCase(Locale.US));
   }


}
