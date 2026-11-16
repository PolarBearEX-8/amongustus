package client.setting;

import client.event.PrimaryListener;

public enum RenderOptionProperty implements PrimaryListener {
    NORTH_WEST(1, "north_west"),
    NORTH(2, "north"),
    NORTH_EAST(3, "north_east"),
    WEST(4, "west"),
    CENTER(5, "center"),
    EAST(6, "east"),
    SOUTH_WEST(7, "south_west"),
    SOUTH(8, "south"),
    SOUTH_EAST(9, "south_east"),
    STEM(10, "stem"),
    ALL_INSIDE(0, "all_inside"),
    ALL_OUTSIDE(14, "all_outside"),
    ALL_STEM(15, "all_stem");

   public static final RenderOptionProperty[] ENUM_VALUES = new RenderOptionProperty[16];
   public final int count;
   public final String labelText;
   public static final RenderOptionProperty[] ENUM_VALUES2 = new RenderOptionProperty[]{
      NORTH_WEST, NORTH, NORTH_EAST, WEST, CENTER, EAST, SOUTH_WEST, SOUTH, SOUTH_EAST, STEM, ALL_INSIDE, ALL_OUTSIDE, ALL_STEM
   };

   public int getCount() {
      return this.count;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static RenderOptionProperty resolveRenderOptionProperty(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES.length) {
         count3 = 0;
      }

      RenderOptionProperty renderOptionProperty = ENUM_VALUES[count3];
      return renderOptionProperty == null ? ENUM_VALUES[0] : renderOptionProperty;
   }

   private RenderOptionProperty(int count2, String text) {
      this.count = count2;
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (RenderOptionProperty entry : values()) {
               ENUM_VALUES[entry.getCount()] = entry;
            }
   }
}
