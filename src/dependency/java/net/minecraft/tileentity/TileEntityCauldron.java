package net.minecraft.tileentity;

import client.event.PreUpdateConsumer;
import client.util.AuxiliaryIInventoryExtension;
import client.util.BezierReader;
import client.util.IBlockState;
import client.util.InternalContainerAdapter;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;

public class TileEntityCauldron extends TileEntityAdapter implements AuxiliaryIInventoryExtension, PreUpdateConsumer {
   public static final int[] countArray = new int[]{3};
   public static final int[] countArray2 = new int[]{0, 1, 2};
   public ItemStack[] itemStackArray = new ItemStack[4];
   public int count2;
   public boolean[] enabledArray;
   public Item item;
   public String labelText;

   @Override
   public int processAction(int count) {
      switch (count) {
         case 0:
            return this.count2;
         default:
            return 0;
      }
   }

   @Override
   public ItemStack getItemStack(int count, int count3) {
      if (count >= 0 && count < this.itemStackArray.length) {
         ItemStack stack = this.itemStackArray[count];
         this.itemStackArray[count] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public void processAction(EntityPlayer player) {
   }

   public boolean[] getFlagArray() {
      boolean[] flagArray = new boolean[3];

      for (int index = 0; index < 3; index++) {
         if (this.itemStackArray[index] != null) {
            flagArray[index] = true;
         }
      }

      return flagArray;
   }

   @Override
   public boolean processAction() {
      return this.labelText != null && this.labelText.length() > 0;
   }

   private void updateState6() {
      if (this.isActive2()) {
         ItemStack stack = this.itemStackArray[3];

         for (int index = 0; index < 3; index++) {
            if (this.itemStackArray[index] != null && this.itemStackArray[index].getItem() == Items.potion) {
               int indexOffset = this.itemStackArray[index].getIndexOffset();
               int count = this.computeCount(indexOffset, stack);
               List list = Items.potion.resolveList(indexOffset);
               List list2 = Items.potion.resolveList(count);
               if (indexOffset > 0 && list == list2 || list != null && (list.equals(list2) || list2 == null)) {
                  if (!ItemPotion.checkCondition(indexOffset) && ItemPotion.checkCondition(count)) {
                     this.itemStackArray[index].applyIndexByIndex(count);
                  }
               } else if (indexOffset != count) {
                  this.itemStackArray[index].applyIndexByIndex(count);
               }
            }
         }

         if (stack.getItem().isActiveAsFlag()) {
            this.itemStackArray[3] = new ItemStack(stack.getItem().getItem());
         } else {
            this.itemStackArray[3].animationsToGo--;
            if (this.itemStackArray[3].animationsToGo <= 0) {
               this.itemStackArray[3] = null;
            }
         }
      }
   }

   @Override
   public ItemStack getStackInSlot(int count) {
      if (count >= 0 && count < this.itemStackArray.length) {
         ItemStack stack = this.itemStackArray[count];
         this.itemStackArray[count] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public String getText() {
      return this.processAction() ? this.labelText : "container.brewing";
   }

   @Override
   public void processAction(int count, ItemStack stack) {
      if (count >= 0 && count < this.itemStackArray.length) {
         this.itemStackArray[count] = stack;
      }
   }

   private int computeCount(int count, ItemStack stack) {
      return stack == null
         ? count
         : (stack.getItem().matchesItemStackByItemStack(stack) ? BezierReader.computeCount(count, stack.getItem().processAction3(stack)) : count);
   }

   @Override
   public int processAction2() {
      return 64;
   }

   @Override
   public boolean processAction2(int count, ItemStack stack) {
      return count == 3 ? stack.getItem().matchesItemStackByItemStack(stack) : stack.getItem() == Items.potion || stack.getItem() == Items.glassBottle;
   }

   @Override
   public void updateState2() {
      for (int index = 0; index < this.itemStackArray.length; index++) {
         this.itemStackArray[index] = null;
      }
   }

   @Override
   public boolean matchesItemStackByItemStack(int count, ItemStack stack, EnumFacing enumFacing) {
      return true;
   }

   private boolean isActive2() {
      if (this.itemStackArray[3] != null && this.itemStackArray[3].animationsToGo > 0) {
         ItemStack stack = this.itemStackArray[3];
         if (!stack.getItem().matchesItemStackByItemStack(stack)) {
            return false;
         } else {
            boolean flag = false;

            for (int index = 0; index < 3; index++) {
               if (this.itemStackArray[index] != null && this.itemStackArray[index].getItem() == Items.potion) {
                  int indexOffset = this.itemStackArray[index].getIndexOffset();
                  int count = this.computeCount(indexOffset, stack);
                  if (!ItemPotion.checkCondition(indexOffset) && ItemPotion.checkCondition(count)) {
                     flag = true;
                     break;
                  }

                  List list = Items.potion.resolveList(indexOffset);
                  List list2 = Items.potion.resolveList(count);
                  if ((indexOffset <= 0 || list != list2) && (list == null || !list.equals(list2) && list2 != null) && indexOffset != count) {
                     flag = true;
                     break;
                  }
               }
            }

            return flag;
         }
      } else {
         return false;
      }
   }

   @Override
   public int processAction3() {
      return 1;
   }

   @Override
   public Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new InternalContainerAdapter(inventoryPlayer, this);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Items", 10);
      this.itemStackArray = new ItemStack[this.processAction4()];

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
         byte byteValue = nBTTagCompound.computeByteCode("Slot");
         if (byteValue >= 0 && byteValue < this.itemStackArray.length) {
            this.itemStackArray[byteValue] = ItemStack.resolveItemStack(nBTTagCompound);
         }
      }

      this.count2 = tagCompound.processValue("BrewTime");
      if (tagCompound.matchesText("CustomName", 8)) {
         this.labelText = tagCompound.resolveText("CustomName");
      }
   }

   @Override
   public int[] resolveIndexArray(EnumFacing enumFacing) {
      return enumFacing == EnumFacing.UP ? countArray : countArray2;
   }

   @Override
   public void updateState() {
      if (this.count2 > 0) {
         this.count2--;
         if (this.count2 == 0) {
            this.updateState6();
            this.processAction5();
         } else if (!this.isActive2()) {
            this.count2 = 0;
            this.processAction5();
         } else if (this.item != this.itemStackArray[3].getItem()) {
            this.count2 = 0;
            this.processAction5();
         }
      } else if (this.isActive2()) {
         this.count2 = 400;
         this.item = this.itemStackArray[3].getItem();
      }

      if (!this.world.state) {
         boolean[] flagArray = this.getFlagArray();
         if (!Arrays.equals(flagArray, this.enabledArray)) {
            this.enabledArray = flagArray;
            IBlockState blockState = this.world.getBlockState(this.getBlockPos());
            if (!(blockState.getBlock() instanceof BlockBrewingStand)) {
               return;
            }

            for (int index = 0; index < BlockBrewingStand.internalImmutableSetEntryArray.length; index++) {
               blockState = blockState.resolveIBlockState(BlockBrewingStand.internalImmutableSetEntryArray[index], flagArray[index]);
            }

            this.world.matchesBlockPos(this.blockPos, blockState, 2);
         }
      }
   }

   @Override
   public boolean matchesItemStack(int count, ItemStack stack, EnumFacing enumFacing) {
      return this.processAction2(count, stack);
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return this.world.resolveTileEntity(this.blockPos) != this
         ? false
         : player.getDouble3((double)this.blockPos.getX() + 0.5, (double)this.blockPos.getY() + 0.5, (double)this.blockPos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public int processAction4() {
      return this.itemStackArray.length;
   }

   @Override
   public void processAction(int count, int count3) {
      switch (count) {
         case 0:
            this.count2 = count3;
      }
   }

   @Override
   public void processAction2(EntityPlayer player) {
   }

   @Override
   public String getLabelText2() {
      return "minecraft:brewing_stand";
   }

   @Override
   public ItemStack processAction3(int count) {
      return count >= 0 && count < this.itemStackArray.length ? this.itemStackArray[count] : null;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      tagCompound.put("BrewTime", (short)this.count2);
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
