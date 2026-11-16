package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class AdvancedCallableAdapter implements Callable {
   public final Minecraft mc;

   public AdvancedCallableAdapter(Minecraft mc2) {
      this.mc = mc2;
   }

   public String getText() throws Exception {
      return Minecraft.resolveTextByMinecraft(this.mc);
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
