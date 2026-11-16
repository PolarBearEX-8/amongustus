package client.util;

import client.event.CoreListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.BezierEvaluatorSlot;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class BezierEvaluator extends Container {
   public IInventory iInventory;
   public final BezierEvaluatorSlot bezierEvaluatorSlot;

   public BezierEvaluator(IInventory iInventory2, IInventory iInventory3) {
      this.iInventory = iInventory3;
      this.resolveSlot(this.bezierEvaluatorSlot = new BezierEvaluatorSlot(this, iInventory3, 0, 136, 110));

      for (int index = 0; index < 3; index++) {
         for (int index2 = 0; index2 < 9; index2++) {
            this.resolveSlot(new Slot(iInventory2, index2 + index * 9 + 9, 36 + index2 * 18, 137 + index * 18));
         }
      }

      for (int index3 = 0; index3 < 9; index3++) {
         this.resolveSlot(new Slot(iInventory2, index3, 36 + index3 * 18, 195));
      }
   }

   @Override
   public void applyEntityPlayer(EntityPlayer player) {
      super.applyEntityPlayer(player);
      if (player != null && !player.worldObj.state) {
         ItemStack stack = this.bezierEvaluatorSlot.decrStackSize(this.bezierEvaluatorSlot.processAction2());
         if (stack != null) {
            player.resolveEntityItem(stack, false);
         }
      }
   }

   @Override
   public void processAction2(int count, int count2) {
      this.iInventory.processAction(count, count2);
   }

   @Override
   public void applyCoreListener(CoreListener coreListener) {
      super.applyCoreListener(coreListener);
      coreListener.applyContainer(this, this.iInventory);
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return this.iInventory.checkEntityPlayer(player);
   }

   @Override
   public ItemStack resolveItemStack(EntityPlayer player, int count) {
      ItemStack stack = null;
      Slot slot = (Slot)this.itemList2.get(count);
      if (slot != null && slot.isActive()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.getItemStack();
         if (count == 0) {
            if (!this.matchesItemStack(stack2, 1, 37, true)) {
               return null;
            }

            slot.applyItemStack(stack2, stack);
         } else if (!this.bezierEvaluatorSlot.isActive() && this.bezierEvaluatorSlot.isItemValid(stack2) && stack2.animationsToGo == 1) {
            if (!this.matchesItemStack(stack2, 0, 1, false)) {
               return null;
            }
         } else if (count >= 1 && count < 28) {
            if (!this.matchesItemStack(stack2, 28, 37, false)) {
               return null;
            }
         } else if (count >= 28 && count < 37) {
            if (!this.matchesItemStack(stack2, 1, 28, false)) {
               return null;
            }
         } else if (!this.matchesItemStack(stack2, 1, 37, false)) {
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

   public IInventory getIInventory() {
      return this.iInventory;
   }
}
