package net.augustus.cosmetics.model;

import java.util.HashMap;

public class GeoAnimationFactory {
   private HashMap animationDataMap = new HashMap();
   private final GeoAnimatable animatable;

   public GeoAnimationFactory(GeoAnimatable geoAnimatable) {
      this.animatable = geoAnimatable;
   }

   public GeoAnimationData getOrCreateAnimationData(Integer num) {
      if (!this.animationDataMap.containsKey(num)) {
         GeoAnimationData geoAnimationData = new GeoAnimationData();
         this.animatable.registerControllers(geoAnimationData);
         this.animationDataMap.put(num, geoAnimationData);
      }

      return (GeoAnimationData)this.animationDataMap.get(num);
   }
}
