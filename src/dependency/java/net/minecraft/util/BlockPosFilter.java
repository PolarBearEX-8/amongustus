package net.minecraft.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;

public final class BlockPosFilter implements Predicate {
   public final BlockPos blockPos;
   public final int count;
   public final int count2;
   public final int count3;
   public final int count4;

   public BlockPosFilter(BlockPos blockPos2, int count5, int count6, int count7, int count8) {
      this.blockPos = blockPos2;
      this.count = count5;
      this.count2 = count6;
      this.count3 = count7;
      this.count4 = count8;
   }

   public boolean matchesEntity(Entity entity) {
      int coordinate = (int)entity.getCoordinate(this.blockPos);
      return (this.count < 0 || coordinate >= this.count2) && (this.count3 < 0 || coordinate <= this.count4);
   }

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }
}
