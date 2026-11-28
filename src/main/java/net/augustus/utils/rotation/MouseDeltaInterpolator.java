package net.augustus.utils.rotation;

import net.augustus.ClientManagers;
import net.augustus.events.EventMouseMove;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;

public class MouseDeltaInterpolator implements MC {
   private long lastFrameTimeMs = 0L;
   private int[] targetMouseDelta;
   private double[] accumulatedDelta;
   private Rotation maxBoundRotation;
   private int[] appliedDelta;
   private Rotation minBoundRotation;
   private long lastTickTimeMs;
   private long tickDurationMs = 0L;

   public MouseDeltaInterpolator() {
      this.lastTickTimeMs = 0L;
      this.appliedDelta = new int[2];
      this.accumulatedDelta = new double[2];
      this.targetMouseDelta = new int[2];
      this.minBoundRotation = null;
      this.maxBoundRotation = null;
   }

   public void setTargetRotation(float f, float f2) {
      this.minBoundRotation = null;
      this.maxBoundRotation = null;
      this.targetMouseDelta = RotationUtil.computeMouseDeltas(f, f2, ClientManagers.rotationController.getPreviousRotation().yaw, ClientManagers.rotationController.getPreviousRotation().pitch);
   }

   public void setTargetRotationBounded(float f, float f2, Rotation rotation, Rotation rotation2) {
      this.minBoundRotation = rotation;
      this.maxBoundRotation = rotation2;
      this.targetMouseDelta = RotationUtil.computeMouseDeltas(f, f2, ClientManagers.rotationController.getPreviousRotation().yaw, ClientManagers.rotationController.getPreviousRotation().pitch);
   }

   public void clearTargetDelta() {
      this.targetMouseDelta[0] = 0;
      this.targetMouseDelta[1] = 0;
   }

   public void onMouseMove(EventMouseMove event) {
      if (this.tickDurationMs > 10L && (this.targetMouseDelta[0] != 0 || this.targetMouseDelta[1] != 0)) {
         int n = (int)(System.currentTimeMillis() - this.lastFrameTimeMs);
         double d = (double)this.targetMouseDelta[0] / (double)this.tickDurationMs * (double)n;
         double d2 = (double)this.targetMouseDelta[1] / (double)this.tickDurationMs * (double)n;
         this.accumulatedDelta[0] = this.accumulatedDelta[0] + d;
         this.accumulatedDelta[1] = this.accumulatedDelta[1] + d2;
         int n2 = (int)Math.round(this.accumulatedDelta[0] - (double)this.appliedDelta[0]);
         int n3 = (int)Math.round(this.accumulatedDelta[1] - (double)this.appliedDelta[1]);
         if (this.minBoundRotation != null && this.maxBoundRotation != null && (n2 != 0 || n3 != 0)) {
            float rotationPitch = mc.thePlayer.rotationPitch;
            Rotation rotation = RotationUtil.applyMouseDeltas(n2, n3, mc.thePlayer.rotationYaw, rotationPitch);
            Rotation maxBoundRotation2 = this.maxBoundRotation;
            if (!rotation.isWithinBounds(this.minBoundRotation, maxBoundRotation2)) {
               n2 = 0;
               n3 = 0;
            }
         }

         this.appliedDelta[0] = this.appliedDelta[0] + n2;
         this.appliedDelta[1] = this.appliedDelta[1] + n3;
         event.setDeltaX(n2);
         event.setDeltaY(n3);
         event.setIgnoreInvertMouse(true);
         event.setBypassSmoothCamera(true);
      } else {
         this.appliedDelta[0] = this.appliedDelta[1] = 0;
         this.accumulatedDelta[0] = this.accumulatedDelta[1] = 0.0;
         this.targetMouseDelta[0] = this.targetMouseDelta[1] = 0;
      }

      this.lastFrameTimeMs = System.currentTimeMillis();
   }

   public void onTick() {
      this.tickDurationMs = (long)((int)Math.abs(this.lastTickTimeMs - System.currentTimeMillis()));
      this.lastTickTimeMs = System.currentTimeMillis();
      this.appliedDelta[0] = this.appliedDelta[1] = 0;
      this.accumulatedDelta[0] = this.accumulatedDelta[1] = 0.0;
   }
}
