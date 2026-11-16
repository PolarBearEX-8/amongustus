package client.util;

public class RecordNode8 {
   public final long[][] timestampArray;
   public final byte[] byteFlagArray = new byte[16];

   public boolean matchesByteArray(byte[] byteValueArray) {
      if (byteValueArray.length != this.byteFlagArray.length) {
         return false;
      } else {
         for (int index = 0; index < this.byteFlagArray.length; index++) {
            if (byteValueArray[index] != this.byteFlagArray[index]) {
               return false;
            }
         }

         return true;
      }
   }

   public RecordNode8(byte[] byteValueArray) {
      System.arraycopy(byteValueArray, 0, this.byteFlagArray, 0, 16);
      this.timestampArray = new long[16][256];
      long value = 0L;
      long value2 = 0L;

      for (int index = 0; index < 8; index++) {
         value = value << 8 | (long)(byteValueArray[index] & 255);
         value2 = value2 << 8 | (long)(byteValueArray[index + 8] & 255);
      }

      for (int index2 = 0; index2 < 16; index2++) {
         long value3 = value;
         long value4 = value2;

         for (int index3 = 0; index3 < index2 * 8; index3++) {
            boolean flag = (value4 & 1L) != 0L;
            value4 = value4 >>> 1 | (value3 & 1L) << 63;
            value3 >>>= 1;
            if (flag) {
               value3 ^= -0x1f00000000000000L;
            }
         }

         for (int index4 = 1; index4 < 256; index4++) {
            long value5 = 0L;
            long value6 = 0L;

            for (int index5 = 0; index5 < 8; index5++) {
               if ((index4 & 1 << index5) != 0) {
                  value5 ^= value3;
                  value6 ^= value4;
               }

               boolean flag2 = (value4 & 1L) != 0L;
               value4 = value4 >>> 1 | (value3 & 1L) << 63;
               value3 >>>= 1;
               if (flag2) {
                  value3 ^= -0x1f00000000000000L;
               }
            }

            this.timestampArray[index2][index4] = value5 << 32 | value6 & 4294967295L;
         }
      }
   }
}
