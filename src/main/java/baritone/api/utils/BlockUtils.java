package baritone.api.utils;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class BlockUtils {
   private static transient Map resourceCache = new HashMap();

   public static Block stringToBlockNullable(String str) {
      Block block = (Block)resourceCache.get(str);
      if (block != null) {
         return block;
      } else if (resourceCache.containsKey(str)) {
         return null;
      } else {
         block = Block.getBlockFromName(str.contains(":") ? str : "minecraft:" + str);
         HashMap map = new HashMap(resourceCache);
         map.put(str, block);
         resourceCache = map;
         return block;
      }
   }

   public static Block stringToBlockRequired(String str) {
      Block block = stringToBlockNullable(str);
      if (block == null) {
         throw new IllegalArgumentException(String.format("Invalid block name %s", str));
      } else {
         return block;
      }
   }

   public static String blockToString(Block block) {
      ResourceLocation nameForObject = (ResourceLocation)Block.blockRegistry.getNameForObject(block);
      String resourcePath = nameForObject.getResourcePath();
      if (!nameForObject.getResourceDomain().equals("minecraft")) {
         resourcePath = nameForObject.toString();
      }

      return resourcePath;
   }

   private BlockUtils() {
   }
}
