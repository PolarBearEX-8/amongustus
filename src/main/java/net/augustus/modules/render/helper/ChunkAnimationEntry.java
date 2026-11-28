package net.augustus.modules.render.helper;

import net.minecraft.util.EnumFacing;

public final class ChunkAnimationEntry {
   public long startTimeMs;
   public EnumFacing slideDirection;

   public ChunkAnimationEntry(long l, EnumFacing facing) {
      this.startTimeMs = l;
      this.slideDirection = facing;
   }
}
