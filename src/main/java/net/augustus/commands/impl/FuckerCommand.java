package net.augustus.commands.impl;



import net.augustus.ClientManagers;
import net.augustus.commands.Command;
public class FuckerCommand extends Command {

   public void commandAction(String[] args, String rawMessage) {
      if (args.length > 1) {
         if (args[1].equalsIgnoreCase("set") && args.length >= 3) {
            int n = -1;

            try {
               n = Integer.parseInt(args[2]);
            } catch (Exception e) {
               System.err.println("No Integer");
            }

            if (n != -1) {
               double d = (double)n;
               ClientManagers.moduleManager.fucker.customID.setValue(d);
               this.sendChat("Set Fucker ID to §2" + (int)ClientManagers.moduleManager.fucker.customID.getValue());
               return;
            }
         } else if (args[1].equalsIgnoreCase("get")) {
            this.sendChat("BlockID: §2" + (int)ClientManagers.moduleManager.fucker.customID.getValue());
            return;
         }
      }

      this.sendUsageMessage();
   }

   public FuckerCommand() {
      super("fucker");
   }

   public void sendUsageMessage() {
      this.sendChat("§7" + this.getCommand() + " [§cset/get§7] [§2BlockID§7]");
   }

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (Get or set the blockID for custom mode)");
   }

}
