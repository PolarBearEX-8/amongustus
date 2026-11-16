package cc.baritone.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public enum BreadthFirstSystem {
    HOME(new String[]{"home", "base"}),
    DEATH(new String[]{"death"}),
    BED(new String[]{"bed", "spawn"}),
    USER(new String[]{"user"});

   public static final List list = Collections.unmodifiableList(Arrays.asList(values()));
   public final String[] names;
   public static final BreadthFirstSystem[] ENUM_VALUES = new BreadthFirstSystem[]{HOME, DEATH, BED, USER};

   private BreadthFirstSystem(String[] textArray) {
      this.names = textArray;
   }

   public String getText() {
      return this.names[0];
   }



   public static BreadthFirstSystem resolveBreadthFirstSystem(String text) {
      for (BreadthFirstSystem breadthFirstSystem : values()) {
         String[] names2 = breadthFirstSystem.names;
         int length = names2.length;

         for (int index = 0; index < length; index++) {
            if (names2[index].equalsIgnoreCase(text)) {
               return breadthFirstSystem;
            }
         }
      }

      return null;
   }

   public static String[] getTextArray() {
      HashSet set = new HashSet();

      for (BreadthFirstSystem breadthFirstSystem : values()) {
         set.addAll(Arrays.asList(breadthFirstSystem.names));
      }

      return (String[])set.toArray(new String[0]);
   }
}
