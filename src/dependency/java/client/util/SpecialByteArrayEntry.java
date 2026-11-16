package client.util;

public class SpecialByteArrayEntry {
   public final byte[] byteFlagArray;

   public int computeCountByIndex(int count, int count2, int count3) {
      return this.computeCount(this.computeCount(count, count2, count3));
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public void applyIndex(int count, int count2) {
      int count3 = this.computeCountByIndex(count);
      if (this.checkIndex(count)) {
         this.byteFlagArray[count3] = (byte)(this.byteFlagArray[count3] & 240 | count2 & 15);
      } else {
         this.byteFlagArray[count3] = (byte)(this.byteFlagArray[count3] & 15 | (count2 & 15) << 4);
      }
   }

   private int computeCount(int count, int count2, int count3) {
      return count2 << 8 | count3 << 4 | count;
   }

   public int computeCount(int count) {
      int count2 = this.computeCountByIndex(count);
      return this.checkIndex(count) ? this.byteFlagArray[count2] & 15 : this.byteFlagArray[count2] >> 4 & 15;
   }

   public void applyIndex(int count, int count2, int count3, int count4) {
      this.applyIndex(this.computeCount(count, count2, count3), count4);
   }

   private int computeCountByIndex(int count) {
      return count >> 1;
   }

   private boolean checkIndex(int count) {
      return (count & 1) == 0;
   }

   public SpecialByteArrayEntry(byte[] byteValueArray) {
      this.byteFlagArray = byteValueArray;
      if (byteValueArray.length != 2048) {
         throw new IllegalArgumentException("ChunkNibbleArrays should be 2048 bytes not: " + byteValueArray.length);
      }
   }

   public SpecialByteArrayEntry() {
      this.byteFlagArray = new byte[2048];
   }
}
