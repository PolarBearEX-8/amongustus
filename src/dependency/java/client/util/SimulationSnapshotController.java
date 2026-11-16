package client.util;

public class SimulationSnapshotController {
   public double value3;
   public double value = 0.0;
   public SimulationSnapshot simulationSnapshot;
   public double value4;
   public double value2;
   public ArrayListRecord arrayListRecord;

   public double[] resolveDoubleArray(double offset) {
      return this.resolveDoubleArray(offset, false);
   }

   public SimulationSnapshotController(ArrayListRecord arrayListRecord2, SimulationSnapshot simulationSnapshot2, double posX, double posY, double posZ) {
      this.arrayListRecord = arrayListRecord2;
      this.simulationSnapshot = simulationSnapshot2;
      this.value2 = posX;
      this.value3 = posY;
      this.value4 = posZ;
   }

   public boolean checkDouble(double offset) {
      double[] offsetArray = this.resolveDoubleArray(offset, true);
      return Math.sqrt(
            (this.arrayListRecord.simulationSnapshot.value - offsetArray[0]) * (this.arrayListRecord.simulationSnapshot.value - offsetArray[0])
               + (this.arrayListRecord.simulationSnapshot.value2 - offsetArray[1]) * (this.arrayListRecord.simulationSnapshot.value2 - offsetArray[1])
         )
         < 12.0;
   }

   public double getValue() {
      return (double)this.arrayListRecord.timestamp * this.value4;
   }

   public double[] resolveDoubleArray(double offset, boolean flag) {
      double offset2 = offset / this.value4;
      double offset3 = -1.0;
      SimulationSnapshotRecord simulationSnapshotRecord = null;

      for (SimulationSnapshotRecord simulationSnapshotRecord2 : (Iterable<SimulationSnapshotRecord>)(Iterable)(this.arrayListRecord.arrayList)) {
         double offset4 = Math.abs((double)simulationSnapshotRecord2.timestamp - offset2);
         if (offset3 == -1.0 || offset4 < offset3) {
            offset3 = offset4;
            simulationSnapshotRecord = simulationSnapshotRecord2;
         }
      }

      return simulationSnapshotRecord == null
         ? new double[]{0.0, 0.0}
         : new double[]{simulationSnapshotRecord.simulationSnapshot.value * (flag ? 1.0 : this.value2), simulationSnapshotRecord.simulationSnapshot.value2 * (flag ? 1.0 : this.value3)};
   }
}
