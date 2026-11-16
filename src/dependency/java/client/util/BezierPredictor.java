package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.multiplayer.WorldClient;

public class BezierPredictor implements Callable {
   public final WorldClient worldClient;

   public BezierPredictor(WorldClient world) {
      this.worldClient = world;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return WorldClient.resolveMinecraft(this.worldClient).thePlayer.getText4();
   }
}
