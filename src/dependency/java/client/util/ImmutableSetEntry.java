package client.util;

import client.event.PrimaryListener;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;

public class ImmutableSetEntry extends NameManager {
   public final ImmutableSet immutableSet;
   public final Map map = Maps.newHashMap();

   public static ImmutableSetEntry resolveImmutableSetEntry(String text, Class clazz, Predicate predicate) {
      return resolveImmutableSetEntry(text, clazz, Collections2.filter(Lists.newArrayList(clazz.getEnumConstants()), predicate));
   }

   @Override
   public Collection getCollection() {
      return this.immutableSet;
   }

   public static ImmutableSetEntry resolveImmutableSetEntry(String text, Class clazz) {
      return resolveImmutableSetEntry(text, clazz, Predicates.alwaysTrue());
   }

   @Override
   public String resolveText(Comparable comparable) {
      return this.resolveText((Enum)comparable);
   }

   public static ImmutableSetEntry resolveImmutableSetEntry(String text, Class clazz, Enum[] enumArray) {
      return resolveImmutableSetEntry(text, clazz, Lists.newArrayList(enumArray));
   }

   public String resolveText(Enum enumItem) {
      return ((PrimaryListener)enumItem).getText();
   }

   public static ImmutableSetEntry resolveImmutableSetEntry(String text, Class clazz, Collection collection) {
      return new ImmutableSetEntry(text, clazz, collection);
   }

   public ImmutableSetEntry(String text, Class clazz, Collection collection) {
      super(text, clazz);
      this.immutableSet = ImmutableSet.copyOf(collection);

      for (Enum enumItem : (Iterable<Enum>)(Iterable<?>)collection) {
         String text2 = ((PrimaryListener)enumItem).getText();
         if (this.map.containsKey(text2)) {
            throw new IllegalArgumentException("Multiple values have the same name '" + text2 + "'");
         }

         this.map.put(text2, enumItem);
      }
   }
}
