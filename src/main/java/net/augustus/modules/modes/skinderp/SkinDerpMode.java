package net.augustus.modules.modes.skinderp;

import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.fun.SkinDerp;
import net.augustus.settings.SettingGroup;
import net.minecraft.entity.player.EnumPlayerModelParts;

public abstract class SkinDerpMode extends AbstractSubMode {

   public SkinDerpMode(SkinDerp skinDerp, SettingGroup doubleValue, String str) {
      super(skinDerp, doubleValue, str);
   }

   public boolean isPartEnabled(EnumPlayerModelParts enumPlayerModelParts) {
      switch (enumPlayerModelParts) {
         case CAPE:
            return ((SkinDerp)this.parentModule).getBlinkCape().getValue();
         case JACKET:
            return ((SkinDerp)this.parentModule).getBlinkJacket().getValue();
         case LEFT_SLEEVE:
            return ((SkinDerp)this.parentModule).getBlinkLeftSleeve().getValue();
         case RIGHT_SLEEVE:
            return ((SkinDerp)this.parentModule).getBlinkRightSleeve().getValue();
         case LEFT_PANTS_LEG:
            return ((SkinDerp)this.parentModule).getBlinkLeftPants().getValue();
         case RIGHT_PANTS_LEG:
            return ((SkinDerp)this.parentModule).getBlinkRightPants().getValue();
         case HAT:
            return ((SkinDerp)this.parentModule).getBlinkHat().getValue();
         default:
            return false;
      }
   }

   public void setModelPartEnabled(EnumPlayerModelParts enumPlayerModelParts, boolean flag) {
      if (mc.gameSettings != null) {
         mc.gameSettings.setModelPartEnabled(enumPlayerModelParts, flag);
      }
   }

   public EnumPlayerModelParts[] getModelParts() {
      return new EnumPlayerModelParts[]{
         EnumPlayerModelParts.CAPE,
         EnumPlayerModelParts.JACKET,
         EnumPlayerModelParts.LEFT_SLEEVE,
         EnumPlayerModelParts.RIGHT_SLEEVE,
         EnumPlayerModelParts.LEFT_PANTS_LEG,
         EnumPlayerModelParts.RIGHT_PANTS_LEG,
         EnumPlayerModelParts.HAT
      };
   }

   public void enableAllParts() {

      for (EnumPlayerModelParts enumPlayerModelParts : this.getModelParts()) {
         this.setModelPartEnabled(enumPlayerModelParts, true);
      }
   }

   public double getDelayMs() {
      return ((SkinDerp)this.parentModule).getDelay().getValue();
   }

}
