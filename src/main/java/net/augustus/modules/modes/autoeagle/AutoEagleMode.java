package net.augustus.modules.modes.autoeagle;

import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.legit.AutoEagle;
import net.augustus.settings.SettingGroup;

public abstract class AutoEagleMode extends AbstractSubMode {

   public AutoEagleMode(AutoEagle autoEagle, SettingGroup doubleValue, String str) {
      super(autoEagle, doubleValue, str);
   }



}
