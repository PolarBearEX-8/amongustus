package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum AutoPotPotionOption implements SelectableOption {
   HEALTH("Health", true),
   SPEED("Speed", true),
   REGENERATION("Regeneration", true),
   ALL_POSITIVE("AllPositive", false);

private final String name;
   private boolean selected;
private AutoPotPotionOption(String str, boolean flag) {
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
}
