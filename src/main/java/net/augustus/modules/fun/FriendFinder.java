package net.augustus.modules.fun;

import java.awt.Color;
import java.util.ArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.utils.TextFormatUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;

public class FriendFinder extends Module implements PacketListener, TickListener {
   private final DoubleValue inviteDelay;
   private final BooleanValue hideFriendMessage;
   private final TimeHelper timeHelper;
   private final ArrayList invitedPlayers;

   public FriendFinder() {
       super("FriendFinder", "FreundeFinder", new Color(235, 201, 52), Categorys.FUN);
      this.inviteDelay = new DoubleValue(this, "InviteDelay", 1000.0, 0.0, 10000.0, 0);
      this.hideFriendMessage = new BooleanValue(this, "HideFriendMessage", true);
      this.timeHelper = new TimeHelper();
      this.invitedPlayers = new ArrayList();
   }

   public void onEnable() {
   }

   public void onDisable() {
      this.invitedPlayers.clear();
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
   }

   public void onTick() {
      if (mc.getNetHandler().getPlayerInfoMap2().values().size() > 1) {
         if (this.timeHelper.reached(this.inviteDelay.getValue())) {
            for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(mc.getNetHandler().getPlayerInfoMap2().values())) {
               String name2 = networkPlayerInfo.getGameProfile().getName();
               if (TextFormatUtil.isCleanPlayerName(name2)
                  && !name2.equals(mc.thePlayer.getName())
                  && !ClientManagers.moduleManager.midClick.isFriend(name2)
                  && !this.invitedPlayers.contains(name2)) {
                  mc.thePlayer.sendChatMessage("/friend add " + name2);
                  this.invitedPlayers.add(name2);
                  this.timeHelper.reset();
                  break;
               }
            }
         }
      }
   }

   public void onPacketReceive(EventPacket event) {
      if (this.hideFriendMessage.getValue()) {
         Packet packet = event.getPacket();
         if (packet instanceof S02PacketChat) {
            String unformattedText = ((S02PacketChat)packet).getChatComponent().getUnformattedText();
            if (unformattedText.contains("Friends") || unformattedText.contains("friends") || unformattedText.contains("Freunde")) {
               event.cancel();
            }
         }
      }
   }

}
