package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public class ItemEspItemOption implements SelectableOption {
   public String name;
   public boolean selected;

   private ItemEspItemOption(String str, boolean flag) {
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

   public ItemEspItemOption(String str, boolean flag, ItemEspOptionSyntheticMarker marker) {
      this(str, flag);
   }
}
