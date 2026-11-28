package net.optifine;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class Lang {
   private static final Splitter splitter = Splitter.on('=').limit(2);
   private static final Pattern pattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");

   public static String getDefault() {
      return I18n.format("generator.default", new Object[0]);
   }

   public static String get(String str, String str2) {
      String str3 = I18n.format(str, new Object[0]);
      return str3 != null && !str3.equals(str) ? str3 : str2;
   }

   public static String getFancy() {
      return I18n.format("options.graphics.fancy", new Object[0]);
   }

   public static String getFast() {
      return I18n.format("options.graphics.fast", new Object[0]);
   }

   public static String get(String str) {
      return I18n.format(str, new Object[0]);
   }

   public static String getOn() {
      return I18n.format("options.on", new Object[0]);
   }

   public static void loadLocaleData(InputStream in, Map map) throws IOException {
      Iterator it = IOUtils.readLines(in, Charsets.UTF_8).iterator();
      in.close();

      while (it.hasNext()) {
         String str = (String)it.next();
         if (!str.isEmpty() && str.charAt(0) != '#') {
            String[] strings = (String[])Iterables.toArray(splitter.split(str), String.class);
            if (strings != null && strings.length == 2) {
               map.put(strings[0], pattern.matcher(strings[1]).replaceAll("%$1s"));
            }
         }
      }
   }

   private static void loadResources(IResourcePack resourcePack, String[] strings, Map map) {
      try {
         for (int i = 0; i < strings.length; i++) {
            String str = strings[i];
            ResourceLocation location = new ResourceLocation(str);
            if (resourcePack.resourceExists(location)) {
               InputStream inputStream = resourcePack.getInputStream(location);
               if (inputStream != null) {
                  loadLocaleData(inputStream, map);
               }
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static String getOff() {
      return I18n.format("options.off", new Object[0]);
   }

   public static void resourcesReloaded() {
      Map localeProperties = I18n.getLocaleProperties();
      ArrayList list = new ArrayList();
      String str = "optifine/lang/";
      String str2 = "en_US";
      String str3 = ".lang";
      list.add(str + str2 + str3);
      if (!Config.getGameSettings().language.equals(str2)) {
         list.add(str + Config.getGameSettings().language + str3);
      }

      String[] strings = (String[])list.toArray(new String[list.size()]);
      loadResources(Config.getDefaultResourcePack(), strings, localeProperties);
      IResourcePack[] resourcePacks = Config.getResourcePacks();

      for (int i = 0; i < resourcePacks.length; i++) {
         loadResources(resourcePacks[i], strings, localeProperties);
      }
   }
}
