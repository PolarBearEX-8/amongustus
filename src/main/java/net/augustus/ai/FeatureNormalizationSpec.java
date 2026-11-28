package net.augustus.ai;

public final class FeatureNormalizationSpec {
   public final FeatureScalingMethod method;
   public final String name;
   public final float hi;
   public final float lo;

   public FeatureNormalizationSpec(String str, FeatureScalingMethod featureScalingMethod, float f, float f2) {
      this.name = str;
      this.method = featureScalingMethod;
      this.lo = f;
      this.hi = f2;
   }
}
