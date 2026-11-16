package client.setting;

import client.render.BoundingBoxDimension;
import client.util.ArrayListData;
import client.util.BacktrackRecord;
import client.util.GenericDoubleEntry;
import client.util.HitBoxUtils;
import client.util.MinecraftAccessor;
import client.util.PositionHistory;
import client.util.SimulatedPlayer;
import client.util.SimulationSnapshot;
import client.util.TrajectoryPredictor;
import java.util.ArrayList;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class NumberSettingEntry implements MinecraftAccessor {
   public NumberSetting settingTargetMovementConstant;
   public int count;
   public ArrayListData arrayListData = new ArrayListData(10);
   public NumberSetting settingPlayerMovementConstant;
   public NumberSetting settingMinInaccuracyForFilterToApply;
   public NumberSetting settingFilterTimeOffset;
   public int count2;
   public NumberSetting numberSetting;
   public EntityPlayerSP entityPlayerSP;
   public BooleanSettingProperty booleanSettingProperty;

   public ArrayList resolveArrayList(short shortValue, EntityPlayerSP player, int count3, int count4, int count5, int count6) {
      int ticksExisted = player.ticksExisted;
      return PositionHistory.resolveArrayList(player, count3, ticksExisted, 0, count4);
   }

   public Vec3 resolveVec3(EntityPlayerSP player, int count3, char character, int count4, char character2) {
      byte byteValue = 0;
      int count5 = 529488999;
      char character3 = '\udb23';
      BacktrackRecord backtrackRecord = player.backtrackRecord5;
      int intItem = MathHelper.getInt12(player.ticksExisted - count3, 0, Math.max(player.backtrackRecord5.getInt() - 1, 0));
      return ((BoundingBoxDimension)backtrackRecord.getObject3(intItem)).positionVec2;
   }

   public double computeValue(EntityPlayerSP player, short shortValue, int count3, EntityLivingBase livingEntity, short shortValue2, int count4) {
      int count5 = player.ticksExisted + count4;
      int ticksExisted = player.ticksExisted;
      ArrayList list = this.resolveArrayList(player, ticksExisted, count5);
      int count6 = player.ticksExisted + count4;
      int ticksExisted2 = player.ticksExisted;
      ArrayList list2 = this.resolveArrayList(livingEntity, ticksExisted2, count6);
      int count7 = Math.max(list.size(), list2.size());
      double offset = Double.POSITIVE_INFINITY;

      for (int index = 0; index < count7; index++) {
         Vec3 vec = PositionHistory.resolveTrackedPosition(list, index);
         AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(PositionHistory.resolveTrackedPosition(list2, index), true);
         Vec3 vec2 = SimulatedPlayer.processAction(vec.getVec312(0.0, (double)player.getHeight2(), 0.0), axisAlignedBB);
         offset = Math.min(offset, vec2.getVec311(vec).lengthVector());
      }

      return offset;
   }

   public GenericDoubleEntry[] resolveGenericDoubleEntryArrayByEntityPlayerSP(
      EntityPlayerSP player, EntityLivingBase livingEntity, int count3, int count4, int count5, int count6
   ) {
      int count7 = count6 + this.count2;
      ArrayList list = this.resolveArrayList(livingEntity, count3, count7);
      int count8 = count6 + this.count;
      ArrayList list2 = PositionHistory.resolveArrayList(player, count3, count4, count5, count8);
      boolean flag = false;
      double offset = 1.0;
      Vec3 vec = PositionHistory.resolveTrackedPosition(list, 0);
      Vec3 vec2 = PositionHistory.resolveTrackedPosition(list2, 0);
      int count9 = 0;
      double offset2 = Double.POSITIVE_INFINITY;

      for (int index = 0; index < Math.max(list.size(), list2.size()); count9 = index++) {
         Vec3 vec3 = PositionHistory.resolveTrackedPosition(list, index);
         Vec3 vec4 = PositionHistory.resolveTrackedPosition(list2, index);
         if (vec3.getVec311(vec4).lengthVector() < offset) {
            flag = true;
            break;
         }

         offset2 = Math.min(offset2, vec3.getVec311(vec4).lengthVector());
         vec = vec3;
         vec2 = vec4;
      }

      boolean flag2 = vec.getVec311(vec2).lengthVector() < offset;
      int count10 = count9 - 1;
      Vec3 vec5 = PositionHistory.resolveTrackedPosition(list, count10);
      int count11 = count9 - 1;
      Vec3 vec6 = PositionHistory.resolveTrackedPosition(list2, count11);
      AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(vec, false);
      double[] coordOffsetArrayByVec = TrajectoryPredictor.getCoordOffsetArrayByVec3(vec2, axisAlignedBB);
      AxisAlignedBB axisAlignedBB2 = HitBoxUtils.getAxisAlignedBB(vec5, false);
      double[] coordOffsetArrayByVec2 = TrajectoryPredictor.getCoordOffsetArrayByVec3(vec6, axisAlignedBB2);
      double offset3 = coordOffsetArrayByVec[0];
      double offset4 = coordOffsetArrayByVec[1];
      double doubleItem = HitBoxUtils.getDouble(MathHelper.getDouble4(coordOffsetArrayByVec2[0]), offset3);
      double doubleItem2 = HitBoxUtils.getDouble(MathHelper.getDouble4(coordOffsetArrayByVec2[1]), offset4);
      if (flag) {
         doubleItem = 0.0;
         doubleItem2 = 0.0;
      }

      double offset5 = coordOffsetArrayByVec2[0];
      double offset6 = coordOffsetArrayByVec2[1];
      GenericDoubleEntry genericDoubleEntry = new GenericDoubleEntry(vec2, vec, doubleItem, 0.0, offset3, offset5, 1);
      GenericDoubleEntry genericDoubleEntry2 = new GenericDoubleEntry(vec2, vec, doubleItem2, 0.0, offset4, offset6, 1);
      genericDoubleEntry.enabled2 = genericDoubleEntry2.enabled2 = flag;
      genericDoubleEntry.enabled = genericDoubleEntry2.enabled = flag2;
      return new GenericDoubleEntry[]{genericDoubleEntry, genericDoubleEntry2};
   }

   public ArrayList resolveArrayList(EntityLivingBase livingEntity, int count3, int count4) {
      if (livingEntity instanceof EntityPlayerSP) {
         short shortValue = (short)0;
         EntityPlayerSP player = (EntityPlayerSP)livingEntity;
         short shortValue2 = shortValue;
         return this.resolveArrayList(shortValue2, player, count3, count4, 1218053952, 49718);
      } else {
         return PositionHistory.resolveArrayList(this.entityPlayerSP, livingEntity, count3, count4);
      }
   }

   public Vec3 resolveVec2(EntityLivingBase livingEntity, int count3) {
      BacktrackRecord backtrackRecord = livingEntity.backtrackRecord;
      int intItem = MathHelper.getInt12(this.entityPlayerSP.ticksExisted - count3, 0, Math.max(livingEntity.backtrackRecord.getInt() - 1, 0));
      return (Vec3)backtrackRecord.getObject3(intItem);
   }

   private static double computeValue(double[] offsetArray, double[] offsetArray2) {
      double offset = 0.5;
      double doubleItem = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(offsetArray, offset));
      double offset2 = 0.5;
      return TrajectoryPredictor.getDouble4(doubleItem, MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(offsetArray2, offset2)));
   }

   public Vec3 resolveVecByEntityLivingBase(EntityLivingBase livingEntity, int count3) {
      if (livingEntity instanceof EntityPlayerSP) {
         EntityPlayerSP player = (EntityPlayerSP)livingEntity;
         char character = 0;
         char character2 = 56099;
         char character3 = character;
         EntityPlayerSP player2 = player;
         return this.resolveVec3(player2, count3, character3, 529488999, character2);
      } else {
         return this.resolveVec2(livingEntity, count3);
      }
   }

   public GenericDoubleEntry[] resolveGenericDoubleEntryArray(EntityPlayerSP player, EntityLivingBase livingEntity, int count3, int count4, int count5, int count6) {
      int count7 = count6;
      int count11 = count6 + this.count2 + (int)this.settingTargetMovementConstant.getDouble2(true);
      ArrayList list = this.resolveArrayList(livingEntity, count3, count11);
      int count12 = count6 + this.count + (int)this.settingPlayerMovementConstant.getDouble2(true);
      ArrayList list2 = PositionHistory.resolveArrayList(player, count3, count4, count5, count12);
      int count13 = list2.size() - 1;
      Vec3 vec = PositionHistory.resolveTrackedPosition(list2, count13);
      int count14 = list.size() - 1;
      Vec3 vec2 = PositionHistory.resolveTrackedPosition(list, count14);
      AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(vec2, false);
      double[] coordOffsetArray = TrajectoryPredictor.getCoordOffsetArray2(vec, axisAlignedBB);
      double offset = 0.5;
      double doubleItem = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(coordOffsetArray, offset));
      Vec3 vec3 = player.getVec35(1.0F);
      AxisAlignedBB axisAlignedBB2 = HitBoxUtils.getAxisAlignedBB(livingEntity.getVec32(1.0F), false);
      double[] coordOffsetArray2 = TrajectoryPredictor.getCoordOffsetArray2(vec3, axisAlignedBB2);
      double offset2 = 0.5;
      double doubleItem2 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(coordOffsetArray2, offset2));
      double doubleItem3 = TrajectoryPredictor.getDouble4(doubleItem, doubleItem2);
      boolean flag = false;
      if (doubleItem3 > this.settingMinInaccuracyForFilterToApply.getDouble2(true)) {
         for (int index = count3; index <= count7; index++) {
            int count15 = index - count3;
            Vec3 vec4 = PositionHistory.resolveTrackedPosition(list2, count15);
            int count16 = index - count3;
            Vec3 vec5 = PositionHistory.resolveTrackedPosition(list, count16);
            int count17 = index + 1;
            char character = 0;
            char character2 = 56099;
            char character3 = character;
            int count18 = count17;
            Vec3 vec6 = this.resolveVec3(player, count18, character3, 529488999, character2);
            int count19 = index + 1;
            Vec3 vec7 = this.resolveVec2(livingEntity, count19);
            SimulationSnapshot simulationSnapshot = new SimulationSnapshot(vec4.xCoord, vec4.zCoord);
            SimulationSnapshot simulationSnapshot2 = new SimulationSnapshot(vec5.xCoord, vec5.zCoord);
            SimulationSnapshot simulationSnapshot3 = new SimulationSnapshot(vec6.xCoord, vec6.zCoord);
            SimulationSnapshot simulationSnapshot4 = new SimulationSnapshot(vec7.xCoord, vec7.zCoord);
            SimulationSnapshot simulationSnapshot5 = simulationSnapshot.resolveSimulationSnapshot(simulationSnapshot2);
            SimulationSnapshot simulationSnapshot6 = simulationSnapshot3.resolveSimulationSnapshot(simulationSnapshot4);
            if (simulationSnapshot5.computeValueBySimulationSnapsh(simulationSnapshot6) > this.numberSetting.getDouble2(true)) {
               flag = true;
               count7 = Math.min(index + (int)this.settingFilterTimeOffset.getDouble2(true), count7);
            }
         }
      }

      int count20 = count7 + this.count2 + (int)this.settingTargetMovementConstant.getDouble2(true) - 1;
      ArrayList list3 = this.resolveArrayList(livingEntity, count3, count20);
      int count21 = count7 + this.count + (int)this.settingPlayerMovementConstant.getDouble2(true) - 1;
      ArrayList list4 = PositionHistory.resolveArrayList(player, count3, count4, count5, count21);
      int count22 = count7 + this.count2 + (int)this.settingTargetMovementConstant.getDouble2(true);
      ArrayList list5 = this.resolveArrayList(livingEntity, count3, count22);
      int count23 = count7 + this.count + (int)this.settingPlayerMovementConstant.getDouble2(true);
      ArrayList list6 = PositionHistory.resolveArrayList(player, count3, count4, count5, count23);
      double offset3 = 1.0;
      Vec3 vec8 = PositionHistory.resolveTrackedPosition(list5, 0);
      Vec3 vec9 = PositionHistory.resolveTrackedPosition(list6, 0);
      int count24 = 0;
      double offset4 = Double.POSITIVE_INFINITY;

      for (int index2 = 0; index2 < Math.max(list5.size(), list6.size()); count24 = index2++) {
         Vec3 vec10 = PositionHistory.resolveTrackedPosition(list5, index2);
         Vec3 vec11 = PositionHistory.resolveTrackedPosition(list6, index2);
         if (vec10.getVec311(vec11).sqrt() < offset3) {
            flag = true;
            break;
         }

         offset4 = Math.min(offset4, vec10.getVec311(vec11).sqrt());
         vec8 = vec10;
         vec9 = vec11;
      }

      boolean flag2 = offset4 < offset3;
      int count25 = count24 - 1;
      Vec3 vec12 = PositionHistory.resolveTrackedPosition(list3, count25);
      int count26 = count24 - 1;
      Vec3 vec13 = PositionHistory.resolveTrackedPosition(list4, count26);
      AxisAlignedBB axisAlignedBB3 = HitBoxUtils.getAxisAlignedBB(vec8, false);
      double[] coordOffsetArray3 = TrajectoryPredictor.getCoordOffsetArray2(vec9, axisAlignedBB3);
      AxisAlignedBB axisAlignedBB4 = HitBoxUtils.getAxisAlignedBB(vec12, false);
      double[] coordOffsetArray4 = TrajectoryPredictor.getCoordOffsetArray2(vec13, axisAlignedBB4);
      double doubleItem4 = MathHelper.getDouble4(coordOffsetArray3[0]);
      double doubleItem5 = MathHelper.getDouble4(coordOffsetArray3[1]);
      double doubleItem6 = HitBoxUtils.getDouble(MathHelper.getDouble4(coordOffsetArray4[0]), doubleItem4);
      double doubleItem7 = HitBoxUtils.getDouble(MathHelper.getDouble4(coordOffsetArray4[1]), doubleItem5);
      if (flag) {
         doubleItem6 = 0.0;
         doubleItem7 = 0.0;
      }

      double doubleItem8 = MathHelper.getDouble4(coordOffsetArray4[0]);
      double doubleItem9 = MathHelper.getDouble4(coordOffsetArray4[1]);
      GenericDoubleEntry genericDoubleEntry = new GenericDoubleEntry(vec9, vec8, doubleItem6, 0.0, doubleItem4, doubleItem8, 1);
      GenericDoubleEntry genericDoubleEntry2 = new GenericDoubleEntry(vec9, vec8, doubleItem7, 0.0, doubleItem5, doubleItem9, 1);
      genericDoubleEntry.enabled2 = genericDoubleEntry2.enabled2 = flag;
      genericDoubleEntry.enabled = genericDoubleEntry2.enabled = flag2;
      return new GenericDoubleEntry[]{genericDoubleEntry, genericDoubleEntry2};
   }

   public NumberSettingEntry(BooleanSettingProperty booleanSettingProperty2) {
      this.booleanSettingProperty = booleanSettingProperty2;
      NumberSetting setting = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3MinInaccuracyForFilterToApply", 20.0, 10.0, 30.0, 1, booleanSettingProperty2.getSettingGroup());
      String text = "Min inaccuracy for filter to apply";
      this.settingMinInaccuracyForFilterToApply = setting.getNumberSetting(text);
      setting = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3MinInaccuracyForFilterToApplyMoment", 20.0, 10.0, 30.0, 1, booleanSettingProperty2.getSettingGroup());
      String text2 = "Min inaccuracy for filter to apply in the moment";
      this.numberSetting = setting.getNumberSetting(text2);
      setting = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3FilterTimeOffset", 1.0, -2.0, 2.0, 0, booleanSettingProperty2.getSettingGroup());
      String text3 = "Filter time offset";
      this.settingFilterTimeOffset = setting.getNumberSetting(text3);
      setting = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3TargetMovementConstantOffset", 0.0, -3.0, 3.0, 0, booleanSettingProperty2.getSettingGroup());
      String text4 = "Target movement constant";
      this.settingTargetMovementConstant = setting.getNumberSetting(text4);
      setting = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3PlayerMovementConstantOffset", 0.0, -3.0, 3.0, 0, booleanSettingProperty2.getSettingGroup());
      String text5 = "Player movement constant";
      this.settingPlayerMovementConstant = setting.getNumberSetting(text5);
   }
}
