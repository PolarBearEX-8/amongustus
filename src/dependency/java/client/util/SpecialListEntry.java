package client.util;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class SpecialListEntry {
   public final int count2;
   public final int count4;
   public final int count3;
   public final int count;
   public List list;
   public InternalComparableAdapter internalComparableAdapter;

   public boolean matchesInternalComparableAdapter(InternalComparableAdapter internalComparableAdapter2) {
      if (this.internalComparableAdapter != null) {
         return false;
      } else {
         int countAsIndex = internalComparableAdapter2.getCountAsIndex();
         int count5 = internalComparableAdapter2.getCount();
         if (countAsIndex <= this.count3 && count5 <= this.count) {
            if (countAsIndex == this.count3 && count5 == this.count) {
               this.internalComparableAdapter = internalComparableAdapter2;
               return true;
            } else {
               if (this.list == null) {
                  this.list = Lists.newArrayListWithCapacity(1);
                  this.list.add(new SpecialListEntry(this.count2, this.count4, countAsIndex, count5));
                  int count6 = this.count3 - countAsIndex;
                  int count7 = this.count - count5;
                  if (count7 > 0 && count6 > 0) {
                     int count8 = Math.max(this.count, count6);
                     int count9 = Math.max(this.count3, count7);
                     if (count8 >= count9) {
                        this.list.add(new SpecialListEntry(this.count2, this.count4 + count5, countAsIndex, count7));
                        this.list.add(new SpecialListEntry(this.count2 + countAsIndex, this.count4, count6, this.count));
                     } else {
                        this.list.add(new SpecialListEntry(this.count2 + countAsIndex, this.count4, count6, count5));
                        this.list.add(new SpecialListEntry(this.count2, this.count4 + count5, this.count3, count7));
                     }
                  } else if (count6 == 0) {
                     this.list.add(new SpecialListEntry(this.count2, this.count4 + count5, countAsIndex, count7));
                  } else if (count7 == 0) {
                     this.list.add(new SpecialListEntry(this.count2 + countAsIndex, this.count4, count6, count5));
                  }
               }

               Iterator iterator = this.list.iterator();

               while (iterator.hasNext()) {
                  if (((SpecialListEntry)iterator.next()).matchesInternalComparableAdapter(internalComparableAdapter2)) {
                     return true;
                  }
               }

               return false;
            }
         } else {
            return false;
         }
      }
   }

   public int getCount4() {
      return this.count4;
   }

   public InternalComparableAdapter getInternalComparableAdapter() {
      return this.internalComparableAdapter;
   }

   public void applyList(List list2) {
      if (this.internalComparableAdapter != null) {
         list2.add(this);
      } else if (this.list != null) {
         Iterator iterator = this.list.iterator();

         while (iterator.hasNext()) {
            ((SpecialListEntry)iterator.next()).applyList(list2);
         }
      }
   }

   public int getCount2() {
      return this.count2;
   }

   public SpecialListEntry(int count5, int count6, int count7, int count8) {
      this.count2 = count5;
      this.count4 = count6;
      this.count3 = count7;
      this.count = count8;
   }

   @Override
   public String toString() {
      return "Slot{originX="
         + this.count2
         + ", originY="
         + this.count4
         + ", width="
         + this.count3
         + ", height="
         + this.count
         + ", texture="
         + this.internalComparableAdapter
         + ", subSlots="
         + this.list
         + '}';
   }
}
