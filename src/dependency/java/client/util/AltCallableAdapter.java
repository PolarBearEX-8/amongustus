package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;
import org.lwjgl.Sys;

public class AltCallableAdapter implements Callable {
   public final Minecraft mc;

   @Override
   public Object call() throws Exception {
      return this.getVersion();
   }

   public AltCallableAdapter(Minecraft mc2) {
      this.mc = mc2;
   }

   public String getVersion() {
      return Sys.getVersion();
   }
}
