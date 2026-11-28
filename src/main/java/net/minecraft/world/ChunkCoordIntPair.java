package net.minecraft.world;

import net.minecraft.util.BlockPos;

public class ChunkCoordIntPair {
   public int chunkXPos;
   public int chunkZPos;
   public int cachedHashCode = 0;

   @Override
   public int hashCode() {
      if (this.cachedHashCode == 0) {
         this.cachedHashCode = (1664525 * this.chunkXPos + 1013904223) ^ (1664525 * (this.chunkZPos ^ -559038737) + 1013904223);
      }

      return this.cachedHashCode;
   }

   @Override
   public String toString() {
      return "[" + this.chunkXPos + ", " + this.chunkZPos + "]";
   }

   public BlockPos getCenterBlock(int n) {
      return new BlockPos(this.getCenterXPos(), n, this.getCenterZPosition());
   }

   public int getZStart() {
      return this.chunkZPos << 4;
   }

   public BlockPos getBlock(int n, int n2, int n3) {
      return new BlockPos((this.chunkXPos << 4) + n, n2, (this.chunkZPos << 4) + n3);
   }

   public static long chunkXZ2Int(int n, int n2) {
      return (long)n & 4294967295L | ((long)n2 & 4294967295L) << 32;
   }

   public int getCenterZPosition() {
      return (this.chunkZPos << 4) + 8;
   }

   public int getXEnd() {
      return (this.chunkXPos << 4) + 15;
   }

   public ChunkCoordIntPair(int n, int n2) {
      this.chunkXPos = n;
      this.chunkZPos = n2;
   }

   public int getZEnd() {
      return (this.chunkZPos << 4) + 15;
   }

   public int getCenterXPos() {
      return (this.chunkXPos << 4) + 8;
   }

   public int getXStart() {
      return this.chunkXPos << 4;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof ChunkCoordIntPair)) {
         return false;
      } else {
         ChunkCoordIntPair chunkCoordIntPair = (ChunkCoordIntPair)obj;
         return this.chunkXPos == chunkCoordIntPair.chunkXPos && this.chunkZPos == chunkCoordIntPair.chunkZPos;
      }
   }
}
