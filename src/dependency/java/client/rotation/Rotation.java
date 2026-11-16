package client.rotation;

import client.util.TimeHelper;

public class Rotation {
   public float yaw;
   public float pitch;

   public Rotation getRotation(Rotation rotation) {
      float factor = Math.abs(this.yaw - rotation.yaw);
      float factor2 = Math.abs(this.pitch - rotation.pitch);
      return new Rotation(factor, factor2);
   }

   public boolean checkRotation(Rotation rotation) {
      return rotation != null && rotation.yaw == this.yaw && rotation.pitch == this.pitch;
   }

   public double computeValue(Rotation rotation) {
      float factor = Math.abs(this.yaw - rotation.yaw);
      return (double)(Math.abs(this.pitch - rotation.pitch) + factor);
   }

   public float[] getFloatArray() {
      return new float[]{this.yaw, this.pitch};
   }

   public Rotation resolveRotation(Rotation rotation) {
      return new Rotation(this.yaw - rotation.yaw, this.pitch - rotation.pitch);
   }

   public Rotation(float factor, float factor2) {
      this.yaw = factor;
      this.pitch = factor2;
   }

   public Rotation() {
      this.yaw = 0.0F;
      this.pitch = 0.0F;
   }

   public Rotation(Rotation rotation) {
      this.yaw = rotation.yaw;
      this.pitch = rotation.pitch;
   }

   public Rotation(float[] factorArray) {
      this.yaw = factorArray[0];
      this.pitch = factorArray[1];
   }

   @Override
   public String toString() {
      return "Yaw: " + this.yaw + "   Pitch: " + this.pitch;
   }
}
