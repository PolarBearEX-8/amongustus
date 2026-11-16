package cc.baritone.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;

public class AStarVerifier {
   public final int count;
   public final int count2;

   public static long computeTimestamp(int count3, int count4) {
      return (long)count3 & 4294967295L | ((long)count4 & 4294967295L) << 32;
   }

   public AStarVerifier(int count3, int count4) {
      this.count = count3;
      this.count2 = count4;
   }

   @Override
   public String toString() {
      return "[" + this.count + ", " + this.count2 + "]";
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof AStarVerifier)) {
         return false;
      } else {
         AStarVerifier aStarVerifier = (AStarVerifier)other;
         return this.count == aStarVerifier.count && this.count2 == aStarVerifier.count2;
      }
   }

   public AStarVerifier(BlockPos blockPos) {
      this.count = blockPos.getX() >> 4;
      this.count2 = blockPos.getZ() >> 4;
   }

   public double computeValue(Entity entity) {
      double offset = (double)(this.count * 16 + 8);
      double offset2 = (double)(this.count2 * 16 + 8);
      double offset3 = offset - entity.posX;
      double offset4 = offset2 - entity.posZ;
      return offset3 * offset3 + offset4 * offset4;
   }

   public int getCount2() {
      return this.count2 << 4;
   }

   public int getCount3() {
      return (this.count2 << 4) + 15;
   }

   public int getCountAsIndex() {
      return this.count << 4;
   }

   public BlockPos resolveBlockPos(int count3, int count4, int count5) {
      return new BlockPos((this.count << 4) + count3, count4, (this.count2 << 4) + count5);
   }

   public int getCount() {
      return (this.count << 4) + 15;
   }

   @Override
   public int hashCode() {
      int count3 = 1664525 * this.count + 1013904223;
      int count4 = 1664525 * (this.count2 ^ -559038737) + 1013904223;
      return count3 ^ count4;
   }
}
