package client.util;

import java.util.List;

public class IteratorStillIteratorBridge implements IteratorBridge {
   public List list;
   public int count;
   public boolean enabled;

   @Override
   public Object next() {
      if (!this.enabled) {
         return null;
      } else {
         Object entry = this.list.get(this.count);
         this.count++;
         this.enabled = this.count < this.list.size();
         return entry;
      }
   }

   @Override
   public boolean hasNext() {
      if (!this.enabled) {
         DequeHelper.applyIteratorBridgeByIteratorBridge(this);
         return false;
      } else {
         return this.enabled;
      }
   }

   @Override
   public void applyList(List list2) {
      if (this.enabled) {
         throw new RuntimeException("Iterator still used, oldList: " + this.list + ", newList: " + list2);
      } else {
         this.list = list2;
         this.count = 0;
         this.enabled = list2 != null && this.count < list2.size();
      }
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException("remove");
   }
}
