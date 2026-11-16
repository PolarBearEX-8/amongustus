package client.util;

public class TickManager {
   public final Double memberRef2;
   public final Double memberRef4;
   public final AdvancedListEntry advancedListEntry;
   public final Double memberRef3;
   public final Double memberRef;

   public TickManager(AdvancedListEntry advancedListEntry2, double offset, double offset2, float factor, double offset3) {
      this.advancedListEntry = advancedListEntry2;
      this.memberRef2 = offset;
      this.memberRef = offset2;
      this.memberRef4 = (double)factor;
      this.memberRef3 = offset3;
   }

   public TickManager(AdvancedListEntry advancedListEntry2, Double offset, Double offset2, Double offset3, Double offset4) {
      this.advancedListEntry = advancedListEntry2;
      this.memberRef2 = offset;
      this.memberRef = offset2;
      this.memberRef4 = offset3;
      this.memberRef3 = offset4;
   }

   @Override
   public String toString() {
      return "Tick: " + this.memberRef2 + " | End Tick: " + this.memberRef + " | Start Value: " + this.memberRef4 + " | End Value: " + this.memberRef3;
   }
}
