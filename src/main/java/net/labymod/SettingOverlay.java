package net.labymod;

public class SettingOverlay {
   @DefaultValue
   public float x;
   @DefaultValue
   public float y;
   @DefaultValue
   public float z;
   @DefaultValue
   public float yaw;
   @DefaultValue
   public float pitch;
   @DefaultValue
   public float roll;
   @DefaultValue(1.0F)
   public float scale = 1.0F;
   @DefaultValue(1.0F)
   public float mulitply = 1.0F;
   @DefaultValue
   public int color;
   @DefaultValue
   public String url;
   @DefaultValue
   public int id;
   @DefaultValue
   public boolean illum;
   @DefaultValue
   public boolean resize;
   @DefaultValue(1.0F)
   public boolean visible = true;
   @DefaultValue(1.0F)
   public boolean animation = true;

   @Override
   public int hashCode() {
      byte b = 31;
      int n = 1;
      n = 31 * n + (this.animation ? 1231 : 1237);
      n = 31 * n + this.color;
      n = 31 * n + this.id;
      n = 31 * n + (this.illum ? 1231 : 1237);
      n = 31 * n + Float.floatToIntBits(this.mulitply);
      n = 31 * n + Float.floatToIntBits(this.pitch);
      n = 31 * n + (this.resize ? 1231 : 1237);
      n = 31 * n + Float.floatToIntBits(this.roll);
      n = 31 * n + Float.floatToIntBits(this.scale);
      n = 31 * n + (this.url == null ? 0 : this.url.hashCode());
      n = 31 * n + (this.visible ? 1231 : 1237);
      n = 31 * n + Float.floatToIntBits(this.x);
      n = 31 * n + Float.floatToIntBits(this.y);
      n = 31 * n + Float.floatToIntBits(this.yaw);
      return 31 * n + Float.floatToIntBits(this.z);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         SettingOverlay settingOverlay = (SettingOverlay)obj;
         if (this.animation != settingOverlay.animation) {
            return false;
         } else if (this.color != settingOverlay.color) {
            return false;
         } else if (this.id != settingOverlay.id) {
            return false;
         } else if (this.illum != settingOverlay.illum) {
            return false;
         } else if (Float.floatToIntBits(this.mulitply) != Float.floatToIntBits(settingOverlay.mulitply)) {
            return false;
         } else if (Float.floatToIntBits(this.pitch) != Float.floatToIntBits(settingOverlay.pitch)) {
            return false;
         } else if (this.resize != settingOverlay.resize) {
            return false;
         } else if (Float.floatToIntBits(this.roll) != Float.floatToIntBits(settingOverlay.roll)) {
            return false;
         } else if (Float.floatToIntBits(this.scale) != Float.floatToIntBits(settingOverlay.scale)) {
            return false;
         } else {
            if (this.url == null) {
               if (settingOverlay.url != null) {
                  return false;
               }
            } else if (!this.url.equals(settingOverlay.url)) {
               return false;
            }

            if (this.visible != settingOverlay.visible) {
               return false;
            } else if (Float.floatToIntBits(this.x) != Float.floatToIntBits(settingOverlay.x)) {
               return false;
            } else if (Float.floatToIntBits(this.y) != Float.floatToIntBits(settingOverlay.y)) {
               return false;
            } else {
               return Float.floatToIntBits(this.yaw) != Float.floatToIntBits(settingOverlay.yaw) ? false : Float.floatToIntBits(this.z) == Float.floatToIntBits(settingOverlay.z);
            }
         }
      }
   }
}
