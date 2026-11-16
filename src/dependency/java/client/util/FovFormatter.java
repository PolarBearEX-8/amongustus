package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityRabbit;
import net.minecraft.entity.EntitySheep;
import net.minecraft.entity.EntityWolf;

public class FovFormatter implements Predicate {
   public final EntityWolf entityWolf;

   public FovFormatter(EntityWolf entityWolf2) {
      this.entityWolf = entityWolf2;
   }

   public boolean matchesEntity(Entity entity) {
      return entity instanceof EntitySheep || entity instanceof EntityRabbit;
   }

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }
}
