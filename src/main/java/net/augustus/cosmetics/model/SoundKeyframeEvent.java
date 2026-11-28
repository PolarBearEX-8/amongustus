package net.augustus.cosmetics.model;

public class SoundKeyframeEvent extends GeoKeyframeEvent {
   public final String sound;

   public SoundKeyframeEvent(Object obj, double d, String str, GeoAnimationController geoAnimationController) {
       super(obj, d, geoAnimationController);
      this.sound = str;
   }
}
