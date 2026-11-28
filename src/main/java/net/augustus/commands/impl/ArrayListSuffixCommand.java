package net.augustus.commands.impl;



import net.augustus.ClientManagers;
import net.augustus.commands.Command;
public class ArrayListSuffixCommand extends Command {

   public void commandAction(String[] args, String rawMessage) {
      if (args.length == 2) {
         String str3 = args[1];
         ClientManagers.moduleManager.arrayList.customSuffix.setValue(str3);
         this.sendChat("§7Set suffix to " + args[1]);
      } else {
         this.sendChat("§7" + this.getCommand() + " [§2Suffix§7]");
      }
   }

   public ArrayListSuffixCommand() {
      super("arraylistsuffix");
   }

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + " §7(Set custom arraylist suffix)");
   }

}
