package cc.baritone.utils;

import it.unimi.dsi.fastutil.bytes.ByteArrayList;

public final class VarIntSizeController {
   public final int count;
   public final byte[] byteFlagArray;
   public final int count2;

   public final int getCount() {
      return this.count;
   }

   private static byte[] resolveByteArray(int count3) {
      ByteArrayList byteArrayList = new ByteArrayList();

      int count4;
      for (count4 = count3; (count4 & 128) != 0; count4 >>>= 7) {
         byteArrayList.add((byte)(count4 & 127 | 128));
      }

      byteArrayList.add((byte)(count4 & 0xFF));
      return byteArrayList.toByteArray();
   }

   public static VarIntSizeController resolveVarIntSizeController(byte[] byteValueArray, int count3) {
      int count4 = 0;
      int count5 = 0;
      int count6 = count3;

      byte byteValue;
      do {
         byteValue = byteValueArray[count6++];
         count4 |= (byteValue & 127) << count5++ * 7;
         if (count5 > 5) {
            throw new IllegalArgumentException("VarInt size cannot exceed 5 bytes");
         }
      } while ((byteValue & 128) != 0);

      return new VarIntSizeController(count4);
   }

   public final byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public final int getCount2() {
      return this.count2;
   }

   public VarIntSizeController(int count3) {
      this.count = count3;
      this.byteFlagArray = resolveByteArray(this.count);
      this.count2 = this.byteFlagArray.length;
   }

   public static VarIntSizeController resolveVarIntSizeController(byte[] byteValueArray) {
      return resolveVarIntSizeController(byteValueArray, 0);
   }
}
