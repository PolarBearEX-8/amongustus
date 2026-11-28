package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;

public enum PingFlushConditionOption implements SelectableOption {
   STANDING_STILL("StandingStill", true),
   SCAFFOLD_TOGGLED("ScaffoldToggled", true),
   PLACE_BLOCK("PlaceBlocks", true),
   BREAK_BLOCKS("BreakBlocks", true),
   HOLDING_BLOCKS("HoldingBlocks", false),
   USE_ITEM("UseItem", true),
   OPEN_GUI("OpenedGui", true),
   INVENTORY_ACTIONS("InventoryActions", true),
   VELOCITY("Velocity", false),
   EXPLOSION("Explosion", false),
   TELEPORT("Teleport", true),
   SPRINT_RESET("SprintReset", false),
   SPLASH_POTION("SplashPotion", false),
   ATTACK("Attack", true),
   PROJECTILE("Projectile", false),
   SLOT_SWITCH("SlotSwitch", false),
   WORLD_CHANGE("WorldChange", false);

private final String name;
   private boolean selected = true;
   private DoubleValue flushDurationSetting;
   private final TimeHelper timeHelper = new TimeHelper();
private PingFlushConditionOption(String str, boolean flag) {
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

   public static DoubleValue setFlushDurationSetting(PingFlushConditionOption pingFlushConditionOption, DoubleValue stringValue) {
      return pingFlushConditionOption.flushDurationSetting = stringValue;
   }

   public static TimeHelper getFlushTimer(PingFlushConditionOption pingFlushConditionOption) {
      return pingFlushConditionOption.timeHelper;
   }

   public static boolean isOptionSelected(PingFlushConditionOption pingFlushConditionOption) {
      return pingFlushConditionOption.selected;
   }

   public static DoubleValue getFlushDurationSetting(PingFlushConditionOption pingFlushConditionOption) {
      return pingFlushConditionOption.flushDurationSetting;
   }
}
