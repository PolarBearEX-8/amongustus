package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum TargetEspModuleOption implements SelectableOption {
   KILL_AURA("KillAura"),
   AIM_BOT("AimBot"),
   AIM_ASSIST("AimAssist"),
   AUTO_ROD("AutoRod"),
   LEGIT("Legit");

private final String name;
   private boolean selected;
private TargetEspModuleOption(String str) {
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
