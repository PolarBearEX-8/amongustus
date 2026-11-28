package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;
import java.util.stream.Stream;

public interface IDatatype {
   Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException;
}
