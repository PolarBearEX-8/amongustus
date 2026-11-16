package net.minecraft.inventory;

import client.util.AuxiliarySlotAdapter;
import client.util.ContainerCraftingIInventory;
import client.util.ExtendedManager;
import client.util.ResultIInventory;
import client.util.SlotAdapter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;

public class ContainerPlayer extends Container {
   public ContainerCraftingIInventory containerCraftingIInventory = new ContainerCraftingIInventory(this, 2, 2);
   public IInventory iInventory = new ResultIInventory();
   public boolean enabled;
   public final EntityPlayer entityPlayer;

   @Override
   public void processAction(IInventory iInventory2) {
      this.iInventory.processAction(0, ExtendedManager.getExtendedManager().resolveItemStack(this.containerCraftingIInventory, this.entityPlayer.worldObj));
   }

   @Override
   public void applyEntityPlayer(EntityPlayer player) {
      super.applyEntityPlayer(player);

      for (int index = 0; index < 4; index++) {
         ItemStack stackInSlot = this.containerCraftingIInventory.getStackInSlot(index);
         if (stackInSlot != null) {
            player.resolveEntityItem(stackInSlot, false);
         }
      }

      this.iInventory.processAction(0, (ItemStack)null);
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
            if (!this.matchesItemStack(stack2, 9, 45, true)) {
               return null;
            }

            slot.applyItemStack(stack2, stack);
         } else if (count >= 1 && count < 5) {
            if (!this.matchesItemStack(stack2, 9, 45, false)) {
               return null;
            }
         } else if (count >= 5 && count < 9) {
            if (!this.matchesItemStack(stack2, 9, 45, false)) {
               return null;
            }
         } else if (stack.getItem() instanceof ItemGoldenBoots && !((Slot)this.itemList2.get(5 + ((ItemGoldenBoots)stack.getItem()).indexOffset3)).isActive()) {
            int count2 = 5 + ((ItemGoldenBoots)stack.getItem()).indexOffset3;
            if (!this.matchesItemStack(stack2, count2, count2 + 1, false)) {
               return null;
            }
         } else if (count >= 9 && count < 36) {
            if (!this.matchesItemStack(stack2, 36, 45, false)) {
               return null;
            }
         } else if (count >= 36 && count < 45) {
            if (!this.matchesItemStack(stack2, 9, 36, false)) {
               return null;
            }
         } else if (!this.matchesItemStack(stack2, 9, 45, false)) {
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
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return true;
   }

   public ContainerPlayer(InventoryPlayer inventoryPlayer, boolean flag, EntityPlayer player, boolean flag2) {
      this.enabled = flag;
      this.entityPlayer = player;
      int count = flag2 ? 20 : 18;
      if (!flag2) {
         this.resolveSlot(new AuxiliarySlotAdapter(inventoryPlayer.targetPlayer, this.containerCraftingIInventory, this.iInventory, 0, 144, 36));

         for (int index = 0; index < 2; index++) {
            for (int index2 = 0; index2 < 2; index2++) {
               this.resolveSlot(new Slot(this.containerCraftingIInventory, index2 + index * 2, 88 + index2 * count, 26 + index * count));
            }
         }

         for (int index3 = 0; index3 < 4; index3++) {
            this.resolveSlot(new SlotAdapter(this, inventoryPlayer, inventoryPlayer.processAction4() - 1 - index3, 8, 8 + index3 * count, index3));
         }
      }

      for (int index4 = 0; index4 < 3; index4++) {
         for (int index5 = 0; index5 < 9; index5++) {
            this.resolveSlot(new Slot(inventoryPlayer, index5 + (index4 + 1) * 9, 8 + index5 * count, 84 + index4 * count));
         }
      }

      if (!flag2) {
         for (int index6 = 0; index6 < 9; index6++) {
            this.resolveSlot(new Slot(inventoryPlayer, index6, 8 + index6 * count, 142));
         }

         this.processAction(this.containerCraftingIInventory);
      }
   }
}
