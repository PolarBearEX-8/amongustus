package client.setting;

import client.event.PrimaryListener;

public enum MovementChoiceProperty implements PrimaryListener {
    TOP("top"),
    BOTTOM("bottom");

   public final String labelText;
   public static final MovementChoiceProperty[] ENUM_VALUES = new MovementChoiceProperty[]{TOP, BOTTOM};

   @Override
   public String getText() {
      return this.labelText;
   }

   private MovementChoiceProperty(String text) {
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }
}
