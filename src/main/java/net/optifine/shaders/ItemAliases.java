package net.optifine.shaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.optifine.config.Config;
import net.optifine.config.ConnectedParser;
import net.optifine.shaders.config.MacroProcessor;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;

public class ItemAliases {
   private static int[] itemAliases = null;
   private static boolean updateOnResourcesReloaded;
   private static final int NO_ALIAS = Integer.MIN_VALUE;

   public static void update(IShaderPack shaderPack) {
      reset();
      if (shaderPack != null) {
         ArrayList list = new ArrayList();
         String str = "/shaders/item.properties";
         InputStream resourceAsStream = shaderPack.getResourceAsStream(str);
         if (resourceAsStream != null) {
            load(resourceAsStream, str, list);
         }

         loadModItemAliases(list);
         if (!list.isEmpty()) {
            itemAliases = toArray(list);
         }
      }
   }

   private static void addToList(List list, int n, int n2) {
      while (list.size() <= n) {
         list.add(Integer.MIN_VALUE);
      }

      list.set(n, n2);
   }

   private static void loadModItemAliases(List list) {
   }

   private static void load(InputStream in, String str, List list) {
      if (in != null) {
         try {
            in = MacroProcessor.process(in, str);
            PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
            propertiesOrdered.load(in);
            in.close();
            Config.dbg("[Shaders] Parsing item mappings: " + str);
            ConnectedParser connectedParser = new ConnectedParser("Shaders");

            for (Object obj : propertiesOrdered.keySet()) {
               String str2 = (String)obj;
               String property = propertiesOrdered.getProperty(str2);
               String str3 = "item.";
               if (!str2.startsWith(str3)) {
                  Config.warn("[Shaders] Invalid item ID: " + str2);
               } else {
                  int n = Config.parseInt(StrUtils.removePrefix(str2, str3), -1);
                  if (n < 0) {
                     Config.warn("[Shaders] Invalid item alias ID: " + n);
                  } else {
                     int[] ints = connectedParser.parseItems(property);
                     if (ints != null && ints.length >= 1) {
                        for (int i = 0; i < ints.length; i++) {
                           addToList(list, ints[i], n);
                        }
                     } else {
                        Config.warn("[Shaders] Invalid item ID mapping: " + str2 + "=" + property);
                     }
                  }
               }
            }
         } catch (IOException e) {
            Config.warn("[Shaders] Error reading: " + str);
         }
      }
   }

   private static int[] toArray(List list) {
      int[] ints = new int[list.size()];

      for (int i = 0; i < ints.length; i++) {
         ints[i] = (Integer)list.get(i);
      }

      return ints;
   }

   public static void reset() {
      itemAliases = null;
   }

   public static void resourcesReloaded() {
      if (updateOnResourcesReloaded) {
         updateOnResourcesReloaded = false;
         update(Shaders.getShaderPack());
      }
   }

   public static int getMappedItemId(int n) {
      if (itemAliases == null) {
         return n;
      } else if (n >= 0 && n < itemAliases.length) {
         int n2 = itemAliases[n];
         return n2 == Integer.MIN_VALUE ? n : n2;
      } else {
         return n;
      }
   }
}
