package client.setting;

import client.event.PrimaryListener;

public enum HandlerOptionType implements PrimaryListener {
    UP("up"),
    SIDE("side"),
    NONE("none");

   public final String labelText;
   public static final HandlerOptionType[] ENUM_VALUES = new HandlerOptionType[]{UP, SIDE, NONE};

   @Override
   public String getText() {
      return this.labelText;
   }

   private HandlerOptionType(String text) {
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.getText();
   }
}
