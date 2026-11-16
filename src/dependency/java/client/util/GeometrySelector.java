package client.util;

public class GeometrySelector {
   public static boolean matchesServiceStringEntry(ServiceStringEntry serviceStringEntry, ServiceStringEntry[] serviceStringEntryArray) {
      if (serviceStringEntryArray == null) {
         return true;
      } else {
         for (int index = 0; index < serviceStringEntryArray.length; index++) {
            if (serviceStringEntryArray[index] == serviceStringEntry) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean matchesIndexArray(int count, int[] countArray) {
      if (countArray == null) {
         return true;
      } else {
         for (int index = 0; index < countArray.length; index++) {
            if (countArray[index] == count) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean matchesBiomeIDIsOutOfBoundsManage(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager, BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray) {
      if (biomeIDIsOutOfBoundsManagerArray == null) {
         return true;
      } else {
         for (int index = 0; index < biomeIDIsOutOfBoundsManagerArray.length; index++) {
            if (biomeIDIsOutOfBoundsManagerArray[index] == biomeIDIsOutOfBoundsManager) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean matchesIBlockStateAdapter(IBlockStateAdapter iBlockStateAdapter, SmoothMatcher[] smoothMatcherArray) {
      if (smoothMatcherArray == null) {
         return true;
      } else {
         for (int index = 0; index < smoothMatcherArray.length; index++) {
            if (smoothMatcherArray[index].matchesIBlockStateAdapter(iBlockStateAdapter)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean matchesSmoothMatcherArray(int count, int count2, SmoothMatcher[] smoothMatcherArray) {
      if (smoothMatcherArray == null) {
         return true;
      } else {
         for (int index = 0; index < smoothMatcherArray.length; index++) {
            if (smoothMatcherArray[index].checkIndex(count, count2)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean matchesSmoothMatcherArray(int count, SmoothMatcher[] smoothMatcherArray) {
      if (smoothMatcherArray == null) {
         return true;
      } else {
         for (int index = 0; index < smoothMatcherArray.length; index++) {
            if (smoothMatcherArray[index].getCount() == count) {
               return true;
            }
         }

         return false;
      }
   }
}
