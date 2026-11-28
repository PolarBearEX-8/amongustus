package net.optifine.shaders.config;

import net.optifine.config.Config;

public class PropertyDefaultFastFancyOff extends Property {
   public static final String[] PROPERTY_VALUES = new String[]{"default", "fast", "fancy", "off"};
   public static final String[] USER_VALUES = new String[]{"Default", "Fast", "Fancy", "OFF"};

   public boolean isOff() {
      return this.getValue() == 3;
   }

   public PropertyDefaultFastFancyOff(String str, String str2, int n) {
      super(str, PROPERTY_VALUES, str2, USER_VALUES, n);
   }

   public boolean isDefault() {
      return this.getValue() == 0;
   }

   public boolean isFast() {
      return this.getValue() == 1;
   }

   @Override
   public boolean setPropertyValue(String str) {
      if (Config.equals(str, "none")) {
         str = "off";
      }

      return super.setPropertyValue(str);
   }

   public boolean isFancy() {
      return this.getValue() == 2;
   }
}
