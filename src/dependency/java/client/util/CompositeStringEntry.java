package client.util;

import client.gui.CoreGuiScreenAdapter;
import net.minecraft.client.gui.GuiChat;

public class CompositeStringEntry extends GuiChat {
   public static final String labelText3 = "/reloadShaders";
   public static final String labelText4 = "/reloadChunks";

   @Override
   public void applyText3(String text) {
      if (this.matchesText(text)) {
         this.mc.ingameGUI.getInternalLoggerEntry().applyText(text);
      } else {
         super.applyText3(text);
      }
   }

   private boolean matchesText(String text) {
      if (text == null) {
         return false;
      } else {
         String text2 = text.trim();
         if (text2.equals("/reloadShaders")) {
            if (BuildManager.canExecute()) {
               PreEndHandManager.updateState77();
               PreEndHandManager.updateState63();
            }

            return true;
         } else if (text2.equals("/reloadChunks")) {
            this.mc.renderGlobal.updateState();
            return true;
         } else {
            return false;
         }
      }
   }

   public CompositeStringEntry(GuiChat guiChat) {
      super(CoreGuiScreenAdapter.resolveText(guiChat));
   }
}
