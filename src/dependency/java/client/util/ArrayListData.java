package client.util;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListData {
   public int count;
   public ArrayList arrayList;

   public double computeValue(ArrayListData arrayListData) {
      if (arrayListData.arrayList.size() != this.arrayList.size()) {
         return Double.NaN;
      } else {
         double doubleItem = arrayListData.getDouble7();
         double doubleItem2 = this.getDouble7();
         double offset = 0.0;

         for (int index = 0; index < this.arrayList.size(); index++) {
            double offset2 = (Double)this.arrayList.get(index) - doubleItem2;
            double offset3 = (Double)arrayListData.arrayList.get(index) - doubleItem;
            offset += offset2 * offset3;
         }

         return offset / (double)this.arrayList.size();
      }
   }

   public double getValueAsDouble() {
      if (this.arrayList.isEmpty()) {
         return Double.NaN;
      } else {
         double offset = (Double)this.arrayList.get(0);

         for (Object offset2_o : this.arrayList) {
            double offset2 = (Double)offset2_o;
            if (offset < offset2) {
               offset = offset2;
            }
         }

         return offset;
      }
   }

   public double[] getDoubleArray() {
      double offset = (double)this.arrayList.size() / 2.0 - 0.5;
      double doubleItem = this.getDouble7();
      double offset2 = 0.0;
      double offset3 = 0.0;

      for (int index = 0; index < this.arrayList.size(); index++) {
         offset2 += ((double)index - offset) * ((Double)this.arrayList.get(index) - doubleItem);
         offset3 += ((double)index - offset) * ((double)index - offset);
      }

      double offset4 = offset2 / offset3;
      return new double[]{offset4, doubleItem - offset4 * offset};
   }

   public double computeValue(int count2) {
      return this.computeValueByIndex(count2) / (double)Math.max(this.arrayList.size() - count2, 1);
   }

   public void processAction(double offset) {
      this.arrayList.add(offset);
      if (this.arrayList.size() > this.count) {
         this.arrayList.remove(0);
      }
   }

   public double getValue() {
      double doubleItem = this.getDouble7();
      double offset = 0.0;

      for (Object offset2_o : this.arrayList) {
         double offset2 = (Double)offset2_o;
         double offset3 = Math.abs(doubleItem - offset2);
         offset += offset3;
      }

      return offset / (double)this.arrayList.size();
   }

   public boolean checkDouble(double offset, double offset2) {
      for (int index = 0; index < this.arrayList.size(); index++) {
         if (Math.abs((Double)this.arrayList.get(index) - offset) <= offset2) {
            return true;
         }
      }

      return false;
   }

   public boolean checkDouble(double offset) {
      for (int index = 0; index < this.arrayList.size(); index++) {
         if ((Double)this.arrayList.get(index) == offset) {
            return true;
         }
      }

      return false;
   }

   public ArrayList getItemList() {
      return this.arrayList;
   }

   public double getValue3() {
      double offset = 0.0;
      double doubleItem = this.getDouble7();

      for (Object offset2_o : this.arrayList) {
         double offset2 = (Double)offset2_o;
         offset += Math.pow(offset2 - doubleItem, 2.0);
      }

      return Math.sqrt(offset / (double)this.arrayList.size());
   }

   public int getCountAsIndex() {
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList(this.arrayList);
      Collections.sort(list2);
      int count2 = (int)Math.ceil((double)list2.size() * 0.25);
      int count3 = (int)Math.floor((double)list2.size() * 0.75);
      double offset = (Double)list2.get(count2);
      double offset2 = (Double)list2.get(count3);
      double offset3 = offset2 - offset;
      double offset4 = offset - 1.5 * offset3;
      double offset5 = offset2 + 1.5 * offset3;

      for (Object offset6_o : list2) {
         double offset6 = (Double)offset6_o;
         if (offset6 < offset4 || offset6 > offset5) {
            list.add(offset6);
         }
      }

      return list.size();
   }

   public double getValue5() {
      double offset = 0.0;
      double offset2 = 0.0;

      for (int index = 0; index < this.arrayList.size(); index++) {
         double offset3 = (Double)this.arrayList.get(index);
         if (index > 0) {
            offset2 += Math.abs(offset - offset3);
         }

         offset = offset3;
      }

      return offset2 / (double)this.arrayList.size();
   }

   public double getValue4() {
      if (this.arrayList.isEmpty()) {
         return Double.NaN;
      } else {
         double offset = (Double)this.arrayList.get(0);

         for (Object offset2_o : this.arrayList) {
            double offset2 = (Double)offset2_o;
            if (offset > offset2) {
               offset = offset2;
            }
         }

         return offset;
      }
   }

   public boolean isActive() {
      double offset = 0.0;

      for (int index = 0; index < this.arrayList.size(); index++) {
         double offset2 = (Double)this.arrayList.get(index);
         if (index > 0 && offset != offset2) {
            return false;
         }

         offset = offset2;
      }

      return true;
   }

   public ArrayListData resolveArrayListData(int count2, int count3) {
      byte byteValue = 0;
      ArrayListData arrayListData = new ArrayListData(count3 - count2 + 1);

      for (int index = count2; index <= count3; index++) {
         double offset = (Double)this.arrayList.get(index);
         arrayListData.processAction(offset);
      }

      return arrayListData;
   }

   public double computeValueByArrayListData(ArrayListData arrayListData) {
      if (arrayListData.arrayList.size() != this.arrayList.size()) {
         return Double.NaN;
      } else {
         double value = arrayListData.getValue3();
         double value2 = this.getValue3();
         return this.computeValue(arrayListData) / (value * value2);
      }
   }

   public double getDouble7() {
      return this.getValue2() / (double)this.arrayList.size();
   }

   public double computeValueByIndex(int count2) {
      if (this.arrayList.isEmpty()) {
         return 0.0;
      } else {
         double offset = 0.0;

         for (int index = 0; index < this.arrayList.size() - count2; index++) {
            offset += (Double)this.arrayList.get(index);
         }

         return offset;
      }
   }

   public boolean size() {
      return this.arrayList.size() == this.count;
   }

   public int getCount() {
      return this.count;
   }

   public double getValue2() {
      double offset = 0.0;

      for (Object offset2_o : this.arrayList) {
         double offset2 = (Double)offset2_o;
         offset += offset2;
      }

      return offset;
   }

   public void clear() {
      this.arrayList.clear();
   }

   public ArrayListData(int count2) {
      this.count = count2;
      this.arrayList = new ArrayList(count2);
   }
}
