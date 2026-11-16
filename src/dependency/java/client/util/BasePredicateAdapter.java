package client.util;

import com.google.common.base.Predicate;
import net.minecraft.util.EnumFacing;

public final class BasePredicateAdapter implements Predicate {
   public boolean matchesEnumFacing(EnumFacing enumFacing) {
      return enumFacing != EnumFacing.UP;
   }

   public boolean apply(Object entry) {
      return this.matchesEnumFacing((EnumFacing)entry);
   }
}
