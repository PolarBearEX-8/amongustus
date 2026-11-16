package cc.baritone.utils;

public class BackupZIPGuiScreenCompositeCustomSwitchMap {
   public static final int[] countArray = new int[CompositeCustom.values().length];

   static {
      try {
         countArray[CompositeCustom.NONE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[CompositeCustom.FOLDER.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[CompositeCustom.ZIP.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
