package net.augustus.commands.impl;



import net.augustus.ClientManagers;
import net.augustus.commands.Command;
import net.augustus.settings.TextInputSetting;
public class DeathMessageCommand extends Command {

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (set the death message)");
   }

   public DeathMessageCommand() {
      super("deathmessage");
   }

   public void commandAction(String[] args, String rawMessage) {
      if (args.length > 1) {
         StringBuilder sb = new StringBuilder();

         for (int i = 1; i < args.length; i++) {
            sb.append(args[i]).append(" ");
         }

         sb.setLength(Math.max(0, sb.length() - 1));
         if (ClientManagers.moduleManager.deathCommand.messageAtDeath.getEntryCount() > 0) {
            String str2 = sb.toString();
            ((TextInputSetting)ClientManagers.moduleManager.deathCommand.messageAtDeath.getEntry((byte)0)).setValue(str2);
            this.sendChat("Set your first death message to: §c§l" + str2);
         } else {
            this.sendChat("§cNo messages available. Add one in the ClickGUI first.");
         }
      }
   }

}
