package net.optifine.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;

public class FontUtils {

   public static void readCustomCharWidths(Properties properties, float[] floats) {
      for (Object obj : properties.keySet()) {
         String str = (String)obj;
         String str2 = "width.";
         if (str.startsWith(str2)) {
            int n = Config.parseInt(str.substring(str2.length()), -1);
            if (n >= 0 && n < floats.length) {
               float f = Config.parseFloat(properties.getProperty(str), -1.0F);
               if (f >= 0.0F) {
                  floats[n] = f;
               }
            }
         }
      }
   }

   public static float readFloat(Properties properties, String str, float f) {
      String property = properties.getProperty(str);
      if (property == null) {
         return f;
      } else {
         float f2 = Config.parseFloat(property, Float.MIN_VALUE);
         if (f2 == Float.MIN_VALUE) {
            Config.warn("Invalid value for " + str + ": " + property);
            return f;
         } else {
            return f2;
         }
      }
   }

   public static boolean readBoolean(Properties properties, String str, boolean flag) {
      String property = properties.getProperty(str);
      if (property == null) {
         return flag;
      } else {
         String str2 = property.toLowerCase().trim();
         if (str2.equals("true") || str2.equals("on")) {
            return true;
         } else if (!str2.equals("false") && !str2.equals("off")) {
            Config.warn("Invalid value for " + str + ": " + property);
            return flag;
         } else {
            return false;
         }
      }
   }

   public static Properties readFontProperties(ResourceLocation location) {
      String resourcePath = location.getResourcePath();
      PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
      String str = ".png";
      if (!resourcePath.endsWith(str)) {
         return propertiesOrdered;
      } else {
         String str2 = resourcePath.substring(0, resourcePath.length() - str.length()) + ".properties";

         try {
            ResourceLocation location2 = new ResourceLocation(location.getResourceDomain(), str2);
            InputStream resourceStream = Config.getResourceStream(Config.getResourceManager(), location2);
            if (resourceStream == null) {
               return propertiesOrdered;
            }

            Config.log("Loading " + str2);
            propertiesOrdered.load(resourceStream);
            resourceStream.close();
         } catch (FileNotFoundException e) {
         } catch (IOException e2) {
            e2.printStackTrace();
         }

         return propertiesOrdered;
      }
   }

   public static ResourceLocation getHdFontLocation(ResourceLocation location) {
      if (!Config.isCustomFonts()) {
         return location;
      } else if (location == null) {
         return location;
      } else if (!Config.isMinecraftThread()) {
         return location;
      } else {
         String resourcePath = location.getResourcePath();
         String str = "textures/";
         String str2 = "mcpatcher/";
         if (!resourcePath.startsWith(str)) {
            return location;
         } else {
            resourcePath = resourcePath.substring(str.length());
            resourcePath = str2 + resourcePath;
            ResourceLocation location2 = new ResourceLocation(location.getResourceDomain(), resourcePath);
            return Config.hasResource(Config.getResourceManager(), location2) ? location2 : location;
         }
      }
   }
}
