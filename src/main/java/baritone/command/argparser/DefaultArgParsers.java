package baritone.command.argparser;

import java.util.Arrays;
import java.util.List;

public class DefaultArgParsers {
   public static final List ALL = Arrays.asList(IntArgumentParser.INSTANCE, LongArgumentParser.INSTANCE, FloatArgumentParser.INSTANCE, DoubleArgumentParser.INSTANCE, BooleanArgumentParser.INSTANCE);
}
