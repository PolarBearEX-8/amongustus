package client.via;

import client.Client;
import client.ClientGlobals;
import client.module.Module;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.util.MinecraftAccessor;
import client.util.TimeHelper;
import net.minecraft.entity.player.EntityPlayer;

public class ViaProtocolHelper implements MinecraftAccessor {
   public float scaleFactor2;
   public float scaleFactor3;
   public float scaleFactor1 = 0.0F;
   public float scaleFactor;

   public float computeFactor(float factor, float factor2) {
      float factor3 = factor2;

      while (factor - factor3 >= 180.0F) {
         factor3 += 360.0F;
      }

      while (factor - factor3 < -180.0F) {
         factor3 -= 360.0F;
      }

      return factor3;
   }

   public float computeFactorByFloat(float factor, float factor2) {
      float factor3 = factor;
      float factor4 = 360.0F;
      if (((Module)Client.getClient().getModuleManager().exploitFixer).isToggled() && Math.abs(factor2 - factor) > 1800000.0F) {
         factor4 = (float)(Math.floor((double)(Math.abs(factor2 - factor) / 360.0F)) * 360.0);
      }

      while (factor2 - factor3 >= 180.0F) {
         factor3 += factor4;
      }

      while (factor2 - factor3 < -180.0F) {
         factor3 -= factor4;
      }

      return factor3;
   }

   public void applyEntityPlayer(EntityPlayer player, boolean flag) {
      long value = 0xa4fd298506dL;
      if (!flag) {
         Client.getClient().getViaProtocolHelper().scaleFactor1 = player.rotationYaw;
         Client.getClient().getViaProtocolHelper().scaleFactor2 = player.rotationPitch;
         Client.getClient().getViaProtocolHelper().scaleFactor3 = player.prevRotationYaw;
         Client.getClient().getViaProtocolHelper().scaleFactor = player.prevRotationPitch;
      }

      RotationManager rotationManager = ClientGlobals.rotationManager;
      Rotation rotation = new Rotation(player.prevRotationYaw, player.prevRotationPitch);
      rotationManager.setRotation3(rotation);
      rotationManager = ClientGlobals.rotationManager;
      Rotation rotation2 = new Rotation(player.rotationYaw, player.rotationPitch);
      rotationManager.setRotation2(rotation2);
      float pitch = player.rotationPitch;
      float yaw = player.rotationYaw;
      ClientGlobals.rotationManager.setRotations(yaw, pitch);
      float pitch2 = player.rotationPitch;
      float yaw2 = player.rotationYaw;
      ClientGlobals.moduleManager.killAura.simulatedPlayer.customFloatEntry.applyFloat(yaw2, pitch2, false);
   }

   public ViaProtocolHelper() {
      this.scaleFactor2 = 0.0F;
      this.scaleFactor3 = 0.0F;
      this.scaleFactor = 0.0F;
   }
}
