package net.augustus.cosmetics.model;

public class GeoBoneAnimationQueue {
   public GeoAnimationPointQueue positionXQueue;
   public GeoAnimationPointQueue scaleYQueue;
   public GeoAnimationPointQueue scaleXQueue;
   public GeoAnimationPointQueue scaleZQueue;
   public GeoAnimationPointQueue rotationYQueue;
   public final IGeoBone bone;
   public GeoAnimationPointQueue positionZQueue;
   public GeoAnimationPointQueue rotationZQueue;
   public GeoAnimationPointQueue positionYQueue;
   public GeoAnimationPointQueue rotationXQueue = new GeoAnimationPointQueue();

   public GeoBoneAnimationQueue(IGeoBone geoBone) {
      this.rotationYQueue = new GeoAnimationPointQueue();
      this.rotationZQueue = new GeoAnimationPointQueue();
      this.positionXQueue = new GeoAnimationPointQueue();
      this.positionYQueue = new GeoAnimationPointQueue();
      this.positionZQueue = new GeoAnimationPointQueue();
      this.scaleXQueue = new GeoAnimationPointQueue();
      this.scaleYQueue = new GeoAnimationPointQueue();
      this.scaleZQueue = new GeoAnimationPointQueue();
      this.bone = geoBone;
   }
}
