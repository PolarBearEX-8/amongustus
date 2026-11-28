package net.minecraft.world.gen;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class MapGenBase {
   public int range = 8;
   public Random rand = new Random();
   public World worldObj;

   public void recursiveGenerate(World world, int n, int n2, int n3, int n4, ChunkPrimer chunkPrimer) {
   }

   public void generate(IChunkProvider chunkProvider, World world, int n, int n2, ChunkPrimer chunkPrimer) {
      int range2 = this.range;
      this.worldObj = world;
      this.rand.setSeed(world.getSeed());
      long l = this.rand.nextLong();
      long l2 = this.rand.nextLong();

      for (int i = n - range2; i <= n + range2; i++) {
         for (int j = n2 - range2; j <= n2 + range2; j++) {
            long l3 = (long)i * l;
            long l4 = (long)j * l2;
            this.rand.setSeed(l3 ^ l4 ^ world.getSeed());
            this.recursiveGenerate(world, i, j, n, n2, chunkPrimer);
         }
      }
   }
}
