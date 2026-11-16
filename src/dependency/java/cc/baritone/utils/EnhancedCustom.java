package cc.baritone.utils;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class EnhancedCustom {
   public static transient Map map = new HashMap();

   private EnhancedCustom() {
   }

   public static Block resolveBlock(String text) {
      Block block = (Block)map.get(text);
      if (block != null) {
         return block;
      } else if (map.containsKey(text)) {
         return null;
      } else {
         Block blockFromName = Block.getBlockFromName(text.contains(":") ? text : "minecraft:" + text);
         HashMap map2 = new HashMap(map);
         map2.put(text, blockFromName);
         map = map2;
         return blockFromName;
      }
   }

   public static Block resolveBlockByText(String text) {
      Block block = resolveBlock(text);
      if (block == null) {
         throw new IllegalArgumentException(String.format("Invalid block name %s", text));
      } else {
         return block;
      }
   }

   public static String resolveText(Block block) {
      ResourceLocation object = (ResourceLocation)Block.iterableExtensionBridge.getObject2(block);
      String text = object.getText1();
      if (!object.getText().equals("minecraft")) {
         text = object.toString();
      }

      return text;
   }
}
