package client.util;

import com.google.common.base.Predicate;

public class ActivePredicateAdapter implements Predicate {
   public final StandardResourceLocationEntry standardResourceLocationEntry;

   public boolean matchesComparatorEntry(ComparatorEntry comparatorEntry) {
      return comparatorEntry.getLabelText() != null && !comparatorEntry.getLabelText().startsWith("#");
   }

   public ActivePredicateAdapter(StandardResourceLocationEntry standardResourceLocationEntry2) {
      this.standardResourceLocationEntry = standardResourceLocationEntry2;
   }

   public boolean apply(Object entry) {
      return this.matchesComparatorEntry((ComparatorEntry)entry);
   }
}
