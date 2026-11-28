package net.augustus.utils;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.utils.rotation.Rotation;
import net.augustus.utils.rotation.RotationController;
import net.augustus.utils.rotation.YawPitchHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerRotationHandler extends YawPitchHelper implements MC {
   @Override
   public float wrapRelativeYaw(float f, float f2) {

      while (f - f2 >= 180.0F) {
         f2 += 360.0F;
      }

      while (f - f2 < -180.0F) {
         f2 -= 360.0F;
      }

      return f2;
   }

   @Override
   public float alignYawToReference(float f, float f2) {
      float f3 = 360.0F;
      if (Augustus.getInstance().getModuleManager().exploitFixer.isToggled() && Math.abs(f2 - f) > 1800000.0F) {
         f3 = (float)(Math.floor((double)(Math.abs(f2 - f) / 360.0F)) * 360.0);
      }

      while (f2 - f >= 180.0F) {
         f += f3;
      }

      while (f2 - f < -180.0F) {
         f -= f3;
      }

      return f;
   }

   @Override
   public void syncPlayerAngles(EntityPlayer player, boolean flag) {
      if (!flag) {
         Augustus.getInstance().getYawPitchHelper().realYaw = player.rotationYaw;
         Augustus.getInstance().getYawPitchHelper().realPitch = player.rotationPitch;
         Augustus.getInstance().getYawPitchHelper().realLastYaw = player.prevRotationYaw;
         Augustus.getInstance().getYawPitchHelper().realLastPitch = player.prevRotationPitch;
      }

      RotationController rotationController = ClientManagers.rotationController;
      rotationController.setPreviousRotation(new Rotation(player.prevRotationYaw, player.prevRotationPitch));
      rotationController = ClientManagers.rotationController;
      rotationController.setCurrentRotation(new Rotation(player.rotationYaw, player.rotationPitch));
      float rotationPitch = player.rotationPitch;
      float rotationYaw = player.rotationYaw;
      ClientManagers.rotationController.syncCameraAngles(rotationYaw, rotationPitch);
      float rotationPitch2 = player.rotationPitch;
      float rotationYaw2 = player.rotationYaw;
      ClientManagers.moduleManager.killAura.rotationUtil.bezierRotationEngine.syncCurrentRotation(rotationYaw2, rotationPitch2, false);
   }
}
