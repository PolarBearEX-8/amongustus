package net.minecraft.entity.monster;

import net.minecraft.entity.Entity;
import com.google.common.base.Predicate;
import net.minecraft.entity.passive.IAnimals;

public interface IMob extends IAnimals {
   Predicate mobSelector = new IMob.FilterPredicate();
   Predicate VISIBLE_MOB_SELECTOR = new IMob.FilterPredicate2();


   public static final class FilterPredicate implements Predicate {
      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public boolean apply(Entity entity) {
         return entity instanceof IMob;
      }
   }

   public static final class FilterPredicate2 implements Predicate {
      public boolean apply(Entity entity) {
         return entity instanceof IMob && !entity.isInvisible();
      }

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }
   }
}
