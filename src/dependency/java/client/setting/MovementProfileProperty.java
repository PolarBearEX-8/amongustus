package client.setting;

import client.event.PrimaryListener;

public enum MovementProfileProperty implements PrimaryListener {
    NORTH_SOUTH(0, "north_south"),
    EAST_WEST(1, "east_west"),
    ASCENDING_EAST(2, "ascending_east"),
    ASCENDING_WEST(3, "ascending_west"),
    ASCENDING_NORTH(4, "ascending_north"),
    ASCENDING_SOUTH(5, "ascending_south"),
    SOUTH_EAST(6, "south_east"),
    SOUTH_WEST(7, "south_west"),
    NORTH_WEST(8, "north_west"),
    NORTH_EAST(9, "north_east");

   public static final MovementProfileProperty[] ENUM_VALUES = new MovementProfileProperty[values().length];
   public final int count;
   public final String labelText;
   public static final MovementProfileProperty[] ENUM_VALUES2 = new MovementProfileProperty[]{
      NORTH_SOUTH, EAST_WEST, ASCENDING_EAST, ASCENDING_WEST, ASCENDING_NORTH, ASCENDING_SOUTH, SOUTH_EAST, SOUTH_WEST, NORTH_WEST, NORTH_EAST
   };

   @Override
   public String getText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   public boolean isActive() {
      return this == ASCENDING_NORTH || this == ASCENDING_EAST || this == ASCENDING_SOUTH || this == ASCENDING_WEST;
   }

   public static MovementProfileProperty resolveMovementProfileProperty(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES.length) {
         count3 = 0;
      }

      return ENUM_VALUES[count3];
   }

   private MovementProfileProperty(int count2, String text) {
      this.count = count2;
      this.labelText = text;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (MovementProfileProperty entry : values()) {
               ENUM_VALUES[entry.getCount()] = entry;
            }
   }
}
