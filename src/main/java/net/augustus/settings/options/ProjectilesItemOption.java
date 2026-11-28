package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum ProjectilesItemOption implements SelectableOption {
   ARROW("Arrow"),
   ENDER_PEARL("EnderPearl"),
   EGG("Egg"),
   SNOW_BALL("SnowBall"),
   XP_BOTTLE("XPBottle"),
   POTION("Potion"),
   FIRE_BALL("FireBall");

private final String name;
   private boolean selected = true;
private ProjectilesItemOption(String str) {
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
