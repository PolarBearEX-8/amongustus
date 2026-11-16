package net.minecraft.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;

public final class AxisAlignedBBFilter implements Predicate {
   public final AxisAlignedBB axisAlignedBB;

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }

   public AxisAlignedBBFilter(AxisAlignedBB boundingBox) {
      this.axisAlignedBB = boundingBox;
   }

   public boolean matchesEntity(Entity entity) {
      return entity.posX >= this.axisAlignedBB.minX && entity.posY >= this.axisAlignedBB.minY && entity.posZ >= this.axisAlignedBB.minZ
         ? entity.posX < this.axisAlignedBB.maxX && entity.posY < this.axisAlignedBB.maxY && entity.posZ < this.axisAlignedBB.maxZ
         : false;
   }
}
