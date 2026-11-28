package baritone.process;

import baritone.api.pathing.goals.GoalYLevel;
import baritone.Baritone;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalComposite;
import baritone.api.pathing.goals.GoalXZ;
import baritone.api.process.IExploreProcess;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import baritone.utils.BaritoneProcessHelper;
import java.nio.file.Path;
import java.util.ArrayList;
import net.minecraft.util.BlockPos;

public final class ExploreProcess extends BaritoneProcessHelper implements IExploreProcess {
   private BlockPos explorationOrigin;
   private IChunkFilter filter;
   private int distanceCompleted;

   private static Goal createGoal(int n, int n2) {
      return (Goal)((Integer)Baritone.settings().exploreMaintainY.value == -1 ? new GoalXZ(n, n2) : new ExploreProcess.DefaultGoalXZ(n, n2));
   }

   @Override
   public boolean isActive() {
      return this.explorationOrigin != null;
   }

   public ExploreProcess(Baritone baritone2) {
      super(baritone2);
   }

   @Override
   public void onLostControl() {
      this.explorationOrigin = null;
   }

   public IChunkFilter calcFilter() {
      Object obj;
      if (this.filter != null) {
         obj = new EitherChunkFilter(this, this.filter, new BaritoneChunkFilter(this, null), null);
      } else {
         obj = new BaritoneChunkFilter(this, null);
      }

      return (IChunkFilter)obj;
   }

   @Override
   public void applyJsonFilter(Path path, boolean flag) throws Exception {
      this.filter = new JsonChunkFilter(this, path, flag, null);
   }

   @Override
   public PathingCommand onTick(boolean flag, boolean flag2) {
      if (flag) {
         this.logDirect2("Failed");
         if ((Boolean)Baritone.settings().notificationOnExploreFinished.value) {
            this.logNotification("Exploration failed", true);
         }

         this.onLostControl();
         return null;
      } else {
         IChunkFilter chunkFilter = this.calcFilter();
         if (!(Boolean)Baritone.settings().disableCompletionCheck.value && chunkFilter.countRemain() == 0) {
            this.logDirect2("Explored all chunks");
            if ((Boolean)Baritone.settings().notificationOnExploreFinished.value) {
               this.logNotification("Explored all chunks", false);
            }

            this.onLostControl();
            return null;
         } else {
            Goal[] goals = this.closestUncachedChunks(this.explorationOrigin, chunkFilter);
            if (goals == null) {
               this.logDebug("awaiting region load from disk");
               return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
            } else {
               return new PathingCommand(new GoalComposite(goals), PathingCommandType.FORCE_REVALIDATE_GOAL_AND_PATH);
            }
         }
      }
   }

   @Override
   public String displayName0() {
      return "Exploring around "
         + this.explorationOrigin
         + ", distance completed "
         + this.distanceCompleted
         + ", currently going to "
         + new GoalComposite(this.closestUncachedChunks(this.explorationOrigin, this.calcFilter()));
   }

   private Goal[] closestUncachedChunks(BlockPos pos, IChunkFilter chunkFilter) {
      int n = pos.getX() >> 4;
      int n2 = pos.getZ() >> 4;
      int n3 = Math.min(chunkFilter.countRemain(), (Integer)Baritone.settings().exploreChunkSetMinimumSize.value);
      ArrayList list = new ArrayList();
      int value = (Integer)Baritone.settings().worldExploringChunkOffset.value;
      int distanceCompleted2 = this.distanceCompleted;

      while (true) {
         for (int j = -distanceCompleted2; j <= distanceCompleted2; j++) {
            int n4 = distanceCompleted2 - Math.abs(j);
            int n5 = 0;

            while (n5 < 2) {
               int n6 = (n5 * 2 - 1) * n4;
               if ((Math.abs(j) + Math.abs(n6)) != distanceCompleted2) {
                  throw new IllegalStateException();
               }

               switch (chunkFilter.isAlreadyExplored(n + j, n2 + n6)) {
                  case UNKNOWN:
                     return null;
                  case NOT_EXPLORED:
                  default:
                     int n8 = (n + j << 4) + 8;
                     int n9 = (n2 + n6 << 4) + 8;
                     int n10 = value << 4;
                     if (j < 0) {
                        n8 -= n10;
                     } else {
                        n8 += n10;
                     }

                     if (n6 < 0) {
                        n9 -= n10;
                     } else {
                        n9 += n10;
                     }

                     list.add(new BlockPos(n8, 0, n9));
                  case EXPLORED:
                     n5++;
               }
            }
         }

         if (distanceCompleted2 % 10 == 0) {
            n3 = Math.min(chunkFilter.countRemain(), (Integer)Baritone.settings().exploreChunkSetMinimumSize.value);
         }

         if (list.size() >= n3) {
            return (Goal[])list.stream().map(obj -> createGoal(((BlockPos)obj).getX(), ((BlockPos)obj).getZ())).toArray(Goal[]::new);
         }

         if (list.isEmpty()) {
            this.distanceCompleted = distanceCompleted2 + 1;
         }

         distanceCompleted2++;
      }
   }

   @Override
   public void explore(int n, int n2) {
      this.explorationOrigin = new BlockPos(n, 0, n2);
      this.distanceCompleted = 0;
   }

   public static Baritone getBaritone(ExploreProcess exploreProcess) {
      return exploreProcess.baritone;
   }

public static final class DefaultGoalXZ extends GoalXZ {
      @Override
      public double heuristic(int n, int n2, int n3) {
         return super.heuristic(n, n2, n3) + GoalYLevel.calculate((Integer)Baritone.settings().exploreMaintainY.value, n2);
      }

      public DefaultGoalXZ(int n, int n2) {
         super(n, n2);
      }
   }

public enum Status {
      EXPLORED,
      NOT_EXPLORED,
      UNKNOWN;
   }
}
