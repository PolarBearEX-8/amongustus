package client.setting;

import client.event.PrimaryListener;

public enum ExternalOptionType implements PrimaryListener {
    SUNFLOWER(0, "sunflower"),
    SYRINGA(1, "syringa"),
    GRASS(2, "double_grass", "grass"),
    FERN(3, "double_fern", "fern"),
    ROSE(4, "double_rose", "rose"),
    PAEONIA(5, "paeonia");

   public static final ExternalOptionType[] ENUM_VALUES2 = new ExternalOptionType[values().length];
   public final int count;
   public final String labelText;
   public final String labelText2;
   public static final ExternalOptionType[] ENUM_VALUES = new ExternalOptionType[]{SUNFLOWER, SYRINGA, GRASS, FERN, ROSE, PAEONIA};

   public String getLabelText2() {
      return this.labelText2;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   public static ExternalOptionType resolveExternalOptionType(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES2.length) {
         count3 = 0;
      }

      return ENUM_VALUES2[count3];
   }

   private ExternalOptionType(int count2, String text) {
      this(count2, text, text);
   }

   private ExternalOptionType(int count2, String text, String text2) {
      this.count = count2;
      this.labelText = text;
      this.labelText2 = text2;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (ExternalOptionType entry : values()) {
               ENUM_VALUES2[entry.getCount()] = entry;
            }
   }
}
