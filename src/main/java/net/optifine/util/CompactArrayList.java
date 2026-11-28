package net.optifine.util;

import java.util.ArrayList;

public class CompactArrayList {
   private ArrayList list = null;
   private int initialCapacity = 0;
   private float loadFactor = 1.0F;
   private int countValid = 0;

   public CompactArrayList(int n, float f) {
      this.list = new ArrayList(n);
      this.initialCapacity = n;
      this.loadFactor = f;
   }

   public CompactArrayList() {
      this(10, 0.75F);
   }

   public boolean contains(Object obj) {
      return this.list.contains(obj);
   }

   public Object remove(int n) {
      Object obj = this.list.remove(n);
      if (obj != null) {
         this.countValid--;
      }

      return obj;
   }

   public Object set(int n, Object obj) {
      Object obj2 = this.list.set(n, obj);
      if (obj != obj2) {
         if (obj2 == null) {
            this.countValid++;
         }

         if (obj == null) {
            this.countValid--;
         }
      }

      return obj2;
   }

   public int size() {
      return this.list.size();
   }

   public Object get(int n) {
      return this.list.get(n);
   }

   public boolean add(Object obj) {
      if (obj != null) {
         this.countValid++;
      }

      return this.list.add(obj);
   }

   public void compact() {
      if (this.countValid <= 0 && this.list.size() <= 0) {
         this.clear();
      } else if (this.list.size() > this.initialCapacity) {
         if (((float)this.countValid * 1.0F / (float)this.list.size()) <= this.loadFactor) {
            int n = 0;

            for (int i = 0; i < this.list.size(); i++) {
               Object obj = this.list.get(i);
               if (obj != null) {
                  if (i != n) {
                     this.list.set(n, obj);
                  }

                  n++;
               }
            }

            for (int j = this.list.size() - 1; j >= n; j--) {
               this.list.remove(j);
            }
         }
      }
   }

   public void clear() {
      this.list.clear();
      this.countValid = 0;
   }

   public void add(int n, Object obj) {
      if (obj != null) {
         this.countValid++;
      }

      this.list.add(n, obj);
   }

   public CompactArrayList(int n) {
      this(n, 0.75F);
   }

   public boolean isEmpty() {
      return this.list.isEmpty();
   }

   public int getCountValid() {
      return this.countValid;
   }
}
