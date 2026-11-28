package net.minecraft.block;

import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockDropper extends BlockDispenser {
   private final IBehaviorDispenseItem dropBehavior = new BehaviorDefaultDispenseItem();

   @Override
   public IBehaviorDispenseItem getBehavior(ItemStack stack) {
      return this.dropBehavior;
   }

   @Override
   public void dispense(World world, BlockPos pos) {
      BlockSourceImpl blockSourceImpl = new BlockSourceImpl(world, pos);
      TileEntityDispenser blockTileEntity = (TileEntityDispenser)blockSourceImpl.getBlockTileEntity();
      if (blockTileEntity != null) {
         int dispenseSlot = blockTileEntity.getDispenseSlot();
         if (dispenseSlot < 0) {
            world.playAuxSFX(1001, pos, 0);
         } else {
            ItemStack stackInSlot = blockTileEntity.getStackInSlot(dispenseSlot);
            if (stackInSlot != null) {
               EnumFacing value = (EnumFacing)world.getBlockState(pos).getValue(FACING);
               BlockPos pos2 = pos.offset(value);
               IInventory inventoryAtPosition = TileEntityHopper.getInventoryAtPosition(world, (double)pos2.getX(), (double)pos2.getY(), (double)pos2.getZ());
               ItemStack stack;
               if (inventoryAtPosition == null) {
                  stack = this.dropBehavior.dispense(blockSourceImpl, stackInSlot);
                  if (stack != null && stack.stackSize <= 0) {
                     stack = null;
                  }
               } else {
                  stack = TileEntityHopper.putStackInInventoryAllSlots(inventoryAtPosition, stackInSlot.copy().splitStack(1), value.getOpposite());
                  if (stack == null) {
                     stack = stackInSlot.copy();
                     if (--stack.stackSize <= 0) {
                        stack = null;
                     }
                  } else {
                     stack = stackInSlot.copy();
                  }
               }

               blockTileEntity.setInventorySlotContents(dispenseSlot, stack);
            }
         }
      }
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityDropper();
   }
}
