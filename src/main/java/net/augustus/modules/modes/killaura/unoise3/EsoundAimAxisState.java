package net.augustus.modules.modes.killaura.unoise3;

public class EsoundAimAxisState {
   public int ticksSinceRetarget;
   private int retargetIntervalTicks;
   public double targetAngle;
   public int interpolationStep;
   public double[] angleBounds;
   public double startAngle;
   public double normalizedAimOffset;
   public boolean locked = false;
   private int interpolationDuration;

   public static int accessInterpolationDuration(EsoundAimAxisState esoundAimAxisState) {
      return esoundAimAxisState.interpolationDuration;
   }

   public static int setInterpolationDuration(EsoundAimAxisState esoundAimAxisState, int n) {
      return esoundAimAxisState.interpolationDuration = n;
   }

   public EsoundAimAxisState(EsoundAimAxisStateSynthetic synthetic) {
      this();
   }

   public void onTick() {
   }

   public static int accessRetargetIntervalTicks(EsoundAimAxisState esoundAimAxisState) {
      return esoundAimAxisState.retargetIntervalTicks;
   }

   public static int setRetargetIntervalTicks(EsoundAimAxisState esoundAimAxisState, int n) {
      return esoundAimAxisState.retargetIntervalTicks = n;
   }

   private EsoundAimAxisState() {
      this.normalizedAimOffset = 0.5;
      this.angleBounds = new double[2];
      this.startAngle = 0.0;
      this.targetAngle = 0.0;
      this.interpolationStep = 0;
      this.ticksSinceRetarget = 0;
      this.retargetIntervalTicks = 1003;
      this.interpolationDuration = 6;
   }
}
