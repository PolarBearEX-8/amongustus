package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalBlock;
import baritone.api.pathing.goals.GoalXZ;
import baritone.api.pathing.goals.GoalYLevel;
import baritone.api.utils.BetterBlockPos;
import baritone.api.command.argument.IArgConsumer;
import java.util.stream.Stream;

public enum RelativeGoal implements IDatatypePost {
   INSTANCE;

public Goal apply2(IDatatypeContext datatypeContext, BetterBlockPos betterBlockPos) throws CommandException {
      if (betterBlockPos == null) {
         betterBlockPos = BetterBlockPos.ORIGIN2;
      }

      IArgConsumer consumer = datatypeContext.getConsumer();
      GoalBlock goalBlock = (GoalBlock)consumer.peekDatatypePostOrNull(RelativeGoalBlock.INSTANCE, betterBlockPos);
      if (goalBlock != null) {
         return goalBlock;
      } else {
         GoalXZ goalXZ = (GoalXZ)consumer.peekDatatypePostOrNull(RelativeGoalXZ.INSTANCE, betterBlockPos);
         if (goalXZ != null) {
            return goalXZ;
         } else {
            GoalYLevel goalYLevel = (GoalYLevel)consumer.peekDatatypePostOrNull(RelativeGoalYLevel.INSTANCE, betterBlockPos);
            return (Goal)(goalYLevel != null ? goalYLevel : new GoalBlock(betterBlockPos));
         }
      }
   }



   @Override
   public Stream tabComplete(IDatatypeContext datatypeContext) {
      return datatypeContext.getConsumer().tabCompleteDatatype(RelativeCoordinate.INSTANCE);
   }

   @Override
   public Object apply(IDatatypeContext datatypeContext, Object obj) throws CommandException {
      return this.apply2(datatypeContext, (BetterBlockPos)obj);
   }
}
