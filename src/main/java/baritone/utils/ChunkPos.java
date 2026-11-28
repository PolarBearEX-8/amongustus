package baritone.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;

public class ChunkPos {
   public final int x;
   public final int z;

   public ChunkPos(int n, int n2) {
      this.x = n;
      this.z = n2;
   }

   public int getZStart() {
      return this.z << 4;
   }

   public ChunkPos(BlockPos pos) {
      this.x = pos.getX() >> 4;
      this.z = pos.getZ() >> 4;
   }

   @Override
   public String toString() {
      return "[" + this.x + ", " + this.z + "]";
   }

   public int getZEnd() {
      return (this.z << 4) + 15;
   }

   public int getXStart() {
      return this.x << 4;
   }

   public double getDistanceSq(Entity entity) {
      double d = (double)(this.x * 16 + 8);
      double d2 = (double)(this.z * 16 + 8);
      double d3 = d - entity.posX;
      double d4 = d2 - entity.posZ;
      return d3 * d3 + d4 * d4;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof ChunkPos)) {
         return false;
      } else {
         ChunkPos chunkPos = (ChunkPos)obj;
         return this.x == chunkPos.x && this.z == chunkPos.z;
      }
   }

   @Override
   public int hashCode() {
      return (1664525 * this.x + 1013904223) ^ (1664525 * (this.z ^ -559038737) + 1013904223);
   }

   public BlockPos getBlock(int n, int n2, int n3) {
      return new BlockPos((this.x << 4) + n, n2, (this.z << 4) + n3);
   }

   public int getXEnd() {
      return (this.x << 4) + 15;
   }

   public static long asLong(int n, int n2) {
      return (long)n & 4294967295L | ((long)n2 & 4294967295L) << 32;
   }
}
