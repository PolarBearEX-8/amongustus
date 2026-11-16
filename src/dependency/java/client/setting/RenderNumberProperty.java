package client.setting;

public enum RenderNumberProperty {
    HT_LIGHT("HitLight"),
    CANCEL("cancel"),
    GARBAGE_HIT("GarbageHit"),
    DM_LIGHT("DMGLight"),
    HT_MEDIUM("HitMedium"),
    CANCEL_FIRST("CancelFist"),
    DMG_MEDIUM("HitMedium"),
    BLOCKING("BLOCKING");

   public final String labelText;
   public static final RenderNumberProperty[] ENUM_VALUES = new RenderNumberProperty[]{
      HT_LIGHT, HT_MEDIUM, RenderNumberProperty.DMG_MEDIUM, DM_LIGHT, GARBAGE_HIT, RenderNumberProperty.BLOCKING, CANCEL, RenderNumberProperty.CANCEL_FIRST
   };

   public String getLabelText() {
      return this.labelText;
   }

   private RenderNumberProperty(String text) {
      this.labelText = text;
   }


}
