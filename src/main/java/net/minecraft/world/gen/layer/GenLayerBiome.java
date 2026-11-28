package net.minecraft.world.gen.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderSettings;

public class GenLayerBiome extends GenLayer {
   private BiomeGenBase[] warmBiomes = new BiomeGenBase[]{
      BiomeGenBase.desert, BiomeGenBase.desert, BiomeGenBase.desert, BiomeGenBase.savanna, BiomeGenBase.savanna, BiomeGenBase.plains
   };
   private BiomeGenBase[] mediumBiomes = new BiomeGenBase[]{
      BiomeGenBase.forest, BiomeGenBase.roofedForest, BiomeGenBase.extremeHills, BiomeGenBase.plains, BiomeGenBase.birchForest, BiomeGenBase.swampland
   };
   private BiomeGenBase[] coldBiomes = new BiomeGenBase[]{BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.taiga, BiomeGenBase.plains};
   private BiomeGenBase[] iceBiomes = new BiomeGenBase[]{BiomeGenBase.icePlains, BiomeGenBase.icePlains, BiomeGenBase.icePlains, BiomeGenBase.coldTaiga};
   private final ChunkProviderSettings settings;

   @Override
   public int[] getInts(int n, int n2, int n3, int n4) {
      int[] ints = this.parent.getInts(n, n2, n3, n4);
      int[] intCache = IntCache.getIntCache(n3 * n4);

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n3; j++) {
            this.initChunkSeed((long)(j + n), (long)(i + n2));
            int n5 = ints[j + i * n3];
            int n6 = (n5 & 3840) >> 8;
            n5 &= -3841;
            if (this.settings != null && this.settings.fixedBiome >= 0) {
               intCache[j + i * n3] = this.settings.fixedBiome;
            } else if (isBiomeOceanic(n5)) {
               intCache[j + i * n3] = n5;
            } else if (n5 == BiomeGenBase.mushroomIsland.biomeID) {
               intCache[j + i * n3] = n5;
            } else if (n5 == 1) {
               if (n6 > 0) {
                  if (this.nextInt(3) == 0) {
                     intCache[j + i * n3] = BiomeGenBase.mesaPlateau.biomeID;
                  } else {
                     intCache[j + i * n3] = BiomeGenBase.mesaPlateau_F.biomeID;
                  }
               } else {
                  intCache[j + i * n3] = this.warmBiomes[this.nextInt(this.warmBiomes.length)].biomeID;
               }
            } else if (n5 == 2) {
               if (n6 > 0) {
                  intCache[j + i * n3] = BiomeGenBase.jungle.biomeID;
               } else {
                  intCache[j + i * n3] = this.mediumBiomes[this.nextInt(this.mediumBiomes.length)].biomeID;
               }
            } else if (n5 == 3) {
               if (n6 > 0) {
                  intCache[j + i * n3] = BiomeGenBase.megaTaiga.biomeID;
               } else {
                  intCache[j + i * n3] = this.coldBiomes[this.nextInt(this.coldBiomes.length)].biomeID;
               }
            } else if (n5 == 4) {
               intCache[j + i * n3] = this.iceBiomes[this.nextInt(this.iceBiomes.length)].biomeID;
            } else {
               intCache[j + i * n3] = BiomeGenBase.mushroomIsland.biomeID;
            }
         }
      }

      return intCache;
   }

   public GenLayerBiome(long l, GenLayer genLayer, WorldType worldType, String str) {
      super(l);
      this.parent = genLayer;
      if (worldType == WorldType.DEFAULT_1_1) {
         this.warmBiomes = new BiomeGenBase[]{
            BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.taiga
         };
         this.settings = null;
      } else if (worldType == WorldType.CUSTOMIZED) {
         this.settings = ChunkProviderSettings.Factory.jsonToFactory(str).build();
      } else {
         this.settings = null;
      }
   }
}
