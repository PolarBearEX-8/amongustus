package net.minecraft.tileentity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityRecordplayer extends TileEntity {
   public ItemStack itemStack;

   public void applyItemStack(ItemStack stack) {
      this.itemStack = stack;
      this.processAction5();
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("RecordItem", 10)) {
         this.applyItemStack(ItemStack.resolveItemStack(tagCompound.getNBTTagCompound("RecordItem")));
      } else if (tagCompound.computeCount("Record") > 0) {
         this.applyItemStack(new ItemStack(Item.processAction(tagCompound.computeCount("Record")), 1, 0));
      }
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      if (this.getItemStack() != null) {
         tagCompound.applyText("RecordItem", this.getItemStack().resolveNBTTagCompound(new NBTTagCompound()));
      }
   }
}
