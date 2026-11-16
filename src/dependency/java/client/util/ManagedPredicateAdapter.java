package client.util;

import client.setting.CombatFactorProperty;
import com.google.common.base.Predicate;

public class ManagedPredicateAdapter implements Predicate {
   public final ImmutableSetEntry2Entry immutableSetEntry2Entry;

   public boolean matchesCombatFactorProperty(CombatFactorProperty combatFactorProperty) {
      return combatFactorProperty.getCombatProfileProperty() == this.immutableSetEntry2Entry.getYELLOW();
   }

   public ManagedPredicateAdapter(ImmutableSetEntry2Entry immutableSetEntry2Entry2) {
      this.immutableSetEntry2Entry = immutableSetEntry2Entry2;
   }

   public boolean apply(Object entry) {
      return this.matchesCombatFactorProperty((CombatFactorProperty)entry);
   }
}
