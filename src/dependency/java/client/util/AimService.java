package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class AimService implements Callable {
   public final IntegratedServer integratedServer;

   public String getText() throws Exception {
      String text = VanillaManager.getText();
      if (!text.equals("vanilla")) {
         return "Definitely; Client brand changed to '" + text + "'";
      } else {
         String textAsText = this.integratedServer.getTextAsText();
         return !textAsText.equals("vanilla")
            ? "Definitely; Server brand changed to '" + textAsText + "'"
            : (
               Minecraft.class.getSigners() == null
                  ? "Very likely; Jar signature invalidated"
                  : "Probably not. Jar signature remains and both client + server brands are untouched."
            );
      }
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public AimService(IntegratedServer integratedServer2) {
      this.integratedServer = integratedServer2;
   }
}
