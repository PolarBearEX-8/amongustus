package client.setting;

public enum ShadowItemsOnGroundType implements NamedOption {
    ITEMS_ON_GROUND("ItemsOnGround", false),
    ITEMS_IN_HAND("ItemsInHand", false),
    ITEMS_IN_HOTBAR("ItemsInHotbar", false);

   public final String labelText;
   public boolean enabled;
   public static final ShadowItemsOnGroundType[] ENUM_VALUES = new ShadowItemsOnGroundType[]{ITEMS_ON_GROUND, ITEMS_IN_HAND, ITEMS_IN_HOTBAR};

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

   private ShadowItemsOnGroundType(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
