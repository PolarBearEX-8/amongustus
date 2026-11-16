package client.util;

import client.Client;
import java.util.Random;
import net.minecraft.util.MathHelper;

public class RandomizationGenerator implements MinecraftAccessor {
   public Random random;
   public boolean state;
   public double coordOffset = 8.0;
   public static final int count = 3;
   public double value2 = 1.0;
   public double value = 1.0;
   public boolean enabled;
   public double value3;
   public float factor;

   public SimulationBoundingBox resolveSimulationBoundingBox(SimulationBoundingBox simulationBoundingBox, MotionVectorSnapshot motionVectorSnapshot, double offset) {
      byte byteValue = 0;
      short shortValue = 16769;
      int count2 = 249757483;
      this.factor += 0.05F;
      SimulationSnapshot simulationSnapshot = simulationBoundingBox.getSimulationSnapshot();
      double doubleItem = MathHelper.getDouble7(motionVectorSnapshot.getValue(), 0.0, 1.0);
      SimulationSnapshot simulationSnapshot2 = motionVectorSnapshot.resolveSimulationSnapshot(doubleItem);
      double offset2 = 1.0;
      SimulationSnapshot simulationSnapshot3 = motionVectorSnapshot.resolveSimulationSnapshot(offset2);
      double offset3 = 1000.0;
      double value4 = motionVectorSnapshot.getValue();

      for (double value5 = motionVectorSnapshot.getValue(); value5 <= 1.0; value5 += 0.01) {
         SimulationSnapshot simulationSnapshot4 = motionVectorSnapshot.resolveSimulationSnapshot(value5);
         double doubleItem2 = HitBoxUtils.getDouble(simulationBoundingBox.getCoordinate2(), simulationSnapshot4.value);
         double offset4 = simulationBoundingBox.getCoordinate() - simulationSnapshot4.value2;
         double offset5 = doubleItem2 * doubleItem2 + offset4 * offset4;
         if (offset3 > offset5 || simulationSnapshot2 == null) {
            simulationSnapshot2 = simulationSnapshot4;
            offset3 = offset5;
            value4 = value5;
         }
      }

      if (this.state) {
         double offset6 = value4 + 1.0 / (double)(motionVectorSnapshot.count + 1);
         motionVectorSnapshot.applyDoubleByDouble(offset6);
      }

      double offset7 = 1.0;
      double coordinate = simulationBoundingBox.getCoordinate2();
      double coordinate2 = simulationBoundingBox.getCoordinate();
      double doubleItem3 = MathHelper.getDouble4(simulationSnapshot2.value);
      double doubleItem4 = MathHelper.getDouble7(simulationSnapshot2.value2, -90.0, 90.0);
      double doubleItem5 = MathHelper.getDouble4(simulationSnapshot3.value);
      double doubleItem6 = MathHelper.getDouble7(simulationSnapshot3.value2, -90.0, 90.0);
      double offset8 = Math.signum(HitBoxUtils.getDouble(simulationBoundingBox.getCoordinate2(), doubleItem3));
      double offset9 = Math.signum(doubleItem4 - coordinate2);
      boolean flag = false;
      boolean flag2 = false;

      for (int index = 0; index < 3; index++) {
         double doubleItem7 = HitBoxUtils.getDouble(coordinate, doubleItem3);
         double offset10 = doubleItem4 - coordinate2;
         double doubleItem8 = HitBoxUtils.getDouble(coordinate, doubleItem5);
         double offset11 = doubleItem6 - coordinate2;
         if (motionVectorSnapshot.enabled) {
            doubleItem7 = 0.0;
            offset10 = 0.0;
            doubleItem8 = 0.0;
            offset11 = 0.0;
         }

         SimulationSnapshot simulationSnapshot5 = new SimulationSnapshot(doubleItem7 / 0.15, offset10 / 0.15);
         SimulationSnapshot simulationSnapshot6 = new SimulationSnapshot(doubleItem8 / 0.15, offset11 / 0.15);
         if (Math.signum(simulationSnapshot6.value) != Math.signum(simulationSnapshot5.value)) {
            simulationSnapshot6.value = simulationSnapshot5.value;
         }

         if (Math.signum(simulationSnapshot6.value2) != Math.signum(simulationSnapshot5.value2)) {
            simulationSnapshot6.value2 = simulationSnapshot5.value2;
         }

         double offset12 = Math.signum(simulationSnapshot5.value) != Math.signum(simulationSnapshot.value) ? this.coordOffset * this.value : this.coordOffset;
         double offset13 = Math.signum(simulationSnapshot5.value2) != Math.signum(simulationSnapshot.value2) ? this.coordOffset * this.value : this.coordOffset;
         double offset14 = offset12 * (this.random.nextDouble() * 0.2 + 0.8);
         double offset15 = offset13 * (this.random.nextDouble() * 0.2 + 0.8);
         double offset16 = this.coordOffset * this.value2;
         double offset17 = Math.abs(offset / 0.15 / 3.0 * offset7);
         if (Math.signum(offset) != Math.signum(simulationSnapshot5.value)) {
            offset17 = 0.0;
         }

         double offset18 = Math.min(offset17 + 1.4142135623730951 * Math.sqrt(offset16 * Math.abs(simulationSnapshot6.value)), Math.abs(simulationSnapshot6.value));
         double offset19 = Math.min(1.4142135623730951 * offset16 * Math.sqrt(Math.abs(simulationSnapshot6.value2) / (2.0 * offset16 - 1.0)), Math.abs(simulationSnapshot6.value2));
         double offset20 = offset18 * Math.signum(simulationSnapshot5.value) - simulationSnapshot.value;
         double offset21 = offset19 * Math.signum(simulationSnapshot5.value2) - simulationSnapshot.value2;
         SimulationSnapshot simulationSnapshot7 = new SimulationSnapshot(
            MathHelper.getDouble7(
                  Math.abs(offset20),
                  0.0,
                  (Math.signum(offset20) != Math.signum(simulationSnapshot5.value) || simulationSnapshot5.value == 0.0) && Math.signum(simulationSnapshot.value) == Math.signum(simulationSnapshot5.value)
                     ? Double.MAX_VALUE
                     : offset14
               )
               * Math.signum(offset20),
            MathHelper.getDouble7(
                  Math.abs(offset21),
                  0.0,
                  (Math.signum(offset21) != Math.signum(simulationSnapshot5.value2) || simulationSnapshot5.value2 == 0.0) && Math.signum(simulationSnapshot.value2) == Math.signum(simulationSnapshot5.value2)
                     ? Double.MAX_VALUE
                     : offset15
               )
               * Math.signum(offset21)
         );
         if (simulationSnapshot7.value == Math.signum(simulationSnapshot.value) && simulationSnapshot7.value2 == Math.signum(simulationSnapshot.value2)) {
            simulationSnapshot7.value = simulationSnapshot7.value / motionVectorSnapshot.value3;
            simulationSnapshot7.value2 = simulationSnapshot7.value2 / motionVectorSnapshot.value3;
         }

         if (Double.isNaN(simulationSnapshot7.value)) {
            simulationSnapshot7.value = 0.0;
            simulationSnapshot7.value2 = 0.0;
            simulationSnapshot.value = 0.0;
            simulationSnapshot.value2 = 0.0;
         }

         if (Double.isNaN(simulationSnapshot7.value2)) {
            simulationSnapshot7.value2 = 0.0;
         }

         SimulationSnapshot simulationSnapshot8 = new SimulationSnapshot(simulationSnapshot.value, simulationSnapshot.value2);
         SimulationSnapshot simulationSnapshot9 = new SimulationSnapshot(simulationSnapshot8.value + (flag ? 0.0 : simulationSnapshot7.value), simulationSnapshot8.value2 + (flag2 ? 0.0 : simulationSnapshot7.value2));
         double offset22 = 1.0 - offset7;
         SimulationSnapshot simulationSnapshot10 = simulationSnapshot.resolveSimulationSnapshotByDouble(offset22);
         SimulationSnapshot simulationSnapshot11 = simulationSnapshot9.resolveSimulationSnapshotByDouble(offset7);
         simulationSnapshot = simulationSnapshot10.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot11);
         if (!flag) {
            coordinate = MathHelper.getDouble4(coordinate + simulationSnapshot.value * 0.15 * offset7);
         }

         if (!flag2) {
            coordinate2 = MathHelper.getDouble7(coordinate2 + simulationSnapshot.value2 * 0.15 * offset7, -90.0, 90.0);
         }

         double doubleItem9 = HitBoxUtils.getDouble(coordinate, doubleItem3);
         double offset23 = doubleItem4 - coordinate2;
         if (!flag && Math.signum(doubleItem9) != offset8 && Math.signum(simulationSnapshot.value) == offset8 && !motionVectorSnapshot.enabled) {
            coordinate = doubleItem3;
            simulationSnapshot.value = offset17 * Math.signum(offset);
            flag = true;
         }

         if (!flag2 && Math.signum(offset23) != offset9 && Math.signum(simulationSnapshot.value2) == offset9 && !motionVectorSnapshot.enabled) {
            coordinate2 = doubleItem4;
            simulationSnapshot.value2 = 0.0;
            flag2 = true;
         }

         if (this.enabled) {
            Client.getClient().getClass();
         }
      }

      if (this.enabled) {
         Client.getClient().getClass();
      }

      return new SimulationBoundingBox(simulationSnapshot, coordinate, coordinate2);
   }

   public RandomizationGenerator() {
      this.state = false;
      this.factor = 0.0F;
      this.random = new Random();
      this.enabled = false;
   }
}
