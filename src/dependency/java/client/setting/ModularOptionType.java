package client.setting;

import client.event.PrimaryListener;

public enum ModularOptionType implements PrimaryListener {
    STRAIGHT("straight"),
    INNER_LEFT("inner_left"),
    INNER_RIGHT("inner_right"),
    OUTER_LEFT("outer_left"),
    OUTER_RIGHT("outer_right");

   public final String labelText;
   public static final ModularOptionType[] ENUM_VALUES = new ModularOptionType[]{STRAIGHT, INNER_LEFT, INNER_RIGHT, OUTER_LEFT, OUTER_RIGHT};

   @Override
   public String getText() {
      return this.labelText;
   }

   private ModularOptionType(String text) {
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }
}
