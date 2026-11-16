package client.util;

public class EnhancedFloatEntry {
   public float factor5;
   public float factor;
   public float factor7;
   public float factor2;
   public float factor8;
   public float factor3;
   public float factor6;
   public float factor4;
   public float factor9;

   public final void setFactor8(float factor10) {
      this.factor8 = factor10;
   }

   public final float getFactor4() {
      return this.factor4;
   }

   public final float getFactor3() {
      return this.factor3;
   }

   public final void setFactor(float factor10) {
      this.factor = factor10;
   }

   public final void setFactor9(float factor10) {
      this.factor9 = factor10;
   }

   public final float getFactor7() {
      return this.factor7;
   }

   public final float getFactor5() {
      return this.factor5;
   }

   public final void setFactor6(float factor10) {
      this.factor6 = factor10;
   }

   public final void setFactor4(float factor10) {
      this.factor4 = factor10;
   }

   public final void applyEnhancedFloatEntry(EnhancedFloatEntry enhancedFloatEntry) {
      float factor10 = this.factor5 * enhancedFloatEntry.factor5 + this.factor * enhancedFloatEntry.factor3 + this.factor8 * enhancedFloatEntry.factor2;
      float factor11 = this.factor5 * enhancedFloatEntry.factor + this.factor * enhancedFloatEntry.factor6 + this.factor8 * enhancedFloatEntry.factor7;
      float factor12 = this.factor5 * enhancedFloatEntry.factor8 + this.factor * enhancedFloatEntry.factor9 + this.factor8 * enhancedFloatEntry.factor4;
      float factor13 = this.factor3 * enhancedFloatEntry.factor5 + this.factor6 * enhancedFloatEntry.factor3 + this.factor9 * enhancedFloatEntry.factor2;
      float factor14 = this.factor3 * enhancedFloatEntry.factor + this.factor6 * enhancedFloatEntry.factor6 + this.factor9 * enhancedFloatEntry.factor7;
      float factor15 = this.factor3 * enhancedFloatEntry.factor8 + this.factor6 * enhancedFloatEntry.factor9 + this.factor9 * enhancedFloatEntry.factor4;
      float factor16 = this.factor2 * enhancedFloatEntry.factor5 + this.factor7 * enhancedFloatEntry.factor3 + this.factor4 * enhancedFloatEntry.factor2;
      float factor17 = this.factor2 * enhancedFloatEntry.factor + this.factor7 * enhancedFloatEntry.factor6 + this.factor4 * enhancedFloatEntry.factor7;
      float factor18 = this.factor2 * enhancedFloatEntry.factor8 + this.factor7 * enhancedFloatEntry.factor9 + this.factor4 * enhancedFloatEntry.factor4;
      this.factor5 = factor10;
      this.factor = factor11;
      this.factor8 = factor12;
      this.factor3 = factor13;
      this.factor6 = factor14;
      this.factor9 = factor15;
      this.factor2 = factor16;
      this.factor7 = factor17;
      this.factor4 = factor18;
   }

   public final float getFactor8() {
      return this.factor8;
   }

   public final void setFactor3(float factor10) {
      this.factor3 = factor10;
   }

   public final float getFactor2() {
      return this.factor2;
   }

   public final void setFactor2(float factor10) {
      this.factor2 = factor10;
   }

   public final void updateState() {
      this.factor5 = 1.0F;
      this.factor = 0.0F;
      this.factor8 = 0.0F;
      this.factor3 = 0.0F;
      this.factor6 = 1.0F;
      this.factor9 = 0.0F;
      this.factor2 = 0.0F;
      this.factor7 = 0.0F;
      this.factor4 = 1.0F;
   }

   public final float getFactor() {
      return this.factor;
   }

   public final void applyFloat3(float factor10) {
      float factor11 = InternalFloatArrayEntry.computeFactorByFloat(factor10);
      float factor12 = InternalFloatArrayEntry.computeFactor(factor10);
      this.factor5 = factor12;
      this.factor = 0.0F;
      this.factor8 = factor11;
      this.factor3 = 0.0F;
      this.factor6 = 1.0F;
      this.factor9 = 0.0F;
      this.factor2 = -factor11;
      this.factor7 = 0.0F;
      this.factor4 = factor12;
   }

   public final float getFactor9() {
      return this.factor9;
   }

   public final void setFactor5(float factor10) {
      this.factor5 = factor10;
   }

   public final float getFactor6() {
      return this.factor6;
   }

   public final void setFactor7(float factor10) {
      this.factor7 = factor10;
   }

   public final void applyFloat(float factor10) {
      this.factor5 *= factor10;
      this.factor *= factor10;
      this.factor8 *= factor10;
      this.factor3 *= factor10;
      this.factor6 *= factor10;
      this.factor9 *= factor10;
      this.factor2 *= factor10;
      this.factor7 *= factor10;
      this.factor4 *= factor10;
   }

   public final void applyFloat2(float factor10) {
      float factor11 = InternalFloatArrayEntry.computeFactorByFloat(factor10);
      float factor12 = InternalFloatArrayEntry.computeFactor(factor10);
      this.factor5 = 1.0F;
      this.factor = 0.0F;
      this.factor8 = 0.0F;
      this.factor3 = 0.0F;
      this.factor6 = factor12;
      this.factor9 = -factor11;
      this.factor2 = 0.0F;
      this.factor7 = factor11;
      this.factor4 = factor12;
   }

   public final void applyFloatByFloat(float factor10) {
      float factor11 = InternalFloatArrayEntry.computeFactorByFloat(factor10);
      float factor12 = InternalFloatArrayEntry.computeFactor(factor10);
      this.factor5 = factor12;
      this.factor = -factor11;
      this.factor8 = 0.0F;
      this.factor3 = factor11;
      this.factor6 = factor12;
      this.factor9 = 0.0F;
      this.factor2 = 0.0F;
      this.factor7 = 0.0F;
      this.factor4 = 1.0F;
   }

   public EnhancedFloatEntry(EnhancedFloatEntry enhancedFloatEntry) {
      this.factor5 = enhancedFloatEntry.factor5;
      this.factor = enhancedFloatEntry.factor;
      this.factor8 = enhancedFloatEntry.factor8;
      this.factor3 = enhancedFloatEntry.factor3;
      this.factor6 = enhancedFloatEntry.factor6;
      this.factor9 = enhancedFloatEntry.factor9;
      this.factor2 = enhancedFloatEntry.factor2;
      this.factor7 = enhancedFloatEntry.factor7;
      this.factor4 = enhancedFloatEntry.factor4;
   }

   public EnhancedFloatEntry() {
      this.factor5 = 0.0F;
      this.factor = 0.0F;
      this.factor8 = 0.0F;
      this.factor3 = 0.0F;
      this.factor6 = 0.0F;
      this.factor9 = 0.0F;
      this.factor2 = 0.0F;
      this.factor7 = 0.0F;
      this.factor4 = 0.0F;
   }
}
