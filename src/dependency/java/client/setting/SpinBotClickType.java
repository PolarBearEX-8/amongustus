package client.setting;

public enum SpinBotClickType implements NamedOption {
    CLICK("Click", true),
    CHEST("Chest", true),
    CRAFTER("CraftingTable", false),
    FURNACE("Furnace", false);

   public final String labelText;
   public boolean enabled;
   public static final SpinBotClickType[] ENUM_VALUES = new SpinBotClickType[]{CLICK, CHEST, CRAFTER, FURNACE};

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

   private SpinBotClickType(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
