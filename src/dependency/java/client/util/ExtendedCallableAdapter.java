package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class ExtendedCallableAdapter implements Callable {
   public final Minecraft mc;

   public ExtendedCallableAdapter(Minecraft mc2) {
      this.mc = mc2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() {
      return GL11.glGetString(7937) + " GL version " + GL11.glGetString(7938) + ", " + GL11.glGetString(7936);
   }
}
