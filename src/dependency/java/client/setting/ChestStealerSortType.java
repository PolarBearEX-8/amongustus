package client.setting;

public enum ChestStealerSortType implements NamedOption {
    SORT("Sort"),
    PUT_ON_ARMOR("Armor"),
    CLEAN("Clean"),
    CRAFT("Craft"),
    PUT_ON_ARMOR_HOT_BAR("HotbarArmor");

   public final String labelText;
   public boolean enabled = true;
   public static final ChestStealerSortType[] ENUM_VALUES = new ChestStealerSortType[]{SORT, PUT_ON_ARMOR, CLEAN, CRAFT, PUT_ON_ARMOR_HOT_BAR};

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static boolean checkChestStealerSortType(ChestStealerSortType chestStealerSortType) {
      return chestStealerSortType.enabled;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private ChestStealerSortType(String text) {
      this.labelText = text;
   }


}
