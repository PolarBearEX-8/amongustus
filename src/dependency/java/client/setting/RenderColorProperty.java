package client.setting;

import client.event.PrimaryListener;

public enum RenderColorProperty implements PrimaryListener {
    COMPARE("compare"),
    SUBTRACT("subtract");

   public final String labelText;
   public static final RenderColorProperty[] ENUM_VALUES = new RenderColorProperty[]{COMPARE, SUBTRACT};

   @Override
   public String getText() {
      return this.labelText;
   }

   private RenderColorProperty(String text) {
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }
}
