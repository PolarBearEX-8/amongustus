package net.optifine.shaders.config;

import net.optifine.Lang;

public class PropertyDefaultTrueFalse extends Property {
   public static final String[] PROPERTY_VALUES = new String[]{"default", "true", "false"};
   public static final String[] USER_VALUES = new String[]{"Default", "ON", "OFF"};

   public boolean isTrue() {
      return this.getValue() == 1;
   }

   public boolean isFalse() {
      return this.getValue() == 2;
   }

   public PropertyDefaultTrueFalse(String str, String str2, int n) {
      super(str, PROPERTY_VALUES, str2, USER_VALUES, n);
   }

   public boolean isDefault() {
      return this.getValue() == 0;
   }

   @Override
   public String getUserValue() {
      return this.isDefault() ? Lang.getDefault() : (this.isTrue() ? Lang.getOn() : (this.isFalse() ? Lang.getOff() : super.getUserValue()));
   }
}
