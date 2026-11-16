package client.util;

public class RotationMonitor {
   public int count;
   public int count2;

   public int getCount() {
      return this.count;
   }

   public RotationMonitor(int count3, int count4) {
      this.count = Math.min(count3, count4);
      this.count2 = Math.max(count3, count4);
   }

   public boolean checkIndex(int count3) {
      return count3 < this.count ? false : count3 <= this.count2;
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public String toString() {
      return "min: " + this.count + ", max: " + this.count2;
   }
}
