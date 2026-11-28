package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.utils.BoundedHistoryList;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.rotation.EntityBoundsUtil;
import net.augustus.utils.rotation.HitboxAngleUtil;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.utils.simulation.PlayerTickSnapshot;
import net.augustus.utils.simulation.TrajectoryPredictionUtil;
import java.util.ArrayList;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class UNoise3MovementFilter implements MC {
   private DoubleValue uNoise3FilterTimeOffset;
   public int targetTickOffset;
   private DoubleValue uNoise3MinInaccuracyForFilterToApplyMoment;
   private DoubleValue uNoise3TargetMovementConstantOffset;
   public DoubleSampleWindow inaccuracyWindow;
   public EntityPlayerSP player;
   private DoubleValue uNoise3PlayerMovementConstantOffset;
   public UNoise3SettingContext uNoise3SettingContext;
   public int playerTickOffset;
   private DoubleValue uNoise3MinInaccuracyForFilterToApply;

   private static double computeMidpointAngleDiff(double[] doubles, double[] doubles2) {
      return HitboxAngleUtil.getShortestAngularDistance(MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles, 0.5)), MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(doubles2, 0.5)));
   }

   public UNoise3MovementFilter(UNoise3SettingContext uNoise3SettingContext2) {
       super();
      this.inaccuracyWindow = new DoubleSampleWindow(10);
      this.uNoise3SettingContext = uNoise3SettingContext2;
      this.uNoise3MinInaccuracyForFilterToApply = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3MinInaccuracyForFilterToApply", 20.0, 10.0, 30.0, 1, uNoise3SettingContext2.getParentGroup()).named("Min inaccuracy for filter to apply");
      this.uNoise3MinInaccuracyForFilterToApplyMoment = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3MinInaccuracyForFilterToApplyMoment", 20.0, 10.0, 30.0, 1, uNoise3SettingContext2.getParentGroup()).named("Min inaccuracy for filter to apply in the moment");
      this.uNoise3FilterTimeOffset = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3FilterTimeOffset", 1.0, -2.0, 2.0, 0, uNoise3SettingContext2.getParentGroup()).named("Filter time offset");
      this.uNoise3TargetMovementConstantOffset = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3TargetMovementConstantOffset", 0.0, -3.0, 3.0, 0, uNoise3SettingContext2.getParentGroup()).named("Target movement constant");
      this.uNoise3PlayerMovementConstantOffset = new DoubleValue(uNoise3SettingContext2.getModule(), "UNoise3PlayerMovementConstantOffset", 0.0, -3.0, 3.0, 0, uNoise3SettingContext2.getParentGroup()).named("Player movement constant");
   }

   public UNoise3PredictionEntry[] predictPitchEntries(EntityPlayerSP player2, EntityLivingBase entity, int n, int n2, int n3, int n4) {
      ArrayList list = this.predictEntityPositions(entity, n, n4 + this.targetTickOffset);
      ArrayList list2 = TrajectoryPredictionUtil.predictPlayerPositions(player2, n, n2, n3, n4 + this.playerTickOffset);
      boolean flag = false;
      double d = 1.0;
      Vec3 clampedPosition = TrajectoryPredictionUtil.getClampedPosition(list, (byte)0);
      Vec3 clampedPosition2 = TrajectoryPredictionUtil.getClampedPosition(list2, (byte)0);
      int n7 = 0;
      double d2 = 1.0 / 0.0;

      for (int i = 0; i < Math.max(list.size(), list2.size()); n7 = i++) {
         Vec3 clampedPosition3 = TrajectoryPredictionUtil.getClampedPosition(list, i);
         Vec3 clampedPosition4 = TrajectoryPredictionUtil.getClampedPosition(list2, i);
         if (clampedPosition3.subtract(clampedPosition4).length() < d) {
            flag = true;
            break;
         }

         d2 = Math.min(d2, clampedPosition3.subtract(clampedPosition4).length());
         clampedPosition = clampedPosition3;
         clampedPosition2 = clampedPosition4;
      }

      boolean flag2 = clampedPosition.subtract(clampedPosition2).length() < d;
      Vec3 clampedPosition5 = TrajectoryPredictionUtil.getClampedPosition(list, n7 - 1);
      Vec3 clampedPosition6 = TrajectoryPredictionUtil.getClampedPosition(list2, n7 - 1);
      double[] pitchRange = HitboxAngleUtil.getPitchRange(clampedPosition2, EntityBoundsUtil.getEntityBoundingBoxAt(entity, clampedPosition, false));
      double[] pitchRange2 = HitboxAngleUtil.getPitchRange(clampedPosition6, EntityBoundsUtil.getEntityBoundingBoxAt(entity, clampedPosition5, false));
      double d3 = pitchRange[0];
      double d4 = pitchRange[1];
      double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double(pitchRange2[0]), d3);
      double d6 = EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double(pitchRange2[1]), d4);
      if (flag) {
         shortestSignedAngleDelta = 0.0;
         d6 = 0.0;
      }

      double d7 = pitchRange2[0];
      double d8 = pitchRange2[1];
      UNoise3PredictionEntry uNoise3PredictionEntry = new UNoise3PredictionEntry(clampedPosition2, clampedPosition, shortestSignedAngleDelta, 0.0, d3, d7, 1);
      UNoise3PredictionEntry uNoise3PredictionEntry2 = new UNoise3PredictionEntry(clampedPosition2, clampedPosition, d6, 0.0, d4, d8, 1);
      uNoise3PredictionEntry.filtered = uNoise3PredictionEntry2.filtered = flag;
      uNoise3PredictionEntry.tooClose = uNoise3PredictionEntry2.tooClose = flag2;
      return new UNoise3PredictionEntry[]{uNoise3PredictionEntry, uNoise3PredictionEntry2};
   }

   public ArrayList predictPlayerPositions(EntityPlayerSP player2, int n, int n2) {
      return TrajectoryPredictionUtil.predictPlayerPositions(player2, n, player2.ticksExisted, (byte)0, n2);
   }

   public Vec3 getHistoricalPosition(EntityLivingBase entity, int n) {
      if (entity instanceof EntityPlayerSP) {
         return this.getHistoricalPlayerPos((EntityPlayerSP)entity, n);
      } else {
         return this.getHistoricalTargetPos(entity, n);
      }
   }

   public double predictMinDistance(EntityPlayerSP player2, EntityLivingBase entity, int n) {
      int n2 = player2.ticksExisted + n;
      int ticksExisted = player2.ticksExisted;
      ArrayList list = this.predictEntityPositions((EntityLivingBase)player2, ticksExisted, n2);
      int n3 = player2.ticksExisted + n;
      ArrayList list2 = this.predictEntityPositions(entity, player2.ticksExisted, n3);
      int n4 = Math.max(list.size(), list2.size());
      double d = 1.0 / 0.0;

      for (int i = 0; i < n4; i++) {
         Vec3 clampedPosition = TrajectoryPredictionUtil.getClampedPosition(list, i);
         AxisAlignedBB entityBoundingBoxAt = EntityBoundsUtil.getEntityBoundingBoxAt(entity, TrajectoryPredictionUtil.getClampedPosition(list2, i), true);
         d = Math.min(d, RotationUtil.clampPointToBox(clampedPosition.addVector(0.0, (double)player2.getEyeHeight(), 0.0), entityBoundingBoxAt).subtract(clampedPosition).length());
      }

      return d;
   }

   public Vec3 getHistoricalPlayerPos(EntityPlayerSP player2, int n) {
      return ((PlayerTickSnapshot)player2.extraSnapshotHistory.getFromEnd(MathHelper.clamp_int(player2.ticksExisted - n, 0, Math.max(player2.extraSnapshotHistory.size() - 1, 0)))).position;
   }

   public ArrayList predictEntityPositions(EntityLivingBase entity, int n, int n2) {
      if (entity instanceof EntityPlayerSP) {
         return this.predictPlayerPositions((EntityPlayerSP)entity, n, n2);
      } else {
         return TrajectoryPredictionUtil.predictTargetPositions(this.player, entity, n, n2);
      }
   }

   public UNoise3PredictionEntry[] predictYawEntries(EntityPlayerSP player2, EntityLivingBase entity, int n, int n2, int n3, int n4) {
      int n5 = n4 + this.targetTickOffset;
      ArrayList list = this.predictEntityPositions(entity, n, n5 + (int)this.uNoise3TargetMovementConstantOffset.getValue(true));
      int n7 = n4 + this.playerTickOffset;
      ArrayList list2 = TrajectoryPredictionUtil.predictPlayerPositions(player2, n, n2, n3, n7 + (int)this.uNoise3PlayerMovementConstantOffset.getValue(true));
      double d2 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(HitboxAngleUtil.getYawRange(TrajectoryPredictionUtil.getClampedPosition(list2, list2.size() - 1), EntityBoundsUtil.getEntityBoundingBoxAt(entity, TrajectoryPredictionUtil.getClampedPosition(list, list.size() - 1), false)), 0.5));
      double shortestAngularDistance = HitboxAngleUtil.getShortestAngularDistance(d2, MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(HitboxAngleUtil.getYawRange(player2.getPositionEyes(1.0F), EntityBoundsUtil.getEntityBoundingBoxAt(entity, entity.getInterpolatedPos(1.0F), false)), 0.5)));
      boolean flag5 = false;
      if (shortestAngularDistance > this.uNoise3MinInaccuracyForFilterToApply.getValue(true)) {
         for (int i = n; i <= n4; i++) {
            Vec3 clampedPosition3 = TrajectoryPredictionUtil.getClampedPosition(list2, i - n);
            Vec3 clampedPosition4 = TrajectoryPredictionUtil.getClampedPosition(list, i - n);
            Vec3 historicalPlayerPos = this.getHistoricalPlayerPos(player2, i + 1);
            Vec3 historicalTargetPos = this.getHistoricalTargetPos(entity, i + 1);
            Vec2d vec2d = new Vec2d(clampedPosition3.xCoord, clampedPosition3.zCoord);
            Vec2d vec2d2 = new Vec2d(clampedPosition4.xCoord, clampedPosition4.zCoord);
            Vec2d vec2d3 = new Vec2d(historicalPlayerPos.xCoord, historicalPlayerPos.zCoord);
            Vec2d vec2d4 = new Vec2d(historicalTargetPos.xCoord, historicalTargetPos.zCoord);
            if (vec2d.subtract(vec2d2).angleDegrees(vec2d3.subtract(vec2d4)) > this.uNoise3MinInaccuracyForFilterToApplyMoment.getValue(true)) {
               flag5 = true;
               int n15 = n4;
               n4 = Math.min(i + (int)this.uNoise3FilterTimeOffset.getValue(true), n4);
               if (n15 != n4) {
               }
            }
         }
      }

      ArrayList list3 = this.predictEntityPositions(entity, n, (n4 + this.targetTickOffset) + (int)this.uNoise3TargetMovementConstantOffset.getValue(true) - 1);
      n7 = n4 + this.playerTickOffset;
      ArrayList list4 = TrajectoryPredictionUtil.predictPlayerPositions(player2, n, n2, n3, n7 + (int)this.uNoise3PlayerMovementConstantOffset.getValue(true) - 1);
      n5 = n4 + this.targetTickOffset;
      list = this.predictEntityPositions(entity, n, n5 + (int)this.uNoise3TargetMovementConstantOffset.getValue(true));
      n7 = n4 + this.playerTickOffset;
      list2 = TrajectoryPredictionUtil.predictPlayerPositions(player2, n, n2, n3, n7 + (int)this.uNoise3PlayerMovementConstantOffset.getValue(true));
      double d7 = 1.0;
      Vec3 clampedPosition5 = TrajectoryPredictionUtil.getClampedPosition(list, (byte)0);
      Vec3 clampedPosition6 = TrajectoryPredictionUtil.getClampedPosition(list2, (byte)0);
      int n21 = 0;
      double d8 = 1.0 / 0.0;

      for (int j = 0; j < Math.max(list.size(), list2.size()); n21 = j++) {
         Vec3 clampedPosition7 = TrajectoryPredictionUtil.getClampedPosition(list, j);
         Vec3 clampedPosition8 = TrajectoryPredictionUtil.getClampedPosition(list2, j);
         if (clampedPosition7.subtract(clampedPosition8).horizontalLength() < d7) {
            flag5 = true;
            break;
         }

         d8 = Math.min(d8, clampedPosition7.subtract(clampedPosition8).horizontalLength());
         clampedPosition5 = clampedPosition7;
         clampedPosition6 = clampedPosition8;
      }

      boolean flag13 = d8 < d7;
      Vec3 clampedPosition9 = TrajectoryPredictionUtil.getClampedPosition(list3, n21 - 1);
      Vec3 clampedPosition10 = TrajectoryPredictionUtil.getClampedPosition(list4, n21 - 1);
      double[] yawRange3 = HitboxAngleUtil.getYawRange(clampedPosition6, EntityBoundsUtil.getEntityBoundingBoxAt(entity, clampedPosition5, false));
      double[] yawRange4 = HitboxAngleUtil.getYawRange(clampedPosition10, EntityBoundsUtil.getEntityBoundingBoxAt(entity, clampedPosition9, false));
      double d9 = MathHelper.wrapAngleTo180_double(yawRange3[0]);
      double d10 = MathHelper.wrapAngleTo180_double(yawRange3[1]);
      double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double(yawRange4[0]), d9);
      double d12 = EntityBoundsUtil.getShortestSignedAngleDelta(MathHelper.wrapAngleTo180_double(yawRange4[1]), d10);
      if (flag5) {
         shortestSignedAngleDelta = 0.0;
         d12 = 0.0;
      }

      double d13 = MathHelper.wrapAngleTo180_double(yawRange4[0]);
      double d14 = MathHelper.wrapAngleTo180_double(yawRange4[1]);
      UNoise3PredictionEntry uNoise3PredictionEntry = new UNoise3PredictionEntry(clampedPosition6, clampedPosition5, shortestSignedAngleDelta, 0.0, d9, d13, 1);
      UNoise3PredictionEntry uNoise3PredictionEntry2 = new UNoise3PredictionEntry(clampedPosition6, clampedPosition5, d12, 0.0, d10, d14, 1);
      uNoise3PredictionEntry.filtered = uNoise3PredictionEntry2.filtered = flag5;
      uNoise3PredictionEntry.tooClose = uNoise3PredictionEntry2.tooClose = flag13;
      return new UNoise3PredictionEntry[]{uNoise3PredictionEntry, uNoise3PredictionEntry2};
   }

   public Vec3 getHistoricalTargetPos(EntityLivingBase entity, int n) {
      return (Vec3)entity.positionHistory.getFromEnd(MathHelper.clamp_int(this.player.ticksExisted - n, 0, Math.max(entity.positionHistory.size() - 1, 0)));
   }

}
