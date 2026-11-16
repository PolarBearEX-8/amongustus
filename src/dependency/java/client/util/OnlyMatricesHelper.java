package client.util;

public class OnlyMatricesHelper {
   public static double[] resolveDoubleArray(double[][] offsetMatrix, double[] offsetArray, int count) {
      return resolveDoubleArray(offsetMatrix, offsetArray);
   }

   public static double[][] resolveDoubleArray(int count, double offset) {
      double[][] offsetMatrix = new double[count][count];

      for (int index = 0; index < count; index++) {
         offsetMatrix[index][index] = offset;
      }

      return offsetMatrix;
   }

   public static double[] resolveDoubleArray(double[][] offsetMatrix, double[] offsetArray) {
      int length = offsetMatrix.length;
      int length2 = offsetMatrix[0].length;
      double[] offsetArray2 = new double[length];

      for (int index = 0; index < length; index++) {
         offsetArray2[index] = 0.0;

         for (int index2 = 0; index2 < length2; index2++) {
            offsetArray2[index] += offsetMatrix[index][index2] * offsetArray[index2];
         }
      }

      return offsetArray2;
   }

   public static double[][] resolveDoubleArrayByDoubleArray(double[][] offsetMatrix, double[][] offsetMatrix2) {
      int length = offsetMatrix.length;
      int length2 = offsetMatrix[0].length;
      double[][] offsetMatrix3 = new double[length][length2];

      for (int index = 0; index < length; index++) {
         for (int index2 = 0; index2 < length2; index2++) {
            offsetMatrix3[index][index2] = offsetMatrix[index][index2] + offsetMatrix2[index][index2];
         }
      }

      return offsetMatrix3;
   }

   public static double[][] resolveDoubleArrayByDoubleArray(double[][] offsetMatrix) {
      int length = offsetMatrix.length;
      int length2 = offsetMatrix[0].length;
      double[][] offsetMatrix2 = new double[length2][length];

      for (int index = 0; index < length; index++) {
         for (int index2 = 0; index2 < length2; index2++) {
            offsetMatrix2[index2][index] = offsetMatrix[index][index2];
         }
      }

      return offsetMatrix2;
   }

   public static double[][] resolveDoubleArray(double[][] offsetMatrix) {
      if (offsetMatrix.length == 2 && offsetMatrix[0].length == 2) {
         double offset = offsetMatrix[0][0];
         double offset2 = offsetMatrix[0][1];
         double offset3 = offsetMatrix[1][0];
         double offset4 = offsetMatrix[1][1];
         double offset5 = offset * offset4 - offset2 * offset3;
         if (Math.abs(offset5) < 1.0E-9) {
            throw new ArithmeticException("Matrix is singular and cannot be inverted.");
         } else {
            double offset6 = 1.0 / offset5;
            return new double[][]{{offset4 * offset6, -offset2 * offset6}, {-offset3 * offset6, offset * offset6}};
         }
      } else {
         throw new IllegalArgumentException("Only 2x2 matrices are supported by this method.");
      }
   }

   public static double[][] resolveDoubleArray(double[][] offsetMatrix, double[][] offsetMatrix2) {
      int length = offsetMatrix.length;
      int length2 = offsetMatrix[0].length;
      int length3 = offsetMatrix2[0].length;
      double[][] offsetMatrix3 = new double[length][length3];

      for (int index = 0; index < length; index++) {
         for (int index2 = 0; index2 < length3; index2++) {
            offsetMatrix3[index][index2] = 0.0;

            for (int index3 = 0; index3 < length2; index3++) {
               offsetMatrix3[index][index2] = offsetMatrix3[index][index2] + offsetMatrix[index][index3] * offsetMatrix2[index3][index2];
            }
         }
      }

      return offsetMatrix3;
   }

   public static double[] resolveDoubleArrayByDoubleArray(double[] offsetArray, double[] offsetArray2) {
      int length = offsetArray.length;
      double[] offsetArray3 = new double[length];

      for (int index = 0; index < length; index++) {
         offsetArray3[index] = offsetArray[index] + offsetArray2[index];
      }

      return offsetArray3;
   }

   public static double[] resolveDoubleArray(double[] offsetArray, double[] offsetArray2) {
      int length = offsetArray.length;
      double[] offsetArray3 = new double[length];

      for (int index = 0; index < length; index++) {
         offsetArray3[index] = offsetArray[index] - offsetArray2[index];
      }

      return offsetArray3;
   }

   public static double[][] resolveDoubleArray2(double[][] offsetMatrix, double[][] offsetMatrix2) {
      int length = offsetMatrix.length;
      int length2 = offsetMatrix[0].length;
      double[][] offsetMatrix3 = new double[length][length2];

      for (int index = 0; index < length; index++) {
         for (int index2 = 0; index2 < length2; index2++) {
            offsetMatrix3[index][index2] = offsetMatrix[index][index2] - offsetMatrix2[index][index2];
         }
      }

      return offsetMatrix3;
   }
}
