package net.augustus.utils.input;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.events.KeyInputSource;
import net.augustus.events.listeners.KeyListener;
import java.util.LinkedList;
import java.util.Queue;
import net.augustus.modules.Module;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class ToggleUtil {
   private static long lastFlushTime;
   private static final Queue PENDING_TOGGLES = new LinkedList();
   public static final int MOUSE_BUTTON_OFFSET = 10000;

   private static boolean isScreenBlockingInput() {
      GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
      return currentScreen != null && GuiScreen.NO_TOGGLE_SCREENS.contains(currentScreen);
   }

   public static void flushPendingToggles() {
      lastFlushTime = System.currentTimeMillis();

      Runnable runnable;
      while ((runnable = (Runnable)PENDING_TOGGLES.poll()) != null) {
         runnable.run();
      }
   }

   public static void onMouseInput(boolean flag) {
      if (Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().thePlayer != null) {
         int n = Mouse.getEventButton() + 10000;
         boolean eventButtonState = Mouse.getEventButtonState();
         handleBindInput(n, eventButtonState, flag);
         if (eventButtonState && n != 0 && !flag) {
            KeyListener.postKey(n, true, eventButtonState, KeyInputSource.TOGGLE_UTIL);
         }
      }
   }

   private static double getHoldThresholdMs() {
      long l = System.currentTimeMillis() - lastFlushTime;
      double value = ClientManagers.moduleManager.clickGui.dynamicKeyTime.getValue();
      if ((double)l > value && value > 70.0) {
         value = (double)(l + 50L);
      }

      return value;
   }

   private static void handleBindInput(int n, boolean flag, boolean flag2) {
      double holdThresholdMs = getHoldThresholdMs();
      long currentTimeMs = System.currentTimeMillis();

      for (Module module : (Iterable<Module>)(Augustus.getInstance().getModuleManager().getModules())) {
         if (n != 0) {
            if (module.getKeyCode() == n) {
               boolean flag3 = !flag && module.isToggled() && module.isToggledByKey() && ((double)(currentTimeMs - module.getLastToggleTimeMs()) > holdThresholdMs || module.isKeyHoldMode());
               if ((flag && !flag2) || flag3) {
                  Queue queue = PENDING_TOGGLES;
                  queue.add((Runnable)() -> module.toggle());
               }

               if (!flag) {
                  module.setToggledByKey(false);
               }
            }
         }
      }
   }

   public static void onKeyboardInput(boolean flag) {
      if (Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().thePlayer != null) {
         boolean eventKeyState = Keyboard.getEventKeyState();
         int eventKey = Keyboard.getEventKey();
         boolean flag2 = flag || isScreenBlockingInput();
         handleBindInput(eventKey, eventKeyState, flag2);
         if (eventKeyState && eventKey != 0 && !flag2) {
            KeyListener.postKey(eventKey, false, eventKeyState, KeyInputSource.TOGGLE_UTIL);
         }
      }
   }
}
