package net.augustus.ai;

import net.augustus.ClientManagers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public final class AiRotationFeatureExtractor {

   private AiRotationFeatureExtractor() {
   }

   public static float[] extractFeatures(EntityPlayerSP player, EntityLivingBase entity, AiRotationFeatureState aiRotationFeatureState) {
      if (!aiRotationFeatureState.isInitialized()) {
         aiRotationFeatureState.initialize(player, entity);
      }

      double posX = player.posX;
      double d = player.posY + (double)player.getEyeHeight();
      double posZ = player.posZ;
      double posX2 = entity.posX;
      double d2 = entity.posY + (double)entity.getEyeHeight();
      double posZ2 = entity.posZ;
      double d3 = posX2 - posX;
      double d4 = d2 - d;
      double d5 = posZ2 - posZ;
      double d6 = Math.hypot(d3, d5);
      double d7 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
      float yaw = ClientManagers.rotationController.getPreviousRotation().yaw;
      float pitch = ClientManagers.rotationController.getPreviousRotation().pitch;
      float f2 = MathHelper.wrapAngleTo180_float(((float)Math.toDegrees(-Math.atan2(d3, d5))) - yaw);
      float f4 = ((float)Math.toDegrees(-Math.atan2(d4, d6))) - pitch;
      float f5 = (float)d7;
      float f6 = (float)(entity.posX - aiRotationFeatureState.getPrevTargetX());
      float f7 = (float)(entity.posY - aiRotationFeatureState.getPrevTargetY());
      float f8 = (float)(entity.posZ - aiRotationFeatureState.getPrevTargetZ());
      float f9 = entity.onGround ? 1.0F : 0.0F;
      float f10 = entity.hurtTime == 10 && aiRotationFeatureState.getPrevTargetHurtTime() != 10 ? 1.0F : 0.0F;
      float f11 = player.hurtTime == 10 && aiRotationFeatureState.getPrevPlayerHurtTime() != 10 ? 1.0F : 0.0F;
      boolean onGround = player.onGround;
      double motionY = player.motionY;
      boolean isPrevPlayerOnGround = aiRotationFeatureState.isPrevPlayerOnGround();
      String str;
      if (onGround && !isPrevPlayerOnGround) {
         str = "ground";
      } else if (!onGround && isPrevPlayerOnGround && motionY > 0.0) {
         str = "ascending";
      } else if (!onGround && motionY > 0.0) {
         str = "ascending";
      } else if (!onGround && motionY <= 0.0 && motionY > -0.1) {
         str = "peak";
      } else if (!onGround && motionY <= -0.1) {
         str = "descending";
      } else if (onGround) {
         str = "ground";
      } else {
         str = aiRotationFeatureState.getJumpPhase();
      }

      float f12 = "ground".equals(str) ? 1.0F : 0.0F;
      float f13 = "ascending".equals(str) ? 1.0F : 0.0F;
      float f14 = "peak".equals(str) ? 1.0F : 0.0F;
      float f15 = "descending".equals(str) ? 1.0F : 0.0F;
      Minecraft minecraft = Minecraft.getMinecraft();
      float f16 = minecraft.gameSettings.keyBindForward.isKeyDown() ? 1.0F : 0.0F;
      float f17 = minecraft.gameSettings.keyBindLeft.isKeyDown() ? 1.0F : 0.0F;
      float f18 = minecraft.gameSettings.keyBindRight.isKeyDown() ? 1.0F : 0.0F;
      float f19 = minecraft.gameSettings.keyBindBack.isKeyDown() ? 1.0F : 0.0F;
      float f20 = minecraft.gameSettings.keyBindJump.isKeyDown() ? 1.0F : 0.0F;
      float f21;
      if (d7 < 1.0E-6) {
         f21 = 0.0F;
      } else {
         Vec3 vectorForRotation = player.getVectorForRotation(ClientManagers.rotationController.getPreviousRotation().pitch, ClientManagers.rotationController.getLastTickRotation().yaw);
         double d8 = d3 / d7;
         double d9 = d4 / d7;
         double d10 = d5 / d7;
         double d11 = vectorForRotation.xCoord * d8 + vectorForRotation.yCoord * d9 + vectorForRotation.zCoord * d10;
         if (d11 > 1.0) {
            d11 = 1.0;
         } else if (d11 < -1.0) {
            d11 = -1.0;
         }

         f21 = (float)Math.toDegrees(Math.acos(d11));
      }

      float[] floats = new float[]{
         f2,
         f4,
         f5,
         pitch,
         (float)entity.hurtTime,
         f6,
         f7,
         f8,
         f9,
         aiRotationFeatureState.getLastYawDelta(),
         aiRotationFeatureState.getLastPitchDelta(),
         onGround ? 1.0F : 0.0F,
         (float)player.hurtTime,
         (float)player.motionX,
         (float)player.motionY,
         (float)player.motionZ,
         f12,
         f13,
         f14,
         f15,
         f16,
         f17,
         f18,
         f19,
         f20,
         f21,
         f10,
         f11
      };
      aiRotationFeatureState.update(player, entity, str);
      return floats;
   }

}
