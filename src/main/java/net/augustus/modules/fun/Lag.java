package net.augustus.modules.fun;

import java.awt.Color;
import java.util.ArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.utils.TextFormatUtil;
import net.augustus.events.listeners.RenderListener;
import net.augustus.events.EventKey;
import net.augustus.settings.KeybindSetting;
import net.augustus.events.listeners.KeyListener;

import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.modules.fun.helper.LagReportStage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S07PacketRespawn;

public class Lag extends Module implements TickListener, PacketListener, KeyListener, RenderListener {
   private final KeybindSetting lagToggleKey;
   private LagReportStage lagReportStage;
   private boolean lagging;
   private final ArrayList discoveredPlayers;
   private final ArrayList reportedPlayers;

   public Lag() {
       super("Lag", "Verzögerung", new Color(201, 240, 153), Categorys.FUN);
      this.lagToggleKey = new KeybindSetting(this, "LagToggleKey");
      this.lagging = false;
      this.discoveredPlayers = new ArrayList();
      this.reportedPlayers = new ArrayList();
   }

   public void onEnable() {
      this.lagReportStage = LagReportStage.SEND_MESSAGE;
      this.lagging = false;
      this.discoveredPlayers.clear();
      this.reportedPlayers.clear();
      if (mc.thePlayer != null) {
         mc.thePlayer.sendChatMessage("/language german");
      }
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(14);
      this.toggleEventSubscription(32);
   }

   public void onRender3D(float partialTicks) {
      if (this.lagging) {
         mc.currentScreen = (GuiScreen)null;
      }
   }

   public void onEarlyTick() {
      if (this.lagging) {
         switch (this.lagReportStage) {
            case SEND_MESSAGE:
               for (String str : (Iterable<String>)(this.discoveredPlayers)) {
                  if (!this.reportedPlayers.contains(str)) {
                     mc.thePlayer.sendChatMessage("/report " + str);
                     this.lagReportStage = LagReportStage.CLICK;
                     this.reportedPlayers.add(str);
                     break;
                  }
               }
               break;
            case CLICK:
               if (mc.lastDisplayedScreen instanceof GuiChest) {
                  GuiChest lastDisplayedScreen = (GuiChest)mc.lastDisplayedScreen;

                  for (int i = 0; i < lastDisplayedScreen.inventorySlots.inventorySlots.size(); i++) {
                     ItemStack stack = lastDisplayedScreen.inventorySlots.getSlot(i).getStack();
                     if (stack != null) {
                        if (stack.getItem() instanceof ItemSword) {
                           mc.playerController.windowClick(lastDisplayedScreen.inventorySlots.windowId, -999, 0, 4, mc.thePlayer);
                           mc.playerController.windowClick(lastDisplayedScreen.inventorySlots.windowId, i, 0, 0, mc.thePlayer);
                           this.lagReportStage = LagReportStage.CLOSE;
                           break;
                        }
                     }
                  }
               }
               break;
            case CLOSE:
               if (mc.lastDisplayedScreen instanceof GuiChest) {
                  mc.thePlayer.closeScreen();
                  this.lagReportStage = LagReportStage.SEND_MESSAGE;
               }
         }
      } else {
         if (mc.lastDisplayedScreen instanceof GuiChest && (this.lagReportStage == LagReportStage.CLOSE || this.lagReportStage == LagReportStage.CLICK)) {
            mc.thePlayer.closeScreen();
            this.lagReportStage = LagReportStage.SEND_MESSAGE;
         }

         for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(mc.getNetHandler().getPlayerInfoMap())) {
            String playerName = networkPlayerInfo.getPlayerName();
            if (TextFormatUtil.isCleanPlayerName(playerName) && !this.discoveredPlayers.contains(playerName) && !playerName.equals(mc.session.getUsername())) {
               this.discoveredPlayers.add(playerName);
            }
         }
      }
   }

   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S07PacketRespawn) {
         this.reportedPlayers.clear();
      }

      if (packet instanceof S02PacketChat) {
         if (((S02PacketChat)packet).getChatComponent().getUnformattedText().contains("Spieler konnte nicht gefunden werden")) {
            this.lagReportStage = LagReportStage.SEND_MESSAGE;
            event.cancel();
         }
      }
   }

   public void onToggleKey(EventKey event) {
      if (event.getKeyCode() == this.lagToggleKey.getKeyCode()) {
         this.lagging = !this.lagging;
         if (this.lagging) {
            this.lagReportStage = LagReportStage.SEND_MESSAGE;
            this.sendChatMessage("Start lagging");
         } else {
            this.sendChatMessage("Stop lagging");
         }
      }
   }

}
