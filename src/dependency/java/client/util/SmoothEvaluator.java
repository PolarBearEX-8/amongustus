package client.util;

public class SmoothEvaluator {
   public SimulationSnapshot simulationSnapshot2;
   public boolean enabled = false;
   public SimulationSnapshot simulationSnapshot;

   public SmoothEvaluator(SimulationSnapshot simulationSnapshot3, SimulationSnapshot simulationSnapshot4, boolean flag) {
      this.simulationSnapshot2 = simulationSnapshot3;
      this.simulationSnapshot = simulationSnapshot4;
      this.enabled = flag;
   }

   public SmoothEvaluator(SimulationSnapshot simulationSnapshot3, SimulationSnapshot simulationSnapshot4) {
      this.simulationSnapshot2 = simulationSnapshot3;
      this.simulationSnapshot = simulationSnapshot4;
   }
}
