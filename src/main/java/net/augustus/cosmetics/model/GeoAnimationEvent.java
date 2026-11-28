package net.augustus.cosmetics.model;

import java.util.List;
import java.util.stream.Collectors;

public class GeoAnimationEvent {
   private final List extraData;
   public double animationTick;
   private final float limbSwingAmount;
   private final GeoAnimatable animatable;
   public GeoAnimationController controller;
   private final boolean isMoving;
   private final float partialTick;
   private final float limbSwing;

   public float getLimbSwing() {
      return this.limbSwing;
   }

   public float getLimbSwingAmount() {
      return this.limbSwingAmount;
   }

   public double getAnimationTick() {
      return this.animationTick;
   }

   public float getPartialTick() {
      return this.partialTick;
   }

   public List getExtraData() {
      return this.extraData;
   }

   public List getExtraDataOfType(Class clazz) {
      return (List)(this.extraData.stream().filter(obj -> clazz.isAssignableFrom(obj.getClass())).map(obj2 -> clazz.cast(obj2)).collect(Collectors.toList()));
   }

   public GeoAnimatable getAnimatable() {
      return this.animatable;
   }

   public GeoAnimationController getController() {
      return this.controller;
   }

   public boolean isMoving() {
      return this.isMoving;
   }

   public GeoAnimationEvent(GeoAnimatable geoAnimatable, float f, float f2, float f3, boolean flag, List list) {
      this.animatable = geoAnimatable;
      this.limbSwing = f;
      this.limbSwingAmount = f2;
      this.partialTick = f3;
      this.isMoving = flag;
      this.extraData = list;
   }

   public void setController(GeoAnimationController geoAnimationController) {
      this.controller = geoAnimationController;
   }



}
