package client.setting;

public enum CombatDelayProperty {
   IDLE("idle", 0),
   MOVING("moving", 1),
   SNEAKING("sneaking", 2);

   public static final CombatDelayProperty[] VALUES = values();
   public int count;
   public String labelText;

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   private CombatDelayProperty(String text, int count2) {
      this.labelText = text;
      this.count = count2;
   }


}
