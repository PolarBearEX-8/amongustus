package net.minecraft.world;

import net.minecraft.nbt.NBTTagCompound;

public class LockCode {
   public static final LockCode EMPTY_CODE = new LockCode("");
   private final String lock;

   public static LockCode fromNBT(NBTTagCompound tag) {
      if (tag.hasKey("Lock", 8)) {
         String string = tag.getString("Lock");
         return new LockCode(string);
      } else {
         return EMPTY_CODE;
      }
   }

   public LockCode(String str) {
      this.lock = str;
   }

   public boolean isEmpty() {
      return this.lock == null || this.lock.isEmpty();
   }

   public String getLock() {
      return this.lock;
   }

   public void toNBT(NBTTagCompound tag) {
      tag.setString("Lock", this.lock);
   }
}
