package net.minecraft.tileentity;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockHopper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityHopper extends TileEntityLockable implements ITickable, IHopper {
   private ItemStack[] inventory = new ItemStack[5];
   public String customName;
   private int transferCooldown = -1;

   @Override
   public World getWorld() {
      return this.worldObj;
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   public static boolean captureDroppedItems(IHopper hopper) {
      IInventory hopperInventory = getHopperInventory(hopper);
      if (hopperInventory != null) {
         EnumFacing down = EnumFacing.DOWN;
         if (isInventoryEmpty(hopperInventory, down)) {
            return false;
         }

         if (hopperInventory instanceof ISidedInventory) {
            int[] slotsForFace = ((ISidedInventory)hopperInventory).getSlotsForFace(down);

            for (int i = 0; i < slotsForFace.length; i++) {
               if (pullItemFromSlot(hopper, hopperInventory, slotsForFace[i], down)) {
                  return true;
               }
            }
         } else {
            int sizeInventory = hopperInventory.getSizeInventory();

            for (int j = 0; j < sizeInventory; j++) {
               if (pullItemFromSlot(hopper, hopperInventory, j, down)) {
                  return true;
               }
            }
         }
      } else {
         for (EntityItem entityItem : (Iterable<EntityItem>)(getCaptureItems(hopper.getWorld(), hopper.getXPos(), hopper.getYPos() + 1.0, hopper.getZPos()))) {
            if (putDropInInventoryAllSlots(hopper, entityItem)) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean isEmpty() {
      for (ItemStack stack : this.inventory) {
         if (stack != null) {
            return false;
         }
      }

      return true;
   }

   @Override
   public void setField(int n, int n2) {
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      if (this.inventory[n] != null) {
         if (this.inventory[n].stackSize <= n2) {
            ItemStack stack = this.inventory[n];
            this.inventory[n] = null;
            return stack;
         } else {
            ItemStack stack2 = this.inventory[n].splitStack(n2);
            if (this.inventory[n].stackSize == 0) {
               this.inventory[n] = null;
            }

            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public int getSizeInventory() {
      return this.inventory.length;
   }

   private boolean isInventoryFull(IInventory inventory2, EnumFacing facing) {
      if (inventory2 instanceof ISidedInventory) {
         ISidedInventory sidedInventory = (ISidedInventory)inventory2;
         int[] slotsForFace = sidedInventory.getSlotsForFace(facing);

         for (int i = 0; i < slotsForFace.length; i++) {
            ItemStack stackInSlot = sidedInventory.getStackInSlot(slotsForFace[i]);
            if (stackInSlot == null || stackInSlot.stackSize != stackInSlot.getMaxStackSize()) {
               return false;
            }
         }
      } else {
         int sizeInventory = inventory2.getSizeInventory();

         for (int j = 0; j < sizeInventory; j++) {
            ItemStack stackInSlot2 = inventory2.getStackInSlot(j);
            if (stackInSlot2 == null || stackInSlot2.stackSize != stackInSlot2.getMaxStackSize()) {
               return false;
            }
         }
      }

      return true;
   }

   private static boolean canCombine(ItemStack stack, ItemStack stack2) {
      return stack.getItem() != stack2.getItem()
         ? false
         : (stack.getMetadata() != stack2.getMetadata() ? false : (stack.stackSize > stack.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(stack, stack2)));
   }

   public void setTransferCooldown(int n) {
      this.transferCooldown = n;
   }

   private static boolean canExtractItemFromSlot(IInventory inventory2, ItemStack stack, int n, EnumFacing facing) {
      return !(inventory2 instanceof ISidedInventory) || ((ISidedInventory)inventory2).canExtractItem(n, stack, facing);
   }

   public static ItemStack putStackInInventoryAllSlots(IInventory inventory2, ItemStack stack, EnumFacing facing) {
      if (inventory2 instanceof ISidedInventory && facing != null) {
         int[] slotsForFace = ((ISidedInventory)inventory2).getSlotsForFace(facing);

         for (int i = 0; i < slotsForFace.length && stack != null && stack.stackSize > 0; i++) {
            stack = insertStack(inventory2, stack, slotsForFace[i], facing);
         }
      } else {
         int sizeInventory = inventory2.getSizeInventory();

         for (int j = 0; j < sizeInventory && stack != null && stack.stackSize > 0; j++) {
            stack = insertStack(inventory2, stack, j, facing);
         }
      }

      if (stack != null && stack.stackSize == 0) {
         stack = null;
      }

      return stack;
   }

   @Override
   public ItemStack getStackInSlot(int n) {
      return this.inventory[n];
   }

   private static ItemStack insertStack(IInventory inventory2, ItemStack stack, int n, EnumFacing facing) {
      ItemStack stackInSlot = inventory2.getStackInSlot(n);
      if (canInsertItemInSlot(inventory2, stack, n, facing)) {
         boolean flag = false;
         if (stackInSlot == null) {
            inventory2.setInventorySlotContents(n, stack);
            stack = null;
            flag = true;
         } else if (canCombine(stackInSlot, stack)) {
            int n2 = stack.getMaxStackSize() - stackInSlot.stackSize;
            int n3 = Math.min(stack.stackSize, n2);
            stack.stackSize -= n3;
            stackInSlot.stackSize += n3;
            flag = n3 > 0;
         }

         if (flag) {
            if (inventory2 instanceof TileEntityHopper) {
               TileEntityHopper tileEntityHopper = (TileEntityHopper)inventory2;
               if (tileEntityHopper.mayTransfer()) {
                  tileEntityHopper.setTransferCooldown(8);
               }

               inventory2.markDirty();
            }

            inventory2.markDirty();
         }
      }

      return stack;
   }

   public void setCustomName(String str) {
      this.customName = str;
   }

   @Override
   public double getXPos() {
      return (double)this.pos.getX() + 0.5;
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      NBTTagList tagList = new NBTTagList();

      for (int i = 0; i < this.inventory.length; i++) {
         if (this.inventory[i] != null) {
            NBTTagCompound tag2 = new NBTTagCompound();
            tag2.setByte("Slot", (byte)i);
            this.inventory[i].writeToNBT(tag2);
            tagList.appendTag(tag2);
         }
      }

      tag.setTag("Items", tagList);
      tag.setInteger("TransferCooldown", this.transferCooldown);
      if (this.hasCustomName()) {
         tag.setString("CustomName", this.customName);
      }
   }

   public boolean isOnTransferCooldown() {
      return this.transferCooldown > 0;
   }

   public boolean mayTransfer() {
      return this.transferCooldown <= 1;
   }

   @Override
   public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new ContainerHopper(inventoryPlayer, this, player);
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.worldObj.getTileEntity(this.pos) != this
         ? false
         : player.getDistanceSq((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      NBTTagList tagList = tag.getTagList("Items", 10);
      this.inventory = new ItemStack[this.getSizeInventory()];
      if (tag.hasKey("CustomName", 8)) {
         this.customName = tag.getString("CustomName");
      }

      this.transferCooldown = tag.getInteger("TransferCooldown");

      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         byte b = compoundTagAt.getByte("Slot");
         if (b >= 0 && b < this.inventory.length) {
            this.inventory[b] = ItemStack.loadItemStackFromNBT(compoundTagAt);
         }
      }
   }

   @Override
   public void update() {
      if (this.worldObj != null && !this.worldObj.isRemote) {
         this.transferCooldown--;
         if (!this.isOnTransferCooldown()) {
            this.setTransferCooldown(0);
            this.updateHopper();
         }
      }
   }

   @Override
   public void openInventory(EntityPlayer player) {
   }

   @Override
   public String getGuiID() {
      return "minecraft:hopper";
   }

   private static boolean pullItemFromSlot(IHopper hopper, IInventory inventory2, int n, EnumFacing facing) {
      ItemStack stackInSlot = inventory2.getStackInSlot(n);
      if (stackInSlot != null && canExtractItemFromSlot(inventory2, stackInSlot, n, facing)) {
         ItemStack stack = stackInSlot.copy();
         ItemStack stack2 = putStackInInventoryAllSlots(hopper, inventory2.decrStackSize(n, 1), (EnumFacing)null);
         if (stack2 == null || stack2.stackSize == 0) {
            inventory2.markDirty();
            return true;
         }

         inventory2.setInventorySlotContents(n, stack);
      }

      return false;
   }

   public static IInventory getInventoryAtPosition(World world, double d, double d2, double d3) {
      Object obj = null;
      int n = MathHelper.floor_double(d);
      int n2 = MathHelper.floor_double(d2);
      int n3 = MathHelper.floor_double(d3);
      BlockPos pos2 = new BlockPos(n, n2, n3);
      Block block = world.getBlockState(pos2).getBlock();
      if (block.hasTileEntity()) {
         TileEntity tileEntity = world.getTileEntity(pos2);
         if (tileEntity instanceof IInventory) {
            obj = (IInventory)tileEntity;
            if (obj instanceof TileEntityChest && block instanceof BlockChest) {
               obj = ((BlockChest)block).getLockableContainer(world, pos2);
            }
         }
      }

      if (obj == null) {
         List list = world.getEntitiesInAABBexcluding(
            (Entity)null, new AxisAlignedBB(d - 0.5, d2 - 0.5, d3 - 0.5, d + 0.5, d2 + 0.5, d3 + 0.5), EntitySelectors.selectInventories
         );
         if (list.size() > 0) {
            obj = (IInventory)list.get(world.rand.nextInt(list.size()));
         }
      }

      return (IInventory)obj;
   }

   @Override
   public double getYPos() {
      return (double)this.pos.getY() + 0.5;
   }

   private IInventory getInventoryForHopperTransfer() {
      EnumFacing facing = BlockHopper.getFacing(this.getBlockMetadata());
      return getInventoryAtPosition(
         this.getWorld(),
         (double)(this.pos.getX() + facing.getFrontOffsetX()),
         (double)(this.pos.getY() + facing.getFrontOffsetY()),
         (double)(this.pos.getZ() + facing.getFrontOffsetZ())
      );
   }

   public static IInventory getHopperInventory(IHopper hopper) {
      return getInventoryAtPosition(hopper.getWorld(), hopper.getXPos(), hopper.getYPos() + 1.0, hopper.getZPos());
   }

   public boolean updateHopper() {
      if (this.worldObj != null && !this.worldObj.isRemote) {
         if (!this.isOnTransferCooldown() && BlockHopper.isEnabled(this.getBlockMetadata())) {
            boolean flag = false;
            if (!this.isEmpty()) {
               flag = this.transferItemsOut();
            }

            if (!this.isFull()) {
               flag = captureDroppedItems(this) || flag;
            }

            if (flag) {
               this.setTransferCooldown(8);
               this.markDirty();
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private boolean transferItemsOut() {
      IInventory inventory2 = this.getInventoryForHopperTransfer();
      if (inventory2 == null) {
         return false;
      } else {
         EnumFacing opposite = BlockHopper.getFacing(this.getBlockMetadata()).getOpposite();
         if (this.isInventoryFull(inventory2, opposite)) {
            return false;
         } else {
            for (int i = 0; i < this.getSizeInventory(); i++) {
               if (this.getStackInSlot(i) != null) {
                  ItemStack stack = this.getStackInSlot(i).copy();
                  ItemStack stack2 = putStackInInventoryAllSlots(inventory2, this.decrStackSize(i, 1), opposite);
                  if (stack2 == null || stack2.stackSize == 0) {
                     inventory2.markDirty();
                     return true;
                  }

                  this.setInventorySlotContents(i, stack);
               }
            }

            return false;
         }
      }
   }

   private static boolean canInsertItemInSlot(IInventory inventory2, ItemStack stack, int n, EnumFacing facing) {
      return !inventory2.isItemValidForSlot(n, stack) ? false : !(inventory2 instanceof ISidedInventory) || ((ISidedInventory)inventory2).canInsertItem(n, stack, facing);
   }

   private static boolean isInventoryEmpty(IInventory inventory2, EnumFacing facing) {
      if (inventory2 instanceof ISidedInventory) {
         ISidedInventory sidedInventory = (ISidedInventory)inventory2;
         int[] slotsForFace = sidedInventory.getSlotsForFace(facing);

         for (int i = 0; i < slotsForFace.length; i++) {
            if (sidedInventory.getStackInSlot(slotsForFace[i]) != null) {
               return false;
            }
         }
      } else {
         int sizeInventory = inventory2.getSizeInventory();

         for (int j = 0; j < sizeInventory; j++) {
            if (inventory2.getStackInSlot(j) != null) {
               return false;
            }
         }
      }

      return true;
   }

   @Override
   public void clear() {
      for (int i = 0; i < this.inventory.length; i++) {
         this.inventory[i] = null;
      }
   }

   @Override
   public int getField(int n) {
      return 0;
   }

   @Override
   public void closeInventory(EntityPlayer player) {
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      if (this.inventory[n] != null) {
         ItemStack stack = this.inventory[n];
         this.inventory[n] = null;
         return stack;
      } else {
         return null;
      }
   }

   public static List getCaptureItems(World world, double d, double d2, double d3) {
      return world.getEntitiesWithinAABB(
         EntityItem.class, new AxisAlignedBB(d - 0.5, d2 - 0.5, d3 - 0.5, d + 0.5, d2 + 0.5, d3 + 0.5), EntitySelectors.selectAnything
      );
   }

   @Override
   public boolean hasCustomName() {
      return this.customName != null && this.customName.length() > 0;
   }

   @Override
   public double getZPos() {
      return (double)this.pos.getZ() + 0.5;
   }

   public static boolean putDropInInventoryAllSlots(IInventory inventory2, EntityItem entityItem) {
      boolean flag = false;
      if (entityItem == null) {
         return false;
      } else {
         ItemStack stack2 = putStackInInventoryAllSlots(inventory2, entityItem.getEntityItem().copy(), (EnumFacing)null);
         if (stack2 != null && stack2.stackSize != 0) {
            entityItem.setEntityItemStack(stack2);
         } else {
            flag = true;
            entityItem.setDead();
         }

         return flag;
      }
   }

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return true;
   }

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      this.inventory[n] = stack;
      if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
         stack.stackSize = this.getInventoryStackLimit();
      }
   }

   private boolean isFull() {
      for (ItemStack stack : this.inventory) {
         if (stack == null || stack.stackSize != stack.getMaxStackSize()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public String getName() {
      return this.hasCustomName() ? this.customName : "container.hopper";
   }

   @Override
   public void markDirty() {
      super.markDirty();
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }
}
