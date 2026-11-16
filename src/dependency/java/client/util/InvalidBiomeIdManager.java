package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class InvalidBiomeIdManager {
   public ComparingBiomesManager comparingBiomesManager2;
   public ComparingBiomesManager comparingBiomesManager;
   public AuxiliaryInvalidBiomeIdManagerEntry auxiliaryInvalidBiomeIdManagerEntry = new AuxiliaryInvalidBiomeIdManagerEntry(this);
   public List list;
   public String labelText = "";

   public BlockPos resolveBlockPos(int count, int count2, int count3, List list2, Random random) {
      CacheManager.updateState();
      int count4 = count - count3 >> 2;
      int count5 = count2 - count3 >> 2;
      int count6 = count + count3 >> 2;
      int count7 = count2 + count3 >> 2;
      int count8 = count6 - count4 + 1;
      int count9 = count7 - count5 + 1;
      int[] countArray = this.comparingBiomesManager2.resolveIndexArray(count4, count5, count8, count9);
      BlockPos blockPos = null;
      int count10 = 0;

      for (int index = 0; index < count8 * count9; index++) {
         int count11 = count4 + index % count8 << 2;
         int count12 = count5 + index / count8 << 2;
         BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(countArray[index]);
         if (list2.contains(biomeIDIsOutOfBoundsManager) && (blockPos == null || random.nextInt(count10 + 1) == 0)) {
            blockPos = new BlockPos(count11, 0, count12);
            count10++;
         }
      }

      return blockPos;
   }

   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(BlockPos blockPos) {
      return this.resolveBiomeIDIsOutOfBoundsManager(blockPos.getX(), blockPos.getZ(), (BiomeIDIsOutOfBoundsManager)null);
   }

   public BiomeIDIsOutOfBoundsManager[] resolveBiomeIDIsOutOfBoundsManagerA(BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray, int count, int count2, int count3, int count4) {
      BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray2 = biomeIDIsOutOfBoundsManagerArray;
      CacheManager.updateState();
      if (biomeIDIsOutOfBoundsManagerArray == null || biomeIDIsOutOfBoundsManagerArray.length < count3 * count4) {
         biomeIDIsOutOfBoundsManagerArray2 = new BiomeIDIsOutOfBoundsManager[count3 * count4];
      }

      int[] countArray = this.comparingBiomesManager2.resolveIndexArray(count, count2, count3, count4);

      try {
         for (int index = 0; index < count3 * count4; index++) {
            biomeIDIsOutOfBoundsManagerArray2[index] = BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager(countArray[index], BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager10);
         }

         return biomeIDIsOutOfBoundsManagerArray2;
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Invalid Biome id");
         CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("RawBiomeBlock");
         crashReportCategory.add("biomes[] size", biomeIDIsOutOfBoundsManagerArray2.length);
         crashReportCategory.add("x", count);
         crashReportCategory.add("z", count2);
         crashReportCategory.add("w", count3);
         crashReportCategory.add("h", count4);
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }
   }

   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(int count, int count2, BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager) {
      return this.auxiliaryInvalidBiomeIdManagerEntry.resolveBiomeIDIsOutOfBoundsManager(count, count2, biomeIDIsOutOfBoundsManager);
   }

   public float[] resolveFloatArray(float[] factorArray, int count, int count2, int count3, int count4) {
      float[] factorArray2 = factorArray;
      CacheManager.updateState();
      if (factorArray == null || factorArray.length < count3 * count4) {
         factorArray2 = new float[count3 * count4];
      }

      int[] countArray = this.comparingBiomesManager.resolveIndexArray(count, count2, count3, count4);

      for (int index = 0; index < count3 * count4; index++) {
         try {
            float factor = (float)BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager(
                     countArray[index], BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager10
                  )
                  .getCount()
               / 65536.0F;
            if (factor > 1.0F) {
               factor = 1.0F;
            }

            factorArray2[index] = factor;
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("DownfallBlock");
            crashReportCategory.add("biome id", index);
            crashReportCategory.add("downfalls[] size", factorArray2.length);
            crashReportCategory.add("x", count);
            crashReportCategory.add("z", count2);
            crashReportCategory.add("w", count3);
            crashReportCategory.add("h", count4);
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      }

      return factorArray2;
   }

   public BiomeIDIsOutOfBoundsManager[] resolveBiomeIDIsOutOfBoundsManagerA2(BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray, int count, int count2, int count3, int count4) {
      return this.resolveBiomeIDIsOutOfBoundsManagerA(biomeIDIsOutOfBoundsManagerArray, count, count2, count3, count4, true);
   }

   public List getList() {
      return this.list;
   }

   public void updateState() {
      this.auxiliaryInvalidBiomeIdManagerEntry.updateState();
   }

   public float computeFactor(float factor, int count) {
      return factor;
   }

   public BiomeIDIsOutOfBoundsManager[] resolveBiomeIDIsOutOfBoundsManagerA(
      BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray, int count, int count2, int count3, int count4, boolean flag
   ) {
      BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray2 = biomeIDIsOutOfBoundsManagerArray;
      CacheManager.updateState();
      if (biomeIDIsOutOfBoundsManagerArray == null || biomeIDIsOutOfBoundsManagerArray.length < count3 * count4) {
         biomeIDIsOutOfBoundsManagerArray2 = new BiomeIDIsOutOfBoundsManager[count3 * count4];
      }

      if (flag && count3 == 16 && count4 == 16 && (count & 15) == 0 && (count2 & 15) == 0) {
         System.arraycopy(this.auxiliaryInvalidBiomeIdManagerEntry.resolveBiomeIDIsOutOfBoundsManagerA(count, count2), 0, biomeIDIsOutOfBoundsManagerArray2, 0, count3 * count4);
         return biomeIDIsOutOfBoundsManagerArray2;
      } else {
         int[] countArray = this.comparingBiomesManager.resolveIndexArray(count, count2, count3, count4);

         for (int index = 0; index < count3 * count4; index++) {
            biomeIDIsOutOfBoundsManagerArray2[index] = BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager(countArray[index], BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager10);
         }

         return biomeIDIsOutOfBoundsManagerArray2;
      }
   }

   public boolean contains(int count, int count2, int count3, List list2) {
      CacheManager.updateState();
      int count4 = count - count3 >> 2;
      int count5 = count2 - count3 >> 2;
      int count6 = count + count3 >> 2;
      int count7 = count2 + count3 >> 2;
      int count8 = count6 - count4 + 1;
      int count9 = count7 - count5 + 1;
      int[] countArray = this.comparingBiomesManager2.resolveIndexArray(count4, count5, count8, count9);

      try {
         for (int index = 0; index < count8 * count9; index++) {
            BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(countArray[index]);
            if (!list2.contains(biomeIDIsOutOfBoundsManager)) {
               return false;
            }
         }

         return true;
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Invalid Biome id");
         CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Layer");
         crashReportCategory.add("Layer", this.comparingBiomesManager2.toString());
         crashReportCategory.add("x", count);
         crashReportCategory.add("z", count2);
         crashReportCategory.add("radius", count3);
         crashReportCategory.add("allowed", list2);
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }
   }

   public InvalidBiomeIdManager(World world) {
      this(world.getTimestamp(), world.getRandomSeedManager().getInfoManager(), world.getRandomSeedManager().getLabelText2());
   }

   public InvalidBiomeIdManager() {
      this.list = Lists.newArrayList();
      this.list.add(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager4);
      this.list.add(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35);
      this.list.add(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager15);
      this.list.add(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager31);
      this.list.add(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager25);
      this.list.add(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager23);
      this.list.add(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager40);
   }

   public InvalidBiomeIdManager(long value, InfoManager infoManager, String text) {
      this();
      this.labelText = text;
      ComparingBiomesManager[] comparingBiomesManagerArray = ComparingBiomesManager.resolveComparingBiomesManagerArray(value, infoManager, text);
      this.comparingBiomesManager2 = comparingBiomesManagerArray[0];
      this.comparingBiomesManager = comparingBiomesManagerArray[1];
   }
}
