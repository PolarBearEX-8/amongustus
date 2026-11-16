package cc.baritone.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BreadthFirstSampler {
   public static final Pattern pattern = Pattern.compile("\\S+");

   public static List resolveList(String text, boolean flag) {
      java.util.ArrayList list = new java.util.ArrayList();
      Matcher matcher = pattern.matcher(text);

      int count;
      for (count = -1; matcher.find(); count = matcher.end()) {
         list.add(new EnumBreadthFirstScheduler(list.size(), matcher.group(), text.substring(matcher.start())));
      }

      if (flag && count < text.length()) {
         list.add(new EnumBreadthFirstScheduler(list.size(), "", ""));
      }

      return list;
   }

   public static List resolveList(String text) {
      return resolveList(text, false);
   }

   public static EnumBreadthFirstScheduler getEnumBreadthFirstScheduler() {
      return new EnumBreadthFirstScheduler(-1, "<unknown>", "");
   }

   private BreadthFirstSampler() {
   }
}
