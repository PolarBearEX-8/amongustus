package client.util;

import client.event.PreRenderListener;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockTnt;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemCallbackAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public final class CurveProfiler extends ItemCallbackAdapter {
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
      if (world.isAirBlock(blockPos)) {
         world.matchesBlockPos(blockPos, Blocks.blockFire.getDefaultState());
         if (stack.matchesRandom(1, world.random)) {
            stack.animationsToGo = 0;
         }
      } else if (world.getBlockState(blockPos).getBlock() == Blocks.block135) {
         Blocks.block135.breakBlock(world, blockPos, Blocks.block135.getDefaultState().resolveIBlockState(BlockTnt.internalImmutableSetEntry, Boolean.TRUE));
         world.matchesBlockPos8(blockPos);
      } else {
         this.enabled = false;
      }

      return stack;
   }
}
