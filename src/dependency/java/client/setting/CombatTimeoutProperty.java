package client.setting;

import client.event.PrimaryListener;

public enum CombatTimeoutProperty implements PrimaryListener {
    DEFAULT(0, "stonebrick", "default"),
    MOSSY(1, "mossy_stonebrick", "mossy"),
    CRACKED(2, "cracked_stonebrick", "cracked"),
    CHISELED(3, "chiseled_stonebrick", "chiseled");

   public static final CombatTimeoutProperty[] ENUM_VALUES = new CombatTimeoutProperty[values().length];
   public final int count;
   public final String labelText;
   public final String labelText2;
   public static final CombatTimeoutProperty[] ENUM_VALUES2 = new CombatTimeoutProperty[]{DEFAULT, MOSSY, CRACKED, CHISELED};

   public static CombatTimeoutProperty resolveCombatTimeoutProperty(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES.length) {
         count3 = 0;
      }

      return ENUM_VALUES[count3];
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   private CombatTimeoutProperty(int count2, String text, String text2) {
      this.count = count2;
      this.labelText = text;
      this.labelText2 = text2;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (CombatTimeoutProperty entry : values()) {
               ENUM_VALUES[entry.getCount()] = entry;
            }
   }
}
