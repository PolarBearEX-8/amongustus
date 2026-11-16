package client.util;

public class LockCountManager {
   public int count;

   public boolean isActiveAsFlag() {
      if (this.count <= 0) {
         return false;
      } else {
         this.count--;
         return this.count == 0;
      }
   }

   public boolean isActive() {
      this.count++;
      return this.count == 1;
   }

   public int getCount() {
      return this.count;
   }

   public boolean isActive2() {
      return this.count > 0;
   }

   @Override
   public String toString() {
      return "lockCount: " + this.count;
   }
}
