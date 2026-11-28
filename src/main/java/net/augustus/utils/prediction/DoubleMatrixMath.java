package net.augustus.utils.prediction;


public class DoubleMatrixMath {

   public static double[] multiplyMatrixVector(double[][] doublesArr, double[] doubles) {
      int length = doublesArr.length;
      int length2 = doublesArr[0].length;
      double[] doubles2 = new double[length];

      for (int i = 0; i < length; i++) {
         doubles2[i] = 0.0;

         for (int j = 0; j < length2; j++) {
            doubles2[i] += doublesArr[i][j] * doubles[j];
         }
      }

      return doubles2;
   }

   public static double[][] multiply(double[][] doublesArr, double[][] doublesArr2) {
      int length = doublesArr.length;
      int length2 = doublesArr[0].length;
      int length3 = doublesArr2[0].length;
      double[][] doublesArr3 = new double[length][length3];

      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length3; j++) {
            doublesArr3[i][j] = 0.0;

            for (int k = 0; k < length2; k++) {
               doublesArr3[i][j] = doublesArr3[i][j] + doublesArr[i][k] * doublesArr2[k][j];
            }
         }
      }

      return doublesArr3;
   }

   public static double[][] diagonal(int n, double d) {
      double[][] doublesArr = new double[n][n];

      for (int i = 0; i < n; i++) {
         doublesArr[i][i] = d;
      }

      return doublesArr;
   }

   public static double[][] transpose(double[][] doublesArr) {
      int length = doublesArr.length;
      int length2 = doublesArr[0].length;
      double[][] doublesArr2 = new double[length2][length];

      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length2; j++) {
            doublesArr2[j][i] = doublesArr[i][j];
         }
      }

      return doublesArr2;
   }

   public static double[] multiplyMatrixVector(double[][] doublesArr, double[] doubles, int n) {
      return multiplyMatrixVector(doublesArr, doubles);
   }

   public static double[][] add(double[][] doublesArr, double[][] doublesArr2) {
      int length = doublesArr.length;
      int length2 = doublesArr[0].length;
      double[][] doublesArr3 = new double[length][length2];

      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length2; j++) {
            doublesArr3[i][j] = doublesArr[i][j] + doublesArr2[i][j];
         }
      }

      return doublesArr3;
   }

   public static double[] addVectors(double[] doubles, double[] doubles2) {
      int length = doubles.length;
      double[] doubles3 = new double[length];

      for (int i = 0; i < length; i++) {
         doubles3[i] = doubles[i] + doubles2[i];
      }

      return doubles3;
   }

   public static double[][] subtract(double[][] doublesArr, double[][] doublesArr2) {
      int length = doublesArr.length;
      int length2 = doublesArr[0].length;
      double[][] doublesArr3 = new double[length][length2];

      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length2; j++) {
            doublesArr3[i][j] = doublesArr[i][j] - doublesArr2[i][j];
         }
      }

      return doublesArr3;
   }

   public static double[][] invert2x2(double[][] doublesArr) {
      if (doublesArr.length == 2 && doublesArr[0].length == 2) {
         double d = doublesArr[0][0];
         double d2 = doublesArr[0][1];
         double d3 = doublesArr[1][0];
         double d4 = doublesArr[1][1];
         double d5 = d * d4 - d2 * d3;
         if (Math.abs(d5) < 1.0E-9) {
            throw new ArithmeticException("Matrix is singular and cannot be inverted.");
         } else {
            double d6 = 1.0 / d5;
            return new double[][]{{d4 * d6, -d2 * d6}, {-d3 * d6, d * d6}};
         }
      } else {
         throw new IllegalArgumentException("Only 2x2 matrices are supported by this method.");
      }
   }

   public static double[] subtractVectors(double[] doubles, double[] doubles2) {
      int length = doubles.length;
      double[] doubles3 = new double[length];

      for (int i = 0; i < length; i++) {
         doubles3[i] = doubles[i] - doubles2[i];
      }

      return doubles3;
   }



}
