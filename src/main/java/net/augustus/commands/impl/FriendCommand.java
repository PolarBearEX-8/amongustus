package net.augustus.commands.impl;

import net.augustus.ClientManagers;
import net.augustus.commands.Command;
import net.augustus.utils.TextFormatUtil;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;

public class FriendCommand extends Command {

   public void commandAction(String[] args, String rawMessage) {
      if (args.length != 3) {
         if (args.length == 2 && args[1].equalsIgnoreCase("removeall")) {
            ClientManagers.moduleManager.midClick.clearAllFriends();
            this.sendChat("Removed all friends from your friend list successfully.");
         } else {
            this.sendUsageMessage();
         }
      } else {
         String str3 = args[2];
         if (!TextFormatUtil.isCleanPlayerName(str3)) {
            this.sendChat("Invalid name");
         } else {
            if (!args[1].equalsIgnoreCase("add") && !args[1].equalsIgnoreCase("permaAdd")) {
               if (args[1].equalsIgnoreCase("remove")) {
                  if (ClientManagers.moduleManager.midClick.friends.contains(str3)) {
                     ClientManagers.moduleManager.midClick.friends.remove(str3);
                     ClientManagers.moduleManager.midClick.removeSavedFriend(str3);
                     this.sendChat("Player §c" + str3 + "§7 successfully removed.");
                  } else {
                     this.sendChat("Player §c" + str3 + "§7 isn't in your friend list.");
                  }
               } else if (args[1].equalsIgnoreCase("removeall")) {
                  ClientManagers.moduleManager.midClick.clearAllFriends();
                  this.sendChat("Removed all friends from your friend list successfully.");
               } else {
                  this.sendUsageMessage();
               }
            } else {
               if (ClientManagers.moduleManager.midClick.friends.contains(str3)) {
                  this.sendChat("Player §c" + str3 + "§7 is already your friend.");
                  return;
               }


               for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(mc.thePlayer.sendQueue.getPlayerInfoMap())) {
                  String name = networkPlayerInfo.getGameProfile().getName();
                  if (!name.equals(mc.thePlayer.getGameProfile().getName()) && str3.equals(name)) {
                     ClientManagers.moduleManager.midClick.friends.add(str3);
                     if (args[1].equalsIgnoreCase("permaAdd")) {
                        ClientManagers.moduleManager.midClick.saveFriend(str3);
                     }

                     this.sendChat("Player §c" + str3 + "§7 successfully added.");
                     return;
                  }
               }

               this.sendChat("Player §c" + str3 + "§7 isn't on the server.");
            }
         }
      }
   }

   public FriendCommand() {
      super("friend");
   }

   public void sendUsageMessage() {
      this.sendChat("§7" + this.getCommand() + "§7 [§cadd/permaAdd/remove/removeall§7] [§2playername§7]");
   }

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (Add or remove friends)");
   }

}
