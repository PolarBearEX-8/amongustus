package net.optifine.util;

import java.util.List;

public class IteratorReadOnly implements IteratorReusable {
   private List list;
   private int index;
   private boolean hasNext;

   @Override
   public void setList(List list2) {
      if (this.hasNext) {
         throw new RuntimeException("Iterator still used, oldList: " + this.list + ", newList: " + list2);
      } else {
         this.list = list2;
         this.index = 0;
         this.hasNext = list2 != null && this.index < list2.size();
      }
   }

   @Override
   public Object next() {
      if (!this.hasNext) {
         return null;
      } else {
         Object obj = this.list.get(this.index);
         this.index++;
         this.hasNext = this.index < this.list.size();
         return obj;
      }
   }

   @Override
   public boolean hasNext() {
      if (!this.hasNext) {
         IteratorCache.releaseIterator(this);
         return false;
      } else {
         return this.hasNext;
      }
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException("remove");
   }
}
