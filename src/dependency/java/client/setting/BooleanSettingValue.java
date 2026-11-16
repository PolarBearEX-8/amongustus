package client.setting;

import client.ClientGlobals;
import client.event.EventMove;
import client.util.AuxiliaryStringEntryProvider;
import client.util.BooleanSupplier;
import client.util.CombatTargetFilter;
import client.util.CombatUtil;
import client.util.CustomStringEntryAdapter;
import client.util.DoubleSupplierEntry;
import client.util.ExtendedStringEntryHandler;
import client.util.HitBoxUtils;
import client.util.InternalStringEntryRegistry;
import client.util.MovingObjectType;
import client.util.PositionHistory;
import client.util.RayTraceUtils;
import client.util.SecondaryStringEntryAdapter;
import client.util.SimulatedPlayer;
import client.util.SimulationSnapshot;
import client.util.SmoothSerializer;
import client.util.SmoothWriter;
import client.util.StringEntryController;
import client.util.TertiaryStringEntryAdapter;
import client.util.TrajectoryPredictor;
import client.util.Vec3Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingBaseEntry;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class BooleanSettingValue {
   public BooleanSettingOption booleanSettingOption2;
   public BooleanSettingProperty booleanSettingProperty;
   public DoubleSupplierEntry doubleSupplierEntry;
   public ArrayList arrayList4;
   public NumberSetting settingUNoise3constantbbsizeoorv;
   public BooleanSettingOption booleanSettingOption5;
   public double value;
   public BooleanSetting settingBoundingBoxSize2;
   public double value6;
   public RangeSettingEntry rangeSettingEntry;
   public InternalNumberSettingEntry internalNumberSettingEntry;
   public ArrayList arrayList2;
   public double value5;
   public RangeSetting settingVertical;
   public ArrayList arrayList3 = new ArrayList();
   public RangeSetting rangeSetting2;
   public ArrayList arrayList;
   public DoubleSupplierEntry doubleSupplierEntry2;
   public double value7;
   public double value4;
   public EntityPlayerSP entityPlayerSP;
   public BooleanSetting settingBoundingBoxSize;
   public double value2;
   public BooleanSettingOption booleanSettingOption3;
   public BooleanSettingOption booleanSettingOption;
   public BooleanSettingOption booleanSettingOption4;
   public DoubleSupplierEntry doubleSupplierEntry3;
   public BooleanSetting settingUNoise3constantvbbsizeoor;
   public ArrayList arrayList5 = new ArrayList();
   public double value3;
   public InternalNumberSettingEntry internalNumberSettingEntry2;
   public NumberSetting settingSize2;
   public BooleanSetting settingUNoise3constantbbsizeoor;
   public RangeSetting rangeSetting;
   public NumberSetting settingUNoise3constantvbbsizeoorv;
   public NumberSetting settingSize;

   public void setEnabled(boolean enabled) {
      ArrayList list = new ArrayList();
      if (enabled) {
         list.addAll(this.arrayList);
         list.addAll(this.arrayList2);
      } else {
         list.addAll(this.arrayList3);
         list.addAll(this.arrayList5);
      }

      double offset = 0.0;
      double offset2 = 0.0;

      for (int index = 0; index < list.size(); index++) {
         SmoothWriter smoothWriter = (SmoothWriter)list.get(index);
         offset += smoothWriter.getCombatChoiceProperty().actionSetting.getDouble5(true);
         offset2 += smoothWriter.getCombatChoiceProperty().actionSetting.getDouble4(true);
      }

      for (int index2 = 0; index2 < list.size(); index2++) {
         SmoothWriter smoothWriter2 = (SmoothWriter)list.get(index2);
         ActionSetting actionSetting = smoothWriter2.getCombatChoiceProperty().actionSetting;
         double offset3 = offset - actionSetting.getDouble5(true);
         double offset4 = offset2 - actionSetting.getDouble4(true);
         double offset5 = actionSetting.getDouble5(true) / (offset4 + actionSetting.getDouble5(true));
         double offset6 = actionSetting.getDouble4(true) / (offset3 + actionSetting.getDouble4(true));
         if (!Double.isFinite(offset5)) {
            offset5 = 0.0;
         }

         if (!Double.isFinite(offset6)) {
            offset6 = 0.0;
         }

         smoothWriter2.applyDouble(offset5, offset6);
      }
   }

   public void applyDouble(double offset, double offset2) {
      this.value2 = this.value = this.value5 = offset;
      this.value4 = this.value6 = this.value7 = offset2;
      this.doubleSupplierEntry3.updateState();
      this.doubleSupplierEntry.updateState();
      this.doubleSupplierEntry2.updateState();
      this.booleanSettingOption4.updateState();
      this.booleanSettingOption2.updateState();
      this.booleanSettingOption5.updateState();
      this.booleanSettingOption3.updateState();
      this.booleanSettingOption.updateState();
      TertiaryStringEntryAdapter.updateState2();
   }

   private double[] resolveDoubleArrayByEntityPlayerSP(EntityPlayerSP player, EntityLivingBase livingEntity, int count, float factor, float factor2) {
      double height = (double)livingEntity.getHeight();
      Vec3 vec3 = player.getVec35(1.0F);
      AxisAlignedBB boundingBox = livingEntity.getBoundingBox2().offset(height, height, height);
      double[] coordOffsetArray = TrajectoryPredictor.getCoordOffsetArray2(vec3, boundingBox);
      double[] coordOffsetArrayByVec = TrajectoryPredictor.getCoordOffsetArrayByVec3(vec3, boundingBox);
      double offset = TrajectoryPredictor.computeValue2(coordOffsetArray[0], coordOffsetArray[1], (double)factor);
      double offset2 = (double)factor2;
      double offset3 = TrajectoryPredictor.computeValue(coordOffsetArrayByVec, offset2);
      double offset4 = Double.MAX_VALUE;
      double offset5 = offset;
      double offset6 = offset3;

      for (int index = 0; index <= 50; index++) {
         for (int index2 = 0; index2 <= 50; index2++) {
            double offset7 = (double)index * 0.02;
            double offset8 = (double)index2 * 0.02;
            double offset9 = offset7 - offset;
            double offset10 = offset8 - offset3;
            double offset11 = offset9 * offset9 + offset10 * offset10;
            if (offset11 < offset4) {
               byte byteValue = (byte)32;
               if (this.matchesEntityLivingBase(livingEntity, offset7, offset8, 28731, 12606589, coordOffsetArray, coordOffsetArrayByVec, byteValue)) {
                  offset4 = offset11;
                  offset5 = offset7;
                  offset6 = offset8;
               }
            }
         }
      }

      return new double[]{TrajectoryPredictor.getCoordinate(coordOffsetArray, offset5), TrajectoryPredictor.getCoordinate(coordOffsetArrayByVec, offset6)};
   }

   private double getValue() {
      return this.rangeSetting.computeValue(true);
   }

   private double[] resolveDoubleArrayByEntityPlayerSP(EntityPlayerSP player, EntityLivingBase livingEntity, float factor, float factor2) {
      MovingObjectPosition hitResult = RayTraceUtils.resolveMovingObjectPosition(1.0F, factor, factor2, 3.0, 3.0, 0.0, true);
      if (hitResult != null && hitResult.typeOfHit == MovingObjectType.ENTITY && hitResult.entityHit == livingEntity) {
         MovingObjectPosition movingObjectPosition = RayTraceUtils.getMovingObjectPosition6(1.0F, factor, factor2);
         if (movingObjectPosition != null && movingObjectPosition.typeOfHit == MovingObjectType.BLOCK) {
            return this.resolveDoubleArrayByEntityPlayerSP(player, livingEntity, 289721952, factor, factor2);
         }
      }

      return new double[]{(double)factor, (double)factor2};
   }

   private double[] resolveDoubleArray(ArrayList list, ArrayList list2, double offset, double offset2) {
      int count = Math.min(list.size(), list2.size());
      double[][] offsetMatrix = new double[count][];
      ArrayList list3 = new ArrayList();

      for (int index = 0; index < count; index++) {
         AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB((Vec3)list2.get(index), true);
         double[] coordOffsetArrayByVec = TrajectoryPredictor.getCoordOffsetArrayByVec3(((Vec3)list.get(index)).getVec312(0.0, offset, 0.0), axisAlignedBB);
         offsetMatrix[index] = coordOffsetArrayByVec;
         list3.add(coordOffsetArrayByVec[0]);
         list3.add(coordOffsetArrayByVec[1]);
      }

      double offset3 = offset2;
      double offset4 = offset2;
      double offset5 = 0.0;
      int count2 = -1;
      int count3 = 0;

      for (int index2 = 0; index2 < list3.size(); index2++) {
         double offset6 = (Double)list3.get(index2);
         int count4 = 0;

         for (double[] offsetArray : offsetMatrix) {
            if (offset6 >= offsetArray[0] && offset6 <= offsetArray[1]) {
               count4++;
            }
         }

         if (count4 > count2 || count4 == count2 && Math.abs(offset2 - offset6) < offset5) {
            offset3 = offset6;
            offset5 = Math.abs(offset2 - offset6);
            count2 = count4;
            count3 = index2;
         }
      }

      double offset7 = 0.0;
      int count5 = -1;

      for (int index3 = 0; index3 < list3.size(); index3++) {
         if (index3 != count3) {
            double offset8 = (Double)list3.get(index3);
            if (offset8 != offset3) {
               int count6 = 0;

               for (double[] offsetArray2 : offsetMatrix) {
                  if (offset8 >= offsetArray2[0] && offset8 <= offsetArray2[1]) {
                     count6++;
                  }
               }

               if (count6 > count5 || count6 == count5 && Math.abs(offset3 - offset8) < offset7) {
                  offset4 = offset8;
                  offset7 = Math.abs(offset3 - offset8);
                  count5 = count6;
                  count3 = index3;
               }
            }
         }
      }

      return new double[]{offset3, offset4};
   }

   private void applyEntityPlayerSPByEntityPlayerSP(EntityPlayerSP player, EntityLivingBase livingEntity, double offset, double offset2) {
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
      double offset3 = posZ;
      double offset4 = posY;
      double offset5 = posX;
      ArrayList list = CombatUtil.onMove(5, player, offset5, offset4, offset3, event);
      int ticksExisted = player.ticksExisted;
      int count4 = player.ticksExisted + list.size();
      int count5 = ticksExisted;
      ArrayList list2 = PositionHistory.resolveArrayList(player, livingEntity, count5, count4);
      list.add(player.getVec32(1.0F));
      list2.add(livingEntity.getVec32(1.0F));
      double height = (double)player.getHeight2();
      double[] offsetArray = this.resolveDoubleArray(list, list2, height, offset);
      double offset6 = offsetArray[0];
      double offset7 = offsetArray[1];
      double[] offsetArray2 = new double[]{Math.min(offset6, offset7), Math.max(offset6, offset7)};
      double doubleItem = MathHelper.getDouble7(offset, offsetArray2[0], offsetArray2[1]);
      this.doubleSupplierEntry.applyTarget(offsetArray2);
      DoubleSupplierEntry doubleSupplierEntry4 = this.doubleSupplierEntry2;
      Double offset8 = doubleItem;
      doubleSupplierEntry4.applyTarget(offset8);
      this.value3 = doubleItem;
      if (offset2 < 3.1 && player.onGround) {
         int size = list.size();
         posX = player.posX;
         posY = player.posY;
         double posZ2 = player.posZ;
         EventMove event2 = new EventMove(
            player.secondaryFloatEntry.moveStrafe,
            player.secondaryFloatEntry.moveForward,
            player.secondaryFloatEntry.state,
            player.secondaryFloatEntry.state2,
            ArrowDodgerPhase.PRE
         );
         double offset9 = 0.25;
         double offset10 = posZ2;
         double offset11 = posY;
         double offset12 = posX;
         ArrayList list3 = CombatUtil.onMove(size, player, offset12, offset11, offset10, offset9, event2);
         list3.add(player.getVec32(1.0F));
         ArrayList list4 = (ArrayList)list3.clone();
         List collidingBoundingBoxes = Minecraft.getMinecraft().theWorld.getCollidingBoundingBoxes(livingEntity, livingEntity.getBoundingBox2().contract(0.0, -0.11125, 0.0));
         double posY2 = livingEntity.posY;
         double offset13 = -0.11125;
         boolean flag = false;
         Iterator iterator = collidingBoundingBoxes.iterator();

         while (iterator.hasNext()) {
            offset13 = ((AxisAlignedBB)iterator.next()).getCoordinate3(livingEntity.getBoundingBox2(), offset13);
            if (offset13 != posY2) {
               flag = true;
               break;
            }
         }

         if (flag && livingEntity.backtrackRecord3.getInt() > 0) {
            Object object = livingEntity.backtrackRecord3.getObject3(0);
            char character = 0;
            Vec3Entry vec3Entry = (Vec3Entry)object;
            char character2 = 63711;
            double offset14 = 0.0;
            double offset15 = 0.25;
            double offset16 = 0.0;
            byte byteValue = 5;
            Vec3Entry vec3Entry2 = vec3Entry;
            char character3 = character;
            ArrayList list5 = CombatTargetFilter.resolveArrayListByEntityLivingBase(livingEntity, character3, vec3Entry2, byteValue, offset16, offset15, offset14, 1912748990, character2);
            list2.addAll(list5);
            list2.add(livingEntity.getVec32(1.0F));
            list3.addAll(list);
            list3.add(player.getVec32(1.0F));
            list2.addAll(list5);
            list2.add(livingEntity.getVec32(1.0F));
            list3.addAll(list4);
            list3.add(player.getVec32(1.0F));
         }

         double height2 = (double)player.getHeight2();
         double[] offsetArray3 = this.resolveDoubleArray(list3, list2, height2, offset);
         double offset17 = offsetArray3[0];
         double offset18 = offsetArray3[1];
         double[] offsetArray4 = new double[]{Math.min(offset17, offset18), Math.max(offset17, offset18)};
         Vec3 vec3 = player.getVec35(1.0F);
         AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(livingEntity.getVec32(1.0F), true);
         double[] coordOffsetArrayByVec = TrajectoryPredictor.getCoordOffsetArrayByVec3(vec3, axisAlignedBB);
         offsetArray4[0] = MathHelper.getDouble7(offsetArray4[0], coordOffsetArrayByVec[0], coordOffsetArrayByVec[1]);
         offsetArray4[1] = MathHelper.getDouble7(offsetArray4[1], coordOffsetArrayByVec[0], coordOffsetArrayByVec[1]);
         this.value3 = MathHelper.getDouble7(offset, offsetArray4[0], offsetArray4[1]);
      }

      DoubleSupplierEntry doubleSupplierEntry5 = this.doubleSupplierEntry3;
      Double value9 = this.value3;
      doubleSupplierEntry5.applyTarget(value9);
   }

   private static int[] countArray;

   public static int[] getCountArray() {
      return null;
   }

   private static boolean matchesDoubleArray(double[] offsetArray, double offset, boolean flag) {
      double offset2;
      if (flag) {
         offset2 = TrajectoryPredictor.computeValue(offsetArray, offset);
      } else {
         offset2 = TrajectoryPredictor.computeValue2(offsetArray[0], offsetArray[1], offset);
      }

      return offset2 >= 0.0 && offset2 <= 1.0;
   }

   public SimulationSnapshot resolveSimulationSnapshotByRangeSettingEntr(
      RangeSettingEntry rangeSettingEntry2,
      EntityPlayerSP player,
      int count,
      EntityLivingBase livingEntity,
      Vec3 vec,
      Vec3 vec2,
      Vec3 vec3,
      Vec3 vec4,
      int count2,
      double posX,
      double posX2,
      double posX3,
      double posY,
      double posZ,
      int count3,
      double offset,
      double offset2
   ) {
      short shortValue = 27989;
      short shortValue2 = 29467;
      short shortValue3 = 3520;
      double value8 = this.value4;
      this.applyEntityPlayerSPByEntityPlayerSP(player, livingEntity, value8, posZ);
      double dataRef = (Double)this.doubleSupplierEntry2.getDataRef();
      double[] dataRef2 = (double[])this.doubleSupplierEntry.getDataRef();
      double dataRef3 = (Double)this.doubleSupplierEntry3.getDataRef();
      boolean flag = Math.abs(offset) > 2.0;
      boolean flag2 = Math.abs(offset2) > 3.0;
      boolean flag3 = flag || flag2;
      double offset3 = 0.0;
      double offset4 = this.booleanSettingOption4.computeValue(offset3);
      boolean flag4 = this.booleanSettingOption4.checkFlag2(false);
      boolean flag5 = false;
      boolean flag6 = this.booleanSettingOption4.checkFlagByFlag(flag5);
      EntityLivingBaseEntry entityLivingBaseEntry = new EntityLivingBaseEntry();
      entityLivingBaseEntry.entityPlayerSP = player;
      entityLivingBaseEntry.entityLivingBase = livingEntity;
      Vec3 vec5;
      entityLivingBaseEntry.vec4 = vec5 = player.getVec35(1.0F);
      Vec3 vec6;
      entityLivingBaseEntry.vec2 = vec6 = livingEntity.getVec32(1.0F);
      Vec3 vec7 = entityLivingBaseEntry.vec4;
      AxisAlignedBB boundingBox;
      AxisAlignedBB boundingBox2 = boundingBox = HitBoxUtils.getAxisAlignedBB(vec6, true);
      Vec3 vec8;
      entityLivingBaseEntry.vec3 = vec8 = SimulatedPlayer.processAction(vec7, boundingBox2);
      entityLivingBaseEntry.axisAlignedBB = boundingBox;
      entityLivingBaseEntry.valueArray = TrajectoryPredictor.getCoordOffsetArray2(vec5, boundingBox);
      AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(vec6, false);
      entityLivingBaseEntry.valueArray3 = TrajectoryPredictor.getCoordOffsetArray2(vec5, axisAlignedBB);
      entityLivingBaseEntry.valueArray2 = dataRef2;
      entityLivingBaseEntry.value3 = dataRef;
      entityLivingBaseEntry.value4 = dataRef3;
      entityLivingBaseEntry.value2 = posX;
      entityLivingBaseEntry.value = this.value2;
      entityLivingBaseEntry.value5 = this.value;
      entityLivingBaseEntry.enabled = false;
      double offset5 = Double.NaN;
      if (posZ > 3.1 && this.settingUNoise3constantbbsizeoor.isEnabled()) {
         offset5 = this.settingUNoise3constantbbsizeoorv.getDouble2(true) / 2.0;
      } else if (this.settingBoundingBoxSize2.isEnabled()) {
         offset5 = this.settingSize2.getDouble2(true) / 2.0;
      }

      if (Double.isFinite(offset5)) {
         double offset6 = 0.5;
         double coordinate = TrajectoryPredictor.getCoordinate(entityLivingBaseEntry.valueArray, offset6);
         double doubleItem = MathHelper.getDouble4(coordinate - offset5);
         double doubleItem2 = MathHelper.getDouble4(coordinate + offset5);
         entityLivingBaseEntry.valueArray = entityLivingBaseEntry.valueArray3 = TrajectoryPredictor.resolveDoubleArray(doubleItem, doubleItem2);
      }

      double offset7;
      searchBlock: {
         offset7 = this.booleanSettingOption2.computeValue(offset4);
         if (!flag3) {
            if (!(offset7 > 0.0) || this.booleanSettingOption2.checkFlagByFlag(flag6)) {
               break searchBlock;
            }

            double value9 = entityLivingBaseEntry.value;
            if (matchesDoubleArray(entityLivingBaseEntry.valueArray, value9, false)) {
               double value10 = entityLivingBaseEntry.value5;
               if (matchesDoubleArray(entityLivingBaseEntry.valueArray, value10, false)) {
                  break searchBlock;
               }
            }
         }

         offset7 = 0.0;
      }

      ArrayList arrayList6 = this.arrayList3;
      boolean flag7 = this.booleanSettingOption2.checkFlag2(flag4);
      boolean flag8 = false;
      ArrayList list = arrayList6;
      ArrayList list2 = this.resolveArrayList(entityLivingBaseEntry, list, offset7, flag8, flag7);
      entityLivingBaseEntry.vec4 = vec.getVec312(0.0, (double)player.getHeight2(), 0.0);
      entityLivingBaseEntry.vec2 = vec2;
      vec7 = entityLivingBaseEntry.vec4;
      AxisAlignedBB boundingBox3 = entityLivingBaseEntry.axisAlignedBB = HitBoxUtils.getAxisAlignedBB(vec2, true);
      entityLivingBaseEntry.vec3 = SimulatedPlayer.processAction(vec7, boundingBox3);
      AxisAlignedBB axisAlignedBB2 = entityLivingBaseEntry.axisAlignedBB;
      entityLivingBaseEntry.valueArray = TrajectoryPredictor.getCoordOffsetArray2(entityLivingBaseEntry.vec4, axisAlignedBB2);
      vec7 = entityLivingBaseEntry.vec4;
      AxisAlignedBB axisAlignedBB3 = HitBoxUtils.getAxisAlignedBB(vec2, false);
      entityLivingBaseEntry.valueArray3 = TrajectoryPredictor.getCoordOffsetArray2(vec7, axisAlignedBB3);
      entityLivingBaseEntry.value5 = this.value5;
      if (Double.isFinite(offset5)) {
         double offset8 = 0.5;
         double coordinate2 = TrajectoryPredictor.getCoordinate(entityLivingBaseEntry.valueArray, offset8);
         double doubleItem3 = MathHelper.getDouble4(coordinate2 - offset5);
         double doubleItem4 = MathHelper.getDouble4(coordinate2 + offset5);
         entityLivingBaseEntry.valueArray = entityLivingBaseEntry.valueArray3 = TrajectoryPredictor.resolveDoubleArray(doubleItem3, doubleItem4);
      }

      double offset9;
      guardBlock: {
         offset9 = this.booleanSettingOption3.computeValue(offset4);
         if (!flag3) {
            if (this.booleanSettingOption3.checkFlagByFlag(flag6) || !(offset9 > 0.0)) {
               break guardBlock;
            }

            double value11 = entityLivingBaseEntry.value;
            if (matchesDoubleArray(entityLivingBaseEntry.valueArray, value11, false)) {
               double value12 = entityLivingBaseEntry.value5;
               if (matchesDoubleArray(entityLivingBaseEntry.valueArray, value12, false)) {
                  break guardBlock;
               }
            }
         }

         offset9 = 0.0;
      }

      arrayList6 = this.arrayList5;
      boolean flag9 = this.booleanSettingOption3.checkFlag2(flag4);
      boolean flag10 = false;
      ArrayList list3 = arrayList6;
      ArrayList list4 = this.resolveArrayList(entityLivingBaseEntry, list3, offset9, flag10, flag9);
      entityLivingBaseEntry.vec4 = vec5;
      entityLivingBaseEntry.vec2 = vec6;
      entityLivingBaseEntry.vec3 = vec8;
      vec7 = entityLivingBaseEntry.vec4;
      AxisAlignedBB boundingBox4 = entityLivingBaseEntry.axisAlignedBB = boundingBox;
      entityLivingBaseEntry.valueArray = TrajectoryPredictor.getCoordOffsetArrayByVec3(vec7, boundingBox4);
      vec7 = entityLivingBaseEntry.vec4;
      AxisAlignedBB axisAlignedBB4 = HitBoxUtils.getAxisAlignedBB(vec6, false);
      entityLivingBaseEntry.valueArray3 = TrajectoryPredictor.getCoordOffsetArrayByVec3(vec7, axisAlignedBB4);
      entityLivingBaseEntry.value2 = posX2;
      entityLivingBaseEntry.value = this.value4;
      entityLivingBaseEntry.value5 = this.value6;
      entityLivingBaseEntry.enabled = true;
      double offset10 = Double.NaN;
      if (posZ > 3.1 && this.settingUNoise3constantvbbsizeoor.isEnabled()) {
         offset10 = this.settingUNoise3constantvbbsizeoorv.getDouble2(true) / 2.0;
      } else if (this.settingBoundingBoxSize.isEnabled()) {
         offset10 = this.settingSize.getDouble2(true) / 2.0;
      }

      if (Double.isFinite(offset10)) {
         double offset11 = 0.5;
         double coordinate3 = TrajectoryPredictor.getCoordinate(entityLivingBaseEntry.valueArray, offset11);
         entityLivingBaseEntry.valueArray = entityLivingBaseEntry.valueArray3 = new double[]{coordinate3 - offset10, coordinate3 + offset10};
      }

      double offset12;
      checkBlock: {
         offset12 = this.booleanSettingOption5.computeValue(offset4);
         if (!flag3) {
            if (!(offset12 > 0.0) || this.booleanSettingOption5.checkFlagByFlag(flag6)) {
               break checkBlock;
            }

            double value13 = entityLivingBaseEntry.value;
            if (matchesDoubleArray(entityLivingBaseEntry.valueArray, value13, true)) {
               double value14 = entityLivingBaseEntry.value5;
               if (matchesDoubleArray(entityLivingBaseEntry.valueArray, value14, true)) {
                  break checkBlock;
               }
            }
         }

         offset12 = 0.0;
      }

      arrayList6 = this.arrayList;
      boolean flag11 = this.booleanSettingOption5.checkFlag2(flag4);
      boolean flag12 = true;
      ArrayList list5 = arrayList6;
      ArrayList list6 = this.resolveArrayList(entityLivingBaseEntry, list5, offset12, flag12, flag11);
      entityLivingBaseEntry.vec4 = vec3.getVec312(0.0, (double)player.getHeight2(), 0.0);
      entityLivingBaseEntry.vec2 = vec4;
      vec7 = entityLivingBaseEntry.vec4;
      AxisAlignedBB boundingBox5 = entityLivingBaseEntry.axisAlignedBB = HitBoxUtils.getAxisAlignedBB(vec4, true);
      entityLivingBaseEntry.vec3 = SimulatedPlayer.processAction(vec7, boundingBox5);
      AxisAlignedBB axisAlignedBB5 = entityLivingBaseEntry.axisAlignedBB;
      entityLivingBaseEntry.valueArray = TrajectoryPredictor.getCoordOffsetArrayByVec3(entityLivingBaseEntry.vec4, axisAlignedBB5);
      AxisAlignedBB axisAlignedBB6 = HitBoxUtils.getAxisAlignedBB(vec4, false);
      entityLivingBaseEntry.valueArray3 = TrajectoryPredictor.getCoordOffsetArrayByVec3(vec5, axisAlignedBB6);
      entityLivingBaseEntry.value5 = this.value7;
      if (Double.isFinite(offset10)) {
         double offset13 = 0.5;
         double coordinate4 = TrajectoryPredictor.getCoordinate(entityLivingBaseEntry.valueArray, offset13);
         entityLivingBaseEntry.valueArray = entityLivingBaseEntry.valueArray3 = new double[]{coordinate4 - offset10, coordinate4 + offset10};
      }

      double offset14;
      evalBlock: {
         offset14 = this.booleanSettingOption.computeValue(offset4);
         if (!flag3) {
            if (!(offset14 > 0.0) || this.booleanSettingOption.checkFlagByFlag(flag6)) {
               break evalBlock;
            }

            double value15 = entityLivingBaseEntry.value;
            if (matchesDoubleArray(entityLivingBaseEntry.valueArray, value15, true)) {
               double value16 = entityLivingBaseEntry.value5;
               if (matchesDoubleArray(entityLivingBaseEntry.valueArray, value16, true)) {
                  break evalBlock;
               }
            }
         }

         offset14 = 0.0;
      }

      arrayList6 = this.arrayList2;
      boolean flag13 = this.booleanSettingOption.checkFlag2(flag4);
      boolean flag14 = true;
      ArrayList list7 = arrayList6;
      ArrayList list8 = this.resolveArrayList(entityLivingBaseEntry, list7, offset14, flag14, flag13);
      double doubleItem5 = MathHelper.getDouble4(computeValue(list2, list4, true));
      double offset15 = computeValue(list6, list8, false);
      if (list2.isEmpty() && list4.isEmpty()) {
         doubleItem5 = MathHelper.getDouble4(posX3);
      }

      if (list6.isEmpty() && list8.isEmpty()) {
         offset15 = posY;
      }

      if (ClientGlobals.moduleManager.killAura.settingSmartAim.isEnabled()) {
         float factor = (float)doubleItem5;
         float factor2 = (float)offset15;
         float factor3 = factor;
         double[] offsetArray = this.resolveDoubleArrayByEntityPlayerSP(player, livingEntity, factor3, factor2);
         doubleItem5 = offsetArray[0];
         offset15 = offsetArray[1];
      }

      if (!Double.isFinite(doubleItem5)) {
         doubleItem5 = posX;
      }

      if (!Double.isFinite(offset15)) {
         offset15 = posX2;
      }

      this.internalNumberSettingEntry2.updateState8();
      this.internalNumberSettingEntry.updateState8();
      double doubleItem6 = MathHelper.getDouble4(doubleItem5 + this.internalNumberSettingEntry2.getValue());
      double doubleItem7 = MathHelper.getDouble7(offset15 + this.internalNumberSettingEntry.getValue(), -90.0, 90.0);
      this.value2 = doubleItem6;
      this.value4 = doubleItem7;
      if (offset7 == 0.0) {
         this.value = this.value2;
      }

      if (offset9 == 0.0) {
         this.value5 = this.value2;
      }

      if (offset12 == 0.0) {
         this.value6 = this.value4;
      }

      if (offset14 == 0.0) {
         this.value7 = this.value4;
      }

      return new SimulationSnapshot(doubleItem6, doubleItem7);
   }

   private static double computeValue(ArrayList list, ArrayList list2, boolean flag) {
      double offset = 0.0;
      double offset2 = 0.0;
      double offset3 = Double.NaN;
      list.addAll(list2);

      for (int index = 0; index < list.size(); index++) {
         SimulationSnapshot simulationSnapshot = (SimulationSnapshot)list.get(index);
         if (flag && Double.isFinite(offset3)) {
            double offset4 = simulationSnapshot.value - offset3;
            if (offset4 > 180.0) {
               simulationSnapshot.value -= 360.0;
            } else if (offset4 < -180.0) {
               simulationSnapshot.value += 360.0;
            }
         }

         offset += simulationSnapshot.value * simulationSnapshot.value2;
         offset2 += simulationSnapshot.value2;
         offset3 = simulationSnapshot.value;
      }

      return offset2 == 0.0 ? 0.0 : offset / offset2;
   }

   private double getValue2() {
      return this.rangeSetting2.computeValue(true);
   }

   private double getValueAsDouble() {
      return this.settingVertical.computeValue(true);
   }

   private ArrayList resolveArrayList(EntityLivingBaseEntry entityLivingBaseEntry, ArrayList list, double offset, boolean flag, boolean flag2) {
      ArrayList list2 = new ArrayList();

      for (SmoothWriter smoothWriter : (Iterable<SmoothWriter>)(Iterable<?>)list) {
         double offset2 = smoothWriter.computeValueByEntityLivingBase(entityLivingBaseEntry);
         if (offset2 != 0.0) {
            if (offset == 0.0 || flag2) {
               smoothWriter.updateState();
            }

            double offset3 = flag ? smoothWriter.computeValue(entityLivingBaseEntry) : MathHelper.getDouble4(smoothWriter.computeValue(entityLivingBaseEntry));
            if (offset > 0.0) {
               list2.add(new SimulationSnapshot(offset3, offset2 * (1.0 - offset)));
               list2.add(new SimulationSnapshot(entityLivingBaseEntry.value5, offset2 * offset));
            } else {
               list2.add(new SimulationSnapshot(offset3, offset2));
            }
         }
      }

      return list2;
   }

   private boolean matchesEntityLivingBase(EntityLivingBase livingEntity, double offset, double offset2, int count, int count2, double[] offsetArray, double[] offsetArray2, byte byteValue) {
      return RayTraceUtils.getMovingObjectPosition6(
               1.0F, (float)TrajectoryPredictor.getCoordinate(offsetArray, offset), (float)TrajectoryPredictor.getCoordinate(offsetArray2, offset2)
            )
            .entityHit
         == livingEntity;
   }

   public BooleanSettingValue(BooleanSettingProperty booleanSettingProperty2, RangeSettingEntry rangeSettingEntry2) {
      this.arrayList = new ArrayList();
      this.arrayList2 = new ArrayList();
      this.arrayList4 = new ArrayList();
      booleanSettingProperty2 = new BooleanSettingProperty(booleanSettingProperty2.getModule(), booleanSettingProperty2.getSettingGroup(), this);
      this.booleanSettingProperty = booleanSettingProperty2;
      this.rangeSettingEntry = rangeSettingEntry2;
      SettingGroup settingGroup = new SettingGroup(booleanSettingProperty2.getModule(), "UNoise3Horizontal", booleanSettingProperty2.getSettingGroup());
      String text = "Horizontal";
      SettingGroup setting = (SettingGroup)settingGroup.getSetting(text);
      BooleanSettingProperty booleanSettingProperty3 = booleanSettingProperty2.resolveBooleanSettingPropertyBySettingGroup(setting);
      this.arrayList3.add(new StringEntryController(booleanSettingProperty3));
      this.arrayList3.add(new SmoothSerializer(booleanSettingProperty3));
      this.arrayList3.add(new InternalBooleanSettingEntry(booleanSettingProperty3));
      this.arrayList3.add(new ExtendedStringEntryHandler(booleanSettingProperty3));
      this.arrayList3.add(new SecondaryStringEntryAdapter(booleanSettingProperty3));
      this.arrayList3.add(new AuxiliaryStringEntryProvider(booleanSettingProperty3));
      this.arrayList3.add(new TertiaryStringEntryAdapter(booleanSettingProperty3));
      InternalNumberSettingEntry internalNumberSettingEntry3 = new InternalNumberSettingEntry(booleanSettingProperty2.getModule(), "UNoise3HorizontalOffset", setting, false);
      double offset = 20.0;
      double offset2 = 0.0;
      double offset3 = 0.0;
      this.internalNumberSettingEntry2 = internalNumberSettingEntry3.resolveInternalNumberSettingEntry(offset3, offset2, offset, 1);
      String text2 = "Horizontal offset";
      this.internalNumberSettingEntry2.resolveInternalNumberSettingEntry(text2);
      InternalNumberSettingEntry internalNumberSettingEntry4 = this.internalNumberSettingEntry2;
      InternalSettingAdapter internalSettingAdapter = this.internalNumberSettingEntry2.getBooleanSupplierEntry().getInternalSettingAdapter();
      internalNumberSettingEntry4.add(internalSettingAdapter);
      SettingGroup settingGroup2 = new SettingGroup(booleanSettingProperty3.getModule(), "UNoise3horizontalconstantboundingbox", setting);
      String text3 = "Constant bounding box size";
      SettingGroup setting2 = (SettingGroup)settingGroup2.getSetting(text3);
      BooleanSetting setting3 = new BooleanSetting(booleanSettingProperty3.getModule(), "UNoise3constanthorizontalbb", false, setting2);
      String text4 = "Bounding box size";
      this.settingBoundingBoxSize2 = (BooleanSetting)setting3.getSetting(text4);
      NumberSetting setting4 = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3constanthorizontalboundingboxsize", 20.0, 1.0, 50.0, 1, setting2);
      String text5 = "Size";
      NumberSetting numberSetting = setting4.getNumberSetting(text5);
      BooleanSupplier booleanSupplier = this.settingBoundingBoxSize2::isEnabled;
      this.settingSize2 = numberSetting.getNumberSetting(booleanSupplier);
      BooleanSetting setting5 = new BooleanSetting(booleanSettingProperty2.getModule(), "UNoise3constantbbsizeoor", true, setting2);
      String text6 = "Constant bounding box size if target is out of reach";
      this.settingUNoise3constantbbsizeoor = (BooleanSetting)setting5.getSetting(text6);
      NumberSetting setting6 = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3constantbbsizeoorv", 20.0, 1.0, 50.0, 1, setting2);
      String text7 = "Bounding box size if target is out of reach";
      NumberSetting numberSetting2 = setting6.getNumberSetting(text7);
      BooleanSupplier booleanSupplier2 = this.settingUNoise3constantbbsizeoor::isEnabled;
      this.settingUNoise3constantbbsizeoorv = numberSetting2.getNumberSetting(booleanSupplier2);
      this.booleanSettingOption2 = new BooleanSettingOption(booleanSettingProperty3, false);
      SettingGroup settingGroup3 = new SettingGroup(booleanSettingProperty2.getModule(), "Horizontal relative to prediction", setting);
      BooleanSettingProperty booleanSettingProperty4 = booleanSettingProperty2.resolveBooleanSettingPropertyBySettingGroup(settingGroup3);
      this.arrayList5.add(new StringEntryController(booleanSettingProperty4));
      this.arrayList5.add(new SmoothSerializer(booleanSettingProperty4));
      this.arrayList5.add(new InternalBooleanSettingEntry(booleanSettingProperty4));
      this.arrayList5.add(new ExtendedStringEntryHandler(booleanSettingProperty4));
      this.arrayList5.add(new SecondaryStringEntryAdapter(booleanSettingProperty4));
      this.arrayList5.add(new AuxiliaryStringEntryProvider(booleanSettingProperty4));
      this.arrayList5.add(new TertiaryStringEntryAdapter(booleanSettingProperty4));
      this.booleanSettingOption3 = new BooleanSettingOption(booleanSettingProperty4, false);
      SettingGroup settingGroup4 = new SettingGroup(booleanSettingProperty2.getModule(), "UNoise3Vertical", booleanSettingProperty2.getSettingGroup());
      String text8 = "Vertical";
      SettingGroup setting7 = (SettingGroup)settingGroup4.getSetting(text8);
      BooleanSettingProperty booleanSettingProperty5 = booleanSettingProperty2.resolveBooleanSettingPropertyBySettingGroup(setting7);
      this.arrayList.add(new StringEntryController(booleanSettingProperty5));
      this.arrayList.add(new SmoothSerializer(booleanSettingProperty5));
      this.arrayList.add(new InternalBooleanSettingEntry(booleanSettingProperty5));
      this.arrayList.add(new ExtendedStringEntryHandler(booleanSettingProperty5));
      this.arrayList.add(new AuxiliaryStringEntryProvider(booleanSettingProperty5));
      InternalStringEntryRegistry internalStringEntryRegistry;
      this.arrayList.add(internalStringEntryRegistry = new InternalStringEntryRegistry(booleanSettingProperty5));
      CustomStringEntryAdapter customStringEntryAdapter;
      this.arrayList.add(customStringEntryAdapter = new CustomStringEntryAdapter(booleanSettingProperty5));
      AuxiliaryRangeSettingEntry auxiliaryRangeSettingEntry;
      this.arrayList.add(auxiliaryRangeSettingEntry = new AuxiliaryRangeSettingEntry(booleanSettingProperty5));
      this.arrayList.add(new TertiaryStringEntryAdapter(booleanSettingProperty5));
      this.settingVertical = internalStringEntryRegistry.getSettingDelay();
      this.rangeSetting2 = customStringEntryAdapter.getSettingDelay();
      this.rangeSetting = auxiliaryRangeSettingEntry.getSettingDelay();
      internalNumberSettingEntry4 = new InternalNumberSettingEntry(booleanSettingProperty2.getModule(), "UNoise3VerticalOffset", setting7, false);
      double offset4 = 20.0;
      double offset5 = 0.0;
      double offset6 = 0.0;
      this.internalNumberSettingEntry = internalNumberSettingEntry4.resolveInternalNumberSettingEntry(offset6, offset5, offset4, 1);
      String text9 = "Vertical offset";
      this.internalNumberSettingEntry.resolveInternalNumberSettingEntry(text9);
      InternalNumberSettingEntry internalNumberSettingEntry5 = this.internalNumberSettingEntry;
      InternalSettingAdapter internalSettingAdapter2 = this.internalNumberSettingEntry.getBooleanSupplierEntry().getInternalSettingAdapter();
      internalNumberSettingEntry5.add(internalSettingAdapter2);
      this.booleanSettingOption5 = new BooleanSettingOption(booleanSettingProperty5, false);
      settingGroup = new SettingGroup(booleanSettingProperty5.getModule(), "UNoise3verticalconstantboundingbox", setting7);
      String text10 = "Constant bounding box size";
      SettingGroup setting8 = (SettingGroup)settingGroup.getSetting(text10);
      BooleanSetting setting9 = new BooleanSetting(booleanSettingProperty5.getModule(), "UNoise3constantverticalbb", false, setting8);
      String text11 = "Bounding box size";
      this.settingBoundingBoxSize = (BooleanSetting)setting9.getSetting(text11);
      NumberSetting setting10 = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3constantverticalboundingboxsize", 20.0, 1.0, 50.0, 1, setting8);
      String text12 = "Size";
      NumberSetting numberSetting3 = setting10.getNumberSetting(text12);
      BooleanSupplier booleanSupplier3 = this.settingBoundingBoxSize::isEnabled;
      this.settingSize = numberSetting3.getNumberSetting(booleanSupplier3);
      BooleanSetting setting11 = new BooleanSetting(booleanSettingProperty2.getModule(), "UNoise3constantvbbsizeoor", true, setting8);
      String text13 = "Constant bounding box size if target is out of reach";
      this.settingUNoise3constantvbbsizeoor = (BooleanSetting)setting11.getSetting(text13);
      NumberSetting setting12 = new NumberSetting(booleanSettingProperty2.getModule(), "UNoise3constantvbbsizeoorv", 20.0, 1.0, 50.0, 1, setting8);
      String text14 = "Bounding box size if target is out of reach";
      NumberSetting numberSetting4 = setting12.getNumberSetting(text14);
      BooleanSupplier booleanSupplier4 = this.settingUNoise3constantvbbsizeoor::isEnabled;
      this.settingUNoise3constantvbbsizeoorv = numberSetting4.getNumberSetting(booleanSupplier4);
      SettingGroup settingGroup5 = new SettingGroup(booleanSettingProperty2.getModule(), "Vertical relative to prediction", setting7);
      BooleanSettingProperty booleanSettingProperty6 = booleanSettingProperty2.resolveBooleanSettingPropertyBySettingGroup(settingGroup5);
      this.arrayList2.add(new StringEntryController(booleanSettingProperty6));
      this.arrayList2.add(new SmoothSerializer(booleanSettingProperty6));
      this.arrayList2.add(new InternalBooleanSettingEntry(booleanSettingProperty6));
      this.arrayList2.add(new ExtendedStringEntryHandler(booleanSettingProperty6));
      this.arrayList2.add(new AuxiliaryStringEntryProvider(booleanSettingProperty6));
      this.arrayList2.add(new TertiaryStringEntryAdapter(booleanSettingProperty6));
      this.booleanSettingOption = new BooleanSettingOption(booleanSettingProperty6, false);
      this.booleanSettingOption4 = new BooleanSettingOption(booleanSettingProperty2, true);
      Iterator iterator = this.arrayList.iterator();

      while (iterator.hasNext()) {
         ((SmoothWriter)iterator.next()).setEnabled(true);
      }

      Iterator iterator2 = this.arrayList2.iterator();

      while (iterator2.hasNext()) {
         ((SmoothWriter)iterator2.next()).setEnabled(true);
      }

      this.arrayList4.addAll(this.arrayList3);
      this.arrayList4.addAll(this.arrayList5);
      this.arrayList4.addAll(this.arrayList);
      this.arrayList4.addAll(this.arrayList2);
      this.doubleSupplierEntry3 = new DoubleSupplierEntry(this::getValue);
      this.doubleSupplierEntry2 = new DoubleSupplierEntry(this::getValueAsDouble);
      this.doubleSupplierEntry = new DoubleSupplierEntry(this::getValue2);
   }
}
