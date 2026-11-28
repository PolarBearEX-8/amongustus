package net.minecraft.world.gen.layer;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderSettings;

public abstract class GenLayer {
   private long worldGenSeed;
   public GenLayer parent;
   private long chunkSeed;
   public long baseSeed;

   public void initChunkSeed(long l, long l2) {
      this.chunkSeed = this.worldGenSeed;
      this.chunkSeed = this.chunkSeed * (this.chunkSeed * 6364136223846793005L + 1442695040888963407L);
      this.chunkSeed += l;
      this.chunkSeed = this.chunkSeed * (this.chunkSeed * 6364136223846793005L + 1442695040888963407L);
      this.chunkSeed += l2;
      this.chunkSeed = this.chunkSeed * (this.chunkSeed * 6364136223846793005L + 1442695040888963407L);
      this.chunkSeed += l;
      this.chunkSeed = this.chunkSeed * (this.chunkSeed * 6364136223846793005L + 1442695040888963407L);
      this.chunkSeed += l2;
   }

   public static boolean isBiomeOceanic(int n) {
      return n == BiomeGenBase.ocean.biomeID || n == BiomeGenBase.deepOcean.biomeID || n == BiomeGenBase.frozenOcean.biomeID;
   }

   public int selectModeOrRandom(int n, int n2, int n3, int n4) {
      return n2 == n3 && n3 == n4
         ? n2
         : (
            n == n2 && n == n3
               ? n
               : (
                  n == n2 && n == n4
                     ? n
                     : (
                        n == n3 && n == n4
                           ? n
                           : (
                              n == n2 && n3 != n4
                                 ? n
                                 : (
                                    n == n3 && n2 != n4
                                       ? n
                                       : (
                                          n == n4 && n2 != n3
                                             ? n
                                             : (
                                                n2 == n3 && n != n4
                                                   ? n2
                                                   : (
                                                      n2 == n4 && n != n3
                                                         ? n2
                                                         : (n3 == n4 && n != n2 ? n3 : this.selectRandom(new int[]{n, n2, n3, n4}))
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
   }

   public int selectRandom(int[] ints) {
      return ints[this.nextInt(ints.length)];
   }

   public int nextInt(int n) {
      int n2 = (int)((this.chunkSeed >> 24) % (long)n);
      if (n2 < 0) {
         n2 += n;
      }

      this.chunkSeed = this.chunkSeed * (this.chunkSeed * 6364136223846793005L + 1442695040888963407L);
      this.chunkSeed = this.chunkSeed + this.worldGenSeed;
      return n2;
   }

   public GenLayer(long l) {
      this.baseSeed = l;
      this.baseSeed = this.baseSeed * (this.baseSeed * 6364136223846793005L + 1442695040888963407L);
      this.baseSeed += l;
      this.baseSeed = this.baseSeed * (this.baseSeed * 6364136223846793005L + 1442695040888963407L);
      this.baseSeed += l;
      this.baseSeed = this.baseSeed * (this.baseSeed * 6364136223846793005L + 1442695040888963407L);
      this.baseSeed += l;
   }

   public static GenLayer[] initializeAllBiomeGenerators(long l, WorldType worldType, String str) {
      GenLayerIsland genLayerIsland = new GenLayerIsland(1L);
      GenLayerFuzzyZoom genLayerFuzzyZoom = new GenLayerFuzzyZoom(2000L, genLayerIsland);
      GenLayerAddIsland genLayerAddIsland = new GenLayerAddIsland(1L, genLayerFuzzyZoom);
      GenLayerZoom genLayerZoom = new GenLayerZoom(2001L, genLayerAddIsland);
      GenLayerAddIsland genLayerAddIsland2 = new GenLayerAddIsland(2L, genLayerZoom);
      genLayerAddIsland2 = new GenLayerAddIsland(50L, genLayerAddIsland2);
      genLayerAddIsland2 = new GenLayerAddIsland(70L, genLayerAddIsland2);
      GenLayerRemoveTooMuchOcean ocean = new GenLayerRemoveTooMuchOcean(2L, genLayerAddIsland2);
      GenLayerAddSnow genLayerAddSnow = new GenLayerAddSnow(2L, ocean);
      GenLayerAddIsland genLayerAddIsland3 = new GenLayerAddIsland(3L, genLayerAddSnow);
      GenLayerEdge genLayerEdge = new GenLayerEdge(2L, genLayerAddIsland3, GenLayerEdge.Mode.COOL_WARM);
      genLayerEdge = new GenLayerEdge(2L, genLayerEdge, GenLayerEdge.Mode.HEAT_ICE);
      genLayerEdge = new GenLayerEdge(3L, genLayerEdge, GenLayerEdge.Mode.SPECIAL);
      GenLayerZoom genLayerZoom2 = new GenLayerZoom(2002L, genLayerEdge);
      genLayerZoom2 = new GenLayerZoom(2003L, genLayerZoom2);
      GenLayerAddIsland genLayerAddIsland4 = new GenLayerAddIsland(4L, genLayerZoom2);
      GenLayerAddMushroomIsland island = new GenLayerAddMushroomIsland(5L, genLayerAddIsland4);
      GenLayer genLayer = GenLayerZoom.magnify(1000L, new GenLayerDeepOcean(4L, island), 0);
      ChunkProviderSettings chunkProviderSettings = null;
      int n = 4;
      int n2 = n;
      if (worldType == WorldType.CUSTOMIZED && str.length() > 0) {
         chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(str).build();
         n = chunkProviderSettings.biomeSize;
         n2 = chunkProviderSettings.riverSize;
      }

      if (worldType == WorldType.LARGE_BIOMES) {
         n = 6;
      }

      GenLayer genLayer2 = GenLayerZoom.magnify(1000L, genLayer, 0);
      GenLayerRiverInit genLayerRiverInit = new GenLayerRiverInit(100L, genLayer2);
      GenLayer genLayer3 = GenLayerZoom.magnify(1000L, new GenLayerBiome(200L, genLayer, worldType, str), 2);
      GenLayerBiomeEdge genLayerBiomeEdge = new GenLayerBiomeEdge(1000L, genLayer3);
      GenLayer genLayer4 = GenLayerZoom.magnify(1000L, genLayerRiverInit, 2);
      GenLayer genLayer5 = new GenLayerHills(1000L, genLayerBiomeEdge, genLayer4);
      GenLayer genLayer6 = GenLayerZoom.magnify(1000L, genLayerRiverInit, 2);
      genLayer6 = GenLayerZoom.magnify(1000L, genLayer6, n2);
      GenLayerRiver genLayerRiver = new GenLayerRiver(1L, genLayer6);
      GenLayerSmooth genLayerSmooth = new GenLayerSmooth(1000L, genLayerRiver);
      genLayer5 = new GenLayerRareBiome(1001L, genLayer5);

      for (int i = 0; i < n; i++) {
         genLayer5 = new GenLayerZoom((long)(1000 + i), genLayer5);
         if (i == 0) {
            genLayer5 = new GenLayerAddIsland(3L, genLayer5);
         }

         if (i == 1 || n == 1) {
            genLayer5 = new GenLayerShore(1000L, genLayer5);
         }
      }

      GenLayerSmooth genLayerSmooth2 = new GenLayerSmooth(1000L, genLayer5);
      GenLayerRiverMix genLayerRiverMix = new GenLayerRiverMix(100L, genLayerSmooth2, genLayerSmooth);
      GenLayerVoronoiZoom genLayerVoronoiZoom = new GenLayerVoronoiZoom(10L, genLayerRiverMix);
      genLayerRiverMix.initWorldGenSeed(l);
      genLayerVoronoiZoom.initWorldGenSeed(l);
      return new GenLayer[]{genLayerRiverMix, genLayerVoronoiZoom, genLayerRiverMix};
   }

   public abstract int[] getInts(int n, int n2, int n3, int n4);

   public void initWorldGenSeed(long l) {
      this.worldGenSeed = l;
      if (this.parent != null) {
         this.parent.initWorldGenSeed(l);
      }

      this.worldGenSeed = this.worldGenSeed * (this.worldGenSeed * 6364136223846793005L + 1442695040888963407L);
      this.worldGenSeed = this.worldGenSeed + this.baseSeed;
      this.worldGenSeed = this.worldGenSeed * (this.worldGenSeed * 6364136223846793005L + 1442695040888963407L);
      this.worldGenSeed = this.worldGenSeed + this.baseSeed;
      this.worldGenSeed = this.worldGenSeed * (this.worldGenSeed * 6364136223846793005L + 1442695040888963407L);
      this.worldGenSeed = this.worldGenSeed + this.baseSeed;
   }

   public static boolean biomesEqualOrMesaPlateau(int n, int n2) {
      if (n == n2) {
         return true;
      } else if (n != BiomeGenBase.mesaPlateau_F.biomeID && n != BiomeGenBase.mesaPlateau.biomeID) {
         BiomeGenBase biome = BiomeGenBase.getBiome(n);
         BiomeGenBase biome2 = BiomeGenBase.getBiome(n2);

         try {
            return biome != null && biome2 != null ? biome.isEqualTo(biome2) : false;
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Comparing biomes");
            CrashReportCategory crashReportCategory = crashReport.makeCategory("Biomes being compared");
            crashReportCategory.addCrashSection("Biome A ID", n);
            crashReportCategory.addCrashSection("Biome B ID", n2);
            crashReportCategory.addCrashSectionCallable("Biome A", new GenLayer.ValueCallable(biome));
            crashReportCategory.addCrashSectionCallable("Biome B", new GenLayer.ValueCallable2(biome2));
            throw new ReportedException(crashReport);
         }
      } else {
         return n2 == BiomeGenBase.mesaPlateau_F.biomeID || n2 == BiomeGenBase.mesaPlateau.biomeID;
      }
   }


   public static final class ValueCallable implements Callable {
      public final BiomeGenBase biome;

      public ValueCallable(BiomeGenBase biomeGenBase) {
         this.biome = biomeGenBase;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public String callAsString() throws Exception {
         return String.valueOf(this.biome);
      }
   }

   public static final class ValueCallable2 implements Callable {
      public final BiomeGenBase biome;

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public String callAsString() throws Exception {
         return String.valueOf(this.biome);
      }

      public ValueCallable2(BiomeGenBase biomeGenBase) {
         this.biome = biomeGenBase;
      }
   }
}
