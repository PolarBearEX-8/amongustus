package net.minecraft.block.properties;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;

public class PropertyBool extends PropertyHelper {
   private final ImmutableSet allowedValues = ImmutableSet.of(Boolean.TRUE, Boolean.FALSE);

   @Override
   public String getName(Comparable comparable) {
      return this.getName((Boolean)comparable);
   }

   @Override
   public Collection getAllowedValues() {
      return this.allowedValues;
   }

   public String getName(Boolean bool) {
      return bool.toString();
   }

   public static PropertyBool create(String str) {
      return new PropertyBool(str);
   }

   public PropertyBool(String str) {
      super(str, Boolean.class);
   }
}
