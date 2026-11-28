package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public class AdvancedClickHurtTimeOption implements SelectableOption {
   private final String name;
   public boolean selected = true;

   public AdvancedClickHurtTimeOption(int n) {
      this.name = String.valueOf(n);
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
}
