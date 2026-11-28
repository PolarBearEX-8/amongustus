package baritone.command.defaults;

import baritone.api.selection.ISelection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;

public enum SelTransformTarget {
   ALL(obj -> obj, new String[]{"all", "a"}),
   NEWEST(obj -> new ISelection[]{((ISelection[])obj)[((ISelection[])obj).length - 1]}, new String[]{"newest", "n"}),
   OLDEST(obj -> new ISelection[]{((ISelection[])obj)[0]}, new String[]{"oldest", "o"});
   private final Function transform;
   private final String[] names;
   public static String[] getAllNames() {
      HashSet set = new HashSet();

      for (SelTransformTarget selTransformTarget : values()) {
         set.addAll(Arrays.asList(selTransformTarget.names));
      }

      return (String[])set.toArray(new String[0]);
   }

   public ISelection[] transform(ISelection[] selections) {
      return (ISelection[])this.transform.apply(selections);
   }

   private SelTransformTarget(Function function, String[] strings) {
      this.transform = function;
      this.names = strings;
   }

   public static SelTransformTarget getByName(String str) {
      for (SelTransformTarget selTransformTarget : values()) {
         for (String str2 : selTransformTarget.names) {
            if (str2.equalsIgnoreCase(str)) {
               return selTransformTarget;
            }
         }
      }

      return null;
   }

}
