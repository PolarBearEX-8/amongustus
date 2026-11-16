package client.util;

import java.util.Comparator;

public final class CustomComparatorAdapter implements Comparator {
   public int computeCount(ComparatorEntry comparatorEntry, ComparatorEntry comparatorEntry2) {
      return comparatorEntry.getIndex() > comparatorEntry2.getIndex() ? 1 : (comparatorEntry.getIndex() < comparatorEntry2.getIndex() ? -1 : comparatorEntry2.getLabelText().compareToIgnoreCase(comparatorEntry.getLabelText()));
   }

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((ComparatorEntry)entry, (ComparatorEntry)entry2);
   }
}
