package client.util;

import java.util.Comparator;
import net.minecraft.entity.Entity;

public class AuxiliaryComparatorAdapter implements Comparator {
   public final Entity entity;

   public AuxiliaryComparatorAdapter(Entity entity2) {
      this.entity = entity2;
   }

   public int computeCount(Entity entity2, Entity entity3) {
      double coordinate = this.entity.getCoordinate(entity2);
      double coordinate2 = this.entity.getCoordinate(entity3);
      return coordinate < coordinate2 ? -1 : (coordinate > coordinate2 ? 1 : 0);
   }

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((Entity)entry, (Entity)entry2);
   }
}
