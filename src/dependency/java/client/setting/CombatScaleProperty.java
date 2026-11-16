package client.setting;

public enum CombatScaleProperty {
   HALF_EVEN(6),
   DOWN(1),
   FLOOR(3),
   UNNECESSARY(7),
   CEILING(2),
   HALF_UP(4),
   UP(0),
   HALF_DOWN(5);

   public final int count;

   public static CombatScaleProperty resolveCombatScaleProperty(int count2) {
      if (count2 == 7) {
         return UNNECESSARY;
      } else if (count2 == 4) {
         return HALF_UP;
      } else if (count2 == 5) {
         return HALF_DOWN;
      } else if (count2 == 6) {
         return HALF_EVEN;
      } else if (count2 == 1) {
         return DOWN;
      } else if (count2 == 2) {
         return CEILING;
      } else if (count2 == 0) {
         return UP;
      } else if (count2 == 3) {
         return FLOOR;
      } else {
         throw new IllegalArgumentException("argument out of range");
      }
   }

   private CombatScaleProperty(int count2) {
      this.count = count2;
   }

}
