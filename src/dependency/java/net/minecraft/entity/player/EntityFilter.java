package net.minecraft.entity.player;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;

public final class EntityFilter implements Predicate {
   public final int count;
   public final int count2;

   public boolean matchesEntity(Entity entity) {
      if (!(entity instanceof EntityPlayerAdapter)) {
         return false;
      } else {
         EntityPlayerAdapter entityPlayerAdapter = (EntityPlayerAdapter)entity;
         return (this.count <= -1 || entityPlayerAdapter.count25 >= this.count) && (this.count2 <= -1 || entityPlayerAdapter.count25 <= this.count2);
      }
   }

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }

   public EntityFilter(int count3, int count4) {
      this.count = count3;
      this.count2 = count4;
   }
}
