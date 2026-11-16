package client.setting;

public enum CombatIntervalProperty {
   Z(3, 0, 0, 1),
   X(1, 1, 0, 0),
   Y(2, 0, 1, 0);

   public final int y;
   public final int x;
   public final int z;
   public final int count;

   public int getCount() {
      return this.count;
   }

   public static CombatIntervalProperty resolveCombatIntervalProperty(int count2) {
      for (CombatIntervalProperty combatIntervalProperty : values()) {
         if (combatIntervalProperty.getCount() == count2) {
            return combatIntervalProperty;
         }
      }

      return null;
   }

   private CombatIntervalProperty(int count2, int count3, int count4, int count5) {
      this.count = count2;
      this.x = count3;
      this.y = count4;
      this.z = count5;
   }
}
