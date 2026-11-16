package net.minecraft.inventory;

import client.util.AuxiliaryNameManager;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.SecondaryCallableAdapter;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.SlotManager;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class InventoryPlayer implements IInventory {
   public ItemStack[] mainInventory = new ItemStack[36];
   public ItemStack[] armorInventory = new ItemStack[4];
   public int currentItem;
   public EntityPlayer targetPlayer;
   public ItemStack itemStack;
   public boolean state;

   @Override
   public ItemStack getItemStack(int count, int count2) {
      int count3 = count;
      ItemStack[] mainInventory2 = this.mainInventory;
      if (count >= this.mainInventory.length) {
         mainInventory2 = this.armorInventory;
         count3 = count - this.mainInventory.length;
      }

      if (mainInventory2[count3] != null) {
         if (mainInventory2[count3].animationsToGo <= count2) {
            ItemStack stack = mainInventory2[count3];
            mainInventory2[count3] = null;
            return stack;
         } else {
            ItemStack stack2 = mainInventory2[count3].resolveItemStack(count2);
            if (mainInventory2[count3].animationsToGo == 0) {
               mainInventory2[count3] = null;
            }

            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public int processAction(int count) {
      return 0;
   }

   public NBTTagList getExtendedLoggerEntry(NBTTagList nBTTagList) {
      for (int index = 0; index < this.mainInventory.length; index++) {
         if (this.mainInventory[index] != null) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.put("Slot", (byte)index);
            this.mainInventory[index].resolveNBTTagCompound(tagCompound);
            nBTTagList.applyLISTManager(tagCompound);
         }
      }

      for (int index2 = 0; index2 < this.armorInventory.length; index2++) {
         if (this.armorInventory[index2] != null) {
            NBTTagCompound tagCompound2 = new NBTTagCompound();
            tagCompound2.put("Slot", (byte)(index2 + 100));
            this.armorInventory[index2].resolveNBTTagCompound(tagCompound2);
            nBTTagList.applyLISTManager(tagCompound2);
         }
      }

      return nBTTagList;
   }

   public void applyInventoryPlayer(InventoryPlayer inventoryPlayer) {
      for (int index = 0; index < this.mainInventory.length; index++) {
         this.mainInventory[index] = ItemStack.resolveItemStack(inventoryPlayer.mainInventory[index]);
      }

      for (int index2 = 0; index2 < this.armorInventory.length; index2++) {
         this.armorInventory[index2] = ItemStack.resolveItemStack(inventoryPlayer.armorInventory[index2]);
      }

      this.currentItem = inventoryPlayer.currentItem;
   }

   public ItemStack getCurrentItem() {
      return this.currentItem < 9 && this.currentItem >= 0 ? this.mainInventory[this.currentItem] : null;
   }

   @Override
   public boolean processAction() {
      return false;
   }

   @Override
   public void processAction(EntityPlayer player) {
   }

   private int getIndex(ItemStack stack) {
      for (int index = 0; index < this.mainInventory.length; index++) {
         if (this.mainInventory[index] != null
            && this.mainInventory[index].getItem() == stack.getItem()
            && this.mainInventory[index].canExecute2()
            && this.mainInventory[index].animationsToGo < this.mainInventory[index].processAction5()
            && this.mainInventory[index].animationsToGo < this.processAction2()
            && (!this.mainInventory[index].processAction2() || this.mainInventory[index].getIndexOffset() == stack.getIndexOffset())
            && ItemStack.matchesItemStack2(this.mainInventory[index], stack)) {
            return index;
         }
      }

      return -1;
   }

   public void applyItem(Item item, int count, boolean flag, boolean flag2) {
      ItemStack slot = this.getCurrentItem();
      int count2 = flag ? this.getIndex(item, count) : this.getIndex(item);
      if (count2 >= 0 && count2 < 9) {
         this.currentItem = count2;
      } else if (flag2 && item != null) {
         int intItem = this.getInt5();
         if (intItem >= 0 && intItem < 9) {
            this.currentItem = intItem;
         }

         if (slot == null || !slot.isActive2() || this.getIndex(slot.getItem(), slot.getIndexOffset2()) != this.currentItem) {
            int index = this.getIndex(item, count);
            int count3;
            if (index >= 0) {
               count3 = this.mainInventory[index].animationsToGo;
               this.mainInventory[index] = this.mainInventory[this.currentItem];
            } else {
               count3 = 1;
            }

            this.mainInventory[this.currentItem] = new ItemStack(item, count3, count);
         }
      }
   }

   public static int processAction6() {
      return 9;
   }

   @Override
   public void processAction(int count, ItemStack stack) {
      int count2 = count;
      ItemStack[] mainInventory2 = this.mainInventory;
      if (count >= mainInventory2.length) {
         count2 = count - mainInventory2.length;
         mainInventory2 = this.armorInventory;
      }

      mainInventory2[count2] = stack;
   }

   @Override
   public String getText() {
      return "container.inventory";
   }

   @Override
   public ItemStack getStackInSlot(int count) {
      int count2 = count;
      ItemStack[] mainInventory2 = this.mainInventory;
      if (count >= this.mainInventory.length) {
         mainInventory2 = this.armorInventory;
         count2 = count - this.mainInventory.length;
      }

      if (mainInventory2[count2] != null) {
         ItemStack stack = mainInventory2[count2];
         mainInventory2[count2] = null;
         return stack;
      } else {
         return null;
      }
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public int getIndex(Item item, int count, int count2, NBTTagCompound tagCompound) {
      int count3 = 0;

      for (int index = 0; index < this.mainInventory.length; index++) {
         ItemStack stack = this.mainInventory[index];
         if (stack != null
            && (item == null || stack.getItem() == item)
            && (count <= -1 || stack.getIndexOffset() == count)
            && (tagCompound == null || AuxiliaryNameManager.matchesLISTManager(tagCompound, stack.getStackTagCompound(), true))) {
            int count4 = count2 <= 0 ? stack.animationsToGo : Math.min(count2 - count3, stack.animationsToGo);
            count3 += count4;
            if (count2 != 0) {
               this.mainInventory[index].animationsToGo -= count4;
               if (this.mainInventory[index].animationsToGo == 0) {
                  this.mainInventory[index] = null;
               }

               if (count2 > 0 && count3 >= count2) {
                  return count3;
               }
            }
         }
      }

      for (int index2 = 0; index2 < this.armorInventory.length; index2++) {
         ItemStack stack2 = this.armorInventory[index2];
         if (stack2 != null
            && (item == null || stack2.getItem() == item)
            && (count <= -1 || stack2.getIndexOffset() == count)
            && (tagCompound == null || AuxiliaryNameManager.matchesLISTManager(tagCompound, stack2.getStackTagCompound(), false))) {
            int count5 = count2 <= 0 ? stack2.animationsToGo : Math.min(count2 - count3, stack2.animationsToGo);
            count3 += count5;
            if (count2 != 0) {
               this.armorInventory[index2].animationsToGo -= count5;
               if (this.armorInventory[index2].animationsToGo == 0) {
                  this.armorInventory[index2] = null;
               }

               if (count2 > 0 && count3 >= count2) {
                  return count3;
               }
            }
         }
      }

      if (this.itemStack != null) {
         if (item != null && this.itemStack.getItem() != item) {
            return count3;
         }

         if (count > -1 && this.itemStack.getIndexOffset() != count) {
            return count3;
         }

         if (tagCompound != null && !AuxiliaryNameManager.matchesLISTManager(tagCompound, this.itemStack.getStackTagCompound(), false)) {
            return count3;
         }

         int count6 = count2 <= 0 ? this.itemStack.animationsToGo : Math.min(count2 - count3, this.itemStack.animationsToGo);
         count3 += count6;
         if (count2 != 0) {
            this.itemStack.animationsToGo -= count6;
            if (this.itemStack.animationsToGo == 0) {
               this.itemStack = null;
            }

            if (count2 > 0 && count3 >= count2) {
               return count3;
            }
         }
      }

      return count3;
   }

   public void updateState3() {
      for (int index = 0; index < this.mainInventory.length; index++) {
         if (this.mainInventory[index] != null) {
            this.mainInventory[index].applyWorld(this.targetPlayer.worldObj, this.targetPlayer, index, this.currentItem == index);
         }
      }
   }

   public int getInt5() {
      for (int index = 0; index < this.mainInventory.length; index++) {
         if (this.mainInventory[index] == null) {
            return index;
         }
      }

      return -1;
   }

   @Override
   public IChatComponent getChatComponent() {
      return (IChatComponent)(this.processAction()
         ? new ChatComponentText(this.getText())
         : new BaseManagerStyleIChatComponentAdapter(this.getText(), new Object[0]));
   }

   @Override
   public int processAction2() {
      return 64;
   }

   public void updateExtendedLoggerEntry(NBTTagList nBTTagList) {
      this.mainInventory = new ItemStack[36];
      this.armorInventory = new ItemStack[4];

      for (int index = 0; index < nBTTagList.getInt(); index++) {
         NBTTagCompound nBTTagCompound = nBTTagList.getNBTTagCompound(index);
         int count = nBTTagCompound.computeByteCode("Slot") & 255;
         ItemStack stack = ItemStack.resolveItemStack(nBTTagCompound);
         if (stack != null) {
            if (count >= 0 && count < this.mainInventory.length) {
               this.mainInventory[count] = stack;
            }

            if (count >= 100 && count < this.armorInventory.length + 100) {
               this.armorInventory[count - 100] = stack;
            }
         }
      }
   }

   public void setItemStack(ItemStack itemStack2) {
      this.itemStack = itemStack2;
   }

   public void updateFloat(float factor) {
      float factor2 = factor / 4.0F;
      if (factor2 < 1.0F) {
         factor2 = 1.0F;
      }

      for (int index = 0; index < this.armorInventory.length; index++) {
         if (this.armorInventory[index] != null && this.armorInventory[index].getItem() instanceof ItemGoldenBoots) {
            this.armorInventory[index].applyEntityLivingBaseByEntityLivingBase((int)factor2, this.targetPlayer);
            if (this.armorInventory[index].animationsToGo == 0) {
               this.armorInventory[index] = null;
            }
         }
      }
   }

   @Override
   public boolean processAction2(int count, ItemStack stack) {
      return true;
   }

   public void updateInt(int count) {
      int count2 = count;
      if (count > 0) {
         count2 = 1;
      }

      if (count2 < 0) {
         count2 = -1;
      }

      SlotManager.indexOffset -= count2;

      while (SlotManager.indexOffset < 0) {
         SlotManager.indexOffset += 9;
      }

      while (SlotManager.indexOffset >= 9) {
         SlotManager.indexOffset -= 9;
      }

      this.currentItem -= count2;

      while (this.currentItem < 0) {
         this.currentItem += 9;
      }

      while (this.currentItem >= 9) {
         this.currentItem -= 9;
      }
   }

   @Override
   public void updateState2() {
      for (int index = 0; index < this.mainInventory.length; index++) {
         this.mainInventory[index] = null;
      }

      for (int index2 = 0; index2 < this.armorInventory.length; index2++) {
         this.armorInventory[index2] = null;
      }
   }

   private int getIndex2(ItemStack stack) {
      Item item = stack.getItem();
      int animationsToGo = stack.animationsToGo;
      int index = this.getIndex(stack);
      if (index < 0) {
         index = this.getInt5();
      }

      if (index < 0) {
         return animationsToGo;
      } else {
         if (this.mainInventory[index] == null) {
            this.mainInventory[index] = new ItemStack(item, 0, stack.getIndexOffset());
            if (stack.canExecute()) {
               this.mainInventory[index].processAction((NBTTagCompound)stack.getStackTagCompound().getLISTManager());
            }
         }

         int count = animationsToGo;
         if (animationsToGo > this.mainInventory[index].processAction5() - this.mainInventory[index].animationsToGo) {
            count = this.mainInventory[index].processAction5() - this.mainInventory[index].animationsToGo;
         }

         if (count > this.processAction2() - this.mainInventory[index].animationsToGo) {
            count = this.processAction2() - this.mainInventory[index].animationsToGo;
         }

         if (count == 0) {
            return animationsToGo;
         } else {
            int count2 = animationsToGo - count;
            this.mainInventory[index].animationsToGo += count;
            this.mainInventory[index].itemDamage = 5;
            return count2;
         }
      }
   }

   @Override
   public int processAction3() {
      return 0;
   }

   public boolean checkItemStack2(ItemStack stack) {
      for (int index = 0; index < this.armorInventory.length; index++) {
         if (this.armorInventory[index] != null && this.armorInventory[index].matchesItemStack(stack)) {
            return true;
         }
      }

      for (int index2 = 0; index2 < this.mainInventory.length; index2++) {
         if (this.mainInventory[index2] != null && this.mainInventory[index2].matchesItemStack(stack)) {
            return true;
         }
      }

      return false;
   }

   public float processAction(Block block) {
      float factor = 1.0F;
      if (this.mainInventory[this.currentItem] != null) {
         factor *= this.mainInventory[this.currentItem].processAction(block);
      }

      return factor;
   }

   public boolean checkItem(Item item) {
      int index = this.getIndex(item);
      if (index < 0) {
         return false;
      } else {
         if (--this.mainInventory[index].animationsToGo <= 0) {
            this.mainInventory[index] = null;
         }

         return true;
      }
   }

   private int getIndex(Item item) {
      for (int index = 0; index < this.mainInventory.length; index++) {
         if (this.mainInventory[index] != null && this.mainInventory[index].getItem() == item) {
            return index;
         }
      }

      return -1;
   }

   public void updateState4() {
      for (int index = 0; index < this.mainInventory.length; index++) {
         if (this.mainInventory[index] != null) {
            this.targetPlayer.resolveEntityItem(this.mainInventory[index], true, false);
            this.mainInventory[index] = null;
         }
      }

      for (int index2 = 0; index2 < this.armorInventory.length; index2++) {
         if (this.armorInventory[index2] != null) {
            this.targetPlayer.resolveEntityItem(this.armorInventory[index2], true, false);
            this.armorInventory[index2] = null;
         }
      }
   }

   public int getInt6() {
      int count = 0;

      for (int index = 0; index < this.armorInventory.length; index++) {
         if (this.armorInventory[index] != null && this.armorInventory[index].getItem() instanceof ItemGoldenBoots) {
            int indexOffset = ((ItemGoldenBoots)this.armorInventory[index].getItem()).indexOffset4;
            count += indexOffset;
         }
      }

      return count;
   }

   public ItemStack armorItemInSlot(int count) {
      return this.armorInventory[count];
   }

   @Override
   public int processAction4() {
      return this.mainInventory.length + 4;
   }

   @Override
   public void processAction(int count, int count2) {
   }

   @Override
   public void processAction2(EntityPlayer player) {
   }

   public boolean checkItemStack3(ItemStack stack) {
      if (stack != null && stack.animationsToGo != 0 && stack.getItem() != null) {
         try {
            if (stack.isActiveAsFlag()) {
               int intItem = this.getInt5();
               if (intItem >= 0) {
                  this.mainInventory[intItem] = ItemStack.resolveItemStack(stack);
                  this.mainInventory[intItem].itemDamage = 5;
                  stack.animationsToGo = 0;
                  return true;
               } else if (this.targetPlayer.invulnerableManager.enabled2) {
                  stack.animationsToGo = 0;
                  return true;
               } else {
                  return false;
               }
            } else {
               int count;
               do {
                  count = stack.animationsToGo;
                  stack.animationsToGo = this.getIndex2(stack);
               } while (stack.animationsToGo > 0 && stack.animationsToGo < count);

               if (stack.animationsToGo == count && this.targetPlayer.invulnerableManager.enabled2) {
                  stack.animationsToGo = 0;
                  return true;
               } else {
                  return stack.animationsToGo < count;
               }
            }
         } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Adding item to inventory");
            CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Item being added");
            crashReportCategory.add("Item ID", Item.getIndex(stack.getItem()));
            crashReportCategory.add("Item data", stack.getIndexOffset());
            crashReportCategory.applyText("Item name", new SecondaryCallableAdapter(this, stack));
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         }
      } else {
         return false;
      }
   }

   public boolean checkItem2(Item item) {
      return this.getIndex(item) >= 0;
   }

   private int getIndex(Item item, int count) {
      for (int index = 0; index < this.mainInventory.length; index++) {
         if (this.mainInventory[index] != null && this.mainInventory[index].getItem() == item && this.mainInventory[index].getIndexOffset() == count) {
            return index;
         }
      }

      return -1;
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return this.targetPlayer.isDead ? false : player.getCoordinate(this.targetPlayer) <= 64.0;
   }

   @Override
   public ItemStack processAction3(int count) {
      int count2 = count;
      ItemStack[] mainInventory2 = this.mainInventory;
      if (count >= mainInventory2.length) {
         count2 = count - mainInventory2.length;
         mainInventory2 = this.armorInventory;
      }

      return mainInventory2[count2];
   }

   public boolean checkBlock(Block block) {
      if (block.getMaterial().isEnabled3()) {
         return true;
      } else {
         ItemStack stack = this.processAction3(this.currentItem);
         return stack != null ? stack.processAction2(block) : false;
      }
   }

   @Override
   public void processAction5() {
      this.state = true;
   }

   public InventoryPlayer(EntityPlayer player) {
      this.targetPlayer = player;
   }
}
