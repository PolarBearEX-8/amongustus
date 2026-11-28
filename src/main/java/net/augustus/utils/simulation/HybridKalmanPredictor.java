package net.augustus.utils.simulation;

import net.augustus.utils.math.CtrvKalmanFilter;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.prediction.KalmanFilter2D;
import net.minecraft.util.Vec3;

public class HybridKalmanPredictor {
   private KalmanFilter2D linearKalmanFilter;
   private final double sigmoidSlope;
   private CtrvKalmanFilter ctrvKalmanFilter;
   private final double linearDeltaTime;
   private final double ctrvDeltaTime;
   private final double yawRateThreshold;

   public HybridKalmanPredictor() {
       super();
      this.linearDeltaTime = 0.05;
      this.ctrvDeltaTime = 0.05;
      this.sigmoidSlope = 8.2;
      this.yawRateThreshold = 2.4;
      this.linearKalmanFilter = new KalmanFilter2D();
      this.ctrvKalmanFilter = new CtrvKalmanFilter();
   }

   public double[] predictNextPosition() {
      double d = 0.05;
      double[] doubles = this.linearKalmanFilter.predict(d);
      double d2 = 0.05;
      double[] doubles2 = this.ctrvKalmanFilter.predict(d2);
      double d3 = Math.abs(this.ctrvKalmanFilter.getYawRate());
      double d4 = 1.0 / (1.0 + Math.exp(-8.2 * (d3 - 2.4)));
      double d5 = (1.0 - d4) * doubles[0] + d4 * doubles2[0];
      double d6 = (1.0 - d4) * doubles[1] + d4 * doubles2[1];
      return new double[]{d5, d6};
   }

   public void initVertical(Vec3 vec, Vec3 vec2, double d) {
      double d2 = 0.0;
      double yCoord = vec2.yCoord;
      double d3 = 0.0;
      this.initState(vec.yCoord, d3, yCoord, d2, d);
   }

   public void initHorizontal(Vec3 vec, Vec3 vec2, double d) {
      double zCoord = vec2.zCoord;
      double xCoord = vec2.xCoord;
      double zCoord2 = vec.zCoord;
      this.initState(vec.xCoord, zCoord2, xCoord, zCoord, d);
   }

   public void init2D(Vec2d vec2d, Vec2d vec2d2, double d) {
      double d2 = vec2d2.y;
      double d3 = vec2d2.x;
      double d4 = vec2d.y;
      this.initState(vec2d.x, d4, d3, d2, d);
   }

   public void initState(double d, double d2, double d3, double d4, double d5) {
      double d6 = (d3 - d) / d5;
      double d7 = (d4 - d2) / d5;
      double d8 = 0.0;
      double d9 = 0.0;
      double[] doubles = new double[]{d, d6, d8, d2, d7, d9};
      this.linearKalmanFilter.setState(doubles);
      double d10 = Math.sqrt(d6 * d6 + d7 * d7);
      double d11 = Math.atan2(d7, d6);
      double d12 = 0.0;
      double[] doubles2 = new double[]{d, d2, d10, d11, d12};
      this.ctrvKalmanFilter.setState(doubles2);
   }

   public void updateMeasurement(double d, double d2) {
      double[] doubles = new double[]{d, d2};
      this.linearKalmanFilter.update(doubles);
      this.ctrvKalmanFilter.update(doubles);
   }
}
