package net.minecraft.item;

import java.util.List;

public class ItemCoal extends Item {
   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      list.add(new ItemStack(item, 1, 0));
      list.add(new ItemStack(item, 1, 1));
   }

   @Override
   public String getText(ItemStack stack) {
      return stack.getIndexOffset() == 1 ? "item.charcoal" : "item.coal";
   }

   public ItemCoal() {
      this.processAction(true);
      this.processAction3(0);
      this.processAction(ItemGroupManager.itemGroupManager11);
   }
}
