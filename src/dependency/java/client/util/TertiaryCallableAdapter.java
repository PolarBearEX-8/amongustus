package client.util;

import java.util.concurrent.Callable;
import net.minecraft.world.World;

public class TertiaryCallableAdapter implements Callable {
   public final World world;

   public String getText() {
      return this.world.commonListener.getText();
   }

   public TertiaryCallableAdapter(World world2) {
      this.world = world2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
