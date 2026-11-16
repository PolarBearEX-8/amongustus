package client.util;

import client.setting.WorldSettingsGameType;

public final class AdvancedLongEntry {
   public final long timestamp;
   public final WorldSettingsGameType worldSettingsGameType;
   public final boolean enabled2;
   public final boolean enabled;
   public final InfoManager infoManager;
   public boolean enabled3;
   public boolean enabled4;
   public String labelText = "";

   public AdvancedLongEntry getAdvancedLongEntry() {
      this.enabled3 = true;
      return this;
   }

   public static WorldSettingsGameType resolveWorldSettingsGameType(int count) {
      return WorldSettingsGameType.resolveWorldSettingsGameType(count);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public WorldSettingsGameType getWorldSettingsGameType() {
      return this.worldSettingsGameType;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public AdvancedLongEntry withLabelText(String text) {
      this.labelText = text;
      return this;
   }

   public InfoManager getInfoManager() {
      return this.infoManager;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public AdvancedLongEntry getAdvancedLongEntryAsAdvancedLongEntr() {
      this.enabled4 = true;
      return this;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public boolean isEnabled4() {
      return this.enabled4;
   }

   public AdvancedLongEntry(long value, WorldSettingsGameType worldSettingsGameType2, boolean flag, boolean flag2, InfoManager infoManager2) {
      this.timestamp = value;
      this.worldSettingsGameType = worldSettingsGameType2;
      this.enabled2 = flag;
      this.enabled = flag2;
      this.infoManager = infoManager2;
   }

   public AdvancedLongEntry(RandomSeedManager randomSeedManager) {
      this(randomSeedManager.getTimestamp5(), randomSeedManager.getWorldSettingsGameType(), randomSeedManager.isEnabled7(), randomSeedManager.isEnabled2(), randomSeedManager.getInfoManager());
   }
}
