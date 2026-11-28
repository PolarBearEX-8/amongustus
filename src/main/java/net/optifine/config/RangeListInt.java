package net.optifine.config;

public class RangeListInt {
   private RangeInt[] ranges = new RangeInt[0];

   public RangeListInt(RangeInt rangeInt) {
      this.addRange(rangeInt);
   }

   public boolean isInRange(int n) {
      for (int i = 0; i < this.ranges.length; i++) {
         if (this.ranges[i].isInRange(n)) {
            return true;
         }
      }

      return false;
   }

   public RangeInt getRange(int n) {
      return this.ranges[n];
   }

   @Override
   public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append("[");

      for (int i = 0; i < this.ranges.length; i++) {
         RangeInt rangeInt = this.ranges[i];
         if (i > 0) {
            sb.append(", ");
         }

         sb.append(rangeInt.toString());
      }

      sb.append("]");
      return sb.toString();
   }

   public RangeListInt() {
   }

   public void addRange(RangeInt rangeInt) {
      this.ranges = (RangeInt[])Config.addObjectToArray(this.ranges, rangeInt);
   }

   public int getRanges() {
      return this.ranges.length;
   }
}
