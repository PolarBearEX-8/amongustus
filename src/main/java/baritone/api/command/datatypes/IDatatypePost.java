package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;

public interface IDatatypePost extends IDatatype {
   Object apply(IDatatypeContext datatypeContext, Object obj) throws CommandException;
}
