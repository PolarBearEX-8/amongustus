package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;
import baritone.api.utils.BetterBlockPos;
import baritone.api.command.argument.IArgConsumer;
import java.util.stream.Stream;

public enum RelativeBlockPos implements IDatatypePost {
   INSTANCE;

public BetterBlockPos apply2(IDatatypeContext datatypeContext, BetterBlockPos betterBlockPos) throws CommandException {
      if (betterBlockPos == null) {
         betterBlockPos = BetterBlockPos.ORIGIN2;
      }

      IArgConsumer consumer = datatypeContext.getConsumer();
      return new BetterBlockPos(
         (Double)consumer.getDatatypePost(RelativeCoordinate.INSTANCE, (double)betterBlockPos.x2),
         (Double)consumer.getDatatypePost(RelativeCoordinate.INSTANCE, (double)betterBlockPos.y2),
         (Double)consumer.getDatatypePost(RelativeCoordinate.INSTANCE, (double)betterBlockPos.z2)
      );
   }

   @Override
   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      IArgConsumer consumer = datatypeContext.getConsumer();
      if (consumer.hasAny() && !consumer.has(4)) {
         while (consumer.has(2) && consumer.peekDatatypeOrNull2(RelativeCoordinate.INSTANCE) != null) {
            consumer.get();
         }

         return consumer.tabCompleteDatatype(RelativeCoordinate.INSTANCE);
      } else {
         return Stream.empty();
      }
   }

   @Override
   public Object apply(IDatatypeContext datatypeContext, Object obj) throws CommandException {
      return this.apply2(datatypeContext, (BetterBlockPos)obj);
   }


}
