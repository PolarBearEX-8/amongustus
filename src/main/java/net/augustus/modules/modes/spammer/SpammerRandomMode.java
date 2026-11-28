package net.augustus.modules.modes.spammer;

import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.fun.Spammer;
import net.augustus.settings.SettingGroup;

public abstract class SpammerRandomMode extends AbstractSubMode {

   public SpammerRandomMode(Spammer spammer, SettingGroup doubleValue, String str) {
      super(spammer, doubleValue, str);
   }

   public abstract String generateRandomSuffix();



}
