package net.minecraft.block.properties;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;
import net.minecraft.util.EnumFacing;

public class PropertyDirection extends PropertyEnum {
   public static PropertyDirection create(String str, Predicate predicate) {
      return create(str, Collections2.filter(Lists.newArrayList(EnumFacing.getValues()), predicate));
   }

   public static PropertyDirection create(String str) {
      return create(str, Predicates.alwaysTrue());
   }

   public static PropertyDirection create(String str, Collection items) {
      return new PropertyDirection(str, items);
   }

   public PropertyDirection(String str, Collection items) {
      super(str, EnumFacing.class, items);
   }
}
