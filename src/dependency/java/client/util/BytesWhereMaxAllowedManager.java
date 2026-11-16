package client.util;

public class BytesWhereMaxAllowedManager {
   public static final BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager = new BytesWhereMaxAllowedManagerAdapter(0L);
   public final long timestamp;
   public long timestamp2;

   public void applyTime(long value) {
      this.timestamp2 += value / 8L;
      if (this.timestamp2 > this.timestamp) {
         throw new RuntimeException(
            "Tried to read NBT tag that was too big; tried to allocate: " + this.timestamp2 + "bytes where max allowed: " + this.timestamp
         );
      }
   }

   public BytesWhereMaxAllowedManager(long value) {
      this.timestamp = value;
   }
}
