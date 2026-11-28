package baritone.command.argparser;


import baritone.api.command.argparser.IStatelessArgParser;
import baritone.api.command.argument.ICommandArgument;
public enum LongArgumentParser implements IStatelessArgParser {
   INSTANCE;

public Long parseArg2(ICommandArgument commandArgument) throws RuntimeException {
      return Long.parseLong(commandArgument.getValue());
   }

   @Override
   public Class getTarget() {
      return Long.class;
   }



   @Override
   public Object parseArg(ICommandArgument commandArgument) throws Exception {
      return this.parseArg2(commandArgument);
   }
}
