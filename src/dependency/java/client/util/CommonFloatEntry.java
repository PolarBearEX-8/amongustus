package client.util;

public class CommonFloatEntry {
   public float factor2;
   public float factor16;
   public float factor10;
   public float factor;
   public float factor8;
   public float factor5;
   public float factor13;
   public float factor14;
   public float factor7;
   public float factor9;
   public float factor4;
   public float factor6;
   public float factor3;
   public float factor12;
   public float factor15;
   public float factor11;

   public final void setFactor8(float factor17) {
      this.factor8 = factor17;
   }

   public final float getFactor16() {
      return this.factor16;
   }

   public final float getFactor5() {
      return this.factor5;
   }

   public final void setFactor7(float factor17) {
      this.factor7 = factor17;
   }

   public final void setFactor5(float factor17) {
      this.factor5 = factor17;
   }

   public final float getFactor6() {
      return this.factor6;
   }

   public final void setFactor14(float factor17) {
      this.factor14 = factor17;
   }

   public final float getFactor4() {
      return this.factor4;
   }

   public final float getFactor10() {
      return this.factor10;
   }

   public final void setFactor13(float factor17) {
      this.factor13 = factor17;
   }

   public final float getFactor7() {
      return this.factor7;
   }

   public final void setFactor2(float factor17) {
      this.factor2 = factor17;
   }

   public final float getFactor12() {
      return this.factor12;
   }

   public final void setFactor9(float factor17) {
      this.factor9 = factor17;
   }

   public final void applyFloat5(float factor17) {
      this.factor7 *= factor17;
      this.factor5 *= factor17;
      this.factor10 *= factor17;
      this.factor *= factor17;
      this.factor11 *= factor17;
      this.factor16 *= factor17;
      this.factor15 *= factor17;
      this.factor9 *= factor17;
      this.factor8 *= factor17;
      this.factor14 *= factor17;
      this.factor2 *= factor17;
      this.factor12 *= factor17;
      this.factor3 *= factor17;
      this.factor6 *= factor17;
      this.factor4 *= factor17;
      this.factor13 *= factor17;
   }

   public final float getFactor() {
      return this.factor;
   }

   public final void applyFloat6(float factor17) {
      float factor18 = InternalFloatArrayEntry.computeFactorByFloat(factor17);
      float factor19 = InternalFloatArrayEntry.computeFactor(factor17);
      this.factor7 *= factor19;
      this.factor5 *= 0.0F;
      this.factor10 *= factor18;
      this.factor *= 0.0F;
      this.factor11 *= 0.0F;
      this.factor16 *= 1.0F;
      this.factor15 *= 0.0F;
      this.factor9 *= 0.0F;
      this.factor8 *= -factor18;
      this.factor14 *= 0.0F;
      this.factor2 *= factor19;
      this.factor12 *= 0.0F;
      this.factor3 *= 0.0F;
      this.factor6 *= 0.0F;
      this.factor4 *= 0.0F;
      this.factor13 *= 1.0F;
   }

   public final float getFactor8() {
      return this.factor8;
   }

   public final void applyCommonFloatEntry(CommonFloatEntry commonFloatEntry) {
      float factor17 = this.factor7 * commonFloatEntry.factor7 + this.factor5 * commonFloatEntry.factor11 + this.factor10 * commonFloatEntry.factor8 + this.factor * commonFloatEntry.factor3;
      float factor18 = this.factor7 * commonFloatEntry.factor5 + this.factor5 * commonFloatEntry.factor16 + this.factor10 * commonFloatEntry.factor14 + this.factor * commonFloatEntry.factor6;
      float factor19 = this.factor7 * commonFloatEntry.factor10 + this.factor5 * commonFloatEntry.factor15 + this.factor10 * commonFloatEntry.factor2 + this.factor * commonFloatEntry.factor4;
      float factor20 = this.factor7 * commonFloatEntry.factor + this.factor5 * commonFloatEntry.factor9 + this.factor10 * commonFloatEntry.factor12 + this.factor * commonFloatEntry.factor13;
      float factor21 = this.factor11 * commonFloatEntry.factor7 + this.factor16 * commonFloatEntry.factor11 + this.factor15 * commonFloatEntry.factor8 + this.factor9 * commonFloatEntry.factor3;
      float factor22 = this.factor11 * commonFloatEntry.factor5 + this.factor16 * commonFloatEntry.factor16 + this.factor15 * commonFloatEntry.factor14 + this.factor9 * commonFloatEntry.factor6;
      float factor23 = this.factor11 * commonFloatEntry.factor10 + this.factor16 * commonFloatEntry.factor15 + this.factor15 * commonFloatEntry.factor2 + this.factor9 * commonFloatEntry.factor4;
      float factor24 = this.factor11 * commonFloatEntry.factor + this.factor16 * commonFloatEntry.factor9 + this.factor15 * commonFloatEntry.factor12 + this.factor9 * commonFloatEntry.factor13;
      float factor25 = this.factor8 * commonFloatEntry.factor7 + this.factor14 * commonFloatEntry.factor11 + this.factor2 * commonFloatEntry.factor8 + this.factor12 * commonFloatEntry.factor3;
      float factor26 = this.factor8 * commonFloatEntry.factor5 + this.factor14 * commonFloatEntry.factor16 + this.factor2 * commonFloatEntry.factor14 + this.factor12 * commonFloatEntry.factor6;
      float factor27 = this.factor8 * commonFloatEntry.factor10 + this.factor14 * commonFloatEntry.factor15 + this.factor2 * commonFloatEntry.factor2 + this.factor12 * commonFloatEntry.factor4;
      float factor28 = this.factor8 * commonFloatEntry.factor + this.factor14 * commonFloatEntry.factor9 + this.factor2 * commonFloatEntry.factor12 + this.factor12 * commonFloatEntry.factor13;
      float factor29 = this.factor3 * commonFloatEntry.factor7 + this.factor6 * commonFloatEntry.factor11 + this.factor4 * commonFloatEntry.factor8 + this.factor13 * commonFloatEntry.factor3;
      float factor30 = this.factor3 * commonFloatEntry.factor5 + this.factor6 * commonFloatEntry.factor16 + this.factor4 * commonFloatEntry.factor14 + this.factor13 * commonFloatEntry.factor6;
      float factor31 = this.factor3 * commonFloatEntry.factor10 + this.factor6 * commonFloatEntry.factor15 + this.factor4 * commonFloatEntry.factor2 + this.factor13 * commonFloatEntry.factor4;
      float factor32 = this.factor3 * commonFloatEntry.factor + this.factor6 * commonFloatEntry.factor9 + this.factor4 * commonFloatEntry.factor12 + this.factor13 * commonFloatEntry.factor13;
      this.factor7 = factor17;
      this.factor5 = factor18;
      this.factor10 = factor19;
      this.factor = factor20;
      this.factor11 = factor21;
      this.factor16 = factor22;
      this.factor15 = factor23;
      this.factor9 = factor24;
      this.factor8 = factor25;
      this.factor14 = factor26;
      this.factor2 = factor27;
      this.factor12 = factor28;
      this.factor3 = factor29;
      this.factor6 = factor30;
      this.factor4 = factor31;
      this.factor13 = factor32;
   }

   public final void updateState() {
      this.factor7 = 1.0F;
      this.factor5 = 0.0F;
      this.factor10 = 0.0F;
      this.factor = 0.0F;
      this.factor11 = 0.0F;
      this.factor16 = 1.0F;
      this.factor15 = 0.0F;
      this.factor9 = 0.0F;
      this.factor8 = 0.0F;
      this.factor14 = 0.0F;
      this.factor2 = 1.0F;
      this.factor12 = 0.0F;
      this.factor3 = 0.0F;
      this.factor6 = 0.0F;
      this.factor4 = 0.0F;
      this.factor13 = 1.0F;
   }

   public final float getFactor3() {
      return this.factor3;
   }

   public final void setFactor16(float factor17) {
      this.factor16 = factor17;
   }

   public final void applyFloat2(float factor17) {
      float factor18 = InternalFloatArrayEntry.computeFactorByFloat(factor17);
      float factor19 = InternalFloatArrayEntry.computeFactor(factor17);
      this.factor7 = 1.0F;
      this.factor5 = 0.0F;
      this.factor10 = 0.0F;
      this.factor = 0.0F;
      this.factor11 = 0.0F;
      this.factor16 = factor19;
      this.factor15 = -factor18;
      this.factor9 = 0.0F;
      this.factor8 = 0.0F;
      this.factor14 = factor18;
      this.factor2 = factor19;
      this.factor12 = 0.0F;
      this.factor3 = 0.0F;
      this.factor6 = 0.0F;
      this.factor4 = 0.0F;
      this.factor13 = 1.0F;
   }

   public final void setFactor15(float factor17) {
      this.factor15 = factor17;
   }

   public final float getFactor13() {
      return this.factor13;
   }

   public final float getFactor14() {
      return this.factor14;
   }

   public final float getFactor15() {
      return this.factor15;
   }

   public final float getFactor9() {
      return this.factor9;
   }

   public final float getFactor2() {
      return this.factor2;
   }

   public final float getFactor11() {
      return this.factor11;
   }

   public final void applyFloat3(float factor17) {
      float factor18 = InternalFloatArrayEntry.computeFactorByFloat(factor17);
      float factor19 = InternalFloatArrayEntry.computeFactor(factor17);
      this.factor7 = factor19;
      this.factor5 = 0.0F;
      this.factor10 = factor18;
      this.factor = 0.0F;
      this.factor11 = 0.0F;
      this.factor16 = 1.0F;
      this.factor15 = 0.0F;
      this.factor9 = 0.0F;
      this.factor8 = -factor18;
      this.factor14 = 0.0F;
      this.factor2 = factor19;
      this.factor12 = 0.0F;
      this.factor3 = 0.0F;
      this.factor6 = 0.0F;
      this.factor4 = 0.0F;
      this.factor13 = 1.0F;
   }

   public final void applyFloat4(float factor17) {
      float factor18 = InternalFloatArrayEntry.computeFactorByFloat(factor17);
      float factor19 = InternalFloatArrayEntry.computeFactor(factor17);
      this.factor7 *= factor19;
      this.factor5 *= -factor18;
      this.factor10 *= 0.0F;
      this.factor *= 0.0F;
      this.factor11 *= factor18;
      this.factor16 *= factor19;
      this.factor15 *= 0.0F;
      this.factor9 *= 0.0F;
      this.factor8 *= 0.0F;
      this.factor14 *= 0.0F;
      this.factor2 *= 1.0F;
      this.factor12 *= 0.0F;
      this.factor3 *= 0.0F;
      this.factor6 *= 0.0F;
      this.factor4 *= 0.0F;
      this.factor13 *= 1.0F;
   }

   public final void applyFloat(float factor17) {
      float factor18 = InternalFloatArrayEntry.computeFactorByFloat(factor17);
      float factor19 = InternalFloatArrayEntry.computeFactor(factor17);
      this.factor7 *= 1.0F;
      this.factor5 *= 0.0F;
      this.factor10 *= 0.0F;
      this.factor *= 0.0F;
      this.factor11 *= 0.0F;
      this.factor16 *= factor19;
      this.factor15 *= -factor18;
      this.factor9 *= 0.0F;
      this.factor8 *= 0.0F;
      this.factor14 *= factor18;
      this.factor2 *= factor19;
      this.factor12 *= 0.0F;
      this.factor3 *= 0.0F;
      this.factor6 *= 0.0F;
      this.factor4 *= 0.0F;
      this.factor13 *= 1.0F;
   }

   public final void applyFloatByFloat(float factor17) {
      float factor18 = InternalFloatArrayEntry.computeFactorByFloat(factor17);
      float factor19 = InternalFloatArrayEntry.computeFactor(factor17);
      this.factor7 = factor19;
      this.factor5 = -factor18;
      this.factor10 = 0.0F;
      this.factor = 0.0F;
      this.factor11 = factor18;
      this.factor16 = factor19;
      this.factor15 = 0.0F;
      this.factor9 = 0.0F;
      this.factor8 = 0.0F;
      this.factor14 = 0.0F;
      this.factor2 = 1.0F;
      this.factor12 = 0.0F;
      this.factor3 = 0.0F;
      this.factor6 = 0.0F;
      this.factor4 = 0.0F;
      this.factor13 = 1.0F;
   }

   public final void setFactor(float factor17) {
      this.factor = factor17;
   }

   public final void setFactor3(float factor17) {
      this.factor3 = factor17;
   }

   public final void setFactor11(float factor17) {
      this.factor11 = factor17;
   }

   public final void setFactor6(float factor17) {
      this.factor6 = factor17;
   }

   public final void setFactor12(float factor17) {
      this.factor12 = factor17;
   }

   public final void setFactor10(float factor17) {
      this.factor10 = factor17;
   }

   public final void setFactor4(float factor17) {
      this.factor4 = factor17;
   }

   public CommonFloatEntry(CommonFloatEntry commonFloatEntry) {
      this.factor7 = commonFloatEntry.factor7;
      this.factor5 = commonFloatEntry.factor5;
      this.factor10 = commonFloatEntry.factor10;
      this.factor = commonFloatEntry.factor;
      this.factor11 = commonFloatEntry.factor11;
      this.factor16 = commonFloatEntry.factor16;
      this.factor15 = commonFloatEntry.factor15;
      this.factor9 = commonFloatEntry.factor9;
      this.factor8 = commonFloatEntry.factor8;
      this.factor14 = commonFloatEntry.factor14;
      this.factor2 = commonFloatEntry.factor2;
      this.factor12 = commonFloatEntry.factor12;
      this.factor3 = commonFloatEntry.factor3;
      this.factor6 = commonFloatEntry.factor6;
      this.factor4 = commonFloatEntry.factor4;
      this.factor13 = commonFloatEntry.factor13;
   }

   public CommonFloatEntry() {
      this.factor7 = 0.0F;
      this.factor5 = 0.0F;
      this.factor10 = 0.0F;
      this.factor = 0.0F;
      this.factor11 = 0.0F;
      this.factor16 = 0.0F;
      this.factor15 = 0.0F;
      this.factor9 = 0.0F;
      this.factor8 = 0.0F;
      this.factor14 = 0.0F;
      this.factor2 = 0.0F;
      this.factor12 = 0.0F;
      this.factor3 = 0.0F;
      this.factor6 = 0.0F;
      this.factor4 = 0.0F;
      this.factor13 = 0.0F;
   }
}
