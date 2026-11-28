package net.optifine.util;

public class LockCounter {
   private int lockCount;

   public boolean isLocked() {
      return this.lockCount > 0;
   }

   public int getLockCount() {
      return this.lockCount;
   }

   public boolean unlock() {
      if (this.lockCount <= 0) {
         return false;
      } else {
         this.lockCount--;
         return this.lockCount == 0;
      }
   }

   @Override
   public String toString() {
      return "lockCount: " + this.lockCount;
   }

   public boolean lock() {
      this.lockCount++;
      return this.lockCount == 1;
   }
}
