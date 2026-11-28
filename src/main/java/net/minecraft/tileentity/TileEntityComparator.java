package net.minecraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityComparator extends TileEntity {
   private int outputSignal;

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      this.outputSignal = tag.getInteger("OutputSignal");
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setInteger("OutputSignal", this.outputSignal);
   }

   public int getOutputSignal() {
      return this.outputSignal;
   }

   public void setOutputSignal(int n) {
      this.outputSignal = n;
   }
}
