package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandPardonPlayer extends CommandBase {

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length == 1 && strings[0].length() > 0) {
         MinecraftServer server = MinecraftServer.getServer();
         GameProfile isUsernameBanned = server.getConfigurationManager().getBannedPlayers().isUsernameBanned(strings[0]);
         if (isUsernameBanned == null) {
            throw new CommandException("commands.unban.failed", new Object[]{strings[0]});
         } else {
            server.getConfigurationManager().getBannedPlayers().removeEntry(isUsernameBanned);
            notifyOperators(commandSender, this, "commands.unban.success", new Object[]{strings[0]});
         }
      } else {
         throw new WrongUsageException("commands.unban.usage", new Object[0]);
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.unban.usage";
   }

   @Override
   public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
      return MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().isLanServer() && super.canCommandSenderUseCommand(commandSender);
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().getKeys())
         : null;
   }

   @Override
   public String getCommandName() {
      return "pardon";
   }
}
