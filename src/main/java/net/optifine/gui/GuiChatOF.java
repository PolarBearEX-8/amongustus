package net.optifine.gui;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiVideoSettings;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;

public class GuiChatOF extends GuiChat {
   private static final String CMD_RELOAD_SHADERS = null;
   private static final String CMD_RELOAD_CHUNKS = null;

   private boolean checkCustomCommand(String str) {
      if (str == null) {
         return false;
      } else {
         str = str.trim();
         if (str.equals("/reloadShaders")) {
            if (Config.isShaderPackLoaded()) {
               Shaders.uninit();
               Shaders.loadShaderPack();
            }

            return true;
         } else if (str.equals("/reloadChunks")) {
            this.mc.renderGlobal.loadRenderers();
            return true;
         } else {
            return false;
         }
      }
   }

   public GuiChatOF(GuiChat guiChat) {
      super(GuiVideoSettings.getGuiChatText(guiChat));
   }

   @Override
   public void sendChatMessage(String str) {
      if (this.checkCustomCommand(str)) {
         this.mc.ingameGUI.getChatGUI().addToSentMessages(str);
      } else {
         super.sendChatMessage(str);
      }
   }
}
