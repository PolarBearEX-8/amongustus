package net.minecraft.tileentity;

import client.util.InterpolationBridge;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityTrap extends TileEntityAdapter implements IInventory {
   public static final Random random = new Random();
   public ItemStack[] itemStackArray = new ItemStack[9];
   public String labelText;

   @Override
   public boolean processAction() {
      return this.labelText != null;
   }

   @Override
   public void processAction(EntityPlayer player) {
   }

   @Override
   public int processAction(int count) {
      return 0;
   }

   @Override
   public ItemStack getItemStack(int count, int count2) {
      if (this.itemStackArray[count] != null) {
         if (this.itemStackArray[count].animationsToGo <= count2) {
            ItemStack stack = this.itemStackArray[count];
            this.itemStackArray[count] = null;
            this.processAction5();
            return stack;
         } else {
            ItemStack stack2 = this.itemStackArray[count].resolveItemStack(count2);
            if (this.itemStackArray[count].animationsToGo == 0) {
               this.itemStackArray[count] = null;
            }

            this.processAction5();
            return stack2;
         }
      } else {
         return null;
      }
   }

   public int computeCount(ItemStack stack) {
      for (int index = 0; index < this.itemStackArray.length; index++) {
         if (this.itemStackArray[index] == null || this.itemStackArray[index].getItem() == null) {
            this.processAction(index, stack);
            return index;
         }
      }

      return -1;
   }

   @Override
   public ItemStack getStackInSlot(int count) {
      if (this.itemStackArray[count] != null) {
         ItemStack stack = this.itemStackArray[count];
         this.itemStackArray[count] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public String getText() {
      return this.processAction() ? this.labelText : "container.dispenser";
   }

   @Override
   public void processAction(int count, ItemStack stack) {
      this.itemStackArray[count] = stack;
      if (stack != null && stack.animationsToGo > this.processAction2()) {
         stack.animationsToGo = this.processAction2();
      }

      this.processAction5();
   }

   @Override
   public int processAction2() {
      return 64;
   }

   @Override
   public void updateState2() {
      for (int index = 0; index < this.itemStackArray.length; index++) {
         this.itemStackArray[index] = null;
      }
   }

   @Override
   public Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new InterpolationBridge(inventoryPlayer, this);
   }

   @Override
   public int processAction3() {
      return 0;
   }

   @Override
   public boolean processAction2(int count, ItemStack stack) {
      return true;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Items", 10);
      this.itemStackArray = new ItemStack[this.processAction4()];

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
         int count = nBTTagCompound.computeByteCode("Slot") & 255;
         if (count >= 0 && count < this.itemStackArray.length) {
            this.itemStackArray[count] = ItemStack.resolveItemStack(nBTTagCompound);
         }
      }

      if (tagCompound.matchesText("CustomName", 8)) {
         this.labelText = tagCompound.resolveText("CustomName");
      }
   }

   public int getCountAsIndex() {
      int count = -1;
      int count2 = 1;

      for (int index = 0; index < this.itemStackArray.length; index++) {
         if (this.itemStackArray[index] != null && random.nextInt(count2++) == 0) {
            count = index;
         }
      }

      return count;
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   @Override
   public int processAction4() {
      return 9;
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return this.world.resolveTileEntity(this.blockPos) != this
         ? false
         : player.getDouble3((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public String getLabelText2() {
      return "minecraft:dispenser";
   }

   @Override
   public void processAction(int count, int count2) {
   }

   @Override
   public void processAction2(EntityPlayer player) {
   }

   @Override
   public ItemStack processAction3(int count) {
      return this.itemStackArray[count];
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      NBTTagList nBTTagList = new NBTTagList();

      for (int index = 0; index < this.itemStackArray.length; index++) {
         if (this.itemStackArray[index] != null) {
            NBTTagCompound tagCompound2 = new NBTTagCompound();
            tagCompound2.put("Slot", (byte)index);
            this.itemStackArray[index].resolveNBTTagCompound(tagCompound2);
            nBTTagList.applyLISTManager(tagCompound2);
         }
      }

      tagCompound.applyText("Items", nBTTagList);
      if (this.processAction()) {
         tagCompound.put("CustomName", this.labelText);
      }
   }
}
