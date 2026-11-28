package net.augustus.settings;

import net.augustus.ClientManagers;
import net.augustus.modules.Module;

public abstract class AbstractArraySetting extends Setting {

   public AbstractArraySetting(Module module, String str) {
       super(module, null, str, false);
      ClientManagers.settingManager.getSettings().add(this);
   }

   public abstract void setValues(Object[] array);

   @Override
   public void renderTooltip() {
   }

   public abstract Object[] getValues();
}
