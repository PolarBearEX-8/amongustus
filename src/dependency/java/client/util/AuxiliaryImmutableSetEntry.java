package client.util;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;

public class AuxiliaryImmutableSetEntry extends NameManager {
   public final ImmutableSet immutableSet;

   @Override
   public Collection getCollection() {
      return this.immutableSet;
   }

   @Override
   public String resolveText(Comparable comparable) {
      return this.resolveText((Integer)comparable);
   }

   public static AuxiliaryImmutableSetEntry resolveAuxiliaryImmutableSetEntry(String text, int count, int count2) {
      return new AuxiliaryImmutableSetEntry(text, count, count2);
   }

   public String resolveText(Integer count) {
      return count.toString();
   }

   public AuxiliaryImmutableSetEntry(String text, int count, int count2) {
      super(text, Integer.class);
      if (count < 0) {
         throw new IllegalArgumentException("Min value of " + text + " must be 0 or greater");
      } else if (count2 <= count) {
         throw new IllegalArgumentException("Max value of " + text + " must be greater than min (" + count + ")");
      } else {
         HashSet set = Sets.newHashSet();

         for (int index = count; index <= count2; index++) {
            set.add(index);
         }

         this.immutableSet = ImmutableSet.copyOf(set);
      }
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other == null || this.getClass() != other.getClass()) {
         return false;
      } else if (!super.equals(other)) {
         return false;
      } else {
         AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = (AuxiliaryImmutableSetEntry)other;
         return this.immutableSet.equals(auxiliaryImmutableSetEntry.immutableSet);
      }
   }

   @Override
   public int hashCode() {
      int hash = super.hashCode();
      return 31 * hash + this.immutableSet.hashCode();
   }
}
