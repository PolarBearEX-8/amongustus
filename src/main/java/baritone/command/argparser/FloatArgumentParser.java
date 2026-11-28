package baritone.command.argparser;


import baritone.api.command.argparser.IStatelessArgParser;
import baritone.api.command.argument.ICommandArgument;
public enum FloatArgumentParser implements IStatelessArgParser {
   INSTANCE;

@Override
   public Class getTarget() {
      return Float.class;
   }

   @Override
   public Object parseArg(ICommandArgument commandArgument) throws Exception {
      return this.parseArg2(commandArgument);
   }

   public Float parseArg2(ICommandArgument commandArgument) throws RuntimeException {
      String value = commandArgument.getValue();
      if (!value.matches("^([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)|)$")) {
         throw new IllegalArgumentException("failed float format check");
      } else {
         return Float.parseFloat(value);
      }
   }
}
