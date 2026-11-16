package client.util;

import client.event.PreRenderListener;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockLitPumpkin;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemCallbackAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public final class CurveSystem extends ItemCallbackAdapter {
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
      World world = preRenderListener.getWorld();
      BlockPos blockPos = preRenderListener.getBlockPos().offset(BlockDispenser.resolveEnumFacing(preRenderListener.getCount()));
      BlockLitPumpkin block = (BlockLitPumpkin)Blocks.block146;
      if (world.isAirBlock(blockPos) && block.matchesWorld4(world, blockPos)) {
         if (!world.state) {
            world.matchesBlockPos(blockPos, block.getDefaultState(), 3);
         }

         stack.animationsToGo--;
      } else {
         this.enabled = false;
      }

      return stack;
   }
}
