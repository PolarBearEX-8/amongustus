package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;

public final class DampenerSerializer implements Predicate {
   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }

   public boolean matchesEntity(Entity entity) {
      return entity instanceof VectorPipeline && !entity.isActive12();
   }
}
