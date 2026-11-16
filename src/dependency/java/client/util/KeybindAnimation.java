package client.util;

import client.setting.EasingFunction;
import net.minecraft.util.MathHelper;

public class KeybindAnimation {
   public EasingFunction easingFunction = EasingFunction.easeInOutBack;
   public int count2;
   public float factor3;
   public int count;
   public float factor5;
   public float factor4;
   public float factor;
   public float factor2;
   public long timestamp;
   public float factor6;

   public float getFloat() {
      float factor7 = this.factor - this.factor6;
      this.factor5 = (float)((int)Math.round((double)this.factor6 + (double)factor7 * this.getDouble3()));
      return this.factor5;
   }

   public static String[] getStringArray(boolean flag) {
      int count3 = flag ? EasingFunction.values().length - 1 : EasingFunction.values().length;
      String[] textArray = new String[count3];

      for (int index = 0; index < count3; index++) {
         textArray[index] = EasingFunction.values()[index].name();
      }

      return textArray;
   }

   private static double computeValue(double offset) {
      double offset2 = 7.5625;
      double offset3 = 2.75;
      if (offset < 1.0 / offset3) {
         return offset2 * offset * offset;
      } else if (offset < 2.0 / offset3) {
         double offset4;
         return offset2 * (offset4 = offset - 1.5 / offset3) * offset4 + 0.75;
      } else {
         double offset5;
         double offset6;
         return offset < 2.5 / offset3 ? offset2 * (offset5 = offset - 2.25 / offset3) * offset5 + 0.9375 : offset2 * (offset6 = offset - 2.625 / offset3) * offset6 + 0.984375;
      }
   }

   public static double getCoordinate(EasingFunction easingFunction2, double offset) {
      switch (VirtualIntArrayEntry.countArray[easingFunction2.ordinal()]) {
         case 1:
            return 1.0 - Math.cos(offset * Math.PI / 2.0);
         case 2:
            return Math.sin(offset * Math.PI / 2.0);
         case 3:
            return -(Math.cos(Math.PI * offset) - 1.0) / 2.0;
         case 4:
            return offset * offset;
         case 5:
            return 1.0 - (1.0 - offset) * (1.0 - offset);
         case 6:
            return offset < 0.5 ? 2.0 * offset * offset : 1.0 - Math.pow(-2.0 * offset + 2.0, 2.0) / 2.0;
         case 7:
            return offset * offset * offset;
         case 8:
            return 1.0 - Math.pow(1.0 - offset, 3.0);
         case 9:
            return offset < 0.5 ? 4.0 * offset * offset * offset : 1.0 - Math.pow(-2.0 * offset + 2.0, 3.0) / 2.0;
         case 10:
            return offset * offset * offset * offset;
         case 11:
            return 1.0 - Math.pow(1.0 - offset, 4.0);
         case 12:
            return offset < 0.5 ? 8.0 * offset * offset * offset * offset : 1.0 - Math.pow(-2.0 * offset + 2.0, 4.0) / 2.0;
         case 13:
            return offset * offset * offset * offset * offset;
         case 14:
            return 1.0 - Math.pow(1.0 - offset, 5.0);
         case 15:
            return offset < 0.5 ? 16.0 * offset * offset * offset * offset * offset : 1.0 - Math.pow(-2.0 * offset + 2.0, 5.0) / 2.0;
         case 16:
            return offset == 0.0 ? 0.0 : Math.pow(2.0, 10.0 * offset - 10.0);
         case 17:
            return offset == 1.0 ? 1.0 : 1.0 - Math.pow(2.0, -10.0 * offset);
         case 18:
            return offset == 0.0
               ? 0.0
               : (offset == 1.0 ? 1.0 : (offset < 0.5 ? Math.pow(2.0, 20.0 * offset - 10.0) / 2.0 : (2.0 - Math.pow(2.0, -20.0 * offset + 10.0)) / 2.0));
         case 19:
            return 1.0 - Math.sqrt(1.0 - Math.pow(offset, 2.0));
         case 20:
            return Math.sqrt(1.0 - Math.pow(offset - 1.0, 2.0));
         case 21:
            return offset < 0.5 ? (1.0 - Math.sqrt(1.0 - Math.pow(2.0 * offset, 2.0))) / 2.0 : (Math.sqrt(1.0 - Math.pow(-2.0 * offset + 2.0, 2.0)) + 1.0) / 2.0;
         case 22:
            double offset2 = 1.70158;
            return (offset2 + 1.0) * offset * offset * offset - offset2 * offset * offset;
         case 23:
            double offset3 = 1.70158;
            double offset4 = offset3 + 1.0;
            return 1.0 + offset4 * Math.pow(offset - 1.0, 3.0) + offset3 * Math.pow(offset - 1.0, 2.0);
         case 24:
            double offset5 = 1.70158 * 1.525;
            double offset6 = Math.pow(2.0 * offset, 2.0) * ((offset5 + 1.0) * 2.0 * offset - offset5) / 2.0;
            double offset7 = (Math.pow(2.0 * offset - 2.0, 2.0) * ((offset5 + 1.0) * (offset * 2.0 - 2.0) + offset5) + 2.0) / 2.0;
            return offset < 0.5 ? offset6 : offset7;
         case 25:
            double offset8 = Math.PI * 2.0 / 3.0;
            return offset == 0.0 ? 0.0 : (offset == 1.0 ? 1.0 : -Math.pow(2.0, 10.0 * offset - 10.0) * Math.sin((offset * 10.0 - 10.75) * offset8));
         case 26:
            double offset9 = Math.PI * 2.0 / 3.0;
            return offset == 0.0 ? 0.0 : (offset == 1.0 ? 1.0 : Math.pow(2.0, -10.0 * offset) * Math.sin((offset * 10.0 - 0.75) * offset9) + 1.0);
         case 27:
            double offset10 = Math.PI * 4.0 / 9.0;
            double offset11 = Math.sin((20.0 * offset - 11.125) * offset10);
            return offset == 0.0
               ? 0.0
               : (
                  offset == 1.0
                     ? 1.0
                     : (offset < 0.5 ? -(Math.pow(2.0, 20.0 * offset - 10.0) * offset11) / 2.0 : Math.pow(2.0, -20.0 * offset + 10.0) * offset11 / 2.0 + 1.0)
               );
         case 28:
            return 1.0 - computeValue(1.0 - offset);
         case 29:
            return computeValue(offset);
         case 30:
            return offset < 0.5 ? (1.0 - computeValue(1.0 - 2.0 * offset)) / 2.0 : (1.0 + computeValue(2.0 * offset - 1.0)) / 2.0;
         case 31:
            return offset;
         case 32:
            return 1.0;
         default:
            return 0.0;
      }
   }

   public static EasingFunction getEasingFunction(String text) {
      for (EasingFunction easingFunction2 : EasingFunction.values()) {
         if (easingFunction2.name().equalsIgnoreCase(text)) {
            return easingFunction2;
         }
      }

      System.err.println("No valid AnimationType!");
      return EasingFunction.easeInBack;
   }

   public void updateEasingFunction(int count3, EasingFunction easingFunction2, int count4, boolean flag) {
      int count5 = (int)(System.currentTimeMillis() - this.timestamp);
      if (count5 < this.count && !flag) {
         double offset = (double)count5 / (double)this.count;
         this.timestamp = (long)((double)System.currentTimeMillis() - (1.0 - offset) * (double)count3);
      } else {
         this.timestamp = System.currentTimeMillis();
      }

      this.count = count3;
      this.easingFunction = easingFunction2;
      this.count2 = count4;
   }

   public double getDouble2() {
      return MathHelper.getDouble7((double)Math.abs(this.timestamp - System.currentTimeMillis()) / (double)this.count, 0.0, 1.0);
   }

   public void processFloat(float posX, float posX2, float posY, float posZ) {
      this.factor6 = posX;
      this.factor2 = posX2;
      this.factor = posY;
      this.factor4 = posZ;
   }

   public float getFloat2() {
      float factor7 = this.factor4 - this.factor2;
      this.factor3 = (float)((int)Math.round((double)this.factor2 + (double)factor7 * this.getDouble3()));
      return this.factor3;
   }

   public double getDouble3() {
      return this.count2 == 1 ? this.getValue() : 1.0 - this.getValue();
   }

   private double getValue() {
      return getCoordinate(
         this.easingFunction, MathHelper.getDouble7((double)Math.abs(this.timestamp - System.currentTimeMillis()) / (double)this.count, 0.0, 1.0)
      );
   }

   public KeybindAnimation(float posX, float posX2, float posY, float posZ) {
      this.factor5 = posX;
      this.factor3 = posX2;
      this.factor6 = posX;
      this.factor2 = posX2;
      this.factor = posY;
      this.factor4 = posZ;
      this.timestamp = System.currentTimeMillis();
   }
}
