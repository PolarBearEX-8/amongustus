package baritone.process;


public class EitherChunkFilter implements IChunkFilter {
   private final IChunkFilter firstFilter;
   private final IChunkFilter secondFilter;
   public final ExploreProcess exploreProcess;

   @Override
   public int countRemain() {
      return Math.min(this.firstFilter.countRemain(), this.secondFilter.countRemain());
   }

   @Override
   public ExploreProcess.Status isAlreadyExplored(int n, int n2) {
      return this.firstFilter.isAlreadyExplored(n, n2) == ExploreProcess.Status.EXPLORED ? ExploreProcess.Status.EXPLORED : this.secondFilter.isAlreadyExplored(n, n2);
   }

   private EitherChunkFilter(ExploreProcess exploreProcess2, IChunkFilter chunkFilter, IChunkFilter chunkFilter2) {
      this.exploreProcess = exploreProcess2;
      this.firstFilter = chunkFilter;
      this.secondFilter = chunkFilter2;
   }

   public EitherChunkFilter(ExploreProcess exploreProcess2, IChunkFilter chunkFilter, IChunkFilter chunkFilter2, ExploreProcess.DefaultGoalXZ obj) {
      this(exploreProcess2, chunkFilter, chunkFilter2);
   }
}
