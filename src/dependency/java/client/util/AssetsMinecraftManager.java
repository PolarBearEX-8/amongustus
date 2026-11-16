package client.util;

import client.event.PreUpdateProcessor;
import client.event.SSSListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.util.ResourceLocation;

public class AssetsMinecraftManager {
   private static String[] resolveTextArray(File file, String text, String[] textArray, String[] textArray2) {
      ArrayList list = new ArrayList();
      String text2 = "assets/minecraft/";
      File[] fileArray = file.listFiles();
      if (fileArray == null) {
         return new String[0];
      } else {
         for (int index = 0; index < fileArray.length; index++) {
            File file2 = fileArray[index];
            if (file2.isFile()) {
               String text3 = text + file2.getName();
               if (text3.startsWith(text2)) {
                  String text4 = text3.substring(text2.length());
                  if (SplineCollector.matchesTextByText(text4, textArray) && SplineCollector.matchesText(text4, textArray2)) {
                     list.add(text4);
                  }
               }
            } else if (file2.isDirectory()) {
               String text5 = text + file2.getName() + "/";
               String[] textArray3 = resolveTextArray(file2, text5, textArray, textArray2);

               for (int index2 = 0; index2 < textArray3.length; index2++) {
                  String text6 = textArray3[index2];
                  list.add(text6);
               }
            }
         }

         return ((String[])list.toArray(new String[list.size()]));
      }
   }

   private static String[] resolveTextArray(PreUpdateProcessor preUpdateProcessor, String[] textArray) {
      if (textArray == null) {
         return new String[0];
      } else {
         ArrayList list = new ArrayList();

         for (int index = 0; index < textArray.length; index++) {
            String text = textArray[index];
            ResourceLocation location = new ResourceLocation(text);
            if (preUpdateProcessor.matchesResourceLocation(location)) {
               list.add(text);
            }
         }

         return ((String[])list.toArray(new String[list.size()]));
      }
   }

   public static String[] resolveTextArray(String text, String text2) {
      return resolveTextArray(new String[]{text}, new String[]{text2});
   }

   private static boolean matchesText(String text) {
      return text.equals(text.toLowerCase(Locale.ROOT));
   }

   public static Properties resolveProperties(String text, String text2) {
      ResourceLocation location = new ResourceLocation(text);

      try {
         InputStream inputStream = BuildManager.resolveInputStream(location);
         if (inputStream == null) {
            return null;
         } else {
            PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
            propertiesAdapter.load(inputStream);
            inputStream.close();
            BuildManager.applyText4("" + text2 + ": Loading " + text);
            return propertiesAdapter;
         }
      } catch (FileNotFoundException fileNotFoundException) {
         return null;
      } catch (IOException ioException) {
         BuildManager.applyText3("" + text2 + ": Error reading " + text);
         return null;
      }
   }

   public static String[] resolveTextArray(String[] textArray, String[] textArray2) {
      LinkedHashSet set = new LinkedHashSet();
      PreUpdateProcessor[] preUpdateProcessorArray = BuildManager.getPreUpdateProcessorArray();

      for (int index = 0; index < preUpdateProcessorArray.length; index++) {
         String[] textArray3 = resolveTextArray(preUpdateProcessorArray[index], textArray, textArray2, (String[])null);
         set.addAll(Arrays.asList(textArray3));
      }

      return ((String[])set.toArray(new String[set.size()]));
   }

   public static String[] resolveTextArray(PreUpdateProcessor preUpdateProcessor, String text, String text2, String[] textArray) {
      return resolveTextArray(preUpdateProcessor, new String[]{text}, new String[]{text2}, textArray);
   }

   public static Properties resolveProperties(InputStream inputStream, String text) {
      if (inputStream == null) {
         return null;
      } else {
         try {
            PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
            propertiesAdapter.load(inputStream);
            inputStream.close();
            return propertiesAdapter;
         } catch (FileNotFoundException fileNotFoundException) {
            return null;
         } catch (IOException ioException) {
            return null;
         }
      }
   }

   public static String[] resolveTextArray(PreUpdateProcessor preUpdateProcessor, String[] textArray, String[] textArray2, String[] textArray3) {
      if (preUpdateProcessor instanceof IReloadableResourceManager) {
         return resolveTextArray(preUpdateProcessor, textArray3);
      } else if (!(preUpdateProcessor instanceof SSSListener)) {
         BuildManager.applyText3("Unknown resource pack type: " + preUpdateProcessor);
         return new String[0];
      } else {
         File file = ((SSSListener)preUpdateProcessor).file;
         if (file == null) {
            return new String[0];
         } else if (file.isDirectory()) {
            return resolveTextArray(file, "", textArray, textArray2);
         } else if (file.isFile()) {
            return resolveTextArray(file, textArray, textArray2);
         } else {
            BuildManager.applyText3("Unknown resource pack file: " + file);
            return new String[0];
         }
      }
   }

   public static String[] resolveTextArray(PreUpdateProcessor preUpdateProcessor, String[] textArray, String[] textArray2) {
      return resolveTextArray(preUpdateProcessor, textArray, textArray2, (String[])null);
   }

   private static String[] resolveTextArray(File file, String[] textArray, String[] textArray2) {
      ArrayList list = new ArrayList();
      String text = "assets/minecraft/";

      try {
         ZipFile zipFile = new ZipFile(file);
         Enumeration enumeration = zipFile.entries();

         while (enumeration.hasMoreElements()) {
            String name = ((ZipEntry)enumeration.nextElement()).getName();
            if (name.startsWith(text)) {
               String text2 = name.substring(text.length());
               if (SplineCollector.matchesTextByText(text2, textArray) && SplineCollector.matchesText(text2, textArray2)) {
                  list.add(text2);
               }
            }
         }

         zipFile.close();
         return ((String[])list.toArray(new String[list.size()]));
      } catch (IOException ioException) {
         ioException.printStackTrace();
         return new String[0];
      }
   }
}
