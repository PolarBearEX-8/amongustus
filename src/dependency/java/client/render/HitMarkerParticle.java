package client.render;

import net.minecraft.util.Vec3;

public class HitMarkerParticle {
   public final String labelText;
   public final Vec3 vec3;
   public final long timestamp;

   public HitMarkerParticle(String text, Vec3 vec, long value) {
      this.labelText = text;
      this.vec3 = vec;
      this.timestamp = value;
   }

   public static long getTimestamp(HitMarkerParticle hitMarkerParticle) {
      return hitMarkerParticle.timestamp;
   }

   public static Vec3 getVec3(HitMarkerParticle hitMarkerParticle) {
      return hitMarkerParticle.vec3;
   }

   public static String getText(HitMarkerParticle hitMarkerParticle) {
      return hitMarkerParticle.labelText;
   }
}
