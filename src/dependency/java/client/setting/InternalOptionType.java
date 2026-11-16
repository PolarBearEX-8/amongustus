package client.setting;

import client.event.PrimaryListener;

public enum InternalOptionType implements PrimaryListener {
    DEFAULT(0, "sandstone", "default"),
    CHISELED(1, "chiseled_sandstone", "chiseled"),
    SMOOTH(2, "smooth_sandstone", "smooth");

   public static final InternalOptionType[] ENUM_VALUES2 = new InternalOptionType[values().length];
   public final int count;
   public final String labelText;
   public final String labelText2;
   public static final InternalOptionType[] ENUM_VALUES = new InternalOptionType[]{DEFAULT, CHISELED, SMOOTH};

   public String getLabelText2() {
      return this.labelText2;
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static InternalOptionType resolveInternalOptionType(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES2.length) {
         count3 = 0;
      }

      return ENUM_VALUES2[count3];
   }

   private InternalOptionType(int count2, String text, String text2) {
      this.count = count2;
      this.labelText = text;
      this.labelText2 = text2;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (InternalOptionType entry : values()) {
               ENUM_VALUES2[entry.getCount()] = entry;
            }
   }
}
