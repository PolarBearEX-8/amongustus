package client.util;

import client.setting.MovementProperty;
import com.google.common.base.Predicate;

public final class SharedPredicateAdapter implements Predicate {
   public boolean matchesMovementProperty(MovementProperty movementProperty) {
      return movementProperty.getCount() < 4;
   }

   public boolean apply(Object entry) {
      return this.matchesMovementProperty((MovementProperty)entry);
   }
}
