package client.util;

import client.setting.ArrayListController;
import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class AbstractArrayListController {
   public boolean enabled2;
   public int count;
   public int count2;
   public int count3;
   public String labelText;
   public ArrayList arrayList = new ArrayList();
   public ArrayListController arrayListController;
   public boolean enabled;
   public int count4;

   public void applyChar(char character, int count5) {
   }

   public int getCount4() {
      return this.count4;
   }

   public void applyIndex(int count5, int count6) {
   }

   public boolean isEmpty() {
      return !this.arrayList.isEmpty();
   }

   public AbstractArrayListController(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }

   public AbstractArrayListController(String text) {
      this.labelText = text;
   }

   public void setEnabled(boolean enabled3) {
      this.enabled = enabled3;
   }

   public void applyArrayListController(ArrayListController arrayListController2, int count5) {
      this.arrayListController = arrayListController2;
      this.count3 = count5;
   }

   public AbstractArrayListController accept(Consumer consumer) {
      consumer.accept(this.arrayList);
      return this;
   }

   public int getCount3() {
      return this.count3;
   }

   public void applyIndex(int count5, int count6, int count7) {
   }

   public void applyTarget(Object entry, int count5, int count6, int count7, int count8, int count9, int count10) {
      this.enabled2 = count9 > count5 && count9 < count5 + count7 && count10 > count6 && count10 < count6 + count8;
      this.count = count5;
      this.count2 = count6;
   }

   public void setEnabled2(boolean enabled3) {
      this.enabled2 = enabled3;
   }

   public void applyTarget(Object entry) {
   }

   public ArrayList getArrayList() {
      return this.arrayList;
   }
}
