package net.minecraft.tileentity;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityDispenser extends TileEntityLockable implements IInventory {
   private static final Random RNG = new Random();
   private ItemStack[] stacks = new ItemStack[9];
   public String customName;

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      NBTTagList tagList = tag.getTagList("Items", 10);
      this.stacks = new ItemStack[this.getSizeInventory()];

      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         int n = compoundTagAt.getByte("Slot") & 255;
         if (n >= 0 && n < this.stacks.length) {
            this.stacks[n] = ItemStack.loadItemStackFromNBT(compoundTagAt);
         }
      }

      if (tag.hasKey("CustomName", 8)) {
         this.customName = tag.getString("CustomName");
      }
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      if (this.stacks[n] != null) {
         if (this.stacks[n].stackSize <= n2) {
            ItemStack stack = this.stacks[n];
            this.stacks[n] = null;
            this.markDirty();
            return stack;
         } else {
            ItemStack stack2 = this.stacks[n].splitStack(n2);
            if (this.stacks[n].stackSize == 0) {
               this.stacks[n] = null;
            }

            this.markDirty();
            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return true;
   }

   @Override
   public void openInventory(EntityPlayer player) {
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      if (this.stacks[n] != null) {
         ItemStack stack = this.stacks[n];
         this.stacks[n] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public void clear() {
      for (int i = 0; i < this.stacks.length; i++) {
         this.stacks[i] = null;
      }
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public String getGuiID() {
      return "minecraft:dispenser";
   }

   @Override
   public boolean hasCustomName() {
      return this.customName != null;
   }

   public void setCustomName(String str) {
      this.customName = str;
   }

   public int addItemStack(ItemStack stack) {
      for (int i = 0; i < this.stacks.length; i++) {
         if (this.stacks[i] == null || this.stacks[i].getItem() == null) {
            this.setInventorySlotContents(i, stack);
            return i;
         }
      }

      return -1;
   }

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      this.stacks[n] = stack;
      if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
         stack.stackSize = this.getInventoryStackLimit();
      }

      this.markDirty();
   }

   @Override
   public int getSizeInventory() {
      return 9;
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.worldObj.getTileEntity(this.pos) != this
         ? false
         : player.getDistanceSq((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public void setField(int n, int n2) {
   }

   @Override
   public ItemStack getStackInSlot(int n) {
      return this.stacks[n];
   }

   @Override
   public int getField(int n) {
      return 0;
   }

   @Override
   public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new ContainerDispenser(inventoryPlayer, this);
   }

   @Override
   public void closeInventory(EntityPlayer player) {
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      NBTTagList tagList = new NBTTagList();

      for (int i = 0; i < this.stacks.length; i++) {
         if (this.stacks[i] != null) {
            NBTTagCompound tag2 = new NBTTagCompound();
            tag2.setByte("Slot", (byte)i);
            this.stacks[i].writeToNBT(tag2);
            tagList.appendTag(tag2);
         }
      }

      tag.setTag("Items", tagList);
      if (this.hasCustomName()) {
         tag.setString("CustomName", this.customName);
      }
   }

   public int getDispenseSlot() {
      int n = -1;
      int n2 = 1;

      for (int i = 0; i < this.stacks.length; i++) {
         if (this.stacks[i] != null && RNG.nextInt(n2++) == 0) {
            n = i;
         }
      }

      return n;
   }

   @Override
   public String getName() {
      return this.hasCustomName() ? this.customName : "container.dispenser";
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }
}
