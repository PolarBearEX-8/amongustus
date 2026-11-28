package net.augustus.commands.impl;



import net.augustus.ClientManagers;
import net.augustus.commands.Command;
import net.augustus.settings.TextInputSetting;
public class KillMessageCommand extends Command {

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (Set killmessage for module KillMessage)");
   }

   public KillMessageCommand() {
      super("killmessage");
   }

   public void commandAction(String[] args, String rawMessage) {
      if (args.length > 1) {
         StringBuilder sb = new StringBuilder();

         for (int i = 1; i < args.length; i++) {
            sb.append(args[i]);
            if (i < args.length - 1) {
               sb.append(" ");
            }
         }

         if (ClientManagers.moduleManager.killMessage.customKillmessages.getEntryCount() > 0) {
            String str2 = sb.toString();
            ((TextInputSetting)ClientManagers.moduleManager.killMessage.customKillmessages.getEntry((byte)0)).setValue(str2);
            this.sendChat("Set your first kill message to: §c§l" + str2);
         } else {
            this.sendChat("§cNo kill messages available. Add one in the ClickGUI first.");
         }
      } else {
         this.sendChat("§7" + this.getCommand() + " [§2Message§7]");
      }
   }

}
