package client.util;

public class SimulationSnapshotRecord {
   public SimulationSnapshot simulationSnapshot;
   public long timestamp;

   public SimulationSnapshotRecord(SimulationSnapshot simulationSnapshot2, long value) {
      this.simulationSnapshot = simulationSnapshot2;
      this.timestamp = value;
   }
}
