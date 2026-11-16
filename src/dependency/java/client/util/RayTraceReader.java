package client.util;

import com.google.common.base.Predicate;
import net.minecraft.block.BlockPoweredComparator;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;

public class RayTraceReader implements Predicate {
   public final EnumFacing enumFacing;
   public final BlockPoweredComparator blockPoweredComparator;

   public RayTraceReader(BlockPoweredComparator blockPoweredComparator2, EnumFacing enumFacing2) {
      this.blockPoweredComparator = blockPoweredComparator2;
      this.enumFacing = enumFacing2;
   }

   public boolean matchesEntity(Entity entity) {
      return entity != null && entity.getEnumFacing2() == this.enumFacing;
   }

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }
}
