package baritone.command.argparser;

import baritone.api.command.argparser.IStatelessArgParser;
import baritone.api.command.argument.ICommandArgument;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class BooleanArgumentParser implements IStatelessArgParser {
   public static final BooleanArgumentParser INSTANCE = new BooleanArgumentParser();
   public static final List TRUTHY_VALUES = Arrays.asList("1", "true", "yes", "t", "y", "on", "enable");
   public static final List FALSY_VALUES = Arrays.asList("0", "false", "no", "f", "n", "off", "disable");

   @Override
   public Object parseArg(ICommandArgument commandArgument) throws Exception {
      return this.parseArg2(commandArgument);
   }

   public Boolean parseArg2(ICommandArgument commandArgument) throws RuntimeException {
      String value = commandArgument.getValue();
      if (TRUTHY_VALUES.contains(value.toLowerCase(Locale.US))) {
         return true;
      } else if (FALSY_VALUES.contains(value.toLowerCase(Locale.US))) {
         return false;
      } else {
         throw new IllegalArgumentException("invalid boolean");
      }
   }

   @Override
   public Class getTarget() {
      return Boolean.class;
   }
}
