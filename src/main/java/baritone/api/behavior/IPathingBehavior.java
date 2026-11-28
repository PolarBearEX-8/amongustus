package baritone.api.behavior;

import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.path.IPathExecutor;
import java.util.Optional;

public interface IPathingBehavior extends IBehavior {
   Optional estimatedTicksToGoal();

   Optional getInProgress();

   default Optional ticksRemainingInSegment(boolean flag) {
      IPathExecutor current = this.getCurrent();
      if (current == null) {
         return Optional.empty();
      } else {
         int n = flag ? current.getPosition() : current.getPosition() + 1;
         return Optional.of(current.getPath().ticksRemainingFrom(n));
      }
   }

   boolean isPathing();

   IPathExecutor getNext();

   Goal getGoal();

   IPathExecutor getCurrent();

   default Optional ticksRemainingInSegment() {
      return this.ticksRemainingInSegment(true);
   }

   boolean cancelEverything();

   default boolean hasPath() {
      return this.getCurrent() != null;
   }

   void forceCancel();

   default Optional getPath() {
      return Optional.ofNullable(this.getCurrent()).map(IPathExecutor::getPath);
   }
}
