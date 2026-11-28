package net.augustus.utils.rotation;


import net.augustus.utils.TickCounter;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.Vec2d;
public class RotationDeltaTracker {
   private TickCounter tickCounter;
   private int cooldownTicks;
   private static float MAX_CUMULATIVE_YAW = 360.0F;
   private static float PITCH_RESET_THRESHOLD = 40.0F;
   private static float YAW_RESET_THRESHOLD = 90.0F;
   private double cumulativeYawDelta = 0.0;
   private static float MAX_CUMULATIVE_PITCH = 180.0F;
   private double cumulativePitchDelta;

   private void triggerCooldownReset() {
      this.tickCounter.reset();
      this.cooldownTicks = DefaultRandomGenerator.instance.nextInt((byte)6, (byte)9);
   }

   private Rotation filterRotationDelta(Rotation rotation, Rotation rotation2, Rotation rotation3) {
      int cooldownTicks2 = this.cooldownTicks;
      if (!this.tickCounter.hasReached(cooldownTicks2)) {
         this.cumulativeYawDelta = 0.0;
         this.cumulativePitchDelta = 0.0;
         return rotation2;
      } else {
         double d = 1.0;
         Rotation rotation4 = rotation3.subtract(rotation2);
         Vec2d vec2d = new Vec2d((double)rotation4.yaw, (double)rotation4.pitch);
         Rotation rotation5 = rotation2.subtract(rotation);
         Vec2d vec2d2 = new Vec2d((double)rotation5.yaw, (double)rotation5.pitch);
         double d2 = vec2d.x / vec2d2.x;
         double d3 = vec2d.y / vec2d2.y;
         if (vec2d2.length() < d) {
            return rotation2;
         } else {
            this.cumulativeYawDelta = this.cumulativeYawDelta + vec2d2.x;
            this.cumulativePitchDelta = this.cumulativePitchDelta + vec2d2.y;
            if (Math.abs(this.cumulativeYawDelta) > (double)MAX_CUMULATIVE_YAW) {
               this.triggerCooldownReset();
            }

            if (Math.abs(this.cumulativePitchDelta) > (double)MAX_CUMULATIVE_PITCH) {
               this.triggerCooldownReset();
            }

            if (Math.abs(this.cumulativeYawDelta) > (double)YAW_RESET_THRESHOLD && Math.abs(vec2d.x) < (double)YAW_RESET_THRESHOLD) {
               this.triggerCooldownReset();
            }

            if (Math.abs(this.cumulativePitchDelta) > (double)PITCH_RESET_THRESHOLD && Math.abs(vec2d.y) < (double)PITCH_RESET_THRESHOLD) {
               this.triggerCooldownReset();
            }

            return new Rotation();
         }
      }
   }

   public RotationDeltaTracker(long l) {
      this.cumulativePitchDelta = 0.0;
      this.tickCounter = new TickCounter();
      this.cooldownTicks = 8;
   }
}
