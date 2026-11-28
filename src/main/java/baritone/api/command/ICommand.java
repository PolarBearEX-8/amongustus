package baritone.api.command;

import baritone.api.command.exception.CommandException;
import baritone.api.utils.Helper;
import baritone.api.command.argument.IArgConsumer;
import java.util.List;
import java.util.stream.Stream;

public interface ICommand extends Helper {
   String getShortDesc();

   Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException;

   default boolean hiddenFromHelp() {
      return false;
   }

   void execute(String str, IArgConsumer argConsumer) throws CommandException;

   List getNames();

   List getLongDesc();
}
