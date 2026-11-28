package net.raphimc.vialegacy.api.model;

import java.util.Objects;

public class ChunkCoord {
   public int chunkX;
   public int chunkZ;

   public ChunkCoord(int chunkX, int chunkZ) {
      this.chunkX = chunkX;
      this.chunkZ = chunkZ;
   }

   public ChunkCoord(long pos) {
      this.chunkX = (int)pos;
      this.chunkZ = (int)(pos >> 32);
   }

   public long toLong() {
      return toLong(this.chunkX, this.chunkZ);
   }

   public static long toLong(int chunkX, int chunkZ) {
      return (long)chunkX & 4294967295L | ((long)chunkZ & 4294967295L) << 32;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         ChunkCoord that = (ChunkCoord)o;
         return this.chunkX == that.chunkX && this.chunkZ == that.chunkZ;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.chunkX, this.chunkZ);
   }

   @Override
   public String toString() {
      int chunkZ2 = this.chunkZ;
      int chunkX2 = this.chunkX;
      return "ChunkCoord{chunkX=" + chunkX2 + ", chunkZ=" + chunkZ2 + "}";
   }
}
