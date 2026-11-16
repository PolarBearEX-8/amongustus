package client.util;

import java.util.Comparator;
import net.minecraft.item.Item;

public class SplineBridgeComparator implements Comparator {
   public final SplineBridge splineBridge;
   public final StatCraftedGuiButtonExtenImpl statCraftedGuiButtonExtenImpl;

   public SplineBridgeComparator(StatCraftedGuiButtonExtenImpl statCraftedGuiButtonExtenImpl2, SplineBridge splineBridge2) {
      this.statCraftedGuiButtonExtenImpl = statCraftedGuiButtonExtenImpl2;
      this.splineBridge = splineBridge2;
   }

   public int computeCount(DuplicateStatIdManagerAdapter duplicateStatIdManagerAdapter, DuplicateStatIdManagerAdapter duplicateStatIdManagerAdapter2) {
      int index = Item.getIndex(duplicateStatIdManagerAdapter.getItem());
      int index2 = Item.getIndex(duplicateStatIdManagerAdapter2.getItem());
      DuplicateStatIdManager duplicateStatIdManager = null;
      DuplicateStatIdManager duplicateStatIdManager2 = null;
      if (this.statCraftedGuiButtonExtenImpl.count15 == 0) {
         duplicateStatIdManager = StatCraftItemManager.duplicateStatIdManagerArray3[index];
         duplicateStatIdManager2 = StatCraftItemManager.duplicateStatIdManagerArray3[index2];
      } else if (this.statCraftedGuiButtonExtenImpl.count15 == 1) {
         duplicateStatIdManager = StatCraftItemManager.duplicateStatIdManagerArray[index];
         duplicateStatIdManager2 = StatCraftItemManager.duplicateStatIdManagerArray[index2];
      } else if (this.statCraftedGuiButtonExtenImpl.count15 == 2) {
         duplicateStatIdManager = StatCraftItemManager.duplicateStatIdManagerArray2[index];
         duplicateStatIdManager2 = StatCraftItemManager.duplicateStatIdManagerArray2[index2];
      }

      if (duplicateStatIdManager != null || duplicateStatIdManager2 != null) {
         if (duplicateStatIdManager == null) {
            return 1;
         }

         if (duplicateStatIdManager2 == null) {
            return -1;
         }

         int count = SplineBridge.resolveStatFileWriter(this.statCraftedGuiButtonExtenImpl.splineBridge2).computeCount(duplicateStatIdManager);
         int count2 = SplineBridge.resolveStatFileWriter(this.statCraftedGuiButtonExtenImpl.splineBridge2).computeCount(duplicateStatIdManager2);
         if (count != count2) {
            return (count - count2) * this.statCraftedGuiButtonExtenImpl.count16;
         }
      }

      return index - index2;
   }

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((DuplicateStatIdManagerAdapter)entry, (DuplicateStatIdManagerAdapter)entry2);
   }
}
