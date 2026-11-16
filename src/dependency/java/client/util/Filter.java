package client.util;

import com.google.common.base.Predicate;

public final class Filter implements Predicate {
   public boolean apply(Object entry) {
      return this.matchesText((String)entry);
   }

   public boolean matchesText(String text) {
      return !ClientBrandHelper.isEmpty(text);
   }
}
