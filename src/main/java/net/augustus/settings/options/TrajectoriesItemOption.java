package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum TrajectoriesItemOption implements SelectableOption {
   BOW("Bow"),
   ENDER_PEARL("EnderPearl"),
   EGG("Egg"),
   SNOW_BALL("SnowBall"),
   XP_BOTTLE("XPBottle"),
   POTION("Potion"),
   ROD("Rod"),
   FIREBALL("Fireball");

private final String name;
   private boolean selected = true;
private TrajectoriesItemOption(String str) {
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
