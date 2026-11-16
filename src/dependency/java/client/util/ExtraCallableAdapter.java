package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class ExtraCallableAdapter implements Callable {
   public final Minecraft mc;

   public String getText() {
      return this.mc.gameSettings.state112 ? "Yes" : "No";
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public ExtraCallableAdapter(Minecraft mc2) {
      this.mc = mc2;
   }
}
