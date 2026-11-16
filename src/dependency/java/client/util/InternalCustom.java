package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class InternalCustom implements Callable {
   public final Minecraft mc;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public InternalCustom(Minecraft mc2) {
      this.mc = mc2;
   }

   public String getText() throws Exception {
      return this.mc.currentScreen.getClass().getCanonicalName();
   }
}
