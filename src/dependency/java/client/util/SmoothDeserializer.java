package client.util;

import java.util.concurrent.Callable;

public class SmoothDeserializer implements Callable {
   public final IntegratedServer integratedServer;

   public SmoothDeserializer(IntegratedServer integratedServer2) {
      this.integratedServer = integratedServer2;
   }

   public String getText() throws Exception {
      return "Integrated Server (map_client.txt)";
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
