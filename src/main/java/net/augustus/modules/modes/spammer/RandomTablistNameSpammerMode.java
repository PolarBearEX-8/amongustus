package net.augustus.modules.modes.spammer;

import net.augustus.utils.TextFormatUtil;
import net.augustus.utils.math.DefaultRandomGenerator;
import java.util.function.BiFunction;
import net.augustus.modules.fun.Spammer;
import net.augustus.settings.SettingGroup;
import net.minecraft.client.network.NetworkPlayerInfo;

public class RandomTablistNameSpammerMode extends SpammerRandomMode {
   private String lastSelectedName = "";

   public RandomTablistNameSpammerMode(Spammer spammer, SettingGroup doubleValue) {
      super(spammer, doubleValue, "RandomTablistName");
   }

   @Override
   public String generateRandomSuffix() {
      int size = mc.getNetHandler().getPlayerInfoMap2().values().size();
      int n = DefaultRandomGenerator.instance.nextInt((byte)8, (byte)15);
      StringBuilder sb = new StringBuilder();
      char[] chars = TextFormatUtil.ALPHANUMERIC_CHARS.toCharArray();
      int length = chars.length;

      for (int i = 0; i < n; i++) {
         int n2 = length - 1;
         sb.append(chars[DefaultRandomGenerator.instance.nextInt((byte)0, n2)]);
      }

      int n3 = 0;
      int n4 = size - 1;
      int n5 = DefaultRandomGenerator.instance.nextInt((byte)0, n4);

      for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(mc.getNetHandler().getPlayerInfoMap2().values())) {
         if (n3 == n5) {
            if (!networkPlayerInfo.getGameProfile().getName().equals(this.lastSelectedName)) {
               sb = new StringBuilder(networkPlayerInfo.getGameProfile().getName());
            }
            break;
         }

         n3++;
      }

      return this.lastSelectedName = sb.toString();
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new RandomTablistNameSpammerMode((Spammer)obj, (SettingGroup)obj2);
   }

}
