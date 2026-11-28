package net.augustus.cosmetics.model;

public abstract class GeoKeyframeEvent {
   private final double animationTick;
   private final Object entity;
   private final GeoAnimationController controller;

   public double getAnimationTick() {
      return this.animationTick;
   }

   public Object getEntity() {
      return this.entity;
   }

   public GeoAnimationController getController() {
      return this.controller;
   }

   public GeoKeyframeEvent(Object obj, double d, GeoAnimationController geoAnimationController) {
      this.entity = obj;
      this.animationTick = d;
      this.controller = geoAnimationController;
   }




}
