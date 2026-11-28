package net.augustus.modules.modes.killaura.unoise3;

public class MouseTrajectoryConfig {
   public double deceleration;
   public double targetDistance;
   public double reverseDeceleration;
   public double targetVelocity;
   public double stepSize = 1.0;
   public double currentVelocity;
   public double maxAcceleration;

   public MouseTrajectoryConfig(double d, double d2, double d3, double d4, double d5, double d6) {
      this.targetDistance = d;
      this.currentVelocity = d2;
      this.targetVelocity = d3;
      this.deceleration = d4;
      this.reverseDeceleration = d6;
      this.maxAcceleration = d5;
   }

   public MouseTrajectoryConfig() {
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return this.copy();
   }

   public MouseTrajectoryConfig copy() {
      MouseTrajectoryConfig mouseTrajectoryConfig = new MouseTrajectoryConfig();
      mouseTrajectoryConfig.targetVelocity = this.targetVelocity;
      mouseTrajectoryConfig.targetDistance = this.targetDistance;
      mouseTrajectoryConfig.maxAcceleration = this.maxAcceleration;
      mouseTrajectoryConfig.reverseDeceleration = this.reverseDeceleration;
      mouseTrajectoryConfig.deceleration = this.deceleration;
      mouseTrajectoryConfig.currentVelocity = this.currentVelocity;
      mouseTrajectoryConfig.stepSize = this.stepSize;
      return mouseTrajectoryConfig;
   }
}
