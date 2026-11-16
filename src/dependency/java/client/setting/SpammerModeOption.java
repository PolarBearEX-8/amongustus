package client.setting;

import augustus.modules.fun.Spammer;

public abstract class SpammerModeOption extends ModeOptionSupplier {
   public abstract String getString2();

   public SpammerModeOption(Spammer spammer, SettingGroup settingGroup, String text) {
      super(spammer, settingGroup, text);
   }
}
