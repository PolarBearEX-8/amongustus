package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.ClientManagers;
import net.augustus.events.EventMoveInput;
import net.augustus.events.MoveInputStage;
import net.augustus.settings.NoiseLayerSetting;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.VarianceNoiseSetting;
import net.augustus.utils.TuplePair;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.rotation.EntityBoundsUtil;
import net.augustus.utils.rotation.HitboxAngleUtil;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.utils.rotation.Rotation;
import net.augustus.utils.rotation.RotationSilenceController;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.utils.simulation.EntityMotionSnapshot;
import net.augustus.utils.simulation.EntityMovementPredictor;
import net.augustus.utils.simulation.TrajectoryPredictionUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class UNoise3PointSelector {
   private VarianceNoiseSetting uNoise3HorizontalOffset;
   private ArrayList allAimModes;
   private RotationSilenceController globalSilenceController;
   private UNoise3SettingContext uNoise3SettingContext;
   private ArrayList horizontalAimModes;
   private DoubleValue uNoise3constantbbsizeoorv;
   private RotationSilenceController horizontalPredSilenceController;
   private TickDelayedPointCache futurePitchBoundsCache;
   private RotationSilenceController verticalSilenceController;
   private boolean firstTickOnTarget;
   public double lastOutputPitch;
   public double unsilencedPredPitch;
   private RotationSilenceController verticalPredSilenceController;
   private RandomizedRangeSettings noobDelaySetting;
   private BooleanValue uNoise3constantvbbsizeoor;
   private DoubleValue uNoise3horizontalfastthreshold;
   private BooleanValue uNoise3constantverticalbb;
   private double latestClampedNoobPitch;
   private RandomizedRangeSettings futureDelaySetting;
   private final double[][] emptyBoundsPair;
   public double unsilencedPredYaw;
   private TickDelayedPointCache noobPitchCache;
   private UNoise3RandomForceTicker uNoise3RandomForceTicker;
   public double unsilencedYaw;
   private UNoise3PredictionScheduler uNoise3PredictionScheduler;
   private ArrayList verticalAimModes;
   private DoubleValue uNoise3constantverticalboundingboxsize;
   public double lastOutputYaw;
   private RotationSilenceController horizontalSilenceController;
   private DoubleValue uNoise3verticalfastthreshold;
   public double unsilencedPitch;
   public EntityPlayerSP player;
   private ArrayList horizontalPredictedAimModes;
   private RandomizedRangeSettings futureIntervalDelaySetting;
   private TickDelayedPointCache futurePitchCache;
   private DoubleValue uNoise3constantvbbsizeoorv;
   private DoubleValue uNoise3constanthorizontalboundingboxsize;
   private VarianceNoiseSetting uNoise3VerticalOffset;
   private ArrayList verticalPredictedAimModes;
   private BooleanValue uNoise3constanthorizontalbb;
   private BooleanValue uNoise3constantbbsizeoor;

   private static boolean isAngleWithinBounds(double[] doubles, double d, boolean flag) {
      double d2;
      if (flag) {
         d2 = HitboxAngleUtil.inverseLerpRange(doubles, d);
      } else {
         d2 = HitboxAngleUtil.getNormalizedYawPosition(doubles[0], doubles[1], d);
      }

      return d2 >= 0.0 && d2 <= 1.0;
   }

   private double[] computeOptimalFuturePitchRange(ArrayList list, ArrayList list2, Entity entity, double d, double d2) {
      int n = Math.min(list.size(), list2.size());
      double[][] doublesArr = new double[n][];
      ArrayList list3 = new ArrayList();

      for (int i = 0; i < n; i++) {
         AxisAlignedBB entityBoundingBoxAt = EntityBoundsUtil.getEntityBoundingBoxAt(entity, (Vec3)list2.get(i), true);
         double[] pitchRange = HitboxAngleUtil.getPitchRange(((Vec3)list.get(i)).addVector(0.0, d, 0.0), entityBoundingBoxAt);
         doublesArr[i] = pitchRange;
         list3.add(pitchRange[0]);
         list3.add(pitchRange[1]);
      }

      double d3 = d2;
      double d4 = d2;
      double d5 = 0.0;
      int n2 = -1;
      int n3 = 0;

      for (int j = 0; j < list3.size(); j++) {
         double d6 = (Double)list3.get(j);
         int n4 = 0;

         for (double[] doubles : doublesArr) {
            if (d6 >= doubles[0] && d6 <= doubles[1]) {
               n4++;
            }
         }

         if (n4 > n2 || n4 == n2 && Math.abs(d2 - d6) < d5) {
            d3 = d6;
            d5 = Math.abs(d2 - d6);
            n2 = n4;
            n3 = j;
         }
      }

      d5 = 0.0;
      n2 = -1;

      for (int k = 0; k < list3.size(); k++) {
         if (k != n3) {
            double d7 = (Double)list3.get(k);
            if (d7 != d3) {
               int n5 = 0;

               for (double[] doubles2 : doublesArr) {
                  if (d7 >= doubles2[0] && d7 <= doubles2[1]) {
                     n5++;
                  }
               }

               if (n5 > n2 || n5 == n2 && Math.abs(d3 - d7) < d5) {
                  d4 = d7;
                  d5 = Math.abs(d3 - d7);
                  n2 = n5;
                  n3 = k;
               }
            }
         }
      }

      return new double[]{d3, d4};
   }

   public void resetState(double d, double d2) {
      this.lastOutputYaw = this.unsilencedYaw = this.unsilencedPredYaw = d;
      this.lastOutputPitch = this.unsilencedPitch = this.unsilencedPredPitch = d2;
      this.noobPitchCache.resetDelay();
      this.futurePitchBoundsCache.resetDelay();
      this.futurePitchCache.resetDelay();
      this.globalSilenceController.reset();
      this.horizontalSilenceController.reset();
      this.verticalSilenceController.reset();
      this.horizontalPredSilenceController.reset();
      this.verticalPredSilenceController.reset();
      this.firstTickOnTarget = true;
      UNoise3CircleAimMode.randomizeAllPhases();
   }

   public static double computeWeightedAngleAverage(ArrayList list, ArrayList list2, boolean flag) {
      double d = 0.0;
      double d2 = 0.0;
      double naN = Double.NaN;
      list.addAll(list2);

      for (int i = 0; i < list.size(); i++) {
         Vec2d vec2d = (Vec2d)list.get(i);
         if (flag && Double.isFinite(naN)) {
            for (double j = vec2d.x - naN; j > 180.0 || j < -180.0; j = vec2d.x - naN) {
               if (j > 180.0) {
                  vec2d.x -= 360.0;
               } else {
                  vec2d.x += 360.0;
               }
            }
         }

         d += vec2d.x * vec2d.y;
         d2 += vec2d.y;
         naN = vec2d.x;
      }

      return d2 == 0.0 ? 0.0 : d / d2;
   }

   private double sampleFutureIntervalDelay() {
      return this.futureIntervalDelaySetting.nextValue(true);
   }

   private ArrayList[] sampleHittableSurfaceGrid(Vec3 vec, EntityLivingBase entity, AxisAlignedBB box, double d) {
      if (RotationUtil.clampPointToBox(vec, box).distanceTo(vec) > d) {
         return new ArrayList[]{new ArrayList(), new ArrayList()};
      } else {
         double[] yawRange = HitboxAngleUtil.getYawRange(vec, box);
         double[] pitchRange = HitboxAngleUtil.getPitchRange(vec, box);
         double d2 = 0.02;
         float rotationYaw = this.player.rotationYaw;
         float rotationPitch = this.player.rotationPitch;
         float f = MathHelper.wrapAngleTo180_float(rotationYaw);
         ArrayList list = new ArrayList();
         ArrayList list2 = new ArrayList();
         double d3 = vec.yCoord - (double)Minecraft.getMinecraft().thePlayer.getEyeHeight();
         double d4 = d * d;

         for (int i = 0; i <= 50; i++) {
            double d5 = (double)i * 0.02;
            float f3 = RotationUtil.quantizeAngleDelta(rotationYaw, (float)EntityBoundsUtil.getShortestSignedAngleDelta((double)f, (double)MathHelper.wrapAngleTo180_float((float)HitboxAngleUtil.lerpRange(yawRange, d5))));
            ArrayList list3 = new ArrayList();
            ArrayList list4 = new ArrayList();

            for (int j = 0; j <= 50; j++) {
               double d6 = (double)j * 0.02;
               float f5 = RotationUtil.quantizeAngleDelta(rotationPitch, ((float)HitboxAngleUtil.lerpRange(pitchRange, d6)) - rotationPitch);
               MovingObjectPosition hit = RayTraceUtil.rayTraceCustom(1.0F, f3, f5, 15.0, 15.0, 0.0, false, vec.xCoord, d3, vec.zCoord, box, entity);
               if (hit.entityHit == entity) {
                  list3.add(new Vec2d(d5, d6));
                  if (hit.hitVec.squareDistanceTo(vec) <= d4) {
                     list4.add(new Vec2d(d5, d6));
                  } else {
                     list4.add(null);
                  }
               } else {
                  list3.add(null);
                  list4.add(null);
               }
            }

            list.add(list4);
            list2.add(list3);
         }

         return new ArrayList[]{list, list2};
      }
   }

   private void updateFuturePitchCaches(EntityPlayerSP player2, EntityLivingBase entity, double d, double d2) {
      double posX = player2.posX;
      double posY = player2.posY;
      double posZ = player2.posZ;
      ArrayList list = PlayerUtil.predictPlayerTrajectory(5, player2, posX, posY, posZ, new EventMoveInput(
         player2.movementInput.moveStrafe, player2.movementInput.moveForward, player2.movementInput.jump, player2.movementInput.sneak, MoveInputStage.PRE
      ));
      ArrayList list2 = TrajectoryPredictionUtil.predictTargetPositions(player2, entity, player2.ticksExisted, player2.ticksExisted + list.size());
      list.add(player2.getInterpolatedPos(1.0F));
      list2.add(entity.getInterpolatedPos(1.0F));
      posZ = (double)player2.getEyeHeight();
      double[] doubles = this.computeOptimalFuturePitchRange(list, list2, entity, posZ, d);
      double d7 = doubles[0];
      double d8 = doubles[1];
      double[] doubles2 = new double[]{Math.min(d7, d8), Math.max(d7, d8)};
      double d9 = MathHelper.clamp_double(d, doubles2[0], doubles2[1]);
      this.futurePitchBoundsCache.update(doubles2);
      TickDelayedPointCache futurePitchCache2 = this.futurePitchCache;
      Double d10 = d9;
      futurePitchCache2.update(d10);
      this.latestClampedNoobPitch = d9;
      if (d2 < 3.1 && player2.onGround) {
         int size = list.size();
         posX = player2.posX;
         posY = player2.posY;
         double posZ2 = player2.posZ;
         EventMoveInput event2 = new EventMoveInput(
            player2.movementInput.moveStrafe,
            player2.movementInput.moveForward,
            player2.movementInput.jump,
            player2.movementInput.sneak,
            MoveInputStage.PRE
         );
         list = PlayerUtil.predictPlayerTrajectory(size, player2, posX, posY, posZ2, 0.25, event2);
         list.add(player2.getInterpolatedPos(1.0F));
         ArrayList list3 = (ArrayList)list.clone();
         List list4 = Minecraft.getMinecraft().theWorld.getCollidingBoundingBoxesCapped(entity, entity.getEntityBoundingBox().addCoord(0.0, -0.11125, 0.0));
         double posY2 = entity.posY;
         double d16 = -0.11125;
         boolean flag = false;

         for (AxisAlignedBB box : (Iterable<AxisAlignedBB>)(list4)) {
            d16 = box.calculateYOffset(entity.getEntityBoundingBox(), d16);
            if (d16 != posY2) {
               flag = true;
               break;
            }
         }

         if (flag && entity.motionSnapshotHistory.size() > 0) {
            EntityMotionSnapshot fromEnd = (EntityMotionSnapshot)entity.motionSnapshotHistory.getFromEnd((byte)0);
            double d17 = 0.0;
            double d18 = 0.25;
            ArrayList list5 = EntityMovementPredictor.predictTrajectoryFromSnapshot(entity, fromEnd, (byte)5, 0.0, d18, d17);
            list2.addAll(list5);
            list2.add(entity.getInterpolatedPos(1.0F));
            list.addAll(list);
            list.add(player2.getInterpolatedPos(1.0F));
            list2.addAll(list5);
            list2.add(entity.getInterpolatedPos(1.0F));
            list.addAll(list3);
            list.add(player2.getInterpolatedPos(1.0F));
         }

         posY = (double)player2.getEyeHeight();
         doubles = this.computeOptimalFuturePitchRange(list, list2, entity, posY, d);
         d7 = doubles[0];
         d8 = doubles[1];
         doubles2 = new double[]{Math.min(d7, d8), Math.max(d7, d8)};
         double[] pitchRange = HitboxAngleUtil.getPitchRange(player2.getPositionEyes(1.0F), EntityBoundsUtil.getEntityBoundingBoxAt(entity, entity.getInterpolatedPos(1.0F), true));
         doubles2[0] = MathHelper.clamp_double(doubles2[0], pitchRange[0], pitchRange[1]);
         doubles2[1] = MathHelper.clamp_double(doubles2[1], pitchRange[0], pitchRange[1]);
         this.latestClampedNoobPitch = MathHelper.clamp_double(d, doubles2[0], doubles2[1]);
      }

      futurePitchCache2 = this.noobPitchCache;
      Double latestClampedNoobPitch2 = this.latestClampedNoobPitch;
      futurePitchCache2.update(latestClampedNoobPitch2);
   }

   private static TuplePair findLargestHittableRectangle(ArrayList list) {
      if (list != null && !list.isEmpty()) {
         int size = list.size();
         int n = 0;

         for (List list2 : (Iterable<List>)(list)) {
            if (list2 != null) {
               n = Math.max(n, list2.size());
            }
         }

         if (n == 0) {
            return null;
         } else {
            int[] ints = new int[size];
            UNoise3HitboxGridRect uNoise3HitboxGridRect = null;
            int n2 = 0;

            for (int i = 0; i < n; i++) {
               for (int j = 0; j < size; j++) {
                  List list3 = (List)list.get(j);
                  ints[j] = (list3 != null && list3.size() > i && list3.get(i) != null) ? ints[j] + 1 : 0;
               }

               ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

               for (int k = 0; k <= size; k++) {
                  int n3 = k == size ? 0 : ints[k];

                  while (!arrayDeque.isEmpty() && n3 < ints[arrayDeque.peek()]) {
                     int n4 = ints[arrayDeque.pop()];
                     int n5 = k - 1;
                     int n6 = arrayDeque.isEmpty() ? 0 : (Integer)arrayDeque.peek() + 1;
                     int n7 = n4 * (n5 - n6 + 1);
                     if (n7 > n2) {
                        n2 = n7;
                        uNoise3HitboxGridRect = new UNoise3HitboxGridRect(n6, n5, i - n4 + 1, i);
                     }
                  }

                  if (k < size) {
                     arrayDeque.push(k);
                  }
               }
            }

            if (uNoise3HitboxGridRect == null) {
               return null;
            } else {
               Vec2d vec2d = (Vec2d)((ArrayList)list.get(uNoise3HitboxGridRect.minCol)).get(uNoise3HitboxGridRect.minRow);
               Vec2d vec2d2 = (Vec2d)((ArrayList)list.get(uNoise3HitboxGridRect.maxCol)).get(uNoise3HitboxGridRect.maxRow);
               return new TuplePair(vec2d, vec2d2);
            }
         }
      } else {
         return null;
      }
   }

   private boolean hasAnySurfaceRestriction(ArrayList list) {

      for (int i = 0; i < list.size(); i++) {
         if (((UNoise3AimMode)list.get(i)).isSurfaceRestrictionActive()) {
            return true;
         }
      }

      return false;
   }

   public void recalculateModeStrengthLabels(boolean flag) {
      ArrayList list = new ArrayList();
      if (flag) {
         list.addAll(this.verticalAimModes);
         list.addAll(this.verticalPredictedAimModes);
      } else {
         list.addAll(this.horizontalAimModes);
         list.addAll(this.horizontalPredictedAimModes);
      }

      double d = 0.0;
      double d2 = 0.0;

      for (int i = 0; i < list.size(); i++) {
         UNoise3AimMode uNoise3AimMode = (UNoise3AimMode)list.get(i);
         d += uNoise3AimMode.getStrengthNoise().intervalRange.getMinValue(true);
         d2 += uNoise3AimMode.getStrengthNoise().intervalRange.getMaxValue(true);
      }

      for (int j = 0; j < list.size(); j++) {
         UNoise3AimMode uNoise3AimMode2 = (UNoise3AimMode)list.get(j);
         RangeSetting intervalRange = uNoise3AimMode2.getStrengthNoise().intervalRange;
         double d3 = d - intervalRange.getMinValue(true);
         double d4 = d2 - intervalRange.getMaxValue(true);
         double minValue = intervalRange.getMinValue(true);
         double d5 = minValue / (d4 + intervalRange.getMinValue(true));
         minValue = intervalRange.getMaxValue(true);
         double d6 = minValue / (d3 + intervalRange.getMaxValue(true));
         if (!Double.isFinite(d5)) {
            d5 = 0.0;
         }

         if (!Double.isFinite(d6)) {
            d6 = 0.0;
         }

         uNoise3AimMode2.updateStrengthSummaryLabel(d5, d6);
      }
   }

   private double[] findNearestUnobstructedAngle(EntityPlayerSP player2, EntityLivingBase entity, float f, float f2) {
      double collisionBorderSize = (double)entity.getCollisionBorderSize();
      Vec3 positionEyes = player2.getPositionEyes(1.0F);
      AxisAlignedBB box = entity.getEntityBoundingBox().expand(collisionBorderSize, collisionBorderSize, collisionBorderSize);
      double[] yawRange = HitboxAngleUtil.getYawRange(positionEyes, box);
      double[] pitchRange = HitboxAngleUtil.getPitchRange(positionEyes, box);
      double normalizedYawPosition = HitboxAngleUtil.getNormalizedYawPosition(yawRange[0], yawRange[1], (double)f);
      double d2 = HitboxAngleUtil.inverseLerpRange(pitchRange, (double)f2);
      double maxvalue = Double.MAX_VALUE;
      double d3 = normalizedYawPosition;
      double d4 = d2;
      double d5 = 0.02;
      float rotationYaw = player2.rotationYaw;
      float rotationPitch = player2.rotationPitch;
      Rotation rotation = new Rotation();

      for (int i = 0; i <= 50; i++) {
         double d6 = (double)i * 0.02;
         float f3 = (float)HitboxAngleUtil.lerpRange(yawRange, d6);

         for (int j = 0; j <= 50; j++) {
            double d7 = (double)j * 0.02;
            double d8 = d6 - normalizedYawPosition;
            double d9 = d7 - d2;
            double d10 = d8 * d8 + d9 * d9;
            if (d10 < maxvalue) {
               RotationUtil.applyMouseSensitivityFix(rotation, f3, (float)HitboxAngleUtil.lerpRange(pitchRange, d7), rotationYaw, rotationPitch);
               float pitch = rotation.pitch;
               if (this.canRaytraceHitTarget(entity, rotation.yaw, pitch)) {
                  maxvalue = d10;
                  d3 = d6;
                  d4 = d7;
               }
            }
         }
      }

      return new double[]{HitboxAngleUtil.lerpRange(yawRange, d3), HitboxAngleUtil.lerpRange(pitchRange, d4)};
   }

   public UNoise3PointSelector(UNoise3SettingContext uNoise3SettingContext2, UNoise3PredictionScheduler scheduler) {
       super();
      this.emptyBoundsPair = new double[2][];
      this.horizontalAimModes = new ArrayList();
      this.horizontalPredictedAimModes = new ArrayList();
      this.verticalAimModes = new ArrayList();
      this.verticalPredictedAimModes = new ArrayList();
      this.allAimModes = new ArrayList();
      this.firstTickOnTarget = false;
      uNoise3SettingContext2 = new UNoise3SettingContext(uNoise3SettingContext2.getModule(), uNoise3SettingContext2.getParentGroup(), this, null);
      this.uNoise3SettingContext = uNoise3SettingContext2;
      this.uNoise3PredictionScheduler = scheduler;
      this.uNoise3RandomForceTicker = new UNoise3RandomForceTicker();
      SettingGroup parentGroup = uNoise3SettingContext2.getParentGroup();
      SettingGroup uNoise3HorizontalGroup = new SettingGroup(uNoise3SettingContext2.getModule(), "UNoise3Horizontal", uNoise3SettingContext2.getParentGroup());
      SettingGroup settingGroup = (SettingGroup)uNoise3HorizontalGroup.withDisplayName("Horizontal");
      UNoise3SettingContext uNoise3SettingContext3 = uNoise3SettingContext2.withParentGroup(settingGroup);
      this.horizontalAimModes.add(new UNoise3BestVecAimMode(uNoise3SettingContext3, false));
      this.horizontalAimModes.add(new UNoise3MidAimMode(uNoise3SettingContext3, false));
      this.horizontalAimModes.add(new UNoise3NearestVecAimMode(uNoise3SettingContext3, false));
      this.horizontalAimModes.add(new UNoise3NearestToAimGoalMode(uNoise3SettingContext3, false));
      this.horizontalAimModes.add(new UNoise3PointAimMode(uNoise3SettingContext3, false));
      this.horizontalAimModes.add(new UNoise3NoiseAimMode(uNoise3SettingContext3, false));
      this.horizontalAimModes.add(new UNoise3EsoundAimMode(uNoise3SettingContext3, false));
      this.horizontalAimModes.add(new UNoise3CircleAimMode(uNoise3SettingContext3, false));
      this.horizontalAimModes.add(new UNoise3RandomPointAimMode(uNoise3SettingContext3, this.uNoise3RandomForceTicker, false));
      VarianceNoiseSetting varianceNoiseSetting = new VarianceNoiseSetting(uNoise3SettingContext2.getModule(), "UNoise3HorizontalOffset", settingGroup, false);
      double d = 20.0;
      double d2 = 0.0;
      this.uNoise3HorizontalOffset = varianceNoiseSetting.withStrength(0.0, d2, d, (byte)1);
      String str2 = "Horizontal offset";
      this.uNoise3HorizontalOffset.named(str2);
      this.uNoise3HorizontalOffset.addLayer(this.uNoise3HorizontalOffset.newLayerBuilder().build());
      SettingGroup settingGroup2 = (SettingGroup)new SettingGroup(uNoise3SettingContext3.getModule(), "UNoise3horizontalconstantboundingbox", settingGroup).withDisplayName("Constant bounding box size");
      this.uNoise3constanthorizontalbb = (BooleanValue)new BooleanValue(uNoise3SettingContext3.getModule(), "UNoise3constanthorizontalbb", false, settingGroup2).withDisplayName("Bounding box size");
      DoubleValue doubleSetting = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3constanthorizontalboundingboxsize", 20.0, 1.0, 50.0, 1, settingGroup2).named("Size");
      BooleanValue booleanValue2 = this.uNoise3constanthorizontalbb;
      this.uNoise3constanthorizontalboundingboxsize = doubleSetting.visibleWhen(() -> booleanValue2.getValue());
      this.uNoise3constantbbsizeoor = (BooleanValue)new BooleanValue(uNoise3SettingContext2.getModule(), "UNoise3constantbbsizeoor", true, settingGroup2).withDisplayName("Constant bounding box size if target is out of reach");
      DoubleValue uNoise3constantbbsizeoorv2 = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3constantbbsizeoorv", 20.0, 1.0, 50.0, 1, settingGroup2).named("Bounding box size if target is out of reach");
      BooleanValue booleanValue5 = this.uNoise3constantbbsizeoor;
      this.uNoise3constantbbsizeoorv = uNoise3constantbbsizeoorv2.visibleWhen(() -> booleanValue5.getValue());
      this.horizontalSilenceController = new RotationSilenceController(uNoise3SettingContext3, false);
      uNoise3SettingContext3 = uNoise3SettingContext2.withParentGroup(new SettingGroup(uNoise3SettingContext2.getModule(), "Horizontal relative to prediction", settingGroup));
      this.horizontalPredictedAimModes.add(new UNoise3BestVecAimMode(uNoise3SettingContext3, false));
      this.horizontalPredictedAimModes.add(new UNoise3MidAimMode(uNoise3SettingContext3, false));
      this.horizontalPredictedAimModes.add(new UNoise3NearestVecAimMode(uNoise3SettingContext3, false));
      this.horizontalPredictedAimModes.add(new UNoise3NearestToAimGoalMode(uNoise3SettingContext3, false));
      this.horizontalPredictedAimModes.add(new UNoise3PointAimMode(uNoise3SettingContext3, false));
      this.horizontalPredictedAimModes.add(new UNoise3NoiseAimMode(uNoise3SettingContext3, false));
      this.horizontalPredictedAimModes.add(new UNoise3CircleAimMode(uNoise3SettingContext3, false));
      this.horizontalPredictedAimModes.add(new UNoise3RandomPointAimMode(uNoise3SettingContext3, this.uNoise3RandomForceTicker, false));
      this.horizontalPredSilenceController = new RotationSilenceController(uNoise3SettingContext3, false);
      uNoise3HorizontalGroup = new SettingGroup(uNoise3SettingContext2.getModule(), "UNoise3Vertical", uNoise3SettingContext2.getParentGroup());
      SettingGroup settingGroup3 = (SettingGroup)uNoise3HorizontalGroup.withDisplayName("Vertical");
      uNoise3SettingContext3 = uNoise3SettingContext2.withParentGroup(settingGroup3);
      this.verticalAimModes.add(new UNoise3BestVecAimMode(uNoise3SettingContext3, true));
      this.verticalAimModes.add(new UNoise3MidAimMode(uNoise3SettingContext3, true));
      this.verticalAimModes.add(new UNoise3NearestVecAimMode(uNoise3SettingContext3, true));
      this.verticalAimModes.add(new UNoise3NearestToAimGoalMode(uNoise3SettingContext3, true));
      this.verticalAimModes.add(new UNoise3NoiseAimMode(uNoise3SettingContext3, true));
      UNoise3FutureAimMode uNoise3FutureAimMode;
      this.verticalAimModes.add(uNoise3FutureAimMode = new UNoise3FutureAimMode(uNoise3SettingContext3));
      UNoise3FutureIntervalAimMode mode;
      this.verticalAimModes.add(mode = new UNoise3FutureIntervalAimMode(uNoise3SettingContext3));
      UNoise3NoobAimMode uNoise3NoobAimMode;
      this.verticalAimModes.add(uNoise3NoobAimMode = new UNoise3NoobAimMode(uNoise3SettingContext3));
      this.verticalAimModes.add(new UNoise3EsoundAimMode(uNoise3SettingContext3, true));
      this.verticalAimModes.add(new UNoise3CircleAimMode(uNoise3SettingContext3, true));
      this.verticalAimModes.add(new UNoise3RandomPointAimMode(uNoise3SettingContext3, this.uNoise3RandomForceTicker, true));
      this.verticalAimModes.add(new UNoise3RangeBasedAimMode(uNoise3SettingContext3));
      this.futureDelaySetting = uNoise3FutureAimMode.getUNoise3FutureDelay();
      this.futureIntervalDelaySetting = mode.getUNoise3FutureIntervalDelay();
      this.noobDelaySetting = uNoise3NoobAimMode.getDelay();
      VarianceNoiseSetting varianceNoiseSetting3 = new VarianceNoiseSetting(uNoise3SettingContext2.getModule(), "UNoise3VerticalOffset", settingGroup3, false);
      double d4 = 20.0;
      double d5 = 0.0;
      this.uNoise3VerticalOffset = varianceNoiseSetting3.withStrength(0.0, d5, d4, (byte)1);
      String str9 = "Vertical offset";
      this.uNoise3VerticalOffset.named(str9);
      this.uNoise3VerticalOffset.addLayer(this.uNoise3VerticalOffset.newLayerBuilder().build());
      this.verticalSilenceController = new RotationSilenceController(uNoise3SettingContext3, false);
      uNoise3HorizontalGroup = new SettingGroup(uNoise3SettingContext3.getModule(), "UNoise3verticalconstantboundingbox", settingGroup3);
      SettingGroup settingGroup4 = (SettingGroup)uNoise3HorizontalGroup.withDisplayName("Constant bounding box size");
      this.uNoise3constantverticalbb = (BooleanValue)new BooleanValue(uNoise3SettingContext3.getModule(), "UNoise3constantverticalbb", false, settingGroup4).withDisplayName("Bounding box size");
      DoubleValue doubleSetting2 = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3constantverticalboundingboxsize", 20.0, 1.0, 50.0, 1, settingGroup4).named("Size");
      BooleanValue booleanValue8 = this.uNoise3constantverticalbb;
      this.uNoise3constantverticalboundingboxsize = doubleSetting2.visibleWhen(() -> booleanValue8.getValue());
      this.uNoise3constantvbbsizeoor = (BooleanValue)new BooleanValue(uNoise3SettingContext2.getModule(), "UNoise3constantvbbsizeoor", true, settingGroup4).withDisplayName("Constant bounding box size if target is out of reach");
      DoubleValue uNoise3constantvbbsizeoorv2 = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3constantvbbsizeoorv", 20.0, 1.0, 50.0, 1, settingGroup4).named("Bounding box size if target is out of reach");
      BooleanValue booleanValue11 = this.uNoise3constantvbbsizeoor;
      this.uNoise3constantvbbsizeoorv = uNoise3constantvbbsizeoorv2.visibleWhen(() -> booleanValue11.getValue());
      uNoise3SettingContext3 = uNoise3SettingContext2.withParentGroup(new SettingGroup(uNoise3SettingContext2.getModule(), "Vertical relative to prediction", settingGroup3));
      this.verticalPredictedAimModes.add(new UNoise3BestVecAimMode(uNoise3SettingContext3, true));
      this.verticalPredictedAimModes.add(new UNoise3MidAimMode(uNoise3SettingContext3, true));
      this.verticalPredictedAimModes.add(new UNoise3NearestVecAimMode(uNoise3SettingContext3, true));
      this.verticalPredictedAimModes.add(new UNoise3NearestToAimGoalMode(uNoise3SettingContext3, true));
      this.verticalPredictedAimModes.add(new UNoise3NoiseAimMode(uNoise3SettingContext3, true));
      this.verticalPredictedAimModes.add(new UNoise3CircleAimMode(uNoise3SettingContext3, true));
      this.verticalPredictedAimModes.add(new UNoise3RandomPointAimMode(uNoise3SettingContext3, this.uNoise3RandomForceTicker, true));
      this.verticalPredictedAimModes.add(new UNoise3RangeBasedAimMode(uNoise3SettingContext3));
      this.verticalPredSilenceController = new RotationSilenceController(uNoise3SettingContext3, false);
      this.uNoise3horizontalfastthreshold = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3horizontalfastthreshold", 2.0, 0.0, 360.0, 1, uNoise3SettingContext2.getParentGroup()).named("Horizontal fast threshold");
      this.uNoise3verticalfastthreshold = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3verticalfastthreshold", 3.0, 0.0, 180.0, 1, uNoise3SettingContext2.getParentGroup()).named("Vertical fast threshold");
      uNoise3HorizontalGroup = new SettingGroup(uNoise3SettingContext2.getModule(), "UNoise3RandomforceTicker", parentGroup);
      SettingGroup settingGroup5 = (SettingGroup)uNoise3HorizontalGroup.withDisplayName("Randompoint Global Ticker");
      this.uNoise3RandomForceTicker.initSettings(uNoise3SettingContext2.withParentGroup(settingGroup5), settingGroup5, () -> true);
      this.globalSilenceController = new RotationSilenceController(uNoise3SettingContext2, true);

      for (UNoise3AimMode uNoise3AimMode : (Iterable<UNoise3AimMode>)(this.verticalAimModes)) {
         uNoise3AimMode.setVertical(true);
      }

      for (UNoise3AimMode uNoise3AimMode2 : (Iterable<UNoise3AimMode>)(this.verticalPredictedAimModes)) {
         uNoise3AimMode2.setVertical(true);
      }

      this.allAimModes.addAll(this.horizontalAimModes);
      this.allAimModes.addAll(this.horizontalPredictedAimModes);
      this.allAimModes.addAll(this.verticalAimModes);
      this.allAimModes.addAll(this.verticalPredictedAimModes);
      this.noobPitchCache = new TickDelayedPointCache(() -> this.sampleNoobDelay());
      this.futurePitchCache = new TickDelayedPointCache(() -> this.sampleFutureDelay());
      this.futurePitchBoundsCache = new TickDelayedPointCache(() -> this.sampleFutureIntervalDelay());
   }

   public Vec2d selectTargetAngles(
      UNoise3PredictionScheduler scheduler,
      EntityPlayerSP player2,
      EntityLivingBase entity,
      Vec3 vec,
      Vec3 vec2,
      Vec3 vec3,
      Vec3 vec4,
      double d,
      double d2,
      double d3,
      double d4,
      double d5,
      double d6,
      double d7
   ) {
      Vec3 vec5;
      Vec3 vec6;
      Vec3 vec7;
      AxisAlignedBB box;
      boolean flag;
      double d8;
      boolean flag2;
      boolean flag3;
      UNoise3AimContext uNoise3AimContext;
      boolean flag4;
      this.updateFuturePitchCaches(player2, entity, this.lastOutputPitch, d5);
         double cachedValue = (Double)this.futurePitchCache.getCachedValue();
         double[] cachedValue2 = (double[])this.futurePitchBoundsCache.getCachedValue();
         double cachedValue3 = (Double)this.noobPitchCache.getCachedValue();
         boolean flag5 = Math.abs(d6) > this.uNoise3horizontalfastthreshold.getValue();
         boolean flag6 = Math.abs(d7) > this.uNoise3verticalfastthreshold.getValue();
         flag = flag5 || flag6;
         double d9 = 0.0;
         d8 = this.globalSilenceController.computeSilenceFactor(d9);
         flag2 = this.globalSilenceController.shouldKeepTicking(false);
         flag3 = this.globalSilenceController.shouldSilenceOffTarget(false);
         this.uNoise3RandomForceTicker.tick();
         uNoise3AimContext = new UNoise3AimContext();
         uNoise3AimContext.player = player2;
         uNoise3AimContext.target = entity;
         uNoise3AimContext.playerEyePos = vec5 = player2.getPositionEyes(1.0F);
         uNoise3AimContext.targetPos = vec6 = entity.getInterpolatedPos(1.0F);
         uNoise3AimContext.closestTargetPoint = vec7 = RotationUtil.clampPointToBox(uNoise3AimContext.playerEyePos, box = EntityBoundsUtil.getEntityBoundingBoxAt(entity, vec6, true));
         uNoise3AimContext.targetBoundingBox = box;
         uNoise3AimContext.expandedAngleBounds = HitboxAngleUtil.getYawRange(vec5, box);
         uNoise3AimContext.exactAngleBounds = HitboxAngleUtil.getYawRange(vec5, EntityBoundsUtil.getEntityBoundingBoxAt(entity, vec6, false));
         uNoise3AimContext.delayedFuturePitchBounds = cachedValue2;
         uNoise3AimContext.delayedFuturePitch = cachedValue;
         uNoise3AimContext.delayedNoobPitch = cachedValue3;
         uNoise3AimContext.currentAngle = d;
         uNoise3AimContext.lastGoalAngle = this.lastOutputYaw;
         uNoise3AimContext.silencedGoalAngle = this.unsilencedYaw;
         uNoise3AimContext.vertical = false;
         uNoise3AimContext.firstTickOnTarget = this.firstTickOnTarget;
         this.firstTickOnTarget = false;
if (!this.hasAnySurfaceRestriction(this.horizontalAimModes)) {
if (!this.hasAnySurfaceRestriction(this.verticalAimModes)) {
flag4 = false;
} else {
flag4 = true;
}
} else {
flag4 = true;
}

      boolean flag11;
      flag11 = flag4;
if (!this.hasAnySurfaceRestriction(this.horizontalPredictedAimModes)) {
if (!this.hasAnySurfaceRestriction(this.verticalPredictedAimModes)) {
flag4 = false;
} else {
flag4 = true;
}
} else {
flag4 = true;
}

      boolean flag12 = flag4;
      Vec3 vec8 = vec3.addVector(0.0, (double)Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0.0);
      ArrayList[] lists = null;
      ArrayList[] lists2 = null;
      if (flag11) {
         lists = this.sampleHittableSurfaceGrid(vec5, entity, box, 3.0);
      }

      if (flag12) {
         lists2 = this.sampleHittableSurfaceGrid(vec8, entity, EntityBoundsUtil.getEntityBoundingBoxAt(entity, vec4, true), 3.0);
      }

      double[][] doublesArr;
      if (flag11) {
         doublesArr = this.extractBoundsFromSurfaceGrid(vec5, lists[0], box, false);
      } else {
         doublesArr = this.emptyBoundsPair;
      }

      double[][] doublesArr2 = doublesArr;
      double[][] doublesArr3;
      if (flag11) {
         doublesArr3 = this.extractBoundsFromSurfaceGrid(vec5, lists[1], box, true);
      } else {
         doublesArr3 = this.emptyBoundsPair;
      }

      double[][] doublesArr4 = doublesArr3;
      double[][] doublesArr5;
      if (flag12) {
         doublesArr5 = this.extractBoundsFromSurfaceGrid(vec5, lists2[0], box, false);
      } else {
         doublesArr5 = this.emptyBoundsPair;
      }

      double[][] doublesArr6 = doublesArr5;
      double[][] doublesArr7;
      if (flag12) {
         doublesArr7 = this.extractBoundsFromSurfaceGrid(vec5, lists2[1], box, true);
      } else {
         doublesArr7 = this.emptyBoundsPair;
      }

      double[][] doublesArr8 = doublesArr7;
      if (doublesArr2 == null) {
         doublesArr2 = doublesArr4;
      }

      if (doublesArr6 == null) {
         doublesArr6 = doublesArr8;
      }

      uNoise3AimContext.hittableAngleBounds = doublesArr2[0];
      uNoise3AimContext.unoccludedAngleBounds = doublesArr4[0];
      double naN = Double.NaN;
      if (d5 > 3.1 && this.uNoise3constantbbsizeoor.getValue()) {
         naN = this.uNoise3constantbbsizeoorv.getValue(true) / 2.0;
      } else if (this.uNoise3constanthorizontalbb.getValue()) {
         naN = this.uNoise3constanthorizontalboundingboxsize.getValue(true) / 2.0;
      }

      if (Double.isFinite(naN)) {
         double d12 = 0.5;
         double d13 = HitboxAngleUtil.lerpRange(uNoise3AimContext.expandedAngleBounds, d12);
         uNoise3AimContext.expandedAngleBounds = uNoise3AimContext.exactAngleBounds = HitboxAngleUtil.normalizeYawInterval(MathHelper.wrapAngleTo180_double(d13 - naN), MathHelper.wrapAngleTo180_double(d13 + naN));
      }

      double d16;
      d16 = this.horizontalSilenceController.computeSilenceFactor(d8);
if (!flag) {
if (!(!(d16 > 0.0) || this.horizontalSilenceController.shouldSilenceOffTarget(flag3))) {
double lastGoalAngle = uNoise3AimContext.lastGoalAngle;
if (isAngleWithinBounds(uNoise3AimContext.expandedAngleBounds, lastGoalAngle, false)) {
double silencedGoalAngle = uNoise3AimContext.silencedGoalAngle;
if (!isAngleWithinBounds(uNoise3AimContext.expandedAngleBounds, silencedGoalAngle, false)) {
d16 = 0.0;
}
} else {
d16 = 0.0;
}
}
} else {
d16 = 0.0;
}

      ArrayList horizontalAimModes3 = this.horizontalAimModes;
      ArrayList list12 = this.evaluateAimModeGroup(uNoise3AimContext, horizontalAimModes3, d16, false, this.horizontalSilenceController.shouldKeepTicking(flag2));
      uNoise3AimContext.playerEyePos = vec.addVector(0.0, (double)player2.getEyeHeight(), 0.0);
      uNoise3AimContext.targetPos = vec2;
      Vec3 playerEyePos2 = uNoise3AimContext.playerEyePos;
      uNoise3AimContext.closestTargetPoint = RotationUtil.clampPointToBox(playerEyePos2, uNoise3AimContext.targetBoundingBox = EntityBoundsUtil.getEntityBoundingBoxAt(entity, vec2, true));
      AxisAlignedBB targetBoundingBox = uNoise3AimContext.targetBoundingBox;
      uNoise3AimContext.expandedAngleBounds = HitboxAngleUtil.getYawRange(uNoise3AimContext.playerEyePos, targetBoundingBox);
      playerEyePos2 = uNoise3AimContext.playerEyePos;
      uNoise3AimContext.exactAngleBounds = HitboxAngleUtil.getYawRange(playerEyePos2, EntityBoundsUtil.getEntityBoundingBoxAt(entity, vec2, false));
      uNoise3AimContext.silencedGoalAngle = this.unsilencedPredYaw;
      uNoise3AimContext.hittableAngleBounds = doublesArr6[0];
      uNoise3AimContext.unoccludedAngleBounds = doublesArr8[0];
      if (Double.isFinite(naN)) {
         double d17 = 0.5;
         double d18 = HitboxAngleUtil.lerpRange(uNoise3AimContext.expandedAngleBounds, d17);
         uNoise3AimContext.expandedAngleBounds = uNoise3AimContext.exactAngleBounds = HitboxAngleUtil.normalizeYawInterval(MathHelper.wrapAngleTo180_double(d18 - naN), MathHelper.wrapAngleTo180_double(d18 + naN));
      }

      double d21;
      d21 = this.horizontalPredSilenceController.computeSilenceFactor(d8);
if (!flag) {
if (!(this.horizontalPredSilenceController.shouldSilenceOffTarget(flag3) || !(d21 > 0.0))) {
double lastGoalAngle2 = uNoise3AimContext.lastGoalAngle;
if (isAngleWithinBounds(uNoise3AimContext.expandedAngleBounds, lastGoalAngle2, false)) {
double silencedGoalAngle2 = uNoise3AimContext.silencedGoalAngle;
if (!isAngleWithinBounds(uNoise3AimContext.expandedAngleBounds, silencedGoalAngle2, false)) {
d21 = 0.0;
}
} else {
d21 = 0.0;
}
}
} else {
d21 = 0.0;
}

      horizontalAimModes3 = this.horizontalPredictedAimModes;
      ArrayList list14 = this.evaluateAimModeGroup(uNoise3AimContext, horizontalAimModes3, d21, false, this.horizontalPredSilenceController.shouldKeepTicking(flag2));
      uNoise3AimContext.playerEyePos = vec5;
      uNoise3AimContext.targetPos = vec6;
      uNoise3AimContext.closestTargetPoint = vec7;
      playerEyePos2 = uNoise3AimContext.playerEyePos;
      uNoise3AimContext.expandedAngleBounds = HitboxAngleUtil.getPitchRange(playerEyePos2, uNoise3AimContext.targetBoundingBox = box);
      playerEyePos2 = uNoise3AimContext.playerEyePos;
      uNoise3AimContext.exactAngleBounds = HitboxAngleUtil.getPitchRange(playerEyePos2, EntityBoundsUtil.getEntityBoundingBoxAt(entity, vec6, false));
      uNoise3AimContext.currentAngle = d2;
      uNoise3AimContext.lastGoalAngle = this.lastOutputPitch;
      uNoise3AimContext.silencedGoalAngle = this.unsilencedPitch;
      uNoise3AimContext.vertical = true;
      uNoise3AimContext.hittableAngleBounds = doublesArr2[1];
      uNoise3AimContext.unoccludedAngleBounds = doublesArr4[1];
      double naN2 = Double.NaN;
      if (d5 > 3.1 && this.uNoise3constantvbbsizeoor.getValue()) {
         naN2 = this.uNoise3constantvbbsizeoorv.getValue(true) / 2.0;
      } else if (this.uNoise3constantverticalbb.getValue()) {
         naN2 = this.uNoise3constantverticalboundingboxsize.getValue(true) / 2.0;
      }

      if (Double.isFinite(naN2)) {
         double d22 = 0.5;
         double d23 = HitboxAngleUtil.lerpRange(uNoise3AimContext.expandedAngleBounds, d22);
         uNoise3AimContext.expandedAngleBounds = uNoise3AimContext.exactAngleBounds = new double[]{d23 - naN2, d23 + naN2};
      }

      double d24;
      d24 = this.verticalSilenceController.computeSilenceFactor(d8);
if (!flag) {
if (!(!(d24 > 0.0) || this.verticalSilenceController.shouldSilenceOffTarget(flag3))) {
double lastGoalAngle3 = uNoise3AimContext.lastGoalAngle;
if (isAngleWithinBounds(uNoise3AimContext.expandedAngleBounds, lastGoalAngle3, true)) {
double silencedGoalAngle3 = uNoise3AimContext.silencedGoalAngle;
if (!isAngleWithinBounds(uNoise3AimContext.expandedAngleBounds, silencedGoalAngle3, true)) {
d24 = 0.0;
}
} else {
d24 = 0.0;
}
}
} else {
d24 = 0.0;
}

      horizontalAimModes3 = this.verticalAimModes;
      ArrayList list16 = this.evaluateAimModeGroup(uNoise3AimContext, horizontalAimModes3, d24, true, this.verticalSilenceController.shouldKeepTicking(flag2));
      uNoise3AimContext.playerEyePos = vec3.addVector(0.0, (double)player2.getEyeHeight(), 0.0);
      uNoise3AimContext.targetPos = vec4;
      playerEyePos2 = uNoise3AimContext.playerEyePos;
      uNoise3AimContext.closestTargetPoint = RotationUtil.clampPointToBox(playerEyePos2, uNoise3AimContext.targetBoundingBox = EntityBoundsUtil.getEntityBoundingBoxAt(entity, vec4, true));
      AxisAlignedBB targetBoundingBox2 = uNoise3AimContext.targetBoundingBox;
      uNoise3AimContext.expandedAngleBounds = HitboxAngleUtil.getPitchRange(uNoise3AimContext.playerEyePos, targetBoundingBox2);
      uNoise3AimContext.exactAngleBounds = HitboxAngleUtil.getPitchRange(vec5, EntityBoundsUtil.getEntityBoundingBoxAt(entity, vec4, false));
      uNoise3AimContext.silencedGoalAngle = this.unsilencedPredPitch;
      uNoise3AimContext.hittableAngleBounds = doublesArr6[1];
      uNoise3AimContext.unoccludedAngleBounds = doublesArr8[1];
      if (Double.isFinite(naN2)) {
         double d25 = 0.5;
         double d26 = HitboxAngleUtil.lerpRange(uNoise3AimContext.expandedAngleBounds, d25);
         uNoise3AimContext.expandedAngleBounds = uNoise3AimContext.exactAngleBounds = new double[]{d26 - naN2, d26 + naN2};
      }

      double d27;
      d27 = this.verticalPredSilenceController.computeSilenceFactor(d8);
if (!flag) {
if (!(!(d27 > 0.0) || this.verticalPredSilenceController.shouldSilenceOffTarget(flag3))) {
double lastGoalAngle4 = uNoise3AimContext.lastGoalAngle;
if (isAngleWithinBounds(uNoise3AimContext.expandedAngleBounds, lastGoalAngle4, true)) {
double silencedGoalAngle4 = uNoise3AimContext.silencedGoalAngle;
if (!isAngleWithinBounds(uNoise3AimContext.expandedAngleBounds, silencedGoalAngle4, true)) {
d27 = 0.0;
}
} else {
d27 = 0.0;
}
}
} else {
d27 = 0.0;
}

      horizontalAimModes3 = this.verticalPredictedAimModes;
      ArrayList list18 = this.evaluateAimModeGroup(uNoise3AimContext, horizontalAimModes3, d27, true, this.verticalPredSilenceController.shouldKeepTicking(flag2));
      double d28 = MathHelper.wrapAngleTo180_double(computeWeightedAngleAverage(list12, list14, true));
      double d29 = computeWeightedAngleAverage(list16, list18, false);
      if (list12.isEmpty() && list14.isEmpty()) {
         d28 = MathHelper.wrapAngleTo180_double(d3);
      }

      if (list16.isEmpty() && list18.isEmpty()) {
         d29 = d4;
      }

      if (ClientManagers.moduleManager.killAura.smartAim.getValue()) {
         double[] doubles = this.avoidBlockObstruction(player2, entity, (float)d28, (float)d29);
         d28 = doubles[0];
         d29 = doubles[1];
      }

      if (!Double.isFinite(d28)) {
         d28 = d;
      }

      if (!Double.isFinite(d29)) {
         d29 = d2;
      }

      this.uNoise3HorizontalOffset.stepLayers();
      this.uNoise3VerticalOffset.stepLayers();
      double d30 = MathHelper.wrapAngleTo180_double(d28 + this.uNoise3HorizontalOffset.sample());
      double d31 = MathHelper.clamp_double(d29 + this.uNoise3VerticalOffset.sample(), -90.0, 90.0);
      this.lastOutputYaw = d30;
      this.lastOutputPitch = d31;
      if (d16 == 0.0) {
         this.unsilencedYaw = this.lastOutputYaw;
      }

      if (d21 == 0.0) {
         this.unsilencedPredYaw = this.lastOutputYaw;
      }

      if (d24 == 0.0) {
         this.unsilencedPitch = this.lastOutputPitch;
      }

      if (d27 == 0.0) {
         this.unsilencedPredPitch = this.lastOutputPitch;
      }

      return new Vec2d(d30, d31);
   }

   private boolean canRaytraceHitTarget(EntityLivingBase entity, float f, float f2) {
      MovingObjectPosition hit = RayTraceUtil.rayTraceDefaultReach(1.0F, f, f2);
      return hit != null && hit.entityHit == entity;
   }

   private double sampleNoobDelay() {
      return this.noobDelaySetting.nextValue(true);
   }

   private double[][] extractBoundsFromSurfaceGrid(Vec3 vec, ArrayList list, AxisAlignedBB box, boolean flag) {
      TuplePair tuplePair = findLargestHittableRectangle(list);
      double[] yawRange = HitboxAngleUtil.getYawRange(vec, box);
      double[] pitchRange = HitboxAngleUtil.getPitchRange(vec, box);
      if (tuplePair == null) {
         return flag ? new double[][]{yawRange, pitchRange} : (double[][])null;
      } else {
         Vec2d first = (Vec2d)tuplePair.getFirst();
         Vec2d second = (Vec2d)tuplePair.getSecond();
         double[][] doublesArr = new double[2][];
         doublesArr[0] = HitboxAngleUtil.normalizeYawInterval(MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(yawRange, first.x)), MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(yawRange, second.x)));
         double[] doubles = new double[2];
         doubles[0] = HitboxAngleUtil.lerpRange(pitchRange, first.y);
         doubles[1] = HitboxAngleUtil.lerpRange(pitchRange, second.y);
         doublesArr[1] = HitboxAngleUtil.findMinMaxWithIndices(doubles);
         return doublesArr;
      }
   }

   private double[] avoidBlockObstruction(EntityPlayerSP player2, EntityLivingBase entity, float f, float f2) {
      MovingObjectPosition hit = RayTraceUtil.rayTrace(1.0F, f, f2, 3.0, 3.0, 0.0, true);
      if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && hit.entityHit == entity) {
         MovingObjectPosition hit2 = RayTraceUtil.rayTraceDefaultReach(1.0F, f, f2);
         if (hit2 != null && hit2.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            return this.findNearestUnobstructedAngle(player2, entity, f, f2);
         }
      }

      return new double[]{(double)f, (double)f2};
   }

   public static double computeWeightedAngleAverage(ArrayList list, boolean flag) {
      return computeWeightedAngleAverage(list, new ArrayList(), flag);
   }

   private double sampleFutureDelay() {
      return this.futureDelaySetting.nextValue(true);
   }

   private ArrayList evaluateAimModeGroup(UNoise3AimContext uNoise3AimContext, ArrayList list, double d, boolean flag, boolean flag2) {
      ArrayList list2 = new ArrayList();

      for (UNoise3AimMode uNoise3AimMode : (Iterable<UNoise3AimMode>)(list)) {
         double d2 = uNoise3AimMode.sampleWeight(uNoise3AimContext);
         if (d2 != 0.0) {
            if (d == 0.0 || flag2) {
               uNoise3AimMode.stepNoise();
            }

            double d3 = flag ? uNoise3AimMode.computeTargetAngle(uNoise3AimContext) : MathHelper.wrapAngleTo180_double(uNoise3AimMode.computeTargetAngle(uNoise3AimContext));
            if (d > 0.0) {
               list2.add(new Vec2d(d3, d2 * (1.0 - d)));
               list2.add(new Vec2d(uNoise3AimContext.silencedGoalAngle, d2 * d));
            } else {
               list2.add(new Vec2d(d3, d2));
            }
         }
      }

      return list2;
   }

}
