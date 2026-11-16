package client.render;

import client.util.BuildManager;
import client.util.PropertiesAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;

public class AuxiliaryTexturesManager {
   public static ResourceLocation resolveResourceLocation(ResourceLocation location) {
      if (!BuildManager.isActive69()) {
         return location;
      } else if (location == null) {
         return location;
      } else if (!BuildManager.currentThread()) {
         return location;
      } else {
         String text = location.getText1();
         String text2 = "textures/";
         String text3 = "mcpatcher/";
         if (!text.startsWith(text2)) {
            return location;
         } else {
            String text4 = text.substring(text2.length());
            String text5 = text3 + text4;
            ResourceLocation location2 = new ResourceLocation(location.getText(), text5);
            return BuildManager.matchesIResourceManager(BuildManager.getIResourceManager(), location2) ? location2 : location;
         }
      }
   }

   public static Properties resolvePropertiesByResourceLocation(ResourceLocation location) {
      String text = location.getText1();
      PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
      String text2 = ".png";
      if (!text.endsWith(text2)) {
         return propertiesAdapter;
      } else {
         String text3 = text.substring(0, text.length() - text2.length()) + ".properties";

         try {
            ResourceLocation location2 = new ResourceLocation(location.getText(), text3);
            InputStream inputStream = BuildManager.resolveInputStream(BuildManager.getIResourceManager(), location2);
            if (inputStream == null) {
               return propertiesAdapter;
            }

            BuildManager.applyText("Loading " + text3);
            propertiesAdapter.load(inputStream);
            inputStream.close();
         } catch (FileNotFoundException fileNotFoundException) {
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }

         return propertiesAdapter;
      }
   }

   public static float computeFactor(Properties properties, String text, float factor) {
      String property = properties.getProperty(text);
      if (property == null) {
         return factor;
      } else {
         float factor2 = BuildManager.computeFactor(property, Float.MIN_VALUE);
         if (factor2 == Float.MIN_VALUE) {
            BuildManager.applyText3("Invalid value for " + text + ": " + property);
            return factor;
         } else {
            return factor2;
         }
      }
   }

   public static void applyProperties(Properties properties, float[] factorArray) {
      for (Object element : properties.keySet()) {
         String text = (String)element;
         String text2 = "width.";
         if (text.startsWith(text2)) {
            int count = BuildManager.computeCount(text.substring(text2.length()), -1);
            if (count >= 0 && count < factorArray.length) {
               float factor = BuildManager.computeFactor(properties.getProperty(text), -1.0F);
               if (factor >= 0.0F) {
                  factorArray[count] = factor;
               }
            }
         }
      }
   }

   public static boolean matchesProperties(Properties properties, String text, boolean flag) {
      String property = properties.getProperty(text);
      if (property == null) {
         return flag;
      } else {
         String text2 = property.toLowerCase().trim();
         if (text2.equals("true") || text2.equals("on")) {
            return true;
         } else if (!text2.equals("false") && !text2.equals("off")) {
            BuildManager.applyText3("Invalid value for " + text + ": " + property);
            return flag;
         } else {
            return false;
         }
      }
   }
}
