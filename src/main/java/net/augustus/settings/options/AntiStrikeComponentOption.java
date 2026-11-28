package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum AntiStrikeComponentOption implements SelectableOption {
   CHAT("Chat"),
   TAB_LIST("TabList"),
   SCOREBOARD("Scoreboard"),
   ITEM_NAMES("ItemNames"),
   PLAYER_NAME_TAGS("PlayerNameTags"),
   OTHER_NAME_TAGS("OtherNameTags"),
   TOOL_TIPS("ToolTips"),
   SIGNS("Signs"),
   TITLE("Title"),
   BOOKS("Books"),
   CHEST_NAMES("ChestNames"),
   MAPS("Maps");

private boolean selected = true;
   private String name;
private AntiStrikeComponentOption(String str) {
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

   public static boolean isOptionSelected(AntiStrikeComponentOption option) {
      return option.selected;
   }
}
