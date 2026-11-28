package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.Date;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.util.BlockPos;

public class CommandBanPlayer extends CommandBase {

   @Override
   public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
      return MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().isLanServer() && super.canCommandSenderUseCommand(commandSender);
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length >= 1 ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames()) : null;
   }

   @Override
   public String getCommandName() {
      return "ban";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length >= 1 && strings[0].length() > 0) {
         MinecraftServer server = MinecraftServer.getServer();
         GameProfile gameProfileForUsername = server.getPlayerProfileCache().getGameProfileForUsername(strings[0]);
         if (gameProfileForUsername == null) {
            throw new CommandException("commands.ban.failed", new Object[]{strings[0]});
         } else {
            String str = null;
            if (strings.length >= 2) {
               str = getChatComponentFromNthArg(commandSender, strings, 1).getUnformattedText();
            }

            UserListBansEntry userListBansEntry = new UserListBansEntry(gameProfileForUsername, (Date)null, commandSender.getName(), (Date)null, str);
            server.getConfigurationManager().getBannedPlayers().addEntry(userListBansEntry);
            EntityPlayerMP playerByUsername = server.getConfigurationManager().getPlayerByUsername(strings[0]);
            if (playerByUsername != null) {
               playerByUsername.playerNetServerHandler.kickPlayerFromServer("You are banned from this server.");
            }

            notifyOperators(commandSender, this, "commands.ban.success", new Object[]{strings[0]});
         }
      } else {
         throw new WrongUsageException("commands.ban.usage", new Object[0]);
      }
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.ban.usage";
   }
}
