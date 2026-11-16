package client.setting;

import client.event.PrimaryListener;

public enum CombatStateProperty implements PrimaryListener {
    HEAD("head"),
    FOOT("foot");

   public final String labelText;
   public static final CombatStateProperty[] ENUM_VALUES = new CombatStateProperty[]{HEAD, FOOT};

   @Override
   public String getText() {
      return this.labelText;
   }

   private CombatStateProperty(String text) {
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }
}
