package client.setting;

public enum KillAuraHurtTime implements NamedOption {
    HURT_TIME_0("0", 0),
    HURT_TIME_1("1", 1),
    HURT_TIME_2("2", 2),
    HURT_TIME_3("3", 3),
    HURT_TIME_4("4", 4),
    HURT_TIME_5("5", 5),
    HURT_TIME_6("6", 6),
    HURT_TIME_7("7", 7),
    HURT_TIME_8("8", 8),
    HURT_TIME_9("9", 9),
    HURT_TIME_10("10", 10);

   public final String labelText;
   public final int count;
   public boolean enabled = true;
   public static final KillAuraHurtTime[] ENUM_VALUES = new KillAuraHurtTime[]{
      HURT_TIME_0, HURT_TIME_1, HURT_TIME_2, HURT_TIME_3, HURT_TIME_4, HURT_TIME_5, HURT_TIME_6, HURT_TIME_7, HURT_TIME_8, HURT_TIME_9, HURT_TIME_10
   };

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static int getIndex(KillAuraHurtTime killAuraHurtTime) {
      return killAuraHurtTime.count;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static boolean checkKillAuraHurtTime(KillAuraHurtTime killAuraHurtTime) {
      return killAuraHurtTime.enabled;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private KillAuraHurtTime(String text, int count2) {
      this.labelText = text;
      this.count = count2;
   }


}
