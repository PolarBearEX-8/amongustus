package baritone.cache;

public enum CachedBlockType {
   AIR(0),
   WATER(1),
   AVOID(2),
   SOLID(3);

private final boolean[] bits;

   public static CachedBlockType getByBits(boolean flag, boolean flag2) {
      return flag ? (flag2 ? SOLID : AVOID) : (flag2 ? WATER : AIR);
   }

   public final boolean[] getBits() {
      return this.bits;
   }

   private CachedBlockType(int n) {
      this.bits = new boolean[]{(n & 2) != 0, (n & 1) != 0};
   }
}
