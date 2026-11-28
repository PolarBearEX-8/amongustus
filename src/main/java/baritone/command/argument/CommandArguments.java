package baritone.command.argument;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommandArguments {
   private static final Pattern ARG_PATTERN = Pattern.compile("\\S+");

   public static List from(String str) {
      return from(str, false);
   }

   public static List from(String str, boolean flag) {
      ArrayList list = new ArrayList();
      Matcher matcher = ARG_PATTERN.matcher(str);

      int n;
      for (n = -1; matcher.find(); n = matcher.end()) {
         list.add(new CommandArgument(list.size(), matcher.group(), str.substring(matcher.start())));
      }

      if (flag && n < str.length()) {
         list.add(new CommandArgument(list.size(), "", ""));
      }

      return list;
   }

   private CommandArguments() {
   }

   public static CommandArgument createUnknown() {
      return new CommandArgument(-1, "<unknown>", "");
   }
}
