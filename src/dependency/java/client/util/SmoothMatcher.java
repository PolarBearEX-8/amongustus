package client.util;

public class SmoothMatcher {
   public int count = -1;
   public int[] countArray = null;

   public int[] getCountArray() {
      return this.countArray;
   }

   public int getCount() {
      return this.count;
   }

   public void applyIndex(int count2) {
      if (this.countArray != null && count2 >= 0 && count2 <= 15) {
         for (int index = 0; index < this.countArray.length; index++) {
            if (this.countArray[index] == count2) {
               return;
            }
         }

         this.countArray = BuildManager.resolveIndexArray(this.countArray, count2);
      }
   }

   public boolean checkIndex(int count2, int count3) {
      return count2 != this.count ? false : GeometrySelector.matchesIndexArray(count3, this.countArray);
   }

   public boolean matchesIBlockStateAdapter(IBlockStateAdapter iBlockStateAdapter) {
      return iBlockStateAdapter.getCount2() != this.count ? false : GeometrySelector.matchesIndexArray(iBlockStateAdapter.getCountAsIndex(), this.countArray);
   }

   public SmoothMatcher(int count2, int[] countArray2) {
      this.count = count2;
      this.countArray = countArray2;
   }

   public SmoothMatcher(int count2, int count3) {
      this.count = count2;
      if (count3 >= 0 && count3 <= 15) {
         this.countArray = new int[]{count3};
      }
   }

   public SmoothMatcher(int count2) {
      this.count = count2;
   }

   @Override
   public String toString() {
      return "" + this.count + ":" + BuildManager.resolveText(this.countArray);
   }
}
