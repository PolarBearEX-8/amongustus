package net.minecraft.world.biome;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WorldChunkManager {
   private GenLayer genBiomes;
   private GenLayer biomeIndexLayer;
   private BiomeCache biomeCache = new BiomeCache(this);
   private List biomesToSpawnIn;
   private String generatorOptions = "";

   public WorldChunkManager(World world) {
      this(world.getSeed(), world.getWorldInfo().getTerrainType(), world.getWorldInfo().getGeneratorOptions());
   }

   public BlockPos findBiomePosition(int n, int n2, int n3, List list, Random rand) {
      IntCache.resetIntCache();
      int n4 = n - n3 >> 2;
      int n5 = n2 - n3 >> 2;
      int n6 = n + n3 >> 2;
      int n7 = n2 + n3 >> 2;
      int n8 = n6 - n4 + 1;
      int n9 = n7 - n5 + 1;
      int[] ints = this.genBiomes.getInts(n4, n5, n8, n9);
      BlockPos pos = null;
      int n10 = 0;

      for (int i = 0; i < n8 * n9; i++) {
         int n11 = n4 + i % n8 << 2;
         int n12 = n5 + i / n8 << 2;
         if (list.contains(BiomeGenBase.getBiome(ints[i])) && (pos == null || rand.nextInt(n10 + 1) == 0)) {
            pos = new BlockPos(n11, 0, n12);
            n10++;
         }
      }

      return pos;
   }

   public WorldChunkManager(long l, WorldType worldType, String str) {
      this();
      this.generatorOptions = str;
      GenLayer[] genLayers = GenLayer.initializeAllBiomeGenerators(l, worldType, str);
      this.genBiomes = genLayers[0];
      this.biomeIndexLayer = genLayers[1];
   }

   public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] biomeGenBases, int n, int n2, int n3, int n4) {
      IntCache.resetIntCache();
      if (biomeGenBases == null || biomeGenBases.length < n3 * n4) {
         biomeGenBases = new BiomeGenBase[n3 * n4];
      }

      int[] ints = this.genBiomes.getInts(n, n2, n3, n4);

      try {
         for (int i = 0; i < n3 * n4; i++) {
            biomeGenBases[i] = BiomeGenBase.getBiomeFromBiomeList(ints[i], BiomeGenBase.DEFAULT);
         }

         return biomeGenBases;
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Invalid Biome id");
         CrashReportCategory crashReportCategory = crashReport.makeCategory("RawBiomeBlock");
         crashReportCategory.addCrashSection("biomes[] size", biomeGenBases.length);
         crashReportCategory.addCrashSection("x", n);
         crashReportCategory.addCrashSection("z", n2);
         crashReportCategory.addCrashSection("w", n3);
         crashReportCategory.addCrashSection("h", n4);
         throw new ReportedException(crashReport);
      }
   }

   public void cleanupCache() {
      this.biomeCache.cleanupCache();
   }

   public BiomeGenBase getBiome(int n, int n2, BiomeGenBase biomeGenBase) {
      return this.biomeCache.getBiome(n, n2, biomeGenBase);
   }

   public List getBiomesToSpawnIn() {
      return this.biomesToSpawnIn;
   }

   public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] biomeGenBases, int n, int n2, int n3, int n4, boolean flag) {
      IntCache.resetIntCache();
      if (biomeGenBases == null || biomeGenBases.length < n3 * n4) {
         biomeGenBases = new BiomeGenBase[n3 * n4];
      }

      if (flag && n3 == 16 && n4 == 16 && (n & 15) == 0 && (n2 & 15) == 0) {
         System.arraycopy(this.biomeCache.getCachedBiomes(n, n2), 0, biomeGenBases, 0, n3 * n4);
         return biomeGenBases;
      } else {
         int[] ints = this.biomeIndexLayer.getInts(n, n2, n3, n4);

         for (int i = 0; i < n3 * n4; i++) {
            biomeGenBases[i] = BiomeGenBase.getBiomeFromBiomeList(ints[i], BiomeGenBase.DEFAULT);
         }

         return biomeGenBases;
      }
   }

   public BiomeGenBase getBiomeGenerator(BlockPos pos) {
      return this.getBiome(pos.getX(), pos.getZ(), (BiomeGenBase)null);
   }

   public boolean areBiomesViable(int n, int n2, int n3, List list) {
      IntCache.resetIntCache();
      int n4 = n - n3 >> 2;
      int n5 = n2 - n3 >> 2;
      int n6 = n + n3 >> 2;
      int n7 = n2 + n3 >> 2;
      int n8 = n6 - n4 + 1;
      int n9 = n7 - n5 + 1;
      int[] ints = this.genBiomes.getInts(n4, n5, n8, n9);

      try {
         for (int i = 0; i < n8 * n9; i++) {
            if (!list.contains(BiomeGenBase.getBiome(ints[i]))) {
               return false;
            }
         }

         return true;
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Invalid Biome id");
         CrashReportCategory crashReportCategory = crashReport.makeCategory("Layer");
         crashReportCategory.addCrashSection("Layer", this.genBiomes.toString());
         crashReportCategory.addCrashSection("x", n);
         crashReportCategory.addCrashSection("z", n2);
         crashReportCategory.addCrashSection("radius", n3);
         crashReportCategory.addCrashSection("allowed", list);
         throw new ReportedException(crashReport);
      }
   }

   public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] biomeGenBases, int n, int n2, int n3, int n4) {
      return this.getBiomeGenAt(biomeGenBases, n, n2, n3, n4, true);
   }

   public float getTemperatureAtHeight(float f, int n) {
      return f;
   }

   public WorldChunkManager() {
      this.biomesToSpawnIn = Lists.newArrayList();
      this.biomesToSpawnIn.add(BiomeGenBase.forest);
      this.biomesToSpawnIn.add(BiomeGenBase.plains);
      this.biomesToSpawnIn.add(BiomeGenBase.taiga);
      this.biomesToSpawnIn.add(BiomeGenBase.taigaHills);
      this.biomesToSpawnIn.add(BiomeGenBase.forestHills);
      this.biomesToSpawnIn.add(BiomeGenBase.jungle);
      this.biomesToSpawnIn.add(BiomeGenBase.jungleHills);
   }

   public float[] getRainfall(float[] floats, int n, int n2, int n3, int n4) {
      IntCache.resetIntCache();
      if (floats == null || floats.length < n3 * n4) {
         floats = new float[n3 * n4];
      }

      int[] ints = this.biomeIndexLayer.getInts(n, n2, n3, n4);

      for (int i = 0; i < n3 * n4; i++) {
         try {
            float f = (float)BiomeGenBase.getBiomeFromBiomeList(ints[i], BiomeGenBase.DEFAULT).getIntRainfall() / 65536.0F;
            if (f > 1.0F) {
               f = 1.0F;
            }

            floats[i] = f;
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Invalid Biome id");
            CrashReportCategory crashReportCategory = crashReport.makeCategory("DownfallBlock");
            crashReportCategory.addCrashSection("biome id", i);
            crashReportCategory.addCrashSection("downfalls[] size", floats.length);
            crashReportCategory.addCrashSection("x", n);
            crashReportCategory.addCrashSection("z", n2);
            crashReportCategory.addCrashSection("w", n3);
            crashReportCategory.addCrashSection("h", n4);
            throw new ReportedException(crashReport);
         }
      }

      return floats;
   }
}
