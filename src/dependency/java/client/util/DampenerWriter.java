package client.util;

import client.event.PostRenderObserver;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class DampenerWriter implements PostRenderObserver {
   public TileEntity tileEntity;

   @Override
   public int getCount2() {
      return -1;
   }

   @Override
   public int getCount() {
      return -1;
   }

   public void setTileEntity(TileEntity tileEntity2) {
      this.tileEntity = tileEntity2;
   }

   @Override
   public BlockPos getBlockPos() {
      return this.tileEntity.getBlockPos();
   }

   @Override
   public String getText() {
      return AngleCounter.resolveTextByTileEntity(this.tileEntity);
   }

   @Override
   public int getCountAsIndex() {
      return BuildManager.computeCount(this.tileEntity.getBlockPos(), this.tileEntity.getCount());
   }

   @Override
   public BiomeIDIsOutOfBoundsManager getBiomeIDIsOutOfBoundsManager() {
      return this.tileEntity.getWorld().resolveBiomeIDIsOutOfBoundsManager(this.tileEntity.getBlockPos());
   }

   public TileEntity getTileEntity() {
      return this.tileEntity;
   }
}
