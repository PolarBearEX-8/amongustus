package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.options.NoScreenClosingScreenOption;
import net.augustus.clickgui.ClickGuiScreen;
import net.augustus.events.listeners.PacketListener;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2EPacketCloseWindow;

public class NoScreenClosing extends Module implements PacketListener {
   private final MultiOptionSetting screensToNoClose;

   public NoScreenClosing() {
       super("NoScreenClosing", "KeinBildschirmSchließen", new Color(255, 153, 64), Categorys.PLAYER);
      this.screensToNoClose = new MultiOptionSetting(this, "ScreensToNoClose", NoScreenClosingScreenOption.values());
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (event.getPacket() instanceof S2EPacketCloseWindow
         && (
            mc.currentScreen instanceof GuiInventory && NoScreenClosingScreenOption.isOptionSelected(NoScreenClosingScreenOption.INVENTORY)
               || mc.currentScreen instanceof ClickGuiScreen && NoScreenClosingScreenOption.isOptionSelected(NoScreenClosingScreenOption.CLICK_GUI)
               || mc.currentScreen instanceof GuiChest && NoScreenClosingScreenOption.isOptionSelected(NoScreenClosingScreenOption.CHEST)
               || mc.currentScreen instanceof GuiChat && NoScreenClosingScreenOption.isOptionSelected(NoScreenClosingScreenOption.CHAT)
               || !(mc.currentScreen instanceof GuiInventory)
                  && !(mc.currentScreen instanceof ClickGuiScreen)
                  && !(mc.currentScreen instanceof GuiChest)
                  && !(mc.currentScreen instanceof GuiChat)
                  && NoScreenClosingScreenOption.isOptionSelected(NoScreenClosingScreenOption.OTHERS)
         )) {
         event.cancel();
      }
   }
}
