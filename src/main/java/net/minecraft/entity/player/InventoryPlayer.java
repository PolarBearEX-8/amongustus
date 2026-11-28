package net.minecraft.entity.player;

import java.util.concurrent.Callable;
import net.augustus.utils.player.SilentSlotManager;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ReportedException;

public class InventoryPlayer implements IInventory {
   public ItemStack[] mainInventory = new ItemStack[36];
   public ItemStack[] armorInventory = new ItemStack[4];
   public int currentItem;
   public EntityPlayer player;
   private ItemStack itemStack;
   public boolean inventoryChanged;

   public void readFromNBT(NBTTagList tag) {
      this.mainInventory = new ItemStack[36];
      this.armorInventory = new ItemStack[4];

      for (int i = 0; i < tag.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tag.getCompoundTagAt(i);
         int n = compoundTagAt.getByte("Slot") & 255;
         ItemStack stack = ItemStack.loadItemStackFromNBT(compoundTagAt);
         if (stack != null) {
            if (n >= 0 && n < this.mainInventory.length) {
               this.mainInventory[n] = stack;
            }

            if (n >= 100 && n < this.armorInventory.length + 100) {
               this.armorInventory[n - 100] = stack;
            }
         }
      }
   }

   @Override
   public void openInventory(EntityPlayer player2) {
   }

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return true;
   }

   public boolean hasItemStack(ItemStack stack) {
      for (int i = 0; i < this.armorInventory.length; i++) {
         if (this.armorInventory[i] != null && this.armorInventory[i].isItemEqual(stack)) {
            return true;
         }
      }

      for (int j = 0; j < this.mainInventory.length; j++) {
         if (this.mainInventory[j] != null && this.mainInventory[j].isItemEqual(stack)) {
            return true;
         }
      }

      return false;
   }

   public boolean consumeInventoryItem(Item item) {
      int n = this.getInventorySlotContainItem(item);
      if (n < 0) {
         return false;
      } else {
         if (--this.mainInventory[n].stackSize <= 0) {
            this.mainInventory[n] = null;
         }

         return true;
      }
   }

   public static int getHotbarSize() {
      return 9;
   }

   @Override
   public String getName() {
      return "container.inventory";
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      ItemStack[] mainInventory2 = this.mainInventory;
      if (n >= this.mainInventory.length) {
         mainInventory2 = this.armorInventory;
         n -= this.mainInventory.length;
      }

      if (mainInventory2[n] != null) {
         ItemStack stack = mainInventory2[n];
         mainInventory2[n] = null;
         return stack;
      } else {
         return null;
      }
   }

   public void setCurrentItem(Item item, int n, boolean flag, boolean flag2) {
      ItemStack currentItem2 = this.getCurrentItem();
      int n2 = flag ? this.getInventorySlotContainItemAndDamage(item, n) : this.getInventorySlotContainItem(item);
      if (n2 >= 0 && n2 < 9) {
         this.currentItem = n2;
      } else if (flag2 && item != null) {
         int firstEmptyStack = this.getFirstEmptyStack();
         if (firstEmptyStack >= 0 && firstEmptyStack < 9) {
            this.currentItem = firstEmptyStack;
         }

         if (currentItem2 == null || !currentItem2.isItemEnchantable() || this.getInventorySlotContainItemAndDamage(currentItem2.getItem(), currentItem2.getItemDamage()) != this.currentItem) {
            int n3 = this.getInventorySlotContainItemAndDamage(item, n);
            int n4;
            if (n3 >= 0) {
               n4 = this.mainInventory[n3].stackSize;
               this.mainInventory[n3] = this.mainInventory[this.currentItem];
            } else {
               n4 = 1;
            }

            this.mainInventory[this.currentItem] = new ItemStack(item, n4, n);
         }
      }
   }

   public ItemStack getCurrentItem() {
      return this.currentItem < 9 && this.currentItem >= 0 ? this.mainInventory[this.currentItem] : null;
   }

   @Override
   public int getField(int n) {
      return 0;
   }

   public void changeCurrentItem(int n) {
      if (n > 0) {
         n = 1;
      }

      if (n < 0) {
         n = -1;
      }

      SilentSlotManager.realHotbarSlot -= n;

      while (SilentSlotManager.realHotbarSlot < 0) {
         SilentSlotManager.realHotbarSlot += 9;
      }

      while (SilentSlotManager.realHotbarSlot >= 9) {
         SilentSlotManager.realHotbarSlot -= 9;
      }

      this.currentItem -= n;

      while (this.currentItem < 0) {
         this.currentItem += 9;
      }

      while (this.currentItem >= 9) {
         this.currentItem -= 9;
      }
   }

   public void decrementAnimations() {
      for (int i = 0; i < this.mainInventory.length; i++) {
         if (this.mainInventory[i] != null) {
            this.mainInventory[i].updateAnimation(this.player.entityWorld, this.player, i, this.currentItem == i);
         }
      }
   }

   public ItemStack armorItemInSlot(int n) {
      return this.armorInventory[n];
   }

   public boolean canHeldItemHarvest(Block block) {
      if (block.getMaterial().isToolNotRequired()) {
         return true;
      } else {
         ItemStack stackInSlot = this.getStackInSlot(this.currentItem);
         return stackInSlot != null ? stackInSlot.canHarvestBlock(block) : false;
      }
   }

   public NBTTagList writeToNBT(NBTTagList tag) {
      for (int i = 0; i < this.mainInventory.length; i++) {
         if (this.mainInventory[i] != null) {
            NBTTagCompound tag2 = new NBTTagCompound();
            tag2.setByte("Slot", (byte)i);
            this.mainInventory[i].writeToNBT(tag2);
            tag.appendTag(tag2);
         }
      }

      for (int j = 0; j < this.armorInventory.length; j++) {
         if (this.armorInventory[j] != null) {
            NBTTagCompound tag3 = new NBTTagCompound();
            tag3.setByte("Slot", (byte)(j + 100));
            this.armorInventory[j].writeToNBT(tag3);
            tag.appendTag(tag3);
         }
      }

      return tag;
   }

   public boolean addItemStackToInventory(ItemStack stack) {
      if (stack != null && stack.stackSize != 0 && stack.getItem() != null) {
         try {
            if (stack.isItemDamaged()) {
               int firstEmptyStack = this.getFirstEmptyStack();
               if (firstEmptyStack >= 0) {
                  this.mainInventory[firstEmptyStack] = ItemStack.copyItemStack(stack);
                  this.mainInventory[firstEmptyStack].animationsToGo = 5;
                  stack.stackSize = 0;
                  return true;
               } else if (this.player.capabilities.isCreativeMode) {
                  stack.stackSize = 0;
                  return true;
               } else {
                  return false;
               }
            } else {
               int n;
               do {
                  n = stack.stackSize;
                  stack.stackSize = this.storePartialItemStack(stack);
               } while (stack.stackSize > 0 && stack.stackSize < n);

               if (stack.stackSize == n && this.player.capabilities.isCreativeMode) {
                  stack.stackSize = 0;
                  return true;
               } else {
                  return stack.stackSize < n;
               }
            }
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Adding item to inventory");
            CrashReportCategory crashReportCategory = crashReport.makeCategory("Item being added");
            crashReportCategory.addCrashSection("Item ID", Item.getIdFromItem(stack.getItem()));
            crashReportCategory.addCrashSection("Item data", stack.getMetadata());
            crashReportCategory.addCrashSectionCallable("Item name", new InventoryPlayer.ValueCallable(this, stack));
            throw new ReportedException(crashReport);
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player2) {
      return this.player.isDead ? false : player2.getDistanceSqToEntity(this.player) <= 64.0;
   }

   public void setItemStack(ItemStack stack) {
      this.itemStack = stack;
   }

   public float getStrVsBlock(Block block) {
      float f = 1.0F;
      if (this.mainInventory[this.currentItem] != null) {
         f *= this.mainInventory[this.currentItem].getStrVsBlock(block);
      }

      return f;
   }

   @Override
   public int getSizeInventory() {
      return this.mainInventory.length + 4;
   }

   public int getTotalArmorValue() {
      int n = 0;

      for (int i = 0; i < this.armorInventory.length; i++) {
         if (this.armorInventory[i] != null && this.armorInventory[i].getItem() instanceof ItemArmor) {
            int damageReduceAmount = ((ItemArmor)this.armorInventory[i].getItem()).damageReduceAmount;
            n += damageReduceAmount;
         }
      }

      return n;
   }

   public void copyInventory(InventoryPlayer inventoryPlayer) {
      for (int i = 0; i < this.mainInventory.length; i++) {
         this.mainInventory[i] = ItemStack.copyItemStack(inventoryPlayer.mainInventory[i]);
      }

      for (int j = 0; j < this.armorInventory.length; j++) {
         this.armorInventory[j] = ItemStack.copyItemStack(inventoryPlayer.armorInventory[j]);
      }

      this.currentItem = inventoryPlayer.currentItem;
   }

   public boolean hasItem(Item item) {
      return this.getInventorySlotContainItem(item) >= 0;
   }

   public void damageArmor(float f) {
      f /= 4.0F;
      if (f < 1.0F) {
         f = 1.0F;
      }

      for (int i = 0; i < this.armorInventory.length; i++) {
         if (this.armorInventory[i] != null && this.armorInventory[i].getItem() instanceof ItemArmor) {
            this.armorInventory[i].damageItem((int)f, this.player);
            if (this.armorInventory[i].stackSize == 0) {
               this.armorInventory[i] = null;
            }
         }
      }
   }

   private int getInventorySlotContainItem(Item item) {
      for (int i = 0; i < this.mainInventory.length; i++) {
         if (this.mainInventory[i] != null && this.mainInventory[i].getItem() == item) {
            return i;
         }
      }

      return -1;
   }

   public InventoryPlayer(EntityPlayer player2) {
      this.player = player2;
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   @Override
   public boolean hasCustomName() {
      return false;
   }

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      ItemStack[] mainInventory2 = this.mainInventory;
      if (n >= mainInventory2.length) {
         n -= mainInventory2.length;
         mainInventory2 = this.armorInventory;
      }

      mainInventory2[n] = stack;
   }

   private int storeItemStack(ItemStack stack) {
      for (int i = 0; i < this.mainInventory.length; i++) {
         if (this.mainInventory[i] != null
            && this.mainInventory[i].getItem() == stack.getItem()
            && this.mainInventory[i].isStackable()
            && this.mainInventory[i].stackSize < this.mainInventory[i].getMaxStackSize()
            && this.mainInventory[i].stackSize < this.getInventoryStackLimit()
            && (!this.mainInventory[i].getHasSubtypes() || this.mainInventory[i].getMetadata() == stack.getMetadata())
            && ItemStack.areItemStackTagsEqual(this.mainInventory[i], stack)) {
            return i;
         }
      }

      return -1;
   }

   private int storePartialItemStack(ItemStack stack) {
      Item item = stack.getItem();
      int stackSize = stack.stackSize;
      int n = this.storeItemStack(stack);
      if (n < 0) {
         n = this.getFirstEmptyStack();
      }

      if (n < 0) {
         return stackSize;
      } else {
         if (this.mainInventory[n] == null) {
            this.mainInventory[n] = new ItemStack(item, 0, stack.getMetadata());
            if (stack.hasTagCompound()) {
               this.mainInventory[n].setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
            }
         }

         int n2 = stackSize;
         if (stackSize > this.mainInventory[n].getMaxStackSize() - this.mainInventory[n].stackSize) {
            n2 = this.mainInventory[n].getMaxStackSize() - this.mainInventory[n].stackSize;
         }

         if (n2 > this.getInventoryStackLimit() - this.mainInventory[n].stackSize) {
            n2 = this.getInventoryStackLimit() - this.mainInventory[n].stackSize;
         }

         if (n2 == 0) {
            return stackSize;
         } else {
            stackSize -= n2;
            this.mainInventory[n].stackSize += n2;
            this.mainInventory[n].animationsToGo = 5;
            return stackSize;
         }
      }
   }

   @Override
   public void markDirty() {
      this.inventoryChanged = true;
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   public void dropAllItems() {
      for (int i = 0; i < this.mainInventory.length; i++) {
         if (this.mainInventory[i] != null) {
            this.player.dropItem(this.mainInventory[i], true, false);
            this.mainInventory[i] = null;
         }
      }

      for (int j = 0; j < this.armorInventory.length; j++) {
         if (this.armorInventory[j] != null) {
            this.player.dropItem(this.armorInventory[j], true, false);
            this.armorInventory[j] = null;
         }
      }
   }

   @Override
   public void clear() {
      for (int i = 0; i < this.mainInventory.length; i++) {
         this.mainInventory[i] = null;
      }

      for (int j = 0; j < this.armorInventory.length; j++) {
         this.armorInventory[j] = null;
      }
   }

   public int clearMatchingItems(Item item, int n, int n2, NBTTagCompound tag) {
      int n3 = 0;

      for (int i = 0; i < this.mainInventory.length; i++) {
         ItemStack stack = this.mainInventory[i];
         if (stack != null
            && (item == null || stack.getItem() == item)
            && (n <= -1 || stack.getMetadata() == n)
            && (tag == null || NBTUtil.areNBTEquals(tag, stack.getTagCompound(), true))) {
            int n4 = n2 <= 0 ? stack.stackSize : Math.min(n2 - n3, stack.stackSize);
            n3 += n4;
            if (n2 != 0) {
               this.mainInventory[i].stackSize -= n4;
               if (this.mainInventory[i].stackSize == 0) {
                  this.mainInventory[i] = null;
               }

               if (n2 > 0 && n3 >= n2) {
                  return n3;
               }
            }
         }
      }

      for (int j = 0; j < this.armorInventory.length; j++) {
         ItemStack stack2 = this.armorInventory[j];
         if (stack2 != null
            && (item == null || stack2.getItem() == item)
            && (n <= -1 || stack2.getMetadata() == n)
            && (tag == null || NBTUtil.areNBTEquals(tag, stack2.getTagCompound(), false))) {
            int n5 = n2 <= 0 ? stack2.stackSize : Math.min(n2 - n3, stack2.stackSize);
            n3 += n5;
            if (n2 != 0) {
               this.armorInventory[j].stackSize -= n5;
               if (this.armorInventory[j].stackSize == 0) {
                  this.armorInventory[j] = null;
               }

               if (n2 > 0 && n3 >= n2) {
                  return n3;
               }
            }
         }
      }

      if (this.itemStack != null) {
         if (item != null && this.itemStack.getItem() != item) {
            return n3;
         }

         if (n > -1 && this.itemStack.getMetadata() != n) {
            return n3;
         }

         if (tag != null && !NBTUtil.areNBTEquals(tag, this.itemStack.getTagCompound(), false)) {
            return n3;
         }

         int n6 = n2 <= 0 ? this.itemStack.stackSize : Math.min(n2 - n3, this.itemStack.stackSize);
         n3 += n6;
         if (n2 != 0) {
            this.itemStack.stackSize -= n6;
            if (this.itemStack.stackSize == 0) {
               this.itemStack = null;
            }

            if (n2 > 0 && n3 >= n2) {
               return n3;
            }
         }
      }

      return n3;
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      ItemStack[] mainInventory2 = this.mainInventory;
      if (n >= this.mainInventory.length) {
         mainInventory2 = this.armorInventory;
         n -= this.mainInventory.length;
      }

      if (mainInventory2[n] != null) {
         if (mainInventory2[n].stackSize <= n2) {
            ItemStack stack = mainInventory2[n];
            mainInventory2[n] = null;
            return stack;
         } else {
            ItemStack stack2 = mainInventory2[n].splitStack(n2);
            if (mainInventory2[n].stackSize == 0) {
               mainInventory2[n] = null;
            }

            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public ItemStack getStackInSlot(int n) {
      ItemStack[] mainInventory2 = this.mainInventory;
      if (n >= mainInventory2.length) {
         n -= mainInventory2.length;
         mainInventory2 = this.armorInventory;
      }

      return mainInventory2[n];
   }

   @Override
   public void setField(int n, int n2) {
   }

   public int getFirstEmptyStack() {
      for (int i = 0; i < this.mainInventory.length; i++) {
         if (this.mainInventory[i] == null) {
            return i;
         }
      }

      return -1;
   }

   private int getInventorySlotContainItemAndDamage(Item item, int n) {
      for (int i = 0; i < this.mainInventory.length; i++) {
         if (this.mainInventory[i] != null && this.mainInventory[i].getItem() == item && this.mainInventory[i].getMetadata() == n) {
            return i;
         }
      }

      return -1;
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public void closeInventory(EntityPlayer player2) {
   }


   public static class ValueCallable implements Callable {
      public final ItemStack itemStack;
      public final InventoryPlayer inventory;

      public ValueCallable(InventoryPlayer inventoryPlayer, ItemStack stack) {
         this.inventory = inventoryPlayer;
         this.itemStack = stack;
      }

      public String getItemDisplayName() throws Exception {
         return this.itemStack.getDisplayName();
      }

      @Override
      public Object call() throws Exception {
         return this.getItemDisplayName();
      }
   }
}
