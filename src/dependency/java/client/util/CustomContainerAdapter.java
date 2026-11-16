package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CustomContainerAdapter extends Container {
   public ContainerCraftingIInventory containerCraftingIInventory = new ContainerCraftingIInventory(this, 3, 3);
   public IInventory iInventory = new ResultIInventory();
   public World world;
   public BlockPos blockPos;

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return this.world.getBlockState(this.blockPos).getBlock() != Blocks.block46
         ? false
         : player.getDouble3((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5) <= 64.0;
   }

   public CustomContainerAdapter(InventoryPlayer inventoryPlayer, World world2, BlockPos blockPos2) {
      this.world = world2;
      this.blockPos = blockPos2;
      this.resolveSlot(new AuxiliarySlotAdapter(inventoryPlayer.targetPlayer, this.containerCraftingIInventory, this.iInventory, 0, 124, 35));

      for (int index = 0; index < 3; index++) {
         for (int index2 = 0; index2 < 3; index2++) {
            this.resolveSlot(new Slot(this.containerCraftingIInventory, index2 + index * 3, 30 + index2 * 18, 17 + index * 18));
         }
      }

      for (int index3 = 0; index3 < 3; index3++) {
         for (int index4 = 0; index4 < 9; index4++) {
            this.resolveSlot(new Slot(inventoryPlayer, index4 + index3 * 9 + 9, 8 + index4 * 18, 84 + index3 * 18));
         }
      }

      for (int index5 = 0; index5 < 9; index5++) {
         this.resolveSlot(new Slot(inventoryPlayer, index5, 8 + index5 * 18, 142));
      }

      this.processAction(this.containerCraftingIInventory);
   }

   @Override
   public void processAction(IInventory iInventory2) {
      this.iInventory.processAction(0, ExtendedManager.getExtendedManager().resolveItemStack(this.containerCraftingIInventory, this.world));
   }

   @Override
   public boolean processAction(ItemStack stack, Slot slot) {
      return slot.iInventory != this.iInventory && super.processAction(stack, slot);
   }

   @Override
   public ItemStack resolveItemStack(EntityPlayer player, int count) {
      ItemStack stack = null;
      Slot slot = (Slot)this.itemList2.get(count);
      if (slot != null && slot.isActive()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.getItemStack();
         if (count == 0) {
            if (!this.matchesItemStack(stack2, 10, 46, true)) {
               return null;
            }

            slot.applyItemStack(stack2, stack);
         } else if (count >= 10 && count < 37) {
            if (!this.matchesItemStack(stack2, 37, 46, false)) {
               return null;
            }
         } else if (count >= 37 && count < 46) {
            if (!this.matchesItemStack(stack2, 10, 37, false)) {
               return null;
            }
         } else if (!this.matchesItemStack(stack2, 10, 46, false)) {
            return null;
         }

         if (stack2.animationsToGo == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }

         if (stack2.animationsToGo == stack.animationsToGo) {
            return null;
         }

         slot.processAction(player, stack2);
      }

      return stack;
   }

   @Override
   public void applyEntityPlayer(EntityPlayer player) {
      super.applyEntityPlayer(player);
      if (!this.world.state) {
         for (int index = 0; index < 9; index++) {
            ItemStack stackInSlot = this.containerCraftingIInventory.getStackInSlot(index);
            if (stackInSlot != null) {
               player.resolveEntityItem(stackInSlot, false);
            }
         }
      }
   }
}
