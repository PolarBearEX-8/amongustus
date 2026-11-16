package client.util;

import net.minecraft.util.EnumFacing;

public class ActiveFloatEntry {
   public float factor5 = Float.MAX_VALUE;
   public float factor6 = Float.MAX_VALUE;
   public float factor3 = Float.MAX_VALUE;
   public float factor = -Float.MAX_VALUE;
   public float factor2 = -Float.MAX_VALUE;
   public float factor4 = -Float.MAX_VALUE;

   public boolean matchesEnumFacing(EnumFacing enumFacing) {
      float factor7;
      float factor8;
      float factor9;
      switch (ComplexIntArrayEntry.countArray[enumFacing.ordinal()]) {
         case 1:
            factor7 = this.getFactor6();
            factor8 = this.getFactor2();
            factor9 = 0.0F;
            break;
         case 2:
            factor7 = this.getFactor6();
            factor8 = this.getFactor2();
            factor9 = 1.0F;
            break;
         case 3:
            factor7 = this.getFactor3();
            factor8 = this.getFactor4();
            factor9 = 0.0F;
            break;
         case 4:
            factor7 = this.getFactor3();
            factor8 = this.getFactor4();
            factor9 = 1.0F;
            break;
         case 5:
            factor7 = this.getFactor5();
            factor8 = this.getFactor();
            factor9 = 0.0F;
            break;
         case 6:
            factor7 = this.getFactor5();
            factor8 = this.getFactor();
            factor9 = 1.0F;
            break;
         default:
            return false;
      }

      return factor7 == factor9 && factor8 == factor9;
   }

   public float getFactor() {
      return this.factor;
   }

   public float getFactor4() {
      return this.factor4;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public float getFactor6() {
      return this.factor6;
   }

   public float getFactor3() {
      return this.factor3;
   }

   public boolean matchesEnumFacingByEnumFacing(EnumFacing enumFacing) {
      float factor7;
      float factor8;
      float factor9;
      float factor10;
      switch (ComplexIntArrayEntry.countArray[enumFacing.ordinal()]) {
         case 1:
         case 2:
            factor7 = this.getFactor5();
            factor8 = this.getFactor();
            factor9 = this.getFactor3();
            factor10 = this.getFactor4();
            break;
         case 3:
         case 4:
            factor7 = this.getFactor5();
            factor8 = this.getFactor();
            factor9 = this.getFactor6();
            factor10 = this.getFactor2();
            break;
         case 5:
         case 6:
            factor7 = this.getFactor6();
            factor8 = this.getFactor2();
            factor9 = this.getFactor3();
            factor10 = this.getFactor4();
            break;
         default:
            return false;
      }

      return factor7 == 0.0F && factor8 == 1.0F && factor9 == 0.0F && factor10 == 1.0F;
   }

   public float getFactor5() {
      return this.factor5;
   }

   public ActiveFloatEntry(int[] countArray) {
      int count = countArray.length / 4;

      for (int index = 0; index < 4; index++) {
         int count2 = index * count;
         float factor7 = Float.intBitsToFloat(countArray[count2 + 0]);
         float factor8 = Float.intBitsToFloat(countArray[count2 + 1]);
         float factor9 = Float.intBitsToFloat(countArray[count2 + 2]);
         if (this.factor5 > factor7) {
            this.factor5 = factor7;
         }

         if (this.factor6 > factor8) {
            this.factor6 = factor8;
         }

         if (this.factor3 > factor9) {
            this.factor3 = factor9;
         }

         if (this.factor < factor7) {
            this.factor = factor7;
         }

         if (this.factor2 < factor8) {
            this.factor2 = factor8;
         }

         if (this.factor4 < factor9) {
            this.factor4 = factor9;
         }
      }
   }
}
