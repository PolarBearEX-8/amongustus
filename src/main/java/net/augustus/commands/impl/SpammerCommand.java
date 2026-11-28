package net.augustus.commands.impl;



import net.augustus.ClientManagers;
import net.augustus.commands.Command;
import net.augustus.settings.TextInputSetting;
public class SpammerCommand extends Command {

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (Sets your spam message)");
   }

   public SpammerCommand() {
      super("spammer");
   }

   public void commandAction(String[] args, String rawMessage) {
      if (args.length > 1) {
         String str2 = "";

         for (int i = 0; i < args.length; i++) {
            if (i > 0) {
               str2 = str2 + args[i] + " ";
            }
         }

         if (ClientManagers.moduleManager.spammer.messages.getEntryCount() > 0) {
            ((TextInputSetting)ClientManagers.moduleManager.spammer.messages.getEntry((byte)0)).setValue(str2);
            this.sendChat("Set your first spam message to: §c§l" + str2);
         } else {
            this.sendChat("§cNo messages available. Add one in the ClickGUI first.");
         }
      } else {
         this.sendChat("§7" + this.getCommand() + " [§2Custom message§7]");
      }
   }

}
