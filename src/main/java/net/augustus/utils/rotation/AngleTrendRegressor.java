package net.augustus.utils.rotation;

import net.augustus.utils.math.DoubleSampleWindow;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class AngleTrendRegressor {

   public static AnglePredictionSample[] predictAngleBounds(
      TargetTrajectoryPredictor predictor, EntityPlayerSP player, EntityLivingBase entity, int n, int n2, int n3, double d, int n4
   ) {
      DoubleSampleWindow doubleSampleWindow = new DoubleSampleWindow(n3);
      int n5 = n - n3 + 1;
      double d2 = 0.0;
      double d3 = 0.0;
      double d4 = 0.0;

      for (int i = n5; i <= n; i++) {
         Vec3 vec = predictor.getPredictedEntityPosition((EntityLivingBase)player, n, i);
         AxisAlignedBB entityBoundingBoxAt = EntityBoundsUtil.getEntityBoundingBoxAt(entity, predictor.getPredictedEntityPosition(entity, n, i), true);
         double d5 = MathHelper.wrapAngleTo180_double(HitboxAngleUtil.getYawRange(vec, entityBoundingBoxAt)[n4]);
         if (i == n5) {
            d3 = d5;
            d4 = d5;
            doubleSampleWindow.add(0.0);
         } else {
            double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(d3, d5);
            if (entityBoundingBoxAt.isVecInside(vec.xCoord, (entityBoundingBoxAt.minY + entityBoundingBoxAt.maxY) / 2.0, vec.zCoord) || Math.abs(shortestSignedAngleDelta) > 90.0) {
               break;
            }

            d2 += shortestSignedAngleDelta;
            d3 = d5;
            doubleSampleWindow.add(d2);
         }
      }

      int n6 = n2 - n5;
      if (doubleSampleWindow.getSamples().size() < 2) {
         return null;
      } else {
         double[] doubles = doubleSampleWindow.linearRegression();
         double d8 = doubles[0];
         double d9 = doubles[1];
         double d10 = Math.abs(d8);
         double d11 = Math.abs(d9);
         return new AnglePredictionSample[]{
            new AnglePredictionSample(d10, d11, MathHelper.wrapAngleTo180_double(d10 * (double)n6 + d11 + d4), d4, n6),
            new AnglePredictionSample(-d10, -d11, MathHelper.wrapAngleTo180_double(-d10 * (double)n6 - d11 + d4), d4, n6)
         };
      }
   }
}
