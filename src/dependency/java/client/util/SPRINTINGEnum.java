package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

public enum SPRINTINGEnum implements TargetObserver {
    IS_ALIVE("is_alive"),
    IS_BURNING("is_burning"),
    IS_CHILD("is_child"),
    IS_GLOWING("is_glowing"),
    IS_HURT("is_hurt"),
    IS_IN_LAVA("is_in_lava"),
    IS_IN_WATER("is_in_water"),
    IS_INVISIBLE("is_invisible"),
    IS_ON_GROUND("is_on_ground"),
    IS_RIDDEN("is_ridden"),
    IS_RIDING("is_riding"),
    IS_SNEAKING("is_sneaking"),
    IS_SPRINTING("is_sprinting"),
    IS_WET("is_wet");

   public String labelText;
   public RenderManager renderManager;
   public static final SPRINTINGEnum[] ENUM_VALUES = values();
   public static final SPRINTINGEnum[] ENUM_VALUES2 = new SPRINTINGEnum[]{
      IS_ALIVE,
      IS_BURNING,
      IS_CHILD,
      IS_GLOWING,
      IS_HURT,
      IS_IN_LAVA,
      IS_IN_WATER,
      IS_INVISIBLE,
      IS_ON_GROUND,
      IS_RIDDEN,
      IS_RIDING,
      IS_SNEAKING,
      IS_SPRINTING,
      IS_WET
   };

   public static SPRINTINGEnum resolveSPRINTINGEnum(String text) {
      if (text == null) {
         return null;
      } else {
         for (int index = 0; index < ENUM_VALUES.length; index++) {
            SPRINTINGEnum sPRINTINGEnum = ENUM_VALUES[index];
            if (sPRINTINGEnum.getLabelText().equals(text)) {
               return sPRINTINGEnum;
            }
         }

         return null;
      }
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public AimSorter getBOOL() {
      return AimSorter.BOOL;
   }

   @Override
   public boolean isActive() {
      EntitySkinLocation resourceLocationEntry = this.renderManager.resourceLocationEntry;
      if (resourceLocationEntry == null) {
         return false;
      } else {
         if (resourceLocationEntry instanceof RendererLivingEntity) {
            EntityLivingBase entityLivingBase = ((RendererLivingEntity)resourceLocationEntry).entityLivingBase;
            if (entityLivingBase == null) {
               return false;
            }

            switch (SPRINTINGEnumSPRINTINGEnumSwitchMap.countArray[this.ordinal()]) {
               case 1:
                  return entityLivingBase.canExecute();
               case 2:
                  return entityLivingBase.canExecute2();
               case 3:
                  return entityLivingBase.isActive16();
               case 4:
                  return entityLivingBase.hurtTime > 0;
               case 5:
                  return entityLivingBase.isWet();
               case 6:
                  return entityLivingBase.isInWater();
               case 7:
                  return entityLivingBase.isActive12();
               case 8:
                  return entityLivingBase.onGround;
               case 9:
                  return entityLivingBase.entity != null;
               case 10:
                  return entityLivingBase.isSneaking();
               case 11:
                  return entityLivingBase.isSprinting();
               case 12:
                  return entityLivingBase.isRiding();
               case 13:
                  return entityLivingBase.isActive11();
            }
         }

         return false;
      }
   }



   private SPRINTINGEnum(String text) {
      this.labelText = text;
      this.renderManager = Minecraft.getMinecraft().getRenderManager();
   }
}
