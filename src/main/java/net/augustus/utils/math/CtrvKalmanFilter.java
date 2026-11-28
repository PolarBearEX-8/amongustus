package net.augustus.utils.math;

import net.augustus.utils.prediction.DoubleMatrixMath;
import java.util.Arrays;

public class CtrvKalmanFilter {
   private final double[][] measurementNoise;
   private final int stateDim;
   private final int measurementDim;
   private double[][] processNoise;
   private double[][] covariance;
   private double[] state;

   public void setState(double[] doubles) {
      this.state = Arrays.copyOf(doubles, 5);
   }

   public double[] predict(double d) {
      double d2 = this.state[0];
      double d3 = this.state[1];
      double d4 = this.state[2];
      double d5 = this.state[3];
      double d6 = this.state[4];
      double d7 = 0.001;
      double d8;
      double d9;
      if (Math.abs(d6) > d7) {
         d8 = d2 + d4 / d6 * (Math.sin(d5 + d6 * d) - Math.sin(d5));
         d9 = d3 - d4 / d6 * (Math.cos(d5 + d6 * d) - Math.cos(d5));
      } else {
         d8 = d2 + d4 * d * Math.cos(d5);
         d9 = d3 + d4 * d * Math.sin(d5);
      }

      double d10 = d5 + d6 * d;
      double[] doubles = new double[]{d8, d9, d4, d10, d6};
      double[][] doublesArr = new double[5][5];
      if (Math.abs(d6) > d7) {
         doublesArr[0][0] = 1.0;
         doublesArr[0][2] = 1.0 / d6 * (Math.sin(d5 + d6 * d) - Math.sin(d5));
         doublesArr[0][3] = d4 / d6 * (Math.cos(d5 + d6 * d) - Math.cos(d5));
         doublesArr[0][4] = -d4 / (d6 * d6) * (Math.sin(d5 + d6 * d) - Math.sin(d5)) + d4 / d6 * d * Math.cos(d5 + d6 * d);
         doublesArr[1][1] = 1.0;
         doublesArr[1][2] = -(1.0 / d6) * (Math.cos(d5 + d6 * d) - Math.cos(d5));
         doublesArr[1][3] = d4 / d6 * (Math.sin(d5 + d6 * d) - Math.sin(d5));
         doublesArr[1][4] = d4 / (d6 * d6) * (Math.cos(d5 + d6 * d) - Math.cos(d5)) + d4 / d6 * d * Math.sin(d5 + d6 * d);
      } else {
         doublesArr[0][0] = 1.0;
         doublesArr[0][2] = d * Math.cos(d5);
         doublesArr[0][3] = -d4 * d * Math.sin(d5);
         doublesArr[1][1] = 1.0;
         doublesArr[1][2] = d * Math.sin(d5);
         doublesArr[1][3] = d4 * d * Math.cos(d5);
      }

      doublesArr[2][2] = 1.0;
      doublesArr[3][3] = 1.0;
      doublesArr[3][4] = d;
      doublesArr[4][4] = 1.0;
      this.state = doubles;
      double[][] doublesArr2 = DoubleMatrixMath.transpose(doublesArr);
      this.covariance = DoubleMatrixMath.add(DoubleMatrixMath.multiply(doublesArr, DoubleMatrixMath.multiply(this.covariance, doublesArr2)), this.processNoise);
      return new double[]{this.state[0], this.state[1]};
   }

   public double getYawRate() {
      return this.state[4];
   }

   public CtrvKalmanFilter() {
       super();
      this.stateDim = 5;
      this.measurementDim = 2;
      this.state = new double[5];
      this.covariance = DoubleMatrixMath.diagonal(5, 1.0);
      this.processNoise = new double[5][5];
      this.processNoise[0][0] = 0.0;
      this.processNoise[1][1] = 0.0;
      this.processNoise[2][2] = 0.8;
      this.processNoise[3][3] = 0.3;
      this.processNoise[4][4] = 0.2;
      this.measurementNoise = new double[][]{{1.0, 0.0}, {0.0, 1.0}};
   }

   public void update(double[] doubles) {
      double[][] doublesArr = new double[][]{{1.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 1.0, 0.0, 0.0, 0.0}};
      double[] doubles3 = DoubleMatrixMath.subtractVectors(doubles, new double[]{this.state[0], this.state[1]});
      double[][] doublesArr2 = DoubleMatrixMath.transpose(doublesArr);
      double[][] doublesArr4 = DoubleMatrixMath.multiply(doublesArr, DoubleMatrixMath.multiply(this.covariance, doublesArr2));
      double[][] doublesArr6 = DoubleMatrixMath.invert2x2(DoubleMatrixMath.add(doublesArr4, this.measurementNoise));
      doublesArr4 = this.covariance;
      double[][] doublesArr8 = DoubleMatrixMath.multiply(doublesArr4, DoubleMatrixMath.multiply(doublesArr2, doublesArr6));
      this.state = DoubleMatrixMath.addVectors(this.state, DoubleMatrixMath.multiplyMatrixVector(doublesArr8, doubles3));
      this.covariance = DoubleMatrixMath.multiply(DoubleMatrixMath.subtract(DoubleMatrixMath.diagonal(5, 1.0), DoubleMatrixMath.multiply(doublesArr8, doublesArr)), this.covariance);
   }

   public double[] getState() {
      return this.state;
   }
}
