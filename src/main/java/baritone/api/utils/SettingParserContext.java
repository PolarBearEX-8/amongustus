package baritone.api.utils;

import baritone.api.Settings;

public class SettingParserContext {
   private final Settings.Setting setting;

   private SettingParserContext(Settings.Setting setting2) {
      this.setting = setting2;
   }

   private Settings.Setting getSetting() {
      return this.setting;
   }

   public SettingParserContext(Settings.Setting setting2, SettingsUtil.InnerEntry obj) {
      this(setting2);
   }
}
