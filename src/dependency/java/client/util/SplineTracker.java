package client.util;

import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class SplineTracker implements Callable {
   public final TileEntity tileEntity;

   public SplineTracker(TileEntity tileEntity2) {
      this.tileEntity = tileEntity2;
   }

   public String getText() throws Exception {
      int idFromBlock = Block.getIdFromBlock(this.tileEntity.world.getBlockState(this.tileEntity.blockPos).getBlock());

      try {
         return String.format("ID #%d (%s // %s)", idFromBlock, Block.processAction3(idFromBlock).getTextAsText(), Block.processAction3(idFromBlock).getClass().getCanonicalName());
      } catch (Throwable throwable) {
         return "ID #" + idFromBlock;
      }
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
