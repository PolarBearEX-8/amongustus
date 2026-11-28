package net.augustus.utils.input;

import net.augustus.utils.ChatUtil;
import java.lang.reflect.Constructor;
import net.augustus.modules.misc.RawMouseInput;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Mouse;
import net.java.games.input.Controller.Type;

public class RawMouseInputThread {
   public ControllerEnvironment controllerEnvironment = createDefaultControllerEnvironment();
   public static final RawMouseInputThread INSTANCE = new RawMouseInputThread();
   public static int deltaY = 0;
   private Mouse mouseDevice;
   public static int deltaX = 0;

   private void pollLoop() {

      while (true) {
         if (RawMouseInput.isActive()) {
            if (this.mouseDevice != null) {
               this.mouseDevice.poll();
               deltaX = deltaX + (int)this.mouseDevice.getX().getPollData();
               deltaY = deltaY + (int)this.mouseDevice.getY().getPollData();
            } else {
               Controller[] controllers = this.controllerEnvironment.getControllers();

               for (int i = 0; i < controllers.length; i++) {
                  if (controllers[i].getType() == Type.MOUSE) {
                     controllers[i].poll();
                     if ((double)((Mouse)controllers[i]).getX().getPollData() != 0.0 || (double)((Mouse)controllers[i]).getY().getPollData() != 0.0) {
                        this.setMouseDevice((Mouse)controllers[i]);
                        ChatUtil.sendChat("Found mouse");
                     }
                  }
               }
            }
         }

         try {
            Thread.sleep(1L);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }

   public static ControllerEnvironment createDefaultControllerEnvironment() {
      try {
         Class<?> cls = Class.forName("net.java.games.input.DefaultControllerEnvironment");
         Constructor<?> ctor = cls.getDeclaredConstructor();
         ctor.setAccessible(true);
         return (ControllerEnvironment)ctor.newInstance();
      } catch (Throwable e) {
         return ControllerEnvironment.getDefaultEnvironment();
      }
   }

   public synchronized void setMouseDevice(Mouse mouse) {
      this.mouseDevice = mouse;
   }

   public void start() {
      Thread thread = new Thread(() -> this.pollLoop());
      thread.setName("inputThread");
      thread.start();
   }

}
