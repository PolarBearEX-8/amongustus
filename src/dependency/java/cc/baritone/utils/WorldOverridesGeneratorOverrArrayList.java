package cc.baritone.utils;

public class WorldOverridesGeneratorOverrArrayList extends java.util.ArrayList {
   public final HeuristicRegistrySplineCalculator heuristicRegistrySplineCalculator;

   public WorldOverridesGeneratorOverrArrayList(HeuristicRegistrySplineCalculator heuristicRegistrySplineCalculator2) {
      this.heuristicRegistrySplineCalculator = heuristicRegistrySplineCalculator2;
      this.add(
         new WdlGuiPreUpdateDispatcherHandler(
            this.heuristicRegistrySplineCalculator, "worldOverrides", new WdlGuiGuiScreenImpl(this.heuristicRegistrySplineCalculator.heuristicRegistry), true
         )
      );
      this.add(
         new WdlGuiPreUpdateDispatcherHandler(
            this.heuristicRegistrySplineCalculator,
            "generatorOverrides",
            new RandomSeedMapGeneratorGuiScreen(this.heuristicRegistrySplineCalculator.heuristicRegistry),
            true
         )
      );
      this.add(
         new WdlGuiPreUpdateDispatcherHandler(
            this.heuristicRegistrySplineCalculator, "playerOverrides", new RuntimeComponent5(this.heuristicRegistrySplineCalculator.heuristicRegistry), true
         )
      );
      this.add(
         new WdlGuiPreUpdateDispatcherHandler(
            this.heuristicRegistrySplineCalculator, "entityOptions", new UserEntityGuiScreen(this.heuristicRegistrySplineCalculator.heuristicRegistry), true
         )
      );
      this.add(
         new WdlGuiPreUpdateDispatcherHandler(
            this.heuristicRegistrySplineCalculator, "backupOptions", new BackupZIPGuiScreen(this.heuristicRegistrySplineCalculator.heuristicRegistry), true
         )
      );
      this.add(
         new WdlGuiPreUpdateDispatcherHandler(
            this.heuristicRegistrySplineCalculator,
            "messageOptions",
            new MessagesEnableAllGuiScreen(this.heuristicRegistrySplineCalculator.heuristicRegistry),
            false
         )
      );
      this.add(
         new WdlGuiPreUpdateDispatcherHandler(
            this.heuristicRegistrySplineCalculator, "permissionsInfo", new HeuristicProvider(this.heuristicRegistrySplineCalculator.heuristicRegistry), false
         )
      );
      this.add(
         new WdlGuiPreUpdateDispatcherHandler(
            this.heuristicRegistrySplineCalculator, "about", new DiagonalTracker(this.heuristicRegistrySplineCalculator.heuristicRegistry), false
         )
      );
      if (WorldDownloaderUpdateThread.isActive()) {
         this.add(
            0,
            new WdlGuiPreUpdateDispatcherHandler(
               this.heuristicRegistrySplineCalculator, "updates.hasNew", new RuntimeComponent7(this.heuristicRegistrySplineCalculator.heuristicRegistry), false
            )
         );
      } else {
         this.add(
            new WdlGuiPreUpdateDispatcherHandler(
               this.heuristicRegistrySplineCalculator, "updates", new RuntimeComponent7(this.heuristicRegistrySplineCalculator.heuristicRegistry), false
            )
         );
      }
   }
}
