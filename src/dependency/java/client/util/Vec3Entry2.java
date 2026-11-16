package client.util;

import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Vec3Entry2 {
   public static Vec3 vec3 = new Vec3(0.0, 0.0, 0.0);

   public static Vec3 resolveVec2(AuxiliaryUUIDEntry auxiliaryUUIDEntry, int count, int count2, Vec3 vec) {
      vec3 = vec.resolveVec3(auxiliaryUUIDEntry.posX, auxiliaryUUIDEntry.posY, auxiliaryUUIDEntry.posZ);
      return resolveVecByAuxiliaryUUIDEnt(auxiliaryUUIDEntry, count, count2, vec3);
   }

   public static Vec3 resolveVec3(AuxiliaryUUIDEntry auxiliaryUUIDEntry, int count, int count2) {
      return resolveVecByAuxiliaryUUIDEnt(auxiliaryUUIDEntry, count, count2, (Vec3)null);
   }

   private static Vec3 resolveVecByAuxiliaryUUIDEnt(AuxiliaryUUIDEntry auxiliaryUUIDEntry, int count, int count2, Vec3 vec) {
      Random random = auxiliaryUUIDEntry.getRandom();
      boolean flag = false;
      int count3 = 0;
      int count4 = 0;
      int count5 = 0;
      float factor = -99999.0F;
      boolean flag2;
      if (auxiliaryUUIDEntry.isActive30()) {
         double offset = auxiliaryUUIDEntry.getBlockPos2()
               .computeValue(
                  (double)MathHelper.ceiling_double_int(auxiliaryUUIDEntry.posX),
                  (double)MathHelper.ceiling_double_int(auxiliaryUUIDEntry.posY),
                  (double)MathHelper.ceiling_double_int(auxiliaryUUIDEntry.posZ)
               )
            + 4.0;
         double offset2 = (double)(auxiliaryUUIDEntry.onPreDisable() + (float)count);
         flag2 = offset < offset2 * offset2;
      } else {
         flag2 = false;
      }

      for (int index = 0; index < 10; index++) {
         int count6 = random.nextInt(2 * count + 1) - count;
         int count7 = random.nextInt(2 * count2 + 1) - count2;
         int count8 = random.nextInt(2 * count + 1) - count;
         if (vec == null || (double)count6 * vec.xCoord + (double)count8 * vec.zCoord >= 0.0) {
            if (auxiliaryUUIDEntry.isActive30() && count > 1) {
               BlockPos blockPos = auxiliaryUUIDEntry.getBlockPos2();
               if (auxiliaryUUIDEntry.posX > (double)blockPos.getX()) {
                  count6 -= random.nextInt(count / 2);
               } else {
                  count6 += random.nextInt(count / 2);
               }

               if (auxiliaryUUIDEntry.posZ > (double)blockPos.getZ()) {
                  count8 -= random.nextInt(count / 2);
               } else {
                  count8 += random.nextInt(count / 2);
               }
            }

            int count9 = count6 + MathHelper.ceiling_double_int(auxiliaryUUIDEntry.posX);
            int count10 = count7 + MathHelper.ceiling_double_int(auxiliaryUUIDEntry.posY);
            int count11 = count8 + MathHelper.ceiling_double_int(auxiliaryUUIDEntry.posZ);
            BlockPos blockPos2 = new BlockPos(count9, count10, count11);
            if (!flag2 || auxiliaryUUIDEntry.matchesBlockPos(blockPos2)) {
               float factor2 = auxiliaryUUIDEntry.computeFactor(blockPos2);
               if (factor2 > factor) {
                  factor = factor2;
                  count3 = count9;
                  count4 = count10;
                  count5 = count11;
                  flag = true;
               }
            }
         }
      }

      return flag ? new Vec3((double)count3, (double)count4, (double)count5) : null;
   }

   public static Vec3 resolveVec3(AuxiliaryUUIDEntry auxiliaryUUIDEntry, int count, int count2, Vec3 vec) {
      vec3 = new Vec3(auxiliaryUUIDEntry.posX, auxiliaryUUIDEntry.posY, auxiliaryUUIDEntry.posZ).getVec311(vec);
      return resolveVecByAuxiliaryUUIDEnt(auxiliaryUUIDEntry, count, count2, vec3);
   }
}
