package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

public enum IdleTimeEnum implements AimValidator {
    LIMB_SWING("limb_swing"),
    LIMB_SWING_SPEED("limb_speed"),
    AGE("age"),
    HEAD_YAW("head_yaw"),
    HEAD_PITCH("head_pitch"),
    SCALE("scale"),
    HEALTH("health"),
    HURT_TIME("hurt_time"),
    IDLE_TIME("idle_time"),
    MAX_HEALTH("max_health"),
    MOVE_FORWARD("move_forward"),
    MOVE_STRAFING("move_strafing"),
    PARTIAL_TICKS("partial_ticks"),
    POS_X("pos_x"),
    POS_Y("pos_y"),
    POS_Z("pos_z"),
    REVENGE_TIME("revenge_time"),
    SWING_PROGRESS("swing_progress");

   public String labelText;
   public RenderManager renderManager;
   public static final IdleTimeEnum[] ENUM_VALUES = values();
   public static final IdleTimeEnum[] ENUM_VALUES2 = new IdleTimeEnum[]{
      LIMB_SWING,
      LIMB_SWING_SPEED,
      AGE,
      HEAD_YAW,
      HEAD_PITCH,
      SCALE,
      HEALTH,
      HURT_TIME,
      IDLE_TIME,
      MAX_HEALTH,
      MOVE_FORWARD,
      MOVE_STRAFING,
      PARTIAL_TICKS,
      POS_X,
      POS_Y,
      POS_Z,
      REVENGE_TIME,
      SWING_PROGRESS
   };

   private IdleTimeEnum(String text) {
      this.labelText = text;
      this.renderManager = Minecraft.getMinecraft().getRenderManager();
   }

   @Override
   public AimSorter getBOOL() {
      return AimSorter.FLOAT;
   }

   public static IdleTimeEnum resolveIdleTimeEnum(String text) {
      if (text == null) {
         return null;
      } else {
         for (int index = 0; index < ENUM_VALUES.length; index++) {
            IdleTimeEnum idleTimeEnum = ENUM_VALUES[index];
            if (idleTimeEnum.getLabelText().equals(text)) {
               return idleTimeEnum;
            }
         }

         return null;
      }
   }

   @Override
   public float getFactor() {
      EntitySkinLocation resourceLocationEntry = this.renderManager.resourceLocationEntry;
      if (resourceLocationEntry == null) {
         return 0.0F;
      } else {
         if (resourceLocationEntry instanceof RendererLivingEntity) {
            RendererLivingEntity rendererLivingEntity = (RendererLivingEntity)resourceLocationEntry;
            switch (IdleTimeEnumIdleTimeEnumSwitchMap.countArray[this.ordinal()]) {
               case 12:
                  return rendererLivingEntity.factor12;
               case 13:
                  return rendererLivingEntity.factor3;
               case 14:
                  return rendererLivingEntity.factor11;
               case 15:
                  return rendererLivingEntity.factor7;
               case 16:
                  return rendererLivingEntity.factor4;
               case 17:
                  return rendererLivingEntity.factor9;
               default:
                  EntityLivingBase entityLivingBase = rendererLivingEntity.entityLivingBase;
                  if (entityLivingBase == null) {
                     return 0.0F;
                  }

                  switch (IdleTimeEnumIdleTimeEnumSwitchMap.countArray[this.ordinal()]) {
                     case 1:
                        return entityLivingBase.getFloat2();
                     case 2:
                        return (float)entityLivingBase.hurtTime;
                     case 3:
                        return (float)entityLivingBase.getIndexOffset14();
                     case 4:
                        return entityLivingBase.getFloat();
                     case 5:
                        return entityLivingBase.scaleFactor3;
                     case 6:
                        return entityLivingBase.scaleFactor24;
                     case 7:
                        return (float)entityLivingBase.posX;
                     case 8:
                        return (float)entityLivingBase.posY;
                     case 9:
                        return (float)entityLivingBase.posZ;
                     case 10:
                        return (float)entityLivingBase.getIndexOffset5();
                     case 11:
                        return entityLivingBase.computeFactorByFloat(rendererLivingEntity.factor10);
                  }
            }
         }

         return 0.0F;
      }
   }

   public String getLabelText() {
      return this.labelText;
   }


}
