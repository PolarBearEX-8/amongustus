package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;

public class CustomCallableAdapter implements Callable {
   public final Minecraft mc;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public CustomCallableAdapter(Minecraft mc2) {
      this.mc = mc2;
   }

   public String getText() {
      return OpenGlHelper.getText();
   }
}
