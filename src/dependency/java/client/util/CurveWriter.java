package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.multiplayer.WorldClient;

public class CurveWriter implements Callable {
   public final WorldClient worldClient;

   public CurveWriter(WorldClient world) {
      this.worldClient = world;
   }

   public String getText() throws Exception {
      return WorldClient.resolveMinecraft(this.worldClient).getIntegratedServer() == null
         ? "Non-integrated multiplayer server"
         : "Integrated singleplayer server";
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
