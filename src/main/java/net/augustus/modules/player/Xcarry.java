package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import net.minecraft.network.play.client.C0DPacketCloseWindow;

public class Xcarry extends Module implements PacketListener {

   public Xcarry() {
       super("Xcarry", "Behalten des X Förmigen Inventares", Color.WHITE, Categorys.PLAYER);
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
   public void onPacketSend(EventPacket event) {
      if (event.getPacket() instanceof C0DPacketCloseWindow) {
         if (((C0DPacketCloseWindow)event.getPacket()).getWindowId() == mc.thePlayer.openContainer.windowId) {
            event.cancel();
         }
      }
   }
}
