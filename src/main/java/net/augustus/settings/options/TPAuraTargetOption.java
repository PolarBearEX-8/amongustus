package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum TPAuraTargetOption implements SelectableOption {
   PLAYER("Player", true),
   MOB("Mob"),
   ANIMAL("Animal"),
   VILLAGER("Villager"),
   INVISIBLE("Invisible"),
   DEAD("Dead");

private final String name;
   private boolean selected;
private TPAuraTargetOption(String str) {
      this.name = str;
   }

   private TPAuraTargetOption(String str, boolean flag) {
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
