package net.augustus.modules.misc;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.discord.DiscordRpcManager;
import net.augustus.Augustus;
import net.augustus.clickgui.ClickGuiScreen;
import net.minecraft.client.gui.GuiChat;

public class NoDiscordRPC extends Module {

   public NoDiscordRPC() {
       super("NoDiscordRPC", "KeinDiscord", new Color(180, 246, 250), Categorys.MISC);
   }

   public void onEnable() {
      if (mc.isNotInGui() || mc.currentScreen instanceof GuiChat || mc.thePlayer != null || mc.theWorld != null || mc.currentScreen instanceof ClickGuiScreen) {
         Augustus.getInstance().getDiscordRpcManager().shutdown();
         Augustus.getInstance().getDiscordRpcManager().start();
      }
   }

   public void onDisable() {
      if (!mc.isNotInGui() && !(mc.currentScreen instanceof GuiChat)) {
         if (mc.thePlayer == null) {
            if (mc.theWorld == null && !(mc.currentScreen instanceof ClickGuiScreen)) {
               return;
            }
         }
      }

      Augustus.getInstance().getDiscordRpcManager().start();
      if (mc.theWorld != null) {
         if (mc.theWorld.isRemote) {
            DiscordRpcManager discordRpcManager = Augustus.getInstance().getDiscordRpcManager();
            String str = "Ingame";
            discordRpcManager.updatePresence("Playing lonely in singleplayer", str);
         } else {
            DiscordRpcManager discordRpcManager2 = Augustus.getInstance().getDiscordRpcManager();
            String str3 = "Cheating";
            discordRpcManager2.updatePresence("Maybe cheating on ReifenHD.net", str3);
         }
      }
   }

   public void updateEventSubscriptions() {
   }

}
