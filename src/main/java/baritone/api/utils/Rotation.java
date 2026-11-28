package baritone.api.utils;


public class Rotation {
   private final float yaw;
   private final float pitch;

   public Rotation subtract(Rotation rotation) {
      return new Rotation(this.yaw - rotation.yaw, this.pitch - rotation.pitch);
   }

   @Override
   public String toString() {
      return "Yaw: " + this.yaw + ", Pitch: " + this.pitch;
   }

   public static float clampPitch(float f) {
      return Math.max(-90.0F, Math.min(90.0F, f));
   }

   public Rotation withPitch(float f) {
      return new Rotation(this.yaw, f);
   }

   public Rotation(float f, float f2) {
      this.yaw = f;
      this.pitch = f2;
   }

   public float getPitch() {
      return this.pitch;
   }

   public boolean yawIsReallyClose(Rotation rotation) {
      float f = Math.abs(normalizeYaw(this.yaw) - normalizeYaw(rotation.yaw));
      return (double)f < 0.01 || (double)f > 359.99;
   }

   public Rotation clamp() {
      return new Rotation(this.yaw, clampPitch(this.pitch));
   }

   public Rotation normalizeAndClamp() {
      return new Rotation(normalizeYaw(this.yaw), clampPitch(this.pitch));
   }

   public Rotation add(Rotation rotation) {
      return new Rotation(this.yaw + rotation.yaw, this.pitch + rotation.pitch);
   }

   public Rotation normalize() {
      return new Rotation(normalizeYaw(this.yaw), this.pitch);
   }

   public float getYaw() {
      return this.yaw;
   }

   public boolean isReallyCloseTo(Rotation rotation) {
      return this.yawIsReallyClose(rotation) && (double)Math.abs(this.pitch - rotation.pitch) < 0.01;
   }

   public static float normalizeYaw(float f) {
      float f2 = f % 360.0F;
      if (f2 < -180.0F) {
         f2 += 360.0F;
      }

      if (f2 > 180.0F) {
         f2 -= 360.0F;
      }

      return f2;
   }
}
