package client.event;

import client.util.Listener71Handler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public interface PreRenderListener extends Listener71Handler {
   BlockPos getBlockPos();

   TileEntity getTileEntity();

   int getCount();

   @Override
   double getValue2();

   @Override
   double getValue();

   @Override
   double getValue3();
}
