package client.setting;

import client.event.PrimaryListener;

public enum CombatAngleProperty implements PrimaryListener {
   UPPER,
   LOWER;

   public static final CombatAngleProperty[] ENUM_VALUES = values();

   @Override
   public String getText() {
      return this == UPPER ? "upper" : "lower";
   }

   @Override
   public String toString() {
      return this.getText();
   }
}
