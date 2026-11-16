package net.minecraft.entity;

import com.google.common.collect.ComparisonChain;
import java.util.Comparator;
import net.minecraft.util.BlockPos;

public final class BlockPosComparator implements Comparator {
   public final BlockPos blockPos;

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((Entity)entry, (Entity)entry2);
   }

   public BlockPosComparator(BlockPos blockPos2) {
      this.blockPos = blockPos2;
   }

   public int computeCount(Entity entity, Entity entity2) {
      return ComparisonChain.start().compare(entity.computeValue(this.blockPos), entity2.computeValue(this.blockPos)).result();
   }
}
