package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class BaseCallableAdapter implements Callable {
   public final Minecraft mc;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      String text = VanillaManager.getText();
      return !text.equals("vanilla")
         ? "Definitely; Client brand changed to '" + text + "'"
         : (
            Minecraft.class.getSigners() == null
               ? "Very likely; Jar signature invalidated"
               : "Probably not. Jar signature remains and client brand is untouched."
         );
   }

   public BaseCallableAdapter(Minecraft mc2) {
      this.mc = mc2;
   }
}
