package client.util;

import java.util.Arrays;

public class Controller4 {
   public double[][] valueArray;
   public final int count;
   public double[] valueArray2;
   public final int count2 = 5;
   public double[][] valueArray3;
   public final double[][] valueArray4;

   public double[] getValueArray2() {
      return this.valueArray2;
   }

   public double[] resolveDoubleArray(double offset) {
      short shortValue = 21007;
      short shortValue2 = 4755;
      short shortValue3 = -2257;
      double offset2 = this.valueArray2[0];
      double offset3 = this.valueArray2[1];
      double offset4 = this.valueArray2[2];
      double offset5 = this.valueArray2[3];
      double offset6 = this.valueArray2[4];
      double offset7 = 0.001;
      double offset8;
      double offset9;
      if (Math.abs(offset6) > offset7) {
         offset8 = offset2 + offset4 / offset6 * (Math.sin(offset5 + offset6 * offset) - Math.sin(offset5));
         offset9 = offset3 - offset4 / offset6 * (Math.cos(offset5 + offset6 * offset) - Math.cos(offset5));
      } else {
         offset8 = offset2 + offset4 * offset * Math.cos(offset5);
         offset9 = offset3 + offset4 * offset * Math.sin(offset5);
      }

      double offset10 = offset5 + offset6 * offset;
      double[] offsetArray = new double[]{offset8, offset9, offset4, offset10, offset6};
      double[][] offsetMatrix = new double[5][5];
      if (Math.abs(offset6) > offset7) {
         offsetMatrix[0][0] = 1.0;
         offsetMatrix[0][2] = 1.0 / offset6 * (Math.sin(offset5 + offset6 * offset) - Math.sin(offset5));
         offsetMatrix[0][3] = offset4 / offset6 * (Math.cos(offset5 + offset6 * offset) - Math.cos(offset5));
         offsetMatrix[0][4] = -offset4 / (offset6 * offset6) * (Math.sin(offset5 + offset6 * offset) - Math.sin(offset5)) + offset4 / offset6 * offset * Math.cos(offset5 + offset6 * offset);
         offsetMatrix[1][1] = 1.0;
         offsetMatrix[1][2] = -(1.0 / offset6) * (Math.cos(offset5 + offset6 * offset) - Math.cos(offset5));
         offsetMatrix[1][3] = offset4 / offset6 * (Math.sin(offset5 + offset6 * offset) - Math.sin(offset5));
         offsetMatrix[1][4] = offset4 / (offset6 * offset6) * (Math.cos(offset5 + offset6 * offset) - Math.cos(offset5)) + offset4 / offset6 * offset * Math.sin(offset5 + offset6 * offset);
      } else {
         offsetMatrix[0][0] = 1.0;
         offsetMatrix[0][2] = offset * Math.cos(offset5);
         offsetMatrix[0][3] = -offset4 * offset * Math.sin(offset5);
         offsetMatrix[1][1] = 1.0;
         offsetMatrix[1][2] = offset * Math.sin(offset5);
         offsetMatrix[1][3] = offset4 * offset * Math.cos(offset5);
      }

      offsetMatrix[2][2] = 1.0;
      offsetMatrix[3][3] = 1.0;
      offsetMatrix[3][4] = offset;
      offsetMatrix[4][4] = 1.0;
      this.valueArray2 = offsetArray;
      double[][] offsetMatrix2 = OnlyMatricesHelper.resolveDoubleArrayByDoubleArray(offsetMatrix);
      double[][] offsetMatrix3 = OnlyMatricesHelper.resolveDoubleArray(this.valueArray, offsetMatrix2);
      double[][] offsetMatrix4 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix, offsetMatrix3);
      double[][] valueArray5 = this.valueArray3;
      this.valueArray = OnlyMatricesHelper.resolveDoubleArrayByDoubleArray(offsetMatrix4, valueArray5);
      return new double[]{this.valueArray2[0], this.valueArray2[1]};
   }

   public void copyOf(double[] offsetArray) {
      this.valueArray2 = Arrays.copyOf(offsetArray, 5);
   }

   public double getValue() {
      return this.valueArray2[4];
   }

   public void applyDoubleArray(double[] offsetArray) {
      double[][] offsetMatrix = new double[][]{{1.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 1.0, 0.0, 0.0, 0.0}};
      double[] offsetArray2 = new double[]{this.valueArray2[0], this.valueArray2[1]};
      double[] offsetArray3 = OnlyMatricesHelper.resolveDoubleArray(offsetArray, offsetArray2);
      double[][] offsetMatrix2 = OnlyMatricesHelper.resolveDoubleArrayByDoubleArray(offsetMatrix);
      double[][] offsetMatrix3 = OnlyMatricesHelper.resolveDoubleArray(this.valueArray, offsetMatrix2);
      double[][] offsetMatrix4 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix, offsetMatrix3);
      double[][] valueArray5 = this.valueArray4;
      double[][] offsetMatrix5 = OnlyMatricesHelper.resolveDoubleArray(OnlyMatricesHelper.resolveDoubleArrayByDoubleArray(offsetMatrix4, valueArray5));
      offsetMatrix4 = this.valueArray;
      double[][] offsetMatrix6 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix2, offsetMatrix5);
      double[][] offsetMatrix7 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix4, offsetMatrix6);
      double[] valueArray6 = this.valueArray2;
      double[] offsetArray4 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix7, offsetArray3);
      this.valueArray2 = OnlyMatricesHelper.resolveDoubleArrayByDoubleArray(valueArray6, offsetArray4);
      double[][] offsetMatrix8 = OnlyMatricesHelper.resolveDoubleArray(5, 1.0);
      double[][] offsetMatrix9 = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix7, offsetMatrix);
      double[][] offsetMatrix10 = OnlyMatricesHelper.resolveDoubleArray2(offsetMatrix8, offsetMatrix9);
      double[][] valueArray7 = this.valueArray;
      this.valueArray = OnlyMatricesHelper.resolveDoubleArray(offsetMatrix10, valueArray7);
   }

   public Controller4() {
      this.count = 2;
      this.valueArray2 = new double[5];
      this.valueArray = OnlyMatricesHelper.resolveDoubleArray(5, 1.0);
      this.valueArray3 = new double[5][5];
      this.valueArray3[0][0] = 0.0;
      this.valueArray3[1][1] = 0.0;
      this.valueArray3[2][2] = 0.8;
      this.valueArray3[3][3] = 0.3;
      this.valueArray3[4][4] = 0.2;
      this.valueArray4 = new double[][]{{1.0, 0.0}, {0.0, 1.0}};
   }
}
