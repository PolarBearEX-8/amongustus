package client.util;

import client.Client;
import client.ClientGlobals;
import client.event.EventMove;
import client.event.ProviderListener;
import client.network.CosmeticsPayload;
import client.rotation.Rotation;
import client.setting.ArrowDodgerPhase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiConnecting;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RecordedRotation implements MinecraftAccessor {
   public float factor2;
   public double value9;
   public int count6;
   public double value25;
   public double value16;
   public int count2;
   public double value6;
   public double value12;
   public double value23;
   public SimulationBoundingBox simulationBoundingBox;
   public Random random;
   public CustomHashMapEntry customHashMapEntry = new CustomHashMapEntry();
   public double[] valueArray;
   public float factor3;
   public MotionVectorSnapshot motionVectorSnapshot;
   public double value17;
   public Listener72Entry listener72Entry = new Listener72Entry();
   public double value22;
   public int count4;
   public double value24;
   public EntityLivingBase entityLivingBase;
   public float factor4;
   public double value27;
   public int count3;
   public double value18;
   public double value7;
   public GenericFloatEntry genericFloatEntry;
   public int count;
   public int count9;
   public double value4;
   public double value21;
   public double value29;
   public SimulationSnapshot simulationSnapshot;
   public double value11;
   public double value10;
   public int count7;
   public double value14;
   public boolean enabled4;
   public double value15;
   public boolean enabled;
   public double value20;
   public double value13;
   public int count5;
   public boolean enabled2;
   public double value28;
   public boolean enabled5;
   public RandomizationGenerator randomizationGenerator = new RandomizationGenerator();
   public double value3;
   public ArrayListData arrayListData;
   public double value;
   public float factor;
   public double value8;
   public int count10 = 0;
   public double value26;
   public boolean enabled6;
   public int count8;
   public CoreDoubleEntry[] coreDoubleEntryArray;
   public double value19;
   public boolean enabled3;
   public double value2;
   public double value5;

   private void applyEntityPlayerSP(EntityPlayerSP player, EntityLivingBase livingEntity, double offset) {
      long value30 = 0x2ceb7395228L;
      ArrayList list;
      ArrayList list2;
      if (this.listener72Entry.providerListener != null) {
         int ticksExisted = player.ticksExisted;
         list = this.listener72Entry.providerListener.resolveArrayList(ticksExisted);
         int ticksExisted2 = player.ticksExisted;
         list2 = this.listener72Entry.providerListener.resolveArrayListByIndex(ticksExisted2);
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
         list = CombatUtil.onMove(5, player, offset4, offset3, offset2, event);
         int size = list.size();
         list2 = CombatTargetFilter.resolveArrayListByEntityLivingBase(livingEntity, size);
      }

      list.add(player.getVec32(1.0F));
      list2.add(livingEntity.getVec32(1.0F));
      int count11 = Math.min(list.size(), list2.size());
      double[][] offsetMatrix = new double[count11][];
      ArrayList list3 = new ArrayList();

      for (int index = 0; index < count11; index++) {
         AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB((Vec3)list2.get(index), false);
         double[] coordOffsetArrayByVec = TrajectoryPredictor.getCoordOffsetArrayByVec3(((Vec3)list.get(index)).getVec312(0.0, (double)player.getHeight2(), 0.0), axisAlignedBB);
         offsetMatrix[index] = coordOffsetArrayByVec;
         list3.add(coordOffsetArrayByVec[0]);
         list3.add(coordOffsetArrayByVec[1]);
      }

      double offset5 = offset;
      double offset6 = offset;
      double offset7 = 0.0;
      int count12 = -1;
      int count13 = 0;

      for (int index2 = 0; index2 < list3.size(); index2++) {
         double offset8 = (Double)list3.get(index2);
         int count14 = 0;

         for (double[] offsetArray : offsetMatrix) {
            if (offset8 >= offsetArray[0] && offset8 <= offsetArray[1]) {
               count14++;
            }
         }

         if (count14 > count12 || count14 == count12 && Math.abs(offset - offset8) < offset7) {
            offset5 = offset8;
            offset7 = Math.abs(offset - offset8);
            count12 = count14;
            count13 = index2;
         }
      }

      double offset9 = 0.0;
      int count15 = -1;

      for (int index3 = 0; index3 < list3.size(); index3++) {
         if (index3 != count13) {
            double offset10 = (Double)list3.get(index3);
            if (offset10 != offset5) {
               int count16 = 0;

               for (double[] offsetArray2 : offsetMatrix) {
                  if (offset10 >= offsetArray2[0] && offset10 <= offsetArray2[1]) {
                     count16++;
                  }
               }

               if (count16 > count15 || count16 == count15 && Math.abs(offset5 - offset10) < offset9) {
                  offset6 = offset10;
                  offset9 = Math.abs(offset5 - offset10);
                  count15 = count16;
                  count13 = index3;
               }
            }
         }
      }

      this.valueArray = new double[]{Math.min(offset5, offset6), Math.max(offset5, offset6)};
      this.value18 = MathHelper.getDouble7(offset, this.valueArray[0], this.valueArray[1]);
   }

   public void applyFloat(float factor5, float factor6, boolean flag) {
      if (this.simulationBoundingBox != null) {
         double offset = (double)factor5;
         double offset2 = (double)factor6;
         double offset3 = offset;
         this.simulationBoundingBox.applyDouble(offset3, offset2);
         if (flag) {
            SimulationBoundingBox simulationBoundingBox2 = this.simulationBoundingBox;
            SimulationSnapshot simulationSnapshot2 = new SimulationSnapshot(
               HitBoxUtils.getDouble(MathHelper.getDouble4((double)MinecraftAccessor.minecraft.thePlayer.scaleFactor11), MathHelper.getDouble4((double)factor5))
                  / 0.15,
               (double)(factor6 - MinecraftAccessor.minecraft.thePlayer.scaleFactor27) / 0.15
            );
            simulationBoundingBox2.setSimulationSnapshot(simulationSnapshot2);
         }
      }
   }

   private CoreDoubleEntry[] resolveCoreDoubleEntryArray(
      SimulationBoundingBox simulationBoundingBox2, EntityPlayerSP player, EntityLivingBase livingEntity, double posX, double posY, double posZ
   ) {
      long value30 = 0x445f06e82d7fL;
      long keySalt = 0x250ad7cfc34fL ^ value30 ^ 0x1b1ab56d2d98L;
      int count11 = (int)((0x250ad7cfc34fL ^ value30 ^ 0x1b1ab56d2d98L) >>> 48);
      int count12 = (int)((0x250ad7cfc34fL ^ value30 ^ 0x1b1ab56d2d98L) << 16 >>> 32);
      int count13 = (int)(keySalt << 48 >>> 48);
      int count14 = this.count9 > this.count3 ? this.random.nextInt(this.count9 - this.count3 + 1) : 0;
      this.listener72Entry.factor2 = this.factor2;
      short shortValue = (short)count11;
      int count15 = player.ticksExisted + this.count3 + count14;
      int count16 = this.count;
      int count17 = count15;
      int count18 = this.count10;
      short shortValue2 = shortValue;
      CoreDoubleEntry[] coreDoubleEntryArray2 = this.listener72Entry.resolveCoreDoubleEntryArray(player, livingEntity, shortValue2, count18, count17, count16, count12, count13, posX, posY, posZ);
      if (count14 > 0 && coreDoubleEntryArray2 != null && Math.abs(coreDoubleEntryArray2[0].value4 + coreDoubleEntryArray2[1].value4) / 2.0 > this.value3 / (double)count14) {
         shortValue = (short)count11;
         int count19 = player.ticksExisted + this.count3;
         int count20 = this.count10;
         short shortValue3 = shortValue;
         coreDoubleEntryArray2 = this.listener72Entry.resolveCoreDoubleEntryArray(player, livingEntity, shortValue3, count20, count19, 3, count12, count13, posX, posY, posZ);
      }

      return coreDoubleEntryArray2;
   }

   public void applyProviderListener(ProviderListener providerListener) {
      this.listener72Entry.providerListener = providerListener;
   }

   public void applyDouble(double offset) {
      this.value5 = offset;
      this.motionVectorSnapshot = null;
   }

   private void applyArrayList(ArrayList list, ArrayList list2) {
      Vec3 vec = (Vec3)list.get(0);
      Vec3 vec2 = (Vec3)list2.get(0);
      double coordinate = vec2.getCoordinate4(vec);
      double offset = 0.0;
      double offset2 = 0.0;
      int count11 = Math.min(list.size(), list2.size());
      int count12 = 1000;

      for (int index = 1; index < count11; index++) {
         if (index == 1) {
            offset = ((Vec3)list.get(index)).getCoordinate4((Vec3)list2.get(index));
            if (coordinate == offset) {
               return;
            }

            offset2 = offset;
         } else {
            double coordinate2 = ((Vec3)list.get(index)).getCoordinate4((Vec3)list2.get(index));
            if (offset > coordinate) {
               if (coordinate2 < offset2) {
                  count12 = index;
                  break;
               }
            } else if (coordinate2 > offset2) {
               count12 = index;
               break;
            }

            offset2 = coordinate2;
         }
      }

      while (list.size() > count12) {
         list.remove(list.size() - 1);
      }

      while (list2.size() > count12) {
         list2.remove(list2.size() - 1);
      }
   }

   public static boolean matchesDoubleArray(double[] offsetArray) {
      Vec3 vec = new Vec3(0.0, 0.0, 0.0);
      AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(new Vec3(3.4, 0.0, 0.0), true);
      double[] coordOffsetArray = TrajectoryPredictor.getCoordOffsetArray2(vec, axisAlignedBB);
      double doubleItem = TrajectoryPredictor.getDouble4(MathHelper.getDouble4(coordOffsetArray[0]), MathHelper.getDouble4(coordOffsetArray[1]));
      if (TrajectoryPredictor.getDouble4(MathHelper.getDouble4(offsetArray[0]), MathHelper.getDouble4(offsetArray[1])) < doubleItem) {
         double offset = (offsetArray[0] + offsetArray[1]) / 2.0;
         offsetArray[0] = offset - doubleItem / 2.0;
         offsetArray[1] = offset + doubleItem / 2.0;
         return true;
      } else {
         return false;
      }
   }

   private void applySimulationBoundingBoxBySimulationBoundi(
      SimulationBoundingBox simulationBoundingBox2, EntityPlayerSP player, EntityLivingBase livingEntity, double offset, double[] offsetArray, double[] offsetArray2, double offset2, boolean flag
   ) {
      double[] offsetArray3 = offsetArray;
      double[] offsetArray4 = offsetArray2;
      long value30 = 0x7334c30a070eL;
      double coordinate = simulationBoundingBox2.getCoordinate2();
      double coordinate2 = simulationBoundingBox2.getCoordinate();
      double offset3 = 0.0;
      double offset4 = 1.0;
      if (this.motionVectorSnapshot != null) {
         MotionVectorSnapshot motionVectorSnapshot2 = this.motionVectorSnapshot;
         double value31 = this.motionVectorSnapshot.getValue();
         SimulationSnapshot simulationSnapshot2 = motionVectorSnapshot2.resolveSimulationSnapshot(value31);
         motionVectorSnapshot2 = this.motionVectorSnapshot;
         double value32 = this.motionVectorSnapshot.getValue4();
         SimulationSnapshot simulationSnapshot3 = motionVectorSnapshot2.resolveSimulationSnapshot(value32);
         SimulationSnapshot simulationSnapshot4 = simulationSnapshot2.resolveSimulationSnapshot(simulationSnapshot3);
         coordinate = MathHelper.getDouble4(simulationSnapshot2.value);
         coordinate2 = simulationSnapshot2.value2;
         offset3 = Math.atan2(simulationSnapshot4.value2, simulationSnapshot4.value);
         if (!Double.isFinite(offset3)) {
            offset3 = 0.0;
         }

         if (!this.motionVectorSnapshot.enabled) {
            double offset5 = 0.5;
            double doubleItem = TrajectoryPredictor.getDouble4(
               this.motionVectorSnapshot.value2, MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(offsetArray, offset5))
            );
            Vec3 vec3 = player.getVec35(1.0F);
            if (flag && doubleItem < this.value24) {
               AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(livingEntity.getVec32(1.0F), true);
               if (SimulatedPlayer.processAction(vec3, axisAlignedBB).getCoordinate4(vec3) > 3.0) {
                  if (this.enabled5) {
                     offsetArray3 = this.motionVectorSnapshot.valueArray;
                  }

                  if (this.enabled3) {
                     offsetArray4 = this.motionVectorSnapshot.valueArray2;
                  }
               }
            }
         }
      }

      SimulationSnapshot simulationSnapshot5 = new SimulationSnapshot(coordinate, coordinate2);
      double offset6 = this.random.nextDouble() * this.value19 * (double)(this.random.nextBoolean() ? -1 : 1);
      double doubleItem2 = TrajectoryPredictor.getDouble4(MathHelper.getDouble4(offsetArray3[0]), MathHelper.getDouble4(offsetArray3[1]));
      double offset7 = Math.abs(offsetArray4[0] - offsetArray4[1]);
      double offset8 = Math.min(doubleItem2, offset7);
      float factor5 = 0.0F;
      float factor6 = this.factor;
      double offset9 = offset8 * PlayerUtil.getDouble(0.3, 0.8, (double)Math.abs(this.customHashMapEntry.computeFactor(factor6, factor5))) / 2.0;
      this.factor += 0.025F;
      SimulationSnapshot simulationSnapshot6 = new SimulationSnapshot(Math.cos(offset3 + offset6 / 180.0 * Math.PI) * offset9, Math.sin(offset3 + offset6 / 180.0 * Math.PI) * offset9);
      if (this.simulationSnapshot != null) {
         double offset10 = this.value29 > this.value14 ? ThreadLocalRandom.current().nextDouble(this.value14, this.value29) : this.value14;
         simulationSnapshot6 = this.simulationSnapshot.resolveSimulationSnapshot(simulationSnapshot6, offset10);
      }

      this.simulationSnapshot = simulationSnapshot6;
      double offset11 = 0.5;
      double coordinate3 = TrajectoryPredictor.getCoordinate(offsetArray3, offset11);
      double offset12 = 0.5;
      SimulationSnapshot simulationSnapshot7 = new SimulationSnapshot(coordinate3, TrajectoryPredictor.getCoordinate(offsetArray4, offset12));
      SimulationSnapshot simulationSnapshot8 = simulationSnapshot6.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot7);
      ArrayListData arrayListData2 = this.arrayListData;
      double offset13 = Math.abs(offset);
      arrayListData2.processAction(offset13);
      double offset14 = TrajectoryPredictor.computeValue2(offsetArray3[0], offsetArray3[1], MathHelper.getDouble4(simulationSnapshot8.value));
      double value33 = simulationSnapshot8.value2;
      double doubleItem3 = PlayerUtil.getDouble(
         TrajectoryPredictor.computeValue(offsetArray4, value33), MathHelper.getDouble7(TrajectoryPredictor.computeValue(offsetArray4, coordinate2), 0.0, 1.0), 0.2
      );
      Double.isNaN(doubleItem3);
      if (!Double.isFinite(this.value12)) {
         this.value12 = 0.0;
      }

      double doubleItem4 = PlayerUtil.getDouble(doubleItem3, this.value12, 0.5);
      this.value27 = offset14;
      this.value12 = doubleItem3;
      double offset15 = (double)Math.abs(RecordNode16Helper.computeFactor(this.factor4, 3.23F));
      simulationSnapshot8.value = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(offsetArray3, offset14)) + PlayerUtil.getDouble(this.value2, this.value17, offset15);
      simulationSnapshot8.value2 = TrajectoryPredictor.getCoordinate(offsetArray4, doubleItem4)
         + PlayerUtil.getDouble(this.value15, this.value9, (double)Math.abs(RecordNode16Helper.computeFactor(this.factor3, -5.34F)));
      simulationSnapshot8.value2 = PlayerUtil.getDouble(simulationSnapshot8.value2, offset2, this.value13);
      if (Double.isFinite(this.value5)) {
         simulationSnapshot8.value2 = this.value5;
         this.value5 = Double.NaN;
      }

      simulationSnapshot8.value = HitBoxUtils.getDouble(simulationSnapshot5.value, MathHelper.getDouble4(simulationSnapshot8.value));
      simulationSnapshot8.value2 = simulationSnapshot8.value2 - simulationSnapshot5.value2;
      SimulationSnapshot simulationSnapshotAsSimulationSnapsh = simulationSnapshot8.getSimulationSnapshotAsSimulationSnapsh();
      if (simulationSnapshot8.sqrt() == 0.0) {
         simulationSnapshotAsSimulationSnapsh = simulationSnapshot8;
      }

      SimulationSnapshot simulationSnapshot9 = simulationSnapshotAsSimulationSnapsh.getSimulationSnapshot();
      if (!Double.isFinite(simulationSnapshot9.value) || !Double.isFinite(simulationSnapshot9.value2)) {
         simulationSnapshot9 = new SimulationSnapshot(0.0, 0.0);
      }

      double offset16 = this.random.nextDouble() * 0.2 + 0.4;
      double doubleItem5 = MathHelper.getDouble7(
         PlayerUtil.getDouble(this.value22, this.value25, Math.random()) * simulationSnapshot8.sqrt() * (double)(ThreadLocalRandom.current().nextBoolean() ? -1 : 1),
         -this.value23,
         this.value23
      );
      SimulationSnapshot simulationSnapshot10 = simulationSnapshot8.resolveSimulationSnapshotByDouble(offset16);
      SimulationSnapshot simulationSnapshot11 = simulationSnapshot9.resolveSimulationSnapshotByDouble(doubleItem5);
      SimulationSnapshot simulationSnapshot12 = simulationSnapshot10.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot11);
      if (simulationSnapshot9.sqrt() == 0.0) {
         simulationSnapshot12 = new SimulationSnapshot(0.0, 0.0);
      }

      MotionVectorSnapshot motionVectorSnapshot3 = new MotionVectorSnapshot();
      if (Double.isFinite(simulationSnapshot5.value)
         && Double.isFinite(simulationSnapshot5.value2)
         && Double.isFinite(simulationSnapshot12.value)
         && Double.isFinite(simulationSnapshot12.value2)
         && Double.isFinite(simulationSnapshot8.value)) {
         Double.isFinite(simulationSnapshot8.value2);
      }

      motionVectorSnapshot3.arrayList.add(simulationSnapshot5);
      motionVectorSnapshot3.arrayList.add(simulationSnapshot12.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot5));
      motionVectorSnapshot3.arrayList.add(simulationSnapshot8.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot5));
      if (this.enabled6) {
         motionVectorSnapshot3.arrayList.add(simulationSnapshot8.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot5));
      }

      double offset17 = 1.0 / (double)(this.count3 + 1);
      motionVectorSnapshot3.applyDoubleByDouble(offset17);
      double offset18 = 0.5;
      motionVectorSnapshot3.value2 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(offsetArray3, offset18));
      motionVectorSnapshot3.value3 = offset4;
      motionVectorSnapshot3.valueArray = offsetArray3;
      motionVectorSnapshot3.valueArray2 = offsetArray4;
      motionVectorSnapshot3.count = this.count3;
      this.motionVectorSnapshot = motionVectorSnapshot3;
   }

   private void applyEntityPlayerSPByEntityPlayerSP(EntityPlayerSP player, EntityLivingBase livingEntity) {
      long value30 = 0xe2293a76524L;
      long keySalt = 0x250ad7cfc34fL ^ value30;
      long keySalt2 = 0x250ad7cfc34fL ^ value30 ^ 0x48115f77e7b8L;
      int count11 = (int)((0x250ad7cfc34fL ^ value30 ^ 0x48115f77e7b8L) >>> 56);
      int count12 = (int)((0x250ad7cfc34fL ^ value30 ^ 0x48115f77e7b8L) << 8 >>> 32);
      int count13 = (int)(keySalt2 << 40 >>> 40);
      int ticksExisted = player.ticksExisted;
      int count14 = this.count10;
      Vec3 vec = this.listener72Entry.resolveVec3(player, count14, ticksExisted);
      byte byteValue = (byte)count11;
      int ticksExisted2 = player.ticksExisted;
      byte byteValue2 = byteValue;
      int count15 = this.count10;
      Vec3 vec2 = this.listener72Entry.resolveVecByEntityLivingBase(livingEntity, count15, byteValue2, ticksExisted2, count12, count13);
      Vec3 vec3 = player.getVec32(1.0F);
      Vec3 vec32 = livingEntity.getVec32(1.0F);
      Vec3 vec33 = vec2.getVec311(vec);
      Vec3 vec34 = vec32.getVec311(vec3);
      double doubleItem = MathHelper.getDouble4(Math.atan2(vec33.zCoord, vec33.xCoord) / Math.PI * 180.0);
      double doubleItem2 = MathHelper.getDouble4(Math.atan2(vec34.zCoord, vec34.xCoord) / Math.PI * 180.0);
      if (vec33.getVec311(vec34).sqrt() < 0.2 && TrajectoryPredictor.getDouble4(doubleItem, doubleItem2) < 2.0) {
         this.count10 = Math.max(player.ticksExisted - this.count6, 0);
      }
   }

   private double computeValue(double[] offsetArray, double[] offsetArray2) {
      return Math.min(
         TrajectoryPredictor.getDouble4((offsetArray[0] + offsetArray[1]) * 0.5, (offsetArray2[0] + offsetArray2[1]) * 0.5),
         (TrajectoryPredictor.getDouble4(offsetArray[0], offsetArray2[0]) + offsetArray[1] + offsetArray2[1]) * 0.5
      );
   }

   private boolean matchesDoubleArray(double[] offsetArray, double[] offsetArray2) {
      double offset = TrajectoryPredictor.computeValue(offsetArray2[0], offsetArray2[1], offsetArray[0]);
      double offset2 = TrajectoryPredictor.computeValue(offsetArray2[0], offsetArray2[1], offsetArray[1]);
      return offset >= 0.0 && offset <= 1.0 && offset2 >= 0.0 && offset2 <= 1.0;
   }

   public Rotation resolveRotationByEntityLivingBase(
      EntityLivingBase livingEntity, EntityPlayerSP player, float factor5, float factor6, double offset, double offset2, Vec3 vec, Vec3 vec2
   ) {
      long value30 = 0x44e93ce323b6L;
      long keySalt = 0x250ad7cfc34fL ^ value30;
      long keySalt2 = 0x250ad7cfc34fL ^ value30 ^ 0x785785282fd6L;
      int count11 = (int)((0x250ad7cfc34fL ^ value30 ^ 0x785785282fd6L) >>> 48);
      int count12 = (int)((0x250ad7cfc34fL ^ value30 ^ 0x785785282fd6L) << 16 >>> 48);
      int count13 = (int)(keySalt2 << 32 >>> 32);
      keySalt2 = keySalt ^ 0x1ea429d5e634L;
      int count14 = (int)((keySalt ^ 0x1ea429d5e634L) >>> 48);
      int count15 = (int)((keySalt ^ 0x1ea429d5e634L) << 16 >>> 32);
      int count16 = (int)(keySalt2 << 48 >>> 48);
      if (CosmeticsPayload.checkCondition(6)) {
         return new Rotation(factor5, factor6);
      } else {
         if (!CosmeticsPayload.checkCondition(1) && !Client.getClient().getString7().equals("Tom") && !Client.getClient().getString7().equals("karuz")) {
            if (GuiConnecting.labelText2 == null || !GuiConnecting.labelText2.toLowerCase().contains("ngrok")) {
               this.randomizationGenerator.state = this.enabled4;
               this.randomizationGenerator.enabled = true;
               this.randomizationGenerator.coordOffset = this.value11;
               this.randomizationGenerator.value2 = this.value;
               this.randomizationGenerator.value = this.value6;
               if (this.simulationBoundingBox == null || this.simulationBoundingBox.getCount() + 1 != player.ticksExisted) {
                  SimulationSnapshot simulationSnapshot2 = new SimulationSnapshot(
                     HitBoxUtils.getDouble(
                           MathHelper.getDouble4((double)MinecraftAccessor.minecraft.thePlayer.scaleFactor30),
                           MathHelper.getDouble4((double)MinecraftAccessor.minecraft.thePlayer.scaleFactor11)
                        )
                        / 0.15
                        / 3.0,
                     (double)(MinecraftAccessor.minecraft.thePlayer.scaleFactor27 - MinecraftAccessor.minecraft.thePlayer.scaleFactor20) / 0.15 / 3.0
                  );
                  double offset3 = (double)factor5;
                  double offset4 = (double)factor6;
                  double offset5 = offset3;
                  SimulationSnapshot simulationSnapshot3 = simulationSnapshot2;
                  this.simulationBoundingBox = new SimulationBoundingBox(simulationSnapshot3, offset5, offset4);
                  this.motionVectorSnapshot = null;
                  this.count10 = player.ticksExisted;
                  this.count7 = player.ticksExisted;
                  this.simulationSnapshot = null;
                  this.factor = (float)ThreadLocalRandom.current().nextDouble(-100.0, 100.0);
               }

               if (this.entityLivingBase != livingEntity) {
                  this.motionVectorSnapshot = null;
                  double offset6 = (double)factor5;
                  double offset7 = (double)factor6;
                  double offset8 = offset6;
                  this.simulationBoundingBox.applyDouble(offset8, offset7);
                  this.simulationSnapshot = null;
                  this.factor = (float)ThreadLocalRandom.current().nextDouble(-100.0, 100.0);
               }

               this.entityLivingBase = livingEntity;
               if (player.ticksExisted - this.count10 > this.count2 || this.enabled2 && player.ticksExisted - this.count7 > this.count2) {
                  this.count7 = this.count10 = player.ticksExisted
                     - (this.count4 < this.count2 ? ThreadLocalRandom.current().nextInt(this.count4, this.count2) : this.count4);
               }

               if (this.count10 < player.ticksExisted && Math.random() <= this.value16) {
                  this.applyEntityPlayerSPByEntityPlayerSP(player, livingEntity);
               }

               double offset9 = (double)factor6;
               this.applyEntityPlayerSP(player, livingEntity, offset9);
               short shortValue = (short)count14;
               short shortValue2 = (short)count16;
               SimulationBoundingBox simulationBoundingBox2 = this.simulationBoundingBox;
               short shortValue3 = shortValue;
               SimulationBoundingBox simulationBoundingBox3 = this.resolveSimulationBoundingBox(shortValue3, simulationBoundingBox2, count15, shortValue2, player, livingEntity, offset2);
               this.simulationBoundingBox = simulationBoundingBox3;
               float[] factorArray = new float[2];
               if (this.enabled
                  && ClientGlobals.moduleManager.killAura.clickPatternProfile != null
                  && ClientGlobals.moduleManager.killAura.settingAdvanced.getSelected().equals("Recorded")) {
                  factorArray = ClientGlobals.moduleManager.killAura.clickPatternProfile.getFloatArray();
               }

               return new Rotation((float)simulationBoundingBox3.getCoordinate2() + factorArray[0], (float)simulationBoundingBox3.getCoordinate() + factorArray[1]);
            }

            Client.getClient().getClass();
         }

         this.genericFloatEntry.count8 = Math.max(this.count4, 4);
         this.genericFloatEntry.count5 = Math.max(this.count2, 4);
         this.genericFloatEntry.count7 = this.count;
         this.genericFloatEntry.count10 = Math.max(0, this.count6);
         this.genericFloatEntry.value7 = Math.max(0.5, this.value16);
         this.genericFloatEntry.value2 = this.value26;
         this.genericFloatEntry.value5 = this.value11;
         this.genericFloatEntry.value4 = this.value;
         this.genericFloatEntry.value6 = this.value6;
         this.genericFloatEntry.enabled3 = this.enabled2;
         this.genericFloatEntry.enabled4 = !this.enabled6;
         short shortValue4 = (short)count11;
         char character = (char)count12;
         short shortValue5 = shortValue4;
         return this.genericFloatEntry.resolveRotation(shortValue5, livingEntity, player, character, count13, factor5, factor6, offset, offset2, vec, vec2);
      }
   }

   private SimulationBoundingBox resolveSimulationBoundingBox(
      short shortValue, SimulationBoundingBox simulationBoundingBox2, int count11, short shortValue2, EntityPlayerSP player, EntityLivingBase livingEntity, double offset
   ) {
      byte byteValue = 0;
      int count12 = 2135409401;
      short shortValue3 = 1741;
      long keySalt = ((long)byteValue << 48 | (long)count12 << 32 >>> 16 | (long)shortValue3 << 48 >>> 48) ^ 0x250ad7cfc34fL ^ 0x1bcc1bd43aa9L;
      int count13 = (int)((((long)byteValue << 48 | (long)count12 << 32 >>> 16 | (long)shortValue3 << 48 >>> 48) ^ 0x250ad7cfc34fL ^ 0x1bcc1bd43aa9L) >>> 48);
      int count14 = (int)((((long)byteValue << 48 | (long)count12 << 32 >>> 16 | (long)shortValue3 << 48 >>> 48) ^ 0x250ad7cfc34fL ^ 0x1bcc1bd43aa9L) << 16 >>> 48);
      double value30 = this.value26;
      double value31 = this.value7;
      double value32 = this.value21;
      CoreDoubleEntry[] coreDoubleEntryArray2 = this.resolveCoreDoubleEntryArray(simulationBoundingBox2, player, livingEntity, value32, value31, value30);
      if (coreDoubleEntryArray2 == null) {
         coreDoubleEntryArray2 = new CoreDoubleEntry[]{
            new CoreDoubleEntry(0.0, 0.0, simulationBoundingBox2.getCoordinate2(), simulationBoundingBox2.getCoordinate2(), 0),
            new CoreDoubleEntry(0.0, 0.0, simulationBoundingBox2.getCoordinate2(), simulationBoundingBox2.getCoordinate2(), 0)
         };
         if (this.motionVectorSnapshot == null || this.motionVectorSnapshot.getValue() >= 1.0) {
            this.coreDoubleEntryArray = coreDoubleEntryArray2;
            this.motionVectorSnapshot = new MotionVectorSnapshot();
            this.motionVectorSnapshot.enabled = true;
         }
      }

      double value33 = coreDoubleEntryArray2[1].value;
      double value34 = coreDoubleEntryArray2[0].value;
      double[] offsetArray = TrajectoryPredictor.resolveDoubleArray(value34, value33);
      offsetArray[0] -= this.value26;
      offsetArray[1] += this.value26;
      boolean flag = matchesDoubleArray(offsetArray);
      this.factor4 = (float)((double)this.factor4 + this.value4);
      this.factor3 = (float)((double)this.factor3 + this.value8);
      if (this.motionVectorSnapshot != null && !(this.motionVectorSnapshot.getValue() >= 1.0) && !(this.random.nextDouble() < 0.01)) {
         double offset2 = 1.0 / (double)(this.count3 + 1);
         this.motionVectorSnapshot.applyDouble(offset2);
      } else {
         this.coreDoubleEntryArray = coreDoubleEntryArray2;
         double offset3 = (this.coreDoubleEntryArray[0].value4 + this.coreDoubleEntryArray[1].value4) / 2.0;
         double offset4 = offset3;
         if (offset3 == 0.0) {
            offset4 = this.random.nextBoolean() ? -1.0 : 1.0;
         }

         int count15 = offset3 < 0.0 ? -1 : (offset3 > 0.0 ? 1 : 0);
         if (this.count5 == -1 || (count15 != this.count8 || offset3 == 0.0) && this.count5 > 20) {
            this.value28 = offset4;
            this.count5 = 0;
         }

         this.count8 = count15;
         double[] valueArray2 = this.valueArray;
         this.applySimulationBoundingBoxBySimulationBoundi(simulationBoundingBox2, player, livingEntity, offset3, offsetArray, valueArray2, offset, flag);
      }

      this.count5++;
      double offset5 = (this.coreDoubleEntryArray[0].value4 + this.coreDoubleEntryArray[1].value4) / 2.0;
      MotionVectorSnapshot motionVectorSnapshot2 = this.motionVectorSnapshot;
      SimulationBoundingBox simulationBoundingBox3 = this.randomizationGenerator.resolveSimulationBoundingBox(simulationBoundingBox2, motionVectorSnapshot2, offset5);
      simulationBoundingBox3.setValueArray(offsetArray);
      return simulationBoundingBox3;
   }

   public void applyHashMap(HashMap map) {
      this.customHashMapEntry.hashMap = map;
   }

   public RecordedRotation() {
      this.count7 = 0;
      this.entityLivingBase = null;
      this.coreDoubleEntryArray = null;
      this.value28 = 0.0;
      this.count8 = 0;
      this.count5 = 0;
      this.arrayListData = new ArrayListData(4);
      this.value5 = Double.NaN;
      this.simulationSnapshot = null;
      this.value10 = 0.5;
      this.value20 = 0.5;
      this.factor4 = 0.0F;
      this.factor3 = 0.0F;
      this.count4 = 0;
      this.count2 = 0;
      this.count3 = 2;
      this.count9 = 3;
      this.count = 3;
      this.value21 = 0.0;
      this.value7 = 0.0;
      this.factor2 = 0.05F;
      this.count6 = 0;
      this.value16 = 0.0;
      this.value26 = 0.0;
      this.value22 = 0.0;
      this.value25 = 0.3;
      this.value11 = 8.0;
      this.value = 1.0;
      this.value6 = 1.0;
      this.enabled2 = false;
      this.value19 = 60.0;
      this.value3 = 30.0;
      this.value13 = 0.0;
      this.value14 = 1.0;
      this.value29 = 1.0;
      this.value2 = -10.0;
      this.value17 = 10.0;
      this.value15 = 0.0;
      this.value9 = 0.0;
      this.value4 = 0.05F;
      this.value8 = 0.0;
      this.value23 = 10.0;
      this.enabled6 = true;
      this.enabled4 = false;
      this.enabled = false;
      this.value27 = 0.5;
      this.value12 = 0.5;
      this.value24 = 15.0;
      this.enabled5 = true;
      this.enabled3 = true;
      this.genericFloatEntry = new GenericFloatEntry();
      this.random = new Random();
      this.factor = 0.0F;
   }
}
