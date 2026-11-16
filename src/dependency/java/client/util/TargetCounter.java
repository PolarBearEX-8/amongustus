package client.util;

import java.util.concurrent.Callable;
import net.minecraft.tileentity.TileEntity;

public class TargetCounter implements Callable {
   public final TileEntity tileEntity;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public TargetCounter(TileEntity tileEntity2) {
      this.tileEntity = tileEntity2;
   }

   public String getText() throws Exception {
      IBlockState blockState = this.tileEntity.world.getBlockState(this.tileEntity.blockPos);
      int index = blockState.getBlock().getIndex(blockState);
      if (index < 0) {
         return "Unknown? (Got " + index + ")";
      } else {
         String text = String.format("%4s", Integer.toBinaryString(index)).replace(" ", "0");
         return String.format("%1$d / 0x%1$X / 0b%2$s", index, text);
      }
   }
}
