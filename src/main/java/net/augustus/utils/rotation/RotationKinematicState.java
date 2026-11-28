package net.augustus.utils.rotation;

import net.augustus.utils.math.Vec2d;
import net.augustus.utils.interfaces.MC;
import net.minecraft.util.MathHelper;

public class RotationKinematicState implements MC {
   private double yaw;
   private int createdTick;
   private double[] targetYawBounds;
   private Vec2d angularVelocity;
   private double pitch;

   public int getCreatedTick() {
      return this.createdTick;
   }

   public double getYaw() {
      return this.yaw;
   }

   public RotationKinematicState(Vec2d vec2d, double d, double d2) {
      this.yaw = MathHelper.wrapAngleTo180_double(d);
      this.pitch = MathHelper.clamp_double(d2, -90.0, 90.0);
      this.angularVelocity = vec2d;
      if (mc.thePlayer != null) {
         this.createdTick = mc.thePlayer.ticksExisted;
      }
   }

   public void setTargetYawBounds(double[] doubles) {
      this.targetYawBounds = doubles;
   }

   public double[] getTargetYawBounds() {
      return this.targetYawBounds;
   }

   public void setAngularVelocity(Vec2d vec2d) {
      this.angularVelocity = vec2d;
   }

   public void setAngles(double d, double d2) {
      this.yaw = MathHelper.wrapAngleTo180_double(d);
      this.pitch = d2;
   }

   public double getPitch() {
      return this.pitch;
   }

   public Vec2d getAngularVelocity() {
      return this.angularVelocity;
   }
}
