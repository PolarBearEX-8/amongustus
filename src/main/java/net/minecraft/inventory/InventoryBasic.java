package net.minecraft.inventory;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class InventoryBasic implements IInventory {
   private String inventoryTitle;
   private IChatComponent displayNameComponent;
   private int slotsCount;
   private ItemStack[] inventoryContents;
   private List changeListeners;
   private boolean hasCustomName;

   public IChatComponent getDisplayNameComponent() {
      return this.displayNameComponent;
   }

   public void addInventoryChangeListener(IInvBasic invBasic) {
      if (this.changeListeners == null) {
         this.changeListeners = Lists.newArrayList();
      }

      this.changeListeners.add(invBasic);
   }

   @Override
   public String getName() {
      return this.inventoryTitle;
   }

   public InventoryBasic(String str, boolean flag, int n) {
      this.inventoryTitle = str;
      this.hasCustomName = flag;
      this.slotsCount = n;
      this.inventoryContents = new ItemStack[n];
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return true;
   }

   @Override
   public boolean hasCustomName() {
      return this.hasCustomName;
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      if (this.inventoryContents[n] != null) {
         if (this.inventoryContents[n].stackSize <= n2) {
            ItemStack stack = this.inventoryContents[n];
            this.inventoryContents[n] = null;
            this.markDirty();
            return stack;
         } else {
            ItemStack stack2 = this.inventoryContents[n].splitStack(n2);
            if (this.inventoryContents[n].stackSize == 0) {
               this.inventoryContents[n] = null;
            }

            this.markDirty();
            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public void markDirty() {
      if (this.changeListeners != null) {
         for (int i = 0; i < this.changeListeners.size(); i++) {
            ((IInvBasic)this.changeListeners.get(i)).onInventoryChanged(this);
         }
      }
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
   }

   @Override
   public void clear() {
      for (int i = 0; i < this.inventoryContents.length; i++) {
         this.inventoryContents[i] = null;
      }
   }

   @Override
   public void closeInventory(EntityPlayer player) {
   }

   public InventoryBasic(IChatComponent component, int n) {
      this(component.getUnformattedText(), true, n);
      this.displayNameComponent = component;
   }

   public void removeInventoryChangeListener(IInvBasic invBasic) {
      this.changeListeners.remove(invBasic);
   }

   @Override
   public int getSizeInventory() {
      return this.slotsCount;
   }

   public void setCustomName(String str) {
      this.hasCustomName = true;
      this.inventoryTitle = str;
   }

   @Override
   public void openInventory(EntityPlayer player) {
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      if (this.inventoryContents[n] != null) {
         ItemStack stack = this.inventoryContents[n];
         this.inventoryContents[n] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public int getField(int n) {
      return 0;
   }

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return true;
   }

   @Override
   public ItemStack getStackInSlot(int n) {
      return n >= 0 && n < this.inventoryContents.length ? this.inventoryContents[n] : null;
   }

   public ItemStack addItem(ItemStack stack) {
      ItemStack stack2 = stack.copy();

      for (int i = 0; i < this.slotsCount; i++) {
         ItemStack stackInSlot = this.getStackInSlot(i);
         if (stackInSlot == null) {
            this.setInventorySlotContents(i, stack2);
            this.markDirty();
            return null;
         }

         if (ItemStack.areItemsEqual(stackInSlot, stack2)) {
            int n = Math.min(this.getInventoryStackLimit(), stackInSlot.getMaxStackSize());
            int n2 = Math.min(stack2.stackSize, n - stackInSlot.stackSize);
            if (n2 > 0) {
               stackInSlot.stackSize += n2;
               stack2.stackSize -= n2;
               if (stack2.stackSize <= 0) {
                  this.markDirty();
                  return null;
               }
            }
         }
      }

      if (stack2.stackSize != stack.stackSize) {
         this.markDirty();
      }

      return stack2;
   }

   @Override
   public void setField(int n, int n2) {
   }

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      this.inventoryContents[n] = stack;
      if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
         stack.stackSize = this.getInventoryStackLimit();
      }

      this.markDirty();
   }
}
