package client.util;

import client.setting.WorldSettingsGameType;

public class ExtendedCustom implements Comparable {
   public final String labelText;
   public final String labelText2;
   public final long timestamp;
   public final long timestamp2;
   public final boolean enabled;
   public final WorldSettingsGameType worldSettingsGameType;
   public final boolean enabled2;
   public final boolean enabled3;

   public String getLabelText() {
      return this.labelText;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public WorldSettingsGameType getWorldSettingsGameType() {
      return this.worldSettingsGameType;
   }

   public int computeCount(ExtendedCustom extendedCustom) {
      return this.timestamp < extendedCustom.timestamp ? 1 : (this.timestamp > extendedCustom.timestamp ? -1 : this.labelText.compareTo(extendedCustom.labelText));
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public ExtendedCustom(String text, String text2, long value, long value2, WorldSettingsGameType worldSettingsGameType2, boolean flag, boolean flag2, boolean flag3) {
      this.labelText = text;
      this.labelText2 = text2;
      this.timestamp = value;
      this.timestamp2 = value2;
      this.worldSettingsGameType = worldSettingsGameType2;
      this.enabled = flag;
      this.enabled2 = flag2;
      this.enabled3 = flag3;
   }

   public long getTimestamp2() {
      return this.timestamp2;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   @Override
   public int compareTo(Object entry) {
      return this.computeCount((ExtendedCustom)entry);
   }
}
