package cc.baritone.utils;

import cc.baritone.api.InternalItemEntry;

public interface ExtendedListener extends SimpleListener {
   @Override
   default void forEach(AStarWatcher aStarWatcher) {
   }

   @Override
   default void applyBreadthFirstProvider(BreadthFirstProvider breadthFirstProvider) {
   }

   @Override
   default void forEach(HeuristicInspector heuristicInspector) {
   }

   @Override
   default void forEach(HeuristicProcessor heuristicProcessor) {
   }

   @Override
   default void forEach(AStarAuditor aStarAuditor) {
   }

   @Override
   default void forEach(SecondaryCustom secondaryCustom) {
   }

   @Override
   default void applySupplementalCustom(SupplementalCustom supplementalCustom) {
   }

   @Override
   default void forEachByHeuristicProcess(HeuristicProcessor heuristicProcessor) {
   }

   @Override
   default void forEach(ActiveCustom activeCustom) {
   }

   @Override
   default void forEach(AStarCalculator aStarCalculator) {
   }

   @Override
   default void applyAStarResolver(AStarResolver aStarResolver) {
   }

   @Override
   default void forEach() {
   }

   @Override
   default void forEach(InternalItemEntry internalItemEntry) {
   }

   @Override
   default void forEach(DepthFirstTracker depthFirstTracker) {
   }

   @Override
   default void applyInternalItemEntry(InternalItemEntry internalItemEntry) {
   }
}
