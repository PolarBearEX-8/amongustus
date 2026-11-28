package net.augustus.utils.rotation;

import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;

public class FullBlockRaytraceFilter {
   public static final FullBlockRaytraceFilter INSTANCE = new FullBlockRaytraceFilter();
   private boolean active = false;

   public void enable() {
      this.active = true;
   }

   public boolean isBlockRaytraceable(Block block) {
      if (!this.active) {
         return true;
      } else {
         return block instanceof BlockSlab
            ? false
            : block.getBlockBoundsMinX() == 0.0
               && block.getBlockBoundsMinY() == 0.0
               && block.getBlockBoundsMinZ() == 0.0
               && block.getBlockBoundsMaxX() == 1.0
               && block.getBlockBoundsMaxY() == 1.0
               && block.getBlockBoundsMaxZ() == 1.0;
      }
   }

   private FullBlockRaytraceFilter() {
   }

   public void disable() {
      this.active = false;
   }
}
