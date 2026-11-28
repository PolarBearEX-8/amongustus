package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum NoSlowRestItemOption implements SelectableOption {
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

private final String name;
   private boolean selected;
private NoSlowRestItemOption(String str) {
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
