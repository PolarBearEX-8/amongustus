package net.optifine;

import java.util.Comparator;
import net.optifine.config.Config;

public class CustomItemsComparator implements Comparator {
   @Override
   public int compare(Object a, Object b) {
      CustomItemProperties customItemProperties = (CustomItemProperties)a;
      CustomItemProperties customItemProperties2 = (CustomItemProperties)b;
      return customItemProperties.weight != customItemProperties2.weight
         ? customItemProperties2.weight - customItemProperties.weight
         : (!Config.equals(customItemProperties.basePath, customItemProperties2.basePath) ? customItemProperties.basePath.compareTo(customItemProperties2.basePath) : customItemProperties.name.compareTo(customItemProperties2.name));
   }
}
