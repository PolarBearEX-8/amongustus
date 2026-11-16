package client.util;

import java.util.concurrent.Callable;
import net.minecraft.tileentity.TileEntity;

public class TargetRanker implements Callable {
   public final TileEntity tileEntity;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return (String)TileEntity.getMap().get(this.tileEntity.getClass()) + " // " + this.tileEntity.getClass().getCanonicalName();
   }

   public TargetRanker(TileEntity tileEntity2) {
      this.tileEntity = tileEntity2;
   }
}
