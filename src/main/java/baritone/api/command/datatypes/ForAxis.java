package baritone.api.command.datatypes;

import net.minecraft.util.EnumFacing;
import baritone.api.command.exception.CommandException;
import baritone.api.command.helpers.TabCompleteHelper;
import java.util.Locale;
import java.util.stream.Stream;

public enum ForAxis implements IDatatypeFor {
   INSTANCE;

@Override
   public Object get(IDatatypeContext datatypeContext) throws CommandException {
      return this.get2(datatypeContext);
   }

   @Override
   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      return new TabCompleteHelper()
         .append(Stream.of(EnumFacing.Axis.values()).map(EnumFacing.Axis::getName).map(String::toLowerCase))
         .filterPrefix(datatypeContext.getConsumer().getString())
         .getStream();
   }

   public EnumFacing.Axis get2(IDatatypeContext datatypeContext) throws CommandException {
      return EnumFacing.Axis.valueOf(datatypeContext.getConsumer().getString().toUpperCase(Locale.US));
   }
}
