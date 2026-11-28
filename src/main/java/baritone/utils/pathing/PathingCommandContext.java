package baritone.utils.pathing;

import baritone.api.pathing.goals.Goal;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import baritone.pathing.movement.CalculationContext;

public class PathingCommandContext extends PathingCommand {
   public final CalculationContext desiredCalcContext;

   public PathingCommandContext(Goal goal2, PathingCommandType pathingCommandType, CalculationContext calculationContext) {
      super(goal2, pathingCommandType);
      this.desiredCalcContext = calculationContext;
   }
}
