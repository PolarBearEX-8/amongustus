package augustus.modes.render;

import client.setting.NamedOption;

public enum EspRenderMode implements NamedOption {
    BOX("Box", false),
    HIT_BOX("HitBox", false),
    COLLISION_BOX("CollisionBox", false),
    THREE_E_CORNER("3DCorner", true),
    REAL_2D("Real2D", false),
    CORNER("Corner", false),
    VANILLA("Vanilla", false);

   public final String labelText;
   public boolean enabled;
   public static final EspRenderMode[] ENUM_VALUES = new EspRenderMode[]{BOX, HIT_BOX, COLLISION_BOX, THREE_E_CORNER, REAL_2D, CORNER, VANILLA};

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static boolean checkEspRenderMode(EspRenderMode espRenderMode) {
      return espRenderMode.enabled;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private EspRenderMode(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
