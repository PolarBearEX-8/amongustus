package net.optifine.shaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.optifine.config.Config;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.shaders.config.MacroProcessor;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;

public class BlockAliases {
   private static BlockAlias[][] blockAliases = (BlockAlias[][])null;
   private static PropertiesOrdered blockLayerProperties = null;
   private static boolean updateOnResourcesReloaded;

   public static void resourcesReloaded() {
      if (updateOnResourcesReloaded) {
         updateOnResourcesReloaded = false;
         update(Shaders.getShaderPack());
      }
   }

   private static void load(InputStream in, String str, List list) {
      if (in != null) {
         try {
            in = MacroProcessor.process(in, str);
            PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
            propertiesOrdered.load(in);
            in.close();
            Config.dbg("[Shaders] Parsing block mappings: " + str);
            ConnectedParser connectedParser = new ConnectedParser("Shaders");

            for (Object obj : propertiesOrdered.keySet()) {
               String str2 = (String)obj;
               String property = propertiesOrdered.getProperty(str2);
               if (str2.startsWith("layer.")) {
                  if (blockLayerProperties == null) {
                     blockLayerProperties = new PropertiesOrdered();
                  }

                  blockLayerProperties.put(str2, property);
               } else {
                  String str3 = "block.";
                  if (!str2.startsWith(str3)) {
                     Config.warn("[Shaders] Invalid block ID: " + str2);
                  } else {
                     int n = Config.parseInt(StrUtils.removePrefix(str2, str3), -1);
                     if (n < 0) {
                        Config.warn("[Shaders] Invalid block ID: " + str2);
                     } else {
                        MatchBlock[] matchBlocks = connectedParser.parseMatchBlocks(property);
                        if (matchBlocks != null && matchBlocks.length >= 1) {
                           addToList(list, new BlockAlias(n, matchBlocks));
                        } else {
                           Config.warn("[Shaders] Invalid block ID mapping: " + str2 + "=" + property);
                        }
                     }
                  }
               }
            }
         } catch (IOException e) {
            Config.warn("[Shaders] Error reading: " + str);
         }
      }
   }

   private static void loadModBlockAliases(List list) {
   }

   private static void addToList(List list, BlockAlias blockAlias) {
      int[] matchBlockIds = blockAlias.getMatchBlockIds();

      for (int i = 0; i < matchBlockIds.length; i++) {
         int n = matchBlockIds[i];

         while (n >= list.size()) {
            list.add(null);
         }

         List list2 = (List)list.get(n);
         if (list2 == null) {
            list2 = new ArrayList();
            list.set(n, list2);
         }

         list2.add(new BlockAlias(blockAlias.getBlockAliasId(), blockAlias.getMatchBlocks(n)));
      }
   }

   public static PropertiesOrdered getBlockLayerProperties() {
      return blockLayerProperties;
   }

   public static void update(IShaderPack shaderPack) {
      reset();
      if (shaderPack != null) {
         ArrayList list = new ArrayList();
         String str = "/shaders/block.properties";
         InputStream resourceAsStream = shaderPack.getResourceAsStream(str);
         if (resourceAsStream != null) {
            load(resourceAsStream, str, list);
         }

         loadModBlockAliases(list);
         if (list.size() > 0) {
            blockAliases = toArrays(list);
         }
      }
   }

   public static void reset() {
      blockAliases = (BlockAlias[][])null;
      blockLayerProperties = null;
   }

   private static BlockAlias[][] toArrays(List list) {
      BlockAlias[][] blockAliasArrs = new BlockAlias[list.size()][];

      for (int i = 0; i < blockAliasArrs.length; i++) {
         List list2 = (List)list.get(i);
         if (list2 != null) {
            blockAliasArrs[i] = (BlockAlias[])list2.toArray(new BlockAlias[list2.size()]);
         }
      }

      return blockAliasArrs;
   }

   public static int getMappedBlockId(int n, int n2) {
      if (blockAliases == null) {
         return n;
      } else if (n >= 0 && n < blockAliases.length) {
         BlockAlias[] blockAliasArr = blockAliases[n];
         if (blockAliasArr == null) {
            return n;
         } else {
            for (int i = 0; i < blockAliasArr.length; i++) {
               BlockAlias blockAlias = blockAliasArr[i];
               if (blockAlias.matches(n, n2)) {
                  return blockAlias.getBlockAliasId();
               }
            }

            return n;
         }
      } else {
         return n;
      }
   }
}
