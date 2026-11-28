package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandDeOp extends CommandBase {

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getConfigurationManager().getOppedPlayerNames()) : null;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public String getCommandName() {
      return "deop";
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.deop.usage";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length == 1 && strings[0].length() > 0) {
         MinecraftServer server = MinecraftServer.getServer();
         GameProfile gameProfileFromName = server.getConfigurationManager().getOppedPlayers().getGameProfileFromName(strings[0]);
         if (gameProfileFromName == null) {
            throw new CommandException("commands.deop.failed", new Object[]{strings[0]});
         } else {
            server.getConfigurationManager().removeOp(gameProfileFromName);
            notifyOperators(commandSender, this, "commands.deop.success", new Object[]{strings[0]});
         }
      } else {
         throw new WrongUsageException("commands.deop.usage", new Object[0]);
      }
   }
}
