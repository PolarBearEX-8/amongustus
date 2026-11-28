package net.augustus.commands.impl;



import net.augustus.commands.Command;
import net.augustus.utils.ChatUtil;
public class GcCommand extends Command {

   public GcCommand() {
      super("gc");
   }

   @Override
   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (clears your memory)");
   }

   @Override
   public void commandAction(String[] args, String rawMessage) {
      System.gc();
      ChatUtil.sendChat("Memory cleared.");
   }
}
