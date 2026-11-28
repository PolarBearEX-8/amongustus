package net.augustus.utils.simulation;

import net.augustus.utils.player.PlayerUtil;
import java.util.ArrayList;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class TrajectoryPredictionUtil implements MC {
   public static MovementPredictionProvider playerPredictionProvider = null;
   public static MovementPredictionProvider targetPredictionProvider = null;

   public static double estimateVerticalVelocity(EntityLivingBase entity, int n) {
      HybridKalmanPredictor hybridKalmanPredictor = new HybridKalmanPredictor();
      hybridKalmanPredictor.initVertical((Vec3)entity.positionHistory.get((byte)0), (Vec3)entity.positionHistory.get(n), (double)n / 20.0);
      double yCoord = ((Vec3)entity.positionHistory.get((byte)0)).yCoord;
      double d2 = 0.0;
      double yCoord2 = ((Vec3)entity.positionHistory.get((byte)0)).yCoord;

      for (int i = 0; i <= n; i++) {
         Vec3 vec5 = (Vec3)entity.positionHistory.get(i);
         if (vec5.yCoord - yCoord2 > 0.8) {
            return 0.0;
         }

         yCoord2 = vec5.yCoord;
      }

      for (int j = 0; j <= n; j++) {
         Vec3 vec6 = (Vec3)entity.positionHistory.get(j);
         double d3 = 0.0;
         hybridKalmanPredictor.updateMeasurement(vec6.yCoord, d3);
         yCoord = d2;
         d2 = hybridKalmanPredictor.predictNextPosition()[0];
      }

      return MathHelper.clamp_double(d2 - yCoord, -1.0, 1.0);
   }

   public static ArrayList predictPlayerPositions(EntityPlayerSP player, int n, int n2, int n3, int n4) {
      return predictPlayerPositions(player, n, n2, n3, n4, false);
   }

   public static ArrayList predictPlayerPositions(EntityPlayerSP player, int n, int n2, int n3, int n4, boolean flag) {
      if (!flag && playerPredictionProvider != null) {
         return playerPredictionProvider.predictTrajectory(n, n2, n3, n4);
      } else if (player.extraSnapshotHistory.size() == 0) {
         ArrayList list = new ArrayList();
         list.add(player.getInterpolatedPos(1.0F));
         return list;
      } else {
         int ticksExisted = player.ticksExisted;
         if (n > ticksExisted) {
            n = ticksExisted;
         }

         if (player.ticksExisted - n4 > player.extraSnapshotHistory.getEntries().size() - 1) {
            ArrayList list2 = new ArrayList();
            list2.add(((PlayerTickSnapshot)player.extraSnapshotHistory.get((byte)0)).position);
            return list2;
         } else if (n >= n4) {
            ArrayList list3 = new ArrayList();
            int n5 = player.ticksExisted - n4;
            list3.add(((PlayerTickSnapshot)player.extraSnapshotHistory.getFromEnd(n5)).position);
            return list3;
         } else {
            int n6 = ticksExisted - n;
            int n7 = n4 - n;
            if (player.extraSnapshotHistory.size() <= n6) {
               ArrayList list4 = new ArrayList();
               list4.add(player.getInterpolatedPos(1.0F));
               return list4;
            } else {
               ArrayList list5 = new ArrayList();

               for (int i = n; i <= n2; i++) {
                  int n8 = ticksExisted - i + n3 - 1;
                  if (n8 >= 0 && n8 < player.extraSnapshotHistory.size()) {
                     list5.add(player.extraSnapshotHistory.getFromEnd(n8));
                  }
               }

               ArrayList list6 = PlayerUtil.simulatePlayerSnapshotsHistory(player, list5, (PlayerTickSnapshot)player.extraSnapshotHistory.getFromEnd(n6), n7 - 1, false);
               ArrayList list7 = new ArrayList();

               for (double[] doubles : (Iterable<double[]>)(list6)) {
                  list7.add(new Vec3(doubles[0], doubles[1], doubles[2]));
               }

               return list7;
            }
         }
      }
   }

   public static ArrayList predictTargetPositions(EntityPlayerSP player, EntityLivingBase entity, int n, int n2, boolean flag) {
      if (entity.positionHistory.size() != 0 && entity.motionSnapshotHistory.size() != 0) {
         int ticksExisted = player.ticksExisted;
         if (n > ticksExisted) {
            n = ticksExisted;
         }

         if (ticksExisted - n2 > entity.positionHistory.size() - 1) {
            ArrayList list = new ArrayList();
            list.add(entity.positionHistory.get((byte)0));
            return list;
         } else if (n < n2 && n >= 0) {
            int n3 = ticksExisted - n;
            int n4 = n2 - n;
            int n5 = entity.positionHistory.size() - (ticksExisted - n) - 1;
            HybridKalmanPredictor hybridKalmanPredictor = new HybridKalmanPredictor();
            hybridKalmanPredictor.initHorizontal((Vec3)entity.positionHistory.get((byte)0), (Vec3)entity.positionHistory.get(n5), (double)n5 / 20.0);
            double[] doubles = new double[2];
            doubles[0] = ((Vec3)entity.positionHistory.get((byte)0)).xCoord;
            doubles[1] = ((Vec3)entity.positionHistory.get((byte)0)).zCoord;
            double[] doubles2 = doubles;
            double[] doubles3 = new double[2];

            for (int i = 0; i <= n5; i++) {
               Vec3 vec5 = (Vec3)entity.positionHistory.get(i);
               double zCoord = vec5.zCoord;
               hybridKalmanPredictor.updateMeasurement(vec5.xCoord, zCoord);
               doubles2 = doubles3;
               doubles3 = hybridKalmanPredictor.predictNextPosition();
            }

            doubles3 = new double[]{doubles3[0] - doubles2[0], doubles3[1] - doubles2[1]};
            double d2 = estimateVerticalVelocity(entity, n5);
            if (n3 > entity.motionSnapshotHistory.getEntries().size() - 1) {
               return EntityMovementPredictor.predictTrajectoryFromSnapshot(entity, (EntityMotionSnapshot)entity.motionSnapshotHistory.get((byte)0), n4, doubles3[0], d2, doubles3[1], flag);
            } else {
               return EntityMovementPredictor.predictTrajectoryFromSnapshot(entity, (EntityMotionSnapshot)entity.motionSnapshotHistory.getFromEnd(n3), n4, doubles3[0], d2, doubles3[1], flag);
            }
         } else {
            ArrayList list2 = new ArrayList();
            int n6 = ticksExisted - n2;
            list2.add(entity.positionHistory.getFromEnd(n6));
            return list2;
         }
      } else {
         ArrayList list3 = new ArrayList();
         list3.add(entity.getInterpolatedPos(1.0F));
         return list3;
      }
   }

   public static ArrayList predictTargetPositions(EntityPlayerSP player, EntityLivingBase entity, int n, int n2) {
      if (targetPredictionProvider != null) {
         return targetPredictionProvider.predictTrajectory(n, (byte)0, (byte)0, n2);
      } else {
         return predictTargetPositions(player, entity, n, n2, false);
      }
   }

   public static Vec3 getClampedPosition(ArrayList list, int n) {
      if (n < 0) {
         return (Vec3)list.get(0);
      } else {
         return n >= list.size() ? (Vec3)list.get(list.size() - 1) : (Vec3)list.get(n);
      }
   }



}
