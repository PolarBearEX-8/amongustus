package augustus.anticheat.checks;

import client.setting.NamedOption;

public enum NoSlowCheck implements NamedOption {
    SLOWDOWN("Slowdown"),
    SPRINT("Sprint"),
    SWITCH("Switch"),
    SWITCH_CUSTOM("SwitchCustom"),
    SLOT("Slot"),
    ON_GROUND("OnGround"),
    TOGGLE("Toggle"),
    SPAM("Spam"),
    SPAM2("Spam2"),
    INPUT("Input"),
    UN_TOGGLE("UnToggle"),
    BUG("Bug"),
    GRIM("GrimDrop"),
    TIMER("Timer"),
    SNEAK("Sneak"),
    INVALID_PACKET("InvalidPacket");

   public final String labelText;
   public boolean enabled;
   public static final NoSlowCheck[] ENUM_VALUES = new NoSlowCheck[]{
      SLOWDOWN, SPRINT, SWITCH, SWITCH_CUSTOM, SLOT, ON_GROUND, TOGGLE, SPAM, SPAM2, INPUT, UN_TOGGLE, BUG, GRIM, TIMER, SNEAK, INVALID_PACKET
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

   private NoSlowCheck(String text) {
      this.labelText = text;
   }


}
