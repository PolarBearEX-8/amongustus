package client.util;

import client.setting.ArrayListController;
import java.util.ArrayList;

public class ArrayListControllerAdapter {
   public int count;
   public int count2;
   public boolean enabled;
   public boolean enabled2;
   public int count3;
   public int count4;
   public int count5;
   public int count6;
   public int count7;
   public int count8;
   public ArrayList arrayList = new ArrayList();
   public ArrayListController arrayListController;
   public int count9;
   public int count10;
   public int count11;

   public void forEach(char character, int count12) {
      this.arrayList.forEach(item -> ArrayListControllerAdapter.applyAbstractArrayListContr(character, count12, (AbstractArrayListContr)item));
   }

   private void applyAbstractArrayListContr(AbstractArrayListContr abstractArrayListContr) {
      this.count11++;
      if (abstractArrayListContr.isEnabled4()) {
         for (AbstractArrayListContr abstractArrayListContr2 : (Iterable<AbstractArrayListContr>)(Iterable)(abstractArrayListContr.getArrayList())) {
            this.applyAbstractArrayListContr(abstractArrayListContr2);
         }
      }
   }

   public void applyIndexByIndex(int count12, int count13, int count14) {
      this.count5 = count12;
      this.applyIndex(count13, count14);
      this.updateState();
      this.count4 = 0;
   }

   public int getCount8() {
      return this.count8;
   }

   private static void applyAbstractArrayListContr(int count12, int count13, int count14, AbstractArrayListContr abstractArrayListContr) {
      abstractArrayListContr.applyIndex(count12, count13, count14);
   }

   public void applyIndex(int count12, int count13, int count14) {
      this.arrayList.forEach(item -> ArrayListControllerAdapter.applyAbstractArrayListContr(count12, count13, count14, (AbstractArrayListContr)item));
      this.updateState();
   }

   public int getCount() {
      return this.count;
   }

   private static void applyAbstractArrayListContr(char character, int count12, AbstractArrayListContr abstractArrayListContr) {
      abstractArrayListContr.applyChar(character, count12);
   }

   public ArrayList getArrayList() {
      return this.arrayList;
   }

   private static void applyAbstractArrayListContrByAbstractArrayLis(int count12, int count13, int count14, AbstractArrayListContr abstractArrayListContr) {
      abstractArrayListContr.applyIndexByIndex(count12, count13, count14);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void updateState() {
      this.count11 = 0;

      for (AbstractArrayListContr abstractArrayListContr : (Iterable<AbstractArrayListContr>)(Iterable)(this.arrayList)) {
         this.applyAbstractArrayListContr(abstractArrayListContr);
      }

      float factor = (float)(20 + (this.enabled2 ? 0 : 1));
      this.count9 = (int)Math.floor((double)((float)(this.count2 - this.count) / factor));
      this.count6 = this.count11;
      this.enabled = this.count6 > this.count9;
   }

   public void setArrayList(ArrayList arrayList2) {
      this.arrayList = arrayList2;
   }

   public void applyIndex(int count12) {
      this.count4 -= count12;
      this.count4 = this.count4 + this.count9 > this.count6 ? this.count6 - this.count9 : this.count4;
      this.count4 = this.count4 < 0 ? 0 : this.count4;
   }

   public void applyTarget(Object entry, int count12, int count13, int count14) {
      this.count3 = 0;
      float factor = (float)this.count4 / (float)this.count6;
      this.count7 = this.count + (int)((float)(this.count2 - this.count) * factor);
      float factor2 = (float)(this.count4 + this.count9) / (float)this.count6;
      this.count8 = this.count + (int)((float)(this.count2 - this.count) * factor2);
      if (this.arrayList.size() > 0) {
         this.count10 = this.count + 2;
         if (this.enabled2) {
            int count15 = this.count10 + this.arrayList.size() * 20 + 3;
            if ((float)count15 > 345.0F) {
               this.count10 = (int)((float)this.count10 - ((float)count15 - 345.0F));
               count15 = this.count10 + this.arrayList.size() * 20 + 3;
            }

            ArrayListControllerAService arrayListControllerAService = this.arrayListController.getArrayListControllerAService();
            int count16 = this.count10 - 1;
            int count17 = this.count5 + count14 + 3;
            int count18 = RecordNode20.count7;
            int count19 = count17;
            int count20 = count16;
            int count21 = this.count5;
            arrayListControllerAService.applyIndex(count21, count20, count19, count15, count18, 5);
            arrayListControllerAService = this.arrayListController.getArrayListControllerAService();
            int count22 = this.count5 + 1;
            count17 = this.count5 + count14 + 3 - 1;
            int count23 = count15 - 1;
            int count24 = RecordNode20.count3;
            int count25 = count23;
            int count26 = count17;
            int count27 = this.count10;
            int count28 = count22;
            arrayListControllerAService.applyIndex(count28, count27, count26, count25, count24, 5);
         }

         for (int index = this.count4; index < this.arrayList.size(); index++) {
            AbstractArrayListContr abstractArrayListContr = (AbstractArrayListContr)this.arrayList.get(index);
            if (this.count10 > this.count2) {
               break;
            }

            int count29 = this.count10;
            int count30 = this.count5;
            abstractArrayListContr.applyTarget(entry, count30, count29, count14, 20, count12, count13);
            this.count3 = this.count5 + count14 > this.count3 ? this.count5 + count14 : this.count3;
            this.count10 = this.count10 + 20 + (this.enabled2 ? 0 : 1);
         }
      }
   }

   public void applyIndexByIndex(int count12, int count13) {
      this.applyIndexByIndex(count12, count13, 0);
   }

   public int getCount7() {
      return this.count7;
   }

   private void applyIndex(int count12, int count13) {
      this.count = this.arrayListController.getCount3();
      this.count2 = count12;
      if (this.enabled2) {
         if (this.arrayListController.get().isEnabled()) {
            this.count = this.arrayListController.getCount3() + count13 * 20 + 2;
         } else {
            this.count = this.arrayListController.getCount3() + count13 * 54 - 2;
            this.count2 = this.count + this.arrayList.size() * 21;
         }
      }
   }

   public int getCount2() {
      return this.count2;
   }

   public int getCount4() {
      return this.count4;
   }

   public void forEach(int count12, int count13, int count14) {
      this.arrayList.forEach(item -> ArrayListControllerAdapter.applyAbstractArrayListContrByAbstractArrayLis(count12, count13, count14, (AbstractArrayListContr)item));
   }

   public ArrayListControllerAdapter(ArrayListController arrayListController2, int count12, boolean flag) {
      this.count5 = count12;
      this.arrayListController = arrayListController2;
      this.enabled2 = flag;
   }

   public int getCount3() {
      return this.count3;
   }
}
