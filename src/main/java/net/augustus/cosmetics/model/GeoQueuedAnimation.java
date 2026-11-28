package net.augustus.cosmetics.model;

import java.util.Objects;

public class GeoQueuedAnimation {
   public String animationName;
   public Boolean loop;

   public GeoQueuedAnimation(String str, Boolean bool) {
      this.animationName = str;
      this.loop = bool;
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.animationName, this.loop);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof GeoQueuedAnimation)) {
         return false;
      } else {
         GeoQueuedAnimation geoQueuedAnimation = (GeoQueuedAnimation)obj;
         return geoQueuedAnimation.loop == this.loop && geoQueuedAnimation.animationName.equals(this.animationName);
      }
   }
}
