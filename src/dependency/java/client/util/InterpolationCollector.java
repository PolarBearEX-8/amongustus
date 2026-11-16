package client.util;

import net.minecraft.util.BlockPos;

public class InterpolationCollector {
   public int count3;
   public int count2;
   public int count = 0;

   public int getCount3() {
      return this.count3 << 4;
   }

   public int getCountAsIndex() {
      return (this.count3 << 4) + 15;
   }

   public BlockPos resolveBlockPos(int count4) {
      return new BlockPos(this.getCount6(), count4, this.getCount4());
   }

   public BlockPos resolveBlockPos(int count4, int count5, int count6) {
      return new BlockPos((this.count3 << 4) + count4, count5, (this.count2 << 4) + count6);
   }

   public int getCount5() {
      return this.count2 << 4;
   }

   public int getCount6() {
      return (this.count3 << 4) + 8;
   }

   public int getCount4() {
      return (this.count2 << 4) + 8;
   }

   public static long computeTimestamp(int count4, int count5) {
      return (long)count4 & 4294967295L | ((long)count5 & 4294967295L) << 32;
   }

   public int getCount2() {
      return (this.count2 << 4) + 15;
   }

   public InterpolationCollector(int count4, int count5) {
      this.count3 = count4;
      this.count2 = count5;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof InterpolationCollector)) {
         return false;
      } else {
         InterpolationCollector interpolationCollector = (InterpolationCollector)other;
         return this.count3 == interpolationCollector.count3 && this.count2 == interpolationCollector.count2;
      }
   }

   @Override
   public String toString() {
      return "[" + this.count3 + ", " + this.count2 + "]";
   }

   @Override
   public int hashCode() {
      if (this.count == 0) {
         int count4 = 1664525 * this.count3 + 1013904223;
         int count5 = 1664525 * (this.count2 ^ -559038737) + 1013904223;
         this.count = count4 ^ count5;
      }

      return this.count;
   }
}
