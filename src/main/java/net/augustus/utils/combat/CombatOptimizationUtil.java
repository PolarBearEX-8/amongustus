package net.augustus.utils.combat;

import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.rotation.EntityBoundsUtil;
import java.util.ArrayList;
import java.util.HashMap;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class CombatOptimizationUtil implements MC {

   public static Vec3 getPredictedEntityPosition(Entity entity) {
      if (entity instanceof EntityLivingBase) {
         EntityLivingBase entity2 = (EntityLivingBase)entity;
         return entity2.newPosRotationIncrements > 0
            ? new Vec3(
               entity2.posX + (entity2.newPosX - entity2.posX) / (double)entity2.newPosRotationIncrements,
               entity2.posY + (entity2.newPosY - entity2.posY) / (double)entity2.newPosRotationIncrements,
               entity2.posZ + (entity2.newPosZ - entity2.posZ) / (double)entity2.newPosRotationIncrements
            )
            : entity.getInterpolatedPos(1.0F);
      } else {
         return entity.getInterpolatedPos(1.0F);
      }
   }

   private static int computeMaxZeroTicks(int n, double d, double d2) {
      byte b = 1;
      double d3 = (double)n * (1.0 - d2 / (double)b);
      double d4 = (double)n / 2.0 * (1.0 - Math.sqrt(1.0 - 4.0 * d * d / (double)(b * b)));
      if (!Double.isFinite(d4)) {
         d4 = (double)n / 2.0;
      }

      return (int)Math.ceil(Math.max(d3, d4));
   }

   public static boolean shouldReduceSprintKnockback(Entity entity, float f) {
      Vec3 vec = PlayerUtil.predictNextTickWithAttack(true, entity, f).addVector(0.0, (double)mc.thePlayer.getEyeHeight(), 0.0);
      Vec3 vec2 = PlayerUtil.predictNextTickWithAttack(false, entity, f).addVector(0.0, (double)mc.thePlayer.getEyeHeight(), 0.0);
      float collisionBorderSize = entity.getCollisionBorderSize();
      AxisAlignedBB box = EntityBoundsUtil.createPlayerBoundingBox(getPredictedEntityPosition(entity), false).expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
      Vec3 vec4 = new Vec3(
         MathHelper.clamp_double(vec.xCoord, box.minX, box.maxX),
         MathHelper.clamp_double(vec.yCoord, box.minY, box.maxY),
         MathHelper.clamp_double(vec.zCoord, box.minZ, box.maxZ)
      );
      Vec3 vec5 = new Vec3(
         MathHelper.clamp_double(vec2.xCoord, box.minX, box.maxX),
         MathHelper.clamp_double(vec2.yCoord, box.minY, box.maxY),
         MathHelper.clamp_double(vec2.zCoord, box.minZ, box.maxZ)
      );
      return vec.distanceTo(vec4) >= vec2.distanceTo(vec5);
   }

   public static int chooseOptimalClickCount(ArrayList list, int n, double d, double d2) {
      double d3 = d2 / 20.0;
      int n2 = (Integer)list.get(0);
      int n3 = computeMaxZeroTicks(list.size(), d, d3);
      int n4 = 0;
      int n5 = 0;
      int n6 = 0;
      int n7 = 0;
      double d4 = 0.0;
      double d5 = 0.0;

      for (int n8 : (Iterable<Integer>)(list)) {
         switch (n8) {
            case 0:
               n4++;
               break;
            case 1:
               n5++;
               break;
            case 2:
               n6++;
         }

         n7 += n8;
      }

      if (list.size() > n) {
         switch (n2) {
            case 0:
               n4--;
               break;
            case 1:
               n5--;
               break;
            case 2:
               n6--;
         }

         n7 -= n2;
         list.remove(0);
      }

      d4 = (double)n7 / (double)list.size();

      for (int n9 : (Iterable<Integer>)(list)) {
         d5 += Math.pow((double)n9 - d4, 2.0);
      }

      d5 = Math.sqrt(d5 / (double)list.size());
      n3++;
      ArrayList list2 = new ArrayList();
      HashMap map = new HashMap();

      for (int i = 0; i < 3; i++) {
         if (i != 0 || n4 < n3) {
            switch (i) {
               case 0:
                  n4++;
                  break;
               case 1:
                  n5++;
                  break;
               case 2:
                  n6++;
            }

            n7 += i;
            list.add(i);
            double d6 = (double)n7 / (double)list.size();
            double d7 = 0.0;

            for (int n10 : (Iterable<Integer>)(list)) {
               d7 += Math.pow((double)n10 - d6, 2.0);
            }

            d7 = Math.sqrt(d7 / (double)list.size());
            if (d6 <= d3 && d7 >= d) {
               list2.add(i);
            }

            double d8 = d4 - d6;
            double d9 = d7 - d5;
            if (d4 > d3 && d5 < d) {
               if (d6 < d3) {
                  map.put(i, (d8 + d9) * 2.0);
               } else {
                  map.put(i, d8 + d9);
               }
            } else if (d4 > d3) {
               map.put(i, d9);
            } else {
               map.put(i, d8);
            }

            switch (i) {
               case 0:
                  n4--;
                  break;
               case 1:
                  n5--;
                  break;
               case 2:
                  n6--;
            }

            n7 -= i;
            list.remove(list.size() - 1);
         }
      }

      if (list2.isEmpty()) {
         System.out.println("Try to find best number");
         double d10 = -1337.0;
         int n11 = 1;
         boolean flag = true;

         for (int n12 : (Iterable<Integer>)(map.keySet())) {
            double d11 = (Double)map.get(n12);
            if (d11 > 0.0 && d11 > d10) {
               d10 = d11;
               n11 = n12;
               flag = false;
            }
         }

         if (flag) {
            System.out.println("Fucking no number found!");
         }

         return n11;
      } else {
         ArrayList list3 = new ArrayList();
         if (list2.size() > 1) {
            for (int j = 0; j < list2.size(); j++) {
               int n13 = (Integer)list2.get(j);
               if (n13 == 0) {
                  list2.remove(j);
               }
            }
         }

         for (int n14 : (Iterable<Integer>)(list2)) {
            if (n14 != 0 || list2.size() == 1) {
               list3.add(n14);
            }
         }

         if (list3.size() > 1) {
            for (int n15 : (Iterable<Integer>)(list3)) {
               if (n2 != n15) {
                  return n15;
               }
            }
         }

         return (Integer)list3.get(0);
      }
   }


}
