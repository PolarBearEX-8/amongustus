package net.augustus.cosmetics.model;

public interface GeoAnimatable {
   void registerControllers(GeoAnimationData geoAnimationData);

   GeoAnimationFactory getFactory();
}
