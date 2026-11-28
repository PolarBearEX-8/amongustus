package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class CommandWhitelist extends CommandBase {

   @Override
   public String getCommandName() {
      return "whitelist";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 1) {
         throw new WrongUsageException("commands.whitelist.usage", new Object[0]);
      } else {
         MinecraftServer server = MinecraftServer.getServer();
         if (strings[0].equals("on")) {
            server.getConfigurationManager().setWhiteListEnabled(true);
            notifyOperators(commandSender, this, "commands.whitelist.enabled", new Object[0]);
         } else if (strings[0].equals("off")) {
            server.getConfigurationManager().setWhiteListEnabled(false);
            notifyOperators(commandSender, this, "commands.whitelist.disabled", new Object[0]);
         } else if (strings[0].equals("list")) {
            commandSender.addChatMessage(
               new ChatComponentTranslation(
                  "commands.whitelist.list",
                  new Object[]{
                     server.getConfigurationManager().getWhitelistedPlayerNames().length, server.getConfigurationManager().getAvailablePlayerDat().length
                  }
               )
            );
            String[] whitelistedPlayerNames = server.getConfigurationManager().getWhitelistedPlayerNames();
            commandSender.addChatMessage(new ChatComponentText(joinNiceString(whitelistedPlayerNames)));
         } else if (strings[0].equals("add")) {
            if (strings.length < 2) {
               throw new WrongUsageException("commands.whitelist.add.usage", new Object[0]);
            }

            GameProfile gameProfileForUsername = server.getPlayerProfileCache().getGameProfileForUsername(strings[1]);
            if (gameProfileForUsername == null) {
               throw new CommandException("commands.whitelist.add.failed", new Object[]{strings[1]});
            }

            server.getConfigurationManager().addWhitelistedPlayer(gameProfileForUsername);
            notifyOperators(commandSender, this, "commands.whitelist.add.success", new Object[]{strings[1]});
         } else if (strings[0].equals("remove")) {
            if (strings.length < 2) {
               throw new WrongUsageException("commands.whitelist.remove.usage", new Object[0]);
            }

            GameProfile byName = server.getConfigurationManager().getWhitelistedPlayers().getByName(strings[1]);
            if (byName == null) {
               throw new CommandException("commands.whitelist.remove.failed", new Object[]{strings[1]});
            }

            server.getConfigurationManager().removePlayerFromWhitelist(byName);
            notifyOperators(commandSender, this, "commands.whitelist.remove.success", new Object[]{strings[1]});
         } else if (strings[0].equals("reload")) {
            server.getConfigurationManager().loadWhiteList();
            notifyOperators(commandSender, this, "commands.whitelist.reloaded", new Object[0]);
         }
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      if (strings.length == 1) {
         return getListOfStringsMatchingLastWord(strings, new String[]{"on", "off", "list", "add", "remove", "reload"});
      } else {
         if (strings.length == 2) {
            if (strings[0].equals("remove")) {
               return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getConfigurationManager().getWhitelistedPlayerNames());
            }

            if (strings[0].equals("add")) {
               return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getPlayerProfileCache().getUsernames());
            }
         }

         return null;
      }
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.whitelist.usage";
   }
}
