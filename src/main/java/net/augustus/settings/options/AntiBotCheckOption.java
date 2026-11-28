package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum AntiBotCheckOption implements SelectableOption {
   ADVANCED("Advanced"),
   HYPIXEL("Hypixel"),
   INTAVE("Intave"),
   NULL_TEAM("NullTeam"),
   ILLEGAL_PITCH("IllegalPitch"),
   TICKS_EXISTED("TicksExisted"),
   NEGATIVE_HEALTH("NegativeHealth"),
   ZERO_PING("ZeroPing"),
   TAB_LIST("IsInTabList"),
   INVALID_ENTITY_ID("InvalidEntityID"),
   INVALID_NAME("InvalidName"),
   NEVER_ON_GROUND("NeverOnGround"),
   SWING("Swinged"),
   ITEM("ItemInHand"),
   ARMOR("Armor"),
   SOUND("Sound");

private String name;
   private boolean selected;
private AntiBotCheckOption(String str) {
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

   public static boolean isOptionSelected(AntiBotCheckOption antiBotCheckOption) {
      return antiBotCheckOption.selected;
   }
}
