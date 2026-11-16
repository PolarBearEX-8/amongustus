package client.setting;

public enum PulseBlinkStandingStillType implements NamedOption {
    STANDING_STILL("StandingStill", true),
    SCAFFOLD_TOGGLED("ScaffoldToggled", true),
    PLACE_BLOCK("PlaceBlocks", true),
    BREAK_BLOCKS("BreakBlocks", true),
    USE_ITEM("UseItem", true),
    OPEN_GUI("OpenedGui", true),
    INVENTORY_ACTIONS("InventoryActions", true),
    VELOCITY("Velocity", false),
    EXPLOSION("Explosion", false),
    TELEPORT("Teleport", true),
    SPRINT_RESET("SprintReset", false),
    SPLASH_POTION("SplashPotion", false);

   public final String labelText;
   public boolean enabled = true;
   public static final PulseBlinkStandingStillType[] ENUM_VALUES = new PulseBlinkStandingStillType[]{
      STANDING_STILL,
      SCAFFOLD_TOGGLED,
      PLACE_BLOCK,
      BREAK_BLOCKS,
      USE_ITEM,
      OPEN_GUI,
      INVENTORY_ACTIONS,
      VELOCITY,
      EXPLOSION,
      TELEPORT,
      SPRINT_RESET,
      SPLASH_POTION
   };

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static boolean checkPulseBlinkStandingStillType(PulseBlinkStandingStillType pulseBlinkStandingStillType) {
      return pulseBlinkStandingStillType.enabled;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private PulseBlinkStandingStillType(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }


}
