package net.optifine.util;


public class SmoothFloat {
   private float valueLast;
   private float timeFadeUpSec;
   private float timeFadeDownSec;
   private long timeLastMs;

   public float getSmoothValue(float f, float f2, float f3) {
      this.timeFadeUpSec = f2;
      this.timeFadeDownSec = f3;
      return this.getSmoothValue(f);
   }

   public float getValueLast() {
      return this.valueLast;
   }

   public static float getSmoothValue(float f, float f2, float f3, float f4) {
      if (f3 <= 0.0F) {
         return f;
      } else {
         float f5 = f2 - f;
         float f6;
         if (f4 > 0.0F && f3 < f4 && Math.abs(f5) > 1.0E-6F) {
            float f7 = f4 / f3;
            float f8 = 4.61F;
            float f9 = 0.13F;
            float f10 = 10.0F;
            float f11 = f8 - 1.0F / (f9 + f7 / f10);
            float f12 = f3 / f4 * f11;
            f12 = NumUtils.limit(f12, 0.0F, 1.0F);
            f6 = f + f5 * f12;
         } else {
            f6 = f2;
         }

         return f6;
      }
   }

   public float getTimeFadeDownSec() {
      return this.timeFadeDownSec;
   }

   public long getTimeLastMs() {
      return this.timeLastMs;
   }

   public SmoothFloat(float f, float f2) {
      this(f, f2, f2);
   }

   public SmoothFloat(float f, float f2, float f3) {
      this.valueLast = f;
      this.timeFadeUpSec = f2;
      this.timeFadeDownSec = f3;
      this.timeLastMs = System.currentTimeMillis();
   }

   public float getSmoothValue(float f) {
      long l = System.currentTimeMillis();
      float valueLast2 = this.valueLast;
      float smoothValue = getSmoothValue(valueLast2, f, (float)(l - this.timeLastMs) / 1000.0F, f >= valueLast2 ? this.timeFadeUpSec : this.timeFadeDownSec);
      this.valueLast = smoothValue;
      this.timeLastMs = l;
      return smoothValue;
   }

   public float getTimeFadeUpSec() {
      return this.timeFadeUpSec;
   }
}
