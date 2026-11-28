package net.optifine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import net.minecraft.network.PacketThreadUtil;
import net.optifine.config.Config;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class CustomLoadingScreens {
   private static CustomLoadingScreen[] screens = null;
   private static int screensMinDimensionId = 0;

   private static Pair parseScreens() {
      String str = "optifine/gui/loading/background";
      String str2 = ".png";
      String[] strings = ResUtils.collectFiles2(str, str2);
      HashMap map = new HashMap();

      for (int i = 0; i < strings.length; i++) {
         String str3 = strings[i];
         String str4 = StrUtils.removePrefixSuffix(str3, str, str2);
         int n = Config.parseInt(str4, Integer.MIN_VALUE);
         if (n == Integer.MIN_VALUE) {
            warn("Invalid dimension ID: " + str4 + ", path: " + str3);
         } else {
            map.put(n, str3);
         }
      }

      Set keys = map.keySet();
      Integer[] nums = (Integer[])keys.toArray(new Integer[keys.size()]);
      Arrays.sort(nums);
      if (nums.length <= 0) {
         return new ImmutablePair(null, 0);
      } else {
         Properties properties = ResUtils.readProperties("optifine/gui/loading/loading.properties", "CustomLoadingScreens");
         int n2 = nums[0];
         int n3 = nums[nums.length - 1];
         CustomLoadingScreen[] customLoadingScreens = new CustomLoadingScreen[n3 - n2 + 1];

         for (int j = 0; j < nums.length; j++) {
            Integer num = nums[j];
            String str6 = (String)map.get(num);
            customLoadingScreens[num - n2] = CustomLoadingScreen.parseScreen(str6, num, properties);
         }

         return new ImmutablePair(customLoadingScreens, n2);
      }
   }

   public static CustomLoadingScreen getCustomLoadingScreen() {
      if (screens == null) {
         return null;
      } else {
         int n = PacketThreadUtil.lastDimensionId - screensMinDimensionId;
         CustomLoadingScreen customLoadingScreen = null;
         if (n >= 0 && n < screens.length) {
            customLoadingScreen = screens[n];
         }

         return customLoadingScreen;
      }
   }

   public static void dbg(String str) {
      Config.dbg("CustomLoadingScreen: " + str);
   }

   public static void warn(String str) {
      Config.warn("CustomLoadingScreen: " + str);
   }

   public static void update() {
      screens = null;
      screensMinDimensionId = 0;
      Pair pair = parseScreens();
      screens = (CustomLoadingScreen[])pair.getLeft();
      screensMinDimensionId = (Integer)pair.getRight();
   }
}
