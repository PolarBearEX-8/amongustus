package cc.baritone.utils;

import java.util.Arrays;
import java.util.HashSet;

public enum AStarValidator {
    LIST(new String[]{"list", "get", "l"}),
    CLEAR(new String[]{"clear", "c"}),
    SAVE(new String[]{"save", "s"}),
    INFO(new String[]{"info", "show", "i"}),
    DELETE(new String[]{"delete", "d"}),
    RESTORE(new String[]{"restore"}),
    GOAL(new String[]{"goal", "g"}),
    GOTO(new String[]{"goto"});

   public final String[] stringArray;
   public static final AStarValidator[] ENUM_VALUES = new AStarValidator[]{LIST, CLEAR, SAVE, INFO, DELETE, RESTORE, GOAL, GOTO};

   public static String[] getTextArray() {
      HashSet set = new HashSet();

      for (AStarValidator aStarValidator : values()) {
         set.addAll(Arrays.asList(aStarValidator.stringArray));
      }

      return ((String[])set.toArray(new String[0]));
   }

   private AStarValidator(String[] textArray) {
      this.stringArray = textArray;
   }

   public static String[] resolveTextArray(AStarValidator aStarValidator) {
      return aStarValidator.stringArray;
   }



   public static AStarValidator resolveAStarValidator(String text) {
      for (AStarValidator aStarValidator : values()) {
         String[] stringArray2 = aStarValidator.stringArray;
         int length = stringArray2.length;

         for (int index = 0; index < length; index++) {
            if (stringArray2[index].equalsIgnoreCase(text)) {
               return aStarValidator;
            }
         }
      }

      return null;
   }
}
