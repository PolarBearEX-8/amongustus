package client.util;

import client.setting.NamedOption;
import client.setting.NumberSetting;

public enum DisablerCondition implements NamedOption {
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
    ATTACK("Attack", true);

   public final String labelText;
   public boolean enabled = true;
   public NumberSetting numberSetting;
   public final TimeHelper timer = new TimeHelper();
   public static final DisablerCondition[] ENUM_VALUES = new DisablerCondition[]{
      STANDING_STILL,
      SCAFFOLD_TOGGLED,
      PLACE_BLOCK,
      BREAK_BLOCKS,
      HOLDING_BLOCKS,
      USE_ITEM,
      OPEN_GUI,
      INVENTORY_ACTIONS,
      VELOCITY,
      EXPLOSION,
      TELEPORT,
      SPRINT_RESET,
      SPLASH_POTION,
      ATTACK
   };

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static TimeHelper getTime(DisablerCondition disablerCondition) {
      return disablerCondition.timer;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static NumberSetting getNumberSetting(DisablerCondition disablerCondition, NumberSetting setting) {
      return disablerCondition.numberSetting = setting;
   }

   public static boolean checkDisablerCondition(DisablerCondition disablerCondition) {
      return disablerCondition.enabled;
   }

   public static NumberSetting getNumberSetting(DisablerCondition disablerCondition) {
      return disablerCondition.numberSetting;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private DisablerCondition(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
