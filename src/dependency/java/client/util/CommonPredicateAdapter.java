package client.util;

import client.setting.MovementProfileProperty;
import com.google.common.base.Predicate;

public final class CommonPredicateAdapter implements Predicate {
   public boolean matchesMovementProfileProperty(MovementProfileProperty movementProfileProperty) {
      return movementProfileProperty != MovementProfileProperty.NORTH_EAST
         && movementProfileProperty != MovementProfileProperty.NORTH_WEST
         && movementProfileProperty != MovementProfileProperty.SOUTH_EAST
         && movementProfileProperty != MovementProfileProperty.SOUTH_WEST;
   }

   public boolean apply(Object entry) {
      return this.matchesMovementProfileProperty((MovementProfileProperty)entry);
   }
}
