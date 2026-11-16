package client.util;

import client.setting.CombatFactorProperty;
import client.setting.CombatProfileProperty;
import com.google.common.base.Predicate;

public final class GenericPredicateAdapter implements Predicate {
   public final CombatProfileProperty combatProfileProperty;

   public boolean matchesCombatFactorProperty(CombatFactorProperty combatFactorProperty) {
      return combatFactorProperty.getCombatProfileProperty() == this.combatProfileProperty;
   }

   public GenericPredicateAdapter(CombatProfileProperty combatProfileProperty2) {
      this.combatProfileProperty = combatProfileProperty2;
   }

   public boolean apply(Object entry) {
      return this.matchesCombatFactorProperty((CombatFactorProperty)entry);
   }
}
