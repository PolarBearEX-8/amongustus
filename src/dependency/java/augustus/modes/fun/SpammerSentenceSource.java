package augustus.modes.fun;

import augustus.modules.fun.Spammer;
import client.network.NetworkPlayerInfo;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SpammerModeOption;
import client.util.MinecraftAccessor;
import client.util.TabListBotFilter;
import java.util.function.BiFunction;

public class SpammerSentenceSource extends SpammerModeOption {
   public String labelText2 = "";

   @Override
   public void updateState() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new SpammerSentenceSource((Spammer)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
   }

   @Override
   public String getString2() {
      int size = MinecraftAccessor.minecraft.getNetHandlerPlayClient().getEntryMap().values().size();
      int intItem = SettingContainer.settingProvider.getInt2(8, 15);
      StringBuilder builder = new StringBuilder();
      char[] characterArray = TabListBotFilter.labelText.toCharArray();
      int length = characterArray.length;

      for (int index = 0; index < intItem; index++) {
         int count = length - 1;
         byte byteValue = 0;
         builder.append(characterArray[SettingContainer.settingProvider.getInt2(byteValue, count)]);
      }

      int count2 = 0;
      int count3 = size - 1;
      byte byteValue2 = 0;
      int intItem2 = SettingContainer.settingProvider.getInt2(byteValue2, count3);

      for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(Iterable)(MinecraftAccessor.minecraft.getNetHandlerPlayClient().getEntryMap().values())) {
         if (count2 == intItem2) {
            if (!networkPlayerInfo.getGameProfile().getName().equals(this.labelText2)) {
               builder = new StringBuilder(networkPlayerInfo.getGameProfile().getName());
            }
            break;
         }

         count2++;
      }

      return this.labelText2 = builder.toString();
   }

   @Override
   public void updateState2() {
   }

   public SpammerSentenceSource(Spammer spammer, SettingGroup settingGroup) {
      super(spammer, settingGroup, "RandomTablistName");
   }
}
