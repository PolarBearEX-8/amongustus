package cc.baritone.utils;

public class AStarController implements SystemListener {
   public final SystemListener systemListener;
   public final SystemListener systemListener2;
   public final ExploringAroundINACTIVEHandlerAdapter exploringAroundINACTIVEHandlerAdapter;

   @Override
   public int getCount() {
      return Math.min(this.systemListener.getCount(), this.systemListener2.getCount());
   }

   public AStarController(ExploringAroundINACTIVEHandlerAdapter exploringAroundINACTIVEHandlerAdapter2, SystemListener systemListener3, SystemListener systemListener4, HeuristicPolicy heuristicPolicy) {
      this(exploringAroundINACTIVEHandlerAdapter2, systemListener3, systemListener4);
   }

   private AStarController(ExploringAroundINACTIVEHandlerAdapter exploringAroundINACTIVEHandlerAdapter2, SystemListener systemListener3, SystemListener systemListener4) {
      this.exploringAroundINACTIVEHandlerAdapter = exploringAroundINACTIVEHandlerAdapter2;
      this.systemListener = systemListener3;
      this.systemListener2 = systemListener4;
   }

   @Override
   public DepthFirstPlanner resolveDepthFirstPlanner(int count, int count2) {
      return this.systemListener.resolveDepthFirstPlanner(count, count2) == DepthFirstPlanner.EXPLORED
         ? DepthFirstPlanner.EXPLORED
         : this.systemListener2.resolveDepthFirstPlanner(count, count2);
   }
}
