package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public final class BezierFormatter implements Runnable {
   public final World world;
   public final BlockPos blockPos;

   public BezierFormatter(World world2, BlockPos blockPos2) {
      this.world = world2;
      this.blockPos = blockPos2;
   }

   private static void applyWorld(World world2, BlockPos blockPos2) {
      TileEntity tileEntity = world2.resolveTileEntity(blockPos2);
      if (tileEntity instanceof TileEntityBeacon) {
         ((TileEntityBeacon)tileEntity).updateState7();
         world2.applyBlockPosByBlockPos(blockPos2, Blocks.blockBeacon, 1, 0);
      }
   }

   @Override
   public void run() {
      AlertNotificationSound loggerEntry = this.world.getLoggerEntry(this.blockPos);

      for (int index = this.blockPos.getY() - 1; index >= 0; index--) {
         BlockPos blockPos2 = new BlockPos(this.blockPos.getX(), index, this.blockPos.getZ());
         if (!loggerEntry.checkIndex(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ())) {
            break;
         }

         if (this.world.getBlockState(blockPos2).getBlock() == Blocks.blockBeacon) {
            ((WorldServer)this.world).resolveListenableFuture(() -> applyWorld(this.world, blockPos2));
         }
      }
   }
}
