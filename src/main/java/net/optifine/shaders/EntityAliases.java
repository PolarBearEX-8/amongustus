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

public class EntityAliases {
   private static int[] entityAliases = null;
   private static boolean updateOnResourcesReloaded;

   public static void resourcesReloaded() {
      if (updateOnResourcesReloaded) {
         updateOnResourcesReloaded = false;
         update(Shaders.getShaderPack());
      }
   }

   public static void reset() {
      entityAliases = null;
   }

   private static void addToList(List list, int n, int n2) {
      while (list.size() <= n) {
         list.add(-1);
      }

      list.set(n, n2);
   }

   public static int getMappedEntityId(int n) {
      if (entityAliases == null) {
         return -1;
      } else {
         return n >= 0 && n < entityAliases.length ? entityAliases[n] : -1;
      }
   }

   public static void update(IShaderPack shaderPack) {
      reset();
      if (shaderPack != null) {
         ArrayList list = new ArrayList();
         String str = "/shaders/entity.properties";
         InputStream resourceAsStream = shaderPack.getResourceAsStream(str);
         if (resourceAsStream != null) {
            load(resourceAsStream, str, list);
         }

         loadModEntityAliases(list);
         if (!list.isEmpty()) {
            entityAliases = toArray(list);
         }
      }
   }

   private static void loadModEntityAliases(List list) {
   }

   private static void load(InputStream in, String str, List list) {
      if (in != null) {
         try {
            in = MacroProcessor.process(in, str);
            PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
            propertiesOrdered.load(in);
            in.close();
            Config.dbg("[Shaders] Parsing entity mappings: " + str);
            ConnectedParser connectedParser = new ConnectedParser("Shaders");

            for (Object obj : propertiesOrdered.keySet()) {
               String str2 = (String)obj;
               String property = propertiesOrdered.getProperty(str2);
               String str3 = "entity.";
               if (!str2.startsWith(str3)) {
                  Config.warn("[Shaders] Invalid entity ID: " + str2);
               } else {
                  int n = Config.parseInt(StrUtils.removePrefix(str2, str3), -1);
                  if (n < 0) {
                     Config.warn("[Shaders] Invalid entity alias ID: " + n);
                  } else {
                     int[] ints = connectedParser.parseEntities(property);
                     if (ints != null && ints.length >= 1) {
                        for (int i = 0; i < ints.length; i++) {
                           addToList(list, ints[i], n);
                        }
                     } else {
                        Config.warn("[Shaders] Invalid entity ID mapping: " + str2 + "=" + property);
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
}
