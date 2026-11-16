package cc.baritone.utils;

public class AStarMonitor implements SystemListener {
   public final BreadthFirstPipeline breadthFirstPipeline;
   public final ExploringAroundINACTIVEHandlerAdapter exploringAroundINACTIVEHandlerAdapter;

   @Override
   public int getCount() {
      return Integer.MAX_VALUE;
   }

   public AStarMonitor(ExploringAroundINACTIVEHandlerAdapter exploringAroundINACTIVEHandlerAdapter2, HeuristicPolicy heuristicPolicy) {
      this(exploringAroundINACTIVEHandlerAdapter2);
   }

   @Override
   public DepthFirstPlanner resolveDepthFirstPlanner(int count, int count2) {
      int count3 = count << 4;
      int count4 = count2 << 4;
      if (this.breadthFirstPipeline.checkIndex(count3, count4)) {
         return DepthFirstPlanner.EXPLORED;
      } else if (!((CachedWorldBreadthFirstPipeline)this.breadthFirstPipeline).checkIndex2(count3, count4)) {
         BaritoneManager.getExecutor().execute(() -> this.applyIndex(count3, count4));
         return DepthFirstPlanner.UNKNOWN;
      } else {
         return DepthFirstPlanner.NOT_EXPLORED;
      }
   }

   private void applyIndex(int count, int count2) {
      ((CachedWorldBreadthFirstPipeline)this.breadthFirstPipeline).applyIndex(count >> 9, count2 >> 9);
   }

   private AStarMonitor(ExploringAroundINACTIVEHandlerAdapter exploringAroundINACTIVEHandlerAdapter2) {
      this.exploringAroundINACTIVEHandlerAdapter = exploringAroundINACTIVEHandlerAdapter2;
      this.breadthFirstPipeline = ExploringAroundINACTIVEHandlerAdapter.resolveBaritoneManager(this.exploringAroundINACTIVEHandlerAdapter)
         .getReadmeTxtManager()
         .getDepthFirstEvaluator()
         .getBreadthFirstPipeline();
   }
}
