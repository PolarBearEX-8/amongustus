package client.setting;

public enum ActiveOptionType {
    MOTION(6, CombatIntervalProperty.Z, "Random Motion", ""),
    FORWARD(3, CombatIntervalProperty.X, "Movement Forward", ""),
    RESIZE(5, (CombatIntervalProperty)null, "Resize Effect", ""),
    BACKWARD(4, CombatIntervalProperty.Z, "Movement Backward", ""),
    BOUNCE(1, CombatIntervalProperty.Y, "Bounce Effect", ""),
    ROTATE(2, CombatIntervalProperty.Y, "Rotation", "");

   public final String labelText2;
   public final String labelText;
   public final int count;
   public final CombatIntervalProperty combatIntervalProperty;
   public static final ActiveOptionType[] VALUES = values();

   public static ActiveOptionType resolveActiveOptionType(int count2) {
      for (ActiveOptionType activeOptionType : values()) {
         if (activeOptionType.getCount() == count2) {
            return activeOptionType;
         }
      }

      return null;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public CombatIntervalProperty getCombatIntervalProperty() {
      return this.combatIntervalProperty;
   }

   public int getCount() {
      return this.count;
   }

   private ActiveOptionType(int count2, CombatIntervalProperty combatIntervalProperty2, String text, String text2) {
      this.count = count2;
      this.combatIntervalProperty = combatIntervalProperty2;
      this.labelText2 = text;
      this.labelText = text2;
   }


}
