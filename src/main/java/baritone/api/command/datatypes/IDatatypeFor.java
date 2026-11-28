package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;

public interface IDatatypeFor extends IDatatype {
   Object get(IDatatypeContext datatypeContext) throws CommandException;
}
