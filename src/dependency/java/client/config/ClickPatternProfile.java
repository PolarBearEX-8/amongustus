package client.config;

import java.util.ArrayList;

public class ClickPatternProfile {
   public final String labelText;
   public int count2;
   public final Integer[] integerArray;
   public int count;
   public ArrayList arrayList;

   public void updateState3() {
      this.count = 0;
   }

   public void updateState() {
      this.count2++;
   }

   public static int[] getIndexArray() {
      return null;
   }

   public void updateState2() {
      getIndexArray();
      if (this.count % 2 != 0) {
         this.count--;
      }
   }

   public float[] getFloatArray() {
      getIndexArray();
      if (this.arrayList.isEmpty()) {
         return new float[2];
      } else {
         int count3 = this.count2;
         if (count3 >= this.arrayList.size()) {
            count3 = this.arrayList.size() - 1;
         }

         return (float[])this.arrayList.get(count3);
      }
   }

   public int getInt() {
      getIndexArray();
      if (this.count >= this.integerArray.length) {
         this.count = 0;
         this.count2 = 0;
      }

      int count3 = this.integerArray[this.count];
      this.count++;
      return count3;
   }

   public ClickPatternProfile(String text, Integer[] countArray, ArrayList list) {
      getIndexArray();
      this.arrayList = new ArrayList();
      this.count = 0;
      this.count2 = 0;
      this.integerArray = countArray;
      this.labelText = text;
      this.arrayList = list;
   }
}
