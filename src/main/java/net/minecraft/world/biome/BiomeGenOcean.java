package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeGenOcean extends BiomeGenBase {
   @Override
   public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int n, int n2, double d) {
      super.genTerrainBlocks(world, rand, chunkPrimer, n, n2, d);
   }

   @Override
   public BiomeGenBase.TempCategory getTempCategory() {
      return BiomeGenBase.TempCategory.OCEAN;
   }

   public BiomeGenOcean(int n) {
      super(n);
      this.spawnableCreatureList.clear();
   }
}
