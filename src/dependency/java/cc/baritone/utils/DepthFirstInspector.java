package cc.baritone.utils;

import java.util.Arrays;
import java.util.HashSet;

public enum DepthFirstInspector {
    POS1(new String[]{"pos1", "p1", "1"}),
    POS2(new String[]{"pos2", "p2", "2"}),
    CLEAR(new String[]{"clear", "c"}),
    UNDO(new String[]{"undo", "u"}),
    SET(new String[]{"set", "fill", "s", "f"}),
    WALLS(new String[]{"walls", "w"}),
    SHELL(new String[]{"shell", "shl"}),
    SPHERE(new String[]{"sphere", "sph"}),
    HSPHERE(new String[]{"hsphere", "hsph"}),
    CYLINDER(new String[]{"cylinder", "cyl"}),
    HCYLINDER(new String[]{"hcylinder", "hcyl"}),
    CLEARAREA(new String[]{"cleararea", "ca"}),
    REPLACE(new String[]{"replace", "r"}),
    EXPAND(new String[]{"expand", "ex"}),
    COPY(new String[]{"copy", "cp"}),
    PASTE(new String[]{"paste", "p"}),
    CONTRACT(new String[]{"contract", "ct"}),
    SHIFT(new String[]{"shift", "sh"});

   public final String[] stringArray;
   public static final DepthFirstInspector[] ENUM_VALUES = new DepthFirstInspector[]{
      POS1, POS2, CLEAR, UNDO, SET, WALLS, SHELL, SPHERE, HSPHERE, CYLINDER, HCYLINDER, CLEARAREA, REPLACE, EXPAND, COPY, PASTE, CONTRACT, SHIFT
   };

   public static String[] getTextArray() {
      HashSet set = new HashSet();

      for (DepthFirstInspector depthFirstInspector : values()) {
         set.addAll(Arrays.asList(depthFirstInspector.stringArray));
      }

      return ((String[])set.toArray(new String[0]));
   }



   public final boolean isActive() {
      return this == SET
         || this == WALLS
         || this == SHELL
         || this == SPHERE
         || this == HSPHERE
         || this == CYLINDER
         || this == HCYLINDER
         || this == CLEARAREA
         || this == REPLACE;
   }

   private DepthFirstInspector(String[] textArray) {
      this.stringArray = textArray;
   }

   public static DepthFirstInspector resolveDepthFirstInspector(String text) {
      for (DepthFirstInspector depthFirstInspector : values()) {
         String[] stringArray2 = depthFirstInspector.stringArray;
         int length = stringArray2.length;

         for (int index = 0; index < length; index++) {
            if (stringArray2[index].equalsIgnoreCase(text)) {
               return depthFirstInspector;
            }
         }
      }

      return null;
   }
}
