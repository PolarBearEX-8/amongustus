package client.setting;

import java.awt.Color;

public enum CombatOptionProperty {
    FIXED(Color.ORANGE),
    IMPROVED(Color.YELLOW),
    REMOVED(Color.RED),
    ADDED(Color.GREEN);

   public static final CombatOptionProperty[] ENUM_VALUES = new CombatOptionProperty[]{ADDED, IMPROVED, FIXED, REMOVED};
   public final Color color;

   public Color getColor() {
      return this.color;
   }

   private CombatOptionProperty(Color color2) {
      this.color = color2;
   }


}
