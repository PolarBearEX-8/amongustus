package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class SupplementalCallable implements Callable {
   public final Minecraft mc;

   public String getText() throws Exception {
      return "Client (map_client.txt)";
   }

   public SupplementalCallable(Minecraft mc2) {
      this.mc = mc2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
