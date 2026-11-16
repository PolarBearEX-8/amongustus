package client.util;

import net.minecraft.client.Minecraft;

public final class AuxiliaryThreadAdapter extends Thread {
   public AuxiliaryThreadAdapter(String text) {
      super(text);
   }

   @Override
   public void run() {
      Minecraft.updateState13();
   }
}
