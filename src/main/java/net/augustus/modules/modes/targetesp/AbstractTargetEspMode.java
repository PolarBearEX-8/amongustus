package net.augustus.modules.modes.targetesp;

import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.render.TargetESP;
import net.augustus.settings.SettingGroup;
import net.minecraft.entity.EntityLivingBase;

public abstract class AbstractTargetEspMode extends AbstractSubMode {

   public AbstractTargetEspMode(TargetESP targetESP, SettingGroup doubleValue, String str) {
      super(targetESP, doubleValue, str);
   }

   public abstract void renderTargetEsp(EntityLivingBase entity);




}
