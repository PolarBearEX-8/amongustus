package net.augustus.modules.modes.killaura.unoise3;

public class MouseSimulationStep {
   public double acceleration;
   public double position;
   public double velocity;

   public MouseSimulationStep(double d, double d2, double d3) {
      this.velocity = d;
      this.position = d2;
      this.acceleration = d3;
   }
}
