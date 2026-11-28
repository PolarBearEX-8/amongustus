package net.augustus.commands.impl;



import net.augustus.ClientManagers;
import net.augustus.commands.Command;
public class CustomNameCommand extends Command {

   public void commandAction(String[] args, String rawMessage) {
      if (args.length == 2) {
         String str3 = args[1];
         ClientManagers.moduleManager.customName.customName.setValue(str3);
         this.sendChat("Set your custom name to: §c§l" + ClientManagers.moduleManager.customName.customName.getValue());
         ClientManagers.moduleManager.customName.refreshCustomName();
      } else {
         this.sendChat("§7" + this.getCommand() + " [§2Name§7]");
      }
   }

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (Sets your custom name)");
   }

   public CustomNameCommand() {
      super("customName");
   }

}
