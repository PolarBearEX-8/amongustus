package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.multiplayer.WorldClient;

public class DampenerCounter implements Callable {
   public final WorldClient worldClient;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() {
      return WorldClient.resolveSet2(this.worldClient).size() + " total; " + WorldClient.resolveSet2(this.worldClient);
   }

   public DampenerCounter(WorldClient world) {
      this.worldClient = world;
   }
}
