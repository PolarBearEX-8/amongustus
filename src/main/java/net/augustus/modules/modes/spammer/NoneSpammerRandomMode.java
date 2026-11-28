package net.augustus.modules.modes.spammer;

import java.util.function.BiFunction;
import net.augustus.modules.fun.Spammer;
import net.augustus.settings.SettingGroup;

public class NoneSpammerRandomMode extends SpammerRandomMode {

   public NoneSpammerRandomMode(Spammer spammer, SettingGroup doubleValue) {
      super(spammer, doubleValue, "None");
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new NoneSpammerRandomMode((Spammer)obj, (SettingGroup)obj2);
   }

   @Override
   public String generateRandomSuffix() {
      return "";
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

}
