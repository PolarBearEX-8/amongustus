package client.util;

public class StandardLongEntry {
   public long timestamp;
   public final int count2;
   public final int count = 1103515245;
   public final int count3;

   public long getTimestamp() {
      long value = 0L;
      value = Long.MAX_VALUE;
      this.timestamp = (this.timestamp * 1103515245L + 12345L) % value;
      return this.timestamp;
   }

   public int computeCount(int count4, int count5) {
      int count6 = 0;
      count6 = count5 - count4 + 1;
      this.timestamp = (this.timestamp * 1103515245L + 12345L) % (long)count6;
      if (this.timestamp < 0L) {
         this.timestamp *= -1L;
      }

      return (int)this.timestamp + count4;
   }

   public long computeTimestamp(long value, long value2) {
      long value3 = 0L;
      long value4 = 0x357dc1a3b7d5f90cL;
      value3 = value2 - value + (value4 % 525801L ^ 206804L);
      value4 = (value4 ^ -0x69028c986d2f1155L) - 0x2dd7c05ad7e4bc86L;
      this.timestamp = (this.timestamp * 1103515245L + 12345L) % value3;
      value4 = (value4 ^ 0x1d6c6cf6f28017L) + 0x4b34f9ab77e4f168L;
      if (this.timestamp < 0L) {
         value4 = (value4 ^ -0x6b004bdfafa32383L) - 0x36a9b6e770a1c9f1L;
         this.timestamp *= -1L;
      }

      return this.timestamp + value;
   }

   public StandardLongEntry(long value) {
      this.count2 = 12345;
      this.count3 = Integer.MAX_VALUE;
      this.timestamp = value;
   }
}
