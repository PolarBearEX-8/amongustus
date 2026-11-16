package client.setting;

public enum TargetESPKillAuraType implements NamedOption {
    KILL_AURA("KillAura"),
    AIM_BOT("AimBot"),
    AIM_ASSIST("AimAssist"),
    AUTO_ROD("AutoRod"),
    LEGIT("Legit");

   public final String labelText;
   public boolean enabled;
   public static final TargetESPKillAuraType[] ENUM_VALUES = new TargetESPKillAuraType[]{KILL_AURA, AIM_BOT, AIM_ASSIST, AUTO_ROD, LEGIT};

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private TargetESPKillAuraType(String text) {
      this.labelText = text;
   }


}
