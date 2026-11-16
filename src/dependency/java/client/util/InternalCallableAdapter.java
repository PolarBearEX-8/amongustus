package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class InternalCallableAdapter implements Callable {
   public final Minecraft mc;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      StringBuilder builder = new StringBuilder();

      for (Object element : this.mc.gameSettings.itemList) {
         if (builder.length() > 0) {
            builder.append(", ");
         }

         builder.append(element);
         if (this.mc.gameSettings.itemList2.contains(element)) {
            builder.append(" (incompatible)");
         }
      }

      return builder.toString();
   }

   public InternalCallableAdapter(Minecraft mc2) {
      this.mc = mc2;
   }
}
