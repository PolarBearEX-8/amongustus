package augustus.anticheat.checks;

import client.setting.NamedOption;

public enum VelocityCheck implements NamedOption {
    SLOWDOWN("Slowdown"),
    SPRINT("Sprint"),
    SWITCH("Switch"),
    SLOT("Slot"),
    ON_GROUND("OnGround"),
    TOGGLE("Toggle"),
    GRIM("Grim"),
    SPAM("Spam"),
    INPUT("Input"),
    BUG("Bug"),
    TIMER("Timer"),
    SNEAK("Sneak"),
    BLINK("Blink"),
    INVALID_PACKET("InvalidPacket"),
    INVALID_DELAY("InvalidDelay");

   public final String labelText;
   public boolean enabled;
   public static final VelocityCheck[] ENUM_VALUES = new VelocityCheck[]{
      SLOWDOWN, SPRINT, SWITCH, SLOT, ON_GROUND, TOGGLE, GRIM, SPAM, INPUT, BUG, TIMER, SNEAK, BLINK, INVALID_PACKET, INVALID_DELAY
   };

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

   private VelocityCheck(String text) {
      this.labelText = text;
   }


}
