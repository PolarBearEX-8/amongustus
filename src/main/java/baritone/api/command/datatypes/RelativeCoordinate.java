package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;
import baritone.api.command.argument.IArgConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public enum RelativeCoordinate implements IDatatypePost {
   INSTANCE;

private static String SCALES = "[kKmM]";
   private static Pattern PATTERN = Pattern.compile("^(~?)([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(" + SCALES + "?)|)$");

   @Override
   public Object apply(IDatatypeContext datatypeContext, Object obj) throws CommandException {
      return this.apply2(datatypeContext, (Double)obj);
   }

   public Double apply2(IDatatypeContext datatypeContext, Double d) throws CommandException {
      if (d == null) {
         d = 0.0;
      }

      Matcher matcher = PATTERN.matcher(datatypeContext.getConsumer().getString());
      if (!matcher.matches()) {
         throw new IllegalArgumentException("pattern doesn't match");
      } else {
         boolean flag = !matcher.group(1).isEmpty();
         double d2 = matcher.group(2).isEmpty() ? 0.0 : Double.parseDouble(matcher.group(2).replaceAll(SCALES, ""));
         if (matcher.group(2).toLowerCase().contains("k")) {
            d2 *= 1000.0;
         }

         if (matcher.group(2).toLowerCase().contains("m")) {
            d2 *= 1000000.0;
         }

         return flag ? d + d2 : d2;
      }
   }

   @Override
   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      IArgConsumer consumer = datatypeContext.getConsumer();
      return !consumer.has(2) && consumer.getString().matches("^(~|$)") ? Stream.of("~") : Stream.empty();
   }
}
