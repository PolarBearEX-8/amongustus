package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum VelocityLagTimingOption implements SelectableOption {
   IN_AIR("InAir"),
   ON_GROUND("OnGround");

private String name;
   private boolean selected = true;
private VelocityLagTimingOption(String str) {
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

   public static boolean isOptionSelected(VelocityLagTimingOption velocityLagTimingOption) {
      return velocityLagTimingOption.selected;
   }
}
