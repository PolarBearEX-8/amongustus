package client.util;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;

public abstract class IInventoryExtensionAdapter extends EntityMinecart implements IInventoryExtension {
   public ItemStack[] itemStackArray = new ItemStack[36];
   public boolean enabled18 = true;

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
            return stack;
         } else {
            ItemStack stack2 = this.itemStackArray[count].resolveItemStack(count2);
            if (this.itemStackArray[count].animationsToGo == 0) {
               this.itemStackArray[count] = null;
            }

            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      if (!this.worldObj.state) {
         player.applyIInventory(this);
      }

      return true;
   }

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      super.applyDamageSource(damageSource);
      if (this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
         DampenerFilter.applyWorld(this.worldObj, this, this);
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("Items", 10);
      this.itemStackArray = new ItemStack[this.processAction4()];

      for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
         NBTTagCompound nBTTagCompound = extendedLoggerEntry.getNBTTagCompound(index);
         int count = nBTTagCompound.computeByteCode("Slot") & 255;
         if (count >= 0 && count < this.itemStackArray.length) {
            this.itemStackArray[count] = ItemStack.resolveItemStack(nBTTagCompound);
         }
      }
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
   public void processAction(int count, ItemStack stack) {
      this.itemStackArray[count] = stack;
      if (stack != null && stack.animationsToGo > this.processAction2()) {
         stack.animationsToGo = this.processAction2();
      }
   }

   @Override
   public String getText() {
      return this.processAction() ? this.getText2() : "container.minecart";
   }

   @Override
   public int processAction2() {
      return 64;
   }

   @Override
   public int processAction3() {
      return 0;
   }

   @Override
   public void updateState2() {
      for (int index = 0; index < this.itemStackArray.length; index++) {
         this.itemStackArray[index] = null;
      }
   }

   @Override
   public boolean processAction2(int count, ItemStack stack) {
      return true;
   }

   @Override
   public LockCode getLockCode() {
      return LockCode.EMPTY_CODE;
   }

   @Override
   public void applyIndex2(int count) {
      this.enabled18 = false;
      super.applyIndex2(count);
   }

   @Override
   public void setLockCode(LockCode lockCode) {
   }

   @Override
   public void processAction2(EntityPlayer player) {
   }

   @Override
   public void processAction(int count, int count2) {
   }

   @Override
   public boolean checkEntityPlayer(EntityPlayer player) {
      return this.isDead ? false : player.getCoordinate(this) <= 64.0;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
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
   }

   @Override
   public boolean isActive16() {
      return false;
   }

   @Override
   public ItemStack processAction3(int count) {
      return this.itemStackArray[count];
   }

   @Override
   public void processAction5() {
   }

   @Override
   public void updateState3() {
      int count = 15 - Container.computeCount(this);
      float factor = 0.98F + (float)count * 0.001F;
      this.motionX *= (double)factor;
      this.motionY *= 0.0;
      this.motionZ *= (double)factor;
   }

   @Override
   public void updateState9() {
      if (this.enabled18) {
         DampenerFilter.applyWorld(this.worldObj, this, this);
      }

      super.updateState9();
   }

   public IInventoryExtensionAdapter(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
   }

   public IInventoryExtensionAdapter(World world) {
      super(world);
   }
}
