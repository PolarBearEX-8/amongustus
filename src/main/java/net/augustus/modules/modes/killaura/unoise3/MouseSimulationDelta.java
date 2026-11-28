package net.augustus.modules.modes.killaura.unoise3;


import net.augustus.utils.math.Vec2d;
public class MouseSimulationDelta {
   public Vec2d baseDelta;
   public Vec2d noisyDelta;
   public boolean reachedGoal = false;

   public MouseSimulationDelta(Vec2d vec2d, Vec2d vec2d2) {
      this.noisyDelta = vec2d;
      this.baseDelta = vec2d2;
   }

   public MouseSimulationDelta(Vec2d vec2d, Vec2d vec2d2, boolean flag) {
      this.noisyDelta = vec2d;
      this.baseDelta = vec2d2;
      this.reachedGoal = flag;
   }
}
