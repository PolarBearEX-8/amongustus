package net.minecraft.tileentity;

import java.util.Arrays;
import java.util.List;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityBrewingStand extends TileEntityLockable implements ISidedInventory, ITickable {
   private static final int[] inputSlots = new int[]{3};
   private static final int[] outputSlots = new int[]{0, 1, 2};
   private ItemStack[] brewingItemStacks = new ItemStack[4];
   private int brewTime;
   private boolean[] filledSlots;
   private Item ingredientID;
   public String customName;

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return n == 3 ? stack.getItem().isPotionIngredient(stack) : stack.getItem() == Items.potionitem || stack.getItem() == Items.glass_bottle;
   }

   private void brewPotions() {
      if (this.canBrew()) {
         ItemStack stack = this.brewingItemStacks[3];

         for (int i = 0; i < 3; i++) {
            if (this.brewingItemStacks[i] != null && this.brewingItemStacks[i].getItem() == Items.potionitem) {
               int metadata = this.brewingItemStacks[i].getMetadata();
               int potionResult = this.getPotionResult(metadata, stack);
               List effects = Items.potionitem.getEffects(metadata);
               List effects2 = Items.potionitem.getEffects(potionResult);
               if (metadata > 0 && effects == effects2 || effects != null && (effects.equals(effects2) || effects2 == null)) {
                  if (!ItemPotion.isSplash(metadata) && ItemPotion.isSplash(potionResult)) {
                     this.brewingItemStacks[i].setItemDamage(potionResult);
                  }
               } else if (metadata != potionResult) {
                  this.brewingItemStacks[i].setItemDamage(potionResult);
               }
            }
         }

         if (stack.getItem().hasContainerItem()) {
            this.brewingItemStacks[3] = new ItemStack(stack.getItem().getContainerItem());
         } else {
            this.brewingItemStacks[3].stackSize--;
            if (this.brewingItemStacks[3].stackSize <= 0) {
               this.brewingItemStacks[3] = null;
            }
         }
      }
   }

   @Override
   public int getFieldCount() {
      return 1;
   }

   @Override
   public boolean canInsertItem(int n, ItemStack stack, EnumFacing facing) {
      return this.isItemValidForSlot(n, stack);
   }

   @Override
   public void setField(int n, int n2) {
      switch (n) {
         case 0:
            this.brewTime = n2;
      }
   }

   @Override
   public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new ContainerBrewingStand(inventoryPlayer, this);
   }

   @Override
   public void update() {
      if (this.brewTime > 0) {
         this.brewTime--;
         if (this.brewTime == 0) {
            this.brewPotions();
            this.markDirty();
         } else if (!this.canBrew()) {
            this.brewTime = 0;
            this.markDirty();
         } else if (this.ingredientID != this.brewingItemStacks[3].getItem()) {
            this.brewTime = 0;
            this.markDirty();
         }
      } else if (this.canBrew()) {
         this.brewTime = 400;
         this.ingredientID = this.brewingItemStacks[3].getItem();
      }

      if (!this.worldObj.isRemote) {
         boolean[] flags = this.createFilledSlotsArray();
         if (!Arrays.equals(flags, this.filledSlots)) {
            this.filledSlots = flags;
            IBlockState blockState = this.worldObj.getBlockState(this.getPos());
            if (!(blockState.getBlock() instanceof BlockBrewingStand)) {
               return;
            }

            for (int i = 0; i < BlockBrewingStand.HAS_BOTTLE.length; i++) {
               blockState = blockState.withProperty(BlockBrewingStand.HAS_BOTTLE[i], flags[i]);
            }

            this.worldObj.setBlockState(this.pos, blockState, 2);
         }
      }
   }

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      if (n >= 0 && n < this.brewingItemStacks.length) {
         this.brewingItemStacks[n] = stack;
      }
   }

   @Override
   public String getGuiID() {
      return "minecraft:brewing_stand";
   }

   public void setName(String str) {
      this.customName = str;
   }

   @Override
   public boolean canExtractItem(int n, ItemStack stack, EnumFacing facing) {
      return true;
   }

   @Override
   public boolean hasCustomName() {
      return this.customName != null && this.customName.length() > 0;
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      if (n >= 0 && n < this.brewingItemStacks.length) {
         ItemStack stack = this.brewingItemStacks[n];
         this.brewingItemStacks[n] = null;
         return stack;
      } else {
         return null;
      }
   }

   private int getPotionResult(int n, ItemStack stack) {
      return stack == null ? n : (stack.getItem().isPotionIngredient(stack) ? PotionHelper.applyIngredient(n, stack.getItem().getPotionEffect(stack)) : n);
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.worldObj.getTileEntity(this.pos) != this
         ? false
         : player.getDistanceSq((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      if (n >= 0 && n < this.brewingItemStacks.length) {
         ItemStack stack = this.brewingItemStacks[n];
         this.brewingItemStacks[n] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public ItemStack getStackInSlot(int n) {
      return n >= 0 && n < this.brewingItemStacks.length ? this.brewingItemStacks[n] : null;
   }

   public boolean[] createFilledSlotsArray() {
      boolean[] flags = new boolean[3];

      for (int i = 0; i < 3; i++) {
         if (this.brewingItemStacks[i] != null) {
            flags[i] = true;
         }
      }

      return flags;
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setShort("BrewTime", (short)this.brewTime);
      NBTTagList tagList = new NBTTagList();

      for (int i = 0; i < this.brewingItemStacks.length; i++) {
         if (this.brewingItemStacks[i] != null) {
            NBTTagCompound tag2 = new NBTTagCompound();
            tag2.setByte("Slot", (byte)i);
            this.brewingItemStacks[i].writeToNBT(tag2);
            tagList.appendTag(tag2);
         }
      }

      tag.setTag("Items", tagList);
      if (this.hasCustomName()) {
         tag.setString("CustomName", this.customName);
      }
   }

   @Override
   public int getField(int n) {
      switch (n) {
         case 0:
            return this.brewTime;
         default:
            return 0;
      }
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      NBTTagList tagList = tag.getTagList("Items", 10);
      this.brewingItemStacks = new ItemStack[this.getSizeInventory()];

      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         byte b = compoundTagAt.getByte("Slot");
         if (b >= 0 && b < this.brewingItemStacks.length) {
            this.brewingItemStacks[b] = ItemStack.loadItemStackFromNBT(compoundTagAt);
         }
      }

      this.brewTime = tag.getShort("BrewTime");
      if (tag.hasKey("CustomName", 8)) {
         this.customName = tag.getString("CustomName");
      }
   }

   private boolean canBrew() {
      if (this.brewingItemStacks[3] != null && this.brewingItemStacks[3].stackSize > 0) {
         ItemStack stack = this.brewingItemStacks[3];
         if (!stack.getItem().isPotionIngredient(stack)) {
            return false;
         } else {
            boolean flag = false;

            for (int i = 0; i < 3; i++) {
               if (this.brewingItemStacks[i] != null && this.brewingItemStacks[i].getItem() == Items.potionitem) {
                  int metadata = this.brewingItemStacks[i].getMetadata();
                  int potionResult = this.getPotionResult(metadata, stack);
                  if (!ItemPotion.isSplash(metadata) && ItemPotion.isSplash(potionResult)) {
                     flag = true;
                     break;
                  }

                  List effects = Items.potionitem.getEffects(metadata);
                  List effects2 = Items.potionitem.getEffects(potionResult);
                  if ((metadata <= 0 || effects != effects2) && (effects == null || !effects.equals(effects2) && effects2 != null) && metadata != potionResult) {
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
   public void openInventory(EntityPlayer player) {
   }

   @Override
   public String getName() {
      return this.hasCustomName() ? this.customName : "container.brewing";
   }

   @Override
   public int[] getSlotsForFace(EnumFacing facing) {
      return facing == EnumFacing.UP ? inputSlots : outputSlots;
   }

   @Override
   public void clear() {
      for (int i = 0; i < this.brewingItemStacks.length; i++) {
         this.brewingItemStacks[i] = null;
      }
   }

   @Override
   public void closeInventory(EntityPlayer player) {
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public int getSizeInventory() {
      return this.brewingItemStacks.length;
   }
}
