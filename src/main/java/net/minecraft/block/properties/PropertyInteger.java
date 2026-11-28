package net.minecraft.block.properties;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;

public class PropertyInteger extends PropertyHelper {
   private final ImmutableSet allowedValues;

   @Override
   public Collection getAllowedValues() {
      return this.allowedValues;
   }

   @Override
   public String getName(Comparable comparable) {
      return this.getName((Integer)comparable);
   }

   @Override
   public int hashCode() {
      return 31 * super.hashCode() + this.allowedValues.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null || this.getClass() != obj.getClass()) {
         return false;
      } else if (!super.equals(obj)) {
         return false;
      } else {
         PropertyInteger propertyInteger = (PropertyInteger)obj;
         return this.allowedValues.equals(propertyInteger.allowedValues);
      }
   }

   public static PropertyInteger create(String str, int n, int n2) {
      return new PropertyInteger(str, n, n2);
   }

   public PropertyInteger(String str, int n, int n2) {
      super(str, Integer.class);
      if (n < 0) {
         throw new IllegalArgumentException("Min value of " + str + " must be 0 or greater");
      } else if (n2 <= n) {
         throw new IllegalArgumentException("Max value of " + str + " must be greater than min (" + n + ")");
      } else {
         HashSet set = Sets.newHashSet();

         for (int i = n; i <= n2; i++) {
            set.add(i);
         }

         this.allowedValues = ImmutableSet.copyOf(set);
      }
   }

   public String getName(Integer num) {
      return num.toString();
   }
}
