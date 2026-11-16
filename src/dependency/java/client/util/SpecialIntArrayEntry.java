package client.util;

public class SpecialIntArrayEntry {
   public int count;
   public int[] countArray;

   public boolean checkIndex(int count2, int count3) {
      return this.count != count2 ? false : this.countArray == null || BuildManager.matchesIndexArray(count3, this.countArray);
   }

   private boolean checkIndex(int count2) {
      return this.countArray == null ? false : BuildManager.matchesIndexArray(count2, this.countArray);
   }

   public int[] getCountArray() {
      return this.countArray;
   }

   public boolean checkIndexByIndex(int count2) {
      if (this.countArray == null) {
         this.countArray = new int[]{count2};
         return true;
      } else if (this.checkIndex(count2)) {
         return false;
      } else {
         this.countArray = BuildManager.resolveIndexArray(this.countArray, count2);
         return true;
      }
   }

   public int getCount() {
      return this.count;
   }

   public SpecialIntArrayEntry(int count2, int[] countArray2) {
      this.count = count2;
      this.countArray = countArray2;
   }

   public SpecialIntArrayEntry(int count2, int count3) {
      this(count2, new int[]{count3});
   }

   public SpecialIntArrayEntry(int count2) {
      this(count2, (int[])null);
   }

   @Override
   public String toString() {
      return this.countArray == null ? "" + this.count : "" + this.count + ":" + BuildManager.resolveText(this.countArray);
   }
}
