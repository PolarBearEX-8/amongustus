package cc.baritone.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;

public enum HeuristicMonitor {
    ALL((item -> HeuristicMonitor.resolveManagedListenerArrayByManagedListenerA((ManagedListener[])item)), new String[]{"all", "a"}),
    NEWEST((item -> HeuristicMonitor.resolveManagedListenerArray((ManagedListener[])item)), new String[]{"newest", "n"}),
    OLDEST((item -> HeuristicMonitor.resolveManagedListenerArray2((ManagedListener[])item)), new String[]{"oldest", "o"});

   public final Function function;
   public final String[] stringArray;
   public static final HeuristicMonitor[] ENUM_VALUES = new HeuristicMonitor[]{ALL, NEWEST, OLDEST};

   public static String[] getTextArray() {
      HashSet set = new HashSet();

      for (HeuristicMonitor heuristicMonitor : values()) {
         set.addAll(Arrays.asList(heuristicMonitor.stringArray));
      }

      return (String[]) set.toArray(new String[0]);
   }

   private HeuristicMonitor(Function mapper, String[] textArray) {
      this.function = mapper;
      this.stringArray = textArray;
   }

   public static HeuristicMonitor resolveHeuristicMonitor(String text) {
      for (HeuristicMonitor heuristicMonitor : values()) {
         String[] stringArray2 = heuristicMonitor.stringArray;
         int length = stringArray2.length;

         for (int index = 0; index < length; index++) {
            if (stringArray2[index].equalsIgnoreCase(text)) {
               return heuristicMonitor;
            }
         }
      }

      return null;
   }

   private static ManagedListener[] resolveManagedListenerArrayByManagedListenerA(ManagedListener[] managedListenerArray) {
      return managedListenerArray;
   }

   public ManagedListener[] apply(ManagedListener[] managedListenerArray) {
      return (ManagedListener[])this.function.apply(managedListenerArray);
   }



   private static ManagedListener[] resolveManagedListenerArray(ManagedListener[] managedListenerArray) {
      return new ManagedListener[]{managedListenerArray[managedListenerArray.length - 1]};
   }

   private static ManagedListener[] resolveManagedListenerArray2(ManagedListener[] managedListenerArray) {
      return new ManagedListener[]{managedListenerArray[0]};
   }
}
