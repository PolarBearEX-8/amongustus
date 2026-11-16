package client.util;

import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class SpecialCallableAdapter implements Callable {
   public final Block block;
   public final World world;

   public SpecialCallableAdapter(World world2, Block block2) {
      this.world = world2;
      this.block = block2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() {
      try {
         return String.format("ID #%d (%s // %s)", Block.getIdFromBlock(this.block), this.block.getTextAsText(), this.block.getClass().getCanonicalName());
      } catch (Throwable throwable) {
         return "ID #" + Block.getIdFromBlock(this.block);
      }
   }
}
