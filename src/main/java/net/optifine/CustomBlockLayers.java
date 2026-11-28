package net.optifine;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.config.Config;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.shaders.BlockAliases;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;

public class CustomBlockLayers {
   private static EnumWorldBlockLayer[] renderLayers = null;
   public static boolean active = false;

   private static void readLayers(String str, Properties properties, List list) {
      Config.dbg("CustomBlockLayers: " + str);
      readLayer("solid", EnumWorldBlockLayer.SOLID, properties, list);
      readLayer("cutout", EnumWorldBlockLayer.CUTOUT, properties, list);
      readLayer("cutout_mipped", EnumWorldBlockLayer.CUTOUT_MIPPED, properties, list);
      readLayer("translucent", EnumWorldBlockLayer.TRANSLUCENT, properties, list);
   }

   private static void readLayer(String str, EnumWorldBlockLayer enumWorldBlockLayer, Properties properties, List list) {
      String property = properties.getProperty("layer." + str);
      if (property != null) {
         MatchBlock[] matchBlocks = new ConnectedParser("CustomBlockLayers").parseMatchBlocks(property);
         if (matchBlocks != null) {
            for (int i = 0; i < matchBlocks.length; i++) {
               int blockId = matchBlocks[i].getBlockId();
               if (blockId > 0) {
                  while (list.size() < blockId + 1) {
                     list.add(null);
                  }

                  if (list.get(blockId) != null) {
                     Config.warn("CustomBlockLayers: Block layer is already set, block: " + blockId + ", layer: " + str);
                  }

                  list.set(blockId, enumWorldBlockLayer);
               }
            }
         }
      }
   }

   public static EnumWorldBlockLayer getRenderLayer(IBlockState state) {
      if (renderLayers == null) {
         return null;
      } else if (state.getBlock().isOpaqueCube()) {
         return null;
      } else if (!(state instanceof BlockStateBase)) {
         return null;
      } else {
         int blockId = ((BlockStateBase)state).getBlockId();
         return blockId > 0 && blockId < renderLayers.length ? renderLayers[blockId] : null;
      }
   }

   public static boolean isActive() {
      return active;
   }

   public static void update() {
      renderLayers = null;
      active = false;
      ArrayList list = new ArrayList();
      String str = "optifine/block.properties";
      Properties properties = ResUtils.readProperties(str, "CustomBlockLayers");
      if (properties != null) {
         readLayers(str, properties, list);
      }

      if (Config.isShaderPackLoaded()) {
         PropertiesOrdered blockLayerProperties = BlockAliases.getBlockLayerProperties();
         if (blockLayerProperties != null) {
            readLayers("shaders/block.properties", blockLayerProperties, list);
         }
      }

      if (!list.isEmpty()) {
         renderLayers = (EnumWorldBlockLayer[])list.toArray(new EnumWorldBlockLayer[list.size()]);
         active = true;
      }
   }
}
