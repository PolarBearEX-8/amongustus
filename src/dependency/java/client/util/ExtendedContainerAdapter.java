package client.util;

import client.event.CoreListener;
import client.event.LocalListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SplineFormatterSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExtendedContainerAdapter extends Container {
   public LocalListener localListener;
   public SplineFormatter splineFormatter;
   public final World world;

   @Override
   public void processAction(IInventory iInventory) {
      this.splineFormatter.updateState();
      super.processAction(iInventory);
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return this.localListener.getEntityPlayer2() == player;
   }

   @Override
   public void processAction2(int count, int count2) {
   }

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();
   }

   @Override
   public void applyCoreListener(CoreListener coreListener) {
      super.applyCoreListener(coreListener);
   }

   @Override
   public ItemStack resolveItemStack(EntityPlayer player, int count) {
      ItemStack stack = null;
      Slot slot = (Slot)this.itemList2.get(count);
      if (slot != null && slot.isActive()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.getItemStack();
         if (count == 2) {
            if (!this.matchesItemStack(stack2, 3, 39, true)) {
               return null;
            }

            slot.applyItemStack(stack2, stack);
         } else if (count != 0 && count != 1) {
            if (count >= 3 && count < 30) {
               if (!this.matchesItemStack(stack2, 30, 39, false)) {
                  return null;
               }
            } else if (count >= 30 && count < 39 && !this.matchesItemStack(stack2, 3, 30, false)) {
               return null;
            }
         } else if (!this.matchesItemStack(stack2, 3, 39, false)) {
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

   public SplineFormatter getSplineFormatter() {
      return this.splineFormatter;
   }

   @Override
   public void applyEntityPlayer(EntityPlayer player) {
      super.applyEntityPlayer(player);
      this.localListener.setEntityPlayer2((EntityPlayer)null);
      super.applyEntityPlayer(player);
      if (!this.world.state) {
         ItemStack stackInSlot = this.splineFormatter.getStackInSlot(0);
         if (stackInSlot != null) {
            player.resolveEntityItem(stackInSlot, false);
         }

         ItemStack stackInSlot2 = this.splineFormatter.getStackInSlot(1);
         if (stackInSlot2 != null) {
            player.resolveEntityItem(stackInSlot2, false);
         }
      }
   }

   public void applyIndex(int count) {
      this.splineFormatter.applyIndex(count);
   }

   public ExtendedContainerAdapter(InventoryPlayer inventoryPlayer, LocalListener localListener2, World world2) {
      this.localListener = localListener2;
      this.world = world2;
      this.splineFormatter = new SplineFormatter(inventoryPlayer.targetPlayer, localListener2);
      this.resolveSlot(new Slot(this.splineFormatter, 0, 36, 53));
      this.resolveSlot(new Slot(this.splineFormatter, 1, 62, 53));
      this.resolveSlot(new SplineFormatterSlot(inventoryPlayer.targetPlayer, localListener2, this.splineFormatter, 2, 120, 53));

      for (int index = 0; index < 3; index++) {
         for (int index2 = 0; index2 < 9; index2++) {
            this.resolveSlot(new Slot(inventoryPlayer, index2 + index * 9 + 9, 8 + index2 * 18, 84 + index * 18));
         }
      }

      for (int index3 = 0; index3 < 9; index3++) {
         this.resolveSlot(new Slot(inventoryPlayer, index3, 8 + index3 * 18, 142));
      }
   }
}
