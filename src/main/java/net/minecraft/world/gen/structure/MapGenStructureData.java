package net.minecraft.world.gen.structure;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class MapGenStructureData extends WorldSavedData {
   private NBTTagCompound tagCompound = new NBTTagCompound();

   public static String formatChunkCoords(int n, int n2) {
      return "[" + n + "," + n2 + "]";
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      tag.setTag("Features", this.tagCompound);
   }

   public MapGenStructureData(String str) {
      super(str);
   }

   public NBTTagCompound getTagCompound() {
      return this.tagCompound;
   }

   public void writeInstance(NBTTagCompound tag, int n, int n2) {
      this.tagCompound.setTag(formatChunkCoords(n, n2), tag);
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      this.tagCompound = tag.getCompoundTag("Features");
   }
}
