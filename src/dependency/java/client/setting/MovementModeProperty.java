package client.setting;

import client.event.PrimaryListener;

public enum MovementModeProperty implements PrimaryListener {
    TOP("top"),
    BOTTOM("bottom");

   public final String labelText;
   public static final MovementModeProperty[] ENUM_VALUES = new MovementModeProperty[]{TOP, BOTTOM};

   @Override
   public String getText() {
      return this.labelText;
   }

   private MovementModeProperty(String text) {
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }
}
