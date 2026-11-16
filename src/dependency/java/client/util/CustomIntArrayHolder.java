package client.util;

public class CustomIntArrayHolder {
   public static final int[][] countArray = new int[][]{{0, 36, 3, 41, 18}, {1, 44, 10, 45, 2}, {62, 6, 43, 15, 61}, {28, 55, 25, 21, 56}, {27, 20, 39, 8, 14}};

   public void applyTimeArray(long[][] valueMatrix) {
      valueMatrix[0][0] = Long.rotateLeft(valueMatrix[0][0], countArray[0][0]);
      valueMatrix[0][1] = Long.rotateLeft(valueMatrix[0][1], countArray[0][1]);
      valueMatrix[0][2] = Long.rotateLeft(valueMatrix[0][2], countArray[0][2]);
      valueMatrix[0][3] = Long.rotateLeft(valueMatrix[0][3], countArray[0][3]);
      valueMatrix[0][4] = Long.rotateLeft(valueMatrix[0][4], countArray[0][4]);
      valueMatrix[1][0] = Long.rotateLeft(valueMatrix[1][0], countArray[1][0]);
      valueMatrix[1][1] = Long.rotateLeft(valueMatrix[1][1], countArray[1][1]);
      valueMatrix[1][2] = Long.rotateLeft(valueMatrix[1][2], countArray[1][2]);
      valueMatrix[1][3] = Long.rotateLeft(valueMatrix[1][3], countArray[1][3]);
      valueMatrix[1][4] = Long.rotateLeft(valueMatrix[1][4], countArray[1][4]);
      valueMatrix[2][0] = Long.rotateLeft(valueMatrix[2][0], countArray[2][0]);
      valueMatrix[2][1] = Long.rotateLeft(valueMatrix[2][1], countArray[2][1]);
      valueMatrix[2][2] = Long.rotateLeft(valueMatrix[2][2], countArray[2][2]);
      valueMatrix[2][3] = Long.rotateLeft(valueMatrix[2][3], countArray[2][3]);
      valueMatrix[2][4] = Long.rotateLeft(valueMatrix[2][4], countArray[2][4]);
      valueMatrix[3][0] = Long.rotateLeft(valueMatrix[3][0], countArray[3][0]);
      valueMatrix[3][1] = Long.rotateLeft(valueMatrix[3][1], countArray[3][1]);
      valueMatrix[3][2] = Long.rotateLeft(valueMatrix[3][2], countArray[3][2]);
      valueMatrix[3][3] = Long.rotateLeft(valueMatrix[3][3], countArray[3][3]);
      valueMatrix[3][4] = Long.rotateLeft(valueMatrix[3][4], countArray[3][4]);
      valueMatrix[4][0] = Long.rotateLeft(valueMatrix[4][0], countArray[4][0]);
      valueMatrix[4][1] = Long.rotateLeft(valueMatrix[4][1], countArray[4][1]);
      valueMatrix[4][2] = Long.rotateLeft(valueMatrix[4][2], countArray[4][2]);
      valueMatrix[4][3] = Long.rotateLeft(valueMatrix[4][3], countArray[4][3]);
      valueMatrix[4][4] = Long.rotateLeft(valueMatrix[4][4], countArray[4][4]);
   }
}
