package cc.baritone.api;

import cc.baritone.utils.InternalOptionType;
import cc.baritone.utils.SecondaryOptionType;
import java.util.function.BiFunction;

public final class InternalItemEntry {
   public static int count2;
   public final SecondaryOptionType secondaryOptionType;
   public final InternalOptionType internalOptionType;
   public final int count;

   public SecondaryOptionType getSecondaryOptionType() {
      return this.secondaryOptionType;
   }

   public static synchronized BiFunction getBiFunction() {
      int id = count2++;
      return (secondaryOption, internalOption) -> resolveInternalItemEntry(id, (SecondaryOptionType)secondaryOption, (InternalOptionType)internalOption);
   }

   private static InternalItemEntry resolveInternalItemEntry(int count3, SecondaryOptionType secondaryOptionType2, InternalOptionType internalOptionType2) {
      return new InternalItemEntry(secondaryOptionType2, internalOptionType2, count3);
   }

   public int getCount() {
      return this.count;
   }

   public InternalOptionType getInternalOptionType() {
      return this.internalOptionType;
   }

   public InternalItemEntry(SecondaryOptionType secondaryOptionType2, InternalOptionType internalOptionType2, int count3) {
      this.secondaryOptionType = secondaryOptionType2;
      this.internalOptionType = internalOptionType2;
      this.count = count3;
   }
}
