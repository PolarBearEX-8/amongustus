package augustus.modes.fun;

import augustus.modules.fun.Spammer;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SpammerModeOption;
import java.util.function.BiFunction;

public class SpammerCustomSource extends SpammerModeOption {
   @Override
   public void updateState() {
   }

   @Override
   public void clear() {
   }

   @Override
   public String getString2() {
      return String.valueOf(SettingContainer.settingProvider.getLong(1000000000L, 100000000000000L));
   }

   @Override
   public void updateState2() {
   }

   public static BiFunction getBiFunction() {
      return (BiFunction<Spammer, SettingGroup, SpammerCustomSource>)SpammerCustomSource::new;
   }

   public SpammerCustomSource(Spammer spammer, SettingGroup settingGroup) {
      super(spammer, settingGroup, "RandomNumbers");
   }
}
