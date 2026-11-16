package client.util;

import net.java.games.input.DefaultControllerEnvironment;
import net.java.games.input.Mouse;

public class FoundMouseManager {
   public Mouse mouse;
   public static int count3 = 0;
   public static final FoundMouseManager foundMouseManager = new FoundMouseManager();
   public DefaultControllerEnvironment defaultControllerEnvironment = new DefaultControllerEnvironment();
   public static int count2 = 0;

   public synchronized void setMouse(Mouse mouse2) {
      this.mouse = mouse2;
   }

   private void updateState() {
      while (true) {
         try {
            Thread.sleep(1L);
         } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
         }
      }
   }

   public void updateState2() {
      Thread thread = new Thread(this::updateState);
      thread.setName("inputThread");
      thread.start();
   }
}
