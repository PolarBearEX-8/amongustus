package net.augustus.modules.fun;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.utils.TextFormatUtil;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.BooleanValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;

public class AutoReport extends Module implements PacketListener, TickListener {
   private final RandomizedRangeSettings delay;
   private final BooleanValue hideMessages;
   private int reportedCount;
   private final TimeHelper timeHelper;
   private final HashSet reportedPlayers;
   private final HashSet pendingPlayers;

   public AutoReport() {
       super("AutoReport", "AutomatischeMeldung", new Color(167, 245, 66), Categorys.FUN);
      this.delay = (new RandomizedRangeSettings(this, "Delay", 11000.0, 13000.0, 0.0, 15000.0, 0)).withoutNotInARow();
      this.hideMessages = new BooleanValue(this, "HideMessages", true);
      this.reportedCount = 0;
      this.timeHelper = new TimeHelper();
      this.reportedPlayers = new HashSet();
      this.pendingPlayers = new HashSet();
   }

   public void onEnable() {
   }

   public void onDisable() {
      this.pendingPlayers.clear();
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(26);
   }

   public void onTick() {
      this.setSuffix("" + this.reportedCount);
      if (mc.thePlayer.ticksExisted > 50) {
         for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(mc.getNetHandler().getPlayerInfoMap())) {
            String playerName = networkPlayerInfo.getPlayerName();
            if (!playerName.equals(mc.session.getUsername()) && !this.reportedPlayers.contains(playerName) && !ClientManagers.moduleManager.midClick.isFriend(playerName)) {
               this.pendingPlayers.add(playerName);
            }
         }

         if (this.timeHelper.reached((long)this.delay.getLastIntValue())) {
            this.delay.nextValue();
            Iterator it = this.pendingPlayers.iterator();

            while (it.hasNext()) {
               String str2 = (String)it.next();
               if (str2.equals(mc.session.getUsername()) || this.reportedPlayers.contains(str2) || ClientManagers.moduleManager.midClick.isFriend(str2)) {
                  return;
               }

               if (TextFormatUtil.isValidMinecraftUsername(str2)) {
                  boolean flag = false;

                  for (NetworkPlayerInfo networkPlayerInfo2 : (Iterable<NetworkPlayerInfo>)(mc.getNetHandler().getPlayerInfoMap())) {
                     if (networkPlayerInfo2.getPlayerName().equals(str2)) {
                        flag = true;
                        break;
                     }
                  }

                  if (flag) {
                     continue;
                  }
               } else {
                  this.reportedPlayers.add(str2);
                  it.remove();
                  continue;
               }

               mc.thePlayer.sendChatMessage("/report " + str2 + " hacking confirm");
               this.timeHelper.reset();
               this.reportedPlayers.add(str2);
               it.remove();
               this.reportedCount++;
               break;
            }

            return;
         }
      }
   }

   public void onPacketReceive(EventPacket event) {
      if (this.hideMessages.getValue()) {
         Packet packet = event.getPacket();
         if (packet instanceof S02PacketChat) {
            if (((S02PacketChat)packet).getChatComponent().getUnformattedText().contains("[Guardian]")) {
               event.cancel();
            }
         }
      }
   }

}
