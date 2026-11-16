package client.util;

import com.google.common.base.Predicate;

public final class DynamicPredicateAdapter implements Predicate {
   public final Predicate predicate;

   public boolean matchesSecondaryWorldEntry(SecondaryWorldEntry secondaryWorldEntry) {
      return secondaryWorldEntry != null && this.predicate.apply(secondaryWorldEntry.getIBlockState());
   }

   public DynamicPredicateAdapter(Predicate predicate2) {
      this.predicate = predicate2;
   }

   public boolean apply(Object entry) {
      return this.matchesSecondaryWorldEntry((SecondaryWorldEntry)entry);
   }
}
