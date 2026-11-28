package net.augustus.cosmetics.model;

import java.util.Objects;

public class GeoEasingCacheKey {
   public final GeoEasingType easingType;
   public final Double customArgument;

   public GeoEasingCacheKey(GeoEasingType geoEasingType, Double d) {
      this.easingType = geoEasingType;
      this.customArgument = d;
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.easingType, this.customArgument);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         GeoEasingCacheKey geoEasingCacheKey = (GeoEasingCacheKey)obj;
         return this.easingType == geoEasingCacheKey.easingType && Objects.equals(this.customArgument, geoEasingCacheKey.customArgument);
      } else {
         return false;
      }
   }
}
