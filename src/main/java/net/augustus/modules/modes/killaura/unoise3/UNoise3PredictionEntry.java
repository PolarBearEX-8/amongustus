package net.augustus.modules.modes.killaura.unoise3;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class UNoise3PredictionEntry {
   public boolean tooClose;
   public double angularAcceleration;
   public int tickSpan;
   public boolean filtered = false;
   public Vec3 predictedPlayerPos;
   public Vec3 predictedTargetPos;
   public double prevAngle;
   public double predictedAngle;
   public double angularVelocity;

   public UNoise3PredictionEntry(Vec3 vec, Vec3 vec2, double d, double d2, double d3, double d4, int n) {
      this.predictedPlayerPos = vec;
      this.predictedTargetPos = vec2;
      this.angularVelocity = d;
      this.angularAcceleration = d2;
      this.predictedAngle = MathHelper.wrapAngleTo180_double(d3);
      this.prevAngle = d4;
      this.tickSpan = n;
   }
}
