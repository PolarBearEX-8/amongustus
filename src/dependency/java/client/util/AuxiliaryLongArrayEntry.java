package client.util;

public class AuxiliaryLongArrayEntry {
   public static final long[] timestampArray;

   public void applyTimeArray(long[][] valueMatrix, int count) {
      valueMatrix[0][0] = valueMatrix[0][0] ^ timestampArray[count];
   }

   static {
      long[] valueArray = new long[19];
      long[] valueArray2 = new long[23];
      long[] valueArray3 = new long[(int)valueArray[6]];
      valueArray3[0] = 1L;
      valueArray3[1] = valueArray2[1];
      valueArray3[2] = valueArray2[19];
      valueArray3[3] = valueArray2[14];
      valueArray3[4] = valueArray2[3];
      valueArray3[5] = valueArray2[17];
      valueArray3[(int)valueArray[12]] = valueArray2[6];
      valueArray3[(int)valueArray[2]] = valueArray2[2];
      valueArray3[(int)valueArray[11]] = valueArray2[13];
      valueArray3[(int)valueArray[3]] = valueArray2[20];
      valueArray3[(int)valueArray[13]] = valueArray2[5];
      valueArray3[(int)valueArray[5]] = valueArray2[22];
      valueArray3[(int)valueArray[15]] = valueArray2[16];
      valueArray3[(int)valueArray[7]] = valueArray2[21];
      valueArray3[(int)valueArray[16]] = valueArray2[10];
      valueArray3[(int)valueArray[1]] = valueArray2[18];
      valueArray3[(int)valueArray[9]] = valueArray2[12];
      valueArray3[(int)valueArray[8]] = valueArray2[8];
      valueArray3[(int)valueArray[4]] = valueArray2[4];
      valueArray3[(int)valueArray[0]] = valueArray2[0];
      valueArray3[(int)valueArray[18]] = valueArray2[7];
      valueArray3[(int)valueArray[17]] = valueArray2[11];
      valueArray3[(int)valueArray[14]] = valueArray2[15];
      valueArray3[(int)valueArray[10]] = valueArray2[9];
      timestampArray = valueArray3;
   }
}
