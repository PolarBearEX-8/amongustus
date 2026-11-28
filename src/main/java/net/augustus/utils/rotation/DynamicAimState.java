package net.augustus.utils.rotation;

import net.augustus.utils.math.Vec2d;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class DynamicAimState {
   private Vec3 targetPos;
   private Vec2d angularVelocity;
   private double lastYawError;
   private boolean trackingLocked = false;
   private Vec3 eyePos;
   private Vec2d previousAngularVelocity;
   private double pitch;
   private boolean inRecovery;
   private double yaw;

   public DynamicAimState(Vec3 vec, Vec3 vec2, Vec2d vec2d, Vec2d vec2d2, double d, double d2) {
      this.eyePos = vec;
      this.targetPos = vec2;
      this.previousAngularVelocity = vec2d;
      this.angularVelocity = vec2d2;
      this.yaw = MathHelper.wrapAngleTo180_double(d);
      this.pitch = d2;
   }

   public Vec2d getPreviousAngularVelocity() {
      return this.previousAngularVelocity;
   }

   public double getLastYawError() {
      return this.lastYawError;
   }

   public double getPitch() {
      return this.pitch;
   }

   public void setAngles(double d, double d2) {
      this.yaw = MathHelper.wrapAngleTo180_double(d);
      this.pitch = d2;
   }

   public Vec3 getEyePos() {
      return this.eyePos;
   }

   public void setInRecovery(boolean flag) {
      this.inRecovery = flag;
   }

   public void setTrackingLocked(boolean flag) {
      this.trackingLocked = flag;
   }

   public boolean isTrackingLocked() {
      return this.trackingLocked;
   }

   public Vec3 getTargetPos() {
      return this.targetPos;
   }

   public Vec2d getAngularVelocity() {
      return this.angularVelocity;
   }

   public void setLastYawError(double d) {
      this.lastYawError = d;
   }

   public double getYaw() {
      return this.yaw;
   }

   public boolean isInRecovery() {
      return this.inRecovery;
   }

   public void setAngularVelocity(Vec2d vec2d) {
      this.angularVelocity = vec2d;
   }
}
