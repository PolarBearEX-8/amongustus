package baritone.api.command.exception;

import baritone.api.command.ICommand;
import baritone.api.utils.Helper;
import java.util.List;

public class CommandNotFoundException extends CommandException {
   public final String command;

   public CommandNotFoundException(String str) {
      super(String.format("Command not found: %s", str));
      this.command = str;
   }

   @Override
   public void handle(ICommand command2, List list) {
      Helper.HELPER.logDirect2(this.getMessage());
   }
}
