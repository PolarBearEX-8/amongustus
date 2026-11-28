package baritone.api.event.events;

import net.minecraft.util.BlockPos;

public final class BlockInteractEvent {
   private final BlockPos pos;
   private final BlockInteractEvent.Type type;

   public final BlockPos getPos() {
      return this.pos;
   }

   public BlockInteractEvent(BlockPos pos2, BlockInteractEvent.Type type2) {
      this.pos = pos2;
      this.type = type2;
   }

   public final BlockInteractEvent.Type getType() {
      return this.type;
   }


   public enum Type {
      START_BREAK,
      USE;
   }
}
