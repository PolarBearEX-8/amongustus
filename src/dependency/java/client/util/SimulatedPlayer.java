package client.util;

import augustus.modules.latency.TimerRangeV2;
import client.Client;
import client.ClientGlobals;
import client.event.EventMove;
import client.module.Module;
import client.network.CosmeticsPayload;
import client.render.AttackRangeRenderer;
import client.rotation.Rotation;
import client.setting.ArrowDodgerPhase;
import client.setting.RangeSetting;
import client.setting.SettingContainer;
import client.setting.SettingProvider;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class SimulatedPlayer implements MinecraftAccessor {
   public double coordOffset26;
   public double coordOffset84;
   public double coordOffset58;
   public float scaleFactor47;
   public double coordOffset1;
   public boolean state55;
   public double coordOffset29;
   public static PlayerSimulationData advancedDoubleEntry78;
   public int indexOffset21;
   public double coordOffset33;
   public float scaleFactor50;
   public boolean state70;
   public static SimulatedPlayerPath extendedArrayListEntry;
   public int indexOffset48;
   public float scaleFactor77;
   public double coordOffset40;
   public int indexOffset45;
   public double coordOffset68;
   public double[] coordOffsetArray67;
   public ArrayListData tertiaryArrayListEntry54;
   public double coordOffset59;
   public double coordOffset35;
   public double coordOffset72;
   public int indexOffset52;
   public ArrayListData tertiaryArrayListEntry;
   public int indexOffset27;
   public static PlayerSimulationData advancedDoubleEntry;
   public static int indexOffset17 = 0;
   public double coordOffset69;
   public double coordOffset14;
   public static Vec3 positionVec;
   public int indexOffset66;
   public int indexOffset19;
   public Rotation rotation;
   public BacktrackRecord backtrackRecord;
   public SimulationEntityHitbox extendedDoubleEntry;
   public ArrayListData tertiaryArrayListEntry34;
   public double coordOffset16;
   public static Vec3 positionVec2;
   public int indexOffset80;
   public double coordOffset36;
   public double coordOffset93;
   public boolean state22;
   public AttackRangeRenderer auxiliaryRenderListenerAdapter;
   public int indexOffset86;
   public final ArrayList itemList;
   public double coordOffset41;
   public double coordOffset38;
   public Rotation rotation2;
   public boolean state13;
   public double coordOffset53;
   public double coordOffset18;
   public final int indexOffset74;
   public double coordOffset;
   public static Vec3 positionVec3;
   public static Vec3 positionVec4;
   public double coordOffset81;
   public static boolean state76 = false;
   public EntityLivingBase targetEntity;
   public double coordOffset63;
   public double coordOffset64;
   public static SimulationDeltaPosition doubleEntry;
   public double coordOffset61;
   public Vec3 positionVec5;
   public static Vec3 positionVec6;
   public double coordOffset82;
   public ArrayListData tertiaryArrayListEntry83 = new ArrayListData(10);
   public float scaleFactor;
   public boolean state;
   public static Vec3 positionVec7;
   public double coordOffset37;
   public boolean state57;
   public double coordOffset20;
   public static boolean state71 = false;
   public double coordOffset42;
   public double coordOffset28;
   public double coordOffset31;
   public int indexOffset65;
   public ArrayList itemList2;
   public static Vec3 positionVec8;
   public RecordedRotation customFloatEntry;
   public int indexOffset5;
   public double coordOffset23;
   public Rotation rotation3;
   public boolean state3;
   public double coordOffset90;
   public ArrayListData tertiaryArrayListEntry43;
   public double coordOffset25;
   public Rotation rotation4;
   public boolean state44;
   public int indexOffset92;
   public double coordOffset87;
   public double coordOffset9;
   public static int indexOffset39 = 0;
   public int indexOffset75;
   public int indexOffset46;
   public double coordOffset56;
   public Entity entity;
   public SimulationSnapshot supplementalItemEntry;
   public SimulationAngle tertiaryFloatEntry;
   public static boolean state7 = false;
   public int indexOffset85;
   public double coordOffset89;
   public float scaleFactor79;
   public float scaleFactor91;
   public double coordOffset10;
   public float scaleFactor32;
   public double coordOffset4;
   public float scaleFactor15;
   public RotationAngleData auxiliaryFloatEntry;
   public MotionVectorSnapshot secondaryDoubleEntry;
   public double coordOffset49;
   public boolean state11;
   public static Vec3 positionVec9;
   public int indexOffset62;
   public double[][] coordOffsetArray;
   public ArrayListWrapper secondaryArrayListEntry;
   public double coordOffset73;
   public ArrayListData tertiaryArrayListEntry88;
   public double coordOffset8;
   public double coordOffset6;
   public float scaleFactor24;
   public double coordOffset51;
   public final TimeHelper timer;
   public double coordOffset12;
   public double coordOffset60;
   public double coordOffset2;
   public int indexOffset;
   public double coordOffset30;

   public static Vec3 getVec3(int count, Vec3 vec, EntityLivingBase livingEntity, double posX, double posY, double posZ, int count2) {
      float factor = livingEntity.width / 2.0F;
      float height = livingEntity.getHeight();
      AxisAlignedBB boundingBox = new AxisAlignedBB(
            posX - (double)factor,
            posY,
            posZ - (double)factor,
            posX + (double)factor,
            posY + (double)MinecraftAccessor.minecraft.thePlayer.height,
            posZ + (double)factor
         )
         .offset((double)height, (double)height, (double)height);
      double doubleItem = MathHelper.getDouble7(vec.xCoord, boundingBox.minX, boundingBox.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec.yCoord, boundingBox.minY, boundingBox.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec.zCoord, boundingBox.minZ, boundingBox.maxZ);
      return new Vec3(doubleItem, doubleItem2, doubleItem3);
   }

   public float getFloat(float posX, float posY, float posZ) {
      float floatItem = getFloat9(posY, posZ, posX);
      return floatItem == posY ? posY : getFloat2(floatItem, posY);
   }

   public void updateSupplementalItemEntry(SimulationSnapshot simulationSnapshot) {
      double offset = 1.0;
      this.supplementalItemEntry = simulationSnapshot.resolveSimulationSnapshotByDouble(offset);
   }

   public static Vec3 getVec32(Vec3 vec, EntityLivingBase livingEntity, Vec3 vec2) {
      double posZ = vec2.zCoord;
      double posY = vec2.yCoord;
      double posX = vec2.xCoord;
      return getVec3(28209, vec, livingEntity, posX, posY, posZ, 52307);
   }

   public float getScaleFactor(float posX, float posY, float posZ, RangeSetting setting, float factor) {
      boolean flag = CosmeticsPayload.checkCondition(1);
      float factor2 = Math.abs(factor);
      float factor3 = (float)setting.recalculate() + (float)(flag ? 15 : 0);
      double offset = setting.getDouble9() + (double)(flag ? 15 : 0) + (setting.getDouble2() - setting.getDouble9()) / 2.0;
      double offset2 = (double)Math.abs((getFloat9(posY, posZ, 180.0F) - posY) / Math.max(factor2, 1.0F));
      double offset3 = Math.abs((double)factor2 / offset);
      float factor4 = MathHelper.clamp_float(Math.abs(factor2) + (offset2 > offset3 ? factor3 : -factor3), 0.0F, posX);
      float floatItem = getFloat9(posY, posZ, factor4);
      return floatItem == posY ? posY : getFloat2(floatItem, posY);
   }

   private Vec3 getVec3(Entity entity2, Vec3 vec, String text, float posX, float posX2, float posY, float posZ, boolean flag, double offset) {
      float height = entity2.getHeight();
      AxisAlignedBB boundingBox = new AxisAlignedBB(
         entity2.getBoundingBox2().minX - (double)height,
         entity2.getBoundingBox2().minY - (double)height,
         entity2.getBoundingBox2().minZ - (double)height,
         entity2.getBoundingBox2().maxX + (double)height,
         entity2.getBoundingBox2().maxY + (double)height,
         entity2.getBoundingBox2().maxZ + (double)height
      );
      switch (text) {
         case "Pattern1":
            double offset2 = 0.4;
            double doubleItem = MathHelper.getDouble7(vec.xCoord - offset2, boundingBox.minX, boundingBox.maxX);
            double doubleItem2 = MathHelper.getDouble7(vec.yCoord - offset2, boundingBox.minY, boundingBox.maxY);
            double doubleItem3 = MathHelper.getDouble7(vec.zCoord - offset2, boundingBox.minZ, boundingBox.maxZ);
            double doubleItem4 = MathHelper.getDouble7(vec.xCoord + offset2, entity2.getBoundingBox2().minX - (double)height, entity2.getBoundingBox2().maxX + (double)height);
            double doubleItem5 = MathHelper.getDouble7(vec.yCoord + offset2, entity2.getBoundingBox2().minY - (double)height, entity2.getBoundingBox2().maxY + (double)height);
            double doubleItem6 = MathHelper.getDouble7(vec.zCoord + offset2, entity2.getBoundingBox2().minZ - (double)height, entity2.getBoundingBox2().maxZ + (double)height);
            double offset3 = 0.1;
            double offset4 = -0.1;
            double offset5 = SettingContainer.settingProvider.getDouble4(offset4, offset3)
                  * (double)MinecraftAccessor.minecraft.thePlayer.getDistanceSqToEntity(entity2)
                  / 1.5
               + this.positionVec5.xCoord;
            double offset6 = 0.1;
            double offset7 = -0.1;
            double offset8 = SettingContainer.settingProvider.getDouble4(offset7, offset6)
                  * (double)MinecraftAccessor.minecraft.thePlayer.getDistanceSqToEntity(entity2)
                  / 1.5
               + this.positionVec5.yCoord;
            double offset9 = 0.1;
            double offset10 = -0.1;
            double offset11 = SettingContainer.settingProvider.getDouble4(offset10, offset9)
                  * (double)MinecraftAccessor.minecraft.thePlayer.getDistanceSqToEntity(entity2)
                  / 1.5
               + this.positionVec5.zCoord;
            double doubleItem7 = MathHelper.getDouble7(vec.xCoord + offset5, doubleItem, doubleItem4);
            double doubleItem8 = MathHelper.getDouble7(vec.yCoord + offset8, doubleItem2, doubleItem5);
            double doubleItem9 = MathHelper.getDouble7(vec.zCoord + offset11, doubleItem3, doubleItem6);
            this.positionVec5 = new Vec3(doubleItem7 - vec.xCoord, doubleItem8 - vec.yCoord, doubleItem9 - vec.zCoord);
            return new Vec3(doubleItem7, doubleItem8, doubleItem9);
         case "IntaveTest":
            long indexOffset2 = (long)this.indexOffset21;
            if (this.timer.hasReached(indexOffset2)) {
               this.indexOffset21 = SettingContainer.settingProvider.getInt2(500, 1000);
               double offset12 = boundingBox.maxX - boundingBox.minX;
               double offset13 = boundingBox.maxY - boundingBox.minY;
               double offset14 = boundingBox.maxZ - boundingBox.minZ;
               double offset15 = 0.0;
               this.coordOffset29 = SettingContainer.settingProvider.getDouble4(offset15, offset12);
               double offset16 = 0.0;
               this.coordOffset40 = SettingContainer.settingProvider.getDouble4(offset16, offset13);
               double offset17 = 0.0;
               this.coordOffset12 = SettingContainer.settingProvider.getDouble4(offset17, offset14);
               this.timer.reset();
            }

            return new Vec3(boundingBox.minX + this.coordOffset29, boundingBox.minY + this.coordOffset40, boundingBox.minZ + this.coordOffset12);
         case "Intave14":
            float factor = (float)(Math.atan2(this.coordOffset89, this.coordOffset49) * 180.0 / Math.PI - 90.0);
            float factor2 = (float)(
               -(
                  MathHelper.getDouble6(
                        this.coordOffset28, (double)MathHelper.sqrt_double(this.coordOffset49 * this.coordOffset49 + this.coordOffset89 * this.coordOffset89)
                     )
                     * 180.0
                     / Math.PI
               )
            );
            float factor3;
            float factor4;
            if (flag) {
               float factor5 = 180.0F;
               float factor6 = 170.0F;
               factor3 = getFloat7(posX, factor, posY / SettingContainer.settingProvider.getFloat2(factor6, factor5));
               float factor7 = 180.0F;
               float factor8 = 170.0F;
               factor4 = getFloat7(posX2, factor2, posZ / SettingContainer.settingProvider.getFloat2(factor8, factor7));
            } else {
               factor3 = getFloat9(posX, factor, posY);
               factor4 = getFloat9(posX2, factor2, posZ);
            }

            MovingObjectPosition hitResult = RayTraceUtils.resolveMovingObjectPosition(1.0F, factor3, factor4, offset, 0.0);
            boolean flag2 = hitResult != null
               && hitResult.typeOfHit == MovingObjectType.ENTITY
               && hitResult.entityHit instanceof EntityLivingBase
               && ((EntityLivingBase)hitResult.entityHit).hurtTime < 5;
            Vec3 vec2 = new Vec3(vec);
            if (flag2) {
               this.positionVec5 = new Vec3(vec);
            } else {
               double offset18 = 0.4;
               double doubleItem10 = MathHelper.getDouble7(vec.xCoord - offset18, boundingBox.minX, boundingBox.maxX);
               double doubleItem11 = MathHelper.getDouble7(vec.yCoord - offset18, boundingBox.minY, boundingBox.maxY);
               double doubleItem12 = MathHelper.getDouble7(vec.zCoord - offset18, boundingBox.minZ, boundingBox.maxZ);
               double doubleItem13 = MathHelper.getDouble7(
                  vec.xCoord + offset18, entity2.getBoundingBox2().minX - (double)height, entity2.getBoundingBox2().maxX + (double)height
               );
               double doubleItem14 = MathHelper.getDouble7(
                  vec.yCoord + offset18, entity2.getBoundingBox2().minY - (double)height, entity2.getBoundingBox2().maxY + (double)height
               );
               double doubleItem15 = MathHelper.getDouble7(
                  vec.zCoord + offset18, entity2.getBoundingBox2().minZ - (double)height, entity2.getBoundingBox2().maxZ + (double)height
               );
               double offset19 = 0.1;
               double offset20 = -0.1;
               double offset21 = SettingContainer.settingProvider.getDouble4(offset20, offset19)
                     * (double)MinecraftAccessor.minecraft.thePlayer.getDistanceSqToEntity(entity2)
                     / 1.5
                  + this.positionVec5.xCoord;
               double offset22 = 0.1;
               double offset23 = -0.1;
               double offset24 = SettingContainer.settingProvider.getDouble4(offset23, offset22)
                     * (double)MinecraftAccessor.minecraft.thePlayer.getDistanceSqToEntity(entity2)
                     / 1.5
                  + this.positionVec5.yCoord;
               double offset25 = 0.1;
               double offset26 = -0.1;
               double offset27 = SettingContainer.settingProvider.getDouble4(offset26, offset25)
                     * (double)MinecraftAccessor.minecraft.thePlayer.getDistanceSqToEntity(entity2)
                     / 1.5
                  + this.positionVec5.zCoord;
               double doubleItem16 = MathHelper.getDouble7(vec.xCoord + offset21, doubleItem10, doubleItem13);
               double doubleItem17 = MathHelper.getDouble7(vec.yCoord + offset24, doubleItem11, doubleItem14);
               double doubleItem18 = MathHelper.getDouble7(vec.zCoord + offset27, doubleItem12, doubleItem15);
               this.positionVec5 = new Vec3(doubleItem16 - vec.xCoord, doubleItem17 - vec.yCoord, doubleItem18 - vec.zCoord);
               vec2 = new Vec3(doubleItem16, doubleItem17, doubleItem18);
            }

            return vec2;
         default:
            return vec;
      }
   }

   public Vec3 getVec3(Entity entity2, String text, int count, int count2, int count3, int count4, Rotation rotation5) {
      switch (text) {
         case "Head":
            return new Vec3(entity2.posX, entity2.posY + (double)entity2.getHeight2(), entity2.posZ);
         case "BestHitVec":
            return getVec3(entity2, count, count2);
         case "NearestHitVec":
            return getVec3(entity2, count3, count4, rotation5);
         default:
            return null;
      }
   }

   private double getCoordinate(float factor, float factor2, RotationAngleData rotationAngleData) {
      Rotation rotation5 = this.rotation3;
      Rotation rotation6 = this.rotation4;
      Rotation rotation7 = new Rotation(factor, factor2);
      Rotation rotation8 = rotation6;
      Rotation rotation9 = rotation5;
      return (double)(180.0F - rotationAngleData.computeFactor(rotation9, rotation8, rotation7));
   }

   private Rotation getRotation(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, double offset) {
      Rotation rotation5 = getRotation8(getFloat9(posX, posY, 180.0F), getFloat9(posX2, posZ, 180.0F), posX, posX2);
      double offset2 = 0.0;
      MovingObjectPosition movingObjectPosition = RayTraceUtils.getMovingObjectPosition(1.0F, rotation5, offset, offset2);
      boolean flag = movingObjectPosition != null && movingObjectPosition.entityHit instanceof EntityLivingBase;
      if (flag && livingEntity.hurtTime > 3) {
         float floatItem = getFloat2(getFloat9(posX, posY, 180.0F), posX);
         if (CombatRandomizer.matchesEntity(livingEntity, floatItem)) {
            flag = false;
         }
      }

      float factor = MathHelper.sqrt_float(this.scaleFactor24 - posY);
      if (flag && Math.abs(factor) > 10.0F) {
         flag = false;
      }

      ArrayList list = new ArrayList();
      if (flag) {
         for (RotationAngleData rotationAngleData : (Iterable<RotationAngleData>)(Iterable)(this.itemList)) {
            MovingObjectPosition movingObjectPosition2 = CombatRayTrace.getMovingObjectPosition(
               1.0F, getRotation8(posX3 + rotationAngleData.getFactor(), posX4 + rotationAngleData.getFactor2(), posX, posX2)
            );
            if (movingObjectPosition2 != null && movingObjectPosition2.entityHit instanceof EntityLivingBase) {
               list.add(rotationAngleData);
            }
         }
      }

      if (list.isEmpty()) {
         list = this.itemList;
      }

      HashMap map = new HashMap();
      Math.abs(getScaleFactorArray(livingEntity, posX, 263465940, posX2, 6342)[0]);

      for (RotationAngleData rotationAngleData2 : (Iterable<RotationAngleData>)(Iterable)(list)) {
         Rotation rotation6 = ClientGlobals.rotationManager.getRotation3();
         Rotation rotation7 = new Rotation(posX3 + rotationAngleData2.getFactor(), posX4 + rotationAngleData2.getFactor2());
         Rotation rotation8 = rotation6;
         double offset3 = 0.0 + (1.0 - Math.min(RotationAngleData.computeValue(rotationAngleData2, rotation8, rotation7), 20.0) / 20.0);
         map.put(rotationAngleData2, offset3);
      }

      List list2 = (List)map.keySet().stream().sorted(Comparator.comparingDouble(key -> (Double)map.get(key))).collect(Collectors.toList());
      RotationAngleData rotationAngleData3 = (RotationAngleData)list2.get(list2.size() - 1);
      this.scaleFactor24 = posY;
      this.scaleFactor = posZ;
      return new Rotation(posX3 + rotationAngleData3.getFactor(), posX4 + rotationAngleData3.getFactor2());
   }

   public static Vec3 getVec3(AxisAlignedBB boundingBox) {
      Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec35(1.0F);
      double doubleItem = MathHelper.getDouble7(vec3.xCoord, boundingBox.minX, boundingBox.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec3.yCoord, boundingBox.minY, boundingBox.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec3.zCoord, boundingBox.minZ, boundingBox.maxZ);
      return new Vec3(doubleItem, doubleItem2, doubleItem3);
   }

   public static Vec3 processAction(float factor, float factor2) {
      float factor3 = MathHelper.cos(-factor2 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float factor4 = MathHelper.abs(-factor2 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float factor5 = -MathHelper.cos(-factor * (float) (Math.PI / 180.0));
      float factor6 = MathHelper.abs(-factor * (float) (Math.PI / 180.0));
      return new Vec3((double)(factor4 * factor5), (double)factor6, (double)(factor3 * factor5));
   }

   public static float getFloat2(float factor, float factor2) {
      return getRotation8(factor, 0.0F, factor2, 0.0F).yaw;
   }

   public static Vec3 getVec3(Entity entity2, float factor, int count, int count2, Rotation rotation5) {
      Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec35(1.0F);
      double offset = (double)factor;
      AxisAlignedBB boundingBox = processAction(entity2, offset);
      double offset2 = boundingBox.maxX - boundingBox.minX;
      double offset3 = boundingBox.maxY - boundingBox.minY;
      double offset4 = offset2 * ((double)count2 / 100.0);
      double offset5 = offset3 * ((double)count / 100.0);
      AxisAlignedBB boundingBox2 = boundingBox.offset((offset4 - offset2) / 2.0, (offset5 - offset3) / 2.0, (offset4 - offset2) / 2.0);
      double coordinate = vec3.getCoordinate4(boundingBox2.getVec3());
      Vec3 vec32 = MinecraftAccessor.minecraft.thePlayer.getVec34(rotation5.pitch, rotation5.yaw);
      Vec3 vec33 = vec3.getVec312(vec32.xCoord * coordinate, vec32.yCoord * coordinate, vec32.zCoord * coordinate);
      double doubleItem = MathHelper.getDouble7(vec33.xCoord, boundingBox2.minX, boundingBox2.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec33.yCoord, boundingBox2.minY, boundingBox2.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec33.zCoord, boundingBox2.minZ, boundingBox2.maxZ);
      return new Vec3(doubleItem, doubleItem2, doubleItem3);
   }

   public static float[] getFloatArray(double posX, double posY, double posZ, float factor, float factor2) {
      double offset = posX - MinecraftAccessor.minecraft.thePlayer.posX;
      double offset2 = posY - (MinecraftAccessor.minecraft.thePlayer.posY + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2());
      double offset3 = posZ - MinecraftAccessor.minecraft.thePlayer.posZ;
      float factor3 = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor4 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      float floatItem = getFloat6(factor3 - factor);
      float floatItem2 = getFloat6(factor4 - factor2);
      return new float[]{floatItem, floatItem2};
   }

   private Rotation getRotation(EntityLivingBase livingEntity, float posX, float posX2, float posY, float posZ) {
      long value = 0x16229040c917L;
      long keySalt = 0x77a2ed254aafL ^ value ^ 0x9b1be1506dL;
      if (this.itemList2.isEmpty()) {
         return new Rotation(posX, posX2);
      } else {
         if (this.indexOffset92 >= this.itemList2.size()) {
            this.indexOffset92 = 0;
         }

         SimulationEntityHitbox simulationEntityHitbox = (SimulationEntityHitbox)this.itemList2.get(this.indexOffset92);
         if (this.extendedDoubleEntry == null) {
            this.extendedDoubleEntry = simulationEntityHitbox;
            return new Rotation(posX, posX2);
         } else {
            this.indexOffset92++;
            float height = livingEntity.getHeight();
            ArrayList list = new ArrayList();
            ArrayList list2 = new ArrayList();

            for (int index = 0; index < this.itemList2.size(); index++) {
               SimulationEntityHitbox simulationEntityHitbox2 = (SimulationEntityHitbox)this.itemList2.get(index);
               float factor = MathHelper.sqrt_float(simulationEntityHitbox2.factor - simulationEntityHitbox2.factor6);
               list.add(Math.abs(factor));
               list2.add(Math.abs(MathHelper.sqrt_float(simulationEntityHitbox2.factor - simulationEntityHitbox2.factor3)));
            }

            System.out.println("Avg: " + PlayerUtil.computeFactorByList(list));
            System.out.println("Std: " + PlayerUtil.computeFactor3(list));
            System.out.println(": " + PlayerUtil.computeValue2(list2, list));
            System.out.println("Avg: " + PlayerUtil.computeFactorByList(list2));
            System.out.println("Std: " + PlayerUtil.computeFactor3(list2));
            AxisAlignedBB boundingBox = new AxisAlignedBB(simulationEntityHitbox.value2, simulationEntityHitbox.value8, simulationEntityHitbox.value5, simulationEntityHitbox.value, simulationEntityHitbox.value4, simulationEntityHitbox.value3)
               .offset((double)height, (double)height, (double)height);
            Vec3 vec = new Vec3(simulationEntityHitbox.value6, simulationEntityHitbox.value7, simulationEntityHitbox.value9);
            float factor2 = Math.abs(MathHelper.clamp_float(simulationEntityHitbox.factor2, -30.0F, 30.0F) / 30.0F);
            Math.abs(MathHelper.clamp_float(simulationEntityHitbox.factor4, -30.0F, 30.0F) / 30.0F);
            double offset = (double)(simulationEntityHitbox.factor5 % 360.0F);
            double coordinate = TrajectoryPredictor.getCoordinate(boundingBox, vec, offset);
            double factor3 = (double)simulationEntityHitbox.factor3;
            double coordinate2 = TrajectoryPredictor.getCoordinate(boundingBox, vec, keySalt, factor3);
            float factor4 = Math.abs(MathHelper.clamp_float(getFloat6(posX - ClientGlobals.rotationManager.getRotation().yaw), -30.0F, 30.0F) / 30.0F);
            Math.abs(MathHelper.clamp_float(getFloat6(posX2 - ClientGlobals.rotationManager.getRotation().pitch), -30.0F, 30.0F) / 30.0F);
            if (factor4 < 1.0F && factor4 > -1.0F) {
               factor4 = 1.0F;
            }

            Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec35(1.0F);
            AxisAlignedBB boundingBox2 = new AxisAlignedBB(
                  this.extendedDoubleEntry.value2,
                  this.extendedDoubleEntry.value8,
                  this.extendedDoubleEntry.value5,
                  this.extendedDoubleEntry.value,
                  this.extendedDoubleEntry.value4,
                  this.extendedDoubleEntry.value3
               )
               .offset((double)height, (double)height, (double)height);
            double offset2 = (double)(this.extendedDoubleEntry.factor5 % 360.0F);
            TrajectoryPredictor.getCoordinate(boundingBox2, vec3, offset2);
            double factor5 = (double)this.extendedDoubleEntry.factor3;
            TrajectoryPredictor.getCoordinate(boundingBox2, vec3, keySalt, factor5);
            double doubleItem = MathHelper.getDouble7(coordinate, 0.0, 1.0);
            double doubleItem2 = MathHelper.getDouble7(coordinate2, 0.0, 1.0);
            System.out.println(factor2 + "  " + factor4);
            AxisAlignedBB boundingBox3 = livingEntity.getBoundingBox2().offset((double)height, (double)height, (double)height);
            Rotation rotation5 = new Rotation(
               (float)TrajectoryPredictor.getCoordinate(TrajectoryPredictor.getCoordOffsetArray(vec3, boundingBox3), doubleItem),
               (float)TrajectoryPredictor.getCoordinate(TrajectoryPredictor.getCoordOffsetArrayByVec3(vec3, boundingBox3), doubleItem2)
            );
            System.out.println(coordinate + "  " + coordinate2 + "  " + doubleItem + "  " + doubleItem2);
            this.extendedDoubleEntry = simulationEntityHitbox;
            return rotation5;
         }
      }
   }

   public static float[] getScaleFactorArray(Entity entity2, float factor, float factor2) {
      double offset = entity2.posX - MinecraftAccessor.minecraft.thePlayer.posX;
      double offset2 = entity2.posY
         + (double)entity2.getHeight2()
         - (MinecraftAccessor.minecraft.thePlayer.posY + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2());
      double offset3 = entity2.posZ - MinecraftAccessor.minecraft.thePlayer.posZ;
      float factor3 = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor4 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      float floatItem = getFloat6(factor3 - factor);
      float floatItem2 = getFloat6(factor4 - factor2);
      return new float[]{floatItem, floatItem2};
   }

   private boolean checkCondition(double posX, double posY, double posZ) {
      return posX > posY && posX < posZ;
   }

   public Rotation getRotation(
      EntityLivingBase livingEntity,
      RangeSetting setting,
      RangeSetting setting2,
      RangeSetting setting3,
      RangeSetting setting4,
      float factor,
      int count,
      float factor2,
      String text,
      boolean flag,
      boolean flag2,
      boolean flag3,
      float factor3,
      Vec3 vec,
      boolean flag4,
      boolean flag5,
      String text2,
      String text3,
      double offset,
      int count2,
      int count3,
      int count4,
      String text4,
      String text5,
      boolean flag6,
      boolean flag7,
      boolean flag8,
      RangeSetting setting5,
      RangeSetting setting6,
      double offset2,
      float posX,
      float posX2,
      float posX3,
      float posX4,
      float posX5,
      float posX6,
      float posX7,
      float posX8,
      float posX9,
      float posY,
      float posZ,
      boolean flag9,
      int count5,
      double posX10,
      double posX11,
      double posX12,
      double posY2,
      double posZ2,
      String text6,
      boolean flag10
   ) {
      byte byteValue = 0;
      int count6 = 599338805;
      char character = '\uc52d';
      long keySalt = ((long)byteValue << 48 | (long)count6 << 32 >>> 16 | (long)character << 48 >>> 48) ^ 0x77a2ed254aafL;
      int count7 = (int)((((long)byteValue << 48 | (long)count6 << 32 >>> 16 | (long)character << 48 >>> 48) ^ 0x77a2ed254aafL ^ 0x6bd658aa9531L) >>> 48);
      int count8 = (int)((keySalt ^ 0x1b00b928e63L) >>> 48);
      long keySalt2 = keySalt ^ 0x3500a4945c57L;
      int count9 = (int)((keySalt ^ 0xb8de9db2006L) >>> 56);
      long keySalt3 = keySalt ^ 0x63974cbbc703L;
      int count10 = (int)((keySalt ^ 0x63974cbbc703L) >>> 32);
      int count11 = (int)((keySalt ^ 0x63974cbbc703L) << 32 >>> 48);
      int count12 = (int)(keySalt3 << 48 >>> 48);
      if (livingEntity == null) {
         return new Rotation(factor, factor2);
      } else {
         SettingProvider settingProvider = SettingContainer.settingProvider;
         char character2 = (char)count7;
         double doubleItem = setting3.getDouble9();
         byte byteValue2 = (byte)count9;
         double doubleItem2 = setting3.getDouble2();
         double offset3 = doubleItem;
         float floatItem = settingProvider.getFloat3(offset3, doubleItem2);
         SettingProvider settingProvider2 = SettingContainer.settingProvider;
         character2 = (char)count7;
         double doubleItem3 = setting4.getDouble9();
         byteValue2 = (byte)count9;
         doubleItem2 = setting4.getDouble2();
         offset3 = doubleItem3;
         float floatItem2 = settingProvider2.getFloat3(offset3, doubleItem2);
         Vec3 vec2;
         if (flag2 && !flag4 && vec != null) {
            vec2 = vec;
         } else {
            Rotation rotation5 = new Rotation(factor, factor2);
            vec2 = this.getVec3(livingEntity, text3, count3, count2, count3, count2, rotation5);
         }

         Vec3 vec3 = vec2;
         Vec3 vec4 = new Vec3(
            vec3.xCoord - MinecraftAccessor.minecraft.thePlayer.posX,
            vec3.yCoord - (MinecraftAccessor.minecraft.thePlayer.posY + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2()),
            vec3.zCoord - MinecraftAccessor.minecraft.thePlayer.posZ
         );
         if (!text2.equals("None")) {
            vec3 = this.getVec3(livingEntity, vec3, text2, factor, factor2, floatItem, floatItem2, flag, offset);
         }

         this.coordOffset49 = vec3.xCoord - MinecraftAccessor.minecraft.thePlayer.posX;
         this.coordOffset28 = vec3.yCoord - (MinecraftAccessor.minecraft.thePlayer.posY + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2());
         this.coordOffset89 = vec3.zCoord - MinecraftAccessor.minecraft.thePlayer.posZ;
         float factor4 = (float)(Math.atan2(this.coordOffset89, this.coordOffset49) * 180.0 / Math.PI - 90.0);
         float factor5 = (float)(
            -(
               MathHelper.getDouble6(
                     this.coordOffset28, (double)MathHelper.sqrt_double(this.coordOffset49 * this.coordOffset49 + this.coordOffset89 * this.coordOffset89)
                  )
                  * 180.0
                  / Math.PI
            )
         );
         float factor6 = (float)(Math.atan2(vec4.zCoord, vec4.xCoord) * 180.0 / Math.PI - 90.0);
         float factor7 = (float)(
            -(
               MathHelper.getDouble6(vec4.yCoord, (double)MathHelper.sqrt_double(vec4.xCoord * vec4.xCoord + vec4.zCoord * vec4.zCoord))
                  * 180.0
                  / Math.PI
            )
         );
         boolean flag11 = false;
         if (text.equals("Noise")) {
            EntityPlayerSP player = MinecraftAccessor.minecraft.thePlayer;
            double offset4 = (double)factor4;
            double offset5 = (double)factor5;
            vec3 = new Vec3(this.coordOffset49, this.coordOffset28, this.coordOffset89);
            doubleItem2 = offset5;
            offset3 = offset4;
            EntityPlayerSP player2 = player;
            Rotation rotation6 = this.getRotation(livingEntity, player2, factor, factor2, offset3, doubleItem2, vec3);
            factor4 = rotation6.yaw;
            factor5 = rotation6.pitch;
            flag11 = true;
         } else if (text.equals("Noise2")) {
            this.customFloatEntry.value11 = this.coordOffset81;
            this.customFloatEntry.count4 = this.indexOffset86;
            this.customFloatEntry.count2 = this.indexOffset52;
            this.customFloatEntry.count = this.indexOffset80;
            this.customFloatEntry.value26 = this.coordOffset18;
            this.customFloatEntry.value16 = this.coordOffset8;
            this.customFloatEntry.count6 = this.indexOffset75;
            this.customFloatEntry.value = this.coordOffset23;
            this.customFloatEntry.value6 = this.coordOffset2;
            this.customFloatEntry.enabled2 = this.state70;
            this.customFloatEntry.value22 = this.coordOffset35;
            this.customFloatEntry.value25 = this.coordOffset37;
            this.customFloatEntry.value19 = this.coordOffset33;
            this.customFloatEntry.enabled5 = this.state55;
            this.customFloatEntry.enabled3 = this.state13;
            this.customFloatEntry.value23 = this.coordOffset56;
            this.customFloatEntry.enabled6 = this.state22;
            this.customFloatEntry.value21 = this.coordOffset53;
            this.customFloatEntry.value7 = this.coordOffset30;
            this.customFloatEntry.factor2 = this.scaleFactor47;
            this.customFloatEntry.value13 = this.coordOffset;
            this.customFloatEntry.value14 = this.coordOffset14;
            this.customFloatEntry.value29 = this.coordOffset10;
            this.customFloatEntry.count3 = this.indexOffset;
            this.customFloatEntry.count9 = this.indexOffset48;
            this.customFloatEntry.value2 = this.coordOffset68;
            this.customFloatEntry.value17 = this.coordOffset42;
            this.customFloatEntry.value15 = this.coordOffset20;
            this.customFloatEntry.value9 = this.coordOffset90;
            this.customFloatEntry.value4 = this.coordOffset73;
            this.customFloatEntry.value8 = this.coordOffset9;
            this.customFloatEntry.enabled4 = this.state11;
            this.customFloatEntry.enabled = this.state;
            RecordedRotation customFloatEntry2 = this.customFloatEntry;
            EntityPlayerSP player3 = MinecraftAccessor.minecraft.thePlayer;
            double offset6 = (double)factor4;
            double offset7 = (double)factor5;
            vec4 = new Vec3(this.coordOffset49, this.coordOffset28, this.coordOffset89);
            doubleItem2 = offset7;
            offset3 = offset6;
            EntityPlayerSP player4 = player3;
            Rotation rotation7 = customFloatEntry2.resolveRotationByEntityLivingBase(livingEntity, player4, factor, factor2, offset3, doubleItem2, vec4, vec3);
            factor4 = rotation7.yaw;
            factor5 = rotation7.pitch;
            flag11 = true;
         } else if (text.equals("Turbo")) {
            this.tertiaryFloatEntry.count = this.indexOffset66;
            this.tertiaryFloatEntry.count4 = this.indexOffset85;
            this.tertiaryFloatEntry.count2 = this.indexOffset46;
            this.tertiaryFloatEntry.count3 = this.indexOffset5;
            this.tertiaryFloatEntry.factor3 = this.scaleFactor32;
            this.tertiaryFloatEntry.factor = this.scaleFactor15;
            this.tertiaryFloatEntry.factor4 = this.scaleFactor79;
            this.tertiaryFloatEntry.factor2 = this.scaleFactor77;
            this.tertiaryFloatEntry.value = this.coordOffset1;
            this.tertiaryFloatEntry.value2 = this.coordOffset6;
            SimulationAngle tertiaryFloatEntry2 = this.tertiaryFloatEntry;
            EntityPlayerSP player5 = MinecraftAccessor.minecraft.thePlayer;
            char character3 = (char)count12;
            double offset8 = (double)factor4;
            double offset9 = (double)factor5;
            vec4 = new Vec3(this.coordOffset49, this.coordOffset28, this.coordOffset89);
            doubleItem2 = offset9;
            offset3 = offset8;
            char character4 = character3;
            EntityPlayerSP player6 = player5;
            Rotation rotation8 = tertiaryFloatEntry2.resolveRotation(count10, livingEntity, count11, player6, character4, factor, factor2, offset3, doubleItem2, vec4, vec3);
            factor4 = rotation8.yaw;
            factor5 = rotation8.pitch;
            flag11 = true;
         } else if (text.equals("Noise3")) {
            AttackRangeRenderer auxiliaryRenderListenerAdapter2 = this.auxiliaryRenderListenerAdapter;
            EntityPlayerSP player7 = MinecraftAccessor.minecraft.thePlayer;
            double offset10 = (double)factor4;
            double offset11 = (double)factor5;
            vec4 = new Vec3(this.coordOffset49, this.coordOffset28, this.coordOffset89);
            doubleItem2 = offset11;
            offset3 = offset10;
            EntityPlayerSP player8 = player7;
            Rotation rotation9 = auxiliaryRenderListenerAdapter2.resolveRotationByEntityLivingBase(livingEntity, player8, factor, factor2, offset3, doubleItem2, vec4, vec3);
            factor4 = rotation9.yaw;
            factor5 = rotation9.pitch;
            flag11 = true;
         } else if (text.equals("LegitTest")) {
            factor4 = MinecraftAccessor.minecraft.thePlayer.rotationYaw;
            factor5 = MinecraftAccessor.minecraft.thePlayer.rotationPitch;
            flag11 = true;
         }

         TimerRangeV2 timerRangeV = Client.getClient().getModuleManager().timerRangeV2;
         if (timerRangeV.checkCondition() && ClientGlobals.moduleManager.killAura.settingTimerRangeV2Fix.isEnabled()) {
            Vec3 vec32 = getVec3(timerRangeV.getBoundingBox());
            Vec3 vec5 = new Vec3(
               MinecraftAccessor.minecraft.thePlayer.posX,
               MinecraftAccessor.minecraft.thePlayer.posY + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2(),
               MinecraftAccessor.minecraft.thePlayer.posZ
            );
            Rotation rotation10 = new Rotation(factor, factor2);
            factor4 = 180.0F;
            factor5 = 180.0F;
            Rotation rotation11 = rotation10;
            Vec3 vec6 = vec5;
            Rotation rotation12 = getRotation(vec32, vec6, rotation11, factor5, factor4);
            factor4 = rotation12.yaw;
            factor5 = rotation12.pitch;
         }

         if (flag10 && this.coordOffset49 == 0.0 && this.coordOffset89 == 0.0) {
            factor4 = factor;
            if (this.coordOffset28 == 0.0) {
               factor5 = factor2;
            }
         }

         float floatItem3 = getFloat9(factor, factor4, 180.0F);
         float floatItem4 = getFloat9(factor2, factor5, 180.0F);
         float factor8 = floatItem3;
         this.applyEntityLivingBase(livingEntity, factor8, floatItem4);
         if (flag) {
            floatItem4 = 180.0F;
            factor8 = 170.0F;
            floatItem4 = getFloat7(factor, factor4, floatItem / SettingContainer.settingProvider.getFloat2(factor8, floatItem4));
            factor8 = 180.0F;
            float factor9 = 170.0F;
            factor8 = getFloat7(factor2, factor5, floatItem2 / SettingContainer.settingProvider.getFloat2(factor9, factor8));
         } else {
            boolean flag12 = CosmeticsPayload.checkCondition(1);
            float factor10 = (float)setting.getValue2() + (float)(flag12 ? 15 : 0);
            float factor11 = (float)setting2.getValue2() + (float)(flag12 ? 15 : 0);
            doubleItem2 = setting.getDouble9() + (double)(flag12 ? 15 : 0) + (setting.getDouble2() - setting.getDouble9()) / 2.0;
            offset3 = setting2.getDouble9() + (double)(flag12 ? 15 : 0) + (setting2.getDouble2() - setting2.getDouble9()) / 2.0;
            float floatItem5 = getFloat9(factor, factor4, 180.0F);
            float floatItem6 = getFloat9(factor2, factor5, 180.0F);
            float factor12 = floatItem5 - factor;
            float factor13 = floatItem6 - factor2;
            double offset12 = flag9 ? (double)Math.abs(this.rotation.yaw - this.rotation2.yaw) : 0.0;
            double offset13 = Math.abs((double)factor12 / Math.max(this.coordOffset69, 1.0));
            doubleItem2 = Math.abs((this.coordOffset69 - offset12) / doubleItem2);
            float factor14 = MathHelper.clamp_float((float)(Math.abs(this.coordOffset69) + (double)(offset13 > doubleItem2 ? factor10 : -factor10)), 0.0F, floatItem);
            floatItem4 = getFloat9(factor, factor4, flag11 ? 180.0F : factor14);
            offset12 = flag9 ? (double)Math.abs(this.rotation.pitch - this.rotation2.pitch) : 0.0;
            offset13 = Math.abs((double)factor13 / Math.max(this.coordOffset36, 1.0));
            doubleItem2 = Math.abs((this.coordOffset36 - offset12) / offset3);
            float factor15 = MathHelper.clamp_float((float)(Math.abs(this.coordOffset36) + (double)(offset13 > doubleItem2 ? factor11 : -factor11)), 0.0F, floatItem2);
            factor8 = getFloat9(factor2, factor5, flag11 ? 180.0F : factor15);
            this.coordOffset69 = (double)Math.abs(floatItem4 - factor);
            this.coordOffset36 = (double)Math.abs(factor8 - factor2);
         }

         doubleItem2 = HitBoxUtils.getDouble(MathHelper.getDouble4((double)factor), MathHelper.getDouble4((double)factor4));
         offset3 = (double)(factor5 - factor2);
         searchBlock:
         switch (text) {
            case "Basic":
               doubleItem2 = (double)factor3;
               floatItem4 += this.getScaleFactor(text4, doubleItem2, factor8);
               doubleItem2 = (double)factor3;
               factor8 += this.getScaleFactor(text4, doubleItem2, floatItem4);
               break;
            case "OnlyRotation":
               if (!((double)(-floatItem) <= doubleItem2) || !(doubleItem2 <= (double)floatItem) || !((double)(-floatItem2) <= offset3) || !(offset3 <= (double)floatItem2)) {
                  doubleItem2 = (double)factor3;
                  floatItem4 += this.getScaleFactor(text4, doubleItem2, factor8);
                  doubleItem2 = (double)factor3;
                  factor8 += this.getScaleFactor(text4, doubleItem2, floatItem4);
               }
               break;
            case "Doubled":
               doubleItem2 = (double)factor3;
               floatItem = this.getScaleFactor(text4, doubleItem2, factor8);
               doubleItem2 = (double)factor3;
               float scaleFactor2 = this.getScaleFactor(text4, doubleItem2, factor8);
               doubleItem2 = (double)factor3;
               float scaleFactor3 = this.getScaleFactor(text4, doubleItem2, factor8);
               doubleItem2 = (double)factor3;
               float scaleFactor4 = this.getScaleFactor(text4, doubleItem2, factor8);
               doubleItem2 = (double)factor3;
               float scaleFactor5 = this.getScaleFactor(text4, doubleItem2, floatItem4);
               doubleItem2 = (double)factor3;
               floatItem2 = this.getScaleFactor(text4, doubleItem2, floatItem4);
               doubleItem2 = (double)factor3;
               factor5 = this.getScaleFactor(text4, doubleItem2, floatItem4);
               doubleItem2 = (double)factor3;
               float scaleFactor6 = this.getScaleFactor(text4, doubleItem2, floatItem4);
               SettingProvider settingProvider3 = SettingContainer.settingProvider;
               doubleItem2 = (double)Math.max(floatItem, scaleFactor2);
               offset3 = (double)Math.min(floatItem, scaleFactor2);
               floatItem4 += settingProvider3.getFloat(offset3, doubleItem2);
               SettingProvider settingProvider4 = SettingContainer.settingProvider;
               doubleItem2 = (double)Math.max(scaleFactor3, scaleFactor4);
               offset3 = (double)Math.min(scaleFactor3, scaleFactor4);
               floatItem4 += settingProvider4.getFloat(offset3, doubleItem2);
               SettingProvider settingProvider5 = SettingContainer.settingProvider;
               doubleItem2 = (double)Math.max(scaleFactor5, floatItem2);
               offset3 = (double)Math.min(scaleFactor5, floatItem2);
               factor8 += settingProvider5.getFloat(offset3, doubleItem2);
               SettingProvider settingProvider6 = SettingContainer.settingProvider;
               doubleItem2 = (double)Math.max(factor5, scaleFactor6);
               offset3 = (double)Math.min(factor5, scaleFactor6);
               factor8 += settingProvider6.getFloat(offset3, doubleItem2);
               break;
            case "Hybrid":
               doubleItem2 = (double)factor3;
               doubleItem2 = (double)this.getScaleFactor(text4, doubleItem2, factor8);
               offset3 = (double)factor3;
               offset3 = (double)this.getScaleFactor(text4, offset3, floatItem4);
               MovingObjectPosition hitResult = RayTraceUtils.resolveMovingObjectPosition(1.0F, floatItem4, factor8, offset, 0.0);
               if (hitResult != null && hitResult.typeOfHit == MovingObjectType.ENTITY) {
                  for (byte byteValue3 = 1; byteValue3 < 10; byteValue3 += 1) {
                     MovingObjectPosition hitResult2 = RayTraceUtils.resolveMovingObjectPosition(
                        1.0F, (float)((double)floatItem4 + doubleItem2 / (double)byteValue3), (float)((double)factor8 + offset3 / (double)byteValue3), offset, 0.0
                     );
                     if (hitResult.typeOfHit != MovingObjectType.ENTITY || hitResult2.typeOfHit == MovingObjectType.ENTITY) {
                        floatItem4 += (float)(doubleItem2 / (double)byteValue3);
                        factor8 += (float)(offset3 / (double)byteValue3);
                        break searchBlock;
                     }
                  }
               } else {
                  floatItem4 += (float)doubleItem2;
                  factor8 += (float)offset3;
               }
               break;
            case "Circle":
               double offset14;
               if (this.coordOffset16 > 360.0) {
                  offset14 = 0.0;
               } else {
                  doubleItem2 = 0.7;
                  offset3 = 0.2;
                  offset14 = this.coordOffset16 + SettingContainer.settingProvider.getDouble4(offset3, doubleItem2);
               }

               this.coordOffset16 = offset14;
               MovingObjectPosition hitResult3 = RayTraceUtils.resolveMovingObjectPosition(
                  1.0F, getFloat9(factor, factor4, 180.0F), getFloat9(factor2, factor5, 180.0F), offset, 0.0
               );
               flag11 = hitResult3 != null && hitResult3.typeOfHit == MovingObjectType.ENTITY && hitResult3.entityHit instanceof EntityLivingBase;
               byte byteValue4 = 0;
               if (flag11) {
                  for (float factor16 = 7.0F; factor16 >= 0.0F; factor16 -= 0.1F) {
                     factor5 = (float)(Math.sin(this.coordOffset16) * (double)factor16);
                     float factor17 = (float)(Math.cos(this.coordOffset16) * (double)factor16);
                     Rotation rotation13 = getRotation8(floatItem4 + factor5, factor8 + factor17, factor, factor2);
                     floatItem = rotation13.yaw;
                     float pitch = rotation13.pitch;
                     MovingObjectPosition hitResult4 = RayTraceUtils.resolveMovingObjectPosition(1.0F, floatItem, pitch, offset, 0.0);
                     if (hitResult4 != null && hitResult4.typeOfHit == MovingObjectType.ENTITY && hitResult4.entityHit instanceof EntityLivingBase) {
                        byteValue4 += 1;
                        if (byteValue4 >= 2) {
                           floatItem4 += factor5;
                           factor8 += factor17;
                           break searchBlock;
                        }
                     } else {
                        byteValue4 = 0;
                     }
                  }
               } else {
                  offset3 = 9.0;
                  double offset15 = Math.sin(this.coordOffset16) * offset3;
                  double offset16 = Math.cos(this.coordOffset16) * offset3;
                  floatItem4 = (float)((double)floatItem4 + offset15);
                  factor8 = (float)((double)factor8 + offset16);
               }
               break;
            case "Advanced":
               boolean flag13 = true;
               Rotation rotation14 = this.getRotation(
                  flag13,
                  livingEntity,
                  floatItem4,
                  factor8,
                  factor,
                  factor2,
                  text4,
                  factor4,
                  factor5,
                  offset2,
                  flag7,
                  flag8,
                  setting5,
                  setting6,
                  offset,
                  flag6,
                  posX,
                  posX2,
                  posX3,
                  posX4,
                  posX5,
                  posX6,
                  posX7,
                  posX8,
                  posX9,
                  posY,
                  posZ
               );
               if (this.coordOffset49 != 0.0 || this.coordOffset89 != 0.0) {
                  floatItem4 = rotation14.yaw;
               }

               if (this.coordOffset49 != 0.0 || this.coordOffset89 != 0.0 || this.coordOffset28 != 0.0) {
                  factor8 = rotation14.pitch;
               }
               break;
            case "PolarTest":
               Rotation rotation15 = this.getRotation(livingEntity, factor, factor2, floatItem4, factor8, factor4, factor5, offset);
               floatItem4 = rotation15.yaw;
               factor8 = rotation15.pitch;
         }

         if (flag5) {
            factor8 += (float)(Math.sin(0.06981317007977318 * (double)(getFloat9(factor, factor4, 180.0F) - floatItem4)) * 8.0);
         }

         if (flag3 && count4 > 9) {
            doubleItem2 = 0.5;
            offset3 = -0.5;
            floatItem4 = factor + SettingContainer.settingProvider.getFloat3(offset3, doubleItem2);
            doubleItem2 = 0.5;
            offset3 = -0.5;
            factor8 = factor2 + SettingContainer.settingProvider.getFloat3(offset3, doubleItem2);
         }

         Rotation rotation16 = getRotation8(floatItem4, factor8, factor, factor2);
         floatItem4 = rotation16.yaw;
         factor8 = rotation16.pitch;
         if (timerRangeV.checkCondition() && this.customFloatEntry != null && ClientGlobals.moduleManager.killAura.settingTimerRangeV2Fix.isEnabled()) {
            this.customFloatEntry.applyFloat(floatItem4, factor8, false);
            doubleItem2 = (double)factor8;
            this.customFloatEntry.applyDouble(doubleItem2);
         }

         vec3 = new Vec3(this.coordOffset49, this.coordOffset28, this.coordOffset89);
         if (!text5.equals("None")
            && (text5.equals("Always") || livingEntity.hurtTime <= 2 || CombatRandomizer.matchesEntity(livingEntity, factor))
            && Math.random() <= (double)count5 / 100.0
            && (vec3.lengthVector() <= offset || (double)MinecraftAccessor.minecraft.thePlayer.getDistanceToEntity(livingEntity) <= offset)) {
            MovingObjectPosition hitResult5 = RayTraceUtils.resolveMovingObjectPosition(1.0F, floatItem4, factor8, offset, 0.0);
            if (hitResult5 != null && !(hitResult5.entityHit instanceof EntityLivingBase)) {
               MovingObjectPosition hitResult6 = RayTraceUtils.resolveMovingObjectPosition(1.0F, floatItem4, factor8, 6.0, 0.0);
               MovingObjectPosition hitResult7 = RayTraceUtils.resolveMovingObjectPosition(1.0F, factor6, factor7, 6.0, 0.0);
               if (hitResult6 != null && hitResult7 != null && hitResult6.entityHit instanceof EntityLivingBase && hitResult7.entityHit == hitResult6.entityHit) {
                  Vec3 vec33 = MinecraftAccessor.minecraft.thePlayer.getVec35(1.0F);
                  vec4 = livingEntity.getVec32(1.0F);
                  AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(vec4, true);
                  double[] coordOffsetArray2 = TrajectoryPredictor.getCoordOffsetArray2(vec33, axisAlignedBB);
                  double[] coordOffsetArrayByVec = TrajectoryPredictor.getCoordOffsetArrayByVec3(vec33, axisAlignedBB);
                  hitResult6.hitVec.getVec311(vec33).lengthVector();
                  doubleItem2 = MathHelper.getDouble4((double)factor6);
                  doubleItem2 = TrajectoryPredictor.getCoordinate(axisAlignedBB, vec33, doubleItem2);
                  offset3 = (double)factor7;
                  offset3 = TrajectoryPredictor.getCoordinate(axisAlignedBB, vec33, keySalt2, offset3);
                  double doubleItem4 = MathHelper.getDouble4((double)factor);
                  doubleItem4 = TrajectoryPredictor.getCoordinate(axisAlignedBB, vec33, doubleItem4);
                  double offset17 = (double)factor2;
                  offset17 = TrajectoryPredictor.getCoordinate(axisAlignedBB, vec33, keySalt2, offset17);
                  Rotation rotation17 = null;
                  Rotation rotation18 = null;

                  for (byte byteValue5 = 0; byteValue5 <= 50; byteValue5 += 1) {
                     double doubleItem5 = PlayerUtil.getDouble(doubleItem4, doubleItem2, (double)byteValue5 / 50.0);
                     double doubleItem6 = PlayerUtil.getDouble(offset17, offset3, (double)byteValue5 / 50.0);
                     doubleItem5 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(coordOffsetArray2, doubleItem5));
                     doubleItem6 = TrajectoryPredictor.getCoordinate(coordOffsetArrayByVec, doubleItem6);
                     Rotation rotation19 = getRotation8(getFloat9(factor, (float)doubleItem5, 180.0F), (float)doubleItem6, factor, factor2);
                     MovingObjectPosition movingObjectPosition = CombatRayTrace.getMovingObjectPosition(1.0F, rotation19.yaw, rotation19.pitch);
                     if (movingObjectPosition != null && movingObjectPosition.entityHit instanceof EntityLivingBase) {
                        AxisAlignedBB boundingBox = movingObjectPosition.entityHit.getBoundingBox2();
                        Vec3 hitVec = movingObjectPosition.hitVec;
                        this.coordOffset29 = hitVec.xCoord - boundingBox.minX;
                        this.coordOffset40 = hitVec.yCoord - boundingBox.minY;
                        this.coordOffset12 = hitVec.zCoord - boundingBox.minZ;
                        this.timer.reset();
                        if (!(movingObjectPosition.hitVec.getVec311(vec33).lengthVector() > posX10)) {
                           if (rotation17 == null) {
                              for (int index = ClientGlobals.rotationManager.getBacktrackRecord().getItemList().size() - 1;
                                 index >= 0 && rotation18 == null;
                                 index -= 1
                              ) {
                                 Rotation object = (Rotation)ClientGlobals.rotationManager.getBacktrackRecord().getObject(index);
                                 if (rotation17 == null && !rotation19.checkRotation(object)) {
                                    rotation17 = object;
                                 } else if (rotation17 != null && !rotation17.checkRotation(object)) {
                                    rotation18 = object;
                                 }
                              }

                              if (rotation17 == null) {
                                 rotation17 = ClientGlobals.rotationManager.getRotation3();
                              }

                              if (rotation18 == null) {
                                 rotation18 = ClientGlobals.rotationManager.getRotation();
                              }
                           }

                           doubleItem5 = getCoordinate(rotation19, rotation17, rotation18);
                           if (doubleItem5 == 0.0 || !(doubleItem5 < posX11)) {
                              double offset18 = (double)MathHelper.sqrt_float(rotation19.yaw);
                              doubleItem5 = (double)MathHelper.sqrt_float(factor6);
                              doubleItem6 = offset18;
                              doubleItem5 = HitBoxUtils.getDouble(doubleItem6, doubleItem5);
                              doubleItem6 = (double)(rotation19.pitch - factor7);
                              if ((!text6.equals("Separate") || !(Math.abs(doubleItem5) < posX12))
                                 && !(Math.abs(doubleItem6) < posY2)
                                 && (!text6.equals("Combined") || !(Math.sqrt(doubleItem5 * doubleItem5 + doubleItem6 * doubleItem6) < posZ2))) {
                                 if (((Module)ClientGlobals.moduleManager.debug).isToggled() && ClientGlobals.moduleManager.debug.booleanSetting17 != null) {
                                    Client.getClient().getClass();
                                 }

                                 floatItem4 = rotation19.yaw;
                                 factor8 = rotation19.pitch;
                                 if (this.customFloatEntry != null) {
                                    this.customFloatEntry.applyFloat(floatItem4, factor8, false);
                                    doubleItem2 = (double)factor8;
                                    this.customFloatEntry.applyDouble(doubleItem2);
                                 }
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         this.targetEntity = livingEntity;
         return new Rotation(floatItem4, factor8);
      }
   }

   public Rotation getRotation(double posX, double posY, double posZ, float factor, float factor2, boolean flag) {
      double offset = posX - MinecraftAccessor.minecraft.thePlayer.posX;
      double offset2 = posY + 1.53 - (MinecraftAccessor.minecraft.thePlayer.posY + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2());
      double offset3 = posZ - MinecraftAccessor.minecraft.thePlayer.posZ;
      float factor3 = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor4 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      float floatItem = getFloat9(factor, factor3, 180.0F);
      float floatItem2 = getFloat9(factor2, factor4, 180.0F);
      if (flag) {
         floatItem += (float)ThreadLocalRandom.current().nextGaussian();
         floatItem2 += (float)ThreadLocalRandom.current().nextGaussian();
      }

      return getRotation8(floatItem, floatItem2, factor, factor2);
   }

   public static float getFloat3(float factor, float factor2) {
      return getRotation8(0.0F, factor, 0.0F, factor2).pitch;
   }

   public void processAction(Rotation rotation5, Rotation rotation6) {
      this.coordOffset69 = (double)Math.abs(rotation5.yaw - rotation6.yaw);
      this.coordOffset36 = (double)Math.abs(rotation5.pitch - rotation6.pitch);
   }

   public Rotation getRotation(Entity entity2, float factor, float factor2, boolean flag) {
      long value = 0x3f226354e649L;
      int count = (int)((0x77a2ed254aafL ^ value ^ 0x6e86e973556L) << 16 >>> 48);
      int count2 = (int)((0x77a2ed254aafL ^ value ^ 0x6e86e973556L) << 32 >>> 32);
      short shortValue = (short)count;
      Vec3 vec3 = getVec3(entity2, shortValue, count2);
      double offset = vec3.xCoord - MinecraftAccessor.minecraft.thePlayer.posX;
      double offset2 = vec3.yCoord - (MinecraftAccessor.minecraft.thePlayer.posY + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2());
      double offset3 = vec3.zCoord - MinecraftAccessor.minecraft.thePlayer.posZ;
      float factor3 = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor4 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      float floatItem = getFloat9(factor, factor3, 180.0F);
      float floatItem2 = getFloat9(factor2, factor4, 180.0F);
      if (flag) {
         floatItem += (float)ThreadLocalRandom.current().nextGaussian();
         floatItem2 += (float)ThreadLocalRandom.current().nextGaussian();
      }

      return getRotation8(floatItem, floatItem2, factor, factor2);
   }

   public MotionVectorSnapshot getSecondaryDoubleEntry(Rotation rotation5, Rotation rotation6, int count, double offset, double offset2, boolean flag) {
      double yaw = (double)rotation5.yaw;
      double pitch = (double)rotation5.pitch;
      double yaw2 = (double)rotation6.yaw;
      double pitch2 = (double)rotation6.pitch;
      double offset3 = yaw2;
      double offset4 = pitch;
      double offset5 = yaw;
      return this.getSecondaryDoubleEntry(offset5, offset4, offset3, pitch2, count, offset, offset2, flag);
   }

   public void processAction(float factor, int count) {

      try {
         if (this.itemList2.isEmpty()) {
            String text = OnlineConfigsManager.text + "/ai";
            String text2 = "rotationData.json";
            String text3 = text;
            this.itemList2 = this.getArrayList(text3, text2);
         }
      } catch (Exception exception) {
      }

      this.auxiliaryFloatEntry = null;
      this.itemList.clear();
      this.itemList.add(new RotationAngleData(0.0F, 0.0F, 0.0F));

      for (int index = 0; index < count; index++) {
         double offset = (Math.PI * 2) * Math.random();
         double offset2 = Math.sqrt(Math.random());
         float factor2 = (float)(offset2 * Math.cos(offset)) * factor;
         float factor3 = (float)(offset2 * Math.sin(offset)) * factor;
         float factor4 = (float)Math.sqrt((double)(factor2 * factor2 + factor3 * factor3));
         RotationAngleData rotationAngleData = new RotationAngleData(factor2, factor3, factor4);
         this.itemList.add(rotationAngleData);
      }
   }

   public static Vec3 getVec3(Entity entity2, float factor, int count, int count2) {
      Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec35(factor);
      double offset = (double)factor;
      AxisAlignedBB boundingBox = processAction(entity2, offset);
      double offset2 = boundingBox.maxX - boundingBox.minX;
      double offset3 = boundingBox.maxY - boundingBox.minY;
      double offset4 = offset2 * ((double)count2 / 100.0);
      double offset5 = offset3 * ((double)count / 100.0);
      AxisAlignedBB boundingBox2 = boundingBox.offset((offset4 - offset2) / 2.0, (offset5 - offset3) / 2.0, (offset4 - offset2) / 2.0);
      double doubleItem = MathHelper.getDouble7(vec3.xCoord, boundingBox2.minX, boundingBox2.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec3.yCoord, boundingBox2.minY, boundingBox2.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec3.zCoord, boundingBox2.minZ, boundingBox2.maxZ);
      return new Vec3(doubleItem, doubleItem2, doubleItem3);
   }

   private boolean checkCondition(int count) {
      return SettingContainer.settingProvider.getInt2(0, 99) < count;
   }

   public Rotation getRotation(EntityLivingBase livingEntity, EntityLivingBase livingEntity2, float factor, float factor2, double offset, double offset2, Vec3 vec) {
      float factor3 = factor2;
      float factor4 = MathHelper.sqrt_float(factor);
      double offset3 = (double)factor4;
      double offset4 = (double)factor2;
      if (this.indexOffset27 != livingEntity2.ticksExisted - 1) {
         SimulationSnapshot simulationSnapshot = new SimulationSnapshot(
            HitBoxUtils.getDouble(MathHelper.getDouble4((double)MinecraftAccessor.minecraft.thePlayer.getScaleFactor11()), (double)factor4) / 3.0,
            (double)((factor2 - MinecraftAccessor.minecraft.thePlayer.getScaleFactor27()) / 3.0F)
         );
         this.updateSupplementalItemEntry(simulationSnapshot);
         advancedDoubleEntry = new PlayerSimulationData(livingEntity2);
         advancedDoubleEntry78 = new PlayerSimulationData(livingEntity);
      }

      this.indexOffset27 = MinecraftAccessor.minecraft.thePlayer.ticksExisted;
      if (advancedDoubleEntry == null || advancedDoubleEntry.targetEntity != livingEntity2) {
         advancedDoubleEntry = new PlayerSimulationData(livingEntity2);
      }

      if (advancedDoubleEntry78 == null || advancedDoubleEntry78.targetEntity != livingEntity) {
         advancedDoubleEntry78 = new PlayerSimulationData(livingEntity);
      }

      PlayerSimulationData playerSimulationData = advancedDoubleEntry;
      double offset5 = 1.0 + Math.random();
      playerSimulationData.applyDouble(offset5);
      PlayerSimulationData playerSimulationData2 = advancedDoubleEntry78;
      double offset6 = 1.0 + Math.random();
      playerSimulationData2.applyDouble(offset6);
      double offset7 = 90.0;
      double offset8 = -90.0;
      int count2 = ThreadLocalRandom.current().nextInt(4, 6);
      EntityPlayerSP player = MinecraftAccessor.minecraft.thePlayer;
      double posX = MinecraftAccessor.minecraft.thePlayer.posX;
      double posY = MinecraftAccessor.minecraft.thePlayer.posY;
      double posZ = MinecraftAccessor.minecraft.thePlayer.posZ;
      EventMove event = new EventMove(
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveStrafe,
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveForward,
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.state,
         MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.state2,
         ArrowDodgerPhase.PRE
      );
      double offset9 = posZ;
      double offset10 = posY;
      double offset11 = posX;
      ArrayList list = CombatUtil.onMove(count2, player, offset11, offset10, offset9, event);
      int size = list.size();
      ArrayList list2 = CombatTargetFilter.resolveArrayListByEntityLivingBase(livingEntity, size);
      list.add(MinecraftAccessor.minecraft.thePlayer.getVec32(1.0F));
      list2.add(livingEntity.getVec32(1.0F));
      int count3 = Math.min(list.size(), list2.size());

      for (int index = 0; index < count3; index++) {
         AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB((Vec3)list2.get(index), true);
         double[] coordOffsetArrayByVec = TrajectoryPredictor.getCoordOffsetArrayByVec3(((Vec3)list.get(index)).getVec312(0.0, (double)livingEntity2.getHeight2(), 0.0), axisAlignedBB);
         offset7 = Math.min(coordOffsetArrayByVec[0], offset7);
         offset8 = Math.max(coordOffsetArrayByVec[1], offset8);
      }

      double offset12 = 1.0;

      for (byte byteValue = 0; byteValue < 3; byteValue += 1) {
         float factor5 = 1.0F;
         float factor6 = ((float)livingEntity2.ticksExisted + factor5) / 20.0F;
         Vec3 vec3 = livingEntity2.getVec32(1.0F).getVec312(0.0, (double)livingEntity2.getHeight2(), 0.0);
         Vec3 vec32 = advancedDoubleEntry.getVec3().getVec312(0.0, (double)livingEntity2.getHeight2(), 0.0);
         Vec3 vec33 = livingEntity.getVec32(factor5);
         Vec3 vec34 = advancedDoubleEntry78.getVec3();
         AxisAlignedBB axisAlignedBB2 = HitBoxUtils.getAxisAlignedBB(vec33, true);
         AxisAlignedBB axisAlignedBB3 = HitBoxUtils.getAxisAlignedBB(vec34, true);
         double[] coordOffsetArray2 = TrajectoryPredictor.getCoordOffsetArray2(vec3, axisAlignedBB2);
         ArrayListData tertiaryArrayListEntry2 = this.tertiaryArrayListEntry34;
         double offset13 = TrajectoryPredictor.getDouble4(this.coordOffset72, coordOffsetArray2[0]) * 3.0;
         tertiaryArrayListEntry2.processAction(offset13);
         this.coordOffset72 = coordOffsetArray2[0];
         double coordinate = TrajectoryPredictor.getCoordinate(axisAlignedBB2, vec3, 0L, offset2);
         double offset14 = (double)factor4;
         double coordinate2 = TrajectoryPredictor.getCoordinate(axisAlignedBB2, vec3, offset14);
         double offset15 = (double)factor3;
         double coordinate3 = TrajectoryPredictor.getCoordinate(axisAlignedBB2, vec3, 0L, offset15);
         posX = (double)factor4;
         double offset16 = (double)factor3;
         double offset17 = posX;
         double offset18 = getCoordOffsetArray(vec32, axisAlignedBB3, offset17, offset16)[0];
         if (this.coordOffset84 > 1.0) {
            this.secondaryDoubleEntry = null;
            this.coordOffset84 = 0.0;
            this.coordOffset58 = 0.0;
         }

         if (this.secondaryDoubleEntry == null
            && (
               coordinate3 < 0.0
                  || coordinate3 > 1.0
                  || coordinate2 < 0.0
                  || coordinate2 > 1.0
                  || offset18 < 0.0
                  || offset18 > 1.0
                  || Math.random() < -Math.pow(1.0 - this.coordOffset26, 0.3333333333333333) + 1.0
            )) {
            SimulationSnapshot simulationSnapshot2 = new SimulationSnapshot(MathHelper.getDouble7(coordinate2, 0.0, 1.0), MathHelper.getDouble7(coordinate3, 0.0, 1.0));
            this.coordOffset84 = 0.3333333333333333;
            this.secondaryDoubleEntry = new MotionVectorSnapshot();
            float factor7 = (float)Math.random();
            this.secondaryDoubleEntry.arrayList.add(simulationSnapshot2);
            this.secondaryDoubleEntry
               .arrayList
               .add(new SimulationSnapshot((double)Math.abs(RecordNode16Helper.computeFactor(factor6, factor7)), PlayerUtil.getDouble(coordinate, Math.random(), 0.5)));
            this.secondaryDoubleEntry
               .arrayList
               .add(
                  new SimulationSnapshot(
                     (double)Math.abs(RecordNode16Helper.computeFactor(factor6 + 0.05F, factor7 + 50.0F)), PlayerUtil.getDouble(coordinate, Math.random(), 0.5)
                  )
               );
         }

         double doubleItem = MathHelper.getDouble7(coordinate2, 0.0, 1.0);
         double doubleItem2 = MathHelper.getDouble7(coordinate3, 0.0, 1.0);
         if (this.secondaryDoubleEntry != null) {
            double coordOffset3 = this.coordOffset84;
            SimulationSnapshot simulationSnapshot3 = this.secondaryDoubleEntry.resolveSimulationSnapshot(coordOffset3);
            if (simulationSnapshot3 != null) {
               doubleItem = simulationSnapshot3.value;
               doubleItem2 = simulationSnapshot3.value2;
            }
         }

         double doubleItem3 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(coordOffsetArray2, doubleItem));
         double coordinate4 = TrajectoryPredictor.getCoordinate(TrajectoryPredictor.getCoordOffsetArrayByVec3(vec3, axisAlignedBB2), doubleItem2);
         if (offset8 >= offset7) {
            coordinate4 = PlayerUtil.getDouble(MathHelper.getDouble7((double)factor3, offset7, offset8), (offset7 + offset8) * 0.5, 0.01 * Math.random());
         }

         if (RayTraceUtils.getMovingObjectPosition6(factor5, (float)doubleItem3, (float)coordinate4) == null) {
            return new Rotation((float)offset3, (float)offset4);
         }

         double doubleItem4 = PlayerUtil.getDouble(coordinate4, offset2, ThreadLocalRandom.current().nextDouble(0.0, 0.01));
         double doubleItem5 = HitBoxUtils.getDouble((double)factor4, doubleItem3);
         double offset19 = doubleItem4 - (double)factor3;
         double offset20 = 4.0 + MathHelper.getDouble7(this.coordOffset93 / 100.0, 0.0, 1.0) * 4.0;
         if (Math.abs(doubleItem5) > 45.0) {
            offset20 += (Math.abs(doubleItem5) - 45.0) / 135.0 * MathHelper.getDouble7(this.coordOffset4, 0.0, 1.0) * 5.0;
         }

         double offset21 = offset20
            + MathHelper.getDouble7(Math.sqrt(this.tertiaryArrayListEntry34.getDouble7()) / 20.0, 0.0, 1.0)
               * (MathHelper.getDouble7(this.coordOffset59 / 100.0, 0.0, 1.0) + 0.5);
         SimulationSnapshot simulationSnapshot4 = new SimulationSnapshot(
            doubleItem5 / 0.15 + (double)RecordNode16Helper.computeFactor(factor6 * 2.0F, 0.0F),
            (offset19 + (Math.abs(offset19) > 0.6 ? 0.0 : Math.sin((Math.PI / 5) * (double)factor6 * 20.0) * 0.6)) / 0.15
         );
         double offset22 = simulationSnapshot4.sqrt();
         double offset23 = 1.0;
         double offset24 = 1.0;
         if (Math.random() < 0.9
            && this.supplementalItemEntry.sqrt() < 13.333333333333334
            && this.supplementalItemEntry.computeValueBySimulationSnapsh(simulationSnapshot4) < 70.0) {
            offset21 = Math.min(offset21, offset22) / 5.0;
         }

         double offset25 = (new SimulationSnapshot(doubleItem5 / 0.15 / offset24, offset19 / 0.15 / offset23)).sqrt();
         SimulationSnapshot simulationSnapshot5 = new SimulationSnapshot(
            (simulationSnapshot4.value - this.supplementalItemEntry.value) * Math.min(offset25, offset21) / offset25,
            (simulationSnapshot4.value2 - this.supplementalItemEntry.value2) * Math.min(offset22, offset21) / offset22 / offset23
         );
         if (Double.isNaN(simulationSnapshot5.value) || Double.isNaN(simulationSnapshot5.value2)) {
            SimulationSnapshot simulationSnapshot6 = new SimulationSnapshot(Math.random(), Math.random());
            double offset26 = 0.01;
            simulationSnapshot5 = simulationSnapshot6.resolveSimulationSnapshotByDouble(offset26);
         }

         SimulationSnapshot simulationSnapshot7 = new SimulationSnapshot(this.supplementalItemEntry.value, this.supplementalItemEntry.value2);
         if (Math.signum(this.supplementalItemEntry.value) == Math.signum(simulationSnapshot5.value)) {
            double offset27 = Math.abs(this.supplementalItemEntry.value);
            double offset28 = Math.abs(simulationSnapshot5.value);
            if (-0.5 * (offset27 * offset27 / (offset28 * offset28)) + offset27 * offset27 / offset28 > Math.abs(simulationSnapshot4.value)) {
               simulationSnapshot5.value *= -1.0;
            }
         }

         if (Math.signum(this.supplementalItemEntry.value2) == Math.signum(simulationSnapshot5.value2)) {
            double offset29 = Math.abs(this.supplementalItemEntry.value2);
            double offset30 = Math.abs(simulationSnapshot5.value2);
            if (-0.5 * (offset29 * offset29 / (offset30 * offset30)) + offset29 * offset29 / offset30 > Math.abs(simulationSnapshot4.value2)) {
               simulationSnapshot5.value2 *= -1.0;
            }
         }

         SimulationSnapshot simulationSnapshot8 = simulationSnapshot7.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot5);
         double offset31 = 1.0 - offset12;
         SimulationSnapshot simulationSnapshot9 = this.supplementalItemEntry.resolveSimulationSnapshotByDouble(offset31);
         SimulationSnapshot simulationSnapshot10 = simulationSnapshot8.resolveSimulationSnapshotByDouble(offset12);
         this.supplementalItemEntry = simulationSnapshot9.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot10);
         factor4 = MathHelper.sqrt_float(factor4 + (float)(this.supplementalItemEntry.value * 0.15 * offset12));
         factor3 = MathHelper.clamp_float(factor3 + (float)(this.supplementalItemEntry.value2 * 0.15 * offset12), -90.0F, 90.0F);
         double offset32 = 0.2;
         this.coordOffset84 += offset32 / 3.0;
      }

      if (this.state57) {
         float factor8 = 2.0F;
         float factor9 = -2.0F;
         factor4 += SettingContainer.settingProvider.getFloat2(factor9, factor8);
         float factor10 = 2.0F;
         float factor11 = -2.0F;
         factor3 += SettingContainer.settingProvider.getFloat2(factor11, factor10);
      }

      return new Rotation(factor4, factor3);
   }

   public static double[] getCoordOffsetArray(Vec3 vec, Vec3 vec2, AxisAlignedBB boundingBox, AxisAlignedBB boundingBox2, double offset, double offset2) {
      double doubleItem = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(TrajectoryPredictor.getCoordOffsetArray2(vec, boundingBox), offset));
      double coordinate = TrajectoryPredictor.getCoordinate(TrajectoryPredictor.getCoordOffsetArrayByVec3(vec, boundingBox), offset2);
      double doubleItem2 = MathHelper.getDouble7(TrajectoryPredictor.getCoordinate(boundingBox2, vec2, doubleItem), 0.0, 1.0);
      double doubleItem3 = MathHelper.getDouble7(TrajectoryPredictor.getCoordinate(boundingBox2, vec2, 0x611b6684d3d5L, coordinate), 0.0, 1.0);
      return new double[]{doubleItem2, doubleItem3};
   }

   public static boolean checkCondition(float factor) {
      float factor2 = MathHelper.sqrt_float(factor);
      if ((double)factor2 > 77.5 && (double)factor2 < 102.5) {
         return true;
      } else if (!((double)factor2 > 167.5) && !(factor2 < -167.0F)) {
         return (double)factor2 < -77.5 && (double)factor2 > -102.5 ? true : (double)factor2 > -12.5 && (double)factor2 < 12.5;
      } else {
         return true;
      }
   }

   public SimulationBoundingBox getCustomDoubleEntry() {
      double doubleItem = MathHelper.getDouble4((double)MinecraftAccessor.minecraft.thePlayer.scaleFactor11);
      double scaleFactor2 = (double)MinecraftAccessor.minecraft.thePlayer.scaleFactor27;
      SimulationSnapshot simulationSnapshot = new SimulationSnapshot(
         HitBoxUtils.getDouble(MathHelper.getDouble4((double)MinecraftAccessor.minecraft.thePlayer.scaleFactor30), doubleItem) / 0.15 / 3.0,
         (scaleFactor2 - (double)MinecraftAccessor.minecraft.thePlayer.scaleFactor20) / 0.15 / 3.0
      );
      return new SimulationBoundingBox(simulationSnapshot, doubleItem, scaleFactor2);
   }

   private float processAction(float factor) {
      float factor2 = Math.max(0.001F, Math.abs(factor));
      return MathHelper.clamp_float(factor2, 0.001F, 30.0F);
   }

   public SimulationBoundingBox getCustomDoubleEntry(RandomizationGenerator randomizationGenerator, SimulationBoundingBox simulationBoundingBox, MotionVectorSnapshot motionVectorSnapshot, int count, double offset) {
      SimulationBoundingBox simulationBoundingBox2 = randomizationGenerator.resolveSimulationBoundingBox(simulationBoundingBox, motionVectorSnapshot, offset);
      double offset2 = 1.0 / (double)(count + 1);
      motionVectorSnapshot.applyDouble(offset2);
      return simulationBoundingBox2;
   }

   public static float getFloat5(float posX, float posY, float posZ) {
      float floatItem = getFloat9(posX, posY, posZ);
      return floatItem == posX ? posX : getFloat3(floatItem, posX);
   }

   public static Rotation getRotation(int[] countArray, float factor, float factor2) {
      return getRotation11(countArray[0], countArray[1], factor, factor2);
   }

   public static Vec3 getVec3(Entity entity2, int count, int count2, Rotation rotation5) {
      Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec35(1.0F);
      float height = entity2.getHeight();
      AxisAlignedBB boundingBox = entity2.getBoundingBox2().offset((double)height, (double)height, (double)height);
      double offset = boundingBox.maxX - boundingBox.minX;
      double offset2 = boundingBox.maxY - boundingBox.minY;
      double offset3 = offset * ((double)count2 / 100.0);
      double offset4 = offset2 * ((double)count / 100.0);
      AxisAlignedBB boundingBox2 = boundingBox.offset((offset3 - offset) / 2.0, (offset4 - offset2) / 2.0, (offset3 - offset) / 2.0);
      double coordinate = vec3.getCoordinate4(boundingBox2.getVec3());
      Vec3 vec32 = MinecraftAccessor.minecraft.thePlayer.getVec34(rotation5.pitch, rotation5.yaw);
      Vec3 vec33 = vec3.getVec312(vec32.xCoord * coordinate, vec32.yCoord * coordinate, vec32.zCoord * coordinate);
      double doubleItem = MathHelper.getDouble7(vec33.xCoord, boundingBox2.minX, boundingBox2.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec33.yCoord, boundingBox2.minY, boundingBox2.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec33.zCoord, boundingBox2.minZ, boundingBox2.maxZ);
      return new Vec3(doubleItem, doubleItem2, doubleItem3);
   }

   private RotationAngleData getAuxiliaryFloatEntry(ArrayList list, float factor, float factor2) {
      list.sort(Comparator.comparingDouble(entry -> SimulatedPlayer.getCoordinate4(factor, factor2, (RotationAngleData)entry)));
      return (RotationAngleData)list.get(0);
   }

   public static float getFloat6(float factor) {
      float factor2 = factor % 360.0F;
      if (factor2 > 180.0F) {
         factor2 -= 360.0F;
      }

      if (factor2 < -180.0F) {
         factor2 += 360.0F;
      }

      return factor2;
   }

   public static float getFloat7(float posX, float posY, float posZ) {
      float factor = posZ;
      float floatItem = getFloat6(posY - posX);
      if (!(floatItem > 10.0F) && !(floatItem < -10.0F)) {
         factor = 1.0F;
      }

      return posX + factor * floatItem;
   }

   public static Vec3 getVec3(Entity entity2, short shortValue, int count) {
      Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec35(1.0F);
      float height = entity2.getHeight();
      AxisAlignedBB boundingBox = entity2.getBoundingBox2().offset((double)height, (double)height, (double)height);
      double doubleItem = MathHelper.getDouble7(vec3.xCoord, boundingBox.minX, boundingBox.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec3.yCoord, boundingBox.minY, boundingBox.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec3.zCoord, boundingBox.minZ, boundingBox.maxZ);
      return new Vec3(doubleItem, doubleItem2, doubleItem3);
   }

   private float getScaleFactor(String text, double offset, float factor) {
      switch (text) {
         case "Random":
            double offset2 = -offset;
            double offset3 = offset2;
            return SettingContainer.settingProvider.getFloat3(offset3, offset);
         case "Gaussian":
            return (float)(ThreadLocalRandom.current().nextGaussian() * offset);
         case "SecureRandom":
            double offset4 = -offset;
            return SettingContainer.settingProvider.getFloat(offset4, offset);
         case "Intave":
            double offset5 = 2.0;
            double offset6 = 1.0;
            return (float)((double)((float)((double)SettingContainer.settingProvider.getFloat(offset6, offset5) * offset)) * Math.sin((double)factor * Math.PI));
         default:
            return 1.0F;
      }
   }

   public static Rotation getRotation2(double posX, double posY, double posZ, Rotation rotation5, float factor, float factor2, boolean flag) {
      double offset = posX - MinecraftAccessor.minecraft.thePlayer.posX;
      double offset2 = posY - (MinecraftAccessor.minecraft.thePlayer.posY + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2());
      double offset3 = posZ - MinecraftAccessor.minecraft.thePlayer.posZ;
      float factor3 = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor4 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      float floatItem = getFloat9(rotation5.yaw, factor3, factor);
      float floatItem2 = getFloat9(rotation5.pitch, factor4, factor2);
      if (flag) {
         double offset4 = 1.5;
         double offset5 = -1.5;
         floatItem += SettingContainer.settingProvider.getFloat3(offset5, offset4);
         double offset6 = 1.5;
         double offset7 = -1.5;
         floatItem2 += SettingContainer.settingProvider.getFloat3(offset7, offset6);
      }

      return new Rotation(floatItem, floatItem2);
   }

   private void applyEntityLivingBase(EntityLivingBase livingEntity, float factor, float factor2) {
      if (this.targetEntity != null && livingEntity != null && livingEntity.getEntityId() == this.targetEntity.getEntityId()) {
         this.rotation = new Rotation(this.rotation2);
         this.rotation2 = new Rotation(factor, factor2);
      } else {
         this.rotation = new Rotation(factor, factor2);
         this.rotation2 = new Rotation(factor, factor2);
      }
   }

   public MotionVectorSnapshot getSecondaryDoubleEntry(double posX, double posX2, double posY, double posZ, int count, double offset, double offset2, boolean flag) {
      SimulationSnapshot simulationSnapshot = new SimulationSnapshot(MathHelper.getDouble4(posX), MathHelper.getDouble7(posX2, -90.0, 90.0));
      SimulationSnapshot simulationSnapshot2 = new SimulationSnapshot(HitBoxUtils.getDouble(simulationSnapshot.value, MathHelper.getDouble4(posY)), posZ - simulationSnapshot.value2);
      SimulationSnapshot simulationSnapshotAsSimulationSnapsh = simulationSnapshot2.getSimulationSnapshotAsSimulationSnapsh();
      if (simulationSnapshot2.sqrt() == 0.0) {
         simulationSnapshotAsSimulationSnapsh = simulationSnapshot2;
      }

      SimulationSnapshot simulationSnapshot3 = simulationSnapshotAsSimulationSnapsh.getSimulationSnapshot();
      if (!Double.isFinite(simulationSnapshot3.value) || !Double.isFinite(simulationSnapshot3.value2)) {
         simulationSnapshot3 = new SimulationSnapshot(0.0, 0.0);
      }

      double offset3 = ThreadLocalRandom.current().nextDouble(0.4, 0.6);
      double doubleItem = MathHelper.getDouble7(ThreadLocalRandom.current().nextDouble(-1.0, 1.0) * simulationSnapshot2.sqrt() * offset, -offset2, offset2);
      SimulationSnapshot simulationSnapshot4 = simulationSnapshot2.resolveSimulationSnapshotByDouble(offset3);
      SimulationSnapshot simulationSnapshot5 = simulationSnapshot3.resolveSimulationSnapshotByDouble(doubleItem);
      SimulationSnapshot simulationSnapshot6 = simulationSnapshot4.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot5);
      if (simulationSnapshot3.sqrt() == 0.0) {
         simulationSnapshot6 = new SimulationSnapshot(0.0, 0.0);
      }

      MotionVectorSnapshot motionVectorSnapshot = new MotionVectorSnapshot();
      motionVectorSnapshot.arrayList.add(simulationSnapshot);
      motionVectorSnapshot.arrayList.add(simulationSnapshot6.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot));
      motionVectorSnapshot.arrayList.add(simulationSnapshot2.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot));
      if (flag) {
         motionVectorSnapshot.arrayList.add(simulationSnapshot2.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot));
      }

      double offset4 = 1.0 / (double)(count + 1);
      motionVectorSnapshot.applyDoubleByDouble(offset4);
      motionVectorSnapshot.count = count;
      return motionVectorSnapshot;
   }

   private double getCoordinate2(float factor, float factor2, RotationAngleData rotationAngleData) {
      Rotation rotation5 = this.rotation3;
      Rotation rotation6 = this.rotation4;
      Rotation rotation7 = new Rotation(factor, factor2);
      Rotation rotation8 = rotation6;
      Rotation rotation9 = rotation5;
      return (double)(180.0F - rotationAngleData.computeFactor(rotation9, rotation8, rotation7));
   }

   public static int[] getIndexOffsetArray(Rotation rotation5, Rotation rotation6) {
      return getIntArray(rotation5.yaw, rotation5.pitch, rotation6.yaw, rotation6.pitch);
   }

   private Vec3 getVec34(Vec3 vec, Vec3 vec2, double offset, boolean flag) {
      Vec3 vec3 = vec.getVec311(vec2);
      double offset2 = vec3.lengthVector();
      Vec3 vec4;
      if (offset2 < offset) {
         vec4 = new Vec3(0.0, 0.0, 0.0);
      } else {
         vec4 = vec3.getVec3(offset / offset2);
         if (flag) {
            RayTraceUtil.applyString("Limit from: " + offset2 + " to: " + vec4.lengthVector());
         }
      }

      return vec.getVec311(vec4);
   }

   public Rotation getRotation3(double posX, double posX2, double posX3, double posX4, double posX5, double posX6, double posY, double posZ) {
      double offset = posX;
      double offset2 = posX2;

      for (int index = 0; index < 3; index++) {
         double doubleItem = HitBoxUtils.getDouble(offset, posX3);
         double offset3 = posX4 - offset2;
         SimulationSnapshot simulationSnapshot = new SimulationSnapshot(doubleItem / 0.15, offset3 / 0.15);
         double offset4 = Math.signum(simulationSnapshot.value) != Math.signum(this.supplementalItemEntry.value) ? posX5 * posY : posX5;
         double offset5 = Math.signum(simulationSnapshot.value2) != Math.signum(this.supplementalItemEntry.value2) ? posX5 * posY : posX5;
         double offset6 = offset4 * ThreadLocalRandom.current().nextDouble(0.8, 1.0);
         double offset7 = offset5 * ThreadLocalRandom.current().nextDouble(0.8, 1.0);
         double offset8 = posX5 * posX6;
         double offset9 = Math.abs(posZ / 0.15 / 3.0);
         if (Math.signum(posZ) != Math.signum(simulationSnapshot.value)) {
            offset9 = 0.0;
         }

         double offset10 = Math.min(offset9 + 1.4142135623730951 * Math.sqrt(offset8 * Math.abs(simulationSnapshot.value)), Math.abs(simulationSnapshot.value));
         double offset11 = Math.min(1.4142135623730951 * offset8 * Math.sqrt(Math.abs(simulationSnapshot.value2) / (2.0 * offset8 - 1.0)), Math.abs(simulationSnapshot.value2));
         double offset12 = offset10 * Math.signum(simulationSnapshot.value) - this.supplementalItemEntry.value;
         SimulationSnapshot simulationSnapshot2 = new SimulationSnapshot(
            MathHelper.getDouble7(
                  Math.abs(offset12),
                  0.0,
                  (Math.signum(offset12) != Math.signum(simulationSnapshot.value) || simulationSnapshot.value == 0.0)
                        && Math.signum(this.supplementalItemEntry.value) == Math.signum(simulationSnapshot.value)
                     ? Double.MAX_VALUE
                     : offset6
               )
               * Math.signum(offset12),
            MathHelper.getDouble7(offset11 * Math.signum(simulationSnapshot.value2) - this.supplementalItemEntry.value2, -offset7, offset7)
         );
         if (Double.isNaN(simulationSnapshot2.value)) {
            simulationSnapshot2.value = 0.0;
         }

         if (Double.isNaN(simulationSnapshot2.value2)) {
            simulationSnapshot2.value2 = 0.0;
         }

         if (Math.signum(this.supplementalItemEntry.value2) == Math.signum(simulationSnapshot.value2)) {
            double offset13 = Math.abs(this.supplementalItemEntry.value2);
            double offset14 = posX5 * posX6;
            double offset15 = -0.5 * (offset13 * offset13 / (offset14 * offset14)) + offset13 * offset13 / offset14;
            double offset16 = Math.abs(simulationSnapshot.value2);
            if (offset15 > offset16) {
               simulationSnapshot2.value2 = -Math.signum(simulationSnapshot.value2) * Math.min(offset14, (offset13 * Math.sqrt(offset13 * offset13 - 2.0 * offset16) + offset13 * offset13) / (2.0 * offset16));
            }
         }

         this.supplementalItemEntry = this.supplementalItemEntry.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot2);
         offset = MathHelper.getDouble4(offset + this.supplementalItemEntry.value * 0.15);
         offset2 = MathHelper.getDouble7(offset2 + this.supplementalItemEntry.value2 * 0.15, -90.0, 90.0);
      }

      return new Rotation((float)offset, (float)offset2);
   }

   public static Vec3 processAction(Entity entity2, Entity entity3) {
      Vec3 vec3 = entity2.getVec35(1.0F);
      float height = entity3.getHeight();
      AxisAlignedBB boundingBox = entity3.getBoundingBox2().offset((double)height, (double)height, (double)height);
      double doubleItem = MathHelper.getDouble7(vec3.xCoord, boundingBox.minX, boundingBox.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec3.yCoord, boundingBox.minY, boundingBox.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec3.zCoord, boundingBox.minZ, boundingBox.maxZ);
      return new Vec3(doubleItem, doubleItem2, doubleItem3);
   }

   private double getCoordinate3(float factor, float factor2, RotationAngleData rotationAngleData) {
      Rotation rotation5 = this.rotation3;
      Rotation rotation6 = new Rotation(factor + rotationAngleData.getFactor(), factor2 + rotationAngleData.getFactor2());
      Rotation rotation7 = rotation5;
      return RotationAngleData.computeValue(rotationAngleData, rotation7, rotation6);
   }

   public static double getCoordinate(Rotation rotation5, Rotation rotation6, Rotation rotation7) {
      short shortValue = 31539;
      short shortValue2 = 4469;
      short shortValue3 = 4573;
      if (rotation7 != null && rotation6 != null && rotation5 != null && !rotation6.checkRotation(rotation7) && !rotation7.checkRotation(rotation5) && !rotation6.checkRotation(rotation5)) {
         double offset = processAction2(rotation6, rotation7);
         double offset2 = processAction2(rotation5, rotation6);
         double offset3 = processAction2(rotation5, rotation7);
         if (!(offset < 0.25) && !(offset2 < 0.25) && !(offset3 < 0.25)) {
            double offset4 = Math.acos((offset * offset + offset2 * offset2 - offset3 * offset3) / (2.0 * offset * offset2)) * (180.0 / Math.PI);
            return !Double.isFinite(offset4) ? 0.0 : (double)((float)offset4);
         } else {
            return 0.0;
         }
      } else {
         return 0.0;
      }
   }

   public Rotation getRotation(
      boolean flag,
      EntityLivingBase livingEntity,
      float posX,
      float posX2,
      float posY,
      float posZ,
      String text,
      float factor,
      float factor2,
      double offset,
      boolean flag2,
      boolean flag3,
      RangeSetting setting,
      RangeSetting setting2,
      double offset2,
      boolean flag4,
      float posX3,
      float posX4,
      float posX5,
      float posX6,
      float posX7,
      float posX8,
      float posX9,
      float posX10,
      float posX11,
      float posY2,
      float posZ2
   ) {
      float factor3 = posX;
      float factor4 = posX2;
      if (this.rotation3 == null) {
         double offset3 = 2.0;
         factor3 = posX + this.getScaleFactor(text, offset3, posX2);
         double offset4 = 2.0;
         factor4 = posX2 + this.getScaleFactor(text, offset4, factor3);
         this.rotation3 = new Rotation(factor3, factor4);
         return new Rotation(factor3, factor4);
      } else if (!flag && this.auxiliaryFloatEntry != null) {
         factor3 = posX + this.auxiliaryFloatEntry.getFactor();
         factor4 = posX2 + this.auxiliaryFloatEntry.getFactor2();
         return new Rotation(factor3, factor4);
      } else {
         Rotation rotation5 = getRotation8(getFloat9(posY, factor, 180.0F), getFloat9(posZ, factor2, 180.0F), posY, posZ);
         double offset5 = 0.0;
         MovingObjectPosition movingObjectPosition = RayTraceUtils.getMovingObjectPosition(1.0F, rotation5, offset2, offset5);
         boolean flag5 = movingObjectPosition != null && movingObjectPosition.typeOfHit == MovingObjectType.ENTITY && movingObjectPosition.entityHit instanceof EntityLivingBase;
         ArrayList list = new ArrayList();
         if (flag2) {
            int count = (int)offset;
            if (this.checkCondition(count)) {
               if (flag5) {
                  MovingObjectPosition hitResult = RayTraceUtils.resolveMovingObjectPosition(1.0F, posY, posZ, offset2, 0.0);
                  if (hitResult != null
                     && hitResult.typeOfHit == MovingObjectType.ENTITY
                     && hitResult.entityHit instanceof EntityLivingBase
                     && hitResult.entityHit.equals(livingEntity)) {
                     this.rotation4 = this.rotation3;
                     this.rotation3 = new Rotation(posY, posZ);
                     return new Rotation(posY, posZ);
                  }
               } else {
                  if (Math.abs(posX - posY) < 5.0F && Math.abs(posX2 - posZ) < 5.0F) {
                     this.rotation4 = this.rotation3;
                     this.rotation3 = new Rotation(posY, posZ);
                     return new Rotation(posY, posZ);
                  }

                  MovingObjectPosition hitResult2 = RayTraceUtils.resolveMovingObjectPosition(1.0F, posY, posZ, 200.0, 0.0);
                  if (hitResult2 != null
                     && hitResult2.typeOfHit == MovingObjectType.ENTITY
                     && hitResult2.entityHit instanceof EntityLivingBase
                     && hitResult2.entityHit.equals(livingEntity)) {
                     this.rotation4 = this.rotation3;
                     this.rotation3 = new Rotation(posY, posZ);
                     return new Rotation(posY, posZ);
                  }
               }
            }
         }

         if (flag5 && flag4 && livingEntity.hurtTime > 3) {
            float floatItem = getFloat2(getFloat9(posY, factor, 180.0F), posY);
            if (CombatRandomizer.matchesEntity(livingEntity, floatItem)) {
               flag5 = false;
            }
         }

         if (flag5) {
            for (RotationAngleData rotationAngleData : (Iterable<RotationAngleData>)(Iterable)(this.itemList)) {
               float factor5 = factor3 + rotationAngleData.getFactor();
               float factor6 = factor4 + rotationAngleData.getFactor2();
               rotation5 = getRotation8(factor5, factor6, posY, posZ);
               double offset6 = 0.0;
               MovingObjectPosition movingObjectPosition2 = RayTraceUtils.getMovingObjectPosition(1.0F, rotation5, offset2, offset6);
               if (movingObjectPosition2 != null && movingObjectPosition2.typeOfHit == MovingObjectType.ENTITY && movingObjectPosition2.entityHit instanceof EntityLivingBase) {
                  list.add(rotationAngleData);
               }
            }
         } else {
            list.addAll(this.itemList);
         }

         this.indexOffset19++;
         ArrayList list2 = list.isEmpty() ? this.itemList : list;
         RotationAngleData auxiliaryFloatEntry2 = this.getAuxiliaryFloatEntry(list2, factor3, factor4, posX3, posX4, posX5, posX6, posX7, posX8, posX9, posX10, posX11, posY2, posZ2);
         RotationAngleData.computeCount(auxiliaryFloatEntry2);
         factor3 += auxiliaryFloatEntry2.getFactor();
         factor4 += auxiliaryFloatEntry2.getFactor2();
         this.auxiliaryFloatEntry = auxiliaryFloatEntry2;
         this.rotation4 = this.rotation3;
         this.rotation3 = new Rotation(factor3, factor4);
         this.coordOffsetArray67 = new double[]{(double)auxiliaryFloatEntry2.getFactor(), (double)auxiliaryFloatEntry2.getFactor2()};
         if (setting2 != null && !flag5 && flag3 && this.indexOffset19 > setting2.getIndexOffset2()) {
            setting2.recalculate();
            this.indexOffset19 = 0;
            float height = livingEntity.getHeight();
            AxisAlignedBB boundingBox = livingEntity.getBoundingBox2().offset((double)height, (double)height, (double)height);
            double[] coordOffsetArrayByVec = TrajectoryPredictor.getCoordOffsetArrayByVec3(MinecraftAccessor.minecraft.thePlayer.getVec35(1.0F), boundingBox);
            coordOffsetArrayByVec[0] = MathHelper.getDouble7(coordOffsetArrayByVec[0] - setting.recalculate(), -90.0, 90.0);
            coordOffsetArrayByVec[1] = MathHelper.getDouble7(coordOffsetArrayByVec[1] + setting.recalculate(), -90.0, 90.0);
            double offset7 = Math.abs((double)factor4 - coordOffsetArrayByVec[0]);
            double offset8 = Math.abs((double)factor4 - coordOffsetArrayByVec[1]);
            factor4 = (float)(offset7 < offset8 ? coordOffsetArrayByVec[0] : coordOffsetArrayByVec[1]);
         }

         return new Rotation(factor3, factor4);
      }
   }

   public Rotation getRotation4(double posX, double posY, double posZ, float posX2, float posX3, float posY2, float posZ2, boolean flag) {
      double offset = posX - MinecraftAccessor.minecraft.thePlayer.posX;
      double offset2 = posY + 1.53 - (MinecraftAccessor.minecraft.thePlayer.posY + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2());
      double offset3 = posZ - MinecraftAccessor.minecraft.thePlayer.posZ;
      float factor = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor2 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      float floatItem = getFloat9(posX2, factor, posY2);
      float floatItem2 = getFloat9(posX3, factor2, posZ2);
      if (flag) {
         floatItem += (float)ThreadLocalRandom.current().nextGaussian();
         floatItem2 += (float)ThreadLocalRandom.current().nextGaussian();
      }

      return getRotation8(floatItem, floatItem2, posX2, posX3);
   }

   private RotationAngleData getAuxiliaryFloatEntry(
      ArrayList list,
      float posX,
      float posX2,
      float posX3,
      float posX4,
      float posX5,
      float posX6,
      float posX7,
      float posX8,
      float posX9,
      float posX10,
      float posX11,
      float posY,
      float posZ
   ) {
      long value = 0x3d9e80b1a22dL;
      long keySalt = 0x77a2ed254aafL ^ value;
      ArrayList list2 = new ArrayList();

      for (RotationAngleData rotationAngleData : (Iterable<RotationAngleData>)(Iterable)(list)) {
         double offset = Math.abs((double)rotationAngleData.getFactor() - this.coordOffsetArray67[0]);
         double offset2 = Math.abs((double)rotationAngleData.getFactor2() - this.coordOffsetArray67[1]);
         if (!rotationAngleData.checkFloat(posX11)) {
            double offset3 = (double)posY;
            double offset4 = (double)posZ;
            double offset5 = offset3;
            if (rotationAngleData.checkDouble(offset5, offset4)
               && Math.abs(this.rotation3.yaw - posX + rotationAngleData.getFactor()) < posX3
               && Math.abs(this.rotation3.pitch - posX2 + rotationAngleData.getFactor2()) < posX4) {
               Rotation rotation5 = this.rotation3;
               Rotation rotation6 = this.rotation4;
               Rotation rotation7 = new Rotation(posX, posX2);
               Rotation rotation8 = rotation6;
               Rotation rotation9 = rotation5;
               if (rotationAngleData.computeFactor(rotation9, rotation8, rotation7) > 180.0F - posX10) {
                  rotation5 = this.rotation3;
                  Rotation rotation10 = new Rotation(posX + rotationAngleData.getFactor(), posX2 + rotationAngleData.getFactor2());
                  Rotation rotation11 = rotation5;
                  if (RotationAngleData.computeValue(rotationAngleData, rotation11, rotation10) > (double)posX5) {
                     offset3 = (double)posX6;
                     double offset6 = (double)posX8;
                     double offset7 = offset3;
                     if (this.checkCondition(offset, offset7, offset6)) {
                        offset3 = (double)posX7;
                        double offset8 = (double)posX9;
                        double offset9 = offset3;
                        if (this.checkCondition(offset2, offset9, offset8)) {
                           list2.add(rotationAngleData);
                        }
                     }
                  }
               }
            }
         }
      }

      if (list2.isEmpty()) {
         for (RotationAngleData rotationAngleData2 : (Iterable<RotationAngleData>)(Iterable)(list)) {
            double offset10 = Math.abs((double)rotationAngleData2.getFactor() - this.coordOffsetArray67[0]);
            double offset11 = Math.abs((double)rotationAngleData2.getFactor2() - this.coordOffsetArray67[1]);
            if (!rotationAngleData2.checkFloat(posX11)) {
               double offset12 = (double)posY;
               double offset13 = (double)posZ;
               double offset14 = offset12;
               if (rotationAngleData2.checkDouble(offset14, offset13)) {
                  Rotation rotation12 = this.rotation3;
                  Rotation rotation13 = this.rotation4;
                  Rotation rotation14 = new Rotation(posX, posX2);
                  Rotation rotation15 = rotation13;
                  Rotation rotation16 = rotation12;
                  if (rotationAngleData2.computeFactor(rotation16, rotation15, rotation14) > 180.0F - posX10) {
                     rotation12 = this.rotation3;
                     Rotation rotation17 = new Rotation(posX + rotationAngleData2.getFactor(), posX2 + rotationAngleData2.getFactor2());
                     Rotation rotation18 = rotation12;
                     if (RotationAngleData.computeValue(rotationAngleData2, rotation18, rotation17) > (double)posX5) {
                        offset12 = (double)posX6;
                        double offset15 = (double)posX8;
                        double offset16 = offset12;
                        if (this.checkCondition(offset10, offset16, offset15)) {
                           offset12 = (double)posX7;
                           double offset17 = (double)posX9;
                           double offset18 = offset12;
                           if (this.checkCondition(offset11, offset18, offset17)) {
                              list2.add(rotationAngleData2);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (list2.isEmpty()) {
         for (RotationAngleData rotationAngleData3 : (Iterable<RotationAngleData>)(Iterable)(list)) {
            double offset19 = Math.abs((double)rotationAngleData3.getFactor() - this.coordOffsetArray67[0]);
            double offset20 = Math.abs((double)rotationAngleData3.getFactor2() - this.coordOffsetArray67[1]);
            if (!rotationAngleData3.checkFloat(posX11)) {
               double offset21 = (double)posY;
               double offset22 = (double)posZ;
               double offset23 = offset21;
               if (rotationAngleData3.checkDouble(offset23, offset22)) {
                  Rotation rotation19 = this.rotation3;
                  Rotation rotation20 = new Rotation(posX + rotationAngleData3.getFactor(), posX2 + rotationAngleData3.getFactor2());
                  Rotation rotation21 = rotation19;
                  if (RotationAngleData.computeValue(rotationAngleData3, rotation21, rotation20) > (double)posX5) {
                     offset21 = (double)posX6;
                     double offset24 = (double)posX8 * 1.4;
                     double offset25 = offset21;
                     if (this.checkCondition(offset19, offset25, offset24)) {
                        offset21 = (double)posX7;
                        double offset26 = (double)posX9 * 1.4;
                        double offset27 = offset21;
                        if (this.checkCondition(offset20, offset27, offset26)) {
                           list2.add(rotationAngleData3);
                        }
                     }
                  }
               }
            }
         }

         if (!list2.isEmpty()) {
            list2.sort(Comparator.comparingDouble(entry -> this.getCoordinate(posX, posX2, (RotationAngleData)entry)));
            return (RotationAngleData)list2.get(0);
         }
      }

      if (list2.isEmpty()) {
         for (RotationAngleData rotationAngleData4 : (Iterable<RotationAngleData>)(Iterable)(list)) {
            double offset28 = Math.abs((double)rotationAngleData4.getFactor() - this.coordOffsetArray67[0]);
            double offset29 = Math.abs((double)rotationAngleData4.getFactor2() - this.coordOffsetArray67[1]);
            if (!rotationAngleData4.checkFloat(posX11)) {
               Rotation rotation22 = this.rotation3;
               Rotation rotation23 = new Rotation(posX + rotationAngleData4.getFactor(), posX2 + rotationAngleData4.getFactor2());
               Rotation rotation24 = rotation22;
               if (RotationAngleData.computeValue(rotationAngleData4, rotation24, rotation23) > (double)posX5) {
                  double offset30 = (double)posX6;
                  double offset31 = (double)posX8 * 1.4;
                  double offset32 = offset30;
                  if (this.checkCondition(offset28, offset32, offset31)) {
                     offset30 = (double)posX7;
                     double offset33 = (double)posX9 * 1.4;
                     double offset34 = offset30;
                     if (this.checkCondition(offset29, offset34, offset33)) {
                        list2.add(rotationAngleData4);
                     }
                  }
               }
            }
         }

         if (!list2.isEmpty()) {
            list2.sort(Comparator.comparingDouble(entry -> this.getCoordinate2(posX, posX2, (RotationAngleData)entry)));
            return (RotationAngleData)list2.get(0);
         }
      }

      if (list2.isEmpty()) {
         for (RotationAngleData rotationAngleData5 : (Iterable<RotationAngleData>)(Iterable)(list)) {
            if (!rotationAngleData5.checkFloat(posX11)) {
               double offset35 = (double)posY;
               double offset36 = (double)posZ;
               double offset37 = offset35;
               if (rotationAngleData5.checkDouble(offset37, offset36)) {
                  list2.add(rotationAngleData5);
               }
            }
         }
      }

      if (!list2.isEmpty()) {
         list2.sort(Comparator.comparingDouble(entry -> this.getCoordinate3(posX, posX2, (RotationAngleData)entry)));
         return (RotationAngleData)list2.get(0);
      } else {
         return this.getAuxiliaryFloatEntry(list, posX, posX2);
      }
   }

   public static Rotation getRotation5(Rotation rotation5, Rotation rotation6) {
      return getRotation8(rotation5.yaw, rotation5.pitch, rotation6.yaw, rotation6.pitch);
   }

   public static double[] getCoordOffsetArray(Vec3 vec, AxisAlignedBB boundingBox, AxisAlignedBB boundingBox2, double offset, double offset2) {
      double doubleItem = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(TrajectoryPredictor.getCoordOffsetArray2(vec, boundingBox), offset));
      double coordinate = TrajectoryPredictor.getCoordinate(TrajectoryPredictor.getCoordOffsetArrayByVec3(vec, boundingBox), offset2);
      double doubleItem2 = MathHelper.getDouble7(TrajectoryPredictor.getCoordinate(boundingBox2, vec, doubleItem), 0.0, 1.0);
      double doubleItem3 = MathHelper.getDouble7(TrajectoryPredictor.getCoordinate(boundingBox2, vec, 0L, coordinate), 0.0, 1.0);
      return new double[]{doubleItem2, doubleItem3};
   }

   public static Rotation getRotation6(float factor, float factor2, Rotation rotation5) {
      return getRotation8(factor, factor2, rotation5.yaw, rotation5.pitch);
   }

   public Rotation getRotation7(double posX, double posX2, double posX3, double posY, double posZ) {
      double offset = posX;
      double offset2 = posX2;
      this.coordOffsetArray = new double[3][];
      double offset3 = MathHelper.getDouble7(posZ, 0.0, 1.0) * 8.0;
      float factor = MinecraftAccessor.minecraft.gameSettings.scaleFactor * 0.6F + 0.2F;
      float factor2 = factor * factor * factor * 8.0F;

      for (int index = 0; index < 3; index++) {
         double doubleItem = HitBoxUtils.getDouble(offset, posX3);
         double offset4 = posY - offset2;
         double offset5 = doubleItem / 0.15;
         double offset6 = offset4 / 0.15;
         SimulationSnapshot simulationSnapshot = new SimulationSnapshot(offset5, offset6);
         double offset7 = simulationSnapshot.sqrt();
         SimulationSnapshot simulationSnapshot2 = new SimulationSnapshot(
            (simulationSnapshot.value - this.supplementalItemEntry.value) * Math.min(offset7, offset3) / offset7,
            (simulationSnapshot.value2 - this.supplementalItemEntry.value2) * Math.min(offset7, offset3) / offset7
         );
         if (Double.isNaN(simulationSnapshot2.value) || Double.isNaN(simulationSnapshot2.value2)) {
            SimulationSnapshot simulationSnapshot3 = new SimulationSnapshot(Math.random(), Math.random());
            double offset8 = 0.01;
            simulationSnapshot2 = simulationSnapshot3.resolveSimulationSnapshotByDouble(offset8);
         }

         SimulationSnapshot simulationSnapshot4 = new SimulationSnapshot(this.supplementalItemEntry.value, this.supplementalItemEntry.value2);
         if (Math.signum(this.supplementalItemEntry.value) == Math.signum(simulationSnapshot2.value)) {
            double offset9 = Math.abs(this.supplementalItemEntry.value);
            double offset10 = Math.abs(simulationSnapshot2.value);
            if (-0.5 * (offset9 * offset9 / (offset10 * offset10)) + offset9 * offset9 / offset10 > Math.abs(simulationSnapshot.value)) {
               simulationSnapshot2.value *= -1.0;
            }
         }

         if (Math.signum(this.supplementalItemEntry.value2) == Math.signum(simulationSnapshot2.value2)) {
            double offset11 = Math.abs(this.supplementalItemEntry.value2);
            double offset12 = Math.abs(simulationSnapshot2.value2);
            if (-0.5 * (offset11 * offset11 / (offset12 * offset12)) + offset11 * offset11 / offset12 > Math.abs(simulationSnapshot.value2)) {
               simulationSnapshot2.value2 *= -1.0;
            }
         }

         SimulationSnapshot simulationSnapshot5 = simulationSnapshot4.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot2);
         double offset13 = 0.0;
         SimulationSnapshot simulationSnapshot6 = this.supplementalItemEntry.resolveSimulationSnapshotByDouble(offset13);
         double offset14 = 1.0;
         SimulationSnapshot simulationSnapshot7 = simulationSnapshot5.resolveSimulationSnapshotByDouble(offset14);
         this.supplementalItemEntry = simulationSnapshot6.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot7);
         double offset15 = this.supplementalItemEntry.value * 0.15 * 1.0;
         double offset16 = this.supplementalItemEntry.value2 * 0.15 * 1.0;
         this.coordOffsetArray[index] = new double[]{
            this.supplementalItemEntry.value * 1.0 / (double)factor2, this.supplementalItemEntry.value2 * 1.0 / (double)factor2
         };
         offset = MathHelper.getDouble4(offset + offset15);
         offset2 = MathHelper.getDouble7(offset2 + offset16, -90.0, 90.0);
      }

      return new Rotation((float)offset, (float)offset2);
   }

   public static Vec3 getVec3(Entity entity2, float factor) {
      Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec35(factor);
      double offset = (double)factor;
      AxisAlignedBB boundingBox = processAction(entity2, offset);
      double doubleItem = MathHelper.getDouble7(vec3.xCoord, boundingBox.minX, boundingBox.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec3.yCoord, boundingBox.minY, boundingBox.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec3.zCoord, boundingBox.minZ, boundingBox.maxZ);
      return new Vec3(doubleItem, doubleItem2, doubleItem3);
   }

   public static float getFloat8(float posX, float posY, float posZ) {
      float floatItem = getFloat9(posX, posY, posZ);
      return floatItem == posX ? posX : getFloat2(floatItem, posX);
   }

   public static int[] getIntArray(float posX, float posX2, float posY, float posZ) {
      float factor = MinecraftAccessor.minecraft.gameSettings.scaleFactor * 0.6F + 0.2F;
      float factor2 = factor * factor * factor * 8.0F;
      int count = (int)Math.round(6.6666665F * HitBoxUtils.getDouble(MathHelper.getDouble4((double)posY), MathHelper.getDouble4((double)posX)) / (double)factor2);
      int count2 = (int)Math.round((6.667 * (double)posX2 - 6.667 * (double)posZ) / (double)factor2) * -1;
      return new int[]{count, count2};
   }

   public static Vec3 getVec3(Entity entity2, int count, int count2) {
      Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec35(1.0F);
      float height = entity2.getHeight();
      AxisAlignedBB boundingBox = entity2.getBoundingBox2().offset((double)height, (double)height, (double)height);
      double offset = boundingBox.maxX - boundingBox.minX;
      double offset2 = boundingBox.maxY - boundingBox.minY;
      double offset3 = offset * ((double)count2 / 100.0);
      double offset4 = offset2 * ((double)count / 100.0);
      AxisAlignedBB boundingBox2 = boundingBox.offset((offset3 - offset) / 2.0, (offset4 - offset2) / 2.0, (offset3 - offset) / 2.0);
      double doubleItem = MathHelper.getDouble7(vec3.xCoord, boundingBox2.minX, boundingBox2.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec3.yCoord, boundingBox2.minY, boundingBox2.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec3.zCoord, boundingBox2.minZ, boundingBox2.maxZ);
      return new Vec3(doubleItem, doubleItem2, doubleItem3);
   }

   public static Rotation getRotation8(float posX, float posX2, float posY, float posZ) {
      if (posX == posY && posX2 == posZ) {
         return new Rotation(posX, posX2);
      } else if (CosmeticsPayload.checkCondition(1)) {
         float factor = MinecraftAccessor.minecraft.gameSettings.scaleFactor * 0.6F + 0.2F;
         float factor2 = factor * factor * factor * 1.2F;
         float factor3 = posX - posX % factor2;
         float factor4 = posX2 - posX2 % factor2;
         return new Rotation(factor3, factor4);
      } else if (ClientGlobals.moduleManager.rotationHandler.settingMouseSensMode.getSelected().equals("Default")) {
         float factor5 = MinecraftAccessor.minecraft.gameSettings.scaleFactor * 0.6F + 0.2F;
         float factor6 = factor5 * factor5 * factor5 * 8.0F;
         int count = (int)((6.667 * (double)posX - 6.667 * (double)posY) / (double)factor6);
         int count2 = (int)((6.667 * (double)posX2 - 6.667 * (double)posZ) / (double)factor6) * -1;
         float factor7 = (float)count * factor6;
         float factor8 = (float)count2 * factor6;
         float factor9 = (float)((double)posY + (double)factor7 * 0.15);
         float factor10 = MathHelper.clamp_float((float)((double)posZ - (double)factor8 * 0.15), -90.0F, 90.0F);
         return new Rotation(factor9, factor10);
      } else {
         int tickDelay = ClientGlobals.moduleManager.rotationHandler.tickDelay;
         float factor11 = MinecraftAccessor.minecraft.gameSettings.scaleFactor * 0.6F + 0.2F;
         float factor12 = factor11 * factor11 * factor11 * 8.0F;
         float factor13 = posY;
         float factor14 = posZ;
         int count3 = (int)Math.round(
            6.6666665F * HitBoxUtils.getDouble(MathHelper.getDouble4((double)posY), MathHelper.getDouble4((double)posX)) / (double)factor12
         );
         int count4 = Math.round((6.6666665F * posX2 - 6.6666665F * posZ) / factor12 * -1.0F);
         int count5 = 0;
         int count6 = 0;
         double offset = 0.0;
         double offset2 = 0.0;
         double offset3 = (double)count3 / (double)tickDelay;
         double offset4 = (double)count4 / (double)tickDelay;
         ArrayList list = new ArrayList();
         ArrayList list2 = new ArrayList();

         for (int index = 0; index < tickDelay - 1; index++) {
            offset += offset3;
            offset2 += offset4;
            int count7 = (int)Math.round(offset - (double)count5);
            int count8 = (int)Math.round(offset2 - (double)count6);
            list.add(count7);
            list2.add(count8);
            count5 += count7;
            count6 += count8;
         }

         Collections.shuffle(list);
         Collections.shuffle(list2);

         for (int index2 = 0; index2 < list.size(); index2++) {
            float factor15 = (float)((Integer)list.get(index2)).intValue() * factor12;
            float factor16 = (float)((Integer)list2.get(index2)).intValue() * factor12;
            float factor17 = (float)((double)factor13 + (double)factor15 * 0.15);
            factor14 = MathHelper.clamp_float((float)((double)factor14 - (double)factor16 * 0.15), -90.0F, 90.0F);
            factor13 = factor17;
         }

         int count9 = (int)(6.6666665F * HitBoxUtils.getDouble(MathHelper.getDouble4((double)factor13), MathHelper.getDouble4((double)posX)) / (double)factor12);
         int count10 = (int)((6.6666665F * posX2 - 6.6666665F * factor14) / factor12) * -1;
         float factor18 = (float)count9 * factor12;
         float factor19 = (float)count10 * factor12;
         float factor20 = (float)((double)factor13 + (double)factor18 * 0.15);
         float factor21 = MathHelper.clamp_float((float)((double)factor14 - (double)factor19 * 0.15), -90.0F, 90.0F);
         return new Rotation(factor20, factor21);
      }
   }

   private static double processAction2(Rotation rotation5, Rotation rotation6) {
      float factor = rotation5.yaw - rotation6.yaw;
      float factor2 = rotation5.pitch - rotation6.pitch;
      return Math.sqrt((double)(factor2 * factor2 + factor * factor));
   }

   public static AxisAlignedBB getAxisAlignedBB(EntityLivingBase livingEntity, Vec3 vec) {
      double posZ = vec.zCoord;
      double posY = vec.yCoord;
      double posX = vec.xCoord;
      return processAction(livingEntity, posX, posY, posZ);
   }

   public static double[] getCoordOffsetArray(Vec3 vec, AxisAlignedBB boundingBox, double offset, double offset2) {
      double coordinate = TrajectoryPredictor.getCoordinate(boundingBox, vec, offset);
      double coordinate2 = TrajectoryPredictor.getCoordinate(boundingBox, vec, 0x611b6684d3d5L, offset2);
      return new double[]{coordinate, coordinate2};
   }

   public static Vec3 processAction(Vec3 vec, AxisAlignedBB boundingBox) {
      double doubleItem = MathHelper.getDouble7(vec.xCoord, boundingBox.minX, boundingBox.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec.yCoord, boundingBox.minY, boundingBox.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec.zCoord, boundingBox.minZ, boundingBox.maxZ);
      return new Vec3(doubleItem, doubleItem2, doubleItem3);
   }

   public static Rotation getRotation(Vec3 vec, Vec3 vec2, Rotation rotation5, float factor, float factor2) {
      double offset = vec.xCoord - vec2.xCoord;
      double offset2 = vec.yCoord - vec2.yCoord;
      double offset3 = vec.zCoord - vec2.zCoord;
      float factor3 = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor4 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      float floatItem = getFloat9(rotation5.yaw, factor3, factor);
      float floatItem2 = getFloat9(rotation5.pitch, factor4, factor2);
      return new Rotation(floatItem, floatItem2);
   }

   private static double getCoordinate4(float factor, float factor2, RotationAngleData rotationAngleData) {
      return rotationAngleData.computeValue(factor, factor2);
   }

   public static float[] getFloatArray2(double posX, double posY, double posZ, float factor, float factor2) {
      float factor3 = (float)(
         MinecraftAccessor.minecraft.thePlayer.lastTickPosX
            + (MinecraftAccessor.minecraft.thePlayer.posX - MinecraftAccessor.minecraft.thePlayer.lastTickPosX)
               * (double)MinecraftAccessor.minecraft.getTimer().scaleFactor6
      );
      float factor4 = (float)(
         MinecraftAccessor.minecraft.thePlayer.lastTickPosY
            + (MinecraftAccessor.minecraft.thePlayer.posY - MinecraftAccessor.minecraft.thePlayer.lastTickPosY)
               * (double)MinecraftAccessor.minecraft.getTimer().scaleFactor6
      );
      float factor5 = (float)(
         MinecraftAccessor.minecraft.thePlayer.lastTickPosZ
            + (MinecraftAccessor.minecraft.thePlayer.posZ - MinecraftAccessor.minecraft.thePlayer.lastTickPosZ)
               * (double)MinecraftAccessor.minecraft.getTimer().scaleFactor6
      );
      double offset = posX - (double)factor3;
      double offset2 = posY - ((double)factor4 + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2());
      double offset3 = posZ - (double)factor5;
      float factor6 = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor7 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      float factor8 = factor6 - factor;
      float factor9 = factor7 - factor2;
      return new float[]{factor8, factor9};
   }

   public static Vec3 processAction(Vec3 vec, AxisAlignedBB boundingBox, int count, int count2) {
      double offset = boundingBox.maxX - boundingBox.minX;
      double offset2 = boundingBox.maxY - boundingBox.minY;
      double offset3 = offset * ((double)count2 / 100.0);
      double offset4 = offset2 * ((double)count / 100.0);
      AxisAlignedBB boundingBox2 = boundingBox.offset((offset3 - offset) / 2.0, (offset4 - offset2) / 2.0, (offset3 - offset) / 2.0);
      double doubleItem = MathHelper.getDouble7(vec.xCoord, boundingBox2.minX, boundingBox2.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec.yCoord, boundingBox2.minY, boundingBox2.maxY);
      double doubleItem3 = MathHelper.getDouble7(vec.zCoord, boundingBox2.minZ, boundingBox2.maxZ);
      return new Vec3(doubleItem, doubleItem2, doubleItem3);
   }

   public static float getFloat9(float posX, float posY, float posZ) {
      float floatItem = getFloat6(posY - posX);
      if (floatItem >= posZ) {
         floatItem = posZ;
      }

      if (floatItem <= -posZ) {
         floatItem = -posZ;
      }

      return posX + floatItem;
   }

   public static Rotation getRotation9(double posX, double posY, double posZ, Rotation rotation5, float factor, float factor2, boolean flag, float factor3) {
      double offset = MinecraftAccessor.minecraft.thePlayer.prevPosX
         + (MinecraftAccessor.minecraft.thePlayer.posX - MinecraftAccessor.minecraft.thePlayer.prevPosX) * (double)factor3;
      double offset2 = MinecraftAccessor.minecraft.thePlayer.prevPosY
         + (MinecraftAccessor.minecraft.thePlayer.posY - MinecraftAccessor.minecraft.thePlayer.prevPosY) * (double)factor3;
      double offset3 = MinecraftAccessor.minecraft.thePlayer.prevPosZ
         + (MinecraftAccessor.minecraft.thePlayer.posZ - MinecraftAccessor.minecraft.thePlayer.prevPosZ) * (double)factor3;
      double offset4 = posX - offset;
      double offset5 = posY - (offset2 + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2());
      double offset6 = posZ - offset3;
      float factor4 = (float)(MathHelper.getDouble6(offset6, offset4) * 180.0 / Math.PI - 90.0);
      float factor5 = (float)(-(MathHelper.getDouble6(offset5, (double)MathHelper.sqrt_double(offset4 * offset4 + offset6 * offset6)) * 180.0 / Math.PI));
      float floatItem = getFloat9(rotation5.yaw, factor4, factor);
      float floatItem2 = getFloat9(rotation5.pitch, factor5, factor2);
      if (flag) {
         floatItem += (float)ThreadLocalRandom.current().nextGaussian();
         floatItem2 += (float)ThreadLocalRandom.current().nextGaussian();
      }

      return new Rotation(floatItem, floatItem2);
   }

   private ArrayList getArrayList(String text, String text2) {
      ConfigManager.applyString2(text);
      File file = new File(text, text2);
      if (!file.exists()) {
         return null;
      } else {
         JsonArray jsonArray = ConfigManager.getJsonArray(text, text2, (Runnable)null);
         ArrayList list = new ArrayList();
         Iterator iterator = jsonArray.iterator();

         while (iterator.hasNext()) {
            JsonObject asJsonObject = ((JsonElement)iterator.next()).getAsJsonObject();
            list.add(
               new SimulationEntityHitbox(
                  asJsonObject.get("LBHDY").getAsFloat(),
                  asJsonObject.get("LBHDP").getAsFloat(),
                  asJsonObject.get("BHDY").getAsFloat(),
                  asJsonObject.get("BHDP").getAsFloat(),
                  asJsonObject.get("LYS").getAsFloat(),
                  asJsonObject.get("LPS").getAsFloat(),
                  asJsonObject.get("LY").getAsFloat(),
                  asJsonObject.get("LP").getAsFloat(),
                  asJsonObject.get("Y").getAsFloat(),
                  asJsonObject.get("P").getAsFloat(),
                  asJsonObject.get("TX").getAsDouble(),
                  asJsonObject.get("TY").getAsDouble(),
                  asJsonObject.get("TZ").getAsDouble(),
                  asJsonObject.get("LTX").getAsDouble(),
                  asJsonObject.get("LTY").getAsDouble(),
                  asJsonObject.get("LTZ").getAsDouble(),
                  asJsonObject.get("PMX").getAsDouble(),
                  asJsonObject.get("PMY").getAsDouble(),
                  asJsonObject.get("PMZ").getAsDouble(),
                  asJsonObject.get("TMX").getAsDouble(),
                  asJsonObject.get("TMY").getAsDouble(),
                  asJsonObject.get("TMZ").getAsDouble(),
                  asJsonObject.get("MinX").getAsDouble(),
                  asJsonObject.get("MinY").getAsDouble(),
                  asJsonObject.get("MinZ").getAsDouble(),
                  asJsonObject.get("MaxX").getAsDouble(),
                  asJsonObject.get("MaxY").getAsDouble(),
                  asJsonObject.get("MaxZ").getAsDouble(),
                  asJsonObject.get("EyeX").getAsDouble(),
                  asJsonObject.get("EyeY").getAsDouble(),
                  asJsonObject.get("EyeZ").getAsDouble()
               )
            );
         }

         return list;
      }
   }

   public static float[] getScaleFactorArray(Entity entity2, float factor, int count, float factor2, int count2) {
      byte byteValue = 0;
      int count3 = 263465940;
      short shortValue = 6342;
      int count4 = (int)((((long)byteValue << 48 | (long)count3 << 32 >>> 16 | (long)shortValue << 48 >>> 48) ^ 0x77a2ed254aafL ^ 0x367e2617cbd9L) << 16 >>> 48);
      int count5 = (int)((((long)byteValue << 48 | (long)count3 << 32 >>> 16 | (long)shortValue << 48 >>> 48) ^ 0x77a2ed254aafL ^ 0x367e2617cbd9L) << 32 >>> 32);
      short shortValue2 = (short)count4;
      Vec3 vec3 = getVec3(entity2, shortValue2, count5);
      double offset = vec3.xCoord - MinecraftAccessor.minecraft.thePlayer.posX;
      double offset2 = vec3.yCoord - (MinecraftAccessor.minecraft.thePlayer.posY + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2());
      double offset3 = vec3.zCoord - MinecraftAccessor.minecraft.thePlayer.posZ;
      float factor3 = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor4 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      float doubleItem = (float)HitBoxUtils.getDouble((double)getFloat6(factor3), (double)getFloat6(factor));
      float factor5 = factor4 - factor2;
      return new float[]{offset == 0.0 && offset3 == 0.0 ? 0.0F : doubleItem, offset == 0.0 && offset3 == 0.0 && offset2 == 0.0 ? 0.0F : factor5};
   }

   public static float getFloat10(float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ, boolean flag, float factor) {
      float factor2 = Math.abs(posX4);
      double offset = (double)(posY + (posZ - posY) / 2.0F);
      float factor3 = getFloat9(posX, posX2, 180.0F) - posX;
      double offset2 = (double)(flag ? Math.abs(factor) : 0.0F);
      double offset3 = (double)Math.abs(factor3 / Math.max(factor2, 1.0F));
      double offset4 = Math.abs(((double)factor2 - offset2) / offset);
      float factor4 = MathHelper.clamp_float(factor2 + (offset3 > offset4 ? posX5 : -posX5), 0.0F, posX3);
      return getFloat9(posX, posX2, factor4);
   }

   public SimulationBoundingBox getCustomDoubleEntry(Rotation rotation5) {
      SimulationSnapshot simulationSnapshot = new SimulationSnapshot(
         HitBoxUtils.getDouble(
            MathHelper.getDouble4((double)MinecraftAccessor.minecraft.thePlayer.scaleFactor30),
            MathHelper.getDouble4((double)MinecraftAccessor.minecraft.thePlayer.scaleFactor11)
         ),
         (double)(MinecraftAccessor.minecraft.thePlayer.scaleFactor27 - MinecraftAccessor.minecraft.thePlayer.scaleFactor20)
      );
      double yaw = (double)rotation5.yaw;
      double pitch = (double)rotation5.pitch;
      double offset = yaw;
      SimulationSnapshot simulationSnapshot2 = simulationSnapshot;
      return new SimulationBoundingBox(simulationSnapshot2, offset, pitch);
   }

   private static AxisAlignedBB processAction(Entity entity2, double offset) {
      float height = entity2.getHeight();
      double offset2 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * offset;
      double offset3 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * offset;
      double offset4 = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * offset;
      float factor = entity2.width / 2.0F;
      return new AxisAlignedBB(offset2 - (double)factor, offset3, offset4 - (double)factor, offset2 + (double)factor, offset3 + (double)entity2.height, offset4 + (double)factor)
         .offset((double)height, (double)height, (double)height);
   }

   public Rotation getRotation(long value, Entity entity2, float factor, float factor2, boolean flag) {
      double offset = entity2.posX - MinecraftAccessor.minecraft.thePlayer.posX;
      double offset2 = entity2.posY
         + (double)entity2.getHeight2()
         - (MinecraftAccessor.minecraft.thePlayer.posY + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2());
      double offset3 = entity2.posZ - MinecraftAccessor.minecraft.thePlayer.posZ;
      float factor3 = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor4 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      float floatItem = getFloat9(factor, factor3, 180.0F);
      float floatItem2 = getFloat9(factor2, factor4, 180.0F);
      if (flag) {
         floatItem += (float)ThreadLocalRandom.current().nextGaussian();
         floatItem2 += (float)ThreadLocalRandom.current().nextGaussian();
      }

      return getRotation8(floatItem, floatItem2, factor, factor2);
   }

   public static float getScaleFactor(float posX, float posX2, float posY, float posZ, RangeSetting setting) {
      return getFloat10(posX, posX2, posY, posZ, (float)setting.getValue2(), (float)setting.getDouble9(), (float)setting.getDouble2(), false, 0.0F);
   }

   public float computeClampedTurnAngle(float posX, float posY, float posZ, RangeSetting setting, float factor) {
      boolean flag = CosmeticsPayload.checkCondition(1);
      float factor2 = Math.abs(factor);
      float factor3 = (float)setting.recalculate() + (float)(flag ? 15 : 0);
      double offset = setting.getDouble9() + (double)(flag ? 15 : 0) + (setting.getDouble2() - setting.getDouble9()) / 2.0;
      double offset2 = (double)Math.abs((getFloat9(posY, posZ, 180.0F) - posY) / Math.max(factor2, 1.0F));
      double offset3 = Math.abs((double)factor2 / offset);
      float factor4 = MathHelper.clamp_float(factor2 + (offset2 > offset3 ? factor3 : -factor3), 0.0F, posX);
      float floatItem = getFloat9(posY, posZ, factor4);
      return floatItem == posY ? posY : getFloat3(floatItem, posY);
   }

   public float getFloat11(float posX, float posY, float posZ) {
      float floatItem = getFloat9(posY, posZ, posX);
      if (floatItem != posZ) {
         double offset = (double)floatItem;
         float factor = 2.0F;
         float factor2 = 1.0F;
         floatItem = (float)(
            offset
               + (double)SettingContainer.settingProvider.getFloat2(factor2, factor)
                  * Math.sin((double)MinecraftAccessor.minecraft.thePlayer.rotationYaw * Math.PI)
         );
      }

      return getFloat3(floatItem, posY);
   }

   public static AxisAlignedBB processAction(EntityLivingBase livingEntity, double posX, double posY, double posZ) {
      float height = livingEntity.getHeight();
      float factor = livingEntity.width / 2.0F + height;
      return new AxisAlignedBB(
         posX - (double)factor, posY - (double)height, posZ - (double)factor, posX + (double)factor, posY + (double)livingEntity.height + (double)height, posZ + (double)factor
      );
   }

   public static AxisAlignedBB processAction(EntityLivingBase livingEntity) {
      float height = livingEntity.getHeight();
      return livingEntity.getBoundingBox2().offset((double)height, (double)height, (double)height);
   }

   public static Rotation getRotation10(int count, int count2, Rotation rotation5) {
      return getRotation11(count, count2, rotation5.yaw, rotation5.pitch);
   }

   public void updateState() {
      this.coordOffset36 = 0.0;
      this.coordOffset69 = 0.0;
      this.tertiaryFloatEntry.updateState();
      this.targetEntity = null;
   }

   public static Rotation getRotation11(int count, int count2, float factor, float factor2) {
      float factor3 = MinecraftAccessor.minecraft.gameSettings.scaleFactor * 0.6F + 0.2F;
      float factor4 = factor3 * factor3 * factor3 * 8.0F;
      float factor5 = (float)count * factor4;
      float factor6 = (float)count2 * factor4;
      float factor7 = (float)((double)factor + (double)factor5 * 0.15);
      float factor8 = MathHelper.clamp_float((float)((double)factor2 - (double)factor6 * 0.15), -90.0F, 90.0F);
      return new Rotation(factor7, factor8);
   }

   public SimulatedPlayer() {
      this.tertiaryArrayListEntry54 = new ArrayListData(10);
      this.tertiaryArrayListEntry43 = new ArrayListData(3);
      this.coordOffset38 = 0.0;
      this.coordOffset72 = 0.0;
      this.coordOffset41 = 0.0;
      this.coordOffset64 = 0.0;
      this.coordOffset51 = 0.0;
      this.supplementalItemEntry = new SimulationSnapshot(0.0, 0.0);
      this.indexOffset74 = 3;
      this.tertiaryArrayListEntry34 = new ArrayListData(9);
      this.scaleFactor91 = 0.0F;
      this.coordOffset93 = 0.0;
      this.coordOffset59 = 0.0;
      this.coordOffset4 = 0.0;
      this.state3 = true;
      this.coordOffset84 = 0.0;
      this.coordOffset26 = 0.142;
      this.state57 = false;
      this.indexOffset86 = 0;
      this.indexOffset52 = 0;
      this.indexOffset80 = 3;
      this.indexOffset = 2;
      this.indexOffset48 = 2;
      this.indexOffset75 = 0;
      this.coordOffset81 = 8.0;
      this.coordOffset18 = 0.0;
      this.coordOffset8 = 0.0;
      this.coordOffset23 = 1.0;
      this.coordOffset2 = 1.0;
      this.state70 = false;
      this.coordOffset35 = 0.0;
      this.coordOffset37 = 0.3;
      this.coordOffset33 = 60.0;
      this.coordOffset53 = 0.0;
      this.coordOffset30 = 0.0;
      this.scaleFactor47 = 0.05F;
      this.coordOffset = 0.0;
      this.coordOffset56 = 10.0;
      this.state55 = true;
      this.state13 = true;
      this.state = false;
      this.state22 = true;
      this.indexOffset66 = 3;
      this.indexOffset85 = 5;
      this.indexOffset46 = 4;
      this.indexOffset5 = 4;
      this.scaleFactor32 = 3.0F;
      this.scaleFactor15 = 6.0F;
      this.scaleFactor79 = 20.0F;
      this.scaleFactor77 = 30.0F;
      this.coordOffset1 = 1.5;
      this.coordOffset6 = 0.0;
      this.tertiaryArrayListEntry = new ArrayListData(4);
      this.tertiaryArrayListEntry88 = new ArrayListData(3);
      this.state44 = false;
      this.coordOffset60 = 0.0;
      this.coordOffset82 = 0.5;
      this.coordOffset87 = 0.0;
      this.indexOffset45 = 0;
      this.scaleFactor50 = 0.0F;
      this.coordOffset25 = 1.0;
      this.backtrackRecord = new BacktrackRecord(6);
      this.secondaryArrayListEntry = new ArrayListWrapper(6);
      this.entity = null;
      this.customFloatEntry = new RecordedRotation();
      this.tertiaryFloatEntry = new SimulationAngle();
      this.positionVec5 = new Vec3(0.0, 0.0, 0.0);
      this.itemList = new ArrayList();
      this.rotation4 = null;
      this.rotation3 = null;
      this.coordOffsetArray67 = new double[]{0.0, 0.0};
      this.itemList2 = new ArrayList();
      this.rotation = new Rotation();
      this.rotation2 = new Rotation();
      this.targetEntity = null;
      this.scaleFactor24 = 10.0F;
      this.scaleFactor = 10.0F;
      this.indexOffset92 = 0;
      this.extendedDoubleEntry = null;
      this.auxiliaryFloatEntry = null;
      this.indexOffset19 = 0;
      this.timer = new TimeHelper();
      this.indexOffset21 = 2000;
   }
}
