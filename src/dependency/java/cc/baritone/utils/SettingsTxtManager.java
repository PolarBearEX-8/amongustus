package cc.baritone.utils;

import cc.Settings;
import cc.SettingsUtil;

public final class SettingsTxtManager {
   public static final AuxiliaryMapEntry auxiliaryMapEntry = new AuxiliaryMapEntry();
   public static final Settings settings = new Settings(auxiliaryMapEntry);
   public static final ExtraListener extraListener = new ListenerAdapter();

   public static ExtraListener getExtraListener() {
      return extraListener;
   }

   public static Settings getSettings() {
      return settings;
   }

   public static AuxiliaryMapEntry getAuxiliaryMapEntry() {
      return auxiliaryMapEntry;
   }

   static {
      String text = "settings.txt";
      SettingsUtil.readAndApply(settings, text);
   }
}
