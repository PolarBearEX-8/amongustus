package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class GeometryValidator implements Callable {
   public final Minecraft mc;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return this.mc.currentScreen.getClass().getCanonicalName();
   }

   public GeometryValidator(Minecraft mc2) {
      this.mc = mc2;
   }
}
