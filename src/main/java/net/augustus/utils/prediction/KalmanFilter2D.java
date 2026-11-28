package net.augustus.utils.prediction;

import java.util.Arrays;

public class KalmanFilter2D {
   private double[][] processNoise;
   private double[][] covariance;
   private final int measurementDim;
   private final int stateDim;
   private final double[][] measurementNoise;
   private double[] state;

   public void update(double[] doubles) {
      double[][] doublesArr = new double[][]{{1.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 1.0, 0.0, 0.0}};
      double[] doubles3 = DoubleMatrixMath.subtractVectors(doubles, new double[]{this.state[0], this.state[3]});
      double[][] doublesArr2 = DoubleMatrixMath.transpose(doublesArr);
      double[][] doublesArr4 = DoubleMatrixMath.multiply(doublesArr, DoubleMatrixMath.multiply(this.covariance, doublesArr2));
      double[][] doublesArr6 = DoubleMatrixMath.invert2x2(DoubleMatrixMath.add(doublesArr4, this.measurementNoise));
      doublesArr4 = this.covariance;
      double[][] doublesArr8 = DoubleMatrixMath.multiply(doublesArr4, DoubleMatrixMath.multiply(doublesArr2, doublesArr6));
      this.state = DoubleMatrixMath.addVectors(this.state, DoubleMatrixMath.multiplyMatrixVector(doublesArr8, doubles3));
      this.covariance = DoubleMatrixMath.multiply(DoubleMatrixMath.subtract(DoubleMatrixMath.diagonal(6, 1.0), DoubleMatrixMath.multiply(doublesArr8, doublesArr)), this.covariance);
   }

   public void setState(double[] doubles) {
      this.state = Arrays.copyOf(doubles, 6);
   }

   public KalmanFilter2D() {
       super();
      this.stateDim = 6;
      this.measurementDim = 2;
      this.state = new double[6];
      this.covariance = DoubleMatrixMath.diagonal(6, 1.0);
      this.processNoise = new double[6][6];
      this.processNoise[0][0] = 0.2;
      this.processNoise[1][1] = 0.05;
      this.processNoise[2][2] = 0.01;
      this.processNoise[3][3] = 0.2;
      this.processNoise[4][4] = 0.05;
      this.processNoise[5][5] = 0.01;
      this.measurementNoise = new double[][]{{1.0, 0.0}, {0.0, 1.0}};
   }

   public double[] predict(double d) {
      double d2 = d * d;
      double[][] doublesArr = new double[][]{{1.0, d, 0.5 * d2}, {0.0, 1.0, d}, {0.0, 0.0, 1.0}};
      double[][] doublesArr2 = new double[6][6];

      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            doublesArr2[i][j] = doublesArr[i][j];
            doublesArr2[i + 3][j + 3] = doublesArr[i][j];
         }
      }

      this.state = DoubleMatrixMath.multiplyMatrixVector(doublesArr2, this.state);
      double[][] doublesArr3 = DoubleMatrixMath.transpose(doublesArr2);
      this.covariance = DoubleMatrixMath.add(DoubleMatrixMath.multiply(doublesArr2, DoubleMatrixMath.multiply(this.covariance, doublesArr3)), this.processNoise);
      return new double[]{this.state[0], this.state[3]};
   }

   public double[] getState() {
      return this.state;
   }
}
