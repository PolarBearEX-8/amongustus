package client.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayListRecord {
   public double value;
   public ArrayList arrayList = new ArrayList();
   public SimulationSnapshot simulationSnapshot;
   public long timestamp;

   public ArrayListRecord(DataInputStream dataInputStream) throws IOException {
      this.applyDataInputStream(dataInputStream);
      if (!this.arrayList.isEmpty()) {
         this.timestamp = ((SimulationSnapshotRecord)this.arrayList.get(this.arrayList.size() - 1)).timestamp;
         SimulationSnapshot simulationSnapshot2 = null;

         for (SimulationSnapshotRecord simulationSnapshotRecord : (Iterable<SimulationSnapshotRecord>)this.arrayList) {
            if (simulationSnapshot2 != null) {
               this.value = this.value + simulationSnapshotRecord.simulationSnapshot.resolveSimulationSnapshot(simulationSnapshot2).sqrt();
            }

            simulationSnapshot2 = simulationSnapshotRecord.simulationSnapshot;
         }
      }
   }

   public void applyDataInputStream(DataInputStream dataInputStream) throws IOException {
      this.simulationSnapshot = new SimulationSnapshot(dataInputStream.readDouble() * 0.15, dataInputStream.readDouble() * 0.15);
      int count = dataInputStream.readInt();

      for (int index = 0; index < count; index++) {
         this.arrayList.add(new SimulationSnapshotRecord(new SimulationSnapshot(dataInputStream.readDouble() * 0.15, dataInputStream.readDouble() * 0.15), dataInputStream.readLong()));
      }
   }
}
