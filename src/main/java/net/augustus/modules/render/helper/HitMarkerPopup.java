package net.augustus.modules.render.helper;

import net.minecraft.util.Vec3;

public class HitMarkerPopup {
   private final String text;
   private final Vec3 position;
   private final long createdAtMs;

   public HitMarkerPopup(String str, Vec3 vec, long l) {
      this.text = str;
      this.position = vec;
      this.createdAtMs = l;
   }

   public static long getCreatedAtMs(HitMarkerPopup hitMarkerPopup) {
      return hitMarkerPopup.createdAtMs;
   }

   public static Vec3 getPosition(HitMarkerPopup hitMarkerPopup) {
      return hitMarkerPopup.position;
   }

   public static String getText(HitMarkerPopup hitMarkerPopup) {
      return hitMarkerPopup.text;
   }
}
