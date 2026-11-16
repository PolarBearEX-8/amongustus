package cc.baritone.utils;

public class StandardCustom {
   public static final int[] countArray = new int[DepthFirstInspector.values().length];

   static {
      try {
         countArray[DepthFirstInspector.WALLS.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[DepthFirstInspector.SHELL.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[DepthFirstInspector.REPLACE.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[DepthFirstInspector.SPHERE.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[DepthFirstInspector.HSPHERE.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[DepthFirstInspector.CYLINDER.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[DepthFirstInspector.HCYLINDER.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }
   }
}
