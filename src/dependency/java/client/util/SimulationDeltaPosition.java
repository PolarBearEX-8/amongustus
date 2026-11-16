package client.util;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationDeltaPosition {
   public double value;
   public final String labelText;
   public double value2;
   public ArrayList arrayList;
   public ArrayList arrayList2;

   public double getValue2() {
      return this.value2;
   }

   public ArrayList getArrayList2() {
      return this.arrayList2;
   }

   public ArrayList getArrayList() {
      return this.arrayList;
   }

   public void setValue(double value3) {
      double offset = value3;
      if (value3 >= (double)this.arrayList.size()) {
         offset = 0.0;
      }

      this.value = offset;
   }

   public double getValue() {
      return this.value;
   }

   public SimulationDeltaPosition(ArrayList list, ArrayList list2, String text) {
      this.arrayList = list;
      this.arrayList2 = list2;
      this.labelText = text;
   }

   public double computeValueByDouble(double offset) {
      int count = (int)Math.floor(offset);
      int count2 = (int)Math.ceil(offset);
      if (count >= this.arrayList.size()) {
         count = this.arrayList.size() - 1;
      }

      if (count2 >= this.arrayList.size()) {
         count2 = this.arrayList.size() - 1;
      }

      double offset2 = offset % 1.0;
      return PlayerUtil.getDouble((Double)this.arrayList.get(count), (Double)this.arrayList.get(count2), offset2);
   }

   public void updateState() {
      this.value = ThreadLocalRandom.current().nextDouble(0.0, (double)(this.arrayList.size() - 1));
      this.value2 = ThreadLocalRandom.current().nextDouble(0.0, (double)(this.arrayList2.size() - 1));
   }

   public void setValue2(double value3) {
      double offset = value3;
      if (value3 >= (double)this.arrayList2.size()) {
         offset = 0.0;
      }

      this.value2 = offset;
   }

   public double computeValue(double offset) {
      int count = (int)Math.floor(offset);
      int count2 = (int)Math.ceil(offset);
      if (count >= this.arrayList2.size()) {
         count = this.arrayList2.size() - 1;
      }

      if (count2 >= this.arrayList2.size()) {
         count2 = this.arrayList2.size() - 1;
      }

      double offset2 = offset % 1.0;
      return PlayerUtil.getDouble((Double)this.arrayList2.get(count), (Double)this.arrayList2.get(count2), offset2);
   }

   public String getLabelText() {
      return this.labelText;
   }
}
