package baritone.process;

import baritone.Baritone;
import baritone.api.cache.ICachedWorld;
import baritone.cache.CachedWorld;

public class BaritoneChunkFilter implements IChunkFilter {
   private final ICachedWorld cache;
   public final ExploreProcess exploreProcess;

   public BaritoneChunkFilter(ExploreProcess exploreProcess2, ExploreProcess.DefaultGoalXZ obj) {
      this(exploreProcess2);
   }

   @Override
   public ExploreProcess.Status isAlreadyExplored(int n, int n2) {
      int n3 = n << 4;
      int n4 = n2 << 4;
      if (this.cache.isCached(n3, n4)) {
         return ExploreProcess.Status.EXPLORED;
      } else if (!((CachedWorld)this.cache).isRegionLoaded(n3, n4)) {
         Baritone.getThreadPool().execute(() -> ((CachedWorld)this.cache).tryLoadFromDisk(n3 >> 9, n4 >> 9));
         return ExploreProcess.Status.UNKNOWN;
      } else {
         return ExploreProcess.Status.NOT_EXPLORED;
      }
   }

   private BaritoneChunkFilter(ExploreProcess exploreProcess2) {
      this.exploreProcess = exploreProcess2;
      this.cache = ExploreProcess.getBaritone(this.exploreProcess).getWorldProvider2().getCurrentWorld2().getCachedWorld();
   }

   @Override
   public int countRemain() {
      return Integer.MAX_VALUE;
   }
}
