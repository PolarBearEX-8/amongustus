package client.setting;

import client.event.PrimaryListener;

public enum ContextOptionType implements PrimaryListener {
   LEFT,
   RIGHT;

   public static final ContextOptionType[] ENUM_VALUES = values();

   @Override
   public String getText() {
      return this == LEFT ? "left" : "right";
   }

   @Override
   public String toString() {
      return this.getText();
   }
}
