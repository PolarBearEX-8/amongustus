package net.minecraft.item;

import client.event.PreRenderListener;
import client.util.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntityTrap;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public final class IronOrb extends ItemCallbackAdapter {
   public final ItemCallbackAdapter itemCallbackAdapter = new ItemCallbackAdapter();

   @Override
   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      World world = preRenderListener.getWorld();
      BlockPos blockPos = preRenderListener.getBlockPos().offset(BlockDispenser.resolveEnumFacing(preRenderListener.getCount()));
      IBlockState blockState = world.getBlockState(blockPos);
      Block block = blockState.getBlock();
      Material material = block.getMaterial();
      Item item;
      if (Material.coreItemEntry1.equals(material) && block instanceof BlockLiquid && (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) == 0) {
         item = Items.waterBucket;
      } else {
         if (!Material.material29.equals(material) || !(block instanceof BlockLiquid) || (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) != 0) {
            return super.resolveItemStackByPreRenderListene(preRenderListener, stack);
         }

         item = Items.lavaBucket;
      }

      world.matchesBlockPos8(blockPos);
      if (--stack.animationsToGo == 0) {
         stack.setItem(item);
         stack.animationsToGo = 1;
      } else if (((TileEntityTrap)preRenderListener.getTileEntity()).computeCount(new ItemStack(item)) < 0) {
         this.itemCallbackAdapter.resolveItemStack(preRenderListener, new ItemStack(item));
      }

      return stack;
   }
}
