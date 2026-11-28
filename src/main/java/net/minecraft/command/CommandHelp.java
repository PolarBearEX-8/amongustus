package net.minecraft.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

public class CommandHelp extends CommandBase {

   @Override
   public String getCommandName() {
      return "help";
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.help.usage";
   }

   public List getSortedPossibleCommands(ICommandSender commandSender) {
      List possibleCommands = MinecraftServer.getServer().getCommandManager().getPossibleCommands(commandSender);
      Collections.sort(possibleCommands);
      return possibleCommands;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 0;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      List sortedPossibleCommands = this.getSortedPossibleCommands(commandSender);
      int n = (sortedPossibleCommands.size() - 1) / 7;
      int n2 = 0;

      try {
         n2 = strings.length == 0 ? 0 : parseInt(strings[0], 1, n + 1) - 1;
      } catch (NumberInvalidException e) {
         ICommand command = (ICommand)this.getCommands().get(strings[0]);
         if (command != null) {
            throw new WrongUsageException(command.getCommandUsage(commandSender), new Object[0]);
         }

         if (MathHelper.parseIntWithDefault(strings[0], -1) != -1) {
            throw e;
         }

         throw new CommandNotFoundException();
      }

      int n3 = Math.min((n2 + 1) * 7, sortedPossibleCommands.size());
      ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation("commands.help.header", new Object[]{n2 + 1, n + 1});
      chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
      commandSender.addChatMessage(chatComponentTranslation);

      for (int i = n2 * 7; i < n3; i++) {
         ICommand command2 = (ICommand)sortedPossibleCommands.get(i);
         ChatComponentTranslation chatComponentTranslation2 = new ChatComponentTranslation(command2.getCommandUsage(commandSender), new Object[0]);
         chatComponentTranslation2.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + command2.getCommandName() + " "));
         commandSender.addChatMessage(chatComponentTranslation2);
      }

      if (n2 == 0 && commandSender instanceof EntityPlayer) {
         ChatComponentTranslation chatComponentTranslation3 = new ChatComponentTranslation("commands.help.footer", new Object[0]);
         chatComponentTranslation3.getChatStyle().setColor(EnumChatFormatting.GREEN);
         commandSender.addChatMessage(chatComponentTranslation3);
      }
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      if (strings.length == 1) {
         Set keys = this.getCommands().keySet();
         return getListOfStringsMatchingLastWord(strings, (String[])keys.toArray(new String[keys.size()]));
      } else {
         return null;
      }
   }

   @Override
   public List getCommandAliases() {
      return Arrays.asList("?");
   }

   public Map getCommands() {
      return MinecraftServer.getServer().getCommandManager().getCommands();
   }
}
