package net.augustus.commands.impl;



import net.augustus.ClientManagers;
import net.augustus.commands.Command;
import net.augustus.settings.TextInputSetting;
public class ClientSpooferCommand extends Command {

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (set a custom string for clientspoofing)");
   }

   public ClientSpooferCommand() {
      super("clientSpoofer");
   }

   public void commandAction(String[] args, String rawMessage) {
      if (args.length > 1) {
         ClientManagers.moduleManager.clientSpoofer.customSpoofString.setValue(rawMessage.substring(this.getCommand().length() + 1));
         this.sendChat("Set spoofer string to: §c§l\"" + ClientManagers.moduleManager.clientSpoofer.customSpoofString.getValue() + "\"");
      } else {
         this.sendChat("§7" + this.getCommand() + " [§2Custom string§7]");
      }
   }

}
