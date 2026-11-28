package net.augustus.settings.options;


import net.augustus.settings.ItemFilterSetting;
import net.augustus.settings.SelectableOption;
public class ItemFilterOption implements SelectableOption {
   private final String name;
   public final ItemFilterSetting parentFilter;
   private boolean selected;

   public static boolean isOptionSelected(ItemFilterOption itemFilterOption) {
      return itemFilterOption.selected;
   }

   public ItemFilterOption(ItemFilterSetting itemFilterSetting, String str, boolean flag) {
      this.parentFilter = itemFilterSetting;
      this.selected = true;
      this.name = str;
      this.selected = flag;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public String getName() {
      return this.name;
   }
}
