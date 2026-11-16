package net.minecraft.tileentity;

import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.IInventoryExtension;
import client.util.Listener63Extension;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.LockCode;

public abstract class TileEntityAdapter extends TileEntity implements Listener63Extension, IInventoryExtension {
   public LockCode lockCode = LockCode.EMPTY_CODE;

   @Override
   public IChatComponent getChatComponent() {
      return (IChatComponent)(this.processAction()
         ? new ChatComponentText(this.getText())
         : new BaseManagerStyleIChatComponentAdapter(this.getText(), new Object[0]));
   }

   @Override
   public LockCode getLockCode() {
      return this.lockCode;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.lockCode = LockCode.fromNBT(tagCompound);
   }

   @Override
   public void setLockCode(LockCode lockCode2) {
      this.lockCode = lockCode2;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      if (this.lockCode != null) {
         this.lockCode.toNBT(tagCompound);
      }
   }

   @Override
   public boolean isActive16() {
      return this.lockCode != null && !this.lockCode.isEmpty();
   }
}
