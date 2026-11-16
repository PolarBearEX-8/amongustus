package client.util;

import java.util.Comparator;
import net.minecraft.item.ItemManager;

public class AngleChecker implements Comparator {
   @Override
   public int compare(Object entry, Object entry2) {
      ItemManager itemManager = (ItemManager)entry;
      ItemManager itemManager2 = (ItemManager)entry2;
      return itemManager.count8 != itemManager2.count8
         ? itemManager2.count8 - itemManager.count8
         : (
            !BuildManager.matchesTarget(itemManager.labelText2, itemManager2.labelText2)
               ? itemManager.labelText2.compareTo(itemManager2.labelText2)
               : itemManager.labelText.compareTo(itemManager2.labelText)
         );
   }
}
