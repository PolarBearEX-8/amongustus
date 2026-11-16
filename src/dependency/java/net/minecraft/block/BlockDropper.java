package net.minecraft.block;

import client.util.RotationAggregator;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.CustomItemCallback;
import net.minecraft.item.ItemCallbackAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityTrap;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockDropper extends BlockDispenser {
   public final CustomItemCallback customItemCallback = new ItemCallbackAdapter();

   @Override
   public CustomItemCallback resolveCustomItemCallback(ItemStack stack) {
      return this.customItemCallback;
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityDropper();
   }

   @Override
   public void applyWorld2(World world, BlockPos blockPos) {
      RotationAggregator rotationAggregator = new RotationAggregator(world, blockPos);
      TileEntityTrap tileEntity = (TileEntityTrap)rotationAggregator.getTileEntity();
      if (tileEntity != null) {
         int countAsIndex = tileEntity.getCountAsIndex();
         if (countAsIndex < 0) {
            world.applyBlockPosByBlockPos(1001, blockPos, 0);
         } else {
            ItemStack stack = tileEntity.processAction3(countAsIndex);
            if (stack != null) {
               EnumFacing comparable = (EnumFacing)world.getBlockState(blockPos).getComparable(autoWalkPath);
               BlockPos blockPos2 = blockPos.offset(comparable);
               IInventory iInventory = TileEntityHopper.resolveIInventory(world, (double)blockPos2.getX(), (double)blockPos2.getY(), (double)blockPos2.getZ());
               ItemStack stack2;
               if (iInventory == null) {
                  stack2 = this.customItemCallback.resolveItemStack(rotationAggregator, stack);
                  if (stack2 != null && stack2.animationsToGo <= 0) {
                     stack2 = null;
                  }
               } else if (TileEntityHopper.resolveItemStack(iInventory, stack.getItemStack().resolveItemStack(1), comparable.getEnumFacing5()) == null) {
                  stack2 = stack.getItemStack();
                  if (--stack2.animationsToGo <= 0) {
                     stack2 = null;
                  }
               } else {
                  stack2 = stack.getItemStack();
               }

               tileEntity.processAction(countAsIndex, stack2);
            }
         }
      }
   }
}
