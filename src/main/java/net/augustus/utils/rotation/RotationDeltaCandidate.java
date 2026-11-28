package net.augustus.utils.rotation;

import net.augustus.utils.TimeHelper;

public class RotationDeltaCandidate {
   private final float pitchDelta;
   private final float deviation;
   private int hitCount = 0;
   private final float yawDelta;

   public static int incrementHitCount(RotationDeltaCandidate rotationDeltaCandidate) {
      return rotationDeltaCandidate.hitCount++;
   }

   public static double computeRotationDistance(RotationDeltaCandidate rotationDeltaCandidate, Rotation rotation, Rotation rotation2) {
      return rotationDeltaCandidate.distanceBetween(rotation, rotation2);
   }

   private double distanceBetween(Rotation rotation, Rotation rotation2) {
      float f = rotation.yaw - rotation2.yaw;
      float f2 = rotation.pitch - rotation2.pitch;
      return Math.sqrt((double)(f2 * f2 + f * f));
   }

   public float getYawDelta() {
      return this.yawDelta;
   }

   public void setHitCount(int n) {
      this.hitCount = n;
   }

   public float getPitchDelta() {
      return this.pitchDelta;
   }

   public int getHitCount() {
      return this.hitCount;
   }

   public boolean exceedsThreshold(double d, double d2) {
      return (double)Math.abs(this.getYawDelta()) > d && (double)Math.abs(this.getPitchDelta()) > d2;
   }

   public boolean isDeviationBelow(float f) {
      return this.deviation < f;
   }

   public RotationDeltaCandidate(float f, float f2, float f3) {
      this.yawDelta = f;
      this.pitchDelta = f2;
      this.deviation = f3;
   }

   public float computeDistanceToSegment(Rotation rotation, Rotation rotation2, Rotation rotation3) {
      return (float)RotationUtil.computeAngleBetweenRotations(new Rotation(rotation3.yaw + this.getYawDelta(), rotation3.pitch + this.getPitchDelta()), rotation, rotation2);
   }

   public double computeHitScore(float f, float f2) {
      return (double)(this.hitCount * 10);
   }
}
