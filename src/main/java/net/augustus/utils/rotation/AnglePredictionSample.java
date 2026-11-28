package net.augustus.utils.rotation;

import net.minecraft.util.MathHelper;

public class AnglePredictionSample {
   public double angularSlope;
   public double angularIntercept;
   public double baseAngle;
   public int predictionTicks;
   public double predictedAngle;

   public AnglePredictionSample(double d, double d2, double d3, double d4, int n) {
      this.angularSlope = d;
      this.angularIntercept = d2;
      this.predictedAngle = MathHelper.wrapAngleTo180_double(d3);
      this.baseAngle = d4;
      this.predictionTicks = n;
   }
}
