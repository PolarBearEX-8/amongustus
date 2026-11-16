package net.minecraft.inventory;

import client.event.CoreListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public abstract class Container {
   public List itemList = Lists.newArrayList();
   public List itemList2 = Lists.newArrayList();
   public int indexOffset2;
   public short shortIndex;
   public int indexOffset = -1;
   public int indexOffset1;
   public final Set entrySet = Sets.newHashSet();
   public List itemList3 = Lists.newArrayList();
   public Set entrySet2 = Sets.newHashSet();

   public void processAction(IInventory iInventory) {
      this.detectAndSendChanges();
   }

   public Slot getSlot(int count) {
      return (Slot)this.itemList2.get(count);
   }

   public void applyEntityPlayer(EntityPlayer player) {
      InventoryPlayer inventoryPlayer = player.inventoryPlayer;
      if (inventoryPlayer.getItemStack() != null) {
         player.resolveEntityItem(inventoryPlayer.getItemStack(), false);
         inventoryPlayer.setItemStack((ItemStack)null);
      }
   }

   public ItemStack slotClick(int count, int count2, int count3, EntityPlayer player) {
      ItemStack stack = null;
      InventoryPlayer inventoryPlayer = player.inventoryPlayer;
      if (count3 == 5) {
         int indexOffset3 = this.indexOffset1;
         this.indexOffset1 = processAction3(count2);
         if ((indexOffset3 != 1 || this.indexOffset1 != 2) && indexOffset3 != this.indexOffset1) {
            this.processAction();
         } else if (inventoryPlayer.getItemStack() == null) {
            this.processAction();
         } else if (this.indexOffset1 == 0) {
            this.indexOffset = processAction2(count2);
            if (matchesEntityPlayer(this.indexOffset, player)) {
               this.indexOffset1 = 1;
               this.entrySet.clear();
            } else {
               this.processAction();
            }
         } else if (this.indexOffset1 == 1) {
            Slot slot = (Slot)this.itemList2.get(count);
            if (slot != null
               && matchesSlot(slot, inventoryPlayer.getItemStack(), true)
               && slot.isItemValid(inventoryPlayer.getItemStack())
               && inventoryPlayer.getItemStack().animationsToGo > this.entrySet.size()
               && this.processAction(slot)) {
               this.entrySet.add(slot);
            }
         } else if (this.indexOffset1 == 2) {
            if (!this.entrySet.isEmpty()) {
               ItemStack stack2 = inventoryPlayer.getItemStack().getItemStack();
               int animationsToGo = inventoryPlayer.getItemStack().animationsToGo;

               for (Slot slot2 : (Iterable<Slot>)this.entrySet) {
                  if (slot2 != null
                     && matchesSlot(slot2, inventoryPlayer.getItemStack(), true)
                     && slot2.isItemValid(inventoryPlayer.getItemStack())
                     && inventoryPlayer.getItemStack().animationsToGo >= this.entrySet.size()
                     && this.processAction(slot2)) {
                     ItemStack stack3 = stack2.getItemStack();
                     int count4 = slot2.isActive() ? slot2.getStack().animationsToGo : 0;
                     applySet(this.entrySet, this.indexOffset, stack3, count4);
                     if (stack3.animationsToGo > stack3.processAction5()) {
                        stack3.animationsToGo = stack3.processAction5();
                     }

                     if (stack3.animationsToGo > slot2.processAction3(stack3)) {
                        stack3.animationsToGo = slot2.processAction3(stack3);
                     }

                     animationsToGo -= stack3.animationsToGo - count4;
                     slot2.putStack(stack3);
                  }
               }

               stack2.animationsToGo = animationsToGo;
               if (stack2.animationsToGo <= 0) {
                  stack2 = null;
               }

               inventoryPlayer.setItemStack(stack2);
            }

            this.processAction();
         } else {
            this.processAction();
         }
      } else if (this.indexOffset1 != 0) {
         this.processAction();
      } else if ((count3 == 0 || count3 == 1) && (count2 == 0 || count2 == 1)) {
         if (count == -999) {
            if (inventoryPlayer.getItemStack() != null) {
               if (count2 == 0) {
                  player.resolveEntityItem(inventoryPlayer.getItemStack(), true);
                  inventoryPlayer.setItemStack((ItemStack)null);
               }

               if (count2 == 1) {
                  player.resolveEntityItem(inventoryPlayer.getItemStack().resolveItemStack(1), true);
                  if (inventoryPlayer.getItemStack().animationsToGo == 0) {
                     inventoryPlayer.setItemStack((ItemStack)null);
                  }
               }
            }
         } else if (count3 == 1) {
            if (count < 0) {
               return null;
            }

            Slot slot3 = (Slot)this.itemList2.get(count);
            if (slot3 != null && slot3.processAction(player)) {
               ItemStack stack4 = this.resolveItemStack(player, count);
               if (stack4 != null) {
                  Item item = stack4.getItem();
                  stack = stack4.getItemStack();
                  if (slot3.getStack() != null && slot3.getStack().getItem() == item) {
                     this.processAction(count, count2, true, player);
                  }
               }
            }
         } else {
            if (count < 0) {
               return null;
            }

            Slot slot4 = (Slot)this.itemList2.get(count);
            if (slot4 != null) {
               ItemStack stack5 = slot4.getStack();
               ItemStack stack6 = inventoryPlayer.getItemStack();
               if (stack5 != null) {
                  stack = stack5.getItemStack();
               }

               if (stack5 == null) {
                  if (stack6 != null && slot4.isItemValid(stack6)) {
                     int count5 = count2 == 0 ? stack6.animationsToGo : 1;
                     if (count5 > slot4.processAction3(stack6)) {
                        count5 = slot4.processAction3(stack6);
                     }

                     if (stack6.animationsToGo >= count5) {
                        slot4.putStack(stack6.resolveItemStack(count5));
                     }

                     if (stack6.animationsToGo == 0) {
                        inventoryPlayer.setItemStack((ItemStack)null);
                     }
                  }
               } else if (slot4.processAction(player)) {
                  if (stack6 == null) {
                     int count6 = count2 == 0 ? stack5.animationsToGo : (stack5.animationsToGo + 1) / 2;
                     ItemStack stack7 = slot4.decrStackSize(count6);
                     inventoryPlayer.setItemStack(stack7);
                     if (stack5.animationsToGo == 0) {
                        slot4.putStack((ItemStack)null);
                     }

                     slot4.processAction(player, inventoryPlayer.getItemStack());
                  } else if (slot4.isItemValid(stack6)) {
                     if (stack5.getItem() == stack6.getItem() && stack5.getIndexOffset() == stack6.getIndexOffset() && ItemStack.matchesItemStack2(stack5, stack6)) {
                        int count7 = count2 == 0 ? stack6.animationsToGo : 1;
                        if (count7 > slot4.processAction3(stack6) - stack5.animationsToGo) {
                           count7 = slot4.processAction3(stack6) - stack5.animationsToGo;
                        }

                        if (count7 > stack6.processAction5() - stack5.animationsToGo) {
                           count7 = stack6.processAction5() - stack5.animationsToGo;
                        }

                        stack6.resolveItemStack(count7);
                        if (stack6.animationsToGo == 0) {
                           inventoryPlayer.setItemStack((ItemStack)null);
                        }

                        stack5.animationsToGo += count7;
                     } else if (stack6.animationsToGo <= slot4.processAction3(stack6)) {
                        slot4.putStack(stack6);
                        inventoryPlayer.setItemStack(stack5);
                     }
                  } else if (stack5.getItem() == stack6.getItem()
                     && stack6.processAction5() > 1
                     && (!stack5.processAction2() || stack5.getIndexOffset() == stack6.getIndexOffset())
                     && ItemStack.matchesItemStack2(stack5, stack6)) {
                     int animationsToGo2 = stack5.animationsToGo;
                     if (animationsToGo2 > 0 && animationsToGo2 + stack6.animationsToGo <= stack6.processAction5()) {
                        stack6.animationsToGo += animationsToGo2;
                        if (slot4.decrStackSize(animationsToGo2).animationsToGo == 0) {
                           slot4.putStack((ItemStack)null);
                        }

                        slot4.processAction(player, inventoryPlayer.getItemStack());
                     }
                  }
               }

               slot4.onSlotChanged();
            }
         }
      } else if (count3 == 2 && count2 >= 0 && count2 < 9) {
         Slot slot5 = (Slot)this.itemList2.get(count);
         if (slot5.processAction(player)) {
            ItemStack stack8 = inventoryPlayer.processAction3(count2);
            boolean flag = stack8 == null || slot5.iInventory == inventoryPlayer && slot5.isItemValid(stack8);
            int count8 = -1;
            if (!flag) {
               count8 = inventoryPlayer.getInt5();
               flag |= count8 > -1;
            }

            if (slot5.isActive() && flag) {
               ItemStack stack9 = slot5.getStack();
               inventoryPlayer.processAction(count2, stack9.getItemStack());
               if ((slot5.iInventory != inventoryPlayer || !slot5.isItemValid(stack8)) && stack8 != null) {
                  if (count8 > -1) {
                     inventoryPlayer.checkItemStack3(stack8);
                     slot5.decrStackSize(stack9.animationsToGo);
                     slot5.putStack((ItemStack)null);
                     slot5.processAction(player, stack9);
                  }
               } else {
                  slot5.decrStackSize(stack9.animationsToGo);
                  slot5.putStack(stack8);
                  slot5.processAction(player, stack9);
               }
            } else if (!slot5.isActive() && stack8 != null && slot5.isItemValid(stack8)) {
               inventoryPlayer.processAction(count2, (ItemStack)null);
               slot5.putStack(stack8);
            }
         }
      } else if (count3 == 3 && player.invulnerableManager.enabled2 && inventoryPlayer.getItemStack() == null && count >= 0) {
         Slot slot6 = (Slot)this.itemList2.get(count);
         if (slot6 != null && slot6.isActive()) {
            ItemStack stack10 = slot6.getStack().getItemStack();
            stack10.animationsToGo = stack10.processAction5();
            inventoryPlayer.setItemStack(stack10);
         }
      } else if (count3 == 4 && inventoryPlayer.getItemStack() == null && count >= 0) {
         Slot slot7 = (Slot)this.itemList2.get(count);
         if (slot7 != null && slot7.isActive() && slot7.processAction(player)) {
            ItemStack stack11 = slot7.decrStackSize(count2 == 0 ? 1 : slot7.getStack().animationsToGo);
            slot7.processAction(player, stack11);
            player.resolveEntityItem(stack11, true);
         }
      } else if (count3 == 6 && count >= 0) {
         Slot slot8 = (Slot)this.itemList2.get(count);
         ItemStack stack12 = inventoryPlayer.getItemStack();
         if (stack12 != null && (slot8 == null || !slot8.isActive() || !slot8.processAction(player))) {
            int count9 = count2 == 0 ? 0 : this.itemList2.size() - 1;
            int count10 = count2 == 0 ? 1 : -1;

            for (int index = 0; index < 2; index++) {
               for (int index2 = count9; index2 >= 0 && index2 < this.itemList2.size() && stack12.animationsToGo < stack12.processAction5(); index2 += count10) {
                  Slot slot9 = (Slot)this.itemList2.get(index2);
                  if (slot9.isActive()
                     && matchesSlot(slot9, stack12, true)
                     && slot9.processAction(player)
                     && this.processAction(stack12, slot9)
                     && (index != 0 || slot9.getStack().animationsToGo != slot9.getStack().processAction5())) {
                     int count11 = Math.min(stack12.processAction5() - stack12.animationsToGo, slot9.getStack().animationsToGo);
                     ItemStack stack13 = slot9.decrStackSize(count11);
                     stack12.animationsToGo += count11;
                     if (stack13.animationsToGo <= 0) {
                        slot9.putStack((ItemStack)null);
                     }

                     slot9.processAction(player, stack13);
                  }
               }
            }
         }

         this.detectAndSendChanges();
      }

      return stack;
   }

   public short computeShortCode(InventoryPlayer inventoryPlayer) {
      this.shortIndex++;
      return this.shortIndex;
   }

   public void processAction(int count, int count2, boolean flag, EntityPlayer player) {
      this.slotClick(count, count2, 1, player);
   }

   public static int processAction(int count, int count2) {
      return count & 3 | (count2 & 3) << 2;
   }

   public boolean processAction(EntityPlayer player, int count) {
      return false;
   }

   public void putStackInSlot(int count, ItemStack stack) {
      this.getSlot(count).putStack(stack);
   }

   public boolean processAction(ItemStack stack, Slot slot) {
      return true;
   }

   public Slot resolveSlot(IInventory iInventory, int count) {
      for (int index = 0; index < this.itemList2.size(); index++) {
         Slot slot = (Slot)this.itemList2.get(index);
         if (slot.matchesIInventory(iInventory, count)) {
            return slot;
         }
      }

      return null;
   }

   public ItemStack resolveItemStack(EntityPlayer player, int count) {
      Slot slot = (Slot)this.itemList2.get(count);
      return slot != null ? slot.getStack() : null;
   }

   public static int processAction2(int count) {
      return count >> 2 & 3;
   }

   public void applyItemStackArray(ItemStack[] stackArray) {
      for (int index = 0; index < stackArray.length; index++) {
         this.getSlot(index).putStack(stackArray[index]);
      }
   }

   public abstract boolean matchesEntityPlayer(EntityPlayer player);

   public static boolean matchesEntityPlayer(int count, EntityPlayer player) {
      return count == 0 || count == 1 || count == 2 && player.invulnerableManager.enabled2;
   }

   public Slot resolveSlot(Slot slot) {
      slot.indexOffset1 = this.itemList2.size();
      this.itemList2.add(slot);
      this.itemList.add((ItemStack)null);
      return slot;
   }

   public void processAction(CoreListener coreListener) {
      this.itemList3.remove(coreListener);
   }

   public static void applySet(Set set, int count, ItemStack stack, int count2) {
      switch (count) {
         case 0:
            stack.animationsToGo = MathHelper.floor_float((float)stack.animationsToGo / (float)set.size());
            break;
         case 1:
            stack.animationsToGo = 1;
            break;
         case 2:
            stack.animationsToGo = stack.getItem().getIndexOffset1();
      }

      stack.animationsToGo += count2;
   }

   public static int processAction3(int count) {
      return count & 3;
   }

   public boolean matchesItemStack(ItemStack stack, int count, int count2, boolean flag) {
      boolean flag2 = false;
      int count3 = count;
      if (flag) {
         count3 = count2 - 1;
      }

      if (stack.canExecute2()) {
         while (stack.animationsToGo > 0 && (!flag && count3 < count2 || flag && count3 >= count)) {
            Slot slot = (Slot)this.itemList2.get(count3);
            ItemStack stack2 = slot.getStack();
            if (stack2 != null
               && stack2.getItem() == stack.getItem()
               && (!stack.processAction2() || stack.getIndexOffset() == stack2.getIndexOffset())
               && ItemStack.matchesItemStack2(stack, stack2)) {
               int count4 = stack2.animationsToGo + stack.animationsToGo;
               if (count4 <= stack.processAction5()) {
                  stack.animationsToGo = 0;
                  stack2.animationsToGo = count4;
                  slot.onSlotChanged();
                  flag2 = true;
               } else if (stack2.animationsToGo < stack.processAction5()) {
                  stack.animationsToGo = stack.animationsToGo - (stack.processAction5() - stack2.animationsToGo);
                  stack2.animationsToGo = stack.processAction5();
                  slot.onSlotChanged();
                  flag2 = true;
               }
            }

            if (flag) {
               count3--;
            } else {
               count3++;
            }
         }
      }

      if (stack.animationsToGo > 0) {
         int count5;
         if (flag) {
            count5 = count2 - 1;
         } else {
            count5 = count;
         }

         while (!flag && count5 < count2 || flag && count5 >= count) {
            Slot slot2 = (Slot)this.itemList2.get(count5);
            if (slot2.getStack() == null) {
               slot2.putStack(stack.getItemStack());
               slot2.onSlotChanged();
               stack.animationsToGo = 0;
               flag2 = true;
               break;
            }

            if (flag) {
               count5--;
            } else {
               count5++;
            }
         }
      }

      return flag2;
   }

   public void processAction() {
      this.indexOffset1 = 0;
      this.entrySet.clear();
   }

   public void processAction2(int count, int count2) {
   }

   public boolean contains(EntityPlayer player) {
      return !this.entrySet2.contains(player);
   }

   public List getList() {
      ArrayList list = Lists.newArrayList();

      for (int index = 0; index < this.itemList2.size(); index++) {
         list.add(((Slot)this.itemList2.get(index)).getStack());
      }

      return list;
   }

   public boolean processAction(Slot slot) {
      return true;
   }

   public static int computeCount(IInventory iInventory) {
      if (iInventory == null) {
         return 0;
      } else {
         int count = 0;
         float factor = 0.0F;

         for (int index = 0; index < iInventory.processAction4(); index++) {
            ItemStack stack = iInventory.processAction3(index);
            if (stack != null) {
               factor += (float)stack.animationsToGo / (float)Math.min(iInventory.processAction2(), stack.processAction5());
               count++;
            }
         }

         return MathHelper.floor_float(factor / (float)iInventory.processAction4() * 14.0F) + (count > 0 ? 1 : 0);
      }
   }

   public void detectAndSendChanges() {
      for (int index = 0; index < this.itemList2.size(); index++) {
         ItemStack stack = ((Slot)this.itemList2.get(index)).getStack();
         if (!ItemStack.matchesItemStack((ItemStack)this.itemList.get(index), stack)) {
            ItemStack stack2 = stack == null ? null : stack.getItemStack();
            this.itemList.set(index, stack2);

            for (int index2 = 0; index2 < this.itemList3.size(); index2++) {
               ((CoreListener)this.itemList3.get(index2)).applyContainer(this, index, stack2);
            }
         }
      }
   }

   public static boolean matchesSlot(Slot slot, ItemStack stack, boolean flag) {
      boolean flag2 = slot == null || !slot.isActive();
      if (slot != null && slot.isActive() && stack != null && stack.matchesItemStack(slot.getStack()) && ItemStack.matchesItemStack2(slot.getStack(), stack)) {
         int count = flag ? 0 : stack.animationsToGo;
         flag2 |= slot.getStack().animationsToGo + count <= stack.processAction5();
      }

      return flag2;
   }

   public void applyEntityPlayer(EntityPlayer player, boolean flag) {
      if (flag) {
         this.entrySet2.remove(player);
      } else {
         this.entrySet2.add(player);
      }
   }

   public void applyCoreListener(CoreListener coreListener) {
      if (this.itemList3.contains(coreListener)) {
         throw new IllegalArgumentException("Listener already listening");
      } else {
         this.itemList3.add(coreListener);
         coreListener.applyContainer(this, this.getList());
         this.detectAndSendChanges();
      }
   }

   public static int computeCount(TileEntity tileEntity) {
      return tileEntity instanceof IInventory ? computeCount((IInventory)tileEntity) : 0;
   }
}
