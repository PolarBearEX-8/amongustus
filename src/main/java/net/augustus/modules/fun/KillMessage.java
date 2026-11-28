package net.augustus.modules.fun;

import java.awt.Color;
import java.util.HashMap;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.TextInputSetting;
import net.augustus.settings.DynamicSettingList;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;

public class KillMessage extends Module implements PacketListener, TickListener {
   private final StringValue sequence;
   private final StringValue killMessageMode;
   private final RandomizedRangeSettings delay;
   private final BooleanValue inInv;
   public final DynamicSettingList customKillmessages;
   private int randomMessageIndex;
   private final HashMap pendingKillMessages;
   private final TimeHelper timeHelper;

   public KillMessage() {
       super("KillMessage", "Tötungsnachricht", new Color(46, 77, 66), Categorys.FUN);
      this.sequence = new StringValue(this, "Sequence", "YourFirst", new String[]{"Irrelevant", "YourFirst", "OpponentFirst"});
      this.killMessageMode = new StringValue(
         this, "KillMessageMode", "Augustus", new String[]{"Augustus", "L", "GG", "GG AC", "Hahaha", "Spectate", "Random", "Custom"}
      );
      this.delay = (new RandomizedRangeSettings(this, "Delay", 500.0, 2000.0, 0.0, 4000.0, 0)).withoutNotInARow();
      this.inInv = new BooleanValue(this, "InInv", false);
      DynamicSettingList dynamicSettingList2 = new DynamicSettingList(this, "CustomKillmessages", n -> this.createCustomMessageSetting(n), 1, 50).withInitialCount((byte)1);
      SettingVisibilityCondition condition = () -> {
         return this.killMessageMode.getSelectedValue().equals("Custom");
      };
      this.customKillmessages = (DynamicSettingList)dynamicSettingList2.withVisibility(condition);
      this.randomMessageIndex = 1;
      this.pendingKillMessages = new HashMap();
      this.timeHelper = new TimeHelper();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      this.pendingKillMessages.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S02PacketChat && mc.thePlayer != null && mc.theWorld != null) {
         String unformattedText = ((S02PacketChat)packet).getChatComponent().getUnformattedText();
         String name2 = mc.thePlayer.getName();
         if (unformattedText.contains(name2)) {
            String[] parts = unformattedText.split(" ");

            for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
               String name3 = entity.getName();
               if (!(entity instanceof EntityPlayerSP) && unformattedText.contains(name3)) {
                  for (String str : parts) {
                     if (this.sequence.getSelectedValue().equals("YourFirst")) {
                        if (str.contains(name2)) {
                           break;
                        }

                        if (str.contains(name3)) {
                           return;
                        }
                     } else {
                        if (str.contains(name2)) {
                           return;
                        }

                        if (str.contains(name3)) {
                           break;
                        }
                     }
                  }

                  this.pendingKillMessages.put(name3, (long)((double)System.currentTimeMillis() + this.delay.nextValue()));
               }
            }
         }
      }
   }

   @Override
   public void onEarlyTick() {
      if (!this.inInv.getValue() || mc.currentScreen == null) {
         for (String str : (Iterable<String>)(this.pendingKillMessages.keySet())) {
            if ((Long)this.pendingKillMessages.get(str) > System.currentTimeMillis()) {
               if (this.timeHelper.reached((long)this.delay.getLastIntValue())) {
                  this.sendKillMessage(str);
                  this.timeHelper.reset();
                  break;
               }
            }
         }
      }
   }

   private void sendKillMessage(String str) {
      switch (this.killMessageMode.getSelectedValue()) {
         case "Augustus":
            mc.thePlayer.sendChatMessage(str + " killed by " + Augustus.getInstance().getName() + "! Learn to cheat Autoclicker User");
            break;
         case "L":
            mc.thePlayer.sendChatMessage("L " + str);
            break;
         case "GG":
            mc.thePlayer.sendChatMessage("gg " + str);
            break;
         case "GG AC":
            mc.thePlayer.sendChatMessage("gg ac user " + str);
            break;
         case "Hahaha":
            mc.thePlayer.sendChatMessage("hahahahahaha " + str);
            break;
         case "Spectate":
            mc.thePlayer.sendChatMessage("Wie ist es so zu spectaten " + str + "?");
            break;
         case "Random":
            switch (this.randomMessageIndex) {
               case 1:
                  mc.thePlayer.sendChatMessage("L " + str);
                  this.randomMessageIndex++;
                  return;
               case 2:
                  mc.thePlayer.sendChatMessage("gg " + str);
                  this.randomMessageIndex++;
                  return;
               case 3:
                  mc.thePlayer.sendChatMessage("gg ac user " + str);
                  this.randomMessageIndex++;
                  return;
               case 4:
                  mc.thePlayer.sendChatMessage("HAHAHAHAHHAHA " + str);
                  this.randomMessageIndex = 1;
                  return;
               default:
                  return;
            }
         case "Custom":
            TextInputSetting textInputSetting = (TextInputSetting)this.customKillmessages.nextEntry();
            if (textInputSetting != null) {
               mc.thePlayer.sendChatMessage(textInputSetting.getValue() + " " + str);
            }
      }
   }

   private TextInputSetting createCustomMessageSetting(int n) {
      return new TextInputSetting(this, "Message_" + n, "killed by Augustus" + Augustus.getInstance().getVersion() + "!");
   }

}
