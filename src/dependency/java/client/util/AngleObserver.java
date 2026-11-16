package client.util;

import java.util.ArrayList;
import java.util.HashSet;

public class AngleObserver {
   public int count;
   public SmoothMatcher[] smoothMatcherArray;

   public int[] getIndexArray() {
      HashSet set = new HashSet();

      for (int index = 0; index < this.smoothMatcherArray.length; index++) {
         int count2 = this.smoothMatcherArray[index].getCount();
         set.add(count2);
      }

      return BuildManager.resolveIndexArray(((Integer[])set.toArray(new Integer[set.size()])));
   }

   public AngleObserver(int count2, SmoothMatcher[] smoothMatcherArray2) {
      this.count = count2;
      this.smoothMatcherArray = smoothMatcherArray2;
   }

   public SmoothMatcher[] resolveSmoothMatcherArray(int count2) {
      ArrayList list = new ArrayList();

      for (int index = 0; index < this.smoothMatcherArray.length; index++) {
         SmoothMatcher smoothMatcher = this.smoothMatcherArray[index];
         if (smoothMatcher.getCount() == count2) {
            list.add(smoothMatcher);
         }
      }

      return (SmoothMatcher[])list.toArray(new SmoothMatcher[list.size()]);
   }

   @Override
   public String toString() {
      return "block." + this.count + "=" + BuildManager.resolveText(this.smoothMatcherArray);
   }

   public boolean checkIndex(int count2, int count3) {
      for (int index = 0; index < this.smoothMatcherArray.length; index++) {
         if (this.smoothMatcherArray[index].checkIndex(count2, count3)) {
            return true;
         }
      }

      return false;
   }

   public int getCount() {
      return this.count;
   }
}
