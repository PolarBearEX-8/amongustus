package client.setting;

import client.event.PrimaryListener;

public enum VClipDirection implements PrimaryListener {
    TOP("top"),
    BOTTOM("bottom");

   public final String labelText;
   public static final VClipDirection[] ENUM_VALUES = new VClipDirection[]{TOP, BOTTOM};

   @Override
   public String getText() {
      return this.labelText;
   }

   private VClipDirection(String text) {
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }
}
