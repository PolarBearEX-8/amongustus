package client.util;

import com.google.common.base.Predicate;

public class HitBoxScheduler extends ExtendedClassEntry {
   public HitBoxAdapter hitBoxAdapter;

   public HitBoxScheduler(HitBoxAdapter hitBoxAdapter2, Class clazz, boolean flag, Predicate predicate) {
      super(hitBoxAdapter2, clazz, 10, flag, false, predicate);
      this.hitBoxAdapter = hitBoxAdapter2;
   }

   @Override
   public boolean isActive() {
      return !this.hitBoxAdapter.isActive32() && super.isActive();
   }
}
