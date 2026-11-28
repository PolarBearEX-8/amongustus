package net.optifine.util;

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
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;

public class ResUtils {

   public static Properties readProperties(String str, String str2) {
      ResourceLocation location = new ResourceLocation(str);

      try {
         InputStream resourceStream = Config.getResourceStream(location);
         if (resourceStream == null) {
            return null;
         } else {
            PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
            propertiesOrdered.load(resourceStream);
            resourceStream.close();
            Config.dbg("" + str2 + ": Loading " + str);
            return propertiesOrdered;
         }
      } catch (FileNotFoundException e) {
         return null;
      } catch (IOException e2) {
         Config.warn("" + str2 + ": Error reading " + str);
         return null;
      }
   }

   public static String[] collectFiles(IResourcePack resourcePack, String str, String str2, String[] strings) {
      return collectFiles2(resourcePack, new String[]{str}, new String[]{str2}, strings);
   }

   public static Properties readProperties2(InputStream in, String str) {
      if (in == null) {
         return null;
      } else {
         try {
            PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
            propertiesOrdered.load(in);
            in.close();
            return propertiesOrdered;
         } catch (FileNotFoundException e) {
            return null;
         } catch (IOException e2) {
            return null;
         }
      }
   }

   public static String[] collectFiles(IResourcePack resourcePack, String[] strings, String[] strings2) {
      return collectFiles2(resourcePack, strings, strings2, (String[])null);
   }

   private static boolean isLowercase(String str) {
      return str.equals(str.toLowerCase(Locale.ROOT));
   }

   private static String[] collectFilesZIP(File file, String[] strings, String[] strings2) {
      ArrayList list = new ArrayList();
      String str = "assets/minecraft/";

      try {
         ZipFile zipFile = new ZipFile(file);
         Enumeration en = zipFile.entries();

         while (en.hasMoreElements()) {
            String name = ((ZipEntry)en.nextElement()).getName();
            if (name.startsWith(str)) {
               name = name.substring(str.length());
               if (StrUtils.startsWith(name, strings) && StrUtils.endsWith(name, strings2)) {
                  list.add(name);
               }
            }
         }

         zipFile.close();
         return (String[])list.toArray(new String[list.size()]);
      } catch (IOException e) {
         e.printStackTrace();
         return new String[0];
      }
   }

   public static String[] collectFiles2(IResourcePack resourcePack, String[] strings, String[] strings2, String[] strings3) {
      if (resourcePack instanceof DefaultResourcePack) {
         return collectFilesFixed(resourcePack, strings3);
      } else if (!(resourcePack instanceof AbstractResourcePack)) {
         Config.warn("Unknown resource pack type: " + resourcePack);
         return new String[0];
      } else {
         File resourcePackFile = ((AbstractResourcePack)resourcePack).resourcePackFile;
         if (resourcePackFile == null) {
            return new String[0];
         } else if (resourcePackFile.isDirectory()) {
            return collectFilesFolder(resourcePackFile, "", strings, strings2);
         } else if (resourcePackFile.isFile()) {
            return collectFilesZIP(resourcePackFile, strings, strings2);
         } else {
            Config.warn("Unknown resource pack file: " + resourcePackFile);
            return new String[0];
         }
      }
   }

   public static String[] collectFiles(String[] strings, String[] strings2) {
      LinkedHashSet set = new LinkedHashSet();
      IResourcePack[] resourcePacks = Config.getResourcePacks();

      for (int i = 0; i < resourcePacks.length; i++) {
         set.addAll(Arrays.asList(collectFiles2(resourcePacks[i], strings, strings2, (String[])null)));
      }

      return (String[])set.toArray(new String[set.size()]);
   }

   private static String[] collectFilesFixed(IResourcePack resourcePack, String[] strings) {
      if (strings == null) {
         return new String[0];
      } else {
         ArrayList list = new ArrayList();

         for (int i = 0; i < strings.length; i++) {
            String str = strings[i];
            if (resourcePack.resourceExists(new ResourceLocation(str))) {
               list.add(str);
            }
         }

         return (String[])list.toArray(new String[list.size()]);
      }
   }

   public static String[] collectFiles2(String str, String str2) {
      return collectFiles(new String[]{str}, new String[]{str2});
   }

   private static String[] collectFilesFolder(File file, String str, String[] strings, String[] strings2) {
      ArrayList list = new ArrayList();
      String str2 = "assets/minecraft/";
      File[] files = file.listFiles();
      if (files == null) {
         return new String[0];
      } else {
         for (int i = 0; i < files.length; i++) {
            File file2 = files[i];
            if (file2.isFile()) {
               String str3 = str + file2.getName();
               if (str3.startsWith(str2)) {
                  str3 = str3.substring(str2.length());
                  if (StrUtils.startsWith(str3, strings) && StrUtils.endsWith(str3, strings2)) {
                     list.add(str3);
                  }
               }
            } else if (file2.isDirectory()) {
               String[] strings3 = collectFilesFolder(file2, str + file2.getName() + "/", strings, strings2);

               for (int j = 0; j < strings3.length; j++) {
                  list.add(strings3[j]);
               }
            }
         }

         return (String[])list.toArray(new String[list.size()]);
      }
   }
}
