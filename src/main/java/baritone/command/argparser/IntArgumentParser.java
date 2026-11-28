package baritone.command.argparser;


import baritone.api.command.argparser.IStatelessArgParser;
import baritone.api.command.argument.ICommandArgument;
public enum IntArgumentParser implements IStatelessArgParser {
   INSTANCE;

@Override
   public Class getTarget() {
      return Integer.class;
   }



   public Integer parseArg2(ICommandArgument commandArgument) throws RuntimeException {
      return Integer.parseInt(commandArgument.getValue());
   }

   @Override
   public Object parseArg(ICommandArgument commandArgument) throws Exception {
      return this.parseArg2(commandArgument);
   }
}
