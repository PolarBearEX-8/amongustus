package client.setting;

import client.event.PrimaryListener;

public enum MovementToggleProperty implements PrimaryListener {
    DEFAULT("normal"),
    STICKY("sticky");

   public final String labelText;
   public static final MovementToggleProperty[] ENUM_VALUES = new MovementToggleProperty[]{DEFAULT, STICKY};

   @Override
   public String getText() {
      return this.labelText;
   }

   private MovementToggleProperty(String text) {
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }
}
