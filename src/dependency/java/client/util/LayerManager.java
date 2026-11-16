package client.util;

import client.render.AuxiliaryShadersManager;
import client.setting.BlockSolidType;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LayerManager {
   public static BlockSolidType[] blockSolidTypeArray = null;
   public static boolean enabled = false;

   private static void applyText(String text, BlockSolidType blockSolidType, Properties properties, List list) {
      String text2 = "layer." + text;
      String property = properties.getProperty(text2);
      if (property != null) {
         SmoothMatcher[] smoothMatcherArray = new NetherManager("CustomBlockLayers").resolveSmoothMatcherArrayByText(property);
         if (smoothMatcherArray != null) {
            for (int index = 0; index < smoothMatcherArray.length; index++) {
               int count = smoothMatcherArray[index].getCount();
               if (count > 0) {
                  while (list.size() < count + 1) {
                     list.add(null);
                  }

                  if (list.get(count) != null) {
                     BuildManager.applyText3("CustomBlockLayers: Block layer is already set, block: " + count + ", layer: " + text);
                  }

                  list.set(count, blockSolidType);
               }
            }
         }
      }
   }

   public static BlockSolidType resolveBlockSolidType(IBlockState blockState) {
      if (blockSolidTypeArray == null) {
         return null;
      } else if (blockState.getBlock().processAction15()) {
         return null;
      } else if (!(blockState instanceof IBlockStateAdapter)) {
         return null;
      } else {
         int count = ((IBlockStateAdapter)blockState).getCount2();
         return count > 0 && count < blockSolidTypeArray.length ? blockSolidTypeArray[count] : null;
      }
   }

   public static void updateState() {
      blockSolidTypeArray = null;
      enabled = false;
      ArrayList list = new ArrayList();
      String text = "optifine/block.properties";
      Properties properties = AssetsMinecraftManager.resolveProperties(text, "CustomBlockLayers");
      if (properties != null) {
         applyText(text, properties, list);
      }

      if (BuildManager.canExecute()) {
         PropertiesAdapter propertiesAdapter = AuxiliaryShadersManager.getPropertiesAdapter();
         if (propertiesAdapter != null) {
            applyText("shaders/block.properties", propertiesAdapter, list);
         }
      }

      if (!list.isEmpty()) {
         blockSolidTypeArray = ((BlockSolidType[])list.toArray(new BlockSolidType[list.size()]));
         enabled = true;
      }
   }

   private static void applyText(String text, Properties properties, List list) {
      BuildManager.applyText4("CustomBlockLayers: " + text);
      applyText("solid", BlockSolidType.SOLID, properties, list);
      applyText("cutout", BlockSolidType.CUTOUT, properties, list);
      applyText("cutout_mipped", BlockSolidType.CUTOUT_MIPPED, properties, list);
      applyText("translucent", BlockSolidType.TRANSLUCENT, properties, list);
   }

   public static boolean isEnabled() {
      return enabled;
   }
}
