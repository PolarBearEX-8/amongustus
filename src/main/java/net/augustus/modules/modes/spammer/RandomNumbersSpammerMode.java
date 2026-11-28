package net.augustus.modules.modes.spammer;

import net.augustus.utils.math.DefaultRandomGenerator;
import java.util.function.BiFunction;
import net.augustus.modules.fun.Spammer;
import net.augustus.settings.SettingGroup;

public class RandomNumbersSpammerMode extends SpammerRandomMode {

   public RandomNumbersSpammerMode(Spammer spammer, SettingGroup doubleValue) {
      super(spammer, doubleValue, "RandomNumbers");
   }

   @Override
   public String generateRandomSuffix() {
      return String.valueOf(DefaultRandomGenerator.instance.nextLong(1000000000L, 100000000000000L));
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
      return (obj, obj2) -> new RandomNumbersSpammerMode((Spammer)obj, (SettingGroup)obj2);
   }

}
