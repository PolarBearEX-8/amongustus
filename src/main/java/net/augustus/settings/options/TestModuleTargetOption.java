package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum TestModuleTargetOption implements SelectableOption {
   PLAYER("Player"),
   MOB("Mob"),
   ANIMAL("Animal"),
   VILLAGER("Villager"),
   INVISIBLE("Invisible"),
   DEAD("Dead");

private final String name;
   private boolean selected = true;
private TestModuleTargetOption(String str) {
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

   public static boolean isOptionSelected(TestModuleTargetOption testModuleTargetOption) {
      return testModuleTargetOption.selected;
   }
}
