package baritone.api;

import baritone.BaritoneProvider;
import baritone.api.utils.SettingsUtil;

public final class BaritoneAPI {
   private static final SettingsRegistry settingsRegistry = new SettingsRegistry();
   private static final Settings settings = new Settings(settingsRegistry);
   private static final IBaritoneProvider provider;

   static {
      String str = "settings.txt";
      SettingsUtil.readAndApply(settings, str);
      provider = new BaritoneProvider();
   }

   public static IBaritoneProvider getProvider() {
      return provider;
   }

   public static SettingsRegistry getSettingsRegistry() {
      return settingsRegistry;
   }

   public static Settings getSettings() {
      return settings;
   }
}
