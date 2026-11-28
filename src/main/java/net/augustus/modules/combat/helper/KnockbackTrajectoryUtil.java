package net.augustus.modules.combat.helper;

import net.augustus.modules.combat.KnockbackSimulationResult;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public final class KnockbackTrajectoryUtil implements MC {

   private KnockbackTrajectoryUtil() {
   }

   public static float calculateKnockbackAngleDelta(Vec3 vec, float f) {
      double d = vec.xCoord - mc.thePlayer.posX;
      double d2 = vec.zCoord - mc.thePlayer.posZ;
      double d3 = Math.sqrt(d * d + d2 * d2);
      if (d3 < 1.0E-4) {
         return 0.0F;
      } else {
         double d4 = Math.atan2(-d, d2);
         double d5 = (double)f * Math.PI / 180.0;
         double d6 = d / d3 * 0.4 - Math.sin(d4) * 0.5;
         double d7 = d2 / d3 * 0.4 + Math.cos(d4) * 0.5;
         double d8 = d / d3 * 0.4 - Math.sin(d5) * 0.5;
         double d9 = d2 / d3 * 0.4 + Math.cos(d5) * 0.5;
         float f2 = (float)Math.toDegrees(Math.atan2(d6, d7));
         return Math.abs(MathHelper.wrapAngleTo180_float(((float)Math.toDegrees(Math.atan2(d8, d9))) - f2));
      }
   }

   public static KnockbackSimulationResult simulateKnockback(EntityLivingBase entity, Vec3 vec, float f, int n) {
      double d = vec.xCoord - mc.thePlayer.posX;
      double d2 = vec.zCoord - mc.thePlayer.posZ;
      double d3 = Math.sqrt(d * d + d2 * d2);
      if (d3 < 1.0E-4) {
         d = 0.0;
         d2 = 1.0;
         d3 = 1.0;
      }

      double d4 = 0.4;
      double d5 = entity.motionX / 2.0 + d / d3 * 0.4;
      double d6 = Math.min(entity.motionY / 2.0 + 0.4, 0.4);
      double d7 = entity.motionZ / 2.0 + d2 / d3 * 0.4;
      double d8 = (double)f * Math.PI / 180.0;
      double d9 = 0.5;
      d5 -= Math.sin(d8) * 0.5;
      d6 += 0.1;
      d7 += Math.cos(d8) * 0.5;
      double xCoord = vec.xCoord;
      double yCoord = vec.yCoord;
      double zCoord = vec.zCoord;
      double d10 = (double)entity.width / 2.0;
      boolean flag = false;
      double d11 = 0.0;

      for (int i = 0; i < 100; i++) {
         xCoord += d5;
         yCoord += d6;
         zCoord += d7;
         boolean flag2 = hasGroundSupport(xCoord, yCoord, zCoord, d10);
         if (d6 < 0.0 && flag2) {
            flag = true;
            d6 = 0.0;
         }

         if (flag) {
            break;
         }

         d6 -= 0.08;
         d6 *= 0.98;
         d5 *= 0.91;
         d7 *= 0.91;
         d11 += (double)measureDropDepth(xCoord, yCoord, zCoord, d10, 24);
         if (vec.yCoord - yCoord > (double)n) {
            break;
         }
      }

      boolean flag3 = !hasGroundSupport(xCoord, yCoord, zCoord, d10);
      return new KnockbackSimulationResult(flag3, new Vec3(xCoord, yCoord, zCoord), d11);
   }

   private static int measureDropDepth(double d, double d2, double d3, double d4, int n) {

      for (int i = 0; i < n; i++) {
         if (hasGroundSupport(d, d2 - (double)i, d3, d4)) {
            return i;
         }
      }

      return n;
   }

   private static boolean hasGroundSupport(double d, double d2, double d3, double d4) {
      int n = (int)Math.floor(d - d4);
      int n2 = (int)Math.floor(d3 - d4);
      int n3 = (int)Math.floor(d + d4);
      int n4 = (int)Math.floor(d3 + d4);
      int n5 = (int)Math.floor(d2 - 0.05);

      for (int i = n; i <= n3; i++) {
         for (int j = n2; j <= n4; j++) {
            if (!mc.theWorld.isAirBlock(i, n5, j)) {
               return true;
            }
         }
      }

      return false;
   }
}
