package net.augustus.utils.rotation;

import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.math.SimplexNoise;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.simulation.EntityMotionSnapshot;
import net.augustus.utils.simulation.EntityMovementPredictor;
import net.augustus.utils.simulation.PlayerTickSnapshot;
import net.augustus.utils.simulation.PositionHistoryProvider;
import java.util.ArrayList;
import java.util.Collections;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class TargetTrajectoryPredictor implements MC {
   public float inaccuracyNoiseStep;
   public float inaccuracyNoisePhase = 0.0F;
   public PositionHistoryProvider positionHistoryProvider;

   public AnglePredictionSample clampAndScalePrediction(AnglePredictionSample[] anglePredictionSamples, double d, double d2) {
      double[] doubles = HitboxAngleUtil.normalizeYawInterval(anglePredictionSamples[0].predictedAngle, anglePredictionSamples[1].predictedAngle);
      double d3 = MathHelper.clamp_double(HitboxAngleUtil.getNormalizedYawPosition(doubles[0], doubles[1], d), 0.0, 1.0);
      if (Double.isNaN(d3)) {
         d3 = 0.0;
      }

      double d4 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles, d3));
      AnglePredictionSample anglePredictionSample = anglePredictionSamples[d3 < 0.5 ? 0 : 1];
      double d5 = (EntityBoundsUtil.getShortestSignedAngleDelta(anglePredictionSample.baseAngle, d4) - anglePredictionSample.angularIntercept) / (double)anglePredictionSample.predictionTicks;
      d5 *= d2;
      d4 = MathHelper.wrapAngleTo180_double(anglePredictionSample.baseAngle + d5 * (double)anglePredictionSample.predictionTicks + anglePredictionSample.angularIntercept);
      return new AnglePredictionSample(d5, anglePredictionSample.angularIntercept, d4, anglePredictionSample.baseAngle, anglePredictionSample.predictionTicks);
   }

   public AnglePredictionSample[] predictYawBounds(
      EntityPlayerSP player, EntityLivingBase entity, int n, int n2, int n3, double d, double d2, double d3
   ) {
      AnglePredictionSample[] anglePredictionSamples = AngleTrendRegressor.predictAngleBounds(this, player, entity, n, n2, n3, d3, (byte)0);
      AnglePredictionSample[] anglePredictionSamples2 = AngleTrendRegressor.predictAngleBounds(this, player, entity, n, n2, n3, d3, (byte)1);
      if (anglePredictionSamples != null && anglePredictionSamples2 != null) {
         double[] expandedYawRange = HitboxAngleUtil.getExpandedYawRange(this.predictPlayerPosition(player, n, n2), EntityBoundsUtil.getEntityBoundingBoxAt(entity, this.predictTargetPosition(entity, n, n2), true), d3, 0.0 / 0.0);
         expandedYawRange[0] = MathHelper.wrapAngleTo180_double(expandedYawRange[0]);
         expandedYawRange[1] = MathHelper.wrapAngleTo180_double(expandedYawRange[1]);
         this.inaccuracyNoisePhase = this.inaccuracyNoisePhase + this.inaccuracyNoiseStep;
         if (this.inaccuracyNoisePhase > 1000.0F) {
            this.inaccuracyNoisePhase -= 1000.0F;
         }

         double d5 = 1.0 + MathStatisticsUtil.lerp(d, d2, (double)Math.abs(SimplexNoise.noise(this.inaccuracyNoisePhase, 0.0F)));
         AnglePredictionSample anglePredictionSample = this.clampAndScalePrediction(anglePredictionSamples, expandedYawRange[0], d5);
         AnglePredictionSample anglePredictionSample2 = this.clampAndScalePrediction(anglePredictionSamples2, expandedYawRange[1], d5);
         return new AnglePredictionSample[]{anglePredictionSample, anglePredictionSample2};
      } else {
         return null;
      }
   }

   public Vec3 predictTargetPosition(EntityLivingBase entity, int n, int n2) {
      if (this.positionHistoryProvider != null) {
         return this.positionHistoryProvider.getTargetPositionAtTick(n, n2);
      } else if (entity.positionHistory.size() != 0 && entity.motionSnapshotHistory.size() != 0) {
         if (mc.thePlayer.ticksExisted - n2 > entity.positionHistory.size() - 1) {
            return (Vec3)entity.positionHistory.get((byte)0);
         } else if (n < n2 && n >= 0) {
            int n3 = mc.thePlayer.ticksExisted - n;
            int n4 = n2 - n;
            if (n3 > entity.motionSnapshotHistory.getEntries().size() - 1) {
               return EntityMovementPredictor.predictPositionFromSnapshot(entity, (EntityMotionSnapshot)entity.motionSnapshotHistory.get((byte)0), n4);
            } else {
               return EntityMovementPredictor.predictPositionFromSnapshot(entity, (EntityMotionSnapshot)entity.motionSnapshotHistory.getFromEnd(n3), n4);
            }
         } else {
            int n5 = mc.thePlayer.ticksExisted - n2;
            return (Vec3)entity.positionHistory.getFromEnd(n5);
         }
      } else {
         return entity.getInterpolatedPos(1.0F);
      }
   }

   public Vec3 predictPlayerPosition(EntityPlayerSP player, int n, int n2) {
      return this.predictPlayerPositionAtTick(player, n, player.ticksExisted, n2);
   }

   public Vec3 predictPlayerPositionAtTick(EntityPlayerSP player, int n, int n2, int n3) {
      if (this.positionHistoryProvider != null) {
         return this.positionHistoryProvider.getPlayerPositionAtTick(n, n2, n3);
      } else if (player.tickSnapshotHistory.size() == 0) {
         return player.getInterpolatedPos(1.0F);
      } else if (player.ticksExisted - n3 > player.tickSnapshotHistory.getEntries().size() - 1) {
         return ((PlayerTickSnapshot)player.tickSnapshotHistory.get((byte)0)).position;
      } else if (n >= n3) {
         int n4 = player.ticksExisted - n3;
         return ((PlayerTickSnapshot)player.tickSnapshotHistory.getFromEnd(n4)).position;
      } else {
         int n5 = player.ticksExisted - n;
         int n6 = n3 - n;
         if (player.tickSnapshotHistory.size() <= n5) {
            return player.getInterpolatedPos(1.0F);
         } else {
            ArrayList list = new ArrayList();

            for (int i = 0; i <= n2 - n; i++) {
               list.add(player.tickSnapshotHistory.getFromEnd(i));
            }

            Collections.reverse(list);
            double[] doubles = PlayerUtil.simulatePlayerSnapshotsFinal(player, list, n6 - 1, false);
            return new Vec3(doubles[0], doubles[1], doubles[2]);
         }
      }
   }

   public TargetTrajectoryPredictor() {
      this.inaccuracyNoiseStep = 0.0F;
   }

   public Vec3 getPredictedEntityPosition(EntityLivingBase entity, int n, int n2) {
      if (entity == mc.thePlayer) {
         return this.predictPlayerPosition(mc.thePlayer, n, n2);
      } else {
         return this.predictTargetPosition(entity, n, n2);
      }
   }
}
