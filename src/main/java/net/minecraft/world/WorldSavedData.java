package net.minecraft.world;

import net.minecraft.nbt.NBTTagCompound;

public abstract class WorldSavedData {
   public final String mapName;
   private boolean dirty;

   public WorldSavedData(String str) {
      this.mapName = str;
   }

   public boolean isDirty() {
      return this.dirty;
   }

   public abstract void readFromNBT(NBTTagCompound tag);

   public void setDirty(boolean flag) {
      this.dirty = flag;
   }

   public void markDirty() {
      this.setDirty(true);
   }

   public abstract void writeToNBT(NBTTagCompound tag);
}
