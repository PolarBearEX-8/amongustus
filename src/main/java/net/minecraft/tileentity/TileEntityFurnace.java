package net.minecraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;

public class TileEntityFurnace extends TileEntityLockable implements ITickable, ISidedInventory {
   private static final int[] slotsTop = new int[]{0};
   private static final int[] slotsBottom = new int[]{2, 1};
   private static final int[] slotsSides = new int[]{1};
   private ItemStack[] furnaceItemStacks = new ItemStack[3];
   private int furnaceBurnTime;
   private int currentItemBurnTime;
   private int cookTime;
   private int totalCookTime;
   public String furnaceCustomName;

   @Override
   public boolean canExtractItem(int n, ItemStack stack, EnumFacing facing) {
      if (facing == EnumFacing.DOWN && n == 1) {
         Item item = stack.getItem();
         if (item != Items.water_bucket && item != Items.bucket) {
            return false;
         }
      }

      return true;
   }

   @Override
   public int getFieldCount() {
      return 4;
   }

   @Override
   public boolean hasCustomName() {
      return this.furnaceCustomName != null && this.furnaceCustomName.length() > 0;
   }

   @Override
   public int getField(int n) {
      switch (n) {
         case 0:
            return this.furnaceBurnTime;
         case 1:
            return this.currentItemBurnTime;
         case 2:
            return this.cookTime;
         case 3:
            return this.totalCookTime;
         default:
            return 0;
      }
   }

   @Override
   public int[] getSlotsForFace(EnumFacing facing) {
      return facing == EnumFacing.DOWN ? slotsBottom : (facing == EnumFacing.UP ? slotsTop : slotsSides);
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new ContainerFurnace(inventoryPlayer, this);
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      if (this.furnaceItemStacks[n] != null) {
         ItemStack stack = this.furnaceItemStacks[n];
         this.furnaceItemStacks[n] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return n == 2 ? false : (n != 1 ? true : isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack));
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.worldObj.getTileEntity(this.pos) != this
         ? false
         : player.getDistanceSq((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public void closeInventory(EntityPlayer player) {
   }

   public boolean isBurning() {
      return this.furnaceBurnTime > 0;
   }

   public static boolean isBurning(IInventory inventory) {
      return inventory.getField(0) > 0;
   }

   @Override
   public boolean canInsertItem(int n, ItemStack stack, EnumFacing facing) {
      return this.isItemValidForSlot(n, stack);
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      NBTTagList tagList = tag.getTagList("Items", 10);
      this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         byte b = compoundTagAt.getByte("Slot");
         if (b >= 0 && b < this.furnaceItemStacks.length) {
            this.furnaceItemStacks[b] = ItemStack.loadItemStackFromNBT(compoundTagAt);
         }
      }

      this.furnaceBurnTime = tag.getShort("BurnTime");
      this.cookTime = tag.getShort("CookTime");
      this.totalCookTime = tag.getShort("CookTimeTotal");
      this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
      if (tag.hasKey("CustomName", 8)) {
         this.furnaceCustomName = tag.getString("CustomName");
      }
   }

   @Override
   public void update() {
      boolean isBurning = this.isBurning();
      boolean flag = false;
      if (this.isBurning()) {
         this.furnaceBurnTime--;
      }

      if (!this.worldObj.isRemote) {
         if (this.isBurning() || this.furnaceItemStacks[1] != null && this.furnaceItemStacks[0] != null) {
            if (!this.isBurning() && this.canSmelt()) {
               this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
               if (this.isBurning()) {
                  flag = true;
                  if (this.furnaceItemStacks[1] != null) {
                     this.furnaceItemStacks[1].stackSize--;
                     if (this.furnaceItemStacks[1].stackSize == 0) {
                        Item containerItem = this.furnaceItemStacks[1].getItem().getContainerItem();
                        this.furnaceItemStacks[1] = containerItem != null ? new ItemStack(containerItem) : null;
                     }
                  }
               }
            }

            if (this.isBurning() && this.canSmelt()) {
               this.cookTime++;
               if (this.cookTime == this.totalCookTime) {
                  this.cookTime = 0;
                  this.totalCookTime = this.getCookTime(this.furnaceItemStacks[0]);
                  this.smeltItem();
                  flag = true;
               }
            } else {
               this.cookTime = 0;
            }
         } else if (!this.isBurning() && this.cookTime > 0) {
            this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
         }

         if (isBurning != this.isBurning()) {
            flag = true;
            BlockFurnace.setState(this.isBurning(), this.worldObj, this.pos);
         }
      }

      if (flag) {
         this.markDirty();
      }
   }

   @Override
   public void clear() {
      for (int i = 0; i < this.furnaceItemStacks.length; i++) {
         this.furnaceItemStacks[i] = null;
      }
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      if (this.furnaceItemStacks[n] != null) {
         if (this.furnaceItemStacks[n].stackSize <= n2) {
            ItemStack stack = this.furnaceItemStacks[n];
            this.furnaceItemStacks[n] = null;
            return stack;
         } else {
            ItemStack stack2 = this.furnaceItemStacks[n].splitStack(n2);
            if (this.furnaceItemStacks[n].stackSize == 0) {
               this.furnaceItemStacks[n] = null;
            }

            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public String getName() {
      return this.hasCustomName() ? this.furnaceCustomName : "container.furnace";
   }

   public int getCookTime(ItemStack stack) {
      return 200;
   }

   public static boolean isItemFuel(ItemStack stack) {
      return getItemBurnTime(stack) > 0;
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setShort("BurnTime", (short)this.furnaceBurnTime);
      tag.setShort("CookTime", (short)this.cookTime);
      tag.setShort("CookTimeTotal", (short)this.totalCookTime);
      NBTTagList tagList = new NBTTagList();

      for (int i = 0; i < this.furnaceItemStacks.length; i++) {
         if (this.furnaceItemStacks[i] != null) {
            NBTTagCompound tag2 = new NBTTagCompound();
            tag2.setByte("Slot", (byte)i);
            this.furnaceItemStacks[i].writeToNBT(tag2);
            tagList.appendTag(tag2);
         }
      }

      tag.setTag("Items", tagList);
      if (this.hasCustomName()) {
         tag.setString("CustomName", this.furnaceCustomName);
      }
   }

   @Override
   public int getSizeInventory() {
      return this.furnaceItemStacks.length;
   }

   private boolean canSmelt() {
      if (this.furnaceItemStacks[0] == null) {
         return false;
      } else {
         ItemStack smeltingResult = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);
         return smeltingResult == null
            ? false
            : (
               this.furnaceItemStacks[2] == null
                  ? true
                  : (
                     !this.furnaceItemStacks[2].isItemEqual(smeltingResult)
                        ? false
                        : (
                           this.furnaceItemStacks[2].stackSize < this.getInventoryStackLimit()
                                 && this.furnaceItemStacks[2].stackSize < this.furnaceItemStacks[2].getMaxStackSize()
                              ? true
                              : this.furnaceItemStacks[2].stackSize < smeltingResult.getMaxStackSize()
                        )
                  )
            );
      }
   }

   public void setCustomInventoryName(String str) {
      this.furnaceCustomName = str;
   }

   @Override
   public void openInventory(EntityPlayer player) {
   }

   public static int getItemBurnTime(ItemStack stack) {
      if (stack == null) {
         return 0;
      } else {
         Item item = stack.getItem();
         if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
            Block blockFromItem = Block.getBlockFromItem(item);
            if (blockFromItem == Blocks.wooden_slab) {
               return 150;
            }

            if (blockFromItem.getMaterial() == Material.wood) {
               return 300;
            }

            if (blockFromItem == Blocks.coal_block) {
               return 16000;
            }
         }

         return item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")
            ? 200
            : (
               item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")
                  ? 200
                  : (
                     item instanceof ItemHoe && ((ItemHoe)item).getMaterialName().equals("WOOD")
                        ? 200
                        : (
                           item == Items.stick
                              ? 100
                              : (
                                 item == Items.coal
                                    ? 1600
                                    : (
                                       item == Items.lava_bucket
                                          ? 20000
                                          : (item == Item.getItemFromBlock(Blocks.sapling) ? 100 : (item == Items.blaze_rod ? 2400 : 0))
                                    )
                              )
                        )
                  )
            );
      }
   }

   public void smeltItem() {
      if (this.canSmelt()) {
         ItemStack smeltingResult = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);
         if (this.furnaceItemStacks[2] == null) {
            this.furnaceItemStacks[2] = smeltingResult.copy();
         } else if (this.furnaceItemStacks[2].getItem() == smeltingResult.getItem()) {
            this.furnaceItemStacks[2].stackSize++;
         }

         if (this.furnaceItemStacks[0].getItem() == Item.getItemFromBlock(Blocks.sponge)
            && this.furnaceItemStacks[0].getMetadata() == 1
            && this.furnaceItemStacks[1] != null
            && this.furnaceItemStacks[1].getItem() == Items.bucket) {
            this.furnaceItemStacks[1] = new ItemStack(Items.water_bucket);
         }

         this.furnaceItemStacks[0].stackSize--;
         if (this.furnaceItemStacks[0].stackSize <= 0) {
            this.furnaceItemStacks[0] = null;
         }
      }
   }

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      boolean flag = stack != null && stack.isItemEqual(this.furnaceItemStacks[n]) && ItemStack.areItemStackTagsEqual(stack, this.furnaceItemStacks[n]);
      this.furnaceItemStacks[n] = stack;
      if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
         stack.stackSize = this.getInventoryStackLimit();
      }

      if (n == 0 && !flag) {
         this.totalCookTime = this.getCookTime(stack);
         this.cookTime = 0;
         this.markDirty();
      }
   }

   @Override
   public void setField(int n, int n2) {
      switch (n) {
         case 0:
            this.furnaceBurnTime = n2;
            break;
         case 1:
            this.currentItemBurnTime = n2;
            break;
         case 2:
            this.cookTime = n2;
            break;
         case 3:
            this.totalCookTime = n2;
      }
   }

   @Override
   public ItemStack getStackInSlot(int n) {
      return this.furnaceItemStacks[n];
   }

   @Override
   public String getGuiID() {
      return "minecraft:furnace";
   }
}
