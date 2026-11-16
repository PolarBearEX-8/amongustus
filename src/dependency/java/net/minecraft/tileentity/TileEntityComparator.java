package net.minecraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityComparator extends TileEntity {
   public int count2;

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.count2 = tagCompound.computeCount("OutputSignal");
   }

   public void setCount2(int count) {
      this.count2 = count;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      tagCompound.put("OutputSignal", this.count2);
   }
}
