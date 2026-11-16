package client.util;

public class SimulationSnapshot {
   public static final SimulationSnapshot simulationSnapshot = new SimulationSnapshot(0.0, 0.0);
   public double value;
   public double value2;

   public SimulationSnapshot getSimulationSnapshotAsSimulationSnapsh() {
      double offset = 1.0 / this.value;
      double offset2;
      if (!Double.isFinite(offset)) {
         offset2 = 0.0;
         offset = 1.0;
      } else {
         offset2 = -this.value * offset / this.value2;
         if (!Double.isFinite(offset2)) {
            offset2 = 1.0;
            offset = 0.0;
         }
      }

      return new SimulationSnapshot(offset, offset2);
   }

   public SimulationSnapshot resolveSimulationSnapshot(SimulationSnapshot simulationSnapshot2, double offset) {
      return new SimulationSnapshot(PlayerUtil.getDouble(this.value, simulationSnapshot2.value, offset), PlayerUtil.getDouble(this.value2, simulationSnapshot2.value2, offset));
   }

   public SimulationSnapshot resolveSimulationSnapshot(double offset) {
      double offset2 = Math.cos(offset);
      double offset3 = Math.sin(offset);
      return new SimulationSnapshot(this.value * offset2 - this.value2 * offset3, this.value * offset3 + this.value2 * offset2);
   }

   public SimulationSnapshot getSimulationSnapshot() {
      double offset = 1.0 / this.sqrt();
      return this.resolveSimulationSnapshotByDouble(offset);
   }

   public SimulationSnapshot resolveSimulationSnapshot(SimulationSnapshot simulationSnapshot2) {
      return new SimulationSnapshot(this.value - simulationSnapshot2.value, this.value2 - simulationSnapshot2.value2);
   }

   public SimulationSnapshot getSimulationSnapshot2() {
      return new SimulationSnapshot(this.value2, this.value);
   }

   public SimulationSnapshot resolveSimulationSnapshotByDouble(double offset) {
      return new SimulationSnapshot(this.value * offset, this.value2 * offset);
   }

   public double sqrt() {
      return Math.sqrt(this.value * this.value + this.value2 * this.value2);
   }

   public double computeValueBySimulationSnapsh(SimulationSnapshot simulationSnapshot2) {
      double offset = Math.acos(this.computeValue(simulationSnapshot2) / (this.sqrt() * simulationSnapshot2.sqrt())) * 180.0 / Math.PI;
      return offset > 180.0 ? 180.0 - offset : offset;
   }

   public SimulationSnapshot resolveSimulationSnapshot2(double offset) {
      double offset2 = this.sqrt();
      return offset2 > offset ? new SimulationSnapshot(this.value / offset2 * offset, this.value2 / offset2 * offset) : new SimulationSnapshot(this.value, this.value2);
   }

   public double computeValue(SimulationSnapshot simulationSnapshot2) {
      return this.value * simulationSnapshot2.value + this.value2 * simulationSnapshot2.value2;
   }

   public SimulationSnapshot resolveSimulationSnapshotBySimulationSnapsh(SimulationSnapshot simulationSnapshot2) {
      return new SimulationSnapshot(this.value + simulationSnapshot2.value, this.value2 + simulationSnapshot2.value2);
   }

   public SimulationSnapshot(double offset, double offset2) {
      this.value = offset;
      this.value2 = offset2;
   }

   public SimulationSnapshot(SimulationSnapshot simulationSnapshot2) {
      this(simulationSnapshot2.value, simulationSnapshot2.value2);
   }
}
