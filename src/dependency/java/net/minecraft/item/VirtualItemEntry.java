package net.minecraft.item;

import client.event.PreRenderListener;
import client.util.CurveDirector;

public final class VirtualItemEntry implements CustomItemCallback {
   public final ItemCallbackAdapter itemCallbackAdapter = new ItemCallbackAdapter();

   @Override
   public ItemStack resolveItemStack(PreRenderListener preRenderListener, ItemStack stack) {
      return ItemPotion.checkCondition(stack.getIndexOffset())
         ? new CurveDirector(this, stack).resolveItemStack(preRenderListener, stack)
         : this.itemCallbackAdapter.resolveItemStack(preRenderListener, stack);
   }
}
