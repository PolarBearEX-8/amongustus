package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum NoSlowBowOption implements SelectableOption {
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

private final String name;
   private boolean selected;
private NoSlowBowOption(String str) {
      this.name = str;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }
}
