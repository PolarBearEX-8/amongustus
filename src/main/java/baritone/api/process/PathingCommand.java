package baritone.api.process;

import baritone.api.pathing.goals.Goal;
import java.util.Objects;

public class PathingCommand {
   public final Goal goal;
   public final PathingCommandType commandType;

   public PathingCommand(Goal goal2, PathingCommandType pathingCommandType) {
      Objects.requireNonNull(pathingCommandType);
      this.goal = goal2;
      this.commandType = pathingCommandType;
   }

   @Override
   public String toString() {
      return this.commandType + " " + this.goal;
   }
}
