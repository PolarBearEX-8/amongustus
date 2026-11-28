package net.augustus.cosmetics.model;

import java.util.EnumMap;

public class CosmeticAnimatable implements GeoAnimatable {
   public GeoAnimationFactory factory;
   private EnumMap triggerAnimations = new EnumMap<>(CosmeticAnimationTrigger.class);
   private String currentAnimation;
   private String defaultAnimation;
   private String controllerName;
   private GeoAnimationController controller;
   private AnimatedGeoModel animatedModel;

   @Override
   public GeoAnimationFactory getFactory() {
      return this.factory;
   }

   public boolean hasAnimation(String str) {
      return this.animatedModel.getAnimationFile().getAnimation(str) != null;
   }

   public AnimatedGeoModel getAnimatedModel() {
      return this.animatedModel;
   }

   public CosmeticAnimatable(AnimatedGeoModel animatedGeoModel) {
      this.factory = new GeoAnimationFactory(this);
      this.controllerName = "mainController";
      this.animatedModel = animatedGeoModel;
   }

   public void setTriggerAnimation(CosmeticAnimationTrigger cosmeticAnimationTrigger) {
      String str = (String)this.triggerAnimations.get(cosmeticAnimationTrigger);
      if (!this.currentAnimation.equals(str)) {
         str = str == null ? this.defaultAnimation : str;
         if (this.hasAnimation(str)) {
            this.currentAnimation = str;
            GeoAnimationController controller2 = this.controller;
            GeoAnimationBuilder geoAnimationBuilder = new GeoAnimationBuilder();
            Boolean bool = true;
            controller2.setAnimation(geoAnimationBuilder.addAnimation(str, bool));
         }
      }
   }

   @Override
   public void registerControllers(GeoAnimationData geoAnimationData) {
      this.controller = new GeoAnimationController(this, this.controllerName, 5.0F, geoAnimationEvent -> GeoPlayState.CONTINUE);
      String firstAnimationName = this.animatedModel.getAnimationFile().getFirstAnimationName();
      if (firstAnimationName != null) {
         this.defaultAnimation = firstAnimationName;
         this.currentAnimation = firstAnimationName;
         GeoAnimationController controller2 = this.controller;
         GeoAnimationBuilder geoAnimationBuilder = new GeoAnimationBuilder();
         Boolean bool = true;
         controller2.setAnimation(geoAnimationBuilder.addAnimation(firstAnimationName, bool));

         for (CosmeticAnimationTrigger cosmeticAnimationTrigger : CosmeticAnimationTrigger.VALUES) {
            this.triggerAnimations.put(cosmeticAnimationTrigger, this.hasAnimation(cosmeticAnimationTrigger.getAnimationName()) ? cosmeticAnimationTrigger.getAnimationName() : this.defaultAnimation);
         }
      }

      geoAnimationData.addAnimationController(this.controller);
   }

   public boolean hasDefaultAnimation() {
      return this.defaultAnimation != null;
   }




}
