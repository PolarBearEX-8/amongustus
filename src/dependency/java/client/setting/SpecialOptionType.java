package client.setting;

import client.event.PrimaryListener;

public enum SpecialOptionType implements PrimaryListener {
    NORMAL(0, "cobblestone", "normal"),
    MOSSY(1, "mossy_cobblestone", "mossy");

   public static final SpecialOptionType[] ENUM_VALUES = new SpecialOptionType[values().length];
   public final int count;
   public final String labelText2;
   public String labelText;
   public static final SpecialOptionType[] ENUM_VALUES2 = new SpecialOptionType[]{NORMAL, MOSSY};

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public String getText() {
      return this.labelText2;
   }

   public static SpecialOptionType resolveSpecialOptionType(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES.length) {
         count3 = 0;
      }

      return ENUM_VALUES[count3];
   }

   private SpecialOptionType(int count2, String text, String text2) {
      this.count = count2;
      this.labelText2 = text;
      this.labelText = text2;
   }



   @Override
   public String toString() {
      return this.labelText2;
   }

   static {
      for (SpecialOptionType entry : values()) {
               ENUM_VALUES[entry.getCount()] = entry;
            }
   }
}
