package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum NoSlowSwordOption implements SelectableOption {
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
   BLINK2("Blink2"),
   INVALID_PACKET("InvalidPacket"),
   INVALID_DELAY("InvalidDelay");

private final String name;
   private boolean selected;
private NoSlowSwordOption(String str) {
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
