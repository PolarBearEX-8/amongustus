package net.augustus.cosmetics.model;

public class CustomInstructionKeyframeEvent extends GeoKeyframeEvent {
   public final String instructions;

   public CustomInstructionKeyframeEvent(Object obj, double d, String str, GeoAnimationController geoAnimationController) {
       super(obj, d, geoAnimationController);
      this.instructions = str;
   }
}
