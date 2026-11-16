package client.util;

import java.util.Arrays;

public class Controller6 {
   public final int count = 6;
   public double[][] valueArray;
   public final double[][] valueArray2;
   public double[] valueArray3;
   public final int count2 = 2;
   public double[][] valueArray4;

   public Controller6() {
      this.valueArray3 = new double[6];
      this.valueArray = OnlyMatricesHelper.resolveDoubleArray(6, 1.0);
      this.valueArray4 = new double[6][6];
      this.valueArray4[0][0] = 0.2;
      this.valueArray4[1][1] = 0.05;
      this.valueArray4[2][2] = 0.01;
      this.valueArray4[3][3] = 0.2;
      this.valueArray4[4][4] = 0.05;
      this.valueArray4[5][5] = 0.01;
      this.valueArray2 = new double[][]{{1.0, 0.0}, {0.0, 1.0}};
   }

   public double[] getValueArray3() {
      return this.valueArray3;
   }

   public double[] resolveDoubleArray(double offset) {
      double offset2 = offset * offset;
      double[][] offsetMatrix = new double[][]{{1.0, offset, 0.5 * offset2}, {0.0, 1.0, offset}, {0.0, 0.0, 1.0}};
      double[][] offsetMatrix2 = new double[6][6];

      for (int index = 0; index < 3; index++) {
         for (int index2 = 0; index2 < 3; index2++) {
            offsetMatrix2[index][index2] = offsetMatrix[index][index2];
            offsetMatrix2[index + 3][index2 + 3] = offsetMatrix[index][index2];
         }
      }

      double[] valueArray5 = this.valueArray3;
      this.valueArray3 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix2, valueArray5);
      double[][] offsetMatrix3 = OnlyMatricesHelper.resolveDoubleArrayByDoubleArray(offsetMatrix2);
      double[][] offsetMatrix4 = OnlyMatricesHelper.resolveDoubleArray(this.valueArray, offsetMatrix3);
      double[][] offsetMatrix5 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix2, offsetMatrix4);
      double[][] valueArray6 = this.valueArray4;
      this.valueArray = OnlyMatricesHelper.resolveDoubleArrayByDoubleArray(offsetMatrix5, valueArray6);
      return new double[]{this.valueArray3[0], this.valueArray3[3]};
   }

   public void copyOf(double[] offsetArray) {
      this.valueArray3 = Arrays.copyOf(offsetArray, 6);
   }

   public void applyDoubleArray(double[] offsetArray) {
      long value = 521069056062L;
      byte byteValue = -64;
      double[][] offsetMatrix = new double[][]{{1.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 1.0, 0.0, 0.0}};
      double[] offsetArray2 = new double[]{this.valueArray3[0], this.valueArray3[3]};
      double[] offsetArray3 = OnlyMatricesHelper.resolveDoubleArray(offsetArray, offsetArray2);
      double[][] offsetMatrix2 = OnlyMatricesHelper.resolveDoubleArrayByDoubleArray(offsetMatrix);
      double[][] offsetMatrix3 = OnlyMatricesHelper.resolveDoubleArray(this.valueArray, offsetMatrix2);
      double[][] offsetMatrix4 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix, offsetMatrix3);
      double[][] valueArray5 = this.valueArray2;
      double[][] offsetMatrix5 = OnlyMatricesHelper.resolveDoubleArray(OnlyMatricesHelper.resolveDoubleArrayByDoubleArray(offsetMatrix4, valueArray5));
      offsetMatrix4 = this.valueArray;
      double[][] offsetMatrix6 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix2, offsetMatrix5);
      double[][] offsetMatrix7 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix4, offsetMatrix6);
      double[] valueArray6 = this.valueArray3;
      double[] offsetArray4 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix7, offsetArray3);
      this.valueArray3 = OnlyMatricesHelper.resolveDoubleArrayByDoubleArray(valueArray6, offsetArray4);
      double[][] offsetMatrix8 = OnlyMatricesHelper.resolveDoubleArray(6, 1.0);
      double[][] offsetMatrix9 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix7, offsetMatrix);
      double[][] offsetMatrix10 = OnlyMatricesHelper.resolveDoubleArray2(offsetMatrix8, offsetMatrix9);
      double[][] valueArray7 = this.valueArray;
      this.valueArray = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix10, valueArray7);
   }
}
