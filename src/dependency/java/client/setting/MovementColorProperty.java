package client.setting;

import client.event.PrimaryListener;

public enum MovementColorProperty implements PrimaryListener {
    DEAD_BUSH(0, "dead_bush"),
    GRASS(1, "tall_grass"),
    FERN(2, "fern");

   public static final MovementColorProperty[] ENUM_VALUES2 = new MovementColorProperty[values().length];
   public final int count;
   public final String labelText;
   public static final MovementColorProperty[] ENUM_VALUES = new MovementColorProperty[]{DEAD_BUSH, GRASS, FERN};

   public static MovementColorProperty resolveMovementColorProperty(int count2) {
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

   public int getCount() {
      return this.count;
   }

   private MovementColorProperty(int count2, String text) {
      this.count = count2;
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (MovementColorProperty entry : values()) {
               ENUM_VALUES2[entry.getCount()] = entry;
            }
   }
}
