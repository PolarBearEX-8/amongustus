package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum PulseBlinkFlushConditionOption implements SelectableOption {
   STANDING_STILL("StandingStill", true),
   SCAFFOLD_TOGGLED("ScaffoldToggled", true),
   PLACE_BLOCK("PlaceBlocks", true),
   BREAK_BLOCKS("BreakBlocks", true),
   USE_ITEM("UseItem", true),
   OPEN_GUI("OpenedGui", true),
   INVENTORY_ACTIONS("InventoryActions", true),
   VELOCITY("Velocity", false),
   EXPLOSION("Explosion", false),
   TELEPORT("Teleport", true),
   SPRINT_RESET("SprintReset", false),
   SPLASH_POTION("SplashPotion", false),
   SLOT_SWITCH("SlotSwitch", false),
   WORLD_CHANGE("WorldChange", false);

private final String name;
   private boolean selected = true;
private PulseBlinkFlushConditionOption(String str, boolean flag) {
      this.name = str;
      this.selected = flag;
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

   public static boolean isOptionSelected(PulseBlinkFlushConditionOption option) {
      return option.selected;
   }
}
