package baritone.api.behavior.look;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongSupplier;

public final class ForkableRandom {
   private static final double DOUBLE_UNIT = 1.110223E-16F;
   private final long[] state;

   public long next() {
      long l = rotl(this.state[0] + this.state[3], 23) + this.state[0];
      long l2 = this.state[1] << 17;
      this.state[2] = this.state[2] ^ this.state[0];
      this.state[3] = this.state[3] ^ this.state[1];
      this.state[1] = this.state[1] ^ this.state[2];
      this.state[0] = this.state[0] ^ this.state[3];
      this.state[2] = this.state[2] ^ l2;
      this.state[3] = rotl(this.state[3], 45);
      return l;
   }

   public ForkableRandom fork() {
      return new ForkableRandom(Arrays.copyOf(this.state, 4));
   }

   public ForkableRandom(long l) {
      AtomicLong atomicLong = new AtomicLong(l);
      LongSupplier longSupplier = () -> {
         long l2 = atomicLong.addAndGet(-7046029254386353131L);
         l2 = (l2 ^ l2 >>> 30) * -4658895280553007687L;
         l2 = (l2 ^ l2 >>> 27) * -7723592293110705685L;
         return l2 ^ l2 >>> 31;
      };
      this.state = new long[]{longSupplier.getAsLong(), longSupplier.getAsLong(), longSupplier.getAsLong(), longSupplier.getAsLong()};
   }

   private static long rotl(long l, int n) {
      return l << n | l >>> 64 - n;
   }

   private ForkableRandom(long[] longs) {
      this.state = longs;
   }

   public ForkableRandom() {
      this(System.nanoTime() ^ System.currentTimeMillis());
   }

   public double nextDouble() {
      return (double)(this.next() >>> 11) * 1.110223E-16F;
   }
}
