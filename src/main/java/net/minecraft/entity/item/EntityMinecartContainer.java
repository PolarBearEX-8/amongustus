package net.minecraft.entity.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;

public abstract class EntityMinecartContainer extends EntityMinecart implements ILockableContainer {
   private ItemStack[] minecartContainerItems = new ItemStack[36];
   private boolean dropContentsWhenDead = true;

   @Override
   public void travelToDimension(int n) {
      this.dropContentsWhenDead = false;
      super.travelToDimension(n);
   }

   @Override
   public void killMinecart(DamageSource damageSource) {
      super.killMinecart(damageSource);
      if (this.entityWorld.getGameRules().getBoolean("doEntityDrops")) {
         InventoryHelper.dropInventoryItems2(this.entityWorld, this, this);
      }
   }

   @Override
   public void setField(int n, int n2) {
   }

   @Override
   public void applyDrag() {
      int n = 15 - Container.calcRedstoneFromInventory(this);
      float f = 0.98F + (float)n * 0.001F;
      this.motionX *= (double)f;
      this.motionY *= 0.0;
      this.motionZ *= (double)f;
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public boolean isLocked() {
      return false;
   }

   public EntityMinecartContainer(World world, double d, double d2, double d3) {
      super(world, d, d2, d3);
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      if (this.minecartContainerItems[n] != null) {
         if (this.minecartContainerItems[n].stackSize <= n2) {
            ItemStack stack = this.minecartContainerItems[n];
            this.minecartContainerItems[n] = null;
            return stack;
         } else {
            ItemStack stack2 = this.minecartContainerItems[n].splitStack(n2);
            if (this.minecartContainerItems[n].stackSize == 0) {
               this.minecartContainerItems[n] = null;
            }

            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public void setDead() {
      if (this.dropContentsWhenDead) {
         InventoryHelper.dropInventoryItems2(this.entityWorld, this, this);
      }

      super.setDead();
   }

   @Override
   public String getName() {
      return this.hasCustomName() ? this.getCustomNameTag() : "container.minecart";
   }

   @Override
   public int getField(int n) {
      return 0;
   }

   @Override
   public LockCode getLockCode() {
      return LockCode.EMPTY_CODE;
   }

   @Override
   public ItemStack getStackInSlot(int n) {
      return this.minecartContainerItems[n];
   }

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return true;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      NBTTagList tagList = tag.getTagList("Items", 10);
      this.minecartContainerItems = new ItemStack[this.getSizeInventory()];

      for (int i = 0; i < tagList.tagCount(); i++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
         int n = compoundTagAt.getByte("Slot") & 255;
         if (n >= 0 && n < this.minecartContainerItems.length) {
            this.minecartContainerItems[n] = ItemStack.loadItemStackFromNBT(compoundTagAt);
         }
      }
   }

   @Override
   public void clear() {
      for (int i = 0; i < this.minecartContainerItems.length; i++) {
         this.minecartContainerItems[i] = null;
      }
   }

   @Override
   public void closeInventory(EntityPlayer player) {
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      NBTTagList tagList = new NBTTagList();

      for (int i = 0; i < this.minecartContainerItems.length; i++) {
         if (this.minecartContainerItems[i] != null) {
            NBTTagCompound tag2 = new NBTTagCompound();
            tag2.setByte("Slot", (byte)i);
            this.minecartContainerItems[i].writeToNBT(tag2);
            tagList.appendTag(tag2);
         }
      }

      tag.setTag("Items", tagList);
   }

   @Override
   public void markDirty() {
   }

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      this.minecartContainerItems[n] = stack;
      if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
         stack.stackSize = this.getInventoryStackLimit();
      }
   }

   public EntityMinecartContainer(World world) {
      super(world);
   }

   @Override
   public void openInventory(EntityPlayer player) {
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.isDead ? false : player.getDistanceSqToEntity(this) <= 64.0;
   }

   @Override
   public boolean interactFirst(EntityPlayer player) {
      if (!this.entityWorld.isRemote) {
         player.displayGUIChest(this);
      }

      return true;
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      if (this.minecartContainerItems[n] != null) {
         ItemStack stack = this.minecartContainerItems[n];
         this.minecartContainerItems[n] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public void setLockCode(LockCode lockCode) {
   }
}
