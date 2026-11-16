package client.util;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;
import net.minecraft.util.EnumFacing;

public class AutoWalkPath extends ImmutableSetEntry {
   public static AutoWalkPath resolveAutoWalkPath(String text, Predicate predicate) {
      return resolveAutoWalkPath(text, Collections2.filter(Lists.newArrayList(EnumFacing.getEntries()), predicate));
   }

   public static AutoWalkPath resolveAutoWalkPath(String text) {
      return resolveAutoWalkPath(text, Predicates.alwaysTrue());
   }

   public static AutoWalkPath resolveAutoWalkPath(String text, Collection collection) {
      return new AutoWalkPath(text, collection);
   }

   public AutoWalkPath(String text, Collection collection) {
      super(text, EnumFacing.class, collection);
   }
}
