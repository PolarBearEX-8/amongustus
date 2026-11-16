package client.util;

public class SupplementalDoubleEntry {
   public double value;
   public AdvancedListEntry advancedListEntry;

   public SupplementalDoubleEntry(AdvancedListEntry advancedListEntry2, double offset) {
      this.advancedListEntry = advancedListEntry2;
      this.value = offset;
   }
}
