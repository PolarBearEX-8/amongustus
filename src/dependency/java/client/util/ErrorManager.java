package client.util;

import client.event.PostUpdateSubscriber;
import client.setting.GameSettings;
import net.minecraft.client.Minecraft;

public class ErrorManager implements PostUpdateSubscriber {
   public final Minecraft mc;

   @Override
   public String resolveText(String text) {
      try {
         return String.format(text, GameSettings.resolveText(this.mc.gameSettings.keyBindInventory.getKeyCode()));
      } catch (Exception exception) {
         return "Error: " + exception.getLocalizedMessage();
      }
   }

   public ErrorManager(Minecraft mc2) {
      this.mc = mc2;
   }
}
