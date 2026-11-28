package net.raphimc.vialegacy.api.util;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import net.raphimc.vialegacy.api.model.ChunkCoord;

public class ChunkCoordSpiral implements Iterable<ChunkCoord> {
   final ChunkCoord center;
   final ChunkCoord lowerBound;
   final ChunkCoord upperBound;
   final int step;
   boolean returnCenter = true;

   public ChunkCoordSpiral(ChunkCoord center, ChunkCoord lowerBound, ChunkCoord upperBound, int step) {
      this.center = center;
      this.lowerBound = lowerBound;
      this.upperBound = upperBound;
      this.step = step;
   }

   public ChunkCoordSpiral(ChunkCoord center, ChunkCoord radius, int step) {
      this(
         center,
         new ChunkCoord(center.chunkX - radius.chunkX, center.chunkZ - radius.chunkZ),
         new ChunkCoord(center.chunkX + radius.chunkX, center.chunkZ + radius.chunkZ),
         step
      );
   }

   public ChunkCoordSpiral(ChunkCoord center, ChunkCoord radius) {
      this(center, radius, 1);
   }

   @Override
   public Iterator<ChunkCoord> iterator() {
      return new Iterator<ChunkCoord>() {
         int x = ChunkCoordSpiral.this.center.chunkX;
         int z = ChunkCoordSpiral.this.center.chunkZ;
         float n = 1.0F;
         int floorN = 1;
         int i = 0;
         int j = 0;

         @Override
         public boolean hasNext() {
            return ChunkCoordSpiral.this.returnCenter
               || this.x >= ChunkCoordSpiral.this.lowerBound.chunkX
                  && this.x <= ChunkCoordSpiral.this.upperBound.chunkX
                  && this.z >= ChunkCoordSpiral.this.lowerBound.chunkZ
                  && this.z <= ChunkCoordSpiral.this.upperBound.chunkZ;
         }

         public ChunkCoord next() {
            if (ChunkCoordSpiral.this.returnCenter) {
               ChunkCoordSpiral.this.returnCenter = false;
               return new ChunkCoord(this.x, this.z);
            } else {
               this.floorN = (int)Math.floor((double)this.n);
               if (this.j < this.floorN) {
                  switch (this.i % 4) {
                     case 0:
                        this.z = this.z + ChunkCoordSpiral.this.step;
                        break;
                     case 1:
                        this.x = this.x + ChunkCoordSpiral.this.step;
                        break;
                     case 2:
                        this.z = this.z - ChunkCoordSpiral.this.step;
                        break;
                     case 3:
                        this.x = this.x - ChunkCoordSpiral.this.step;
                  }

                  this.j++;
                  return new ChunkCoord(this.x, this.z);
               } else {
                  this.j = 0;
                  this.n += 0.5F;
                  this.i++;
                  return this.next();
               }
            }
         }
      };
   }

   @Override
   public Spliterator<ChunkCoord> spliterator() {
      return Spliterators.spliteratorUnknownSize(this.iterator(), 16);
   }
}
