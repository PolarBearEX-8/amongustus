package net.augustus.modules.misc;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.input.RawMouseInputThread;
import net.java.games.input.Mouse;
import net.minecraft.client.Minecraft;

public class RawMouseInput extends Module {
   private static boolean active = false;

   public RawMouseInput() {
       super("RawMouseInput", "RohMausEingabe", new Color(255, 211, 99), Categorys.MISC);
   }

   public void onEnable() {
      if (!Minecraft.isRunningOnMac) {
         RawMouseInputThread.INSTANCE.controllerEnvironment = RawMouseInputThread.createDefaultControllerEnvironment();
         RawMouseInputThread.INSTANCE.setMouseDevice((Mouse)null);
         setActive(true);
      }
   }

   public void onDisable() {
      RawMouseInputThread.INSTANCE.setMouseDevice((Mouse)null);
      setActive(false);
   }

   public void updateEventSubscriptions() {
   }

   public static synchronized boolean isActive() {
      return active;
   }

   public static void setActive(boolean flag) {
      active = flag;
   }

}
