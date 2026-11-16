package client.util;

public final class SecondaryComparableAdapter implements Comparable {
   public double value;
   public double value2;
   public String labelText;

   @Override
   public int compareTo(Object entry) {
      return this.computeCount((SecondaryComparableAdapter)entry);
   }

   public int computeCount(SecondaryComparableAdapter secondaryComparableAdapter) {
      return secondaryComparableAdapter.value < this.value ? -1 : (secondaryComparableAdapter.value > this.value ? 1 : secondaryComparableAdapter.labelText.compareTo(this.labelText));
   }

   public SecondaryComparableAdapter(String text, double offset, double offset2) {
      this.labelText = text;
      this.value = offset;
      this.value2 = offset2;
   }

   public int getCount() {
      return (this.labelText.hashCode() & 11184810) + 4473924;
   }
}
