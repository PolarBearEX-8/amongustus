package cc.baritone.utils;

import client.setting.EnumFacingXType;
import java.util.Locale;
import java.util.stream.Stream;

public enum DepthFirstVerifier implements AuxiliaryListener10Extension {
    INSTANCE;

   public static final DepthFirstVerifier[] ENUM_VALUES = new DepthFirstVerifier[]{INSTANCE};

   public EnumFacingXType resolveEnumFacingXType(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return EnumFacingXType.valueOf(heuristicSampler.getDepthFirstManager().getText().toUpperCase(Locale.US));
   }

   @Override
   public Object resolveTarget(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return this.resolveEnumFacingXType(heuristicSampler);
   }

   @Override
   public Stream empty(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return new HeuristicEstimator()
         .concat(Stream.of(EnumFacingXType.values()).map(EnumFacingXType::getText).map(String::toLowerCase))
         .resolveHeuristicEstimatorByText(heuristicSampler.getDepthFirstManager().getText())
         .getStream();
   }
}
