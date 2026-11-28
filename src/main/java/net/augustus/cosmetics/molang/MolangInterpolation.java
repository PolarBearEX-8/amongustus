package net.augustus.cosmetics.molang;


public enum MolangInterpolation {
   LINEAR("linear") {
      @Override
      public float interpolate(float f, float f2, float f3) {
         return MolangInterpolations.lerp(f, f2, f3);
      }
   },
   QUAD_IN("quad_in") {
      @Override
      public float interpolate(float f, float f2, float f3) {
         return f + (f2 - f) * f3 * f3;
      }
   },
   QUAD_OUT("quad_out") {
      @Override
      public float interpolate(float f, float f2, float f3) {
         return f - (f2 - f) * f3 * (f3 - 2.0F);
      }
   },
   QUAD_INOUT("quad_inout") {
      @Override
      public float interpolate(float f, float f2, float f3) {
         f3 *= 2.0F;
         if (f3 < 1.0F) {
            return f + (f2 - f) / 2.0F * f3 * f3;
         } else {
            f3--;
            return f - (f2 - f) / 2.0F * (f3 * (f3 - 2.0F) - 1.0F);
         }
      }
   },
   CUBIC_IN("cubic_in") {
      @Override
      public float interpolate(float f, float f2, float f3) {
         return f + (f2 - f) * f3 * f3 * f3;
      }
   },
   CUBIC_OUT("cubic_out") {
      @Override
      public float interpolate(float f, float f2, float f3) {
         f3--;
         return f + (f2 - f) * (f3 * f3 * f3 + 1.0F);
      }
   },
   CUBIC_INOUT("cubic_inout") {
      @Override
      public float interpolate(float f, float f2, float f3) {
         f3 *= 2.0F;
         if (f3 < 1.0F) {
            return f + (f2 - f) / 2.0F * f3 * f3 * f3;
         } else {
            f3 -= 2.0F;
            return f + (f2 - f) / 2.0F * (f3 * f3 * f3 + 2.0F);
         }
      }
   },
   EXP_IN("exp_in") {
      @Override
      public float interpolate(float f, float f2, float f3) {
         return f + (f2 - f) * (float)Math.pow(2.0, (double)(10.0F * (f3 - 1.0F)));
      }
   },
   EXP_OUT("exp_out") {
      @Override
      public float interpolate(float f, float f2, float f3) {
         return f + (f2 - f) * (float)(-Math.pow(2.0, (double)(-10.0F * f3)) + 1.0);
      }
   },
   EXP_INOUT("exp_inout") {
      @Override
      public float interpolate(float f, float f2, float f3) {
         if (f3 == 0.0F) {
            return f;
         } else if (f3 == 1.0F) {
            return f2;
         } else {
            f3 *= 2.0F;
            if (f3 < 1.0F) {
               return f + (f2 - f) / 2.0F * (float)Math.pow(2.0, (double)(10.0F * (f3 - 1.0F)));
            } else {
               f3--;
               return f + (f2 - f) / 2.0F * (float)(-Math.pow(2.0, (double)(-10.0F * f3)) + 2.0);
            }
         }
      }
   };

   public final String key;

private MolangInterpolation(String str, Object obj) {
      this(str);
   }

   public String getName() {
      return "mclib.interpolations." + this.key;
   }

   private MolangInterpolation(String str) {
      this.key = str;
   }


   public abstract float interpolate(float f, float f2, float f3);
}
