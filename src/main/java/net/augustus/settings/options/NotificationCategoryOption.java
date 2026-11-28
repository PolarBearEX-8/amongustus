package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum NotificationCategoryOption implements SelectableOption {
   MODULE_TOGGLES("ModuleToggles", false),
   CONFIG_SYSTEM("ConfigSystem", true),
   BEVERAGE_NOTIFICATION("BeverageNotification", true),
   ENDER_PEARL_NOTIFICATION("EnderPearlNotification", true),
   VANISH_NOTIFICATION("VanishNotification", true);

private final String name;
   private boolean selected;
private NotificationCategoryOption(String str, boolean flag) {
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
