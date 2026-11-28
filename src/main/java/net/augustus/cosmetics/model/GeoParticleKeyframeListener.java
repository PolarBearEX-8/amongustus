package net.augustus.cosmetics.model;

@FunctionalInterface
public interface GeoParticleKeyframeListener {
   void summonParticle(ParticleKeyframeEvent particleKeyframeEvent);
}
