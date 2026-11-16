package client.util;

import client.setting.CombatLimitProperty;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.item.DiamondArtifact;

public abstract class ComparingBiomesManager {
   public long timestamp2;
   public ComparingBiomesManager comparingBiomesManager;
   public long timestamp;
   public long timestamp3;

   public int computeCount(int[] countArray) {
      return countArray[this.computeCount(countArray.length)];
   }

   public static boolean checkIndex(int count) {
      return count == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager28.count
         || count == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager39.count
         || count == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager26.count;
   }

   public static boolean checkIndex(int count, int count2) {
      if (count == count2) {
         return true;
      } else if (count != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager9.count
         && count != BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager30.count) {
         BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(count);
         BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager2 = BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager2(count2);

         try {
            return biomeIDIsOutOfBoundsManager != null && biomeIDIsOutOfBoundsManager2 != null ? biomeIDIsOutOfBoundsManager.matchesBiomeIDIsOutOfBoundsManage(biomeIDIsOutOfBoundsManager2) : false;
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Comparing biomes");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Biomes being compared");
            crashReportCategory.add("Biome A ID", count);
            crashReportCategory.add("Biome B ID", count2);
            crashReportCategory.applyText("Biome A", new RotationTracker(biomeIDIsOutOfBoundsManager));
            crashReportCategory.applyText("Biome B", new CurveWatcher(biomeIDIsOutOfBoundsManager2));
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      } else {
         return count2 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager9.count
            || count2 == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager30.count;
      }
   }

   public int computeCount(int count) {
      int count2 = (int)((this.timestamp >> 24) % (long)count);
      if (count2 < 0) {
         count2 += count;
      }

      this.timestamp = this.timestamp * (this.timestamp * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL);
      this.timestamp = this.timestamp + this.timestamp2;
      return count2;
   }

   public void applyTime(long value) {
      this.timestamp2 = value;
      if (this.comparingBiomesManager != null) {
         this.comparingBiomesManager.applyTime(value);
      }

      this.timestamp2 = this.timestamp2 * (this.timestamp2 * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL);
      this.timestamp2 = this.timestamp2 + this.timestamp3;
      this.timestamp2 = this.timestamp2 * (this.timestamp2 * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL);
      this.timestamp2 = this.timestamp2 + this.timestamp3;
      this.timestamp2 = this.timestamp2 * (this.timestamp2 * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL);
      this.timestamp2 = this.timestamp2 + this.timestamp3;
   }

   public abstract int[] resolveIndexArray(int count, int count2, int count3, int count4);

   public static ComparingBiomesManager[] resolveComparingBiomesManagerArray(long value, InfoManager infoManager, String text) {
      SecondaryComparingBiomesManagerAdapter secondaryComparingBiomesManagerAdapter = new SecondaryComparingBiomesManagerAdapter(1L);
      ClientManagerRegistry clientManagerRegistry = new ClientManagerRegistry(2000L, secondaryComparingBiomesManagerAdapter);
      InterpolationProfiler interpolationProfiler = new InterpolationProfiler(1L, clientManagerRegistry);
      VectorDirector vectorDirector = new VectorDirector(2001L, interpolationProfiler);
      InterpolationProfiler interpolationProfiler2 = new InterpolationProfiler(2L, vectorDirector);
      InterpolationProfiler interpolationProfiler3 = new InterpolationProfiler(50L, interpolationProfiler2);
      InterpolationProfiler interpolationProfiler4 = new InterpolationProfiler(70L, interpolationProfiler3);
      VectorSystem vectorSystem = new VectorSystem(2L, interpolationProfiler4);
      ComparingBiomesManagerAdapter comparingBiomesManagerAdapter = new ComparingBiomesManagerAdapter(2L, vectorSystem);
      InterpolationProfiler interpolationProfiler5 = new InterpolationProfiler(3L, comparingBiomesManagerAdapter);
      TertiaryComparingBiomesManagerAdapter tertiaryComparingBiomesManagerAdapter = new TertiaryComparingBiomesManagerAdapter(2L, interpolationProfiler5, CombatLimitProperty.COOL_WARM);
      TertiaryComparingBiomesManagerAdapter tertiaryComparingBiomesManagerAdapter2 = new TertiaryComparingBiomesManagerAdapter(2L, tertiaryComparingBiomesManagerAdapter, CombatLimitProperty.HEAT_ICE);
      TertiaryComparingBiomesManagerAdapter tertiaryComparingBiomesManagerAdapter3 = new TertiaryComparingBiomesManagerAdapter(3L, tertiaryComparingBiomesManagerAdapter2, CombatLimitProperty.SPECIAL);
      VectorDirector vectorDirector2 = new VectorDirector(2002L, tertiaryComparingBiomesManagerAdapter3);
      VectorDirector vectorDirector3 = new VectorDirector(2003L, vectorDirector2);
      InterpolationProfiler interpolationProfiler6 = new InterpolationProfiler(4L, vectorDirector3);
      ExtendedComparingBiomesManagerAdapter extendedComparingBiomesManagerAdapter = new ExtendedComparingBiomesManagerAdapter(5L, interpolationProfiler6);
      InterpolationMonitor interpolationMonitor = new InterpolationMonitor(4L, extendedComparingBiomesManagerAdapter);
      ComparingBiomesManager comparingBiomesManager2 = VectorDirector.resolveComparingBiomesManager(1000L, interpolationMonitor, 0);
      int count = 4;
      int count2 = 4;
      if (infoManager == InfoManager.infoManager3 && text.length() > 0) {
         GlobalFloatEntry globalFloatEntry = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry(text).getGlobalFloatEntry();
         count = globalFloatEntry.count6;
         count2 = globalFloatEntry.count26;
      }

      if (infoManager == InfoManager.infoManager5) {
         count = 6;
      }

      ComparingBiomesManager comparingBiomesManager3 = VectorDirector.resolveComparingBiomesManager(1000L, comparingBiomesManager2, 0);
      VectorScheduler vectorScheduler = new VectorScheduler(100L, comparingBiomesManager3);
      BiomeIDIsOutOfBoundsManagerArrayEntry biomeIDIsOutOfBoundsManagerArrayEntry = new BiomeIDIsOutOfBoundsManagerArrayEntry(200L, comparingBiomesManager2, infoManager, text);
      ComparingBiomesManager comparingBiomesManager4 = VectorDirector.resolveComparingBiomesManager(1000L, biomeIDIsOutOfBoundsManagerArrayEntry, 2);
      AuxiliaryComparingBiomesManagerAdapter auxiliaryComparingBiomesManagerAdapter = new AuxiliaryComparingBiomesManagerAdapter(1000L, comparingBiomesManager4);
      ComparingBiomesManager comparingBiomesManager5 = VectorDirector.resolveComparingBiomesManager(1000L, vectorScheduler, 2);
      InternalComparingBiomesManagerAdapter internalComparingBiomesManagerAdapter = new InternalComparingBiomesManagerAdapter(1000L, auxiliaryComparingBiomesManagerAdapter, comparingBiomesManager5);
      ComparingBiomesManager comparingBiomesManager6 = VectorDirector.resolveComparingBiomesManager(1000L, vectorScheduler, 2);
      ComparingBiomesManager comparingBiomesManager7 = VectorDirector.resolveComparingBiomesManager(1000L, comparingBiomesManager6, count2);
      VectorCoordinator vectorCoordinator = new VectorCoordinator(1L, comparingBiomesManager7);
      VectorSynchronizer vectorSynchronizer = new VectorSynchronizer(1000L, vectorCoordinator);
      Object vectorEngine = new VectorEngine(1001L, internalComparingBiomesManagerAdapter);

      for (int index = 0; index < count; index++) {
         vectorEngine = new VectorDirector((long)(1000 + index), (ComparingBiomesManager)vectorEngine);
         if (index == 0) {
            vectorEngine = new InterpolationProfiler(3L, (ComparingBiomesManager)vectorEngine);
         }

         if (index == 1 || count == 1) {
            vectorEngine = new VectorService(1000L, (ComparingBiomesManager)vectorEngine);
         }
      }

      VectorSynchronizer vectorSynchronizer2 = new VectorSynchronizer(1000L, (ComparingBiomesManager)vectorEngine);
      DiamondArtifact diamondArtifact = new DiamondArtifact(100L, vectorSynchronizer2, vectorSynchronizer);
      VectorProvider vectorProvider = new VectorProvider(10L, diamondArtifact);
      diamondArtifact.applyTime(value);
      vectorProvider.applyTime(value);
      return new ComparingBiomesManager[]{diamondArtifact, vectorProvider, diamondArtifact};
   }

   public int computeCount(int count, int count2, int count3, int count4) {
      return count2 == count3 && count3 == count4
         ? count2
         : (
            count == count2 && count == count3
               ? count
               : (
                  count == count2 && count == count4
                     ? count
                     : (
                        count == count3 && count == count4
                           ? count
                           : (
                              count == count2 && count3 != count4
                                 ? count
                                 : (
                                    count == count3 && count2 != count4
                                       ? count
                                       : (
                                          count == count4 && count2 != count3
                                             ? count
                                             : (
                                                count2 == count3 && count != count4
                                                   ? count2
                                                   : (
                                                      count2 == count4 && count != count3
                                                         ? count2
                                                         : (count3 == count4 && count != count2 ? count3 : this.computeCount(new int[]{count, count2, count3, count4}))
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
   }

   public void applyTime(long value, long value2) {
      this.timestamp = this.timestamp2;
      this.timestamp = this.timestamp * (this.timestamp * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL);
      this.timestamp += value;
      this.timestamp = this.timestamp * (this.timestamp * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL);
      this.timestamp += value2;
      this.timestamp = this.timestamp * (this.timestamp * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL);
      this.timestamp += value;
      this.timestamp = this.timestamp * (this.timestamp * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL);
      this.timestamp += value2;
   }

   public ComparingBiomesManager(long value) {
      this.timestamp3 = value;
      this.timestamp3 = this.timestamp3 * (this.timestamp3 * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL);
      this.timestamp3 += value;
      this.timestamp3 = this.timestamp3 * (this.timestamp3 * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL);
      this.timestamp3 += value;
      this.timestamp3 = this.timestamp3 * (this.timestamp3 * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL);
      this.timestamp3 += value;
   }
}
