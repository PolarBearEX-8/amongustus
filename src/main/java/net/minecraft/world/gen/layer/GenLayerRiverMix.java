package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerRiverMix extends GenLayer {
   private GenLayer biomePatternGeneratorChain;
   private GenLayer riverPatternGeneratorChain;

   @Override
   public void initWorldGenSeed(long l) {
      this.biomePatternGeneratorChain.initWorldGenSeed(l);
      this.riverPatternGeneratorChain.initWorldGenSeed(l);
      super.initWorldGenSeed(l);
   }

   public GenLayerRiverMix(long l, GenLayer genLayer, GenLayer genLayer2) {
      super(l);
      this.biomePatternGeneratorChain = genLayer;
      this.riverPatternGeneratorChain = genLayer2;
   }

   @Override
   public int[] getInts(int n, int n2, int n3, int n4) {
      int[] ints = this.biomePatternGeneratorChain.getInts(n, n2, n3, n4);
      int[] ints2 = this.riverPatternGeneratorChain.getInts(n, n2, n3, n4);
      int[] intCache = IntCache.getIntCache(n3 * n4);

      for (int i = 0; i < n3 * n4; i++) {
         if (ints[i] == BiomeGenBase.ocean.biomeID || ints[i] == BiomeGenBase.deepOcean.biomeID) {
            intCache[i] = ints[i];
         } else if (ints2[i] == BiomeGenBase.river.biomeID) {
            if (ints[i] == BiomeGenBase.icePlains.biomeID) {
               intCache[i] = BiomeGenBase.frozenRiver.biomeID;
            } else if (ints[i] != BiomeGenBase.mushroomIsland.biomeID && ints[i] != BiomeGenBase.mushroomIslandShore.biomeID) {
               intCache[i] = ints2[i] & 0xFF;
            } else {
               intCache[i] = BiomeGenBase.mushroomIslandShore.biomeID;
            }
         } else {
            intCache[i] = ints[i];
         }
      }

      return intCache;
   }
}
