package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;
import baritone.api.pathing.goals.GoalYLevel;
import baritone.api.utils.BetterBlockPos;
import baritone.api.command.argument.IArgConsumer;
import java.util.stream.Stream;
import net.minecraft.util.MathHelper;

public enum RelativeGoalYLevel implements IDatatypePost {
   INSTANCE;

@Override
   public Object apply(IDatatypeContext datatypeContext, Object obj) throws CommandException {
      return this.apply2(datatypeContext, (BetterBlockPos)obj);
   }



   public GoalYLevel apply2(IDatatypeContext datatypeContext, BetterBlockPos betterBlockPos) throws CommandException {
      if (betterBlockPos == null) {
         betterBlockPos = BetterBlockPos.ORIGIN2;
      }

      return new GoalYLevel(MathHelper.floor_double((Double)datatypeContext.getConsumer().getDatatypePost(RelativeCoordinate.INSTANCE, (double)betterBlockPos.y2)));
   }

   @Override
   public Stream tabComplete(IDatatypeContext datatypeContext) {
      IArgConsumer consumer = datatypeContext.getConsumer();
      return consumer.hasAtMost(1) ? consumer.tabCompleteDatatype(RelativeCoordinate.INSTANCE) : Stream.empty();
   }
}
