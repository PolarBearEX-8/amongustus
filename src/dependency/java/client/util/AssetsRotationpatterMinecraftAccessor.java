package client.util;

import client.event.EventMove;
import client.rotation.Rotation;
import client.setting.ArrowDodgerPhase;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class AssetsRotationpatterMinecraftAccessor implements MinecraftAccessor {
   public Listener72Entry listener72Entry = new Listener72Entry();
   public double[] valueArray;
   public double value;
   public SimulationSnapshot simulationSnapshot;
   public SimulationSnapshotMinecraftAccessor simulationSnapshotMinecraftAccessor;
   public SimulationSnapshotController simulationSnapshotController;
   public static ArrayList arrayList = new ArrayList();
   public long timestamp;
   public int count = 2;
   public double value2;
   public int count2;

   private double signum(double offset) {
      double offset2 = Math.signum(offset);
      return offset2 == 0.0 ? 1.0 : offset2;
   }

   public Rotation resolveRotation(EntityLivingBase livingEntity, EntityPlayerSP player, float factor, float factor2, double offset, double offset2, Vec3 vec, Vec3 vec2) {
      long value3 = 0x4749f1d43e0bL;
      if (this.count2 != player.ticksExisted - 1) {
         this.simulationSnapshotMinecraftAccessor.value2 = 0.0;
         this.value2 = 0.0;
         this.simulationSnapshotController = null;
      }

      if (this.simulationSnapshotController == null
         || this.simulationSnapshotController.arrayListRecord.value <= this.simulationSnapshotMinecraftAccessor.value4
         || this.count2 != player.ticksExisted - 1) {
         double offset3 = (double)factor2;
         this.applyEntityPlayerSP(player, livingEntity, offset3);
         double offset4 = 0.0;
         CoreDoubleEntry[] coreDoubleEntryArray = this.resolveCoreDoubleEntryArray(player, livingEntity, offset4);
         if (coreDoubleEntryArray == null) {
            this.simulationSnapshotController = null;
            return new Rotation(factor, factor2);
         }

         double[] offsetArray = TrajectoryPredictor.resolveDoubleArray(coreDoubleEntryArray[0].value, coreDoubleEntryArray[1].value);
         double offset5 = 0.5;
         double doubleItem = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(offsetArray, offset5));
         double value4 = this.value;
         double doubleItem2 = HitBoxUtils.getDouble((double)factor, doubleItem);
         double offset6 = value4 - (double)factor2;
         this.simulationSnapshotMinecraftAccessor.value2 = this.value2;
         this.simulationSnapshotMinecraftAccessor.value4 = 0.0;
         double offset7 = 0.0;
         double offset8 = 0.0;
         if (this.simulationSnapshotController != null) {
            offset7 = Math.signum(this.simulationSnapshotController.value2);
            offset8 = Math.signum(this.simulationSnapshotController.value3);
         }

         this.simulationSnapshot = new SimulationSnapshot((double)factor, (double)factor2);
         double offset9 = 2.0;
         double offset10 = 0.5;
         double offset11 = 2.0;
         double offset12 = 0.5;
         this.simulationSnapshotController = this.resolveSimulationSnapshotController(doubleItem2, offset6, offset12, offset11, offset10, offset9);
         double offset13 = TrajectoryPredictor.computeValue2(offsetArray[0], offsetArray[1], MathHelper.getDouble4((double)factor));
         double offset14 = TrajectoryPredictor.computeValue2(this.valueArray[0], this.valueArray[1], (double)factor2);
         if (offset13 >= 0.0 && offset13 <= 1.0 && offset14 >= 0.0 && offset14 <= 1.0) {
            SimulationSnapshot simulationSnapshot2 = this.simulationSnapshot;
            SimulationSnapshot simulationSnapshot3 = this.simulationSnapshotController.arrayListRecord.simulationSnapshot.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot2);
            double doubleItem3 = TrajectoryPredictor.getDouble4(MathHelper.getDouble4(simulationSnapshot3.value), doubleItem);
            double offset15 = value4 - simulationSnapshot3.value2;
            double offset16 = doubleItem3 * doubleItem3 + offset15 * offset15;
            if (doubleItem2 * doubleItem2 + offset6 * offset6 < offset16) {
               this.simulationSnapshotController = null;
               return new Rotation(factor, factor2);
            }
         }

         if (offset7 != Math.signum(this.simulationSnapshotController.value2) || offset8 != Math.signum(this.simulationSnapshotController.value3)) {
            this.simulationSnapshotMinecraftAccessor.value2 = 0.0;
         }

         this.value2 = (coreDoubleEntryArray[0].value4 + coreDoubleEntryArray[1].value4) / 2.0;
         double value5 = this.value2;
         ArrayListRecord arrayListRecord = this.simulationSnapshotController.arrayListRecord;
         this.value2 = this.simulationSnapshotMinecraftAccessor.computeValue(arrayListRecord, value5);
         this.timestamp = 0L;
      }

      SimulationSnapshotController simulationSnapshotController2 = this.simulationSnapshotController;
      SimulationSnapshot simulationSnapshot4 = this.simulationSnapshotMinecraftAccessor.resolveSimulationSnapshot(simulationSnapshotController2);
      this.count2 = player.ticksExisted;
      return new Rotation((float)simulationSnapshot4.value, (float)simulationSnapshot4.value2);
   }

   static {
      try {
         applyText("1");
         System.out.println(arrayList.size());
      } catch (IOException ioException) {
      }
   }

   private void applyEntityPlayerSP(EntityPlayerSP player, EntityLivingBase livingEntity, double offset) {
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
      int count3 = Math.min(list.size(), list2.size());
      double[][] offsetMatrix = new double[count3][];
      ArrayList list3 = new ArrayList();

      for (int index = 0; index < count3; index++) {
         AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB((Vec3)list2.get(index), false);
         double[] coordOffsetArrayByVec = TrajectoryPredictor.getCoordOffsetArrayByVec3(((Vec3)list.get(index)).getVec312(0.0, (double)player.getHeight2(), 0.0), axisAlignedBB);
         offsetMatrix[index] = coordOffsetArrayByVec;
         list3.add(coordOffsetArrayByVec[0]);
         list3.add(coordOffsetArrayByVec[1]);
      }

      double offset5 = offset;
      double offset6 = offset;
      double offset7 = 0.0;
      int count4 = -1;
      int count5 = 0;

      for (int index2 = 0; index2 < list3.size(); index2++) {
         double offset8 = (Double)list3.get(index2);
         int count6 = 0;

         for (double[] offsetArray : offsetMatrix) {
            if (offset8 >= offsetArray[0] && offset8 <= offsetArray[1]) {
               count6++;
            }
         }

         if (count6 > count4 || count6 == count4 && Math.abs(offset - offset8) < offset7) {
            offset5 = offset8;
            offset7 = Math.abs(offset - offset8);
            count4 = count6;
            count5 = index2;
         }
      }

      double offset9 = 0.0;
      int count7 = -1;

      for (int index3 = 0; index3 < list3.size(); index3++) {
         if (index3 != count5) {
            double offset10 = (Double)list3.get(index3);
            if (offset10 != offset5) {
               int count8 = 0;

               for (double[] offsetArray2 : offsetMatrix) {
                  if (offset10 >= offsetArray2[0] && offset10 <= offsetArray2[1]) {
                     count8++;
                  }
               }

               if (count8 > count7 || count8 == count7 && Math.abs(offset5 - offset10) < offset9) {
                  offset6 = offset10;
                  offset9 = Math.abs(offset5 - offset10);
                  count7 = count8;
                  count5 = index3;
               }
            }
         }
      }

      this.valueArray = new double[]{Math.min(offset5, offset6), Math.max(offset5, offset6)};
      this.value = MathHelper.getDouble7(offset, this.valueArray[0], this.valueArray[1]);
   }

   private CoreDoubleEntry[] resolveCoreDoubleEntryArray(EntityPlayerSP player, EntityLivingBase livingEntity, double offset) {
      return null;
   }

   public AssetsRotationpatterMinecraftAccessor() {
      this.timestamp = 0L;
      this.value2 = 0.0;
      this.simulationSnapshotMinecraftAccessor = new SimulationSnapshotMinecraftAccessor();
   }

   public static void applyText(String text) throws IOException {
      Inflater inflater = new Inflater(true);
      DataInputStream dataInputStream = new DataInputStream(
         new InflaterInputStream(AssetsRotationpatterMinecraftAccessor.class.getResourceAsStream("/assets/rotationpatterns/" + text + ".aim"), inflater)
      );
      int count3 = dataInputStream.readInt();

      for (int index = 0; index < count3; index++) {
         ArrayListRecord arrayListRecord = new ArrayListRecord(dataInputStream);
         if (arrayListRecord.arrayList.size() >= 2) {
            arrayList.add(arrayListRecord);
         }
      }
   }

   private SimulationSnapshotController resolveSimulationSnapshotController(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      ArrayListRecord arrayListRecord = null;
      double offset = 0.0;
      double offset2 = 1.0;
      double offset3 = 1.0;
      double offset4 = Math.abs(posX);
      double offset5 = Math.abs(posX2);
      double offset6 = 0.0;

      for (ArrayListRecord arrayListRecord2 : (Iterable<ArrayListRecord>)(Iterable)(arrayList)) {
         double doubleItem = MathHelper.getDouble7(offset4 / arrayListRecord2.simulationSnapshot.value, posX3, posX4);
         double doubleItem2 = MathHelper.getDouble7(offset5 / arrayListRecord2.simulationSnapshot.value2, posY, posZ);
         if (!Double.isFinite(doubleItem)) {
            doubleItem = 1.0;
         }

         if (!Double.isFinite(doubleItem2)) {
            doubleItem2 = 1.0;
         }

         double offset7 = arrayListRecord2.simulationSnapshot.value * doubleItem;
         double offset8 = arrayListRecord2.simulationSnapshot.value2 * doubleItem2;
         double offset9 = (offset7 - offset4) * (offset7 - offset4) + (offset8 - offset5) * (offset8 - offset5);
         if (arrayListRecord == null || offset > offset9 || Math.abs(offset - offset9) < 0.15 && arrayListRecord2.value < offset6) {
            arrayListRecord = arrayListRecord2;
            offset = offset9;
            offset2 = doubleItem;
            offset3 = doubleItem2;
            offset6 = arrayListRecord2.value;
         }
      }

      double doubleItem3 = MathHelper.getDouble7(200.0 / (double)arrayListRecord.timestamp, 0.9, 1.1);
      return new SimulationSnapshotController(arrayListRecord, this.simulationSnapshot, this.signum(posX) * offset2, this.signum(posX2) * offset3, doubleItem3);
   }
}
