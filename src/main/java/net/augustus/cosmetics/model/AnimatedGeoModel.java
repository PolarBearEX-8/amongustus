package net.augustus.cosmetics.model;

import net.augustus.cosmetics.CosmeticRenderState;
import net.augustus.cosmetics.CosmeticTickListener;
import net.augustus.cosmetics.MoreCosmeticsCore;
import net.augustus.cosmetics.molang.MolangParser;
import java.util.Collections;

public abstract class AnimatedGeoModel implements IGeoAnimatableModel {
   private final GeoAnimationProcessor animationProcessor;
   private GeoModel currentModel;
   public double lastGameTickTime;
   public double seekTime;
   private GeoAnimationFile animationFile;
   public boolean crashWhenOutOfBounds;

   public GeoAnimationFile getAnimationFile() {
      return this.animationFile;
   }

   public void registerBoneRecursively(GeoBone geoBone) {
      this.registerModelRenderer((IGeoBone)geoBone);

      for (GeoBone geoBone2 : (Iterable<GeoBone>)(geoBone.childBones)) {
         this.registerBoneRecursively(geoBone2);
      }
   }

   public void registerModelRenderer(IGeoBone geoBone) {
      this.animationProcessor.registerModelRenderer(geoBone);
   }

   @Override
   public GeoAnimationProcessor getAnimationProcessor() {
      return this.animationProcessor;
   }

   public GeoModel getCurrentModel() {
      return this.currentModel;
   }

   @Override
   public void setMolangQueries(GeoAnimatable geoAnimatable, double d2) {
   }

   @Override
   public GeoAnimation getAnimation(String str, GeoAnimatable geoAnimatable) {
      return this.animationFile.getAnimation(str);
   }

   @Override
   public void setLivingAnimations(Object obj, Integer num, GeoAnimationEvent geoAnimationEvent) {
      this.setLivingAnimations2((GeoAnimatable)obj, num, geoAnimationEvent);
   }

   public void setLivingAnimations2(GeoAnimatable geoAnimatable, Integer num, GeoAnimationEvent geoAnimationEvent) {
      GeoAnimationData orCreateAnimationData = geoAnimatable.getFactory().getOrCreateAnimationData(num);
      if (orCreateAnimationData.tickListener == null) {
         GeoAnimationTickListener listener = new GeoAnimationTickListener(orCreateAnimationData);
         orCreateAnimationData.tickListener = listener;
         MoreCosmeticsCore.getInstance().registerTickListener((CosmeticTickListener)listener);
      }

      this.seekTime = orCreateAnimationData.tick + (double)CosmeticRenderState.partialTicks;
      GeoAnimationEvent geoAnimationEvent2;
      if (geoAnimationEvent == null) {
         geoAnimationEvent2 = new GeoAnimationEvent(geoAnimatable, 0.0F, 0.0F, 0.0F, false, Collections.emptyList());
      } else {
         geoAnimationEvent2 = geoAnimationEvent;
      }

      geoAnimationEvent2.animationTick = this.seekTime;
      this.animationProcessor.preAnimationSetup(geoAnimationEvent2.getAnimatable(), this.seekTime);
      if (!this.animationProcessor.getModelRendererList().isEmpty()) {
         boolean crashWhenOutOfBounds2 = this.crashWhenOutOfBounds;
         MolangParser molangparser = GeckoLibRegistry.MOLANG_PARSER;
         double seekTime3 = this.seekTime;
         this.animationProcessor.tickAnimation(geoAnimatable, num, seekTime3, geoAnimationEvent2, molangparser, crashWhenOutOfBounds2);
      }
   }

   public AnimatedGeoModel(GeoModel geoModel, GeoAnimationFile geoAnimationFile) {
       super();
      this.crashWhenOutOfBounds = false;
      this.animationProcessor = new GeoAnimationProcessor(this);
      this.animationProcessor.clearModelRendererList();

      for (GeoBone geoBone : (Iterable<GeoBone>)(geoModel.topLevelBones)) {
         this.registerBoneRecursively(geoBone);
      }

      this.currentModel = geoModel;
      this.animationFile = geoAnimationFile;
   }



}
