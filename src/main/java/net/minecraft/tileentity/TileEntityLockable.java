package net.minecraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;

public abstract class TileEntityLockable extends TileEntity implements ILockableContainer, IInteractionObject {
   private LockCode code = LockCode.EMPTY_CODE;

   @Override
   public void setLockCode(LockCode lockCode) {
      this.code = lockCode;
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      this.code = LockCode.fromNBT(tag);
   }

   @Override
   public boolean isLocked() {
      return this.code != null && !this.code.isEmpty();
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
   }

   @Override
   public LockCode getLockCode() {
      return this.code;
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      if (this.code != null) {
         this.code.toNBT(tag);
      }
   }
}
