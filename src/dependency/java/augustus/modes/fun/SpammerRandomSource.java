package augustus.modes.fun;

import augustus.modules.fun.Spammer;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SpammerModeOption;
import client.util.TabListBotFilter;
import java.util.function.BiFunction;

public class SpammerRandomSource extends SpammerModeOption {
   @Override
   public void updateState() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new SpammerRandomSource((Spammer)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
   }

   @Override
   public String getString2() {
      int intItem = SettingContainer.settingProvider.getInt2(8, 15);
      StringBuilder builder = new StringBuilder();
      char[] characterArray = TabListBotFilter.labelText.toCharArray();
      int length = characterArray.length;

      for (int index = 0; index < intItem; index++) {
         int count = length - 1;
         byte byteValue = 0;
         builder.append(characterArray[SettingContainer.settingProvider.getInt2(byteValue, count)]);
      }

      return builder.toString();
   }

   @Override
   public void updateState2() {
   }

   public SpammerRandomSource(Spammer spammer, SettingGroup settingGroup) {
      super(spammer, settingGroup, "RandomAlphanumeric");
   }
}
