package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public class WeaponFilterOption implements SelectableOption {
   private final String name;
   private boolean selected;

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   public WeaponFilterOption(String str) {
      this.name = str;
      this.selected = true;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   @Override
   public String getName() {
      return this.name;
   }
}
