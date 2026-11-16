package client.setting;

public enum AntiStrikeChatType implements NamedOption {
    CHAT("Chat"),
    TAB_LIST("TabList"),
    SCOREBOARD("Scoreboard"),
    ITEM_NAMES("ItemNames"),
    NAME_TAGS("NameTags"),
    TOOL_TIPS("ToolTips"),
    SIGNS("Signs"),
    TITLE("Title"),
    BOOKS("Books"),
    CHEST_NAMES("ChestNames"),
    MAPS("Maps");

   public boolean enabled = true;
   public String labelText;
   public static final AntiStrikeChatType[] ENUM_VALUES = new AntiStrikeChatType[]{
      CHAT, TAB_LIST, SCOREBOARD, ITEM_NAMES, NAME_TAGS, TOOL_TIPS, SIGNS, TITLE, BOOKS, CHEST_NAMES, MAPS
   };

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static boolean checkAntiStrikeChatType(AntiStrikeChatType antiStrikeChatType) {
      return antiStrikeChatType.enabled;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private AntiStrikeChatType(String text) {
      this.labelText = text;
   }


}
