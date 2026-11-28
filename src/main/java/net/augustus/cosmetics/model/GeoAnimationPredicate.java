package net.augustus.cosmetics.model;

@FunctionalInterface
public interface GeoAnimationPredicate {
   GeoPlayState test(GeoAnimationEvent geoAnimationEvent);
}
