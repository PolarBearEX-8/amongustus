package client.util;

import client.event.ProviderListener;
import client.render.BoundingBoxDimension;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Listener72Entry implements MinecraftAccessor {
   public ProviderListener providerListener;
   public float factor2;
   public float factor = 0.0F;

   public Vec3 resolveVec3(EntityPlayerSP player, int count, int count2) {
      int ticksExisted = player.ticksExisted;
      return this.resolveVecByEntityPlayerSP(player, count, ticksExisted, count2);
   }

   public Vec3 resolveVecByEntityPlayerSP(EntityPlayerSP player, int count, int count2, int count3) {
      long value = 0x70adcfddb387L;
      if (this.providerListener != null) {
         return this.providerListener.resolveVec3(count, count2, count3);
      } else if (player.backtrackRecord4.getInt() == 0) {
         return player.getVec32(1.0F);
      } else if (player.ticksExisted - count3 > player.backtrackRecord4.getItemList().size() - 1) {
         return ((BoundingBoxDimension)player.backtrackRecord4.getObject(0)).positionVec2;
      } else if (count >= count3) {
         int count4 = player.ticksExisted - count3;
         return ((BoundingBoxDimension)player.backtrackRecord4.getObject3(count4)).positionVec2;
      } else {
         int count5 = player.ticksExisted - count;
         int count6 = count3 - count;
         if (player.backtrackRecord4.getInt() <= count5) {
            return player.getVec32(1.0F);
         } else {
            ArrayList list = new ArrayList();

            for (int index = 0; index <= count2 - count; index++) {
               list.add(player.backtrackRecord4.getObject3(index));
            }

            Collections.reverse(list);
            int count7 = count6 - 1;
            double[] offsetArray = CombatUtil.resolveDoubleArray(player, list, count7, false);
            return new Vec3(offsetArray[0], offsetArray[1], offsetArray[2]);
         }
      }
   }

   public CoreDoubleEntry resolveCoreDoubleEntry(CoreDoubleEntry[] coreDoubleEntryArray, double offset, double offset2) {
      double[] offsetArray = TrajectoryPredictor.resolveDoubleArray(coreDoubleEntryArray[0].value, coreDoubleEntryArray[1].value);
      double doubleItem = MathHelper.getDouble7(TrajectoryPredictor.computeValue2(offsetArray[0], offsetArray[1], offset), 0.0, 1.0);
      if (Double.isNaN(doubleItem)) {
         doubleItem = 0.0;
      }

      double doubleItem2 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(offsetArray, doubleItem));
      CoreDoubleEntry coreDoubleEntry = coreDoubleEntryArray[doubleItem < 0.5 ? 0 : 1];
      double offset3 = (HitBoxUtils.getDouble(coreDoubleEntry.value2, doubleItem2) - coreDoubleEntry.value3) / (double)coreDoubleEntry.count2 * offset2;
      double doubleItem3 = MathHelper.getDouble4(coreDoubleEntry.value2 + offset3 * (double)coreDoubleEntry.count2 + coreDoubleEntry.value3);
      return new CoreDoubleEntry(offset3, coreDoubleEntry.value3, doubleItem3, coreDoubleEntry.value2, coreDoubleEntry.count2);
   }

   public Vec3 resolveVecByEntityLivingBase(EntityLivingBase livingEntity, int count, byte byteValue, int count2, int count3, int count4) {
      byte byteValue2 = 0;
      int count5 = 6502683;
      int count6 = 2048467;
      if (this.providerListener != null) {
         return this.providerListener.resolveVec3(count, count2);
      } else if (livingEntity.backtrackRecord.getInt() != 0 && livingEntity.backtrackRecord3.getInt() != 0) {
         if (MinecraftAccessor.minecraft.thePlayer.ticksExisted - count2 > livingEntity.backtrackRecord.getInt() - 1) {
            return (Vec3)livingEntity.backtrackRecord.getObject(0);
         } else if (count < count2 && count >= 0) {
            int count7 = MinecraftAccessor.minecraft.thePlayer.ticksExisted - count;
            int count8 = count2 - count;
            if (count7 > livingEntity.backtrackRecord3.getItemList().size() - 1) {
               Vec3Entry object = (Vec3Entry)livingEntity.backtrackRecord3.getObject(0);
               return CombatTargetFilter.resolveVecByEntityLivingBase(livingEntity, object, count8);
            } else {
               Vec3Entry object2 = (Vec3Entry)livingEntity.backtrackRecord3.getObject3(count7);
               return CombatTargetFilter.resolveVecByEntityLivingBase(livingEntity, object2, count8);
            }
         } else {
            int count9 = MinecraftAccessor.minecraft.thePlayer.ticksExisted - count2;
            return (Vec3)livingEntity.backtrackRecord.getObject3(count9);
         }
      } else {
         return livingEntity.getVec32(1.0F);
      }
   }

   public CoreDoubleEntry[] resolveCoreDoubleEntryArray(
      EntityPlayerSP player, EntityLivingBase livingEntity, short shortValue, int count, int count2, int count3, int count4, int count5, double posX, double posY, double posZ
   ) {
      byte byteValue = 0;
      int count6 = 2052023370;
      char character = '\uc3a8';
      long keySalt = ((long)byteValue << 48 | (long)count6 << 32 >>> 16 | (long)character << 48 >>> 48) ^ 0x5159a89419adL ^ 0x482fd7c19bd6L;
      int count7 = (int)((((long)byteValue << 48 | (long)count6 << 32 >>> 16 | (long)character << 48 >>> 48) ^ 0x5159a89419adL ^ 0x482fd7c19bd6L) >>> 56);
      int count8 = (int)((((long)byteValue << 48 | (long)count6 << 32 >>> 16 | (long)character << 48 >>> 48) ^ 0x5159a89419adL ^ 0x482fd7c19bd6L) << 8 >>> 32);
      int count9 = (int)(keySalt << 40 >>> 40);
      CoreDoubleEntry[] coreDoubleEntryArray = GeometryRanker.resolveCoreDoubleEntryArrayByListener72Entry(this, player, livingEntity, count, count2, count3, posZ, 0);
      CoreDoubleEntry[] coreDoubleEntryArray2 = GeometryRanker.resolveCoreDoubleEntryArrayByListener72Entry(this, player, livingEntity, count, count2, count3, posZ, 1);
      if (coreDoubleEntryArray != null && coreDoubleEntryArray2 != null) {
         Vec3 vec = this.resolveVec3(player, count, count2);
         byte byteValue2 = (byte)count7;
         Vec3 vec2 = this.resolveVecByEntityLivingBase(livingEntity, count, byteValue2, count2, count8, count9);
         AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(vec2, true);
         double offset = Double.NaN;
         AxisAlignedBB boundingBox = axisAlignedBB;
         double[] offsetArray = TrajectoryPredictor.resolveDoubleArray(vec, boundingBox, posZ, offset);
         offsetArray[0] = MathHelper.getDouble4(offsetArray[0]);
         offsetArray[1] = MathHelper.getDouble4(offsetArray[1]);
         this.factor = this.factor + this.factor2;
         if (this.factor > 1000.0F) {
            this.factor -= 1000.0F;
         }

         double offset2 = 1.0 + PlayerUtil.getDouble(posX, posY, (double)Math.abs(RecordNode16Helper.computeFactor(this.factor, 0.0F)));
         double offset3 = offsetArray[0];
         CoreDoubleEntry coreDoubleEntry = this.resolveCoreDoubleEntry(coreDoubleEntryArray, offset3, offset2);
         double offset4 = offsetArray[1];
         CoreDoubleEntry coreDoubleEntry2 = this.resolveCoreDoubleEntry(coreDoubleEntryArray2, offset4, offset2);
         return new CoreDoubleEntry[]{coreDoubleEntry, coreDoubleEntry2};
      } else {
         return null;
      }
   }

   public Vec3 resolveVec3(EntityLivingBase livingEntity, int count, int count2) {
      long value = 0x4769482aa678L;
      long keySalt = 0x5159a89419adL ^ value ^ 0x7509fba1fe06L;
      int count3 = (int)((0x5159a89419adL ^ value ^ 0x7509fba1fe06L) >>> 56);
      int count4 = (int)((0x5159a89419adL ^ value ^ 0x7509fba1fe06L) << 8 >>> 32);
      int count5 = (int)(keySalt << 40 >>> 40);
      if (livingEntity == MinecraftAccessor.minecraft.thePlayer) {
         EntityPlayerSP player = MinecraftAccessor.minecraft.thePlayer;
         return this.resolveVec3(player, count, count2);
      } else {
         byte byteValue = (byte)count3;
         return this.resolveVecByEntityLivingBase(livingEntity, count, byteValue, count2, count4, count5);
      }
   }

   public Listener72Entry() {
      this.factor2 = 0.0F;
   }
}
