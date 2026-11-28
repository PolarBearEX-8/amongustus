package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;
import baritone.api.command.helpers.TabCompleteHelper;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.util.EnumFacing;

public enum ForEnumFacing implements IDatatypeFor {
   INSTANCE;

public EnumFacing get2(IDatatypeContext datatypeContext) throws CommandException {
      return EnumFacing.valueOf(datatypeContext.getConsumer().getString().toUpperCase(Locale.US));
   }

   @Override
   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      return new TabCompleteHelper().append(Stream.of(EnumFacing.values()).map(EnumFacing::getName).map(String::toLowerCase)).filterPrefix(datatypeContext.getConsumer().getString()).getStream();
   }

   @Override
   public Object get(IDatatypeContext datatypeContext) throws CommandException {
      return this.get2(datatypeContext);
   }
}
