package client.setting;

public enum CombatBooleanProperty {
    ANY_VALUE,
    BOOLEAN_VALUE,
    NUMERICAL_VALUE;

   public static final CombatBooleanProperty[] ENUM_VALUES = new CombatBooleanProperty[]{ANY_VALUE, BOOLEAN_VALUE, NUMERICAL_VALUE};

   private CombatBooleanProperty() {
   }


}
