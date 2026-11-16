package client.util;

import java.util.concurrent.Callable;
import net.minecraft.world.World;

public class AuxiliaryCallableAdapter implements Callable {
   public final World world;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() {
      return this.world.itemList7.size() + " total; " + this.world.itemList7;
   }

   public AuxiliaryCallableAdapter(World world2) {
      this.world = world2;
   }
}
