package client.setting;

import client.util.AbstractArrayListContr;
import client.util.AbstractArrayListController;
import client.util.ArrayListControllerAService;
import client.util.ArrayListControllerAdapter;
import client.util.ArrayListControllerContext;
import client.util.InternalFloatArrayEntry;
import client.util.MoreCosmeticsManager;
import java.util.ArrayList;

public class ArrayListController {
   public boolean enabled;
   public ArrayListControllerAService arrayListControllerAService;
   public ArrayListControllerAdapter arrayListControllerAdapter;
   public ArrayListControllerAdapter arrayListControllerAdapter2;
   public int count;
   public int count2;
   public int count3;
   public boolean enabled2;
   public boolean enabled3;
   public int count4;
   public int count5;
   public static final ArrayListControllerContext arrayListControllerContext = new ArrayListControllerContext("", "");
   public static final int count6 = 9;
   public int count7;
   public static final ArrayListControllerContext arrayListControllerContext2 = new ArrayListControllerContext("", "");
   public int count8;
   public int count9;
   public AbstractArrayListController abstractArrayListController;
   public boolean enabled4;
   public int count10;
   public int count11;
   public int count12;
   public ArrayList arrayList = new ArrayList();
   public int count13;
   public int count14;
   public int count15;

   private static void applyAbstractArrayListControlle(char character, int count16, AbstractArrayListController abstractArrayListController2) {
      abstractArrayListController2.applyChar(character, count16);
   }

   public void applyIndexByIndex(int count16, int count17) {
      int count18 = (int)Math.signum((float)MoreCosmeticsManager.getMoreCosmeticsManager().getPostUpdateNotifier().getDWheel());
      if (count18 != 0) {
         if (this.enabled4 && count16 < this.getCount5()) {
            this.count7 = InternalFloatArrayEntry.computeCount(this.count7 -= count18, 0, this.arrayList.size() - this.count11);
         } else {
            if (this.enabled3) {
               this.applyIndex(count18);
            }

            if (((ArrayListControllerContext)this.arrayList.get(this.count12)).isEnabled()
               && this.arrayListControllerAdapter.isEnabled()
               && count16 < this.getCountAsIndex()) {
               this.arrayListControllerAdapter.applyIndex(count18);
            }

            if (count16 > this.getCountAsIndex() + 4 && this.enabled && this.arrayListControllerAdapter2.isEnabled()) {
               this.arrayListControllerAdapter2.applyIndex(count18);
            }
         }
      }
   }

   public int getCount4() {
      return this.count4;
   }

   public void updateState2() {
      if (this.arrayList.size() - 1 >= this.count12) {
         for (ArrayListControllerContext arrayListControllerContext3 : (Iterable<ArrayListControllerContext>)(Object)(this.arrayList)) {
            for (int index = 0; index < arrayListControllerContext3.getArrayList().size(); index++) {
               ((AbstractArrayListController)arrayListControllerContext3.getArrayList().get(index)).applyArrayListController(this, index);
            }
         }

         this.count5 = 0;
         int size = ((ArrayListControllerContext)this.arrayList.get(this.count12)).getArrayList().size();
         this.enabled3 = size > this.count9 * this.count8;
         this.count2 = Math.round((float)size / (float)this.count9);
      }
   }

   public int getCount2() {
      return 0;
   }

   public void applyChar(char character, int count16) {
      if (this.arrayList.size() > this.count12) {
         ArrayListControllerContext arrayListControllerContext3 = (ArrayListControllerContext)this.arrayList.get(this.count12);
         if (!arrayListControllerContext3.isEnabled()) {
            arrayListControllerContext3.getArrayList().forEach(item -> ArrayListController.applyAbstractArrayListControlle(character, count16, (AbstractArrayListController)item));
         }

         if (arrayListControllerContext3.isEnabled()) {
            this.arrayListControllerAdapter.forEach(character, count16);
         }

         if (this.enabled) {
            this.arrayListControllerAdapter2.forEach(character, count16);
         }
      }
   }

   public boolean checkIndex(int count16, int count17, int count18) {
      if (this.enabled && count16 < this.getCountAsIndex()) {
         AbstractArrayListController abstractArrayListController2 = null;
         boolean flag = false;
         this.applyAbstractArrayListControlle(flag, abstractArrayListController2);
      }

      this.applyIndex(count16, count17);
      if (this.arrayList.size() > this.count12) {
         ArrayListControllerContext arrayListControllerContext3 = (ArrayListControllerContext)this.arrayList.get(this.count12);
         if (!arrayListControllerContext3.isEnabled()) {
            arrayListControllerContext3.getArrayList().forEach(item -> ArrayListController.applyAbstractArrayListControlle(count16, count17, count18, (AbstractArrayListController)item));
         }

         if (this.enabled) {
            this.arrayListControllerAdapter2.applyIndex(count16, count17, count18);
         }

         if (arrayListControllerContext3.isEnabled()) {
            this.arrayListControllerAdapter.applyIndex(count16, count17, count18);
         }
      }

      return false;
   }

   public int getCount5() {
      return 93;
   }

   private static void applyAbstractArrayListControlle(int count16, int count17, int count18, AbstractArrayListController abstractArrayListController2) {
      abstractArrayListController2.applyIndex(count16, count17, count18);
   }

   public void applyIndex2(int count16, int count17) {
      this.count10 = count16;
      this.count15 = count17;
      this.count13 = this.getCountAsIndex() + 1 - this.getCount5();
      this.count14 = this.getCount15() - this.getCount3();
      this.count9 = this.count13 / 75;
      this.count8 = this.count14 / 54;
      this.count9 = this.count9 < 1 ? 1 : this.count9;
      this.count8 = this.count8 < 1 ? 1 : this.count8;
      this.updateState2();
      if (this.enabled) {
         this.count3 = this.abstractArrayListController.getCount3() / this.count9 - this.count5;
         ArrayListControllerAdapter arrayListControllerAdapter3 = this.arrayListControllerAdapter2;
         int count19 = this.getCountAsIndex() + (this.isActive() ? 5 : 0);
         int count20 = this.getCount15();
         int count21 = this.count3;
         int count22 = count20;
         int count23 = count19;
         arrayListControllerAdapter3.applyIndexByIndex(count23, count22, count21);
      }

      if (this.arrayList.size() > this.count12 && ((ArrayListControllerContext)this.arrayList.get(this.count12)).isEnabled()) {
         ArrayListControllerAdapter arrayListControllerAdapter4 = this.arrayListControllerAdapter;
         int count24 = this.getCount5();
         int count25 = this.getCount15();
         int count26 = count24;
         arrayListControllerAdapter4.applyIndexByIndex(count26, count25);
      }
   }

   private void applyIndex(int count16, int count17) {
      if (count16 >= this.getCount2() && count16 <= this.getCount5() && count17 >= this.getCount3() + 2 && count17 <= this.getCount15()) {
         int count18 = this.count7 + (count17 - this.getCount3() + 2) / 21;
         if (this.arrayList.size() > count18) {
            ArrayListControllerContext arrayListControllerContext3 = (ArrayListControllerContext)this.arrayList.get(count18);
            if (arrayListControllerContext3 == arrayListControllerContext || arrayListControllerContext3 == arrayListControllerContext2) {
               return;
            }

            if (arrayListControllerContext3.isActive()) {
               arrayListControllerContext3.run();
               return;
            }

            this.count12 = count18;
            if (arrayListControllerContext3.isEnabled()) {
               this.arrayListControllerAdapter.updateState();
               ArrayListControllerAdapter arrayListControllerAdapter3 = this.arrayListControllerAdapter;
               int count19 = this.getCount15();
               int count20 = this.getCount5();
               arrayListControllerAdapter3.applyIndexByIndex(count20, count19, 0);
            }

            this.abstractArrayListController = null;
            this.enabled = false;
         }

         this.updateState2();
      }
   }

   public ArrayList getArrayList() {
      return this.arrayList;
   }

   public void applyIndex(int count16, int count17, int count18) {
      if (this.arrayList.size() > this.count12) {
         ArrayListControllerContext arrayListControllerContext3 = (ArrayListControllerContext)this.arrayList.get(this.count12);
         if (!arrayListControllerContext3.isEnabled()) {
            arrayListControllerContext3.getArrayList().forEach(item -> ArrayListController.applyAbstractArrayListControlle(count16, count17, (AbstractArrayListController)item));
         }

         if (this.enabled) {
            this.arrayListControllerAdapter2.forEach(count16, count17, count18);
         }

         if (arrayListControllerContext3.isEnabled()) {
            this.arrayListControllerAdapter.forEach(count16, count17, count18);
         }
      }
   }

   public String getText() {
      return this.arrayList.size() > this.count12 ? ((ArrayListControllerContext)this.arrayList.get(this.count12)).getLabelText2() : "";
   }

   public int getCount12() {
      return this.count12;
   }

   public ArrayListController(int count16, int count17, ArrayListControllerAService arrayListControllerAService2) {
      this.count15 = count17;
      this.count10 = count16;
      this.arrayListControllerAService = arrayListControllerAService2;
      this.arrayListControllerAdapter = new ArrayListControllerAdapter(this, this.getCount5(), false);
      this.arrayListControllerAdapter2 = new ArrayListControllerAdapter(this, this.getCountAsIndex() + (this.enabled3 ? 5 : 0), true);
   }

   public ArrayListControllerAService getArrayListControllerAService() {
      return this.arrayListControllerAService;
   }

   public void setEnabled(boolean enabled5) {
      AbstractArrayListController abstractArrayListController2 = null;
      this.applyAbstractArrayListControlle(enabled5, abstractArrayListController2);
   }

   public void updateState() {
      if (this.arrayList.size() > this.count12) {
         ((ArrayListControllerContext)this.arrayList.get(this.count12)).isEnabled();
      }
   }

   static {
      Setting[] settingArray = new Setting[3];
   }

   public void applyIndex(int count16) {
      this.count5 -= count16;
      this.count5 = this.count5 + this.count8 > this.count2 ? this.count2 - this.count8 : this.count5;
      this.count5 = this.count5 < 0 ? 0 : this.count5;

      for (int index = 0; index < ((ArrayListControllerContext)this.arrayList.get(this.count12)).getArrayList().size(); index++) {
         AbstractArrayListController abstractArrayListController2 = (AbstractArrayListController)((ArrayListControllerContext)this.arrayList.get(this.count12))
            .getArrayList()
            .get(index);
         if (index < this.count5 * this.count9 || index > (this.count5 + this.count8) * this.count9) {
            abstractArrayListController2.setEnabled2(false);
         }
      }

      if (this.enabled) {
         if (this.abstractArrayListController.getCount3() >= this.count5 * this.count9
            && this.abstractArrayListController.getCount3() <= (this.count5 + this.count8) * this.count9) {
            this.count3 = this.abstractArrayListController.getCount3() / this.count9 - this.count5;
         } else if (this.abstractArrayListController instanceof AbstractArrayListContr) {
            this.count3 = this.abstractArrayListController.getCount3() - this.arrayListControllerAdapter.getCount4();
         }

         ArrayListControllerAdapter arrayListControllerAdapter3 = this.arrayListControllerAdapter2;
         int count17 = this.getCountAsIndex() + (this.isActive() ? 5 : 0);
         int count18 = this.count3;
         int count19 = this.count15;
         int count20 = count17;
         arrayListControllerAdapter3.applyIndexByIndex(count20, count19, count18);
      } else {
         this.enabled = false;
      }
   }

   private static void applyAbstractArrayListControlle(int count16, int count17, AbstractArrayListController abstractArrayListController2) {
      abstractArrayListController2.applyIndex(count16, count17);
   }

   public int getCount3() {
      return 22;
   }

   public ArrayListControllerContext get() {
      return (ArrayListControllerContext)this.arrayList.get(this.count12);
   }

   public boolean isActive() {
      return this.enabled3 || this.arrayListControllerAdapter.isEnabled();
   }

   public void applyAbstractArrayListControlle(boolean flag, AbstractArrayListController abstractArrayListController2) {
      if (abstractArrayListController2 != null) {
         ArrayListControllerAdapter arrayListControllerAdapter3 = this.arrayListControllerAdapter2;
         ArrayList arrayList2 = abstractArrayListController2.getArrayList();
         arrayListControllerAdapter3.setArrayList(arrayList2);
         if (abstractArrayListController2 instanceof AbstractArrayListContr) {
            this.count3 = abstractArrayListController2.getCount3() - this.arrayListControllerAdapter.getCount4();
            if (abstractArrayListController2.isEmpty()) {
               ((AbstractArrayListContr)abstractArrayListController2).withEnabled3(flag);
            }
         } else {
            this.count3 = abstractArrayListController2.getCount3() / this.count9 - this.count5;
         }

         this.abstractArrayListController = abstractArrayListController2;
      }

      this.enabled = flag;

      for (AbstractArrayListController abstractArrayListController3 : (Iterable<AbstractArrayListController>)(Object)(((ArrayListControllerContext)this.arrayList.get(this.count12)).getArrayList())) {
         boolean flag2 = !this.enabled;
         abstractArrayListController3.setEnabled(flag2);
         if (!flag && abstractArrayListController3 instanceof AbstractArrayListContr) {
            ((AbstractArrayListContr)abstractArrayListController3).withEnabled3(false);
         }
      }

      if (this.enabled) {
         if (this.abstractArrayListController != null) {
            this.abstractArrayListController.setEnabled(true);
         }

         ArrayListControllerAdapter arrayListControllerAdapter4 = this.arrayListControllerAdapter2;
         int count16 = this.getCountAsIndex() + (this.isActive() ? 5 : 0);
         int count17 = this.count3;
         int count18 = this.count15;
         int count19 = count16;
         arrayListControllerAdapter4.applyIndexByIndex(count19, count18, count17);
      }
   }

   public int getCount() {
      return this.count;
   }

   public void applyTarget(Object entry, int count16, int count17, boolean flag) {
      if (flag) {
         int size = this.arrayList.size();
         if (size != 0) {
            if (this.count12 >= size) {
               this.count12 = size - 1;
            }

            int count18 = this.getCount3() + 10;
            if (!(this.enabled4 = count18 + 21 + this.arrayList.size() * 21 > this.count15)) {
               this.count7 = 0;
            } else {
               this.count11 = 0;

               for (int index = count18 + 21 + 21; index < this.count15; this.count11++) {
                  index += 21;
               }

               this.count7 = InternalFloatArrayEntry.computeCount(this.count7, 0, this.arrayList.size() - this.count11);
            }

            for (int index2 = this.count7; index2 < this.arrayList.size(); index2++) {
               ArrayListControllerContext arrayListControllerContext3 = (ArrayListControllerContext)this.arrayList.get(index2);
               if (count18 + 21 + 21 > this.count15) {
                  break;
               }

               if (arrayListControllerContext3 != arrayListControllerContext) {
                  boolean flag2 = this.count12 == index2;
                  boolean flag3 = index2 == 0;
                  boolean flag4 = flag2;
                  this.arrayListControllerAService.applyArrayListControllerContext(count18, flag4, flag3, arrayListControllerContext3);
               }

               if (this.count12 == index2) {
                  if (arrayListControllerContext3.isEnabled()) {
                     this.arrayListControllerAdapter.applyTarget(entry, count16, count17, 219);
                  } else {
                     if (this.enabled3) {
                        float factor = (float)this.count5 / (float)this.count2;
                        this.count = (int)((float)this.count14 * factor) + this.getCount3();
                        float factor2 = (float)(this.count5 + this.count8) / (float)this.count2;
                        this.count4 = (int)((float)this.count14 * factor2) + this.getCount3();
                     }

                     int count19 = 0;
                     int count20 = this.getCount3() + 2;
                     boolean flag5 = true;

                     for (int index3 = this.count5 * this.count9; index3 < arrayListControllerContext3.getArrayList().size(); index3++) {
                        AbstractArrayListController abstractArrayListController2 = (AbstractArrayListController)arrayListControllerContext3.getArrayList().get(index3);
                        if (index3 / this.count9 > this.count5 + this.count8 - 1) {
                           break;
                        }

                        if (flag5) {
                           count19 = this.getCount5();
                           flag5 = false;
                        }

                        abstractArrayListController2.applyTarget(entry, count19, count20, 72, 51, count16, count17);
                        count19 += 75;
                        if (count19 + 32 + 40 >= this.getCountAsIndex()) {
                           count20 += 54;
                           flag5 = true;
                        }
                     }
                  }
               }

               count18 += 21;
            }

            if (this.enabled3 && !((ArrayListControllerContext)this.arrayList.get(this.count12)).isEnabled()) {
               this.arrayListControllerAService.updateState();
            }

            if ((this.enabled || ((ArrayListControllerContext)this.arrayList.get(this.count12)).isEnabled()) && this.arrayListControllerAdapter.isEnabled()) {
               ArrayListControllerAdapter arrayListControllerAdapter3 = this.arrayListControllerAdapter;
               this.arrayListControllerAService.applyArrayListControllerAdapter(arrayListControllerAdapter3);
            }

            if (this.enabled) {
               float factor3 = 180.0F;
               boolean flag6 = true;
               this.arrayListControllerAService.applyFlag(flag6, factor3);
               this.arrayListControllerAdapter2.applyTarget(entry, count16, count17, 150);
               float factor4 = 0.0F;
               boolean flag7 = false;
               this.arrayListControllerAService.applyFlag(flag7, factor4);
            }
         }
      }
   }

   public int getCountAsIndex() {
      return 318;
   }

   public int getCount15() {
      return this.count15;
   }

   private static Setting[] settingArray;

   public static Setting[] getSettingArray() {
      return null;
   }
}
