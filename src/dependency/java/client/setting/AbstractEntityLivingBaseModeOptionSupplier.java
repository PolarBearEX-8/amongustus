package client.setting;

import client.module.Module;
import net.minecraft.entity.EntityLivingBase;

public abstract class AbstractEntityLivingBaseModeOptionSupplier extends ModeOptionSupplier {
   public AbstractEntityLivingBaseModeOptionSupplier(Module module, SettingGroup settingGroup, String text) {
      super(module, settingGroup, text);
   }

   public abstract int computeCount(EntityLivingBase livingEntity);

   public abstract void applyEntityLivingBase(EntityLivingBase livingEntity);

   public abstract void updateState3();
}
