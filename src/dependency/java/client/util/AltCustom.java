package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.multiplayer.WorldClient;

public class AltCustom implements Callable {
   public final WorldClient worldClient;

   public String getText() {
      return WorldClient.resolveSet(this.worldClient).size() + " total; " + WorldClient.resolveSet(this.worldClient);
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public AltCustom(WorldClient world) {
      this.worldClient = world;
   }
}
