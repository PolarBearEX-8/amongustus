package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public enum EspRenderModeOption implements SelectableOption {
   BOX("Box", false),
   HIT_BOX("HitBox", false),
   COLLISION_BOX("CollisionBox", false),
   THREE_E_CORNER("3DCorner", true),
   REAL_2D("Real2D", false),
   CORNER("Corner", false),
   PICTURE("Picture", false),
   VANILLA("Vanilla", false);

private final String name;
   private boolean selected;
private EspRenderModeOption(String str, boolean flag) {
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

   public static boolean isOptionSelected(EspRenderModeOption espRenderModeOption) {
      return espRenderModeOption.selected;
   }
}
