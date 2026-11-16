package client.util;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityHorseSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.TertiaryContainerAdaSlot;
import net.minecraft.item.ItemStack;

public class TertiaryContainerAdapter extends Container {
   public IInventory iInventory;
   public EntityHorse entityHorse;

   public TertiaryContainerAdapter(IInventory iInventory2, IInventory iInventory3, EntityHorse entityHorse2, EntityPlayer player) {
      this.iInventory = iInventory3;
      this.entityHorse = entityHorse2;
      iInventory3.processAction2(player);
      this.resolveSlot(new TertiaryContainerAdaSlot(this, iInventory3, 0, 8, 18));
      this.resolveSlot(new EntityHorseSlot(this, iInventory3, 1, 8, 36, entityHorse2));
      if (entityHorse2.isActive32()) {
         for (int index = 0; index < 3; index++) {
            for (int index2 = 0; index2 < 5; index2++) {
               this.resolveSlot(new Slot(iInventory3, 2 + index2 + index * 5, 80 + index2 * 18, 18 + index * 18));
            }
         }
      }

      for (int index3 = 0; index3 < 3; index3++) {
         for (int index4 = 0; index4 < 9; index4++) {
            this.resolveSlot(new Slot(iInventory2, index4 + index3 * 9 + 9, 8 + index4 * 18, 102 + index3 * 18 + -18));
         }
      }

      for (int index5 = 0; index5 < 9; index5++) {
         this.resolveSlot(new Slot(iInventory2, index5, 8 + index5 * 18, 142));
      }
   }

   @Override
   public ItemStack resolveItemStack(EntityPlayer player, int count) {
      ItemStack stack = null;
      Slot slot = (Slot)this.itemList2.get(count);
      if (slot != null && slot.isActive()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.getItemStack();
         if (count < this.iInventory.processAction4()) {
            if (!this.matchesItemStack(stack2, this.iInventory.processAction4(), this.itemList2.size(), true)) {
               return null;
            }
         } else if (this.getSlot(1).isItemValid(stack2) && !this.getSlot(1).isActive()) {
            if (!this.matchesItemStack(stack2, 1, 2, false)) {
               return null;
            }
         } else if (this.getSlot(0).isItemValid(stack2)) {
            if (!this.matchesItemStack(stack2, 0, 1, false)) {
               return null;
            }
         } else if (this.iInventory.processAction4() <= 2 || !this.matchesItemStack(stack2, 2, this.iInventory.processAction4(), false)) {
            return null;
         }

         if (stack2.animationsToGo == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }
      }

      return stack;
   }

   @Override
   public void applyEntityPlayer(EntityPlayer player) {
      super.applyEntityPlayer(player);
      this.iInventory.processAction(player);
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return this.iInventory.checkEntityPlayer(player) && this.entityHorse.canExecute() && this.entityHorse.getDistanceSqToEntity(player) < 8.0F;
   }
}
