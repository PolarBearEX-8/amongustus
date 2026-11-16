package client.setting;

public enum BeverageNotifyModuleTogglesType implements NamedOption {
    MODULE_TOGGLES("ModuleToggles", false),
    LEVEL_SYSTEM("LevelSystem", true),
    CONFIG_SYSTEM("ConfigSystem", true),
    BEVERAGE_NOTIFICATION("BeverageNotification", true),
    ENDER_PEARL_NOTIFICATION("EnderPearlNotification", true),
    VANISH_NOTIFICATION("VanishNotification", true);

   public final String labelText;
   public boolean enabled;
   public static final BeverageNotifyModuleTogglesType[] ENUM_VALUES = new BeverageNotifyModuleTogglesType[]{
      MODULE_TOGGLES, LEVEL_SYSTEM, CONFIG_SYSTEM, BEVERAGE_NOTIFICATION, ENDER_PEARL_NOTIFICATION, VANISH_NOTIFICATION
   };

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

   private BeverageNotifyModuleTogglesType(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
