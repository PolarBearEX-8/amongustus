package net.augustus.cosmetics.model;

public class ParticleKeyframeEvent extends GeoKeyframeEvent {
   public final String script;
   public final String effect;
   public final String locator;

   public ParticleKeyframeEvent(Object obj, double d, String str, String str2, String str3, GeoAnimationController geoAnimationController) {
       super(obj, d, geoAnimationController);
      this.effect = str;
      this.locator = str2;
      this.script = str3;
   }
}
