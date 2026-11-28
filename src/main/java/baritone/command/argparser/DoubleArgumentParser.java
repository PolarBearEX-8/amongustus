package baritone.command.argparser;


import baritone.api.command.argparser.IStatelessArgParser;
import baritone.api.command.argument.ICommandArgument;
public enum DoubleArgumentParser implements IStatelessArgParser {
   INSTANCE;

@Override
   public Object parseArg(ICommandArgument commandArgument) throws Exception {
      return this.parseArg2(commandArgument);
   }

   @Override
   public Class getTarget() {
      return Double.class;
   }

   public Double parseArg2(ICommandArgument commandArgument) throws RuntimeException {
      String value = commandArgument.getValue();
      if (!value.matches("^([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)|)$")) {
         throw new IllegalArgumentException("failed double format check");
      } else {
         return Double.parseDouble(value);
      }
   }
}
