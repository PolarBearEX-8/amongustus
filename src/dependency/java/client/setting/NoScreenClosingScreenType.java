package client.setting;

public enum NoScreenClosingScreenType implements NamedOption {
    CLICK_GUI("ClickGui", true),
    CHAT("Chat", true),
    INVENTORY("Inventory", false),
    CHEST("Chest", false),
    OTHERS("Others", false);

   public final String labelText;
   public boolean enabled;
   public static final NoScreenClosingScreenType[] ENUM_VALUES = new NoScreenClosingScreenType[]{CLICK_GUI, CHAT, INVENTORY, CHEST, OTHERS};

   public static boolean checkNoScreenClosingScreenType(NoScreenClosingScreenType noScreenClosingScreenType) {
      return noScreenClosingScreenType.enabled;
   }

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

   private NoScreenClosingScreenType(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
