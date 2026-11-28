package net.augustus.utils.rotation;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.minecraft.entity.player.EntityPlayer;

public class YawPitchHelper {
   public float realLastYaw;
   public float realLastPitch;
   public float realYaw = 0.0F;
   public float realPitch = 0.0F;

   public float alignYawToReference(float f, float f2) {
      float f3 = 360.0F;
      if (Augustus.getInstance().getModuleManager().exploitFixer.isToggled() && Math.abs(f2 - f) > 1800000.0F) {
         f3 = (float)(Math.floor((double)(Math.abs(f2 - f) / 360.0F)) * 360.0);
      }

      while (f2 - f >= 360.0F) {
         f += f3;
      }

      while (f2 - f < -360.0F) {
         f -= f3;
      }

      return f;
   }

   public YawPitchHelper() {
      this.realLastYaw = 0.0F;
      this.realLastPitch = 0.0F;
   }

   public float wrapRelativeYaw(float f, float f2) {

      while (f - f2 >= 360.0F) {
         f2 += 360.0F;
      }

      while (f - f2 < -360.0F) {
         f2 -= 360.0F;
      }

      return f2;
   }

   public void syncPlayerAngles(EntityPlayer player, boolean flag) {
      if (flag) {
         Augustus.getInstance().getYawPitchHelper().realYaw = player.rotationYaw;
         Augustus.getInstance().getYawPitchHelper().realPitch = player.rotationPitch;
         Augustus.getInstance().getYawPitchHelper().realLastYaw = player.prevRotationYaw;
         Augustus.getInstance().getYawPitchHelper().realLastPitch = player.prevRotationPitch;
      } else if (player.rotationYaw < 500.0F) {
         RotationController rotationController = ClientManagers.rotationController;
         rotationController.setPreviousRotation(new Rotation(player.prevRotationYaw, player.prevRotationPitch));
         rotationController = ClientManagers.rotationController;
         rotationController.setCurrentRotation(new Rotation(player.rotationYaw, player.rotationPitch));
         float rotationPitch = player.rotationPitch;
         float rotationYaw = player.rotationYaw;
         ClientManagers.rotationController.syncCameraAngles(rotationYaw, rotationPitch);
      }
   }



}
