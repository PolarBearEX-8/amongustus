package client.setting;

import client.event.PrimaryListener;

public enum CombatDistanceProperty implements PrimaryListener {
   UPPER,
   LOWER;

   public static final CombatDistanceProperty[] ENUM_VALUES = values();

   @Override
   public String getText() {
      return this == UPPER ? "upper" : "lower";
   }

   @Override
   public String toString() {
      return this.getText();
   }
}
