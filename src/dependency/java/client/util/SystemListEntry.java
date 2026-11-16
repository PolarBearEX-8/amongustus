package client.util;

import java.util.ArrayList;
import java.util.List;

public class SystemListEntry {
   public List list3;
   public List list;
   public List list2;

   public double getValue() {
      double offset = 0.0;

      for (AdvancedListEntry advancedListEntry : (Iterable<AdvancedListEntry>)(Iterable<?>)this.list) {
         offset += advancedListEntry.getMemberRef();
      }

      double offset2 = 0.0;

      for (AdvancedListEntry advancedListEntry2 : (Iterable<AdvancedListEntry>)(Iterable<?>)this.list3) {
         offset2 += advancedListEntry2.getMemberRef();
      }

      double offset3 = 0.0;

      for (AdvancedListEntry advancedListEntry3 : (Iterable<AdvancedListEntry>)(Iterable<?>)this.list2) {
         offset3 += advancedListEntry3.getMemberRef();
      }

      return Math.max(offset, Math.max(offset2, offset3));
   }

   public SystemListEntry(List list4, List list5, List list6) {
      this.list = list4;
      this.list3 = list5;
      this.list2 = list6;
   }

   public SystemListEntry() {
      this.list = new ArrayList();
      this.list3 = new ArrayList();
      this.list2 = new ArrayList();
   }
}
