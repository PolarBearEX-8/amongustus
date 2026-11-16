package client.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class PredicateController20 extends Controller20 {
   public final Predicate predicate;

   public PredicateController20(int count, String text, boolean flag, Predicate predicate2) {
      super(count, text, flag);
      this.predicate = (Predicate)MoreObjects.firstNonNull(predicate2, Predicates.alwaysTrue());
   }

   public Predicate getPredicate() {
      return this.predicate;
   }
}
