package net.augustus.utils.rotation;

import net.augustus.utils.TimeHelper;

public class Rotation {
   public float pitch;
   public float yaw;

   public Rotation() {
      this.yaw = 0.0F;
      this.pitch = 0.0F;
   }

   @Override
   public String toString() {
      return "Yaw: " + this.yaw + "   Pitch: " + this.pitch;
   }

   public Rotation(Rotation rotation) {
      this.yaw = rotation.yaw;
      this.pitch = rotation.pitch;
   }

   public Rotation subtract(Rotation rotation) {
      return new Rotation(this.yaw - rotation.yaw, this.pitch - rotation.pitch);
   }

   public Rotation(float f, float f2) {
      this.yaw = f;
      this.pitch = f2;
   }

   public boolean isWithinBounds(Rotation rotation, Rotation rotation2) {
      return this.yaw >= rotation.yaw && this.pitch >= rotation.pitch && this.yaw <= rotation2.yaw && this.pitch <= rotation2.pitch;
   }

   public double manhattanDistance(Rotation rotation) {
      float f = Math.abs(this.yaw - rotation.yaw);
      return (double)(Math.abs(this.pitch - rotation.pitch) + f);
   }

   public float[] toArray() {
      return new float[]{this.yaw, this.pitch};
   }

   public Rotation(float[] floats) {
      this.yaw = floats[0];
      this.pitch = floats[1];
   }

   public Rotation absDelta(Rotation rotation) {
      float f = Math.abs(this.yaw - rotation.yaw);
      float f2 = Math.abs(this.pitch - rotation.pitch);
      return new Rotation(f, f2);
   }

   public boolean equalsRotation(Rotation rotation) {
      return rotation != null && rotation.yaw == this.yaw && rotation.pitch == this.pitch;
   }

}
