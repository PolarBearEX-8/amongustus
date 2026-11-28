package net.augustus.modules.modes.faststop;

import net.augustus.events.EventMoveInput;
import net.augustus.events.EventSafeWalk;
import net.augustus.events.MoveInputStage;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.modules.movement.FastStop;
import net.augustus.settings.SettingGroup;

public class LegitFastStopMode extends FastStopMode {
   private double initialMotionX;
   private double initialMotionZ;
   private double stopOriginX;
   private double stopOriginZ;
   private boolean stopCompleted = false;
   private boolean initialOnGround = false;

   public LegitFastStopMode(FastStop fastStop, SettingGroup doubleValue) {
      super(fastStop, doubleValue, "Legit");
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onUpdate() {
   }

   @Override
   public void onSafeWalk(EventSafeWalk event) {
   }

   @Override
   public void onMoveInput(EventMoveInput event) {
      if (!this.isMovingInput && this.wasMovingInput) {
         this.initialMotionX = mc.thePlayer.motionX;
         this.initialMotionZ = mc.thePlayer.motionZ;
         this.stopOriginX = mc.thePlayer.posX;
         this.stopOriginZ = mc.thePlayer.posZ;
         this.stopCompleted = false;
         this.initialOnGround = mc.thePlayer.onGround;
      }

      if (!this.isAllowedByScaffold()) {
         this.stopCompleted = true;
      }

      if (!this.isMovingInput && !this.stopCompleted && (this.initialMotionX != 0.0 || this.initialMotionZ != 0.0) && this.initialOnGround == mc.thePlayer.onGround) {
         int n = -2;
         int n2 = -2;
         double d = 0.0;
         double d2 = 0.0;
         double d3 = 1.7976931348623157E308;

         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               double[] doubles = this.predictNextPosition(new EventMoveInput((float)j, (float)i, event.isJump(), event.isSneak(), MoveInputStage.PRE));
               double d4 = doubles[0];
               double d5 = doubles[2];
               double d6 = this.stopOriginX - d4;
               double d7 = this.stopOriginZ - d5;
               double d8 = d6 * d6 + d7 * d7;
               if (d8 < d3) {
                  d3 = d8;
                  n = i;
                  n2 = j;
                  d = d4;
                  d2 = d5;
               }
            }
         }

         if (n == 0 && n2 == 0) {
            this.stopCompleted = true;
         }

         if (d3 != 1.7976931348623157E308 && !this.stopCompleted) {
            boolean flag;
            double posX = mc.thePlayer.posX;
               double stopOriginX2 = this.stopOriginX;
if (!this.wouldOvershootOrigin(this.initialMotionX, d, stopOriginX2, posX)) {
double posZ = mc.thePlayer.posZ;
                  double stopOriginZ2 = this.stopOriginZ;
if (!this.wouldOvershootOrigin(this.initialMotionZ, d2, stopOriginZ2, posZ)) {
flag = false;
} else {
flag = true;
}
} else {
flag = true;
}

            if (!flag) {
               event.setMoveForward((float)n);
               event.setMoveStrafe((float)n2);
            }

            double d9 = d - mc.thePlayer.posX;
            double d10 = d2 - mc.thePlayer.posZ;
            if (!hasSameSign(d9, this.initialMotionX) || !hasSameSign(d10, this.initialMotionZ)) {
               this.stopCompleted = true;
            }
         }
      } else {
         this.stopCompleted = true;
      }
   }

   private double[] predictNextPosition(EventMoveInput event) {
      float rotationYaw = mc.thePlayer.rotationYaw;
      double posZ = mc.thePlayer.posZ;
      double posY = mc.thePlayer.posY;
      double posX = mc.thePlayer.posX;
      return PlayerUtil.predictPlayerPosition(0, mc.thePlayer, posX, posY, posZ, rotationYaw, event, false);
   }

   private boolean wouldOvershootOrigin(double d, double d2, double d3, double d4) {
      boolean flag = false;
      if (d > 0.0 && d2 < d3 && Math.abs(d2 - d4) > 0.01) {
         flag = true;
      }

      if (d < 0.0 && d2 > d3 && Math.abs(d2 - d4) > 0.01) {
         flag = true;
      }

      return flag;
   }

   public static boolean hasSameSign(double d, double d2) {
      if (Double.isNaN(d) || Double.isNaN(d2)) {
         return false;
      } else {
         return d != 0.0 && d2 != 0.0 ? d > 0.0 == d2 > 0.0 : d == 0.0 && d2 == 0.0;
      }
   }

}
