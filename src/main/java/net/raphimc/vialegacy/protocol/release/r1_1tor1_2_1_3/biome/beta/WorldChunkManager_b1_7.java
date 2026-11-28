package net.raphimc.vialegacy.protocol.release.r1_1tor1_2_1_3.biome.beta;

import java.util.Random;
import net.raphimc.vialegacy.api.model.ChunkCoord;
import net.raphimc.vialegacy.protocol.release.r1_1tor1_2_1_3.biome.IWorldChunkManager;
import net.raphimc.vialegacy.protocol.release.r1_1tor1_2_1_3.biome.release.NewBiomeGenBase;

public class WorldChunkManager_b1_7 implements IWorldChunkManager {
   private final NoiseGeneratorOctaves2 temperatureNoise;
   private final NoiseGeneratorOctaves2 humidityNoise;
   private final NoiseGeneratorOctaves2 detailNoise;
   public double[] temperature;
   public double[] humidity;
   public double[] detailBuffer;
   public OldBiomeGenBase[] cachedBiomes;

   public WorldChunkManager_b1_7(long seed) {
      this.temperatureNoise = new NoiseGeneratorOctaves2(new Random(seed * 9871L), 4);
      this.humidityNoise = new NoiseGeneratorOctaves2(new Random(seed * 39811L), 4);
      this.detailNoise = new NoiseGeneratorOctaves2(new Random(seed * 543321L), 2);
   }

   @Override
   public byte[] getBiomeDataAt(int chunkX, int chunkZ) {
      byte[] biomeData = new byte[256];

      for (int x = 0; x < 16; x++) {
         for (int z = 0; z < 16; z++) {
            biomeData[z << 4 | x] = (byte)this.getBiomeGenAt(chunkX * 16 + x, chunkZ * 16 + z).biomeID;
         }
      }

      return biomeData;
   }

   public NewBiomeGenBase getBiomeGenAtChunkCoord(ChunkCoord chunkcoordintpair) {
      return this.getBiomeGenAt(chunkcoordintpair.chunkX << 4, chunkcoordintpair.chunkZ << 4);
   }

   public NewBiomeGenBase getBiomeGenAt(int i, int j) {
      OldBiomeGenBase oldBiomeGenBase = this.getBiomesForGeneration(i, j, 1, 1)[0];
      if (oldBiomeGenBase.equals(OldBiomeGenBase.rainforest)) {
         return NewBiomeGenBase.jungle;
      } else if (oldBiomeGenBase.equals(OldBiomeGenBase.swampland)) {
         return NewBiomeGenBase.swampland;
      } else if (oldBiomeGenBase.equals(OldBiomeGenBase.seasonalForest)) {
         return NewBiomeGenBase.forest;
      } else if (oldBiomeGenBase.equals(OldBiomeGenBase.forest)) {
         return NewBiomeGenBase.forest;
      } else if (oldBiomeGenBase.equals(OldBiomeGenBase.savanna)) {
         return NewBiomeGenBase.savanna;
      } else if (oldBiomeGenBase.equals(OldBiomeGenBase.shrubland)) {
         return NewBiomeGenBase.mutatedJungleEdge;
      } else if (oldBiomeGenBase.equals(OldBiomeGenBase.taiga)) {
         return NewBiomeGenBase.taiga;
      } else if (oldBiomeGenBase.equals(OldBiomeGenBase.desert)) {
         return NewBiomeGenBase.desert;
      } else if (oldBiomeGenBase.equals(OldBiomeGenBase.plains)) {
         return NewBiomeGenBase.plains;
      } else if (oldBiomeGenBase.equals(OldBiomeGenBase.iceDesert)) {
         return NewBiomeGenBase.icePlains;
      } else if (oldBiomeGenBase.equals(OldBiomeGenBase.tundra)) {
         return NewBiomeGenBase.icePlains;
      } else if (oldBiomeGenBase.equals(OldBiomeGenBase.hell)) {
         return NewBiomeGenBase.hell;
      } else {
         return oldBiomeGenBase.equals(OldBiomeGenBase.sky) ? NewBiomeGenBase.sky : NewBiomeGenBase.plains;
      }
   }

   public double getTemperature(int i, int j) {
      this.temperature = this.temperatureNoise.generateNoiseOctaves(this.temperature, (double)i, (double)j, 1, 1, 0.025F, 0.025F, 0.5);
      return this.temperature[0];
   }

   public OldBiomeGenBase[] getBiomesForGeneration(int i, int j, int k, int l) {
      this.cachedBiomes = this.loadBlockGeneratorData(this.cachedBiomes, i, j, k, l);
      return this.cachedBiomes;
   }

   public double[] getTemperatures(double[] ad, int i, int j, int k, int l) {
      if (ad == null || ad.length < k * l) {
         ad = new double[k * l];
      }

      ad = this.temperatureNoise.generateNoiseOctaves(ad, (double)i, (double)j, k, l, 0.025F, 0.025F, 0.25);
      this.detailBuffer = this.detailNoise.generateNoiseOctaves(this.detailBuffer, (double)i, (double)j, k, l, 0.25, 0.25, 0.5882352941176471);
      int i1 = 0;

      for (int j1 = 0; j1 < k; j1++) {
         for (int k1 = 0; k1 < l; k1++) {
            double d = this.detailBuffer[i1] * 1.1 + 0.5;
            double d1 = 0.01;
            double d2 = 1.0 - d1;
            double d3 = (ad[i1] * 0.15 + 0.7) * d2 + d * d1;
            d3 = 1.0 - (1.0 - d3) * (1.0 - d3);
            if (d3 < 0.0) {
               d3 = 0.0;
            }

            if (d3 > 1.0) {
               d3 = 1.0;
            }

            ad[i1] = d3;
            i1++;
         }
      }

      return ad;
   }

   public OldBiomeGenBase[] loadBlockGeneratorData(OldBiomeGenBase[] abiomegenbase, int i, int j, int k, int l) {
      if (abiomegenbase == null || abiomegenbase.length < k * l) {
         abiomegenbase = new OldBiomeGenBase[k * l];
      }

      this.temperature = this.temperatureNoise.generateNoiseOctaves(this.temperature, (double)i, (double)j, k, k, 0.025F, 0.025F, 0.25);
      this.humidity = this.humidityNoise.generateNoiseOctaves(this.humidity, (double)i, (double)j, k, k, 0.05F, 0.05F, 0.3333333333333333);
      this.detailBuffer = this.detailNoise.generateNoiseOctaves(this.detailBuffer, (double)i, (double)j, k, k, 0.25, 0.25, 0.5882352941176471);
      int i1 = 0;

      for (int j1 = 0; j1 < k; j1++) {
         for (int k1 = 0; k1 < l; k1++) {
            double d = this.detailBuffer[i1] * 1.1 + 0.5;
            double d1 = 0.01;
            double d2 = 1.0 - d1;
            double d3 = (this.temperature[i1] * 0.15 + 0.7) * d2 + d * d1;
            d1 = 0.002;
            d2 = 1.0 - d1;
            double d4 = (this.humidity[i1] * 0.15 + 0.5) * d2 + d * d1;
            d3 = 1.0 - (1.0 - d3) * (1.0 - d3);
            if (d3 < 0.0) {
               d3 = 0.0;
            }

            if (d4 < 0.0) {
               d4 = 0.0;
            }

            if (d3 > 1.0) {
               d3 = 1.0;
            }

            if (d4 > 1.0) {
               d4 = 1.0;
            }

            this.temperature[i1] = d3;
            this.humidity[i1] = d4;
            abiomegenbase[i1++] = OldBiomeGenBase.getBiomeFromLookup(d3, d4);
         }
      }

      return abiomegenbase;
   }
}
