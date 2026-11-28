package net.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.util.MathUtils;
import net.optifine.util.PropertiesOrdered;

public class CustomPanorama {
   private static CustomPanoramaProperties customPanoramaProperties = null;
   private static final Random random = new Random();

   private static String[] getPanoramaFolders() {
      ArrayList list = new ArrayList();
      list.add("textures/gui/title/background");

      for (int i = 0; i < 100; i++) {
         String str = "optifine/gui/background" + i;
         String str2 = str + "/panorama_0.png";
         if (Config.hasResource(new ResourceLocation(str2))) {
            list.add(str);
         }
      }

      return (String[])list.toArray(new String[list.size()]);
   }

   private static int[] getWeights(Properties[] propertiesArr) {
      int[] ints = new int[propertiesArr.length];

      for (int i = 0; i < ints.length; i++) {
         Properties properties = propertiesArr[i];
         if (properties == null) {
            properties = propertiesArr[0];
         }

         if (properties == null) {
            ints[i] = 1;
         } else {
            ints[i] = Config.parseInt(properties.getProperty("weight", (String)null), 1);
         }
      }

      return ints;
   }

   public static CustomPanoramaProperties getCustomPanoramaProperties() {
      return customPanoramaProperties;
   }

   public static void update() {
      customPanoramaProperties = null;
      String[] panoramaFolders = getPanoramaFolders();
      if (panoramaFolders.length > 1) {
         Properties[] panoramaProperties = getPanoramaProperties(panoramaFolders);
         int randomIndex = getRandomIndex(getWeights(panoramaProperties));
         String str = panoramaFolders[randomIndex];
         Object obj = panoramaProperties[randomIndex];
         if (obj == null) {
            obj = panoramaProperties[0];
         }

         if (obj == null) {
            obj = new PropertiesOrdered();
         }

         customPanoramaProperties = new CustomPanoramaProperties(str, (Properties)obj);
      }
   }

   private static int getRandomIndex(int[] ints) {
      int sum = MathUtils.getSum(ints);
      int n = random.nextInt(sum);
      int n2 = 0;

      for (int i = 0; i < ints.length; i++) {
         n2 += ints[i];
         if (n2 > n) {
            return i;
         }
      }

      return ints.length - 1;
   }

   private static Properties[] getPanoramaProperties(String[] strings) {
      Properties[] propertiesArr = new Properties[strings.length];

      for (int i = 0; i < strings.length; i++) {
         String str = strings[i];
         if (i == 0) {
            str = "optifine/gui";
         } else {
            Config.dbg("CustomPanorama: " + str);
         }

         ResourceLocation location = new ResourceLocation(str + "/background.properties");

         try {
            InputStream resourceStream = Config.getResourceStream(location);
            if (resourceStream != null) {
               PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
               propertiesOrdered.load(resourceStream);
               Config.dbg("CustomPanorama: " + location.getResourcePath());
               propertiesArr[i] = propertiesOrdered;
               resourceStream.close();
            }
         } catch (IOException e) {
         }
      }

      return propertiesArr;
   }
}
