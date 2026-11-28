package net.augustus.utils.simulation;

import net.augustus.utils.math.FastTrigTable;
import java.util.HashMap;
import java.util.Map.Entry;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class PlayerInputPredictor implements MC {

   public static boolean isLikelyOnGround(EntityLivingBase entity) {
      double d = entity.realPosY - entity.predictedPosY;
      double d2 = entity.realPosX - entity.predictedPosX;
      double d3 = entity.realPosZ - entity.predictedPosZ;
      d -= 0.08;
      d *= 0.9800000190734863;
      float f = entity.width / 2.0F;
      AxisAlignedBB box = new AxisAlignedBB(
         entity.realPosX - (double)f,
         entity.realPosY,
         entity.realPosZ - (double)f,
         entity.realPosX + (double)f,
         entity.realPosY + (double)entity.height,
         entity.realPosZ + (double)f
      );
      double d4 = d;

      for (AxisAlignedBB box2 : (Iterable<AxisAlignedBB>)(mc.theWorld.getCollidingBoundingBoxesCapped(entity, box.addCoord(d2, d, d3)))) {
         d = box2.calculateYOffset(box, d);
      }

      box = box.offset(0.0, d, 0.0);
      return d4 != d && d4 < 0.0 || d == d;
   }

   public static Vec3 estimateMovementInput(AbstractClientPlayer clientPlayer) {
      if (EntityMovementPredictor.hasInvalidPositionHistory(clientPlayer)) {
         return null;
      } else {
         HashMap map = computeCandidateVelocities(clientPlayer, 0.2F);
         Vec3 vec = new Vec3(
            MathHelper.clamp_double(clientPlayer.realPosX - clientPlayer.predictedPosX, -20.0, 20.0),
            MathHelper.clamp_double(clientPlayer.posY - clientPlayer.lastTickPosY, -20.0, 20.0),
            MathHelper.clamp_double(clientPlayer.realPosY - clientPlayer.predictedPosY, -20.0, 20.0)
         );
         double d = 1.7976931348623157E308;
         Vec3 vec2 = null;
         Object obj = null;

         for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
            double d2 = ((Vec3)entry.getKey()).distanceToXZ(vec);
            if (d2 < d) {
               d = d2;
               vec2 = (Vec3)entry.getValue();
               obj = (Vec3)entry.getKey();
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

   public static Vec3 normalizeMoveInput(AbstractClientPlayer clientPlayer, Vec3 vec) {
      float f;
      float f2;
      if (clientPlayer.isSneaking()) {
         f = (float)(vec.xCoord * 0.30000001192092896);
         f2 = (float)(vec.zCoord * 0.30000001192092896);
      } else {
         f = Math.min(Math.max(-1.0F, (float)Math.round(vec.xCoord)), 1.0F);
         f2 = Math.min(Math.max(-1.0F, (float)Math.round(vec.zCoord)), 1.0F);
      }

      if (clientPlayer.isBlocking()) {
         f *= 0.2F;
         f2 *= 0.2F;
      }

      Vec3 vec2 = new Vec3((double)f, 0.0, (double)f2);
      vec2 = vec2.scale(0.9800000190734863);
      vec2 = new Vec3((double)((float)vec2.xCoord), (double)((float)vec2.yCoord), (double)((float)vec2.zCoord));
      if (vec2.lengthSquared() > 1.0) {
         double d = Math.sqrt(vec2.xCoord * vec2.xCoord + vec2.yCoord * vec2.yCoord + vec2.zCoord * vec2.zCoord);
         vec2 = new Vec3(vec2.xCoord / d, vec2.yCoord / d, vec2.zCoord / d);
      }

      return vec2;
   }

   public static Vec3 rotateInputVector(Vec3 vec, float f, float f2) {
      float f3 = FastTrigTable.sin(f2 * 0.017453292F);
      float f4 = FastTrigTable.cos(f2 * 0.017453292F);
      double d = vec.xCoord * (double)f4 - vec.zCoord * (double)f3;
      double d2 = vec.zCoord * (double)f4 + vec.xCoord * (double)f3;
      return new Vec3(d * (double)f, 0.0, d2 * (double)f);
   }

   public static HashMap computeCandidateVelocities(AbstractClientPlayer clientPlayer, float f) {
      HashMap map = new HashMap();

      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            Vec3 vec = new Vec3((double)i, 0.0, (double)j);
            map.put(rotateInputVector(normalizeMoveInput(clientPlayer, vec), f, clientPlayer.rotationYaw), vec);
         }
      }

      return map;
   }

}
