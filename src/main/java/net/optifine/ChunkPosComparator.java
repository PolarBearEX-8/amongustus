package net.optifine;

import java.util.Comparator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;

public class ChunkPosComparator implements Comparator {
   private int chunkPosX;
   private int chunkPosZ;
   private double yawRad;
   private double pitchNorm;

   public ChunkPosComparator(int n, int n2, double d, double d2) {
      this.chunkPosX = n;
      this.chunkPosZ = n2;
      this.yawRad = d;
      this.pitchNorm = 1.0 - MathHelper.clamp_double(Math.abs(d2) / (Math.PI / 2), 0.0, 1.0);
   }

   @Override
   public int compare(Object a, Object b) {
      return this.compare2((ChunkCoordIntPair)a, (ChunkCoordIntPair)b);
   }

   public int compare2(ChunkCoordIntPair chunkCoordIntPair, ChunkCoordIntPair chunkCoordIntPair2) {
      return this.getDistSq(chunkCoordIntPair) - this.getDistSq(chunkCoordIntPair2);
   }

   private int getDistSq(ChunkCoordIntPair chunkCoordIntPair) {
      int n = chunkCoordIntPair.chunkXPos - this.chunkPosX;
      int n2 = chunkCoordIntPair.chunkZPos - this.chunkPosZ;
      int n3 = n * n + n2 * n2;
      double d2 = Math.abs(MathHelper.atan2((double)n2, (double)n) - this.yawRad);
      if (d2 > Math.PI) {
         d2 = (Math.PI * 2) - d2;
      }

      return (int)((double)n3 * 1000.0 * this.pitchNorm * d2 * d2);
   }
}
