package cc.baritone.utils;

import cc.baritone.api.InternalItemEntry;

public interface SimpleListener {
   void applyBreadthFirstProvider(BreadthFirstProvider breadthFirstProvider);

   void forEach(AStarWatcher aStarWatcher);

   void forEach(HeuristicInspector heuristicInspector);

   void forEach(AStarAuditor aStarAuditor);

   void forEach(HeuristicProcessor heuristicProcessor);

   void forEach(SecondaryCustom secondaryCustom);

   void forEach(ActiveCustom activeCustom);

   void applySupplementalCustom(SupplementalCustom supplementalCustom);

   void forEachByHeuristicProcess(HeuristicProcessor heuristicProcessor);

   void forEach(AStarCalculator aStarCalculator);

   void applyAStarResolver(AStarResolver aStarResolver);

   void forEach();

   void forEach(InternalItemEntry internalItemEntry);

   void forEach(DepthFirstTracker depthFirstTracker);

   void applyInternalItemEntry(InternalItemEntry internalItemEntry);
}
