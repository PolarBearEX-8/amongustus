package client.setting;

import client.event.PrimaryListener;

public enum CombatToggleProperty implements PrimaryListener {
    DEFAULT(0, "red_sandstone", "default"),
    CHISELED(1, "chiseled_red_sandstone", "chiseled"),
    SMOOTH(2, "smooth_red_sandstone", "smooth");

   public static final CombatToggleProperty[] ENUM_VALUES2 = new CombatToggleProperty[values().length];
   public final int count;
   public final String labelText;
   public final String labelText2;
   public static final CombatToggleProperty[] ENUM_VALUES = new CombatToggleProperty[]{DEFAULT, CHISELED, SMOOTH};

   public static CombatToggleProperty resolveCombatToggleProperty(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES2.length) {
         count3 = 0;
      }

      return ENUM_VALUES2[count3];
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public int getCount() {
      return this.count;
   }

   private CombatToggleProperty(int count2, String text, String text2) {
      this.count = count2;
      this.labelText = text;
      this.labelText2 = text2;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (CombatToggleProperty entry : values()) {
               ENUM_VALUES2[entry.getCount()] = entry;
            }
   }
}
