package net.augustus.cosmetics.model;

import net.augustus.cosmetics.molang.MolangParser;
import net.augustus.cosmetics.molang.ast.ConstantValue;
import net.augustus.cosmetics.molang.ast.MolangValue;
import net.augustus.utils.UnaryFunction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;

public class GeoAnimationController {
   public UnaryFunction customEasingMethod;
   private GeoCustomInstructionListener customInstructionListener;
   public GeoAnimationBuilder currentAnimationBuilder;
   private final HashMap boneAnimationQueues;
   public double tickOffset;
   private final Set executedKeyFrames;
   private SoundKeyframeListener soundListener;
   public boolean needsAnimationReload;
   public GeoAnimationState animationState = GeoAnimationState.Stopped;
   public static List modelFetchers = new ArrayList();
   public double animationSpeed;
   private final HashMap boneSnapshots;
   public double transitionLengthTicks;
   private boolean justStopped;
   public Queue animationQueue;
   private GeoParticleKeyframeListener particleListener;
   public GeoAnimation currentAnimation;
   private final String name;
   public boolean justStartedTransition;
   public static final boolean assertionsDisabled = !GeoAnimationController.class.desiredAssertionStatus();
   public GeoAnimatable animatable;
   public boolean shouldResetTick;
   public GeoAnimationPredicate animationPredicate;
   public GeoEasingType easingType;
   public boolean isJustStarting = false;

   public GeoAnimationController(GeoAnimatable geoAnimatable, String str, float f, UnaryFunction unaryFunction, GeoAnimationPredicate geoAnimationPredicate, long l) {
      this.boneAnimationQueues = new HashMap();
      this.animationQueue = new LinkedList();
      this.currentAnimationBuilder = new GeoAnimationBuilder();
      this.needsAnimationReload = false;
      this.boneSnapshots = new HashMap();
      this.justStopped = false;
      this.justStartedTransition = false;
      this.shouldResetTick = false;
      this.animationSpeed = 1.0;
      this.executedKeyFrames = new HashSet();
      this.easingType = GeoEasingType.NONE;
      this.animatable = geoAnimatable;
      this.name = str;
      this.transitionLengthTicks = (double)f;
      this.customEasingMethod = unaryFunction;
      this.easingType = GeoEasingType.CUSTOM;
      this.animationPredicate = geoAnimationPredicate;
      this.tickOffset = 0.0;
   }

   private IGeoAnimatableModel getModel(GeoAnimatable geoAnimatable) {
      for (GeoModelFetcher geoModelFetcher : (Iterable<GeoModelFetcher>)(modelFetchers)) {
         IGeoAnimatableModel geoAnimatableModel = (IGeoAnimatableModel)geoModelFetcher.apply(geoAnimatable);
         if (geoAnimatableModel != null) {
            return geoAnimatableModel;
         }
      }

      System.out.printf("Could not find suitable model for animatable of type %s. Did you register a Model Fetcher?%n", geoAnimatable.getClass());
      return null;
   }

   public GeoAnimation getCurrentAnimation() {
      return this.currentAnimation;
   }

   private void resetEventKeyFrames() {
      this.executedKeyFrames.clear();
   }


   private void saveSnapshotsForAnimation(GeoAnimation geoAnimation, HashMap map) {

      for (Pair pair : (Iterable<Pair>)(map.values())) {
         if (geoAnimation != null && geoAnimation.boneAnimations != null) {
            if (geoAnimation.boneAnimations.stream().anyMatch(obj -> ((GeoBoneAnimation)obj).boneName.equals(((IGeoBone)pair.getLeft()).getName()))) {
               this.boneSnapshots.put(((IGeoBone)pair.getLeft()).getName(), new GeoBoneSnapshot((GeoBoneSnapshot)pair.getRight()));
            }
         }
      }
   }

   public GeoAnimationController(GeoAnimatable geoAnimatable, String str, float f, GeoEasingType geoEasingType, long l, GeoAnimationPredicate geoAnimationPredicate) {
      this.boneAnimationQueues = new HashMap();
      this.animationQueue = new LinkedList();
      this.currentAnimationBuilder = new GeoAnimationBuilder();
      this.needsAnimationReload = false;
      this.boneSnapshots = new HashMap();
      this.justStopped = false;
      this.justStartedTransition = false;
      this.shouldResetTick = false;
      this.animationSpeed = 1.0;
      this.executedKeyFrames = new HashSet();
      this.easingType = GeoEasingType.NONE;
      this.animatable = geoAnimatable;
      this.name = str;
      this.transitionLengthTicks = (double)f;
      this.easingType = geoEasingType;
      this.animationPredicate = geoAnimationPredicate;
      this.tickOffset = 0.0;
   }

   public GeoPlayState testAnimationPredicate(GeoAnimationEvent geoAnimationEvent) {
      return this.animationPredicate.test(geoAnimationEvent);
   }

   private GeoAnimationPoint getAnimationPointAtTick(List list, double d, boolean flag, GeoAnimationAxis geoAnimationAxis) {
      GeoKeyFrameLocation geoKeyFrameLocation = this.getCurrentKeyFrameLocation(list, d);
      AnimationKeyframe keyframe = geoKeyFrameLocation.keyframe;
      double d2 = ((MolangValue)keyframe.getStartValue()).get();
      double d3 = ((MolangValue)keyframe.getEndValue()).get();
      if (flag) {
         if (!(keyframe.getStartValue() instanceof ConstantValue)) {
            d2 = Math.toRadians(d2);
            if (geoAnimationAxis == GeoAnimationAxis.X || geoAnimationAxis == GeoAnimationAxis.Y) {
               d2 *= -1.0;
            }
         }

         if (!(keyframe.getEndValue() instanceof ConstantValue)) {
            d3 = Math.toRadians(d3);
            if (geoAnimationAxis == GeoAnimationAxis.X || geoAnimationAxis == GeoAnimationAxis.Y) {
               d3 *= -1.0;
            }
         }
      }

      return new GeoAnimationPoint(keyframe, geoKeyFrameLocation.startTick, keyframe.getLength(), d2, d3);
   }

   public double adjustTick(double d) {
      if (this.needsAnimationReload) {
         if (this.getAnimationState() == GeoAnimationState.Transitioning) {
            this.tickOffset = d;
         } else if (this.getAnimationState() == GeoAnimationState.Running) {
            this.tickOffset = d;
         }

         this.needsAnimationReload = false;
         return 0.0;
      } else {
         return this.animationSpeed * Math.max(d - this.tickOffset, 0.0);
      }
   }

   public void setCustomInstructionListener(GeoCustomInstructionListener listener) {
      this.customInstructionListener = listener;
   }

   public void markNeedsReload() {
      this.shouldResetTick = true;
   }

   public double getAnimationSpeed() {
      return this.animationSpeed;
   }

   private GeoAnimation resolveQueuedAnimation(IGeoAnimatableModel geoAnimatableModel, AtomicBoolean atomicBoolean, GeoQueuedAnimation geoQueuedAnimation) {
      GeoAnimatable animatable2 = this.animatable;
      GeoAnimation animation = geoAnimatableModel.getAnimation(geoQueuedAnimation.animationName, animatable2);
      if (animation == null) {
         System.out.printf("Could not load animation: %s. Is it missing?", geoQueuedAnimation.animationName);
         atomicBoolean.set(true);
      }

      if (animation != null && geoQueuedAnimation.loop != null) {
         animation.loop = geoQueuedAnimation.loop;
      }

      return animation;
   }

   public static void removeModelFetcher(GeoModelFetcher geoModelFetcher) {
      Objects.requireNonNull(geoModelFetcher);
      modelFetchers.remove(geoModelFetcher);
   }

   public HashMap getBoneAnimationQueues() {
      return this.boneAnimationQueues;
   }

   public void setParticleListener(GeoParticleKeyframeListener listener) {
      this.particleListener = listener;
   }

   public void setAnimationSpeed(double d) {
      this.animationSpeed = d;
   }

   public String getName() {
      return this.name;
   }

   public GeoAnimationController(GeoAnimatable geoAnimatable, String str, float f, GeoAnimationPredicate geoAnimationPredicate) {
      this.boneAnimationQueues = new HashMap();
      this.animationQueue = new LinkedList();
      this.currentAnimationBuilder = new GeoAnimationBuilder();
      this.needsAnimationReload = false;
      this.boneSnapshots = new HashMap();
      this.justStopped = false;
      this.justStartedTransition = false;
      this.shouldResetTick = false;
      this.animationSpeed = 1.0;
      this.executedKeyFrames = new HashSet();
      this.easingType = GeoEasingType.NONE;
      this.animatable = geoAnimatable;
      this.name = str;
      this.transitionLengthTicks = (double)f;
      this.animationPredicate = geoAnimationPredicate;
      this.tickOffset = 0.0;
   }

   private void setAnimTime(MolangParser molangParser, double d) {
      double d2 = d / 20.0;
      molangParser.setValue("query.anim_time", d2);
   }

   public void setSoundListener(SoundKeyframeListener listener) {
      this.soundListener = listener;
   }

   public void setAnimation(GeoAnimationBuilder geoAnimationBuilder) {
      IGeoAnimatableModel model = this.getModel(this.animatable);
      if (model != null) {
         if (geoAnimationBuilder != null && geoAnimationBuilder.getAnimationList().size() != 0) {
            if (!geoAnimationBuilder.getAnimationList().equals(this.currentAnimationBuilder.getAnimationList()) || this.shouldResetTick) {
               AtomicBoolean atomicBoolean = new AtomicBoolean(false);
               LinkedList list = (LinkedList)geoAnimationBuilder.getAnimationList()
                  .stream()
                  .map(obj -> this.resolveQueuedAnimation(model, atomicBoolean, (GeoQueuedAnimation)obj))
                  .collect(Collectors.toCollection(LinkedList::new));
               if (atomicBoolean.get()) {
                  return;
               }

               this.animationQueue = list;
               this.currentAnimationBuilder = geoAnimationBuilder;
               this.needsAnimationReload = true;
               this.animationState = GeoAnimationState.Transitioning;
               this.justStartedTransition = true;
               this.shouldResetTick = false;
            }
         } else {
            this.animationState = GeoAnimationState.Stopped;
         }
      }
   }

   public GeoAnimationState getAnimationState() {
      return this.animationState;
   }

   public void clearAnimationCache() {
      this.currentAnimationBuilder = new GeoAnimationBuilder();
   }

   private void processCurrentAnimation(double d, double d2, MolangParser molangParser, boolean flag) {
      if (!assertionsDisabled && this.currentAnimation == null) {
         throw new AssertionError();
      } else {
         if (d >= this.currentAnimation.animationLength) {
            this.resetEventKeyFrames();
            if (!this.currentAnimation.loop) {
               if (((GeoAnimation)this.animationQueue.peek()) == null) {
                  this.animationState = GeoAnimationState.Stopped;
                  return;
               }

               this.animationState = GeoAnimationState.Transitioning;
               this.needsAnimationReload = true;
               this.currentAnimation = (GeoAnimation)this.animationQueue.peek();
            } else {
               this.needsAnimationReload = true;
               d = this.adjustTick(d2);
            }
         }

         this.setAnimTime(molangParser, d);

         for (GeoBoneAnimation geoBoneAnimation : (Iterable<GeoBoneAnimation>)(this.currentAnimation.boneAnimations)) {
            GeoBoneAnimationQueue geoBoneAnimationQueue = (GeoBoneAnimationQueue)this.boneAnimationQueues.get(geoBoneAnimation.boneName);
            if (geoBoneAnimationQueue == null) {
               if (flag) {
                  throw new RuntimeException("Could not find bone: " + geoBoneAnimation.boneName);
               }
            } else {
               GeoVectorKeyFrameList rotationKeyFrames = geoBoneAnimation.rotationKeyFrames;
               GeoVectorKeyFrameList positionKeyFrames = geoBoneAnimation.positionKeyFrames;
               GeoVectorKeyFrameList scaleKeyFrames = geoBoneAnimation.scaleKeyFrames;
               if (!rotationKeyFrames.xKeyFrames.isEmpty()) {
                  GeoAnimationAxis geoAnimationAxis = GeoAnimationAxis.X;
                  List xKeyFrames = rotationKeyFrames.xKeyFrames;
                  geoBoneAnimationQueue.rotationXQueue.add(this.getAnimationPointAtTick(xKeyFrames, d, true, geoAnimationAxis));
                  GeoAnimationAxis geoAnimationAxis2 = GeoAnimationAxis.Y;
                  List yKeyFrames = rotationKeyFrames.yKeyFrames;
                  geoBoneAnimationQueue.rotationYQueue.add(this.getAnimationPointAtTick(yKeyFrames, d, true, geoAnimationAxis2));
                  GeoAnimationAxis geoAnimationAxis3 = GeoAnimationAxis.Z;
                  List zKeyFrames = rotationKeyFrames.zKeyFrames;
                  geoBoneAnimationQueue.rotationZQueue.add(this.getAnimationPointAtTick(zKeyFrames, d, true, geoAnimationAxis3));
               }

               if (!positionKeyFrames.xKeyFrames.isEmpty()) {
                  GeoAnimationAxis geoAnimationAxis4 = GeoAnimationAxis.X;
                  List xKeyFrames2 = positionKeyFrames.xKeyFrames;
                  geoBoneAnimationQueue.positionXQueue.add(this.getAnimationPointAtTick(xKeyFrames2, d, false, geoAnimationAxis4));
                  GeoAnimationAxis geoAnimationAxis5 = GeoAnimationAxis.Y;
                  List yKeyFrames2 = positionKeyFrames.yKeyFrames;
                  geoBoneAnimationQueue.positionYQueue.add(this.getAnimationPointAtTick(yKeyFrames2, d, false, geoAnimationAxis5));
                  GeoAnimationAxis geoAnimationAxis6 = GeoAnimationAxis.Z;
                  List zKeyFrames2 = positionKeyFrames.zKeyFrames;
                  geoBoneAnimationQueue.positionZQueue.add(this.getAnimationPointAtTick(zKeyFrames2, d, false, geoAnimationAxis6));
               }

               if (!scaleKeyFrames.xKeyFrames.isEmpty()) {
                  GeoAnimationAxis geoAnimationAxis7 = GeoAnimationAxis.X;
                  List xKeyFrames3 = scaleKeyFrames.xKeyFrames;
                  geoBoneAnimationQueue.scaleXQueue.add(this.getAnimationPointAtTick(xKeyFrames3, d, false, geoAnimationAxis7));
                  GeoAnimationAxis geoAnimationAxis8 = GeoAnimationAxis.Y;
                  List yKeyFrames3 = scaleKeyFrames.yKeyFrames;
                  geoBoneAnimationQueue.scaleYQueue.add(this.getAnimationPointAtTick(yKeyFrames3, d, false, geoAnimationAxis8));
                  GeoAnimationAxis geoAnimationAxis9 = GeoAnimationAxis.Z;
                  List zKeyFrames3 = scaleKeyFrames.zKeyFrames;
                  geoBoneAnimationQueue.scaleZQueue.add(this.getAnimationPointAtTick(zKeyFrames3, d, false, geoAnimationAxis9));
               }
            }
         }

         if (this.soundListener != null || this.particleListener != null || this.customInstructionListener != null) {
            for (GeoEventKeyFrame geoEventKeyFrame : (Iterable<GeoEventKeyFrame>)(this.currentAnimation.soundKeyFrames)) {
               if (!this.executedKeyFrames.contains(geoEventKeyFrame) && d >= geoEventKeyFrame.getStartTick()) {
                  SoundKeyframeEvent soundKeyframeEvent = new SoundKeyframeEvent(this.animatable, d, (String)geoEventKeyFrame.getEventData(), this);
                  this.soundListener.playSound(soundKeyframeEvent);
                  this.executedKeyFrames.add(geoEventKeyFrame);
               }
            }

            for (GeoParticleEventKeyFrame geoParticleEventKeyFrame : (Iterable<GeoParticleEventKeyFrame>)(this.currentAnimation.particleKeyFrames)) {
               if (!this.executedKeyFrames.contains(geoParticleEventKeyFrame) && d >= geoParticleEventKeyFrame.getStartTick()) {
                  ParticleKeyframeEvent particleKeyframeEvent = new ParticleKeyframeEvent(this.animatable, d, geoParticleEventKeyFrame.effect, geoParticleEventKeyFrame.locator, geoParticleEventKeyFrame.script, this);
                  this.particleListener.summonParticle(particleKeyframeEvent);
                  this.executedKeyFrames.add(geoParticleEventKeyFrame);
               }
            }

            for (GeoEventKeyFrame geoEventKeyFrame2 : (Iterable<GeoEventKeyFrame>)(this.currentAnimation.customInstructionKeyframes)) {
               if (!this.executedKeyFrames.contains(geoEventKeyFrame2) && d >= geoEventKeyFrame2.getStartTick()) {
                  CustomInstructionKeyframeEvent event = new CustomInstructionKeyframeEvent(this.animatable, d, (String)geoEventKeyFrame2.getEventData(), this);
                  this.customInstructionListener.executeInstruction(event);
                  this.executedKeyFrames.add(geoEventKeyFrame2);
               }
            }
         }

         if (this.transitionLengthTicks == 0.0 && this.needsAnimationReload && this.animationState == GeoAnimationState.Transitioning) {
            this.currentAnimation = (GeoAnimation)this.animationQueue.poll();
         }
      }
   }

   public static void addModelFetcher(GeoModelFetcher geoModelFetcher) {
      modelFetchers.add(geoModelFetcher);
   }

   public void process(double d, GeoAnimationEvent geoAnimationEvent, List list, HashMap map, MolangParser molangParser, boolean flag) {
      double d2 = d / 20.0;
      molangParser.setValue("query.life_time", d2);
      if (this.currentAnimation != null) {
         IGeoAnimatableModel model = this.getModel(this.animatable);
         if (model != null) {
            GeoAnimatable animatable3 = this.animatable;
            GeoAnimation animation = model.getAnimation(this.currentAnimation.animationName, animatable3);
            if (animation != null) {
               boolean loop = this.currentAnimation.loop;
               this.currentAnimation = animation;
               this.currentAnimation.loop = loop;
            }
         }
      }

      this.createInitialQueues(list);
      d = this.adjustTick(d);
      if (this.animationState == GeoAnimationState.Transitioning && d >= this.transitionLengthTicks) {
         this.needsAnimationReload = true;
         this.animationState = GeoAnimationState.Running;
         d = this.adjustTick(d);
      }

      if (!assertionsDisabled && !(d >= 0.0)) {
         throw new AssertionError("GeckoLib: Tick was less than zero");
      } else {
         if (this.testAnimationPredicate(geoAnimationEvent) != GeoPlayState.STOP && (this.currentAnimation != null || this.animationQueue.size() != 0)) {
            if (!this.justStartedTransition || !this.needsAnimationReload && !this.justStopped) {
               if (this.currentAnimation == null && this.animationQueue.size() != 0) {
                  this.needsAnimationReload = true;
                  this.animationState = GeoAnimationState.Transitioning;
                  this.justStartedTransition = true;
                  this.shouldResetTick = false;
                  d = this.adjustTick(d);
               } else if (this.animationState != GeoAnimationState.Transitioning) {
                  this.animationState = GeoAnimationState.Running;
               }
            } else {
               this.justStopped = false;
               d = this.adjustTick(d);
            }

            if (this.animationState == GeoAnimationState.Transitioning) {
               if (d == 0.0 || this.isJustStarting) {
                  this.justStartedTransition = false;
                  this.currentAnimation = (GeoAnimation)this.animationQueue.poll();
                  this.resetEventKeyFrames();
                  this.saveSnapshotsForAnimation(this.currentAnimation, map);
               }

               if (this.currentAnimation != null) {
                  this.setAnimTime(molangParser, 0.0);

                  for (GeoBoneAnimation geoBoneAnimation : (Iterable<GeoBoneAnimation>)(this.currentAnimation.boneAnimations)) {
                     GeoBoneAnimationQueue geoBoneAnimationQueue = (GeoBoneAnimationQueue)this.boneAnimationQueues.get(geoBoneAnimation.boneName);
                     GeoBoneSnapshot geoBoneSnapshot = (GeoBoneSnapshot)this.boneSnapshots.get(geoBoneAnimation.boneName);
                     Optional optional = list.stream().filter(obj -> ((IGeoBone)obj).getName().equals(geoBoneAnimation.boneName)).findFirst();
                     if (!optional.isPresent()) {
                        if (flag) {
                           throw new RuntimeException("Could not find bone: " + geoBoneAnimation.boneName);
                        }
                     } else {
                        GeoBoneSnapshot initialSnapshot = ((IGeoBone)optional.get()).getInitialSnapshot();
                        if (!assertionsDisabled && geoBoneSnapshot == null) {
                           throw new AssertionError("Bone snapshot was null");
                        }

                        GeoVectorKeyFrameList rotationKeyFrames = geoBoneAnimation.rotationKeyFrames;
                        GeoVectorKeyFrameList positionKeyFrames = geoBoneAnimation.positionKeyFrames;
                        GeoVectorKeyFrameList scaleKeyFrames = geoBoneAnimation.scaleKeyFrames;
                        if (!rotationKeyFrames.xKeyFrames.isEmpty()) {
                           GeoAnimationAxis geoAnimationAxis = GeoAnimationAxis.X;
                           double d4 = 0.0;
                           GeoAnimationPoint animationPointAtTick = this.getAnimationPointAtTick(rotationKeyFrames.xKeyFrames, d4, true, geoAnimationAxis);
                           GeoAnimationAxis geoAnimationAxis2 = GeoAnimationAxis.Y;
                           double d5 = 0.0;
                           GeoAnimationPoint animationPointAtTick2 = this.getAnimationPointAtTick(rotationKeyFrames.yKeyFrames, d5, true, geoAnimationAxis2);
                           GeoAnimationAxis geoAnimationAxis3 = GeoAnimationAxis.Z;
                           double d6 = 0.0;
                           GeoAnimationPoint animationPointAtTick3 = this.getAnimationPointAtTick(rotationKeyFrames.zKeyFrames, d6, true, geoAnimationAxis3);
                           geoBoneAnimationQueue.rotationXQueue.add(new GeoAnimationPoint((AnimationKeyframe)null, d, this.transitionLengthTicks, geoBoneSnapshot.rotationValueX - initialSnapshot.rotationValueX, animationPointAtTick.animationStartValue.doubleValue()));
                           geoBoneAnimationQueue.rotationYQueue.add(new GeoAnimationPoint((AnimationKeyframe)null, d, this.transitionLengthTicks, geoBoneSnapshot.rotationValueY - initialSnapshot.rotationValueY, animationPointAtTick2.animationStartValue.doubleValue()));
                           geoBoneAnimationQueue.rotationZQueue.add(new GeoAnimationPoint((AnimationKeyframe)null, d, this.transitionLengthTicks, geoBoneSnapshot.rotationValueZ - initialSnapshot.rotationValueZ, animationPointAtTick3.animationStartValue.doubleValue()));
                        }

                        if (!positionKeyFrames.xKeyFrames.isEmpty()) {
                           GeoAnimationAxis geoAnimationAxis4 = GeoAnimationAxis.X;
                           double d7 = 0.0;
                           GeoAnimationPoint animationPointAtTick4 = this.getAnimationPointAtTick(positionKeyFrames.xKeyFrames, d7, false, geoAnimationAxis4);
                           GeoAnimationAxis geoAnimationAxis5 = GeoAnimationAxis.Y;
                           double d8 = 0.0;
                           GeoAnimationPoint animationPointAtTick5 = this.getAnimationPointAtTick(positionKeyFrames.yKeyFrames, d8, false, geoAnimationAxis5);
                           GeoAnimationAxis geoAnimationAxis6 = GeoAnimationAxis.Z;
                           double d9 = 0.0;
                           GeoAnimationPoint animationPointAtTick6 = this.getAnimationPointAtTick(positionKeyFrames.zKeyFrames, d9, false, geoAnimationAxis6);
                           geoBoneAnimationQueue.positionXQueue.add(new GeoAnimationPoint((AnimationKeyframe)null, d, this.transitionLengthTicks, geoBoneSnapshot.positionOffsetX, animationPointAtTick4.animationStartValue.doubleValue()));
                           geoBoneAnimationQueue.positionYQueue.add(new GeoAnimationPoint((AnimationKeyframe)null, d, this.transitionLengthTicks, geoBoneSnapshot.positionOffsetY, animationPointAtTick5.animationStartValue.doubleValue()));
                           geoBoneAnimationQueue.positionZQueue.add(new GeoAnimationPoint((AnimationKeyframe)null, d, this.transitionLengthTicks, geoBoneSnapshot.positionOffsetZ, animationPointAtTick6.animationStartValue.doubleValue()));
                        }

                        if (!scaleKeyFrames.xKeyFrames.isEmpty()) {
                           GeoAnimationAxis geoAnimationAxis7 = GeoAnimationAxis.X;
                           double d10 = 0.0;
                           GeoAnimationPoint animationPointAtTick7 = this.getAnimationPointAtTick(scaleKeyFrames.xKeyFrames, d10, false, geoAnimationAxis7);
                           GeoAnimationAxis geoAnimationAxis8 = GeoAnimationAxis.Y;
                           double d11 = 0.0;
                           GeoAnimationPoint animationPointAtTick8 = this.getAnimationPointAtTick(scaleKeyFrames.yKeyFrames, d11, false, geoAnimationAxis8);
                           GeoAnimationAxis geoAnimationAxis9 = GeoAnimationAxis.Z;
                           double d12 = 0.0;
                           GeoAnimationPoint animationPointAtTick9 = this.getAnimationPointAtTick(scaleKeyFrames.zKeyFrames, d12, false, geoAnimationAxis9);
                           geoBoneAnimationQueue.scaleXQueue.add(new GeoAnimationPoint((AnimationKeyframe)null, d, this.transitionLengthTicks, geoBoneSnapshot.scaleValueX, animationPointAtTick7.animationStartValue.doubleValue()));
                           geoBoneAnimationQueue.scaleYQueue.add(new GeoAnimationPoint((AnimationKeyframe)null, d, this.transitionLengthTicks, geoBoneSnapshot.scaleValueY, animationPointAtTick8.animationStartValue.doubleValue()));
                           geoBoneAnimationQueue.scaleZQueue.add(new GeoAnimationPoint((AnimationKeyframe)null, d, this.transitionLengthTicks, geoBoneSnapshot.scaleValueZ, animationPointAtTick9.animationStartValue.doubleValue()));
                        }
                     }
                  }
               }
            } else if (this.getAnimationState() == GeoAnimationState.Running) {
               this.processCurrentAnimation(d, d, molangParser, flag);
            }
         } else {
            this.animationState = GeoAnimationState.Stopped;
            this.justStopped = true;
         }
      }
   }

   private GeoKeyFrameLocation getCurrentKeyFrameLocation(List list, double d) {
      double d2 = 0.0;

      for (AnimationKeyframe animationKeyframe : (Iterable<AnimationKeyframe>)(list)) {
         d2 += animationKeyframe.getLength();
         if (d2 > d) {
            double d3 = d - (d2 - animationKeyframe.getLength());
            return new GeoKeyFrameLocation(animationKeyframe, d3);
         }
      }

      return new GeoKeyFrameLocation((AnimationKeyframe)list.get(list.size() - 1), d);
   }

   private void createInitialQueues(List list) {
      this.boneAnimationQueues.clear();

      for (IGeoBone geoBone : (Iterable<IGeoBone>)(list)) {
         this.boneAnimationQueues.put(geoBone.getName(), new GeoBoneAnimationQueue(geoBone));
      }
   }



}
