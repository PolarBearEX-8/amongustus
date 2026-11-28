package net.augustus.commands.impl;

import net.augustus.Augustus;
import net.augustus.commands.Command;
import net.minecraft.util.ChatComponentText;

public class HelpCommand extends Command {

   public HelpCommand() {
      super("help");
   }

   public void commandAction(String[] args, String rawMessage) {
      String str2 = "§6[§9" + Augustus.getInstance().getName() + "§6] ";
      mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("\n\n" + str2 + "§7Help commands:"));

      for (Command command2 : (Iterable<Command>)(Augustus.getInstance().getCommandManager().getCommands())) {
         command2.helpMessage();
      }

   }

   public void helpMessage() {
   }

}
