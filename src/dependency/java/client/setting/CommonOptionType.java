package client.setting;

import client.event.PrimaryListener;

public enum CommonOptionType implements PrimaryListener {
    DEFAULT(0, "default", "default"),
    CHISELED(1, "chiseled", "chiseled"),
    LINES_Y(2, "lines_y", "lines"),
    LINES_X(3, "lines_x", "lines"),
    LINES_Z(4, "lines_z", "lines");

   public static final CommonOptionType[] ENUM_VALUES2 = new CommonOptionType[values().length];
   public final int count;
   public final String labelText2;
   public final String labelText;
   public static final CommonOptionType[] ENUM_VALUES = new CommonOptionType[]{DEFAULT, CHISELED, LINES_Y, LINES_X, LINES_Z};

   public static CommonOptionType resolveCommonOptionType(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES2.length) {
         count3 = 0;
      }

      return ENUM_VALUES2[count3];
   }

   @Override
   public String getText() {
      return this.labelText2;
   }

   public int getCount() {
      return this.count;
   }

   private CommonOptionType(int count2, String text, String text2) {
      this.count = count2;
      this.labelText2 = text;
      this.labelText = text2;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (CommonOptionType entry : values()) {
               ENUM_VALUES2[entry.getCount()] = entry;
            }
   }
}
