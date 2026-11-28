package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;
import baritone.api.pathing.goals.GoalXZ;
import baritone.api.utils.BetterBlockPos;
import baritone.api.command.argument.IArgConsumer;
import java.util.stream.Stream;
import net.minecraft.util.MathHelper;

public enum RelativeGoalXZ implements IDatatypePost {
   INSTANCE;

@Override
   public Stream tabComplete(IDatatypeContext datatypeContext) {
      IArgConsumer consumer = datatypeContext.getConsumer();
      return consumer.hasAtMost(2) ? consumer.tabCompleteDatatype(RelativeCoordinate.INSTANCE) : Stream.empty();
   }

   @Override
   public Object apply(IDatatypeContext datatypeContext, Object obj) throws CommandException {
      return this.apply2(datatypeContext, (BetterBlockPos)obj);
   }

   public GoalXZ apply2(IDatatypeContext datatypeContext, BetterBlockPos betterBlockPos) throws CommandException {
      if (betterBlockPos == null) {
         betterBlockPos = BetterBlockPos.ORIGIN2;
      }

      IArgConsumer consumer = datatypeContext.getConsumer();
      return new GoalXZ(
         MathHelper.floor_double((Double)consumer.getDatatypePost(RelativeCoordinate.INSTANCE, (double)betterBlockPos.x2)),
         MathHelper.floor_double((Double)consumer.getDatatypePost(RelativeCoordinate.INSTANCE, (double)betterBlockPos.z2))
      );
   }


}
