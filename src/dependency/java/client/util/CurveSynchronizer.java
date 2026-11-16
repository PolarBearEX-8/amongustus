package client.util;

import client.event.PreRenderListener;
import client.setting.LocalOptionType;
import net.minecraft.block.BlockDispenser;
import net.minecraft.item.ItemCallbackAdapter;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public final class CurveSynchronizer extends ItemCallbackAdapter {
   public boolean enabled = true;

   @Override
   public void applyPreRenderListener(PreRenderListener preRenderListener) {
      if (this.enabled) {
         preRenderListener.getWorld().applyBlockPosByBlockPos(1000, preRenderListener.getBlockPos(), 0);
      } else {
         preRenderListener.getWorld().applyBlockPosByBlockPos(1001, preRenderListener.getBlockPos(), 0);
      }
   }

   @Override
   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      if (LocalOptionType.WHITE == LocalOptionType.resolveLocalOptionTypeByIndex(stack.getIndexOffset())) {
         World world = preRenderListener.getWorld();
         BlockPos blockPos = preRenderListener.getBlockPos().offset(BlockDispenser.resolveEnumFacing(preRenderListener.getCount()));
         if (ItemDye.matchesItemStack(stack, world, blockPos)) {
            if (!world.state) {
               world.applyBlockPosByBlockPos(2005, blockPos, 0);
            }
         } else {
            this.enabled = false;
         }

         return stack;
      } else {
         return super.resolveItemStackByPreRenderListene(preRenderListener, stack);
      }
   }
}
