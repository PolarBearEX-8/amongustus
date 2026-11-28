package net.augustus.modules.fun;

import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;

public class PartySpammer extends Module implements PacketListener, TickListener {
   private final DoubleValue inviteDelay;
   private final BooleanValue hidePartyMessage;
   private Collection pendingPlayerInfos;

   public PartySpammer() {
       super("PartySpammer", "FeierNachrichtenÜberschütter", new Color(204, 230, 122), Categorys.FUN);
      this.inviteDelay = new DoubleValue(this, "InviteDelay", 1000.0, 0.0, 4000.0, 0);
      this.hidePartyMessage = new BooleanValue(this, "HidePartyMessage", true);
      this.pendingPlayerInfos = new LinkedList();
   }

   public void onEnable() {
      if (mc.thePlayer != null) {
         this.pendingPlayerInfos = mc.getNetHandler().getPlayerInfoMap2().values();
      }
   }

   public void onDisable() {
      this.pendingPlayerInfos.clear();
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
   }

   public void onTick() {
      if (mc.getNetHandler().getPlayerInfoMap2().values().size() > 1) {
         if (mc.thePlayer.ticksExisted % (int)Math.max(1.0, this.inviteDelay.getValue() / 50.0 * 2.0) == 0) {
            if (this.pendingPlayerInfos.isEmpty()) {
               this.pendingPlayerInfos = mc.getNetHandler().getPlayerInfoMap2().values();
            }

            Iterator it = this.pendingPlayerInfos.iterator();

            while (it.hasNext()) {
               NetworkPlayerInfo networkPlayerInfo = (NetworkPlayerInfo)it.next();
               if (networkPlayerInfo != null && networkPlayerInfo.getGameProfile() != null) {
                  String name2 = networkPlayerInfo.getGameProfile().getName();
                  if (name2 != null && name2.length() >= 3 && !name2.contains("§") && !name2.contains("#") && !name2.contains(" ") && name2.length() < 17) {
                     if (!name2.equals(mc.thePlayer.getName()) && !ClientManagers.moduleManager.midClick.isFriend(name2)) {
                        mc.thePlayer.sendChatMessage("/party invite " + name2);
                        it.remove();
                        break;
                     }

                     it.remove();
                  }
               }
            }
         } else if (mc.thePlayer.ticksExisted % (int)Math.max(1.0, this.inviteDelay.getValue() / 50.0) == 0) {
            mc.thePlayer.sendChatMessage("/party leave");
         }
      }
   }

   public void onPacketReceive(EventPacket event) {
      if (this.hidePartyMessage.getValue()) {
         Packet packet = event.getPacket();
         if (packet instanceof S02PacketChat) {
            S02PacketChat packet2 = (S02PacketChat)packet;
            if (packet2.getChatComponent().getUnformattedText().contains("Party") || packet2.getChatComponent().getUnformattedText().contains("party")) {
               event.cancel();
            }
         }
      }
   }

}
