package net.augustus.modules.modes.targetesp;

import net.augustus.modules.render.TargetESP;
import net.augustus.settings.SettingGroup;
import net.minecraft.entity.EntityLivingBase;

public class NoneTargetEspMode extends AbstractTargetEspMode {
   public NoneTargetEspMode(TargetESP targetESP, SettingGroup doubleValue) {
      super(targetESP, doubleValue, "None");
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
   public void renderTargetEsp(EntityLivingBase entity) {
   }
}
