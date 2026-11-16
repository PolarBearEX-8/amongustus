package client.setting;

import client.event.PrimaryListener;

public enum SystemOptionType implements PrimaryListener {
    ROUGH(0, "prismarine", "rough"),
    BRICKS(1, "prismarine_bricks", "bricks"),
    DARK(2, "dark_prismarine", "dark");

   public static final SystemOptionType[] ENUM_VALUES2 = new SystemOptionType[values().length];
   public final int count;
   public final String labelText;
   public final String labelText2;
   public static final SystemOptionType[] ENUM_VALUES = new SystemOptionType[]{ROUGH, BRICKS, DARK};

   public static SystemOptionType resolveSystemOptionType(int count2) {
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

   private SystemOptionType(int count2, String text, String text2) {
      this.count = count2;
      this.labelText = text;
      this.labelText2 = text2;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (SystemOptionType entry : values()) {
               ENUM_VALUES2[entry.getCount()] = entry;
            }
   }
}
