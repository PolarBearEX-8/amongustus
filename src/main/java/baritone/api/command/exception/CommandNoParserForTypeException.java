package baritone.api.command.exception;


public class CommandNoParserForTypeException extends CommandUnhandledException {
   public CommandNoParserForTypeException(Class clazz) {
      super(String.format("Could not find a handler for type %s", clazz.getSimpleName()));
   }
}
