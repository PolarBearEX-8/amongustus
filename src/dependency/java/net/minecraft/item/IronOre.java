package net.minecraft.item;

import client.event.PreRenderListener;
import net.minecraft.block.BlockDispenser;
import net.minecraft.util.BlockPos;

public final class IronOre extends ItemCallbackAdapter {
   public final ItemCallbackAdapter itemCallbackAdapter = new ItemCallbackAdapter();

   @Override
   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      ItemLavaBucket item = (ItemLavaBucket)stack.getItem();
      BlockPos blockPos = preRenderListener.getBlockPos().offset(BlockDispenser.resolveEnumFacing(preRenderListener.getCount()));
      if (item.matchesWorldByWorld(preRenderListener.getWorld(), blockPos)) {
         stack.setItem(Items.bucket);
         stack.animationsToGo = 1;
         return stack;
      } else {
         return this.itemCallbackAdapter.resolveItemStack(preRenderListener, stack);
      }
   }
}
