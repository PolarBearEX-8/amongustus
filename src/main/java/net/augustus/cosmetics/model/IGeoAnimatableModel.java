package net.augustus.cosmetics.model;

public interface IGeoAnimatableModel {
   String d = "Could not find bone: ";

   default IGeoBone getBone(String str) {
      IGeoBone bone = this.getAnimationProcessor().getBone(str);
      if (bone == null) {
         throw new RuntimeException(d + str);
      } else {
         return bone;
      }
   }

   default void setLivingAnimations(Object obj, Integer num) {
      this.setLivingAnimations(obj, num, (GeoAnimationEvent)null);
   }

   void setLivingAnimations(Object obj, Integer num, GeoAnimationEvent geoAnimationEvent);

   GeoAnimation getAnimation(String str, GeoAnimatable geoAnimatable);

   void setMolangQueries(GeoAnimatable geoAnimatable, double d2);

   GeoAnimationProcessor getAnimationProcessor();

   default double getCurrentTick() {
      return (double)(System.nanoTime() / 1000000L) / 50.0;
   }

}
