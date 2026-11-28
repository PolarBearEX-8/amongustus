package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class CommandBroadcast extends CommandBase {

   @Override
   public String getCommandName() {
      return "say";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length > 0 && strings[0].length() > 0) {
         IChatComponent component = getChatComponentFromNthArg(commandSender, strings, 0, true);
         MinecraftServer.getServer()
            .getConfigurationManager()
            .sendChatMsg(new ChatComponentTranslation("chat.type.announcement", new Object[]{commandSender.getDisplayName(), component}));
      } else {
         throw new WrongUsageException("commands.say.usage", new Object[0]);
      }
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.say.usage";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length >= 1 ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames()) : null;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 1;
   }
}
