package net.augustus.commands.impl;

import net.augustus.Augustus;
import net.augustus.commands.Command;
import net.augustus.events.EventManager;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.DoubleListSetting;
import net.augustus.settings.StringListSetting;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.NumberFormatUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import net.augustus.modules.misc.ClientSettings;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;

public class AlertCommand extends Command implements TickListener {
   private final StringListSetting alertNames;
   private boolean needsInit;
   private final HashSet alertedPlayers;
   private final DoubleListSetting alertDistances;
   private final HashMap alertMap;

   private void removeAlert(String str) {
      Double d = (Double)this.alertMap.get(str);
      if (d != null) {
         this.alertMap.remove(str);
         this.alertNames.remove(str);
         this.alertDistances.remove(d);
      }
   }

   public boolean hasAlert(String str) {
      return this.alertMap.containsKey(str);
   }

   public AlertCommand() {
       super("alert");
      this.alertNames = new StringListSetting(Augustus.getInstance().getModuleManager().clientSettings, "AlertNames", new ArrayList());
      ClientSettings augustusServer = Augustus.getInstance().getModuleManager().clientSettings;
      ArrayList list = new ArrayList();
      String str = "AlertNumbers";
      this.alertDistances = new DoubleListSetting(augustusServer, str, list);
      this.alertMap = new HashMap();
      this.alertedPlayers = new HashSet();
      this.needsInit = true;
      EventManager.getEventBus().subscribe(40, this);
   }

   public void sendUsageMessage() {
      this.sendChat("§7" + this.getCommand() + " [§cadd/remove/clear/list§7]");
   }

   public void sendRemoveUsageMessage() {
      this.sendChat("§7" + this.getCommand() + " [§cremove§7] [§2Name§7]");
   }

   private void loadAlertsFromSettings() {
      int n = 0;

      while (true) {
         if (n >= this.alertNames.size()) {
            break;
         }

         if (n >= this.alertDistances.size()) {
            break;
         }

         String str = (String)this.alertNames.get(n);
         Double d = (Double)this.alertDistances.get(n);
         if (str != null && d != null) {
            this.alertMap.put(str, d);
         }

         n++;
      }

      this.needsInit = false;
   }

   private void clearAlerts() {
      this.alertMap.clear();
      this.alertNames.clear();
      this.alertDistances.clear();
   }

   @Override
   public void onEarlyTick() {
      if (this.needsInit) {
         this.loadAlertsFromSettings();
      }

      if (!this.alertMap.isEmpty()) {
         HashSet set = new HashSet();

         for (EntityPlayer player : (Iterable<EntityPlayer>)(mc.theWorld.playerEntities)) {
            if (!(player instanceof EntityPlayerSP) && player.getGameProfile() != null) {
               String name = player.getGameProfile().getName();
               Double d = (Double)this.alertMap.get(name);
               if (d != null) {
                  if (d < 0.0) {
                     if (!this.alertedPlayers.contains(name)) {
                        ChatUtil.sendChat("§cALERT: Player " + name + " is nearby!");
                        this.alertedPlayers.add(name);
                     }

                     set.add(name);
                  } else if ((double)mc.thePlayer.getDistanceToEntity(player) < d) {
                     if (!this.alertedPlayers.contains(name)) {
                        ChatUtil.sendChat("§cALERT: Player " + name + " is nearby!");
                        this.alertedPlayers.add(name);
                     }

                     set.add(name);
                  }
               }
            }
         }

         for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(mc.getNetHandler().getPlayerInfoMap())) {
            String name2 = networkPlayerInfo.getGameProfile().getName();
            if (!name2.equals(mc.session.getUsername())) {
               Double d2 = (Double)this.alertMap.get(name2);
               if (d2 != null && !(d2 >= 0.0)) {
                  if (!this.alertedPlayers.contains(name2)) {
                     ChatUtil.sendChat("§cALERT: Player " + name2 + " is nearby!");
                     this.alertedPlayers.add(name2);
                  }

                  set.add(name2);
               }
            }
         }

         this.alertedPlayers.removeIf(obj -> {
            boolean flag = set.contains(obj);
            if (!flag) {
               ChatUtil.sendChat("§aAll-clear: " + obj + " is no longer nearby");
            }

            return !flag;
         });
      }
   }

   @Override
   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (Alerts players nearby)");
   }

   @Override
   public void commandAction(String[] args, String rawMessage) {
      if (args.length < 2) {
         this.sendUsageMessage();
      } else {
         if (args[1].equalsIgnoreCase("add")) {
            if (args.length == 3) {
               this.addAlert(args[2], -1.0);
               ChatUtil.sendChat("Added " + args[2] + " successfully");
            } else if (args.length == 4) {
               double d2 = -1.0;

               try {
                  d2 = Double.parseDouble(args[3]);
               } catch (Exception e) {
               }

               this.addAlert(args[2], d2);
               ChatUtil.sendChat("Added " + args[2] + " successfully");
            } else {
               this.sendAddUsageMessage();
            }
         } else if (args[1].equalsIgnoreCase("remove")) {
            if (args.length != 3) {
               this.sendRemoveUsageMessage();
               return;
            }

            if (((Double)this.alertMap.get(args[2])) == null) {
               ChatUtil.sendChat("Unable to find: " + args[2]);
               return;
            }

            ChatUtil.sendChat("Removed " + args[2] + " successfully");
            this.removeAlert(args[2]);
         } else if (args[1].equalsIgnoreCase("clear")) {
            if (args.length != 2) {
               this.sendUsageMessage();
               return;
            }

            ChatUtil.sendChat("Cleared " + this.alertMap.size() + " alerts");
            this.clearAlerts();
         } else if (args[1].equalsIgnoreCase("list")) {
            if (args.length != 2) {
               this.sendUsageMessage();
               return;
            }

            if (this.alertMap.isEmpty()) {
               ChatUtil.sendChat("No names to alert found");
               return;
            }

            ChatUtil.sendChat("Alerting names: ");

            for (String str6 : (Iterable<String>)(this.alertMap.keySet())) {
               Double d4 = (Double)this.alertMap.get(str6);
               ChatUtil.sendChat(str6 + (d4 > 0.0 ? "; " + NumberFormatUtil.formatDecimal(d4, 1) : ""));
            }
         } else {
            this.sendUsageMessage();
         }
      }
   }

   public void sendAddUsageMessage() {
      this.sendChat("§7" + this.getCommand() + " [§cadd§7] [§2Name§7] ([§2Distance§7])");
   }

   private void addAlert(String str, double d) {
      if (!this.alertMap.containsKey(str)) {
         this.alertMap.put(str, d);
         this.alertNames.add(str);
         DoubleListSetting alertDistances2 = this.alertDistances;
         Double d2 = d;
         alertDistances2.add(d2);
      }
   }




}
