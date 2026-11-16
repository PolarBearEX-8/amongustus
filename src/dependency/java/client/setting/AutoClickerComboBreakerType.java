package client.setting;

public enum AutoClickerComboBreakerType implements NamedOption {
    COMBO_BREAKER("ComboBreaker"),
    HIT_SELECTION("HitSelection");

   public final String labelText;
   public boolean enabled;
   public static final AutoClickerComboBreakerType[] ENUM_VALUES = new AutoClickerComboBreakerType[]{COMBO_BREAKER, HIT_SELECTION};

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static boolean checkAutoClickerComboBreakerType(AutoClickerComboBreakerType autoClickerComboBreakerType) {
      return autoClickerComboBreakerType.enabled;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private AutoClickerComboBreakerType(String text) {
      this.labelText = text;
   }


}
