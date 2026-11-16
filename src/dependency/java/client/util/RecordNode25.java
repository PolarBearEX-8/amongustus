package client.util;

public class RecordNode25 {
   public final byte[] byteFlagArray;
   public final int count;
   public final int count2;

   public RecordNode25(byte[] byteValueArray, int count3) {
      this.byteFlagArray = byteValueArray;
      this.count = count3;
      this.count2 = count3 + 4;
   }

   public int computeCount(int count3, int count4, int count5) {
      int count6 = count3 << this.count2 | count5 << this.count | count4;
      int count7 = count6 >> 1;
      return (count6 & 1) == 0 ? this.byteFlagArray[count7] & 15 : this.byteFlagArray[count7] >> 4 & 15;
   }
}
