package client.util;

import client.rotation.Rotation;

public class RotationAngleData {
   public int count = 0;
   public final float factor3;
   public final float factor;
   public final float factor2;

   public static double computeValue(RotationAngleData rotationAngleData, Rotation rotation, Rotation rotation2) {
      return rotationAngleData.computeValue(rotation, rotation2);
   }

   public boolean checkDouble(double offset, double offset2) {
      return (double)Math.abs(this.getFactor()) > offset && (double)Math.abs(this.getFactor2()) > offset2;
   }

   public int getCount() {
      return this.count;
   }

   public float getFactor() {
      return this.factor;
   }

   private double computeValue(Rotation rotation, Rotation rotation2) {
      float factor4 = rotation.yaw - rotation2.yaw;
      float factor5 = rotation.pitch - rotation2.pitch;
      return Math.sqrt((double)(factor5 * factor5 + factor4 * factor4));
   }

   public double computeValue(float factor4, float factor5) {
      return (double)(this.count * 10);
   }

   public static int computeCount(RotationAngleData rotationAngleData) {
      return rotationAngleData.count++;
   }

   public float computeFactor(Rotation rotation, Rotation rotation2, Rotation rotation3) {
      return (float)SimulatedPlayer.getCoordinate(new Rotation(rotation3.yaw + this.getFactor(), rotation3.pitch + this.getFactor2()), rotation, rotation2);
   }

   public boolean checkFloat(float factor4) {
      return this.factor3 < factor4;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public void setCount(int count2) {
      this.count = count2;
   }

   public RotationAngleData(float posX, float posY, float posZ) {
      this.factor = posX;
      this.factor2 = posY;
      this.factor3 = posZ;
   }
}
