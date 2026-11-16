package net.minecraft.tileentity;

import client.event.PreUpdateConsumer;
import net.minecraft.block.BlockDaylightDetectorInverted;

public class TileEntityDldetector extends TileEntity implements PreUpdateConsumer {
   @Override
   public void updateState() {
      if (this.world != null && !this.world.state && this.world.getTimestamp2() % 20L == 0L) {
         this.block = this.getBlock();
         if (this.block instanceof BlockDaylightDetectorInverted) {
            ((BlockDaylightDetectorInverted)this.block).applyWorld2(this.world, this.blockPos);
         }
      }
   }
}
