package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum RotationRecorderTargetOption implements SelectableOption {
   PLAYER("Player"),
   MOB("Mob"),
   ANIMAL("Animal"),
   VILLAGER("Villager"),
   ARMOR_STAND("ArmorStand"),
   INVISIBLE("Invisible"),
   DEAD("Dead");

private final String name;
   private boolean selected;
private RotationRecorderTargetOption(String str) {
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
