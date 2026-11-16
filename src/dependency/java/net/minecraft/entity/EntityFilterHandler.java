package net.minecraft.entity;

import client.util.FovAggregator;
import com.google.common.base.Predicate;

public final class EntityFilterHandler implements Predicate {
   public final int count;
   public final int count2;

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }

   public boolean matchesEntity(Entity entity) {
      int count3 = FovAggregator.computeCount((int)Math.floor((double)entity.rotationPitch));
      return this.count > this.count2 ? count3 >= this.count || count3 <= this.count2 : count3 >= this.count && count3 <= this.count2;
   }

   public EntityFilterHandler(int count3, int count4) {
      this.count = count3;
      this.count2 = count4;
   }
}
