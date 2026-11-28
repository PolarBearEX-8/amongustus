package net.augustus.cosmetics.model;

import java.util.HashMap;

public class GeoAnimationData {
   public double tick;
   private HashMap boneSnapshotCollection;
   public Object tickListener;
   public boolean isFirstTick;
   public boolean shouldPlayWhilePaused;
   public Double startTick;
   private double resetTickLength;
   private HashMap animationControllers;

   public HashMap getBoneSnapshotCollection() {
      return this.boneSnapshotCollection;
   }

   public void clearSnapshotCollection() {
      this.boneSnapshotCollection = new HashMap();
   }

   public GeoAnimationController addAnimationController(GeoAnimationController geoAnimationController) {
      return (GeoAnimationController)(this.animationControllers.put(geoAnimationController.getName(), geoAnimationController));
   }

   public HashMap getAnimationControllers() {
      return this.animationControllers;
   }

   public void setResetSpeedInTicks(double resetSpeedInTicks) {
      this.resetTickLength = resetSpeedInTicks < 0.0 ? 0.0 : resetSpeedInTicks;
   }

   public GeoAnimationData() {
      this.boneSnapshotCollection = new HashMap();
      this.animationControllers = new HashMap();
      this.isFirstTick = true;
      this.resetTickLength = 1.0;
      this.shouldPlayWhilePaused = false;
   }

   public double getResetTickLength() {
      return this.resetTickLength;
   }

   public void setBoneSnapshotCollection(HashMap map) {
      this.boneSnapshotCollection = map;
   }



}
