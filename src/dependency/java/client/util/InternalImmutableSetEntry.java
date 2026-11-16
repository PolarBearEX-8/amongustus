package client.util;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;

public class InternalImmutableSetEntry extends NameManager {
   public final ImmutableSet immutableSet = ImmutableSet.of(Boolean.TRUE, Boolean.FALSE);

   public String resolveText(Boolean flag) {
      return flag.toString();
   }

   @Override
   public Collection getCollection() {
      return this.immutableSet;
   }

   public static InternalImmutableSetEntry resolveInternalImmutableSetEntry(String text) {
      return new InternalImmutableSetEntry(text);
   }

   @Override
   public String resolveText(Comparable comparable) {
      return this.resolveText((Boolean)comparable);
   }

   public InternalImmutableSetEntry(String text) {
      super(text, Boolean.class);
   }
}
