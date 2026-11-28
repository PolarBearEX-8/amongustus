package net.minecraft.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RandomPositionGenerator {
   private static Vec3 staticVector = new Vec3(0.0, 0.0, 0.0);

   private static Vec3 findRandomTargetBlock(EntityCreature entityCreature, int n, int n2, Vec3 vec) {
      Random rng = entityCreature.getRNG();
      boolean flag = false;
      int n3 = 0;
      int n4 = 0;
      int n5 = 0;
      float f = -99999.0F;
      boolean flag2;
      if (entityCreature.hasHome()) {
         double d = entityCreature.getHomePosition()
               .distanceSq((double)MathHelper.floor_double(entityCreature.posX), (double)MathHelper.floor_double(entityCreature.posY), (double)MathHelper.floor_double(entityCreature.posZ))
            + 4.0;
         double d2 = (double)(entityCreature.getMaximumHomeDistance() + (float)n);
         flag2 = d < d2 * d2;
      } else {
         flag2 = false;
      }

      for (int i = 0; i < 10; i++) {
         int n6 = rng.nextInt(2 * n + 1) - n;
         int n7 = rng.nextInt(2 * n2 + 1) - n2;
         int n8 = rng.nextInt(2 * n + 1) - n;
         if (vec == null || (double)n6 * vec.xCoord + (double)n8 * vec.zCoord >= 0.0) {
            if (entityCreature.hasHome() && n > 1) {
               BlockPos homePosition = entityCreature.getHomePosition();
               if (entityCreature.posX > (double)homePosition.getX()) {
                  n6 -= rng.nextInt(n / 2);
               } else {
                  n6 += rng.nextInt(n / 2);
               }

               if (entityCreature.posZ > (double)homePosition.getZ()) {
                  n8 -= rng.nextInt(n / 2);
               } else {
                  n8 += rng.nextInt(n / 2);
               }
            }

            n6 += MathHelper.floor_double(entityCreature.posX);
            n7 += MathHelper.floor_double(entityCreature.posY);
            n8 += MathHelper.floor_double(entityCreature.posZ);
            BlockPos pos = new BlockPos(n6, n7, n8);
            if (!flag2 || entityCreature.isWithinHomeDistanceFromPosition(pos)) {
               float blockPathWeight = entityCreature.getBlockPathWeight(pos);
               if (blockPathWeight > f) {
                  f = blockPathWeight;
                  n3 = n6;
                  n4 = n7;
                  n5 = n8;
                  flag = true;
               }
            }
         }
      }

      return flag ? new Vec3((double)n3, (double)n4, (double)n5) : null;
   }

   public static Vec3 findRandomTargetBlockTowards(EntityCreature entityCreature, int n, int n2, Vec3 vec) {
      staticVector = vec.subtract(entityCreature.posX, entityCreature.posY, entityCreature.posZ);
      return findRandomTargetBlock(entityCreature, n, n2, staticVector);
   }

   public static Vec3 findRandomTarget(EntityCreature entityCreature, int n, int n2) {
      return findRandomTargetBlock(entityCreature, n, n2, (Vec3)null);
   }

   public static Vec3 findRandomTargetBlockAwayFrom(EntityCreature entityCreature, int n, int n2, Vec3 vec) {
      staticVector = new Vec3(entityCreature.posX, entityCreature.posY, entityCreature.posZ).subtract(vec);
      return findRandomTargetBlock(entityCreature, n, n2, staticVector);
   }
}
