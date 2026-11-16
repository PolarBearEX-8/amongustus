package net.raphimc.viaproxy;

import java.util.Arrays;

public class Helper {
   public static int[] resolveIndexArray(byte[] byteValueArray) {
      int[] countArray = new int[(int)Math.ceil((double)byteValueArray.length / 3.0)];

      for (int index = 0; index < countArray.length; index++) {
         int count = index * 3;
         byte byteValue = byteValueArray[count];
         Byte byteValue2 = count + 1 < byteValueArray.length ? byteValueArray[count + 1] : null;
         Byte byteValue3 = count + 2 < byteValueArray.length ? byteValueArray[count + 2] : null;
         int count2 = -1073741824 | (byteValue & 255) << 16;
         if (byteValue2 != null) {
            count2 = count2 | 536870912 | (byteValue2 & 255) << 8;
         }

         if (byteValue3 != null) {
            count2 = count2 | 268435456 | byteValue3 & 255;
         }

         countArray[index] = count2;
      }

      return countArray;
   }

   public static byte[] resolveByteArray(int[] countArray) {
      byte[] byteValueArray = new byte[countArray.length * 3];
      int count = 0;

      for (int count2 : countArray) {
         int count3 = count2 >> 16 & 0xFF;
         int count4 = count2 >> 8 & 0xFF;
         int count5 = count2 & 0xFF;
         if ((count2 >> 30 & 1) == 1) {
            byteValueArray[count] = (byte)count3;
            count++;
         }

         if ((count2 >> 29 & 1) == 1) {
            byteValueArray[count] = (byte)count4;
            count++;
         }

         if ((count2 >> 28 & 1) == 1) {
            byteValueArray[count] = (byte)count5;
            count++;
         }
      }

      return Arrays.copyOfRange(byteValueArray, 0, count);
   }

   public static byte[] resolveByteArray(int count) {
      return resolveByteArray(new int[]{count});
   }
}
