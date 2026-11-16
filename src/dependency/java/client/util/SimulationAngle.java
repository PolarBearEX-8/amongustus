package client.util;

import client.ClientGlobals;
import client.event.EventMove;
import client.rotation.Rotation;
import client.setting.ArrowDodgerPhase;
import client.setting.SettingContainer;
import java.util.ArrayList;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class SimulationAngle implements MinecraftAccessor {
   public float factor2;
   public static final int count5 = 23;
   public float factor4;
   public int count = 3;
   public float factor;
   public int count2;
   public EntityLivingBase entityLivingBase;
   public float factor3;
   public int count4 = 5;
   public double value;
   public Rotation rotation;
   public double value2;
   public int count3;
   public Rotation rotation2;

   private Rotation resolveRotationByEntityLivingBase(
      EntityLivingBase livingEntity, short shortValue, Vec3 vec, Vec3 vec2, Vec3 vec3, Vec3 vec4, Rotation rotation3, Rotation rotation4
   ) {
      long value3 = 722652094L;
      short shortValue2 = 11347;
      long keySalt = (value3 << 16 | (long)shortValue2 << 48 >>> 48) ^ 0x4bb5f73617fdL;
      long keySalt2 = (value3 << 16 | (long)shortValue2 << 48 >>> 48) ^ 0x4bb5f73617fdL ^ 0x7c5bcda499bL;
      int count6 = (int)((keySalt ^ 0x7c5bcda499bL) >>> 32);
      int count7 = (int)(keySalt2 << 32 >>> 48);
      int count8 = (int)(keySalt2 << 48 >>> 48);
      int count9 = (int)((keySalt ^ 0x7f4588f3cf74L) << 32 >>> 32);
      float yaw = rotation4.yaw;
      Vec3 vec32 = SimulatedPlayer.getVec3(livingEntity, 0, 100);
      float factor5 = 180.0F;
      float factor6 = 180.0F;
      Rotation rotation5 = new Rotation(yaw, SimulatedPlayer.getRotation(vec32, vec3, rotation4, factor6, factor5).pitch);
      double offset = livingEntity.hurtTime >= 5 && MinecraftAccessor.minecraft.thePlayer.hurtTime <= 5 ? 0.2 : -0.3;
      double offset2 = 7.0;
      Rotation rotation6 = rotation5;
      float factor7 = 1.0F;
      MovingObjectPosition movingObjectPosition = RayTraceUtils.getMovingObjectPosition(factor7, rotation6, offset2, offset);
      if (movingObjectPosition != null && movingObjectPosition.typeOfHit == MovingObjectType.ENTITY) {
         Vec3 vec33 = CombatTargetFilter.getVec3(count9, livingEntity, 1);
         double posZ = vec33.zCoord;
         double posY = vec33.yCoord;
         double posX = vec33.xCoord;
         AxisAlignedBB boundingBox = SimulatedPlayer.processAction(livingEntity, posX, posY, posZ).resolveAxisAlignedBB(0.3, 0.3, 0.3);
         double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(0, MinecraftAccessor.minecraft.thePlayer);
         Vec3 vec34 = new Vec3(coordOffsetArray[0], coordOffsetArray[1], coordOffsetArray[2]).getVec312(0.0, (double)MinecraftAccessor.minecraft.thePlayer.getHeight2(), 0.0);
         EntityPlayerSP player = MinecraftAccessor.minecraft.thePlayer;
         float yaw2 = rotation4.yaw;
         Vec3 vec5 = SimulatedPlayer.processAction(vec34, boundingBox, 0, 100);
         float factor8 = 180.0F;
         float factor9 = 180.0F;
         Vec3 vec35 = player.getVec33(1.0F, yaw2, SimulatedPlayer.getRotation(vec5, vec34, rotation4, factor9, factor8).pitch);
         Vec3 vec36 = vec34.getVec312(vec35.xCoord * 7.0, vec35.yCoord * 7.0, vec35.zCoord * 7.0);
         if (boundingBox.calculateIntercept(vec34, vec36) != null || livingEntity.hurtTime < 5 || MinecraftAccessor.minecraft.thePlayer.hurtTime > 5) {
            rotation3.yaw = MathHelper.sqrt_float(rotation4.yaw);
         }
      }

      short shortValue3 = (short)count7;
      short shortValue4 = (short)count8;
      short shortValue5 = shortValue3;
      float pitch = rotation3.pitch;
      float yaw3 = rotation3.yaw;
      this.applyEntityLivingBase(livingEntity, yaw3, count6, pitch, shortValue5, shortValue4);
      float yaw4 = rotation4.yaw;
      float yaw5 = rotation3.yaw;
      float factor10 = this.factor2;
      float factor11 = this.factor4;
      float floatItem = SettingContainer.settingProvider.getFloat2(factor11, factor10);
      yaw = ClientGlobals.rotationManager.getRotation3().yaw - ClientGlobals.rotationManager.getRotation().yaw;
      float factor12 = this.factor;
      float factor13 = this.factor3;
      float floatItem2 = SettingContainer.settingProvider.getFloat2(factor13, factor12);
      float factor14 = this.factor3;
      float factor15 = this.factor;
      boolean flag = this.value2 != 0.0;
      float factor16 = (float)Math.min((double)Math.abs(this.rotation.yaw - this.rotation2.yaw) * this.value2, (double)this.factor4);
      boolean flag2 = flag;
      float factor17 = factor15;
      float factor18 = factor14;
      float factor19 = floatItem2;
      float factor20 = yaw;
      float factor21 = floatItem;
      float factor22 = yaw5;
      float factor23 = yaw4;
      float floatItem3 = SimulatedPlayer.getFloat10(factor23, factor22, factor21, factor20, factor19, factor18, factor17, flag2, factor16);
      float pitch2 = rotation4.pitch;
      float pitch3 = rotation3.pitch;
      float factor24 = this.factor2;
      float factor25 = this.factor4;
      floatItem = SettingContainer.settingProvider.getFloat2(factor25, factor24);
      yaw = ClientGlobals.rotationManager.getRotation3().pitch - ClientGlobals.rotationManager.getRotation().pitch;
      float factor26 = this.factor;
      float factor27 = this.factor3;
      float floatItem4 = SettingContainer.settingProvider.getFloat2(factor27, factor26);
      float factor28 = this.factor3;
      factor15 = this.factor;
      flag = this.value2 != 0.0;
      float factor29 = (float)Math.min((double)Math.abs(this.rotation.pitch - this.rotation2.pitch) * this.value2, (double)this.factor4);
      boolean flag3 = flag;
      float factor30 = factor15;
      float factor31 = factor28;
      float factor32 = floatItem4;
      float factor33 = yaw;
      float factor34 = floatItem;
      float factor35 = pitch3;
      float factor36 = pitch2;
      float floatItem5 = SimulatedPlayer.getFloat10(factor36, factor35, factor34, factor33, factor32, factor31, factor30, flag3, factor29);
      return new Rotation(MathHelper.sqrt_float(floatItem3), floatItem5);
   }

   private float computeFactor(EntityPlayerSP player, char character, EntityLivingBase livingEntity, int count6, double offset, int count7) {
      byte byteValue = 0;
      int count8 = 1544354833;
      char character2 = '\ub4da';
      if (player.getDistanceToEntity(livingEntity) > 4.0F) {
         return (float)offset;
      } else {
         double posX = player.posX;
         double posY = player.posY;
         double posZ = player.posZ;
         EventMove event = new EventMove(
            player.secondaryFloatEntry.moveStrafe,
            player.secondaryFloatEntry.moveForward,
            player.secondaryFloatEntry.state,
            player.secondaryFloatEntry.state2,
            ArrowDodgerPhase.PRE
         );
         double offset2 = posZ;
         double offset3 = posY;
         double offset4 = posX;
         ArrayList list = CombatUtil.onMove(5, player, offset4, offset3, offset2, event);
         int size = list.size();
         ArrayList list2 = CombatTargetFilter.resolveArrayListByEntityLivingBase(livingEntity, size);
         list.add(player.getVec32(1.0F));
         list2.add(livingEntity.getVec32(1.0F));
         int count9 = Math.min(list.size(), list2.size());
         double[][] offsetMatrix = new double[count9][];
         ArrayList list3 = new ArrayList();

         for (int index = 0; index < count9; index++) {
            double doubleItem = MathHelper.getDouble7((double)(player.getDistanceToEntity(livingEntity) - 3.0F) / 3.0, 0.0, 1.0);
            AxisAlignedBB boundingBox = HitBoxUtils.getAxisAlignedBB((Vec3)list2.get(index), false).offset(doubleItem, doubleItem, doubleItem);
            double[] coordOffsetArrayByVec = TrajectoryPredictor.getCoordOffsetArrayByVec3(((Vec3)list.get(index)).getVec312(0.0, (double)player.getHeight2(), 0.0), boundingBox);
            offsetMatrix[index] = coordOffsetArrayByVec;
            list3.add(coordOffsetArrayByVec[0]);
            list3.add(coordOffsetArrayByVec[1]);
         }

         double offset5 = offset;
         double offset6 = offset;
         double offset7 = 0.0;
         int count10 = -1;
         int count11 = 0;

         for (int index2 = 0; index2 < list3.size(); index2++) {
            double offset8 = (Double)list3.get(index2);
            int count12 = 0;

            for (double[] offsetArray : offsetMatrix) {
               if (offset8 >= offsetArray[0] && offset8 <= offsetArray[1]) {
                  count12++;
               }
            }

            if (count12 > count10 || count12 == count10 && Math.abs(offset - offset8) < offset7) {
               offset5 = offset8;
               offset7 = Math.abs(offset - offset8);
               count10 = count12;
               count11 = index2;
            }
         }

         double offset9 = 0.0;
         int count13 = -1;

         for (int index3 = 0; index3 < list3.size(); index3++) {
            if (index3 != count11) {
               double offset10 = (Double)list3.get(index3);
               if (offset10 != offset5) {
                  int count14 = 0;

                  for (double[] offsetArray2 : offsetMatrix) {
                     if (offset10 >= offsetArray2[0] && offset10 <= offsetArray2[1]) {
                        count14++;
                     }
                  }

                  if (count14 > count13 || count14 == count13 && Math.abs(offset5 - offset10) < offset9) {
                     offset6 = offset10;
                     offset9 = Math.abs(offset5 - offset10);
                     count13 = count14;
                     count11 = index3;
                  }
               }
            }
         }

         double[] offsetArray3 = new double[]{Math.min(offset5, offset6), Math.max(offset5, offset6)};
         return (float)MathHelper.getDouble7(offset, offsetArray3[0], offsetArray3[1]);
      }
   }

   private Rotation resolveRotationByEntityLivingBase(EntityLivingBase livingEntity, Rotation rotation3, Rotation rotation4, int count6) {
      double offset = Math.abs(HitBoxUtils.getDouble((double)rotation3.yaw, (double)rotation4.yaw));
      double offset2 = (double)Math.abs(rotation3.pitch - rotation4.pitch);
      if (!(offset2 > 0.075) && !(offset > 0.075) || !(offset < 0.075) && !(offset2 < 0.075) && !(offset / offset2 < 0.1) && !(offset2 / offset < 0.1)) {
         return rotation3;
      } else {
         double offset3 = 0.6;
         double offset4 = -0.6;
         float factor5 = rotation3.yaw = rotation3.yaw + SettingContainer.settingProvider.getFloat3(offset4, offset3);
         double offset5 = 0.6;
         double offset6 = -0.6;
         return new Rotation(factor5, rotation3.pitch = rotation3.pitch + SettingContainer.settingProvider.getFloat3(offset6, offset5));
      }
   }

   private Rotation resolveRotationByEntityLivingBase(EntityLivingBase livingEntity, Vec3 vec, Vec3 vec2, Vec3 vec3, Vec3 vec4, Rotation rotation3) {
      long value3 = 0x141131fd15edL;
      long keySalt = 0x4bb5f73617fdL ^ value3 ^ 0x3a83adab6caL;
      int count6 = (int)((0x4bb5f73617fdL ^ value3 ^ 0x3a83adab6caL) >>> 48);
      int count7 = (int)((0x4bb5f73617fdL ^ value3 ^ 0x3a83adab6caL) << 16 >>> 32);
      int count8 = (int)(keySalt << 48 >>> 48);
      double posZ = vec2.zCoord;
      double posY = vec2.yCoord;
      double posX = vec2.xCoord;
      AxisAlignedBB boundingBox = SimulatedPlayer.processAction(livingEntity, posX, posY, posZ);
      Vec3 vec32 = SimulatedPlayer.processAction(vec, boundingBox, 100, 20).getVec311(vec);
      double posZ2 = vec4.zCoord;
      double posY2 = vec4.yCoord;
      double posX2 = vec4.xCoord;
      AxisAlignedBB boundingBox2 = SimulatedPlayer.processAction(livingEntity, posX2, posY2, posZ2);
      Vec3 vec33 = SimulatedPlayer.processAction(vec, boundingBox2, 100, 20).getVec311(vec);
      float factor5 = (float)(MathHelper.getDouble6(vec32.zCoord, vec32.xCoord) * 180.0 / Math.PI - 90.0);
      MathHelper.getDouble6(vec33.yCoord, (double)MathHelper.sqrt_double(vec33.xCoord * vec33.xCoord + vec33.zCoord * vec33.zCoord));
      char character = (char)count6;
      double pitch = (double)rotation3.pitch;
      char character2 = character;
      EntityPlayerSP player = MinecraftAccessor.minecraft.thePlayer;
      return new Rotation(factor5, this.computeFactor(player, character2, livingEntity, count7, pitch, count8));
   }

   private Vec3 resolveVec3(short shortValue, char character, int count6, EntityLivingBase livingEntity, int count7, int count8) {
      if (livingEntity != MinecraftAccessor.minecraft.thePlayer) {
         BacktrackRecord backtrackRecord = livingEntity.backtrackRecord3;
         if (backtrackRecord.getItemList().isEmpty()) {
            return livingEntity.getVec32(1.0F);
         } else {
            Vec3Entry vec3Entry;
            if (!backtrackRecord.canExecute()) {
               vec3Entry = (Vec3Entry)backtrackRecord.getItemList().get(backtrackRecord.getItemList().size() - 1);
            } else {
               int count9 = Math.max(0, 23 - count7 - 1 + count8);
               vec3Entry = (Vec3Entry)backtrackRecord.getObject(count9);
            }

            return CombatTargetFilter.resolveVecByEntityLivingBase(livingEntity, vec3Entry, count7);
         }
      } else {
         int count10 = livingEntity.backtrackRecord4.getInt() - count7 - 1;
         if (count10 < livingEntity.backtrackRecord4.getInt() && count10 >= 0) {
            ArrayList list;
            for (list = new ArrayList(); count10 < livingEntity.backtrackRecord4.getInt(); count10++) {
               list.add(livingEntity.backtrackRecord4.getObject(count10));
            }

            double[] offsetArray = CombatUtil.resolveDoubleArray((EntityPlayerSP)livingEntity, list, count7, false);
            return new Vec3(offsetArray[0], offsetArray[1], offsetArray[2]);
         } else {
            return livingEntity.getVec32(1.0F);
         }
      }
   }

   public void updateState() {
      this.entityLivingBase = null;
   }

   public Rotation resolveRotation(
      int count6, EntityLivingBase livingEntity, int count7, EntityPlayerSP player, char character, float factor5, float factor6, double offset, double offset2, Vec3 vec, Vec3 vec2
   ) {
      short shortValue = 14220;
      char character2 = '\u8eab';
      short shortValue2 = 18561;
      long keySalt = ((long)shortValue << 32 | (long)character2 << 48 >>> 32 | (long)shortValue2 << 48 >>> 48) ^ 0x4bb5f73617fdL;
      int count8 = (int)((((long)shortValue << 32 | (long)character2 << 48 >>> 32 | (long)shortValue2 << 48 >>> 48) ^ 0x4bb5f73617fdL ^ 0x780cbe77c2f0L) >>> 32);
      int count9 = (int)((keySalt ^ 0x572bb223732fL) << 48 >>> 48);
      if (livingEntity == null) {
         return new Rotation(factor5, factor6);
      } else {
         Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec35(1.0F);
         Vec3 vec32 = livingEntity.getVec32(1.0F);
         Vec3 vec4 = new Vec3(0.0, 0.0, 0.0);

         for (int index = this.count2; index <= this.count3; index++) {
            EntityPlayerSP player2 = MinecraftAccessor.minecraft.thePlayer;
            vec4 = vec4.getVec36(this.resolveVecByEntityLivingBase(player2, index).getVec3(1.0 / (double)(this.count3 - this.count2 + 1)));
         }

         Vec3 vec33 = vec4.getVec312(0.0, (double)MinecraftAccessor.minecraft.thePlayer.getHeight2(), 0.0);
         Vec3 vec5 = new Vec3(0.0, 0.0, 0.0);

         for (int index2 = this.count; index2 <= this.count4; index2++) {
            vec5 = vec5.getVec36(this.resolveVecByEntityLivingBase(livingEntity, index2).getVec3(1.0 / (double)(this.count4 - this.count + 1)));
         }

         double value3 = this.value;
         Vec3 vec6 = this.resolveVec3(vec32, vec3, vec5, vec33, value3);
         Rotation rotation3 = new Rotation(factor5, factor6);
         Rotation rotation4 = this.resolveRotationByEntityLivingBase(livingEntity, vec33, vec6, vec3, vec32, rotation3);
         short shortValue3 = (short)count9;
         Rotation rotation5 = new Rotation(factor5, factor6);
         short shortValue4 = shortValue3;
         Rotation rotation6 = this.resolveRotationByEntityLivingBase(livingEntity, shortValue4, vec33, vec6, vec3, vec32, rotation4, rotation5);
         this.entityLivingBase = livingEntity;
         Rotation rotation7 = new Rotation(MathHelper.sqrt_float(factor5), factor6);
         return this.resolveRotationByEntityLivingBase(livingEntity, rotation6, rotation7, count8);
      }
   }

   private void applyEntityLivingBase(EntityLivingBase livingEntity, float factor5, int count6, float factor6, short shortValue, short shortValue2) {
      if (this.entityLivingBase != null && livingEntity != null && livingEntity.getEntityId() == this.entityLivingBase.getEntityId()) {
         this.rotation = new Rotation(this.rotation2);
         this.rotation2 = new Rotation(factor5, factor6);
      } else {
         this.rotation = new Rotation(factor5, factor6);
         this.rotation2 = new Rotation(factor5, factor6);
      }
   }

   private Vec3 resolveVec3(Vec3 vec, Vec3 vec2, Vec3 vec3, Vec3 vec4, double offset) {
      Vec3 vec32 = vec.getVec311(vec2);
      Vec3 vec33 = vec3.getVec311(vec4);
      vec32.yCoord = 0.0;
      vec33.yCoord = 0.0;
      Vec3 vec5 = null;
      if (vec33.lengthVector() < offset) {
         for (double offset2 = vec32.lengthVector(); offset2 < 10.0; offset2 += 0.1) {
            double offset3 = offset2 / vec32.lengthVector();
            Vec3 vec34 = vec32.getVec3(offset3).getVec36(vec2);
            Vec3 vec35 = vec3.getVec311(vec);
            vec5 = vec34.getVec36(vec35);
            if (vec5.getVec311(vec4).lengthVector() > offset) {
               break;
            }
         }
      }

      if (vec5 == null) {
         vec5 = vec3;
      }

      vec5.yCoord = vec3.yCoord;
      return vec5;
   }

   private Vec3 resolveVecByEntityLivingBase(EntityLivingBase livingEntity, int count6) {
      long value3 = 0x7bbf066e876bL;
      long keySalt = 0x4bb5f73617fdL ^ value3 ^ 0x789d5aa99007L;
      int count7 = (int)((0x4bb5f73617fdL ^ value3 ^ 0x789d5aa99007L) >>> 48);
      int count8 = (int)((0x4bb5f73617fdL ^ value3 ^ 0x789d5aa99007L) << 16 >>> 48);
      int count9 = (int)(keySalt << 32 >>> 32);
      short shortValue = (short)count7;
      char character = (char)count8;
      short shortValue2 = shortValue;
      return this.resolveVec3(shortValue2, character, count9, livingEntity, count6, 0);
   }

   public SimulationAngle() {
      this.count2 = 4;
      this.count3 = 4;
      this.factor3 = 3.0F;
      this.factor = 6.0F;
      this.factor4 = 20.0F;
      this.factor2 = 30.0F;
      this.value = 1.5;
      this.value2 = 0.0;
      this.rotation = new Rotation();
      this.rotation2 = new Rotation();
      this.entityLivingBase = null;
   }
}
