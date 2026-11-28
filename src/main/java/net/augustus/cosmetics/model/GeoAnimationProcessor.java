package net.augustus.cosmetics.model;

import net.augustus.cosmetics.molang.MolangParser;
import net.augustus.utils.UnaryFunction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;

public class GeoAnimationProcessor {
   private Set animatedEntities;
   private final IGeoAnimatableModel model;
   private double lastTickValue;
   public boolean reloadAnimations;
   private List modelRendererList;

   public void registerModelRenderer(IGeoBone geoBone) {
      geoBone.saveInitialSnapshot();
      this.modelRendererList.add(geoBone);
   }

   public List getModelRendererList() {
      return this.modelRendererList;
   }

   private HashMap createNewDirtyTracker() {
      HashMap map = new HashMap();

      for (IGeoBone geoBone : (Iterable<IGeoBone>)(this.modelRendererList)) {
         map.put(geoBone.getName(), new GeoBoneDirtyTracker(false, false, false, geoBone));
      }

      return map;
   }

   public IGeoBone getBone(String str) {
      return (IGeoBone)(this.modelRendererList.stream().filter(obj -> ((IGeoBone)obj).getName().equals(str)).findFirst().orElse(null));
   }

   public GeoAnimationProcessor(IGeoAnimatableModel geoAnimatableModel) {
      this.reloadAnimations = false;
      this.modelRendererList = new ArrayList();
      this.lastTickValue = -1.0;
      this.animatedEntities = new HashSet();
      this.model = geoAnimatableModel;
   }

   public void tickAnimation(GeoAnimatable geoAnimatable, Integer num, double d, GeoAnimationEvent geoAnimationEvent, MolangParser molangParser, boolean flag) {
      if (d != this.lastTickValue) {
         this.animatedEntities.clear();
      } else if (this.animatedEntities.contains(num)) {
         return;
      }

      this.lastTickValue = d;
      this.animatedEntities.add(num);
      GeoAnimationData orCreateAnimationData = geoAnimatable.getFactory().getOrCreateAnimationData(num);
      HashMap map = this.createNewDirtyTracker();
      this.updateBoneSnapshots(orCreateAnimationData.getBoneSnapshotCollection());
      HashMap boneSnapshotCollection2 = orCreateAnimationData.getBoneSnapshotCollection();

      for (GeoAnimationController geoAnimationController : (Iterable<GeoAnimationController>)(orCreateAnimationData.getAnimationControllers().values())) {
         if (this.reloadAnimations) {
            geoAnimationController.markNeedsReload();
            geoAnimationController.getBoneAnimationQueues().clear();
         }

         geoAnimationController.isJustStarting = orCreateAnimationData.isFirstTick;
         geoAnimationEvent.setController(geoAnimationController);
         geoAnimationController.process(d, geoAnimationEvent, this.modelRendererList, boneSnapshotCollection2, molangParser, flag);

         for (GeoBoneAnimationQueue geoBoneAnimationQueue : (Iterable<GeoBoneAnimationQueue>)(geoAnimationController.getBoneAnimationQueues().values())) {
            IGeoBone bone = geoBoneAnimationQueue.bone;
            GeoBoneSnapshot right = (GeoBoneSnapshot)((Pair)boneSnapshotCollection2.get(bone.getName())).getRight();
            GeoBoneSnapshot initialSnapshot = bone.getInitialSnapshot();
            GeoAnimationPoint geoAnimationPoint = (GeoAnimationPoint)geoBoneAnimationQueue.rotationXQueue.poll();
            GeoAnimationPoint geoAnimationPoint2 = (GeoAnimationPoint)geoBoneAnimationQueue.rotationYQueue.poll();
            GeoAnimationPoint geoAnimationPoint3 = (GeoAnimationPoint)geoBoneAnimationQueue.rotationZQueue.poll();
            GeoAnimationPoint geoAnimationPoint4 = (GeoAnimationPoint)geoBoneAnimationQueue.positionXQueue.poll();
            GeoAnimationPoint geoAnimationPoint5 = (GeoAnimationPoint)geoBoneAnimationQueue.positionYQueue.poll();
            GeoAnimationPoint geoAnimationPoint6 = (GeoAnimationPoint)geoBoneAnimationQueue.positionZQueue.poll();
            GeoAnimationPoint geoAnimationPoint7 = (GeoAnimationPoint)geoBoneAnimationQueue.scaleXQueue.poll();
            GeoAnimationPoint geoAnimationPoint8 = (GeoAnimationPoint)geoBoneAnimationQueue.scaleYQueue.poll();
            GeoAnimationPoint geoAnimationPoint9 = (GeoAnimationPoint)geoBoneAnimationQueue.scaleZQueue.poll();
            GeoBoneDirtyTracker geoBoneDirtyTracker = (GeoBoneDirtyTracker)map.get(bone.getName());
            if (geoBoneDirtyTracker != null) {
               if (geoAnimationPoint != null && geoAnimationPoint2 != null && geoAnimationPoint3 != null) {
                  UnaryFunction customEasingMethod = geoAnimationController.customEasingMethod;
                  bone.setRotationX(GeoEasingInterpolator.lerpValues(geoAnimationPoint, geoAnimationController.easingType, customEasingMethod) + initialSnapshot.rotationValueX);
                  UnaryFunction customEasingMethod2 = geoAnimationController.customEasingMethod;
                  bone.setRotationY(GeoEasingInterpolator.lerpValues(geoAnimationPoint2, geoAnimationController.easingType, customEasingMethod2) + initialSnapshot.rotationValueY);
                  UnaryFunction customEasingMethod3 = geoAnimationController.customEasingMethod;
                  bone.setRotationZ(GeoEasingInterpolator.lerpValues(geoAnimationPoint3, geoAnimationController.easingType, customEasingMethod3) + initialSnapshot.rotationValueZ);
                  right.rotationValueX = bone.getRotationX();
                  right.rotationValueY = bone.getRotationY();
                  right.rotationValueZ = bone.getRotationZ();
                  right.isCurrentlyRunningRotationAnimation = true;
                  geoBoneDirtyTracker.hasRotationChanged = true;
               }

               if (geoAnimationPoint4 != null && geoAnimationPoint5 != null && geoAnimationPoint6 != null) {
                  UnaryFunction customEasingMethod4 = geoAnimationController.customEasingMethod;
                  bone.setPositionX(GeoEasingInterpolator.lerpValues(geoAnimationPoint4, geoAnimationController.easingType, customEasingMethod4));
                  UnaryFunction customEasingMethod5 = geoAnimationController.customEasingMethod;
                  bone.setPositionY(GeoEasingInterpolator.lerpValues(geoAnimationPoint5, geoAnimationController.easingType, customEasingMethod5));
                  UnaryFunction customEasingMethod6 = geoAnimationController.customEasingMethod;
                  bone.setPositionZ(GeoEasingInterpolator.lerpValues(geoAnimationPoint6, geoAnimationController.easingType, customEasingMethod6));
                  right.positionOffsetX = bone.getPositionX();
                  right.positionOffsetY = bone.getPositionY();
                  right.positionOffsetZ = bone.getPositionZ();
                  right.isCurrentlyRunningPositionAnimation = true;
                  geoBoneDirtyTracker.hasPositionChanged = true;
               }

               if (geoAnimationPoint7 != null && geoAnimationPoint8 != null && geoAnimationPoint9 != null) {
                  UnaryFunction customEasingMethod7 = geoAnimationController.customEasingMethod;
                  bone.setScaleX(GeoEasingInterpolator.lerpValues(geoAnimationPoint7, geoAnimationController.easingType, customEasingMethod7));
                  UnaryFunction customEasingMethod8 = geoAnimationController.customEasingMethod;
                  bone.setScaleY(GeoEasingInterpolator.lerpValues(geoAnimationPoint8, geoAnimationController.easingType, customEasingMethod8));
                  UnaryFunction customEasingMethod9 = geoAnimationController.customEasingMethod;
                  bone.setScaleZ(GeoEasingInterpolator.lerpValues(geoAnimationPoint9, geoAnimationController.easingType, customEasingMethod9));
                  right.scaleValueX = bone.getScaleX();
                  right.scaleValueY = bone.getScaleY();
                  right.scaleValueZ = bone.getScaleZ();
                  right.isCurrentlyRunningScaleAnimation = true;
                  geoBoneDirtyTracker.hasScaleChanged = true;
               }
            }
         }
      }

      this.reloadAnimations = false;
      double resetTickLength = orCreateAnimationData.getResetTickLength();

      for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
         IGeoBone model2 = ((GeoBoneDirtyTracker)entry.getValue()).model;
         GeoBoneSnapshot initialSnapshot2 = model2.getInitialSnapshot();
         GeoBoneSnapshot right2 = (GeoBoneSnapshot)((Pair)boneSnapshotCollection2.get(entry.getKey())).getRight();
         if (right2 == null) {
            if (flag) {
               throw new RuntimeException(
                  "Could not find save snapshot for bone: "
                     + ((GeoBoneDirtyTracker)entry.getValue()).model.getName()
                     + ". Please don't add bones that are used in an animation at runtime."
               );
            }
         } else {
            if (!((GeoBoneDirtyTracker)entry.getValue()).hasRotationChanged) {
               if (right2.isCurrentlyRunningRotationAnimation) {
                  right2.mostRecentResetRotationTick = (float)d;
                  right2.isCurrentlyRunningRotationAnimation = false;
               }

               double d2 = Math.min((d - (double)right2.mostRecentResetRotationTick) / resetTickLength, 1.0);
               model2.setRotationX(GeoEasingInterpolator.lerpFloatValues(d2, (double)right2.rotationValueX, (double)initialSnapshot2.rotationValueX));
               model2.setRotationY(GeoEasingInterpolator.lerpFloatValues(d2, (double)right2.rotationValueY, (double)initialSnapshot2.rotationValueY));
               model2.setRotationZ(GeoEasingInterpolator.lerpFloatValues(d2, (double)right2.rotationValueZ, (double)initialSnapshot2.rotationValueZ));
               if (d2 >= 1.0) {
                  right2.rotationValueX = model2.getRotationX();
                  right2.rotationValueY = model2.getRotationY();
                  right2.rotationValueZ = model2.getRotationZ();
               }
            }

            if (!((GeoBoneDirtyTracker)entry.getValue()).hasPositionChanged) {
               if (right2.isCurrentlyRunningPositionAnimation) {
                  right2.mostRecentResetPositionTick = (float)d;
                  right2.isCurrentlyRunningPositionAnimation = false;
               }

               double d3 = Math.min((d - (double)right2.mostRecentResetPositionTick) / resetTickLength, 1.0);
               model2.setPositionX(GeoEasingInterpolator.lerpFloatValues(d3, (double)right2.positionOffsetX, (double)initialSnapshot2.positionOffsetX));
               model2.setPositionY(GeoEasingInterpolator.lerpFloatValues(d3, (double)right2.positionOffsetY, (double)initialSnapshot2.positionOffsetY));
               model2.setPositionZ(GeoEasingInterpolator.lerpFloatValues(d3, (double)right2.positionOffsetZ, (double)initialSnapshot2.positionOffsetZ));
               if (d3 >= 1.0) {
                  right2.positionOffsetX = model2.getPositionX();
                  right2.positionOffsetY = model2.getPositionY();
                  right2.positionOffsetZ = model2.getPositionZ();
               }
            }

            if (!((GeoBoneDirtyTracker)entry.getValue()).hasScaleChanged) {
               if (right2.isCurrentlyRunningScaleAnimation) {
                  right2.mostRecentResetScaleTick = (float)d;
                  right2.isCurrentlyRunningScaleAnimation = false;
               }

               double d4 = Math.min((d - (double)right2.mostRecentResetScaleTick) / resetTickLength, 1.0);
               model2.setScaleX(GeoEasingInterpolator.lerpFloatValues(d4, (double)right2.scaleValueX, (double)initialSnapshot2.scaleValueX));
               model2.setScaleY(GeoEasingInterpolator.lerpFloatValues(d4, (double)right2.scaleValueY, (double)initialSnapshot2.scaleValueY));
               model2.setScaleZ(GeoEasingInterpolator.lerpFloatValues(d4, (double)right2.scaleValueZ, (double)initialSnapshot2.scaleValueZ));
               if (d4 >= 1.0) {
                  right2.scaleValueX = model2.getScaleX();
                  right2.scaleValueY = model2.getScaleY();
                  right2.scaleValueZ = model2.getScaleZ();
               }
            }
         }
      }

      orCreateAnimationData.isFirstTick = false;
   }

   public void clearModelRendererList() {
      this.modelRendererList.clear();
   }

   private void updateBoneSnapshots(HashMap map) {

      for (IGeoBone geoBone : (Iterable<IGeoBone>)(this.modelRendererList)) {
         if (!map.containsKey(geoBone.getName())) {
            map.put(geoBone.getName(), Pair.of(geoBone, new GeoBoneSnapshot(geoBone.getInitialSnapshot())));
         }
      }
   }

   public void preAnimationSetup(GeoAnimatable geoAnimatable, double d) {
      this.model.setMolangQueries(geoAnimatable, d);
   }




}
