package client.util;

import net.minecraft.nbt.NBTTagCompound;

public abstract class AdapterStringEntry {
   public final String labelText;
   public boolean enabled;

   public void updateState() {
      this.setEnabled(true);
   }

   public abstract void applyNBTTagCompound(NBTTagCompound tagCompound);

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public abstract void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound);

   public boolean isEnabled() {
      return this.enabled;
   }

   public AdapterStringEntry(String text) {
      this.labelText = text;
   }
}
