package client.util;

public class AngleTracker extends StandardByteArrayEntry {
   @Override
   public StandardByteArrayEntry resolveStandardByteArrayEntryByByteArray(byte[] byteValueArray, int count, int count2) {
      int count3 = (int)((0x5d4438c35972L ^ 0x2cc4159f46c2L) >>> 48);
      int count4 = 0;
      StandardByteArrayEntry.applyIndex(count, count2, byteValueArray.length);
      count4 = this.getCount2();
      if (count2 <= this.getCount4() - count4) {
         System.arraycopy(super.byteFlagArray, this.computeCount(count4), byteValueArray, count, count2);
         int count5 = count4 + count2;
         int count6 = count5;
         this.resolveStandardByteArrayEntryByIndex(count6);
         return this;
      } else {
         throw new RuntimeException();
      }
   }

   @Override
   public StandardByteArrayEntry resolveStandardByteArrayEntry(byte byteValue) {
      byte[] byteFlagArray = super.byteFlagArray;
      int countAsIndex = this.getCountAsIndex();
      byteFlagArray[this.computeCount(countAsIndex)] = byteValue;
      return this;
   }

   @Override
   public byte getByteCode() {
      byte[] byteFlagArray = super.byteFlagArray;
      int count = this.getCount();
      return byteFlagArray[this.computeCount(count)];
   }

   @Override
   public StandardByteArrayEntry resolveStandardByteArrayEntry(byte[] byteValueArray, int count, int count2) {
      byte byteValue = 0;
      short shortValue = 29962;
      int count3 = -110238142;
      int count4 = (int)((((long)byteValue << 48 | (long)shortValue << 48 >>> 16 | (long)count3 << 32 >>> 32) ^ 0x48ad431f9f2L) >>> 48);
      int count5 = 0;
      StandardByteArrayEntry.applyIndex(count, count2, byteValueArray.length);
      count5 = this.getCount2();
      if (count2 <= this.getCount4() - count5) {
         System.arraycopy(byteValueArray, count, super.byteFlagArray, this.computeCount(count5), count2);
         int count6 = count5 + count2;
         int count7 = count6;
         this.resolveStandardByteArrayEntryByIndex(count7);
         return this;
      } else {
         throw new RuntimeException();
      }
   }

   public int computeCount(int count) {
      return count + super.count2;
   }

   public AngleTracker(byte[] byteValueArray, int count, int count2) {
      super(-1, count, count + count2, byteValueArray.length, byteValueArray, 0);
   }

   public AngleTracker(int count, int count2) {
      super(-1, 0, count2, count, new byte[count], 0);
   }
}
