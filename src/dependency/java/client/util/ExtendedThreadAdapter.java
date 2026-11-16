package client.util;

import net.minecraft.client.Minecraft;

public class ExtendedThreadAdapter extends Thread {
   public final Minecraft mc;

   public ExtendedThreadAdapter(Minecraft mc2, String text) {
      super(text);
      this.mc = mc2;
   }

   @Override
   public void run() {
      while (this.mc.enabled17) {
         try {
            Thread.sleep(2147483647L);
         } catch (InterruptedException interruptedException) {
         }
      }
   }
}
