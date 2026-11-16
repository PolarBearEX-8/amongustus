package client.util;

import net.minecraft.nbt.NBTTagCompound;

public class StringEntryDispatcher extends AdapterStringEntry {
   public NBTTagCompound nBTTagCompound = new NBTTagCompound();

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.applyText("Features", this.nBTTagCompound);
   }

   public NBTTagCompound getNBTTagCompound() {
      return this.nBTTagCompound;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      this.nBTTagCompound = tagCompound.getNBTTagCompound("Features");
   }

   public static String resolveText(int count, int count2) {
      return "[" + count + "," + count2 + "]";
   }

   public StringEntryDispatcher(String text) {
      super(text);
   }

   public void applyNBTTagCompound(NBTTagCompound tagCompound, int count, int count2) {
      this.nBTTagCompound.applyText(resolveText(count, count2), tagCompound);
   }
}
