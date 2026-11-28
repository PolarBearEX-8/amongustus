package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum KillEffectTypeOption implements SelectableOption {
   FLASH("Flash"),
   ENDER_DRAGON_DEATH_SOUND("DragonDeathSound"),
   FIRE("Fire");

private final String name;
   private boolean selected;
private KillEffectTypeOption(String str) {
      this.name = str;
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
