package augustus.modes.misc;

import augustus.modules.fun.Spammer;
import client.setting.SettingGroup;
import client.setting.SpammerModeOption;
import java.util.function.BiFunction;

public class SpammerFileSource extends SpammerModeOption {
   @Override
   public void updateState() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new SpammerFileSource((Spammer)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
   }

   @Override
   public String getString2() {
      return "";
   }

   @Override
   public void updateState2() {
   }

   public SpammerFileSource(Spammer spammer, SettingGroup settingGroup) {
      super(spammer, settingGroup, "None");
   }
}
