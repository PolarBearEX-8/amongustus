package net.minecraft.block.properties;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import net.minecraft.util.IStringSerializable;

public class PropertyEnum extends PropertyHelper {
   private final ImmutableSet allowedValues;
   private final Map nameToValue = Maps.newHashMap();

   public static PropertyEnum create(String str, Class clazz, Predicate predicate) {
      return create(str, clazz, Collections2.filter(Lists.newArrayList(clazz.getEnumConstants()), predicate));
   }

   @Override
   public Collection getAllowedValues() {
      return this.allowedValues;
   }

   public PropertyEnum(String str, Class clazz, Collection items) {
      super(str, clazz);
      this.allowedValues = ImmutableSet.copyOf(items);

      for (Enum obj : (Iterable<Enum>)(items)) {
         String name2 = ((IStringSerializable)obj).getName();
         if (this.nameToValue.containsKey(name2)) {
            throw new IllegalArgumentException("Multiple values have the same name '" + name2 + "'");
         }

         this.nameToValue.put(name2, obj);
      }
   }

   public static PropertyEnum create(String str, Class clazz, Collection items) {
      return new PropertyEnum(str, clazz, items);
   }

   @Override
   public String getName(Comparable comparable) {
      return this.getName((Enum)comparable);
   }

   public static PropertyEnum create(String str, Class clazz, Enum[] array) {
      return create(str, clazz, Lists.newArrayList(array));
   }

   public static PropertyEnum create(String str, Class clazz) {
      return create(str, clazz, Predicates.alwaysTrue());
   }

   public String getName(Enum obj) {
      return ((IStringSerializable)obj).getName();
   }
}
