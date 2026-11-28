package baritone.api.command.exception;

import baritone.api.command.ICommand;
import baritone.api.utils.Helper;
import java.util.List;

public class CommandUnhandledException extends RuntimeException implements ICommandException {
   @Override
   public void handle(ICommand command, List list) {
      Helper.HELPER.logUnhandledException(this);
   }

   public CommandUnhandledException(Throwable t) {
      super(t);
   }

   public CommandUnhandledException(String str) {
      super(str);
   }
}
