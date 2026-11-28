package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum AutoWalkTargetOption implements SelectableOption {
   PLAYER("Player", true),
   MOB("Mob", false),
   ANIMAL("Animal", false),
   VILLAGER("Villager", false),
   ARMOR_STAND("ArmorStand", false),
   INVISIBLE("Invisible", false);

private final String name;
   private boolean selected;
private AutoWalkTargetOption(String str, boolean flag) {
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
