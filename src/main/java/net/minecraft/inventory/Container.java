package net.minecraft.inventory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public abstract class Container {
   public List inventoryItemStacks = Lists.newArrayList();
   public List inventorySlots = Lists.newArrayList();
   public int windowId;
   private short transactionID;
   private boolean receivedItems = false;
   private int dragMode = -1;
   private int dragEvent;
   private final Set dragSlots = Sets.newHashSet();
   public List crafters = Lists.newArrayList();
   private Set playerList = Sets.newHashSet();

   public static int getQuickcraftMask(int n, int n2) {
      return n & 3 | (n2 & 3) << 2;
   }

   public boolean canMergeSlot(ItemStack stack, Slot slot) {
      return true;
   }

   public boolean enchantItem(EntityPlayer player, int n) {
      return false;
   }

   public void onCraftGuiOpened(ICrafting crafting) {
      if (this.crafters.contains(crafting)) {
         throw new IllegalArgumentException("Listener already listening");
      } else {
         this.crafters.add(crafting);
         crafting.updateCraftingInventory(this, this.getInventory());
         this.detectAndSendChanges();
      }
   }

   public void detectAndSendChanges() {
      for (int i = 0; i < this.inventorySlots.size(); i++) {
         ItemStack stack = ((Slot)this.inventorySlots.get(i)).getStack();
         ItemStack stack2 = (ItemStack)this.inventoryItemStacks.get(i);
         if (!ItemStack.areItemStacksEqual(stack2, stack)) {
            stack2 = stack == null ? null : stack.copy();
            this.inventoryItemStacks.set(i, stack2);

            for (int j = 0; j < this.crafters.size(); j++) {
               ((ICrafting)this.crafters.get(j)).sendSlotContents(this, i, stack2);
            }
         }
      }
   }

   public boolean mergeItemStack(ItemStack stack, int n, int n2, boolean flag) {
      boolean flag2 = false;
      int n3 = n;
      if (flag) {
         n3 = n2 - 1;
      }

      if (stack.isStackable()) {
         while (stack.stackSize > 0 && (!flag && n3 < n2 || flag && n3 >= n)) {
            Slot slot = (Slot)this.inventorySlots.get(n3);
            ItemStack stack2 = slot.getStack();
            if (stack2 != null
               && stack2.getItem() == stack.getItem()
               && (!stack.getHasSubtypes() || stack.getMetadata() == stack2.getMetadata())
               && ItemStack.areItemStackTagsEqual(stack, stack2)) {
               int n4 = stack2.stackSize + stack.stackSize;
               if (n4 <= stack.getMaxStackSize()) {
                  stack.stackSize = 0;
                  stack2.stackSize = n4;
                  slot.onSlotChanged();
                  flag2 = true;
               } else if (stack2.stackSize < stack.getMaxStackSize()) {
                  stack.stackSize = stack.stackSize - (stack.getMaxStackSize() - stack2.stackSize);
                  stack2.stackSize = stack.getMaxStackSize();
                  slot.onSlotChanged();
                  flag2 = true;
               }
            }

            if (flag) {
               n3--;
            } else {
               n3++;
            }
         }
      }

      if (stack.stackSize > 0) {
         if (flag) {
            n3 = n2 - 1;
         } else {
            n3 = n;
         }

         while (!flag && n3 < n2 || flag && n3 >= n) {
            Slot slot2 = (Slot)this.inventorySlots.get(n3);
            if (slot2.getStack() == null) {
               slot2.putStack(stack.copy());
               slot2.onSlotChanged();
               stack.stackSize = 0;
               flag2 = true;
               break;
            }

            if (flag) {
               n3--;
            } else {
               n3++;
            }
         }
      }

      return flag2;
   }

   public boolean getCanCraft(EntityPlayer player) {
      return !this.playerList.contains(player);
   }

   public void putStackInSlot(int n, ItemStack stack) {
      this.getSlot(n).putStack(stack);
   }

   public Slot addSlotToContainer(Slot slot) {
      slot.slotNumber = this.inventorySlots.size();
      this.inventorySlots.add(slot);
      this.inventoryItemStacks.add((ItemStack)null);
      return slot;
   }

   public ItemStack transferStackInSlot(EntityPlayer player, int n) {
      Slot slot = (Slot)this.inventorySlots.get(n);
      return slot != null ? slot.getStack() : null;
   }

   public static boolean isValidDragMode(int n, EntityPlayer player) {
      return n == 0 || n == 1 || n == 2 && player.capabilities.isCreativeMode;
   }

   public void removeCraftingFromCrafters(ICrafting crafting) {
      this.crafters.remove(crafting);
   }

   public static void computeStackSize(Set set, int n, ItemStack stack, int n2) {
      switch (n) {
         case 0:
            stack.stackSize = MathHelper.floor_float((float)stack.stackSize / (float)set.size());
            break;
         case 1:
            stack.stackSize = 1;
            break;
         case 2:
            stack.stackSize = stack.getItem().getItemStackLimit();
      }

      stack.stackSize += n2;
   }

   public short getNextTransactionID(InventoryPlayer inventoryPlayer) {
      this.transactionID++;
      return this.transactionID;
   }

   public boolean isReceivedItems() {
      return this.receivedItems;
   }

   public static boolean canAddItemToSlot(Slot slot, ItemStack stack, boolean flag) {
      boolean flag2 = slot == null || !slot.getHasStack();
      if (slot != null && slot.getHasStack() && stack != null && stack.isItemEqual(slot.getStack()) && ItemStack.areItemStackTagsEqual(slot.getStack(), stack)) {
         int n = flag ? 0 : stack.stackSize;
         flag2 |= slot.getStack().stackSize + n <= stack.getMaxStackSize();
      }

      return flag2;
   }

   public void putStacksInSlots(ItemStack[] stacks) {
      for (int i = 0; i < stacks.length; i++) {
         this.getSlot(i).putStack(stacks[i]);
      }

      this.receivedItems = true;
   }

   public static int extractDragMode(int n) {
      return n >> 2 & 3;
   }

   public void onCraftMatrixChanged(IInventory inventory) {
      this.detectAndSendChanges();
   }

   public ItemStack slotClick(int n, int n2, int n3, EntityPlayer player) {
      ItemStack stack = null;
      InventoryPlayer inventory = player.inventory;
      if (n3 == 5) {
         int dragEvent2 = this.dragEvent;
         this.dragEvent = getDragEvent(n2);
         if ((dragEvent2 != 1 || this.dragEvent != 2) && dragEvent2 != this.dragEvent) {
            this.resetDrag();
         } else if (inventory.getItemStack() == null) {
            this.resetDrag();
         } else if (this.dragEvent == 0) {
            this.dragMode = extractDragMode(n2);
            if (isValidDragMode(this.dragMode, player)) {
               this.dragEvent = 1;
               this.dragSlots.clear();
            } else {
               this.resetDrag();
            }
         } else if (this.dragEvent == 1) {
            Slot slot = (Slot)this.inventorySlots.get(n);
            if (slot != null
               && canAddItemToSlot(slot, inventory.getItemStack(), true)
               && slot.isItemValid(inventory.getItemStack())
               && inventory.getItemStack().stackSize > this.dragSlots.size()
               && this.canDragIntoSlot(slot)) {
               this.dragSlots.add(slot);
            }
         } else if (this.dragEvent == 2) {
            if (!this.dragSlots.isEmpty()) {
               ItemStack stack2 = inventory.getItemStack().copy();
               int stackSize = inventory.getItemStack().stackSize;

               for (Slot slot2 : (Iterable<Slot>)(this.dragSlots)) {
                  if (slot2 != null
                     && canAddItemToSlot(slot2, inventory.getItemStack(), true)
                     && slot2.isItemValid(inventory.getItemStack())
                     && inventory.getItemStack().stackSize >= this.dragSlots.size()
                     && this.canDragIntoSlot(slot2)) {
                     ItemStack stack3 = stack2.copy();
                     int n4 = slot2.getHasStack() ? slot2.getStack().stackSize : 0;
                     computeStackSize(this.dragSlots, this.dragMode, stack3, n4);
                     if (stack3.stackSize > stack3.getMaxStackSize()) {
                        stack3.stackSize = stack3.getMaxStackSize();
                     }

                     if (stack3.stackSize > slot2.getItemStackLimit(stack3)) {
                        stack3.stackSize = slot2.getItemStackLimit(stack3);
                     }

                     stackSize -= stack3.stackSize - n4;
                     slot2.putStack(stack3);
                  }
               }

               stack2.stackSize = stackSize;
               if (stack2.stackSize <= 0) {
                  stack2 = null;
               }

               inventory.setItemStack(stack2);
            }

            this.resetDrag();
         } else {
            this.resetDrag();
         }
      } else if (this.dragEvent != 0) {
         this.resetDrag();
      } else if ((n3 == 0 || n3 == 1) && (n2 == 0 || n2 == 1)) {
         if (n == -999) {
            if (inventory.getItemStack() != null) {
               if (n2 == 0) {
                  player.dropPlayerItemWithRandomChoice(inventory.getItemStack(), true);
                  inventory.setItemStack((ItemStack)null);
               }

               if (n2 == 1) {
                  player.dropPlayerItemWithRandomChoice(inventory.getItemStack().splitStack(1), true);
                  if (inventory.getItemStack().stackSize == 0) {
                     inventory.setItemStack((ItemStack)null);
                  }
               }
            }
         } else if (n3 == 1) {
            if (n < 0) {
               return null;
            }

            Slot slot3 = (Slot)this.inventorySlots.get(n);
            if (slot3 != null && slot3.canTakeStack(player)) {
               ItemStack stack4 = this.transferStackInSlot(player, n);
               if (stack4 != null) {
                  Item item = stack4.getItem();
                  stack = stack4.copy();
                  if (slot3.getStack() != null && slot3.getStack().getItem() == item) {
                     this.retrySlotClick(n, n2, true, player);
                  }
               }
            }
         } else {
            if (n < 0) {
               return null;
            }

            Slot slot4 = (Slot)this.inventorySlots.get(n);
            if (slot4 != null) {
               ItemStack stack5 = slot4.getStack();
               ItemStack itemStack = inventory.getItemStack();
               if (stack5 != null) {
                  stack = stack5.copy();
               }

               if (stack5 == null) {
                  if (itemStack != null && slot4.isItemValid(itemStack)) {
                     int n5 = n2 == 0 ? itemStack.stackSize : 1;
                     if (n5 > slot4.getItemStackLimit(itemStack)) {
                        n5 = slot4.getItemStackLimit(itemStack);
                     }

                     if (itemStack.stackSize >= n5) {
                        slot4.putStack(itemStack.splitStack(n5));
                     }

                     if (itemStack.stackSize == 0) {
                        inventory.setItemStack((ItemStack)null);
                     }
                  }
               } else if (slot4.canTakeStack(player)) {
                  if (itemStack == null) {
                     inventory.setItemStack(slot4.decrStackSize(n2 == 0 ? stack5.stackSize : (stack5.stackSize + 1) / 2));
                     if (stack5.stackSize == 0) {
                        slot4.putStack((ItemStack)null);
                     }

                     slot4.onPickupFromSlot(player, inventory.getItemStack());
                  } else if (slot4.isItemValid(itemStack)) {
                     if (stack5.getItem() == itemStack.getItem() && stack5.getMetadata() == itemStack.getMetadata() && ItemStack.areItemStackTagsEqual(stack5, itemStack)) {
                        int n7 = n2 == 0 ? itemStack.stackSize : 1;
                        if (n7 > slot4.getItemStackLimit(itemStack) - stack5.stackSize) {
                           n7 = slot4.getItemStackLimit(itemStack) - stack5.stackSize;
                        }

                        if (n7 > itemStack.getMaxStackSize() - stack5.stackSize) {
                           n7 = itemStack.getMaxStackSize() - stack5.stackSize;
                        }

                        itemStack.splitStack(n7);
                        if (itemStack.stackSize == 0) {
                           inventory.setItemStack((ItemStack)null);
                        }

                        stack5.stackSize += n7;
                     } else if (itemStack.stackSize <= slot4.getItemStackLimit(itemStack)) {
                        slot4.putStack(itemStack);
                        inventory.setItemStack(stack5);
                     }
                  } else if (stack5.getItem() == itemStack.getItem()
                     && itemStack.getMaxStackSize() > 1
                     && (!stack5.getHasSubtypes() || stack5.getMetadata() == itemStack.getMetadata())
                     && ItemStack.areItemStackTagsEqual(stack5, itemStack)) {
                     int stackSize2 = stack5.stackSize;
                     if (stackSize2 > 0 && stackSize2 + itemStack.stackSize <= itemStack.getMaxStackSize()) {
                        itemStack.stackSize += stackSize2;
                        stack5 = slot4.decrStackSize(stackSize2);
                        if (stack5.stackSize == 0) {
                           slot4.putStack((ItemStack)null);
                        }

                        slot4.onPickupFromSlot(player, inventory.getItemStack());
                     }
                  }
               }

               slot4.onSlotChanged();
            }
         }
      } else if (n3 == 2 && n2 >= 0 && n2 < 9) {
         Slot slot5 = (Slot)this.inventorySlots.get(n);
         if (slot5.canTakeStack(player)) {
            ItemStack stackInSlot = inventory.getStackInSlot(n2);
            boolean flag = stackInSlot == null || slot5.inventory == inventory && slot5.isItemValid(stackInSlot);
            int n8 = -1;
            if (!flag) {
               n8 = inventory.getFirstEmptyStack();
               flag |= n8 > -1;
            }

            if (slot5.getHasStack() && flag) {
               ItemStack stack7 = slot5.getStack();
               inventory.setInventorySlotContents(n2, stack7.copy());
               if ((slot5.inventory != inventory || !slot5.isItemValid(stackInSlot)) && stackInSlot != null) {
                  if (n8 > -1) {
                     inventory.addItemStackToInventory(stackInSlot);
                     slot5.decrStackSize(stack7.stackSize);
                     slot5.putStack((ItemStack)null);
                     slot5.onPickupFromSlot(player, stack7);
                  }
               } else {
                  slot5.decrStackSize(stack7.stackSize);
                  slot5.putStack(stackInSlot);
                  slot5.onPickupFromSlot(player, stack7);
               }
            } else if (!slot5.getHasStack() && stackInSlot != null && slot5.isItemValid(stackInSlot)) {
               inventory.setInventorySlotContents(n2, (ItemStack)null);
               slot5.putStack(stackInSlot);
            }
         }
      } else if (n3 == 3 && player.capabilities.isCreativeMode && inventory.getItemStack() == null && n >= 0) {
         Slot slot6 = (Slot)this.inventorySlots.get(n);
         if (slot6 != null && slot6.getHasStack()) {
            ItemStack stack8 = slot6.getStack().copy();
            stack8.stackSize = stack8.getMaxStackSize();
            inventory.setItemStack(stack8);
         }
      } else if (n3 == 4 && inventory.getItemStack() == null && n >= 0) {
         Slot slot7 = (Slot)this.inventorySlots.get(n);
         if (slot7 != null && slot7.getHasStack() && slot7.canTakeStack(player)) {
            ItemStack stack9 = slot7.decrStackSize(n2 == 0 ? 1 : slot7.getStack().stackSize);
            slot7.onPickupFromSlot(player, stack9);
            player.dropPlayerItemWithRandomChoice(stack9, true);
         }
      } else if (n3 == 6 && n >= 0) {
         Slot slot8 = (Slot)this.inventorySlots.get(n);
         ItemStack itemStack2 = inventory.getItemStack();
         if (itemStack2 != null && (slot8 == null || !slot8.getHasStack() || !slot8.canTakeStack(player))) {
            int n9 = n2 == 0 ? 0 : this.inventorySlots.size() - 1;
            int n10 = n2 == 0 ? 1 : -1;

            for (int i = 0; i < 2; i++) {
               for (int j = n9; j >= 0 && j < this.inventorySlots.size() && itemStack2.stackSize < itemStack2.getMaxStackSize(); j += n10) {
                  Slot slot9 = (Slot)this.inventorySlots.get(j);
                  if (slot9.getHasStack()
                     && canAddItemToSlot(slot9, itemStack2, true)
                     && slot9.canTakeStack(player)
                     && this.canMergeSlot(itemStack2, slot9)
                     && (i != 0 || slot9.getStack().stackSize != slot9.getStack().getMaxStackSize())) {
                     int n11 = Math.min(itemStack2.getMaxStackSize() - itemStack2.stackSize, slot9.getStack().stackSize);
                     ItemStack stack10 = slot9.decrStackSize(n11);
                     itemStack2.stackSize += n11;
                     if (stack10.stackSize <= 0) {
                        slot9.putStack((ItemStack)null);
                     }

                     slot9.onPickupFromSlot(player, stack10);
                  }
               }
            }
         }

         this.detectAndSendChanges();
      }

      return stack;
   }

   public List getInventory() {
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i < this.inventorySlots.size(); i++) {
         list.add(((Slot)this.inventorySlots.get(i)).getStack());
      }

      return list;
   }

   public boolean canDragIntoSlot(Slot slot) {
      return true;
   }

   public void setCanCraft(EntityPlayer player, boolean flag) {
      if (flag) {
         this.playerList.remove(player);
      } else {
         this.playerList.add(player);
      }
   }

   public void retrySlotClick(int n, int n2, boolean flag, EntityPlayer player) {
      this.slotClick(n, n2, 1, player);
   }

   public abstract boolean canInteractWith(EntityPlayer player);

   public void resetDrag() {
      this.dragEvent = 0;
      this.dragSlots.clear();
   }

   public static int getDragEvent(int n) {
      return n & 3;
   }

   public static int calcRedstoneFromInventory(IInventory inventory) {
      if (inventory == null) {
         return 0;
      } else {
         int n = 0;
         float f = 0.0F;

         for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stackInSlot = inventory.getStackInSlot(i);
            if (stackInSlot != null) {
               f += (float)stackInSlot.stackSize / (float)Math.min(inventory.getInventoryStackLimit(), stackInSlot.getMaxStackSize());
               n++;
            }
         }

         f /= (float)inventory.getSizeInventory();
         return MathHelper.floor_float(f * 14.0F) + (n > 0 ? 1 : 0);
      }
   }

   public void onContainerClosed(EntityPlayer player) {
      InventoryPlayer inventory = player.inventory;
      if (inventory.getItemStack() != null) {
         player.dropPlayerItemWithRandomChoice(inventory.getItemStack(), false);
         inventory.setItemStack((ItemStack)null);
      }
   }

   public void updateProgressBar(int n, int n2) {
   }

   public Slot getSlot(int n) {
      return (Slot)this.inventorySlots.get(n);
   }

   public Slot getSlotFromInventory(IInventory inventory, int n) {
      for (int i = 0; i < this.inventorySlots.size(); i++) {
         Slot slot = (Slot)this.inventorySlots.get(i);
         if (slot.isHere(inventory, n)) {
            return slot;
         }
      }

      return null;
   }

   public static int calcRedstone(TileEntity tileEntity) {
      return tileEntity instanceof IInventory ? calcRedstoneFromInventory((IInventory)tileEntity) : 0;
   }
}
