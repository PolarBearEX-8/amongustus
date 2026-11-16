package client.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class PngManager {
   public static InterpolationChecker[] interpolationCheckerArray = null;
   public static int count = 0;

   public static void updateState() {
      interpolationCheckerArray = null;
      count = 0;
      org.apache.commons.lang3.tuple.Pair pair = getPair();
      interpolationCheckerArray = (InterpolationChecker[])pair.getLeft();
      count = (Integer)pair.getRight();
   }

   public static void applyText(String text) {
      BuildManager.applyText4("CustomLoadingScreen: " + text);
   }

   public static InterpolationChecker getInterpolationChecker() {
      if (interpolationCheckerArray == null) {
         return null;
      } else {
         int count2 = ClientManager.count - count;
         InterpolationChecker interpolationChecker = null;
         if (count2 >= 0 && count2 < interpolationCheckerArray.length) {
            interpolationChecker = interpolationCheckerArray[count2];
         }

         return interpolationChecker;
      }
   }

   public static void applyTextByText(String text) {
      BuildManager.applyText3("CustomLoadingScreen: " + text);
   }

   private static org.apache.commons.lang3.tuple.Pair getPair() {
      String text = "optifine/gui/loading/background";
      String text2 = ".png";
      String[] textArray = AssetsMinecraftManager.resolveTextArray(text, text2);
      HashMap map = new HashMap();

      for (int index = 0; index < textArray.length; index++) {
         String text3 = textArray[index];
         String text4 = SplineCollector.resolveText3(text3, text, text2);
         int count2 = BuildManager.computeCount(text4, Integer.MIN_VALUE);
         if (count2 == Integer.MIN_VALUE) {
            applyTextByText("Invalid dimension ID: " + text4 + ", path: " + text3);
         } else {
            map.put(count2, text3);
         }
      }

      Set keys = map.keySet();
      Integer[] countArray = (Integer[]) keys.toArray(new Integer[keys.size()]);
      Arrays.sort(countArray);
      if (countArray.length <= 0) {
         return new ImmutablePair(null, 0);
      } else {
         Properties properties = AssetsMinecraftManager.resolveProperties("optifine/gui/loading/loading.properties", "CustomLoadingScreens");
         int count3 = countArray[0];
         InterpolationChecker[] interpolationCheckerArray2 = new InterpolationChecker[countArray[countArray.length - 1] - count3 + 1];

         for (int index2 = 0; index2 < countArray.length; index2++) {
            Integer count4 = countArray[index2];
            String text5 = (String)map.get(count4);
            interpolationCheckerArray2[count4 - count3] = InterpolationChecker.resolveInterpolationChecker(text5, count4, properties);
         }

         return new ImmutablePair(interpolationCheckerArray2, count3);
      }
   }
}
