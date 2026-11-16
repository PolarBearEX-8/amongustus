package client.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class SthWentMinecraftAccessor implements MinecraftAccessor {
   public static Vec3 resolveVec3(AbstractClientPlayer player, Vec3 vec) {
      float factor;
      float factor2;
      if (player.isSprinting()) {
         factor = (float)(vec.xCoord * 0.3F);
         factor2 = (float)(vec.zCoord * 0.3F);
      } else {
         factor = Math.min(Math.max(-1.0F, (float)Math.round(vec.xCoord)), 1.0F);
         factor2 = Math.min(Math.max(-1.0F, (float)Math.round(vec.zCoord)), 1.0F);
      }

      if (player.isBlocking()) {
         factor *= 0.2F;
         factor2 *= 0.2F;
      }

      Vec3 vec3 = new Vec3((double)factor, 0.0, (double)factor2).getVec3(0.98F);
      Vec3 vec2 = new Vec3((double)((float)vec3.xCoord), (double)((float)vec3.yCoord), (double)((float)vec3.zCoord));
      if (vec2.getValue() > 1.0) {
         double offset = Math.sqrt(vec2.xCoord * vec2.xCoord + vec2.yCoord * vec2.yCoord + vec2.zCoord * vec2.zCoord);
         vec2 = new Vec3(vec2.xCoord / offset, vec2.yCoord / offset, vec2.zCoord / offset);
      }

      return vec2;
   }

   public static HashMap resolveHashMap(AbstractClientPlayer player, float factor, char character) {
      HashMap map = new HashMap();

      for (int index = -1; index <= 1; index++) {
         for (int index2 = -1; index2 <= 1; index2++) {
            Vec3 vec = new Vec3((double)index, 0.0, (double)index2);
            Vec3 vec2 = resolveVec3(player, vec);
            float factor2 = player.rotationYaw;
            map.put(resolveVec3(vec2, factor, factor2), vec);
         }
      }

      return map;
   }

   public static Vec3 resolveVec3(Vec3 vec, float factor, float factor2) {
      float factor3 = Helper.computeFactorByFloat(factor2 * (float) (Math.PI / 180.0));
      float factor4 = Helper.computeFactor(factor2 * (float) (Math.PI / 180.0));
      double offset = vec.xCoord * (double)factor4 - vec.zCoord * (double)factor3;
      double offset2 = vec.zCoord * (double)factor4 + vec.xCoord * (double)factor3;
      return new Vec3(offset * (double)factor, 0.0, offset2 * (double)factor);
   }

   public static Vec3 resolveVec3(AbstractClientPlayer player, int count, int count2, char character) {
      if (CombatTargetFilter.checkEntityLivingBase(1222936913, 29201, player)) {
         return null;
      } else {
         char character3 = 8227;
         float factor = 0.2F;
         HashMap map = resolveHashMap(player, factor, character3);
         Vec3 vec = new Vec3(
            MathHelper.getDouble7(player.coordOffset5 - player.value12, -20.0, 20.0),
            MathHelper.getDouble7(player.posY - player.lastTickPosY, -20.0, 20.0),
            MathHelper.getDouble7(player.coordOffset20 - player.value20, -20.0, 20.0)
         );
         double offset = Double.MAX_VALUE;
         Vec3 vec2 = null;

         for (Entry entry : (Iterable<Entry>)(Iterable<?>)map.entrySet()) {
            double offset2 = ((Vec3)entry.getKey()).squareDistanceTo(vec);
            if (offset2 < offset) {
               offset = offset2;
               vec2 = (Vec3)entry.getValue();
               Vec3 key = (Vec3)entry.getKey();
            }
         }

         if (vec2 == null) {
            System.err.println("Sth went really wrong in prediction...");
            return null;
         } else {
            return vec2;
         }
      }
   }

   public static boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      double offset = livingEntity.coordOffset20 - livingEntity.value20;
      double offset2 = livingEntity.coordOffset5 - livingEntity.value12;
      double offset3 = livingEntity.coordOffset2 - livingEntity.value10;
      double offset4 = (offset - 0.08) * 0.98F;
      float factor = livingEntity.width / 2.0F;
      AxisAlignedBB boundingBox = new AxisAlignedBB(
         livingEntity.coordOffset5 - (double)factor,
         livingEntity.coordOffset20,
         livingEntity.coordOffset2 - (double)factor,
         livingEntity.coordOffset5 + (double)factor,
         livingEntity.coordOffset20 + (double)livingEntity.height,
         livingEntity.coordOffset2 + (double)factor
      );
      double offset5 = offset4;
      Iterator iterator = minecraft.theWorld.getCollidingBoundingBoxes(livingEntity, boundingBox.contract(offset2, offset4, offset3)).iterator();

      while (iterator.hasNext()) {
         offset4 = ((AxisAlignedBB)iterator.next()).getCoordinate3(boundingBox, offset4);
      }

      boundingBox.expand(0.0, offset4, 0.0);
      return offset5 != offset4 && offset5 < 0.0 || offset4 == offset;
   }
}
