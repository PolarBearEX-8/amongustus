package net.minecraft.command.server;

import java.util.List;
import java.util.regex.Matcher;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandPardonIp extends CommandBase {

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getConfigurationManager().getBannedIPs().getKeys()) : null;
   }

   @Override
   public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
      return MinecraftServer.getServer().getConfigurationManager().getBannedIPs().isLanServer() && super.canCommandSenderUseCommand(commandSender);
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.unbanip.usage";
   }

   @Override
   public String getCommandName() {
      return "pardon-ip";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length == 1 && strings[0].length() > 1) {
         if (CommandBanIp.IP_PATTERN.matcher(strings[0]).matches()) {
            MinecraftServer.getServer().getConfigurationManager().getBannedIPs().removeEntry(strings[0]);
            notifyOperators(commandSender, this, "commands.unbanip.success", new Object[]{strings[0]});
         } else {
            throw new SyntaxErrorException("commands.unbanip.invalid", new Object[0]);
         }
      } else {
         throw new WrongUsageException("commands.unbanip.usage", new Object[0]);
      }
   }
}
