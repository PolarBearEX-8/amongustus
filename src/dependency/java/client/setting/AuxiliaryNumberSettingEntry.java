package client.setting;

import augustus.modes.movement.JesusPushMode;

public class AuxiliaryNumberSettingEntry {
   public final NumberSetting numberSetting;
   public final BooleanSetting booleanSetting;
   public final JesusPushMode jesusPushMode;

   public double getValue() {
      return this.numberSetting.getValue() / 100.0;
   }

   public boolean isActive() {
      return this.booleanSetting.isEnabled();
   }

   public AuxiliaryNumberSettingEntry(JesusPushMode jesusPushMode2, NumberSetting setting, BooleanSetting setting2) {
      this.jesusPushMode = jesusPushMode2;
      this.numberSetting = setting;
      this.booleanSetting = setting2;
   }
}
