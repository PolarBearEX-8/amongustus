package client.util;

import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public enum HurtInvisibleEnum implements TargetObserver {
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
   public static final HurtInvisibleEnum[] ENUM_VALUES = values();
   public static final HurtInvisibleEnum[] ENUM_VALUES2 = new HurtInvisibleEnum[]{
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

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public AimSorter getBOOL() {
      return AimSorter.BOOL;
   }

   public static HurtInvisibleEnum resolveHurtInvisibleEnum(String text) {
      if (text == null) {
         return null;
      } else {
         for (int index = 0; index < ENUM_VALUES.length; index++) {
            HurtInvisibleEnum hurtInvisibleEnum = ENUM_VALUES[index];
            if (hurtInvisibleEnum.getLabelText().equals(text)) {
               return hurtInvisibleEnum;
            }
         }

         return null;
      }
   }



   @Override
   public boolean isActive() {
      Entity renderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
      if (renderViewEntity instanceof EntityLivingBase) {
         EntityLivingBase livingEntity = (EntityLivingBase)renderViewEntity;
         switch (HurtInvisibleEnumHurtInvisibleEnumSwitchMap.countArray[this.ordinal()]) {
            case 1:
               return livingEntity.canExecute();
            case 2:
               return livingEntity.canExecute2();
            case 3:
               return livingEntity.isActive16();
            case 4:
               return livingEntity.hurtTime > 0;
            case 5:
               return livingEntity.isWet();
            case 6:
               return livingEntity.isInWater();
            case 7:
               return livingEntity.isActive12();
            case 8:
               return livingEntity.onGround;
            case 9:
               return livingEntity.entity != null;
            case 10:
               return livingEntity.isSneaking();
            case 11:
               return livingEntity.isSprinting();
            case 12:
               return livingEntity.isRiding();
            case 13:
               return livingEntity.isActive11();
         }
      }

      return false;
   }

   private HurtInvisibleEnum(String text) {
      this.labelText = text;
      this.renderManager = Minecraft.getMinecraft().getRenderManager();
   }
}
