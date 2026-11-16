package client.util;

import java.util.ArrayList;

public class ArrayListControllerSupport {
   public ArrayList arrayList = null;
   public int count = 0;
   public float factor = 1.0F;
   public int count2 = 0;

   public int getCount2() {
      return this.count2;
   }

   public void add(int count3, Object entry) {
      if (entry != null) {
         this.count2++;
      }

      this.arrayList.add(count3, entry);
   }

   public ArrayListControllerSupport(int count3, float factor2) {
      this.arrayList = new ArrayList(count3);
      this.count = count3;
      this.factor = factor2;
   }

   public int size() {
      return this.arrayList.size();
   }

   public ArrayListControllerSupport() {
      this(10, 0.75F);
   }

   public boolean add(Object entry) {
      if (entry != null) {
         this.count2++;
      }

      return this.arrayList.add(entry);
   }

   public Object resolveTarget(int count3, Object entry) {
      Object entry2 = this.arrayList.set(count3, entry);
      if (entry != entry2) {
         if (entry2 == null) {
            this.count2++;
         }

         if (entry == null) {
            this.count2--;
         }
      }

      return entry2;
   }

   public boolean contains(Object entry) {
      return this.arrayList.contains(entry);
   }

   public ArrayListControllerSupport(int count3) {
      this(count3, 0.75F);
   }

   public Object remove(int count3) {
      Object entry = this.arrayList.remove(count3);
      if (entry != null) {
         this.count2--;
      }

      return entry;
   }

   public void updateState() {
      if (this.count2 <= 0 && this.arrayList.size() <= 0) {
         this.clear();
      } else if (this.arrayList.size() > this.count && (float)this.count2 * 1.0F / (float)this.arrayList.size() <= this.factor) {
         int count3 = 0;

         for (int index = 0; index < this.arrayList.size(); index++) {
            Object entry = this.arrayList.get(index);
            if (entry != null) {
               if (index != count3) {
                  this.arrayList.set(count3, entry);
               }

               count3++;
            }
         }

         for (int index2 = this.arrayList.size() - 1; index2 >= count3; index2--) {
            this.arrayList.remove(index2);
         }
      }
   }

   public Object get(int count3) {
      return this.arrayList.get(count3);
   }

   public boolean isEmpty() {
      return this.arrayList.isEmpty();
   }

   public void clear() {
      this.arrayList.clear();
      this.count2 = 0;
   }
}
