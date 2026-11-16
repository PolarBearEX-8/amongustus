package cc.baritone.utils;

import cc.baritone.api.InternalItemEntry;
import client.util.AlertNotificationSound;
import client.util.IBlockState;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.world.World;

public final class ListenerDispatcher implements CompositeListener, DRNHandler {
   public final BaritoneManager baritoneManager;
   public final List<SimpleListener> list = new CopyOnWriteArrayList<>();

   @Override
   public void applyBreadthFirstProvider(BreadthFirstProvider breadthFirstProvider) {
      if ((Boolean)BaritoneManager.getSettings().repackOnAnyBlockChange.dataRef
         && breadthFirstProvider.getList().stream().map(o -> ((DepthFirstRegistry)o).getDataRef2()).map(o -> ((IBlockState)o).getBlock()).anyMatch(o -> BreadthFirstWatcher.immutableSet.contains(o))) {
         this.baritoneManager.getReadmeTxtManager().applyConsumer(o -> this.applyBreadthFirstProvider(breadthFirstProvider, (NativeListener)o));
      }

      this.list.forEach(l -> applyBreadthFirstProvider(breadthFirstProvider, l));
   }

   @Override
   public void forEach(AStarWatcher aStarWatcher) {
      this.list.forEach(l -> applyAStarWatcher(aStarWatcher, l));
   }

   @Override
   public final void forEach(HeuristicInspector heuristicInspector) {
      this.list.forEach(l -> applyHeuristicInspector(heuristicInspector, l));
   }

   @Override
   public void forEach(AStarAuditor aStarAuditor) {
      this.list.forEach(l -> applyAStarAuditor(aStarAuditor, l));
   }

   @Override
   public final void forEach(HeuristicProcessor heuristicProcessor) {
      this.list.forEach(l -> applyHeuristicProcessorByHeuristicProcess(heuristicProcessor, l));
   }

   private static void applySecondaryCustom(SecondaryCustom secondaryCustom, SimpleListener simpleListener) {
      simpleListener.forEach(secondaryCustom);
   }

   private static void applyHeuristicProcessor(HeuristicProcessor heuristicProcessor, SimpleListener simpleListener) {
      simpleListener.forEachByHeuristicProcess(heuristicProcessor);
   }

   @Override
   public void forEach(SecondaryCustom secondaryCustom) {
      this.list.forEach(l -> applySecondaryCustom(secondaryCustom, l));
   }

   private static void applyInternalItemEntry(InternalItemEntry internalItemEntry, SimpleListener simpleListener) {
      simpleListener.forEach(internalItemEntry);
   }

   private static void applySupplementalCustom(SupplementalCustom supplementalCustom, SimpleListener simpleListener) {
      simpleListener.applySupplementalCustom(supplementalCustom);
   }

   @Override
   public final void forEachByHeuristicProcess(HeuristicProcessor heuristicProcessor) {
      this.list.forEach(l -> applyHeuristicProcessor(heuristicProcessor, l));
   }

   @Override
   public final void applySupplementalCustom(SupplementalCustom supplementalCustom) {
      ReadmeTxtManager readmeTxtManager = this.baritoneManager.getReadmeTxtManager();
      if (supplementalCustom.getSecondaryOptionType() == SecondaryOptionType.POST) {
         readmeTxtManager.updateState();
         if (supplementalCustom.getWorldClient() != null) {
            readmeTxtManager.applyWorldByWorld(supplementalCustom.getWorldClient());
         }
      }

      this.list.forEach(l -> applySupplementalCustom(supplementalCustom, l));
   }

   @Override
   public void forEach(ActiveCustom activeCustom) {
      this.list.forEach(l -> applyActiveCustom(activeCustom, l));
   }

   @Override
   public final void forEach(AStarCalculator aStarCalculator) {
      this.list.forEach(l -> applyAStarCalculator(aStarCalculator, l));
   }

   private static void applyInternalItemEntryByInternalItemEntr(InternalItemEntry internalItemEntry, SimpleListener simpleListener) {
      simpleListener.applyInternalItemEntry(internalItemEntry);
   }

   private static void applyAStarAuditor(AStarAuditor aStarAuditor, SimpleListener simpleListener) {
      simpleListener.forEach(aStarAuditor);
   }

   private static void applyAStarResolver(AStarResolver aStarResolver, SimpleListener simpleListener) {
      simpleListener.applyAStarResolver(aStarResolver);
   }

   private static void applyActiveCustom(ActiveCustom activeCustom, SimpleListener simpleListener) {
      simpleListener.forEach(activeCustom);
   }

   private static void applyAStarWatcher(AStarWatcher aStarWatcher, SimpleListener simpleListener) {
      simpleListener.forEach(aStarWatcher);
   }

   private static void applyWorld(World world, AStarResolver aStarResolver, NativeListener nativeListener) {
      AlertNotificationSound loggerEntry = world.getLoggerEntry2(aStarResolver.getCount(), aStarResolver.getCount2());
      nativeListener.getBreadthFirstPipeline().applyAlertNotificationSound(loggerEntry);
   }

   @Override
   public void forEach() {
      this.list.forEach(l -> l.forEach());
   }

   @Override
   public void applyAStarResolver(AStarResolver aStarResolver) {
      SecondaryOptionType secondaryOptionType = aStarResolver.getSecondaryOptionType();
      BreadthFirstService breadthFirstService = aStarResolver.getBreadthFirstService();
      World world = this.baritoneManager.getStaticListener().getWorld();
      boolean flag = secondaryOptionType == SecondaryOptionType.PRE
         && breadthFirstService == BreadthFirstService.UNLOAD
         && world.getCommonListener().checkIndex(aStarResolver.getCount(), aStarResolver.getCount2());
      if (aStarResolver.isActive() || flag) {
         this.baritoneManager.getReadmeTxtManager().applyConsumer(o -> applyWorld(world, aStarResolver, (NativeListener)o));
      }

      this.list.forEach(l -> applyAStarResolver(aStarResolver, l));
   }

   private static void applyHeuristicProcessorByHeuristicProcess(HeuristicProcessor heuristicProcessor, SimpleListener simpleListener) {
      simpleListener.forEach(heuristicProcessor);
   }

   @Override
   public void forEach(InternalItemEntry internalItemEntry) {
      this.list.forEach(l -> applyInternalItemEntry(internalItemEntry, l));
   }

   private static void applyHeuristicInspector(HeuristicInspector heuristicInspector, SimpleListener simpleListener) {
      simpleListener.forEach(heuristicInspector);
   }

   private static void applyDepthFirstTracker(DepthFirstTracker depthFirstTracker, SimpleListener simpleListener) {
      simpleListener.forEach(depthFirstTracker);
   }

   @Override
   public final void applyInternalItemEntry(InternalItemEntry internalItemEntry) {
      if (internalItemEntry.getInternalOptionType() == InternalOptionType.IN) {
         try {
            this.baritoneManager.heuristicManager = new HeuristicManager(this.baritoneManager.getStaticListener(), true);
         } catch (Exception exception) {
            this.baritoneManager.heuristicManager = null;
         }
      } else {
         this.baritoneManager.heuristicManager = null;
      }

      this.list.forEach(l -> applyInternalItemEntryByInternalItemEntr(internalItemEntry, l));
   }

   private void applyBreadthFirstProvider(BreadthFirstProvider breadthFirstProvider, NativeListener nativeListener) {
      World world = this.baritoneManager.getStaticListener().getWorld();
      AStarVerifier aStarVerifier = breadthFirstProvider.getAStarVerifier();
      nativeListener.getBreadthFirstPipeline().applyAlertNotificationSound(world.getLoggerEntry2(aStarVerifier.count, aStarVerifier.count2));
   }

   private static void applyBreadthFirstProvider(BreadthFirstProvider breadthFirstProvider, SimpleListener simpleListener) {
      simpleListener.applyBreadthFirstProvider(breadthFirstProvider);
   }

   @Override
   public final void add(SimpleListener simpleListener) {
      this.list.add(simpleListener);
   }

   @Override
   public final void forEach(DepthFirstTracker depthFirstTracker) {
      this.list.forEach(l -> applyDepthFirstTracker(depthFirstTracker, l));
   }

   private static void applyAStarCalculator(AStarCalculator aStarCalculator, SimpleListener simpleListener) {
      simpleListener.forEach(aStarCalculator);
   }

   public ListenerDispatcher(BaritoneManager baritoneManager2) {
      this.baritoneManager = baritoneManager2;
   }
}
