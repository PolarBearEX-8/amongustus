package net.raphimc.vialegacy.protocol.release.r1_1tor1_2_1_3.biome.release.genlayer;

import net.raphimc.vialegacy.protocol.release.r1_1tor1_2_1_3.biome.release.IntCache;
import net.raphimc.vialegacy.protocol.release.r1_1tor1_2_1_3.biome.release.NewBiomeGenBase;

public class GenLayerRiverMix extends GenLayer {
   private final GenLayer biomePatternGeneratorChain;
   private final GenLayer riverPatternGeneratorChain;

   public GenLayerRiverMix(long l, GenLayer genlayer, GenLayer genlayer1) {
      super(l);
      this.biomePatternGeneratorChain = genlayer;
      this.riverPatternGeneratorChain = genlayer1;
   }

   @Override
   public void initWorldGenSeed(long l) {
      this.biomePatternGeneratorChain.initWorldGenSeed(l);
      this.riverPatternGeneratorChain.initWorldGenSeed(l);
      super.initWorldGenSeed(l);
   }

   @Override
   public int[] getInts(int i, int j, int k, int l) {
      int[] ai = this.biomePatternGeneratorChain.getInts(i, j, k, l);
      int[] ai1 = this.riverPatternGeneratorChain.getInts(i, j, k, l);
      int[] ai2 = IntCache.getIntCache(k * l);

      for (int i1 = 0; i1 < k * l; i1++) {
         if (ai[i1] == NewBiomeGenBase.ocean.biomeID) {
            ai2[i1] = ai[i1];
         } else if (ai1[i1] >= 0) {
            if (ai[i1] == NewBiomeGenBase.icePlains.biomeID) {
               ai2[i1] = NewBiomeGenBase.frozenRiver.biomeID;
            } else if (ai[i1] != NewBiomeGenBase.mushroomIsland.biomeID && ai[i1] != NewBiomeGenBase.mushroomIslandShore.biomeID) {
               ai2[i1] = ai1[i1];
            } else {
               ai2[i1] = NewBiomeGenBase.mushroomIslandShore.biomeID;
            }
         } else {
            ai2[i1] = ai[i1];
         }
      }

      return ai2;
   }
}
