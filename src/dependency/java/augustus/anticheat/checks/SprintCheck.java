package augustus.anticheat.checks;

import client.setting.NamedOption;

public enum SprintCheck implements NamedOption {
    SLOWDOWN("Slowdown"),
    SPRINT("Sprint"),
    SWITCH("Switch"),
    SWITCH_CUSTOM("SwitchCustom"),
    SLOT("Slot"),
    ON_GROUND("OnGround"),
    TOGGLE("Toggle"),
    SPAM("Spam"),
    PLACE("Place"),
    INPUT("Input"),
    TIMER("Timer"),
    SNEAK("Sneak"),
    INVALID_PACKET("InvalidPacket");

   public final String labelText;
   public boolean enabled;
   public static final SprintCheck[] ENUM_VALUES = new SprintCheck[]{
      SLOWDOWN, SPRINT, SWITCH, SWITCH_CUSTOM, SLOT, ON_GROUND, TOGGLE, SPAM, PLACE, INPUT, TIMER, SNEAK, INVALID_PACKET
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

   private SprintCheck(String text) {
      this.labelText = text;
   }


}
