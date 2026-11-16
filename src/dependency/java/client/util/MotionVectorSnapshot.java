package client.util;

import java.util.ArrayList;
import net.minecraft.util.MathHelper;

public class MotionVectorSnapshot {
   public double value2;
   public boolean enabled;
   public double value;
   public double[] valueArray2;
   public double value4;
   public ArrayList arrayList = new ArrayList();
   public int count;
   public double value3;
   public double[] valueArray;

   public void applyDouble(double offset) {
      double doubleItem = MathHelper.getDouble7(this.value + offset, 0.0, 1.0);
      this.applyDoubleByDouble(doubleItem);
   }

   public double getValue4() {
      return this.value4;
   }

   public double getValue() {
      return this.value;
   }

   public void applyDoubleByDouble(double offset) {
      this.value4 = this.value;
      this.value = offset;
   }

   public SimulationSnapshot resolveSimulationSnapshot(double offset) {
      if (this.enabled) {
         return SimulationSnapshot.simulationSnapshot;
      } else {
         ArrayList list = new ArrayList(this.arrayList);

         for (ArrayList list2 = new ArrayList(); list.size() > 1; list2 = new ArrayList()) {
            SimulationSnapshot simulationSnapshot = null;

            for (SimulationSnapshot simulationSnapshot2 : (Iterable<SimulationSnapshot>)(Iterable)(list)) {
               if (simulationSnapshot != null) {
                  list2.add(simulationSnapshot.resolveSimulationSnapshot(simulationSnapshot2, offset));
               }

               simulationSnapshot = simulationSnapshot2;
            }

            list = list2;
         }

         return (SimulationSnapshot)list.get(0);
      }
   }

   public MotionVectorSnapshot() {
      this.value2 = 0.0;
      this.value3 = 1.0;
      this.enabled = false;
   }
}
