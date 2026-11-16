package cc.baritone.utils;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongSupplier;

public final class DoubleEntry {
   public static final double value = 1.110223E-16F;
   public final long[] timestampArray;

   private static long computeTimestamp(long value2, int count) {
      return value2 << count | value2 >>> 64 - count;
   }

   public long getTimestamp() {
      long value2 = computeTimestamp(this.timestampArray[0] + this.timestampArray[3], 23) + this.timestampArray[0];
      long value3 = this.timestampArray[1] << 17;
      this.timestampArray[2] = this.timestampArray[2] ^ this.timestampArray[0];
      this.timestampArray[3] = this.timestampArray[3] ^ this.timestampArray[1];
      this.timestampArray[1] = this.timestampArray[1] ^ this.timestampArray[2];
      this.timestampArray[0] = this.timestampArray[0] ^ this.timestampArray[3];
      this.timestampArray[2] = this.timestampArray[2] ^ value3;
      this.timestampArray[3] = computeTimestamp(this.timestampArray[3], 45);
      return value2;
   }

   private static long computeTimestamp(AtomicLong atomicLong) {
      long value2 = atomicLong.addAndGet(-0x61c8864680b583ebL);
      long value3 = (value2 ^ value2 >>> 30) * -0x40a7b892e31b1a47L;
      long value4 = (value3 ^ value3 >>> 27) * -0x6b2fb644ecceee15L;
      return value4 ^ value4 >>> 31;
   }

   public DoubleEntry copyOf() {
      return new DoubleEntry(Arrays.copyOf(this.timestampArray, 4));
   }

   public double getValue() {
      return (double)(this.getTimestamp() >>> 11) * 1.110223E-16F;
   }

   public DoubleEntry(long value2) {
      AtomicLong seed = new AtomicLong(value2);
      LongSupplier longSupplier = () -> DoubleEntry.computeTimestamp(seed);
      this.timestampArray = new long[]{longSupplier.getAsLong(), longSupplier.getAsLong(), longSupplier.getAsLong(), longSupplier.getAsLong()};
   }

   public DoubleEntry() {
      this(System.nanoTime() ^ System.currentTimeMillis());
   }

   private DoubleEntry(long[] valueArray) {
      this.timestampArray = valueArray;
   }
}
