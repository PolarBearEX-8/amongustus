package client.util;

import client.rotation.Rotation;
import client.setting.SettingContainer;

public class AnimationTimerController {
   public AnimationTimer animationTimer;
   public static float factor = 180.0F;
   public double value;
   public double value2 = 0.0;
   public int count;
   public static float factor2 = 40.0F;
   public static float factor3 = 360.0F;
   public static float factor4 = 90.0F;

   private Rotation resolveRotation(Rotation rotation, Rotation rotation2, Rotation rotation3) {
      client.rotation.RandomController.getStringArray();
      int count2 = this.count;
      if (!this.animationTimer.checkCondition(count2)) {
         this.value2 = 0.0;
         this.value = 0.0;
         return rotation2;
      } else {
         double offset = 1.0;
         Rotation rotation4 = rotation3.resolveRotation(rotation2);
         SimulationSnapshot simulationSnapshot = new SimulationSnapshot((double)rotation4.yaw, (double)rotation4.pitch);
         Rotation rotation5 = rotation2.resolveRotation(rotation);
         SimulationSnapshot simulationSnapshot2 = new SimulationSnapshot((double)rotation5.yaw, (double)rotation5.pitch);
         if (simulationSnapshot2.sqrt() < offset) {
            return rotation2;
         } else {
            this.value2 = this.value2 + simulationSnapshot2.value;
            this.value = this.value + simulationSnapshot2.value2;
            if (Math.abs(this.value2) > (double)factor3) {
               this.updateState();
            }

            if (Math.abs(this.value) > (double)factor) {
               this.updateState();
            }

            if (Math.abs(this.value2) > (double)factor4 && Math.abs(simulationSnapshot.value) < (double)factor4) {
               this.updateState();
            }

            if (Math.abs(this.value) > (double)factor2 && Math.abs(simulationSnapshot.value2) < (double)factor2) {
               this.updateState();
            }

            return new Rotation();
         }
      }
   }

   public AnimationTimerController() {
      client.rotation.RandomController.getStringArray();
      this.value = 0.0;
      this.animationTimer = new AnimationTimer();
      this.count = 8;
   }

   private void updateState() {
      this.animationTimer.processAction3();
      this.count = SettingContainer.settingProvider.getInt2(6, 9);
   }
}
