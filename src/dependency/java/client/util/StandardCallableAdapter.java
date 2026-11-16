package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class StandardCallableAdapter implements Callable {
   public final Minecraft mc;

   public StandardCallableAdapter(Minecraft mc2) {
      this.mc = mc2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return this.mc.uNKNOWNManager.enabled ? this.mc.uNKNOWNManager.getText() : "N/A (disabled)";
   }
}
