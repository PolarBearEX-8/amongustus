package net.minecraft.world;

import net.minecraft.nbt.NBTTagCompound;

public class LockCode {
   public static final LockCode EMPTY_CODE = new LockCode("");
   public final String lock;

   public void toNBT(NBTTagCompound tagCompound) {
      tagCompound.put("Lock", this.lock);
   }

   public String getLock() {
      return this.lock;
   }

   public static LockCode fromNBT(NBTTagCompound tagCompound) {
      if (tagCompound.matchesText("Lock", 8)) {
         String text = tagCompound.resolveText("Lock");
         return new LockCode(text);
      } else {
         return EMPTY_CODE;
      }
   }

   public boolean isEmpty() {
      return this.lock == null || this.lock.isEmpty();
   }

   public LockCode(String text) {
      this.lock = text;
   }
}
