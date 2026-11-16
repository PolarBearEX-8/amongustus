package client.util;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;

public class StatCraftedGuiButtonExtenImpl extends AbstractGuiButtonExtendedMinecraftEntry {
   public final SplineBridge splineBridge2;

   @Override
   public void applyTessellator(int count, int count2, Tessellator tessellator) {
      super.applyTessellator(count, count2, tessellator);
      if (this.count17 == 0) {
         SplineBridge.applySplineBridge(this.splineBridge2, count + 115 - 18 + 1, count2 + 1 + 1, 72, 18);
      } else {
         SplineBridge.applySplineBridge(this.splineBridge2, count + 115 - 18, count2 + 1, 72, 18);
      }

      if (this.count17 == 1) {
         SplineBridge.applySplineBridge(this.splineBridge2, count + 165 - 18 + 1, count2 + 1 + 1, 18, 18);
      } else {
         SplineBridge.applySplineBridge(this.splineBridge2, count + 165 - 18, count2 + 1, 18, 18);
      }

      if (this.count17 == 2) {
         SplineBridge.applySplineBridge(this.splineBridge2, count + 215 - 18 + 1, count2 + 1 + 1, 36, 18);
      } else {
         SplineBridge.applySplineBridge(this.splineBridge2, count + 215 - 18, count2 + 1, 36, 18);
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      DuplicateStatIdManagerAdapter duplicateStatIdManagerAdapter = this.get(count);
      Item item = duplicateStatIdManagerAdapter.getItem();
      SplineBridge.applySplineBridge(this.splineBridge2, count2 + 40, count3, item);
      int index = Item.getIndex(item);
      this.applyDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManagerArray3[index], count2 + 115, count3, count % 2 == 0);
      this.applyDuplicateStatIdManager(StatCraftItemManager.duplicateStatIdManagerArray[index], count2 + 165, count3, count % 2 == 0);
      this.applyDuplicateStatIdManager(duplicateStatIdManagerAdapter, count2 + 215, count3, count % 2 == 0);
   }

   public StatCraftedGuiButtonExtenImpl(SplineBridge splineBridge, Minecraft mc) {
      super(splineBridge, mc);
      this.splineBridge2 = splineBridge;
      this.list = Lists.newArrayList();

      for (DuplicateStatIdManagerAdapter duplicateStatIdManagerAdapter : (Iterable<DuplicateStatIdManagerAdapter>)(Iterable<?>)StatCraftItemManager.list3) {
         boolean flag = false;
         int index = Item.getIndex(duplicateStatIdManagerAdapter.getItem());
         if (SplineBridge.resolveStatFileWriter(splineBridge).computeCount(duplicateStatIdManagerAdapter) > 0) {
            flag = true;
         } else if (StatCraftItemManager.duplicateStatIdManagerArray3[index] != null
            && SplineBridge.resolveStatFileWriter(splineBridge).computeCount(StatCraftItemManager.duplicateStatIdManagerArray3[index]) > 0) {
            flag = true;
         } else if (StatCraftItemManager.duplicateStatIdManagerArray[index] != null
            && SplineBridge.resolveStatFileWriter(splineBridge).computeCount(StatCraftItemManager.duplicateStatIdManagerArray[index]) > 0) {
            flag = true;
         }

         if (flag) {
            this.list.add(duplicateStatIdManagerAdapter);
         }
      }

      this.comparator = new SplineBridgeComparator(this, splineBridge);
   }

   @Override
   public String resolveText(int count) {
      return count == 1 ? "stat.crafted" : (count == 2 ? "stat.used" : "stat.depleted");
   }
}
